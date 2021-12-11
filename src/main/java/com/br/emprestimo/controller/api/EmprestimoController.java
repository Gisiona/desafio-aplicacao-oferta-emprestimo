package com.br.emprestimo.controller.api;

import com.br.emprestimo.controller.dto.ClienteDto;
import com.br.emprestimo.controller.dto.EmprestimoRequestDto;
import com.br.emprestimo.controller.dto.EmprestimoResponseDto;
import com.br.emprestimo.controller.mapper.ClienteMapper;
import com.br.emprestimo.controller.mapper.EmprestimoMapper;
import com.br.emprestimo.model.EmprestimoModel;
import com.br.emprestimo.service.impl.ProdutoEmprestimoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/emprestimos/v1")
public class EmprestimoController {

    private final ProdutoEmprestimoService service;
    private final ClienteMapper clienteMapper;
    private final EmprestimoMapper emprestimoMapper;
    private static final String BASE_URL = "/emprestimos/v1/produtos_oferta_disponivel/";

    @Autowired
    public EmprestimoController(ProdutoEmprestimoService emprestimoService, ClienteMapper clienteMapper, EmprestimoMapper emprestimoMapper){
        this.service = emprestimoService;
        this.clienteMapper = clienteMapper;
        this.emprestimoMapper = emprestimoMapper;
    }

    @PostMapping("/produtos_oferta_disponivel")
    public ResponseEntity<EmprestimoResponseDto> produtosDisponivel(@RequestBody @Valid EmprestimoRequestDto emprestimoRequestDto) {
        log.info("Emprestimo Solicitado: {}", emprestimoRequestDto);

        List<EmprestimoModel> produtoEmprestimoDisponivel = service.produtoEmprestimoDisponivel(clienteMapper.toClienteModel(emprestimoRequestDto.getCliente()));
        EmprestimoResponseDto emprestimoResponseDto = emprestimoMapper.toEmprestimoResponseDto(produtoEmprestimoDisponivel, emprestimoRequestDto.getCliente());

        log.info("Emprestimo Disponivel: {}", emprestimoResponseDto);

        return new ResponseEntity<EmprestimoResponseDto>(emprestimoResponseDto, HttpStatus.OK);
    }

    @GetMapping("/produtos_oferta_disponivel")
    public ResponseEntity<EmprestimoResponseDto> produtosOferta() {
        log.info("Emprestimo Solicitado: {}");

        EmprestimoResponseDto emprestimoResponseDto = EmprestimoResponseDto
                .builder()
                .codigoSolicitacao(UUID.randomUUID())
                .emprestimos(new ArrayList<>())
                .cliente(new ClienteDto())
                .build();
        log.info("Emprestimo Disponivel: {}", emprestimoResponseDto);
        return ResponseEntity.ok(emprestimoResponseDto);
    }
}
