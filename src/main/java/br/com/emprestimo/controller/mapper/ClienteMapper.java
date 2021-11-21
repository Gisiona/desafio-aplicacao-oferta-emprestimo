package br.com.emprestimo.controller.mapper;

import br.com.emprestimo.controller.dto.ClienteDto;
import br.com.emprestimo.model.ClienteModel;
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
