# IFSP - App Integrated Secure API Firebase

O aplicativo Android é uma aplicação mobile Android desenvolvida como projeto final para as disciplinas de Desenvolvimento Mobile e Segurança da Informação. O aplicativo implementa uma arquitetura MVVM moderna com autenticação JWT, consumindo uma API REST segura desenvolvida em Node.js.

O projeto demonstra a aplicação integrada de conceitos de desenvolvimento mobile, arquitetura de software, segurança da informação e boas práticas de programação.

## 🎯 Funcionalidades


✅ CRUD completo de itens – Criar, listar, editar e excluir
✅ Tela de perfil – Exibe informações do usuário
✅ Configurações – Tema claro/escuro com persistência
✅ Listagem com RecyclerView – Exibição eficiente de dados
✅ Formulários – Criação e edição com validação
✅ AlertDialog e Snackbar – Feedback e confirmações
✅ Loading States – Indicadores durante operações
✅ Tratamento de erros – Mensagens amigáveis para o usuário
✅ Persistência de token – SharedPreferences com segurança
✅ Navegação – Entre telas com Intents e Menu
✅ Material Design – Interface consistente e moderna

## 🧱 Segurança

✅ Autenticação com JWT (JSON Web Tokens)
✅ Armazenamento seguro de token em SharedPreferences
✅ Controle de sessão com validação de token
✅ HTTPS (configurável para produção)
✅ Validação de permissões em rotas protegidas
✅ Tratamento de falhas com mensagens seguras
✅ Sanitização de entradas no backend
✅ Proteção contra exposição de credenciais
✅ Interceptor HTTP para adicionar token automaticamente
✅ Logout com limpeza de dados da sessão


## 📋 Pré-requisitos

Para o Mobile

    Android Studio 4.2.2 ou superior

    Java JDK 8 ou superior

    Android SDK com API 16+ configurada

    Emulador Android (API 16+) ou dispositivo físico

    Git para clonar o repositório

Para o Backend

    Node.js 10.x ou superior

    npm 6.x ou superior

    Porta 3000 disponível

## 🚀 Como executar

1. **Clone o repositório**:

```bash
   git clone  https://github.com/seuusuario/Ifsp-AppIntegratedSecureAPIFirebase.git

   cd Ifsp-AppIntegratedSecureAPIFirebase.git
```


# 📁 Estrutura do Projeto

```text

SecureApp/
├── app/                                      # Módulo Android
│   └── src/main/java/com/example/secureapp/
│       ├── core/                             # Camada Core
│       │   ├── di/                           # Injeção de dependência (Koin)
│       │   ├── network/                      # Comunicação com API (Retrofit)
│       │   ├── security/                     # Segurança (TokenManager)
│       │   └── utils/                        # Utilitários (Constants, SharedPrefs)
│       │
│       ├── data/                             # Camada de Dados
│       │   ├── remote/                       # DTOs e modelos remotos
│       │   │   └── model/                    # AuthRequest, AuthResponse, ItemDto
│       │   └── repository/                   # Implementações dos repositórios
│       │
│       ├── domain/                           # Camada de Domínio
│       │   ├── model/                        # Modelos de domínio (User, Item)
│       │   ├── repository/                   # Interfaces dos repositórios
│       │   └── usecase/                      # Casos de uso (Login, CRUD)
│       │
│       └── ui/                               # Camada de Apresentação
│           ├── activities/                   # Activities com ViewBinding
│           ├── viewmodels/                   # ViewModels com LiveData
│           ├── adapters/                     # RecyclerView Adapters
│           └── utils/                        # Utilitários de UI
│
└── backend/                                  # Módulo Backend
    ├── server.js                             # Servidor Node.js
    ├── package.json                          # Dependências
    └── .env                                  # Variáveis de ambiente
```

# 📲 Contato / Contact

<a href="https://www.linkedin.com/in/f%C3%A1bio-lu%C3%ADs-guia-da-concei%C3%A7%C3%A3o-77784741/"><img src="https://img.shields.io/badge/linkedin%20-%230077B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn"/></a>

---

<h5 align="center">
  &copy;06/2026 - <a href="https://github.com/Flgc/">Fábio Luis</a>
</h5>

