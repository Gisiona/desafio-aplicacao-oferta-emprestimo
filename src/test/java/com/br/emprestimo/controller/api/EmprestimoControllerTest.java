package com.br.emprestimo.controller.api;

import com.br.emprestimo.controller.dto.ClienteDto;
import com.br.emprestimo.controller.dto.EmprestimoResponseDto;
import com.br.emprestimo.controller.dto.ProdutoEmprestimoDto;
import com.br.emprestimo.controller.dto.SolicitacaoEmprestimoRequestDto;
import com.br.emprestimo.controller.mapper.ClienteMapper;
import com.br.emprestimo.controller.mapper.EmprestimoMapper;
import com.br.emprestimo.model.ClienteEmprestimoModel;
import com.br.emprestimo.model.OfertaEmprestimoModel;
import com.br.emprestimo.model.TaxaEmprestimoModel;
import com.br.emprestimo.model.TipoEmprestimoModel;
import com.br.emprestimo.service.impl.OfertaProdutoEmprestimoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@WebMvcTest @Slf4j
class EmprestimoControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private OfertaProdutoEmprestimoServiceImpl service;

    @MockBean
    private ClienteMapper clienteMapper;

    @MockBean
    private EmprestimoMapper emprestimoMapper;

    @Autowired
    private EmprestimoController emprestimoController;

    private static String BASE_URL = "/emprestimos/v1";
    private static String BASE_PATH_OFERTA = "/ofertas_produto_disponivel";

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(this.emprestimoController);
        this.mockMvc = MockMvcBuilders.standaloneSetup(emprestimoController).build();
    }

    @Test
    public void createMockOfertaProdutoEmprestimoServiceImplTest() {
        Assertions.assertNotNull(service);
    }

    @Test
    public void createMockClienteMapperTest() {
        Assertions.assertNotNull(clienteMapper);
    }

    @Test
    public void createMockEmprestimoMapperTest() {
        Assertions.assertNotNull(emprestimoMapper);
    }

    @Test
    public void createMockEmprestimoControllerTest() {
        Assertions.assertNotNull(emprestimoController);
    }

    @Test
    public void quandoRecursoPostSolitarEmprestimoForExeutado_DeveRetornarStatusCode200Test() throws Exception {
        ObjectMapper objectMapperq = new ObjectMapper();

        SolicitacaoEmprestimoRequestDto dto = getEmprestimoRequestDto();

        String body = objectMapperq.writeValueAsString(dto);

        log.info("Body: {}", body);
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + BASE_PATH_OFERTA)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                //.andExpect(MockMvcResultMatchers.content().string("Retornou conteudo"))
                .andExpect(MockMvcResultMatchers.header().exists("Location"))
                .andExpect(MockMvcResultMatchers.header().string("Location", Matchers.containsString(BASE_URL + BASE_PATH_OFERTA)));

        log.info("Response: {}", response.toString());
    }

    @Test
    public void quandoRecursoPostSolitarEmprestimoForExeutadoSemInformarValorRendaMensal_NaoDeveRetornarListaProdutoOfertaEmprestimoTest() throws Exception {
        ObjectMapper objectMapperq = new ObjectMapper();

        UUID idSolicitacaoUUID = UUID.randomUUID();

        SolicitacaoEmprestimoRequestDto dto = getEmprestimoRequestDto();
        dto.getCliente().setValorRenda(0);

        String body = objectMapperq.writeValueAsString(dto);

        ClienteEmprestimoModel clienteEmprestimoModel = ClienteEmprestimoModel
                .builder()
                .cpf("12345678911")
                .valorRenda(1)
                .uf("SP")
                .nome("Teste Sem Renda")
                .build();

        OfertaEmprestimoModel produtoEmprestimoDisponivel = OfertaEmprestimoModel
                .builder()
                .tipoEmprestimo(TipoEmprestimoModel.EMPRESTIMO_PESSOAL)
                .cliente(clienteEmprestimoModel)
                .taxaJuros(TaxaEmprestimoModel.builder().taxa(4.0).build())
                .build();

        ProdutoEmprestimoDto produtoEmprestimoDto = ProdutoEmprestimoDto
                .builder()
                .tipoEmprestimo(produtoEmprestimoDisponivel.getTipoEmprestimo().toString())
                .taxaJuros(produtoEmprestimoDisponivel.getTaxaJuros().getTaxa())
                .build();

        EmprestimoResponseDto emprestimoResponseDto = EmprestimoResponseDto
                .builder()
                .cliente(getClienteDto())
                .emprestimos(Arrays.asList(produtoEmprestimoDto))
                .dataSolicitacao(LocalDateTime.now())
                .codigoSolicitacao(idSolicitacaoUUID)
                .build();

        Mockito
                .when(service.produtoEmprestimoDisponivel(clienteEmprestimoModel))
                .thenReturn(Arrays.asList(produtoEmprestimoDisponivel));

        Mockito
                .when(emprestimoMapper.toEmprestimoResponseDto(Arrays.asList(produtoEmprestimoDisponivel), getClienteDto()))
                .thenReturn(emprestimoResponseDto);

        log.info("Body: {}", body);
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + BASE_PATH_OFERTA)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                //.andExpect(MockMvcResultMatchers.jsonPath("$.{}", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.header().exists("Location"))
                .andExpect(MockMvcResultMatchers.header().string("Location", Matchers.containsString(BASE_URL + BASE_PATH_OFERTA + "/0")));

        //Mockito.verify(service, Mockito.times(1)).produtoEmprestimoDisponivel(clienteEmprestimoModel);
        //Mockito.verify(emprestimoMapper, Mockito.times(1)).toEmprestimoResponseDto(Arrays.asList(produtoEmprestimoDisponivel), getClienteDto());

        log.info("Response: {}", response);
    }

    private SolicitacaoEmprestimoRequestDto getEmprestimoRequestDto() {
        ClienteDto clienteDto = getClienteDto();

        SolicitacaoEmprestimoRequestDto dto = new SolicitacaoEmprestimoRequestDto();
        dto.setCliente(clienteDto);
        return dto;
    }

    private ClienteDto getClienteDto() {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setCpf("1234");
        clienteDto.setNome("Teste de Unidade");
        clienteDto.setUf("BA");
        clienteDto.setValorRenda(3000);
        //clienteDto.setDataNascimento(LocalDate.now().minusYears(20L));
        return clienteDto;
    }
}