package br.com.emprestimo.service;

import br.com.emprestimo.model.ClienteModel;
import br.com.emprestimo.model.EmprestimoModel;

import java.util.Optional;

public interface EmprestimoService {
    Optional<EmprestimoModel> precessar(ClienteModel clienteModel);
}
