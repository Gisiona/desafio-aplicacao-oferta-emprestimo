package com.br.emprestimo.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class SimulacaoParcelamentoResponseDto {

    @JsonProperty("numero_simulacao_emprestimo")
    private UUID codigoSimulacao;

    @JsonProperty("numero_solicitacao_emprestimo")
    private String numeroSolicitacaoEmprestimo;

    @JsonProperty("data_simulacao")
    private LocalDateTime dataSimulacao;

    @JsonProperty("cliente")
    private ClienteDto cliente;

    @JsonProperty("tipo_emprestimo")
    private String tipoEmprestimo;

    @JsonProperty("valor_total_emprestimo_solicitado")
    double valorEmprestimoSolicitado;

    @JsonProperty("valor_total_emprestimo_financiado")
    double valorEmprestimoFinanciado;

    @JsonProperty("taxa_juros")
    double taxaJuros;

    @JsonProperty("valor_iof")
    double valorIof;

    @JsonProperty("data_vencimento_primeira_parcela")
    LocalDate dataPrimeiraParcela;

    @JsonProperty("data_vencimento_ultima_parcela")
    LocalDate dataUltimaParcela;

    @JsonProperty("quantidade_parcela")
    int quantidadeParcela;

    @JsonProperty("valor_parcela")
    double valorParcela;

    @JsonProperty("opcoes_parcela")
    List<ParcelaEmprestimoDto> parcelas;
}
