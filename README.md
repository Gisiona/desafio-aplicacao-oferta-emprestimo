# Resolvendo desafio de código encontrado no github
# Desafio de Código
A proposta da aplicação que vamos desenvolver em conjunto é disponibilizar a uma pessoa as modalidades de empréstimo as quais ela tem acesso de acordo com algumas variáveis.

Prover os seguintes modelos de empréstimo:
- Empréstimo pessoal. Taxa de juros: 4%
- Empréstimo com garantia. Taxa de juros: 3%
- Consignado. Taxa de juros: 2%

Abaixo seguem as regras de negócio relacionadas a concessão de empréstimo de acordo com o perfil do cliente:

|                          | Empréstimo pessoal | Empréstimo c/ garantia | Consignado |
| ------------------------ | ------------------ | :--------------------: | ---------- |
| Salário <= 3000          | Sim                |       Sim\*\*\*        | Não        |
| Salário >= 3000          | Sim                |        Sim\*\*         | Não        |
| Salário >= 5000          | Sim                |         Sim\*          | Sim        |

Regras adicionais
- \* Clientes com menos de 30 anos
- \*\* Clientes que residem em SP
- \*\*\* Clientes com menos de 30 anos que residem em SP

### Utilização da aplicação:

A aplicação deve receber como entrada as seguintes informações:

##### Dados de entrada

```json
{
  "cliente": {
    "name": "Gisiona",
    "cpf": "123.456.789-10",
    "age": 29,
    "uf": "SP",
    "renda_mensal": 3000
  }
}
```

Para fins de simplicidade, considere que vamos sempre receber os dados corretos (tipos e formatos).

A aplicação deve responder com o seguinte modelo de informações:

##### Dados de saída

```json
{
  "numero_solicitacao": "745f2812-c3f4-42ce-93fb-e119e643bda2",
  "data_solicitacao": "2021-11-20T21:32:58.787",
  "cliente": {
      "name": "Gisiona",
      "cpf": "123.456.789-10",
      "age": 29,
      "uf": "SP",
      "renda_mensal": 3000
  },
  "produtos_emprestimo": [
    {
      "tipo_emprestimo": "EMPRESTIMO_CONSIGNADO",
      "taxa": 1
    },
    {
      "tipo_emprestimo": "EMPRESTIMO_PESSOAL",
      "taxa": 1
    }
  ]
}
```

### Domínio dos tipos de empréstimo
- EMPRESTIMO_CONSIGNADO
- EMPRESTIMO_PESSOAL
- EMPRESTIMO_GARANTIA

# Setup da apliacação
Instale as dependências
- Apache Maven 3.8.4 (https://maven.apache.org/download.cgi)
- Java jdk 1.8.0 (https://www.oracle.com/br/java/technologies/javase/javase8u211-later-archive-downloads.html)
- IDE de sua preferência (estou utilizando IntelliJ IDEA(https://www.jetbrains.com/pt-br/idea/))

# Rode os testes
Você pode executar os testes com o comando a seguir pelo CLI:

```bash
$ mvn test
```

### Repositório original do desafio
- https://github.com/Creditas/challenge/blob/master/backend/code-challenges/README.pt-BR.md

