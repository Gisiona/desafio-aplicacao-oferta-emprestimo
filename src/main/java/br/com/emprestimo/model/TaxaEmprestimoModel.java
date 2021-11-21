package br.com.emprestimo.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data @Getter @Setter @Builder
public class TaxaEmprestimoModel {
    public Double taxa;
}
