package com.br.emprestimo.service.impl;

import com.br.emprestimo.model.ClienteModel;
import com.br.emprestimo.model.EmprestimoModel;
import com.br.emprestimo.service.EmprestimoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProdutoEmprestimoService {
    private List<EmprestimoService> emprestimoServices;

    @Autowired
    public ProdutoEmprestimoService(List<EmprestimoService> emprestimoServices) {
        this.emprestimoServices = emprestimoServices;
    }

    public List<EmprestimoModel> produtoEmprestimoDisponivel(ClienteModel clienteModel) {
        List<EmprestimoModel> emprestimoProdutos = new ArrayList();
        for (EmprestimoService emprestimo: emprestimoServices) {
            log.info("Implementação EmprestimoService: {}", emprestimo);
            Optional<EmprestimoModel> model = emprestimo.precessar(clienteModel);
            if(model.isPresent()) {
                emprestimoProdutos.add(model.get());
            }
        }
        return emprestimoProdutos;
    }
}
