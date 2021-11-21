package com.br.emprestimo.model;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class EmprestimoModel {
    private TipoEmprestimo tipoEmprestimo;
    private TaxaEmprestimoModel taxaJuros;
    private ClienteModel cliente;
}
