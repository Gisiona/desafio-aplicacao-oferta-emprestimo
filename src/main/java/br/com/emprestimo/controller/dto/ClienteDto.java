package br.com.emprestimo.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data @Getter @RequiredArgsConstructor
public class ClienteDto {
    @JsonProperty("nome")
    String nome;

    @JsonProperty("cpf")
    String cpf;

    @JsonProperty("uf")
    String uf;

    @Min(value = 1, message = "Valor mínimo de renda mensal deve ser 1.")
    @JsonProperty("renda_mensal")
    double valorRenda;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @NotNull(message = "Data de nascimento é obrigatório.")
    @JsonProperty("data_nascimento")
    LocalDate dataNascimento;

    @Min(value = 18, message = "Idade mínima deve ser maior ou igual 18 anos.")
    @JsonProperty("idade")
    int idade;
}
