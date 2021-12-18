package com.br.emprestimo.service;

import com.br.emprestimo.model.ClienteEmprestimoModel;
import com.br.emprestimo.model.OfertaEmprestimoModel;

import java.util.Optional;

public interface OfertaProdutoEmprestimoService {
    Optional<OfertaEmprestimoModel> calcularOferta(ClienteEmprestimoModel clienteModel);
}
