# Bank Account Prototype API

Este é um protótipo de API para gerenciamento de contas bancárias. Abaixo estão as rotas disponíveis e exemplos de JSON para cada uma delas.

## Pré-requisitos

- Uma conta no Azure.
- IntelliJ IDEA com o plugin Azure Toolkit instalado.
- Projeto Spring Boot pronto para deploy.

## Passos

### 1. Configurar o Azure Toolkit for IntelliJ

1. Abra o IntelliJ IDEA.
2. Vá para `File` > `Settings` (ou `IntelliJ IDEA` > `Preferences` no macOS).
3. Na barra lateral, selecione `Azure` > `Azure Toolkit for IntelliJ`.
4. Faça login com sua conta do Azure.

### 2. Criar uma instância do Azure Database for PostgreSQL usando o Azure Toolkit

1. No IntelliJ IDEA, na barra lateral, abra a aba `Azure Explorer`.
2. Clique com o botão direito em `Azure Database for PostgreSQL` e selecione `Create Server`.
3. Preencha os detalhes necessários e clique em `OK`.

### 3. Configurar o projeto para usar o PostgreSQL

1. No seu `build.gradle`, adicione a dependência para o driver do PostgreSQL:
```
    implementation 'org.postgresql:postgresql:42.6.0'
```

### 4. Deploy do aplicativo no Azure usando o Azure Toolkit

1. No IntelliJ IDEA, clique com o botão direito do mouse no projeto e selecione `Azure` > `Deploy to Azure Web Apps`.
2. Selecione sua assinatura e clique em `Create` para criar um novo Web App.
3. Preencha os detalhes necessários e clique em `OK`.
4. Selecione o Web App recém-criado e clique em `Deploy`.

### 5. Conectar o Web App ao Azure Database for PostgreSQL

1. No IntelliJ IDEA, na barra lateral, abra a aba `Azure Explorer`.
2. Navegue até o seu Web App, clique com o botão direito e selecione `Manage`.
3. No menu lateral, clique em `Connection strings`.
4. Adicione uma nova string de conexão com o nome `Default` e insira a string de conexão do seu Azure Database for PostgreSQL.
5. Clique em `Save`.

### 6. Verificar o deploy

1. No IntelliJ IDEA, na barra lateral, abra a aba `Azure Explorer`.
2. Navegue até o seu Web App, clique com o botão direito e selecione `Open in Browser`.



## Rotas

### Chave Pix

- **Criar Chave Pix**
  - **Método**: POST
  - **Rota**: `/chavepix`
  - **JSON de Entrada**:
    ```json
    {
      "usuarioId": "UUID_DO_USUARIO",
      "tipo": "TIPO_DA_CHAVE",
      "valorChave": "VALOR_DA_CHAVE"
    }
    ```

### Transações

- **Realizar Transferência**
  - **Método**: POST
  - **Rota**: `/transaction/transfer`
  - **JSON de Entrada**:
    ```json
    {
      "valorTransferencia": VALOR_TRANSFERENCIA,
      "debitor": {
        "id": "ID_USUARIO_DEBITOR",
        "tipo": "TIPO_CHAVE (EX: CPF)",
        "valorChave": "VALOR_CHAVE"
      },
      "creditor": {
        "id": "ID_USUARIO_CREDITOR",
        "tipo": "TIPO_CHAVE (EX: CPF)",
        "valorChave": "VALOR_CHAVE"
      }
    }
    ```

- **Obter Histórico de Transações**
  - **Método**: GET
  - **Rota**: `/transaction/history?id=UUID_DO_USUARIO`

### Produtos

- **Criar Produto**
  - **Método**: POST
  - **Rota**: `/product`
  - **JSON de Entrada**:
    ```json
    {
      "nome": "PIX",
      "habilitado": true
    }
    ```

- **Desativar Produto**
  - **Método**: PUT
  - **Rota**: `/product/inactivate?id=UUID_DO_PRODUTO`

- **Ativar Produto**
  - **Método**: PUT
  - **Rota**: `/product/activate?id=UUID_DO_PRODUTO`

- **Obter Todos os Produtos**
  - **Método**: GET
  - **Rota**: `/product/all`

### Usuários

- **Criar Usuário**
  - **Método**: POST
  - **Rota**: `/user`
  - **JSON de Entrada**:
    ```json
    {
      "nome": "test_153db9e1bede",
      "email": "test_4ae7f6f3f84b",
      "nomeMae": "test_c0273f98f2ca",
      "telefone": "test_da2b744f8a2c",
      "dataNascimento": "2023-07-22",
      "endereco": "test_381117d5005a",
      "cpf": "4571561308",
      "rg": "test_a5f119b6e9c8",
      "pessoaExpostaPoliticamente": false,
      "rendaMensal": 83.13,
      "valorPatrimonio": 35.22,
      "ativo": true,
      "produtos": [
        {
          "id": "fef772fa-59c0-466e-9985-e6e548b7f31b",
          "nome": "PIX",
          "dataCadastro": "2021-10-24",
          "dataAtualizacao": "2030-05-11",
          "habilitado": false
        }
      ],
      "senha": "test_dfb5c176a67c"
    }
    ```

- **Desativar Usuário**
  - **Método**: PUT
  - **Rota**: `/user/inactivate?id=UUID_DO_USUARIO`

- **Ativar Usuário**
  - **Método**: PUT
  - **Rota**: `/user/activate?id=UUID_DO_USUARIO`

- **Obter Todos os Usuários**
  - **Método**: GET
  - **Rota**: `/user/all`

---
