package com.br.emprestimo.controller.mapper;

import com.br.emprestimo.controller.dto.ClienteDto;
import com.br.emprestimo.model.ClienteEmprestimoModel;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClienteMapper {
    public ClienteEmprestimoModel toClienteModel(ClienteDto clienteRequestDto) {
        return ClienteEmprestimoModel
                .builder()
                .cpf(clienteRequestDto.getCpf())
                .dataNascimento(clienteRequestDto.getDataNascimento())
                .nome(clienteRequestDto.getNome())
                .uf(clienteRequestDto.getUf())
                .valorRenda(clienteRequestDto.getValorRenda())
                .build();
    }
}
