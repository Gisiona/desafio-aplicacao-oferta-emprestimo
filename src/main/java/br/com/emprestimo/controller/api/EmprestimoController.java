package br.com.emprestimo.controller.api;

import br.com.emprestimo.controller.dto.ClienteDto;
import br.com.emprestimo.controller.dto.EmprestimoRequestDto;
import br.com.emprestimo.controller.dto.EmprestimoResponseDto;
import br.com.emprestimo.controller.mapper.ClienteMapper;
import br.com.emprestimo.controller.mapper.EmprestimoMapper;
import br.com.emprestimo.model.EmprestimoModel;
import br.com.emprestimo.service.impl.ProdutoEmprestimoService;
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
        log.info("Cliente: {}", emprestimoRequestDto);
        List<EmprestimoModel> produtoEmprestimoDisponivel = service.produtoEmprestimoDisponivel(clienteMapper.toClienteModel(emprestimoRequestDto.getCliente()));
        log.info("Emprestimo Disponivel: {}", produtoEmprestimoDisponivel);
        EmprestimoResponseDto emprestimoResponseDto = emprestimoMapper.toEmprestimoResponseDto(produtoEmprestimoDisponivel, emprestimoRequestDto.getCliente());
        return ResponseEntity.ok(emprestimoResponseDto);
    }
}
