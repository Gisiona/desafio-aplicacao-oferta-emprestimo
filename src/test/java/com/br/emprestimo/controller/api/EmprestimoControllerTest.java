package com.br.emprestimo.controller.api;

import com.br.emprestimo.controller.dto.ClienteDto;
import com.br.emprestimo.controller.dto.EmprestimoRequestDto;
import com.br.emprestimo.controller.mapper.ClienteMapper;
import com.br.emprestimo.controller.mapper.EmprestimoMapper;
import com.br.emprestimo.service.impl.ProdutoEmprestimoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebMvcTest @Slf4j
class EmprestimoControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private ProdutoEmprestimoService service;

    @MockBean
    private ClienteMapper clienteMapper;

    @MockBean
    private EmprestimoMapper emprestimoMapper;

    @Autowired
    private EmprestimoController emprestimoController;

    private static String BASE_URL = "/emprestimos/v1/produtos_oferta_disponivel";

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(this.emprestimoController);
        this.mockMvc = MockMvcBuilders.standaloneSetup(emprestimoController).build();
    }

    @Test
    public void quandoRecursoPostSolitarEmprestimoForExeutado_DeveRetornarStatusCode200Test() throws Exception {
        ObjectMapper objectMapperq = new ObjectMapper();

        EmprestimoRequestDto dto = getEmprestimoRequestDto();

        String body = objectMapperq.writeValueAsString(dto);

        log.info("Body: {}", body);
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(MockMvcResultMatchers.status().isOk());
        log.info("Response: {}", response);
    }

    private EmprestimoRequestDto getEmprestimoRequestDto() {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setCpf("1234");
        clienteDto.setIdade(19);
        clienteDto.setNome("Teste de Unidade");
        clienteDto.setUf("BA");
        clienteDto.setValorRenda(3000);

        EmprestimoRequestDto dto = new EmprestimoRequestDto();
        dto.setCliente(clienteDto);
        return dto;
    }
}