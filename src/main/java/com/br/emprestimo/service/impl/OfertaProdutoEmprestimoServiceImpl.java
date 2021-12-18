package com.br.emprestimo.service.impl;

import com.br.emprestimo.model.ClienteEmprestimoModel;
import com.br.emprestimo.model.OfertaEmprestimoModel;
import com.br.emprestimo.service.OfertaProdutoEmprestimoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OfertaProdutoEmprestimoServiceImpl {
    private List<OfertaProdutoEmprestimoService> emprestimoServices;

    @Autowired
    public OfertaProdutoEmprestimoServiceImpl(List<OfertaProdutoEmprestimoService> emprestimoServices) {
        this.emprestimoServices = emprestimoServices;
    }

    public List<OfertaEmprestimoModel> produtoEmprestimoDisponivel(ClienteEmprestimoModel clienteModel) {
        List<OfertaEmprestimoModel> emprestimoProdutos = new ArrayList();
        for (OfertaProdutoEmprestimoService emprestimo: emprestimoServices) {
            log.info("Implementação EmprestimoService: {}", emprestimo);
            Optional<OfertaEmprestimoModel> model = emprestimo.calcularOferta(clienteModel);
            if(model.isPresent()) {
                emprestimoProdutos.add(model.get());
            }
        }
        return emprestimoProdutos;
    }
}
