package com.br.emprestimo.service.impl;

import com.br.emprestimo.model.ClienteModel;
import com.br.emprestimo.model.EmprestimoModel;
import com.br.emprestimo.model.TaxaEmprestimoModel;
import com.br.emprestimo.model.TipoEmprestimo;
import com.br.emprestimo.service.EmprestimoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoGarantiaServiceImpl implements EmprestimoService {
    private static final Double TAXA_JURO_EMPRESTIMO_PESSOAL = 3.0;

    @Override
    public Optional<EmprestimoModel> precessar(ClienteModel clienteModel) {
        List<Boolean> listaRegras = new ArrayList<>();
        listaRegras.add(clienteModel.getValorRenda() > 3000);
        listaRegras.add(clienteModel.getUf().equals("SP"));

        boolean atendeRegra = true;

        for (boolean regra: listaRegras) {
            if(!regra) {
                atendeRegra = regra;
            }
        }

        if (atendeRegra) {
            return Optional.of(EmprestimoModel
                    .builder()
                    .cliente(clienteModel)
                    .tipoEmprestimo(TipoEmprestimo.EMPRESTIMO_GARANTIA)
                    .taxaJuros(TaxaEmprestimoModel.builder().taxa(TAXA_JURO_EMPRESTIMO_PESSOAL).build())
                    .build());
        }
        return Optional.ofNullable(null);
    }
}
