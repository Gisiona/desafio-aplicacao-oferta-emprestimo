package com.br.emprestimo.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@RequiredArgsConstructor
public class SolicitacaoEmprestimoRequestDto {

    @JsonProperty("cliente")
    private ClienteDto cliente;
}
