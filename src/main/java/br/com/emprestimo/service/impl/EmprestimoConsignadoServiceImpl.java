package br.com.emprestimo.service.impl;

import br.com.emprestimo.model.ClienteModel;
import br.com.emprestimo.model.EmprestimoModel;
import br.com.emprestimo.model.TaxaEmprestimoModel;
import br.com.emprestimo.model.TipoEmprestimo;
import br.com.emprestimo.service.EmprestimoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoConsignadoServiceImpl implements EmprestimoService {
    private static final Double TAXA_JURO_EMPRESTIMO_PESSOAL = 2.0;

    @Override
    public Optional<EmprestimoModel> precessar(ClienteModel clienteModel) {
        List<Boolean> listaRegras = new ArrayList<>();
        listaRegras.add(clienteModel.getValorRenda() >= 5000);
        listaRegras.add(clienteModel.getIdade() < 30);

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
                    .tipoEmprestimo(TipoEmprestimo.EMPRESTIMO_CONSIGNADO)
                    .taxaJuros(TaxaEmprestimoModel.builder().taxa(TAXA_JURO_EMPRESTIMO_PESSOAL).build())
                    .build());
        }
        return Optional.ofNullable(null);
    }
}
