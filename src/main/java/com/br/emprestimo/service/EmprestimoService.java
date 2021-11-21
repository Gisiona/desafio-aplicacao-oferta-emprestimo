package com.br.emprestimo.service;

import com.br.emprestimo.model.ClienteModel;
import com.br.emprestimo.model.EmprestimoModel;

import java.util.Optional;

public interface EmprestimoService {
    Optional<EmprestimoModel> precessar(ClienteModel clienteModel);
}
