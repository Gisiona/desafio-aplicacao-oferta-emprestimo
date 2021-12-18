package com.br.emprestimo.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;

@Getter @Setter @Data @Builder
public class ClienteModel {
    String nome;
    String cpf;
    String uf;
    double valorRenda;
    LocalDate dataNascimento;
    int idade;

    public Integer getIdade() {
        if (dataNascimento != null) {
            LocalDate dataAtual = LocalDate.now();
            return Period.between(dataNascimento, dataAtual).getYears();
        } else {
            return 0;
        }
    }
}
