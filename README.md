# 🧪 API de Gerenciamento de Inventário (cp56)

API RESTful desenvolvida em Spring Boot para o gerenciamento de inventário de reagentes de laboratório. O projeto implementa operações CRUD (Create, Read, Update, Delete) seguindo os princípios REST e uma arquitetura em camadas (Controller, Service, Repository).

Este projeto **não** utiliza um banco de dados persistente. Os dados são armazenados e gerenciados em memória durante a execução da aplicação e são pré-populados na inicialização (`DataInitializer`).

## 👨‍💻 Membros da Equipe

* **Geronimo Augusto Nascimento Santos** - RM: 557170;
* **Ana Laura** - RM: 554375;
* **Ianny Raquel** - RM: 559096;
* **Murilo Cordeiro** - RM: 556727;
* **Vitor Augusto** - RM: 555469.

---

## 🚀 Endpoints da API

A API está estruturada em 4 recursos principais.

### 1. Fabricantes

Gerencia os fornecedores e fabricantes dos reagentes.

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `GET` | `/fabricantes` | Lista todos os fabricantes cadastrados. |
| `GET` | `/fabricantes/{id}` | Busca um fabricante específico pelo seu UUID. |
| `POST` | `/fabricantes` | Cria um novo fabricante. |
| `PUT` | `/fabricantes/{id}` | Atualiza um fabricante existente. |
| `DELETE` | `/fabricantes/{id}` | Exclui um fabricante. |

#### Input (`POST` / `PUT`): `FabricanteInputDTO`
```json
{
    "nomeOficial": "Nome Oficial da Empresa Ltda.",
    "nomeFantasia": "Nome Fantasia",
    "cnpj": "12.345.678/0001-99",
    "paisOrigem": "Brasil"
}
```

#### Output (`GET`): `FabricanteOutputDTO`
```json
{
    "id": "a14f8346-7fb3-4d06-a2f7-a1aaed1cc7c8",
    "nomeOficial": "Nome Oficial da Empresa Ltda.",
    "nomeFantasia": "Nome Fantasia",
    "cnpj": "12.345.678/0001-99",
    "paisOrigem": "Brasil"
}
```

---

### 2. Localizações de Estoque

Gerencia os locais físicos de armazenamento (refrigeradores, freezers, etc.).

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `GET` | `/localizacoes-estoque` | Lista todas as localizações de estoque. |
| `GET` | `/localizacoes-estoque/{id}` | Busca uma localização específica pelo seu UUID. |
| `POST` | `/localizacoes-estoque` | Cria uma nova localização. |
| `PUT` | `/localizacoes-estoque/{id}` | Atualiza uma localização existente. |
| `DELETE` | `/localizacoes-estoque/{id}` | Exclui uma localização. |

#### Input (`POST` / `PUT`): `LocalizacaoEstoqueInputDTO`
```json
{
    "codigoLocal": "REF-01",
    "descricao": "Refrigerador 1 - Imunologia",
    "setor": "Imunologia",
    "faixaTemperaturaNominal": "2-8°C",
    "tipo": "REFRIGERADOR"
}
```
*Valores de `tipo`: `REFRIGERADOR`, `FREEZER_MINUS20`, `FREEZER_MINUS80`, `PRATELEIRA_AMBIENTE`, `OUTRO`*

#### Output (`GET`): `LocalizacaoEstoqueOutputDTO`
```json
{
    "id": "1fce386a-870c-43ff-a98c-5196932cd7c3",
    "codigoLocal": "REF-01",
    "descricao": "Refrigerador 1 - Imunologia",
    "setor": "Imunologia",
    "faixaTemperaturaNominal": "2-8°C",
    "tipo": "REFRIGERADOR"
}
```

---

### 3. Reagentes

O recurso principal. Gerencia os reagentes, associando-os a um fabricante e uma localização.

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `GET` | `/reagentes` | Lista todos os reagentes. |
| `GET` | `/reagentes/{id}` | Busca um reagente específico pelo seu UUID. |
| `POST` | `/reagentes` | Cria um novo reagente. |
| `PUT` | `/reagentes/{id}` | Atualiza um reagente existente. |
| `DELETE` | `/reagentes/{id}` | Exclui um reagente. |

### Input (`POST` / `PUT`): `ReagenteInputDTO`
*Note que para os relacionamentos, enviamos apenas os IDs.*
```json
{
    "nome": "Elecsys Anti-HBs II",
    "codigoSku": "04788737190",
    "lote": "LOTE-A123",
    "dataValidade": "2026-05-08",
    "dataRecebimento": "2025-10-29",
    "quantidadeEstoque": 100,
    "status": "LIBERADO",
    "fabricanteId": "a14f8346-7fb3-4d06-a2f7-a1aaed1cc7c8",
    "localizacaoEstoqueId": "1fce386a-870c-43ff-a98c-5196932cd7c3"
}
```
*Valores de `status`: `QUARENTENA`, `LIBERADO`, `EM_USO`, `VENCIDO`, `REPROVADO_CONTROLE_QUALIDADE`, `DESCARTADO`*

#### Output (`GET`): `ReagenteOutputDTO`
*Note que na saída, os relacionamentos vêm como objetos completos.*
```json
{
    "id": "f5d2f8a0-7b3e-4f6a-8d1e-2c9c7f6b0a1a",
    "nome": "Elecsys Anti-HBs II",
    "codigoSku": "04788737190",
    "lote": "LOTE-A123",
    "dataValidade": "2026-05-08",
    "dataRecebimento": "2025-10-29",
    "quantidadeEstoque": 100,
    "status": "LIBERADO",
    "estaVencido": false,
    "fabricante": {
        "id": "a14f8346-7fb3-4d06-a2f7-a1aaed1cc7c8",
        "nomeOficial": "Roche Diagnóstica Brasil Ltda.",
        "nomeFantasia": "Roche",
        "cnpj": "30.280.358/0001-23",
        "paisOrigem": "Brasil"
    },
    "localizacaoEstoque": {
        "id": "1fce386a-870c-43ff-a98c-5196932cd7c3",
        "codigoLocal": "REF-01",
        "descricao": "Refrigerador 1 - Imunologia",
        "setor": "Imunologia",
        "faixaTemperaturaNominal": "2-8°C",
        "tipo": "REFRIGERADOR"
    }
}
```

---

### 4. Movimentações de Estoque

Gerencia o histórico de entradas e saídas de um reagente. É um sub-recurso de `Reagentes`.

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `GET` | `/reagentes/{reagenteId}/movimentacoes` | Lista todas as movimentações de um reagente específico.|
| `POST` | `/reagentes/{reagenteId}/movimentacoes` | Cria uma nova movimentação para um reagente. (O estoque do reagente é atualizado automaticamente).|
| `DELETE` | `/reagentes/{reagenteId}/movimentacoes/{movimentacaoId}` | Exclui uma movimentação. (O estoque do reagente é revertido).|

#### Input (`POST`): `MovimentacaoEstoqueInputDTO`
```json
{
    "observacao": "Uso em equipamento Cobas",
    "quantidadeMovimentada": -5,
    "dataBalanco": null,
    "tipo": "SAIDA_USO_ANALISADOR"
}
```
*Valores de `tipo`: `ENTRADA_NOTA`, `SAIDA_USO_ANALISADOR`, `SAIDA_DESCARTE_VENCIMENTO`, etc.*

### Output (`GET`): `MovimentacaoEstoqueOutputDTO`
```json
{
    "id": "c3e4b5d6-8a1b-4f9e-9e1a-2d8c3e4b5d6f",
    "observacao": "Uso em equipamento Cobas",
    "quantidadeMovimentada": -5,
    "dataHoraMovimentacao": "2025-11-09T10:30:00",
    "dataBalanco": null,
    "tipo": "SAIDA_USO_ANALISADOR",
    "reagenteId": "f5d2f8a0-7b3e-4f6a-8d1e-2c9c7f6b0a1a"
}
```
