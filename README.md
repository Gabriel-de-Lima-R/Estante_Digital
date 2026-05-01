# 📚 Estante Digital

> Sistema de gerenciamento de biblioteca desenvolvido em Java CLI para controle de empréstimos, devoluções e acervo da Biblioteca Municipal.


## 📋 Sobre o Projeto

O **Estante Digital** é uma aplicação que substitui o sistema manual de papel da biblioteca, permitindo que leitores consultem o acervo, peguem livros emprestados e devolvam de forma automatizada. Tudo via terminal, com persistência em arquivos JSON.

**Problema resolvido:** Antes, o bibliotecário precisava ir até a estante física para verificar disponibilidade. Agora, o sistema mostra digitalmente todos os livros disponíveis e emprestados.

---

## 🎯 Funcionalidades

### 👤 Usuários não autenticados
- 🔍 Ver acervo disponível
- 📝 Criar conta (com validações)
- 🔑 Fazer login

### 👥 Usuários autenticados
- 📖 Ver acervo disponível
- 🔎 Buscar livros por título
- ✅ Pegar livro emprestado (com validações)
- 📦 Devolver livro (com controle de atraso)
- 🚪 Logout

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão | Finalidade |
|------------|--------|------------|
| Java | 17+ | Linguagem principal |
| Maven | 3.6+ | Gerenciador de dependências |
| Gson | 2.10.1 | Leitura/escrita de JSON |
| JUnit 5 | 5.10.2 | Testes unitários |

---

## 📁 Estrutura do Projeto
```
Estate_Digital/                                 # Raiz do projeto
│
├── pom.xml                                      # Configuração do Maven (dependências, plugins, versão Java)
├── README.md                                    # Documentação do projeto
├── .gitignore                                   # Arquivos ignorados pelo Git
│
│
└── src/                                         # Código fonte principal
    │
    └── main/                                    # Código de produção
    |   │
    |   ├── java/                                # Código Java
    |   │   └── com/estantedigital/              # Pacote base do projeto
    |   │       │
    |   │       ├── Main.java                    # Ponto de entrada da aplicação
    |   │       │
    |   │       ├── adapter/                     # Adaptadores para bibliotecas externas
    |   │       │   └── LocalDateAdapter.java    # Converte LocalDate para JSON (Gson)
    |   │       │
    |   │       ├── cli/                         # Interface com o usuário (CLI)
    |   │       │   └── CentralMenus.java        # Constantes com artes ASCII
    |   │       │
    |   │       ├── dto/                         # Objetos de Transferência de Dados
    |   │       │   └── ItemAcervoDTO.java       # Leitura bruta do JSON 
    |   │       │
    |   │       ├── model/                       # Entidades/Modelos do domínio
    |   │       │   ├── Emprestimo.java          # Dados do empréstimo
    |   │       │   ├── Enciclopedia.java        # Subclasse de ItemAcervo para enciclopédias
    |   │       │   ├── ItemAcervo.java          # Classe pai (abstrata) do acervo
    |   │       │   ├── Livro.java               # Subclasse de ItemAcervo para livros
    |   │       │   ├── Revista.java             # Subclasse de ItemAcervo para revistas
    |   │       │   └── Usuario.java             # Dados do usuário (nome, CPF, email, senha, bloqueio)
    |   │       │
    |   │       ├── repository/                  # Camada de persistência (JSON)
    |   │       │   ├── AcervoRepository.java    # Gerencia arquivos JSON
    |   │       │   ├── EmprestimoRepository.java # Gerencia emprestimos.json (CRUD de empréstimos)
    |   │       │   └── UsuarioRepository.java   # Gerencia usuarios.json (CRUD de usuários)
    |   │       │
    |   │       └── service/                     # Regras de negócio
    |   │           ├── ContaService.java        # Lógica de cadastro, login e validações
    |   │           └── EmprestimoService.java   # Lógica de empréstimo e devolução
    |   │
    |   └── resources/                           # Recursos do projeto (arquivos JSON)
    |       ├── livros.json                      # Acervo de livros 
    |       ├── revistas.json                    # Acervo de revistas
    |       ├── enciclopedias.json               # Acervo de enciclopédias
    |       ├── usuarios.json                    # Usuários cadastrados
    |       └── emprestimos.json                 # Empréstimos registrados
    │
    └── test/                                    # Testes unitários
        └── java/                                # Código de teste
            └── com/estantedigital/              # Mesma estrutura de pacotes do main
                │
                ├── model/                       # Testes das entidades
                │   ├── UsuarioTest.java         # Testa getPrimeiroNome(), getters/setters, toString()
                │   ├── ItemAcervoTest.java      # Testa status, disponibilidade, tipos, construtores
                │   └── EmprestimoTest.java      # Testa isAtrasado(), datas, construtores
                │
                └── service/                     # Testes das regras de negócio
                    └── EmprestimoServiceTest.java # Testa empréstimo

---

## 🚀 Como Executar o Projeto

### Pré-requisitos
- Java 17 ou superior
- Maven 3.6 ou superior

### Passos

```bash
# Clonar o repositório
git clone https://github.com/Gabriel-de-Lima/estante-digital.git

# Entrar no diretório
cd estante-digital

# Compilar
mvn clean compile

# Executar
mvn exec:java -Dexec.mainClass="com.estantedigital.Main"
```

---

## 🏠 Tela Inicial
```
========================================
            ESTANTE DIGITAL
========================================
Bem-vindo à Biblioteca Municipal!

1 - Ver acervo disponível
2 - Fazer login / Criar conta
3 - Fechar programa

Escolha uma opção: _
```

---

## 📊 Regras de Negócio Implementadas

### RF01 - Tela Inicial (Não Logado)

| Código | Descrição |
|--------|-----------|
| RF01.1 | Ver acervo disponível - Lista apenas itens com status "disponível" |
| RF01.2 | Fazer login / Criar conta - Navega para tela de autenticação |
| RF01.3 | Fechar programa - Encerra a aplicação |

### RF02 - Tela de Autenticação

| Código | Descrição |
|--------|-----------|
| RF02.1 | Fazer login - Valida e-mail e senha |
| RF02.2 | Criar conta - Coleta dados com validações rigorosas |
| RF02.3 | Voltar - Retorna ao menu inicial |

#### RF02.2 - Validações de Cadastro

| Campo | Regra | Mensagem de erro |
|-------|-------|------------------|
| Nome completo | Mínimo 3 caracteres | "Nome deve ter pelo menos 3 caracteres." |
| CPF | Exatamente 11 dígitos numéricos | "CPF inválido! Deve conter exatamente 11 números." |
| E-mail | Domínios @gmail.com ou @hotmail.com | "E-mail inválido! Use @gmail.com ou @hotmail.com." |
| E-mail | Não pode duplicar | "E-mail já cadastrado." |
| CPF | Não pode duplicar | "CPF já cadastrado." |
| Senha | Mínimo 4 caracteres, letra + número | "Senha deve ter pelo menos 4 caracteres, contendo letra e número." |

### RF03 - Menu Principal (Logado)

| Código | Descrição |
|--------|-----------|
| RF03.1 | Ver acervo disponível - Lista itens disponíveis para usuário logado |
| RF03.2 | Pegar livro emprestado - Valida bloqueio, disponibilidade e empréstimo ativo |
| RF03.3 | Devolver livro - Registra devolução, verifica atraso e aplica bloqueio |
| RF03.4 | Buscar por livro - Busca por parte do título (case insensitive) |
| RF03.5 | Sair - Encerra sessão e retorna ao menu inicial |

### RN - Regras de Negócio Específicas

| Código | Descrição |
|--------|-----------|
| RN01 | Um usuário pode ter no máximo um empréstimo ativo por vez |
| RN02 | Prazo padrão de devolução: 7 dias corridos |
| RN03 | Devolução atrasada = bloqueio de 7 dias |
| RN04 | Bloqueio impede novos empréstimos |
| RN05 | Atraso exige justificativa obrigatória |
| RN06 | Acervo composto por livros, revistas e enciclopédias |
| RN07 | Dados do acervo vindos de arquivos JSON separados |

---

## 🧪 Testes

### Visão Geral

| Métrica | Valor |
|---------|-------|
| Total de testes | 50+ |
| Framework | JUnit 5 (Jupiter) |
| Cobertura | Modelos, serviços e validações |

---

## ✍️ Autor

**Gabriel de Lima**

Desenvolvedor Full Stack

- GitHub: [@Gabriel-de-Lima](https://github.com/Gabriel-de-Lima-R)
