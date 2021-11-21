package com.br.emprestimo.controller.api;

import com.br.emprestimo.controller.dto.EmprestimoRequestDto;
import com.br.emprestimo.controller.dto.EmprestimoResponseDto;
import com.br.emprestimo.controller.mapper.ClienteMapper;
import com.br.emprestimo.controller.mapper.EmprestimoMapper;
import com.br.emprestimo.model.EmprestimoModel;
import com.br.emprestimo.service.impl.ProdutoEmprestimoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emprestimos/v1")
public class EmprestimoController {

    private ProdutoEmprestimoService service;
    private ClienteMapper clienteMapper;
    private EmprestimoMapper emprestimoMapper;

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
        return ResponseEntity.ok(emprestimoResponseDto);
    }
}
