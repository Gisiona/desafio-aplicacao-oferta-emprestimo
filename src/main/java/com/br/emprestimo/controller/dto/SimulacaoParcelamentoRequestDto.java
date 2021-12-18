package com.br.emprestimo.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Data
@Getter
@RequiredArgsConstructor
public class SimulacaoParcelamentoRequestDto {

    @JsonProperty("numero_solicitacao_emprestimo")
    private String numeroSolicitacaoEmprestimo;

    @JsonProperty("cliente")
    private ClienteDto cliente;

    @JsonProperty("valor_emprestimo_solicitado")
    private Double valorSolicitado;

    @JsonProperty("tipo_emprestimo")
    private String tipoEmprestimo;
}
