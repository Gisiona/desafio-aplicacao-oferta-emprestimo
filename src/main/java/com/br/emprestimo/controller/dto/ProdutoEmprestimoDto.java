package com.br.emprestimo.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data @Getter @Builder
public class ProdutoEmprestimoDto {
    @JsonProperty("numero_tipo_emprestimo")
    private String numeroTipoEmprestimo;

    @JsonProperty("tipo_emprestimo")
    private String tipoEmprestimo;

    @JsonProperty("taxa")
    private Double taxaJuros;
}
