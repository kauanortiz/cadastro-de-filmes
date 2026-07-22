# Cadastro de Filmes

Sistema desktop desenvolvido em Java para gerenciamento de filmes, gêneros e atores. A aplicação utiliza Java Swing para a interface gráfica e segue o padrão arquitetural MVC, separando as responsabilidades entre modelos, telas e controladores.

## Sobre o projeto

O sistema permite cadastrar, consultar, editar e excluir filmes, gêneros e atores.

Cada filme possui:

* título;
* duração;
* um gênero;
* um ou mais atores.

A aplicação também possui validações para impedir a exclusão de gêneros ou atores que estejam associados a algum filme.

## Funcionalidades

### Filmes

* Cadastrar filmes;
* editar filmes existentes;
* excluir filmes;
* selecionar um gênero;
* selecionar vários atores;
* visualizar os filmes cadastrados em uma tabela;
* manter gênero e atores selecionados durante a edição;
* validar campos obrigatórios.

### Gêneros

* Cadastrar gêneros;
* editar gêneros;
* excluir gêneros;
* impedir nomes duplicados;
* impedir a exclusão de gêneros associados a filmes.

### Atores

* Cadastrar atores;
* editar atores;
* excluir atores;
* impedir nomes duplicados;
* impedir a exclusão de atores associados a filmes.

## Tecnologias utilizadas

* Java;
* Java Swing;
* Programação Orientada a Objetos;
* Arquitetura MVC;
* Collections do Java;
* Eclipse IDE.

## Arquitetura MVC

O projeto foi organizado utilizando o padrão MVC.

### Model

As classes de modelo representam as entidades do sistema:

```text
Ator
Filme
Genero
```

Exemplo de relacionamento:

```text
Filme
 ├── Genero
 └── List<Ator>
```

Cada filme possui um gênero e uma lista de atores.

### View

As classes de visualização são responsáveis pela interface gráfica:

```text
AtorView
FilmeView
GeneroView
```

As telas utilizam componentes do Java Swing, como:

```text
JFrame
JPanel
JButton
JTextField
JSpinner
JComboBox
JList
JTable
JOptionPane
```

### Controller

Os controllers recebem os eventos das telas, validam os dados e acessam os repositórios:

```text
AtorController
FilmeController
GeneroController
```

Exemplo de evento:

```java
view.getSalvarButton().addActionListener(
    e -> salvarFilme()
);
```

Quando o usuário clica no botão, o controller executa a operação correspondente.

### Repository

Os repositórios são responsáveis pelo armazenamento e gerenciamento dos objetos:

```text
AtorRepository
FilmeRepository
GeneroRepository
```

Entre as principais operações estão:

```text
adicionar
buscar por ID
listar todos
atualizar
excluir
```

## Estrutura do projeto

```text
src/
├── controller/
│   ├── AtorController.java
│   ├── FilmeController.java
│   └── GeneroController.java
│
├── model/
│   ├── Ator.java
│   ├── Filme.java
│   └── Genero.java
│
├── repository/
│   ├── AtorRepository.java
│   ├── FilmeRepository.java
│   └── GeneroRepository.java
│
├── view/
│   ├── AtorView.java
│   ├── FilmeView.java
│   └── GeneroView.java
│
└── Main.java
```

A estrutura pode apresentar pequenas diferenças dependendo da organização adotada no projeto.

## Validações implementadas

A aplicação possui validações para evitar operações inválidas.

Entre elas:

* título do filme não pode ficar vazio;
* gênero deve ser selecionado;
* pelo menos um ator deve ser selecionado;
* duração do filme deve ser maior que zero;
* nome do gênero não pode ficar vazio;
* nome do ator não pode ficar vazio;
* gêneros duplicados não são permitidos;
* atores duplicados não são permitidos;
* gêneros associados a filmes não podem ser excluídos;
* atores associados a filmes não podem ser excluídos.

As mensagens são exibidas utilizando `JOptionPane`.

Exemplo:

```java
JOptionPane.showMessageDialog(
    view,
    "Filme cadastrado com sucesso!",
    "Cadastro realizado",
    JOptionPane.INFORMATION_MESSAGE
);
```

## Relacionamentos entre as entidades

A classe `Filme` possui relacionamento com `Genero` e `Ator`.

Exemplo simplificado:

```java
public class Filme {

    private Integer id;
    private String titulo;
    private Integer duracao;
    private Genero genero;
    private List<Ator> atores;

}
```

O relacionamento pode ser representado da seguinte maneira:

```text
Genero 1 -------- N Filme

Ator N ---------- N Filme
```

Um gênero pode estar associado a vários filmes.

Um ator pode participar de vários filmes, e cada filme pode possuir vários atores.

## Interface gráfica

A interface utiliza componentes do Java Swing.

### Cadastro de filmes

A tela de filmes possui:

* campo para título;
* campo para duração;
* caixa de seleção de gênero;
* lista com seleção múltipla de atores;
* tabela com os filmes cadastrados;
* botões para salvar, criar novo registro e excluir.

### Cadastro de gêneros

A tela de gêneros possui:

* campo para nome;
* tabela com os gêneros cadastrados;
* botões para salvar, atualizar, criar e excluir.

### Cadastro de atores

A tela de atores possui:

* campo para nome;
* tabela com os atores cadastrados;
* botões para salvar, atualizar, criar e excluir.

## Como executar o projeto

### Pré-requisitos

Para executar o projeto, é necessário possuir:

* Java JDK instalado;
* uma IDE compatível com Java, como Eclipse, IntelliJ IDEA ou NetBeans.

### Executando no Eclipse

1. Clone ou baixe este repositório.
2. Abra o Eclipse.
3. Acesse:

```text
File > Import
```

4. Selecione:

```text
Existing Projects into Workspace
```

5. Escolha a pasta do projeto.
6. Localize a classe `Main`.
7. Execute utilizando:

```text
Run As > Java Application
```

## Como usar

1. Cadastre os gêneros disponíveis.
2. Cadastre os atores.
3. Abra a tela de filmes.
4. Informe o título e a duração.
5. Selecione um gênero.
6. Selecione um ou mais atores.
7. Clique em salvar.

Para editar um registro, selecione uma linha da tabela, altere os dados e clique em atualizar ou salvar, conforme a implementação da tela.

Para excluir, selecione um registro e clique no botão de exclusão.

## Exemplo de cadastro

```text
Título: Interestelar
Duração: 169 minutos
Gênero: Ficção científica
Atores:
- Matthew McConaughey
- Anne Hathaway
```

## Conceitos aplicados

Durante o desenvolvimento foram aplicados os seguintes conceitos:

* classes e objetos;
* encapsulamento;
* construtores;
* listas com `ArrayList`;
* associação entre objetos;
* tratamento de eventos;
* expressões lambda;
* interfaces gráficas;
* arquitetura MVC;
* separação de responsabilidades;
* validação de dados;
* operações de CRUD.

## Possíveis melhorias

Como melhorias futuras, podem ser implementadas:

* persistência dos dados em banco de dados;
* pesquisa de filmes por título;
* filtros por gênero ou ator;
* ordenação das tabelas;
* tela principal utilizando `JTabbedPane`;
* inclusão de imagem ou pôster do filme;
* cadastro de diretor;
* avaliação dos filmes;
* armazenamento da data de lançamento;
* exportação dos dados;
* autenticação de usuários.

## Autor

Desenvolvido por **Kauan Ortiz**.

Projeto acadêmico desenvolvido para praticar Programação Orientada a Objetos, Java Swing e arquitetura MVC.
