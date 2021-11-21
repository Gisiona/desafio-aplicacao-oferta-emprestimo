package com.br.emprestimo.controller.mapper;

import com.br.emprestimo.controller.dto.ClienteDto;
import com.br.emprestimo.model.ClienteModel;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClienteMapper {
    public ClienteModel toClienteModel(ClienteDto clienteRequestDto) {
        return ClienteModel
                .builder()
                .cpf(clienteRequestDto.getCpf())
                .dataNascimento(clienteRequestDto.getDataNascimento())
                .idade(clienteRequestDto.getIdade())
                .nome(clienteRequestDto.getNome())
                .uf(clienteRequestDto.getUf())
                .valorRenda(clienteRequestDto.getValorRenda())
                .build();
    }
}
