package com.br.emprestimo.controller.mapper;

import com.br.emprestimo.controller.dto.ClienteDto;
import com.br.emprestimo.controller.dto.EmprestimoResponseDto;
import com.br.emprestimo.controller.dto.ProdutoEmprestimoDto;
import com.br.emprestimo.model.OfertaEmprestimoModel;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
public class EmprestimoMapper {
    public EmprestimoResponseDto toEmprestimoResponseDto(List<OfertaEmprestimoModel> emprestimoModel, ClienteDto clienteRequestDto) {
        return EmprestimoResponseDto
                .builder()
                .codigoSolicitacao(UUID.randomUUID())
                .dataSolicitacao(LocalDateTime.now())
                .cliente(clienteRequestDto)
                .emprestimos(toProdutoEmprestimoDto(emprestimoModel))
                .build();
    }

    public ProdutoEmprestimoDto toProdutoEmprestimoDto(OfertaEmprestimoModel emprestimoModel) {
        return ProdutoEmprestimoDto
                .builder()
                .numeroTipoEmprestimo(UUID.randomUUID().toString())
                .tipoEmprestimo(emprestimoModel.getTipoEmprestimo().toString())
                .taxaJuros(emprestimoModel.getTaxaJuros().getTaxa())
                .build();
    }

    public List<ProdutoEmprestimoDto> toProdutoEmprestimoDto(List<OfertaEmprestimoModel> emprestimoModels) {
        List<ProdutoEmprestimoDto> produtoEmprestimoDtos = new ArrayList<>();

        for (OfertaEmprestimoModel emprestimoModel : emprestimoModels) {
                produtoEmprestimoDtos.add(toProdutoEmprestimoDto(emprestimoModel));
        }
        return produtoEmprestimoDtos;
    }
}
