package com.br.emprestimo.service;

import com.br.emprestimo.model.SimulacaoEmprestimoModel;

public interface SimulacaoParcelamentoEmprestimoService {
    Object calcularSimulacao(SimulacaoEmprestimoModel simulacaoModel);
}
