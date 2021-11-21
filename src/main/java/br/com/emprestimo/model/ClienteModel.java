package br.com.emprestimo.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter @Data @Builder
public class ClienteModel {
    String nome;
    String cpf;
    String uf;
    double valorRenda;
    LocalDate dataNascimento;
    int idade;
}
