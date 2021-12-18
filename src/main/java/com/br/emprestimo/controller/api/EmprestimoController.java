package com.br.emprestimo.controller.api;

import com.br.emprestimo.controller.dto.SolicitacaoEmprestimoRequestDto;
import com.br.emprestimo.controller.dto.EmprestimoResponseDto;
import com.br.emprestimo.controller.dto.SimulacaoParcelamentoRequestDto;
import com.br.emprestimo.controller.dto.SimulacaoParcelamentoResponseDto;
import com.br.emprestimo.controller.mapper.ClienteMapper;
import com.br.emprestimo.controller.mapper.EmprestimoMapper;
import com.br.emprestimo.model.OfertaEmprestimoModel;
import com.br.emprestimo.service.impl.OfertaProdutoEmprestimoServiceImpl;
import com.br.emprestimo.service.impl.SimulacaoParcelamentoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/emprestimos/v1")
public class EmprestimoController {

    private final OfertaProdutoEmprestimoServiceImpl service;
    SimulacaoParcelamentoServiceImpl simulacaoParcelamentoService;
    private final ClienteMapper clienteMapper;
    private final EmprestimoMapper emprestimoMapper;
    private static final String BASE_URL = "/emprestimos/v1/ofertas_produto_disponivel/";

    @Autowired
    public EmprestimoController(OfertaProdutoEmprestimoServiceImpl emprestimoService, ClienteMapper clienteMapper, EmprestimoMapper emprestimoMapper){
        this.service = emprestimoService;
        this.clienteMapper = clienteMapper;
        this.emprestimoMapper = emprestimoMapper;
    }

    @ResponseStatus(HttpStatus.OK )
    @PostMapping("/ofertas_produto_disponivel")
    public ResponseEntity<EmprestimoResponseDto> produtosDisponivel(@RequestBody @Valid SolicitacaoEmprestimoRequestDto emprestimoRequestDto) {
        log.info("Emprestimo Solicitado: {}", emprestimoRequestDto);

        List<OfertaEmprestimoModel> produtoEmprestimoDisponivel = service.produtoEmprestimoDisponivel(clienteMapper.toClienteModel(emprestimoRequestDto.getCliente()));
        EmprestimoResponseDto emprestimoResponseDto = emprestimoMapper.toEmprestimoResponseDto(produtoEmprestimoDisponivel, emprestimoRequestDto.getCliente());

        log.info("Emprestimo Disponivel: {}", emprestimoResponseDto);

        HttpHeaders headers = getHttpHeaders(emprestimoResponseDto);

        return new ResponseEntity<>(emprestimoResponseDto, headers, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/simulacoes_parcelamento")
    public ResponseEntity<SimulacaoParcelamentoResponseDto> simulacaoParcelamento(@RequestBody @Valid SimulacaoParcelamentoRequestDto simulacaoParcelamentoRequestDto) {
        log.info("Emprestimo Solicitado: {}", simulacaoParcelamentoRequestDto);

       HttpHeaders headers = getHttpHeaders(null);

        SimulacaoParcelamentoResponseDto simulacaoParcelamentoResponseDto = SimulacaoParcelamentoResponseDto
                .builder()
                .codigoSimulacao(UUID.randomUUID())
                .numeroSolicitacaoEmprestimo(simulacaoParcelamentoRequestDto.getNumeroSolicitacaoEmprestimo())
                .tipoEmprestimo(simulacaoParcelamentoRequestDto.getTipoEmprestimo())
                .dataSimulacao(LocalDateTime.now())
                .cliente(simulacaoParcelamentoRequestDto.getCliente())
                .valorEmprestimoSolicitado(simulacaoParcelamentoRequestDto.getValorSolicitado())
                .build();

        return new ResponseEntity<>(simulacaoParcelamentoResponseDto, null, HttpStatus.CREATED);
    }

    private HttpHeaders getHttpHeaders(EmprestimoResponseDto emprestimoResponseDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setLocation(URI.create(BASE_URL + (emprestimoResponseDto != null ? emprestimoResponseDto.getCodigoSolicitacao() : 0)));
        log.info("Header Controller: {}", headers);
        return headers;
    }
}
