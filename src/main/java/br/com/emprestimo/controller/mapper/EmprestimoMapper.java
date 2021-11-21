package br.com.emprestimo.controller.mapper;

import br.com.emprestimo.controller.dto.ClienteDto;
import br.com.emprestimo.controller.dto.EmprestimoResponseDto;
import br.com.emprestimo.controller.dto.ProdutoEmprestimoDto;
import br.com.emprestimo.model.EmprestimoModel;
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
