package br.com.emprestimo.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data @Getter @Builder
public class ProdutoEmprestimoDto {
    @JsonProperty("tipo_emprestimo")
    private String tipoEmprestimo;

    @JsonProperty("taxa")
    private Double taxaJuros;
}
