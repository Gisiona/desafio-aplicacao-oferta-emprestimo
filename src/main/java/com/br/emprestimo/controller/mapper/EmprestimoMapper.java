package com.br.emprestimo.controller.mapper;

import com.br.emprestimo.controller.dto.ClienteDto;
import com.br.emprestimo.controller.dto.EmprestimoResponseDto;
import com.br.emprestimo.controller.dto.ProdutoEmprestimoDto;
import com.br.emprestimo.model.EmprestimoModel;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
public class EmprestimoMapper {
    public EmprestimoResponseDto toEmprestimoResponseDto(List<EmprestimoModel> emprestimoModels, ClienteDto clienteRequestDto) {
        return EmprestimoResponseDto
                .builder()
                .codigoSolicitacao(UUID.randomUUID())
                .dataSolicitacao(LocalDateTime.now())
                .cliente(clienteRequestDto)
                .emprestimos(toProdutoEmprestimoDto(emprestimoModels))
                .build();
    }

    public ProdutoEmprestimoDto toProdutoEmprestimoDto(EmprestimoModel emprestimoModel) {
        return ProdutoEmprestimoDto
                .builder()
                .tipoEmprestimo(emprestimoModel.getTipoEmprestimo().toString())
                .taxaJuros(emprestimoModel.getTaxaJuros().getTaxa())
                .build();
    }

    public List<ProdutoEmprestimoDto> toProdutoEmprestimoDto(List<EmprestimoModel> emprestimoModels) {
        List<ProdutoEmprestimoDto> produtoEmprestimoDtos = new ArrayList<>();

        for (EmprestimoModel emprestimoModel : emprestimoModels) {
                produtoEmprestimoDtos.add(toProdutoEmprestimoDto(emprestimoModel));
        }
        return produtoEmprestimoDtos;
    }
}
