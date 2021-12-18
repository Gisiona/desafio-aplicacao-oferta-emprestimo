package com.br.emprestimo.model;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class OfertaEmprestimoModel {
    private TipoEmprestimoModel tipoEmprestimo;
    private TaxaEmprestimoModel taxaJuros;
    private ClienteEmprestimoModel cliente;
}
