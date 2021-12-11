package com.br.emprestimo.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data @Builder
public class EmprestimoResponseDto {
    @JsonProperty("numero_solicitacao")
    private UUID codigoSolicitacao;

    @JsonProperty("data_solicitacao")
    private LocalDateTime dataSolicitacao;

    @JsonProperty("cliente")
    ClienteDto cliente;

    @JsonProperty("produtos_emprestimo_disponivel")
    List<ProdutoEmprestimoDto> emprestimos;
}
