package br.com.emprestimo.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@RequiredArgsConstructor
public class EmprestimoRequestDto {

    @JsonProperty("cliente")
    private ClienteDto cliente;
}
