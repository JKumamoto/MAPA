# Mapa Universitário: Entidades Estudantis
A plataforma Mapa Universitário é um indexador para integração da comunidade universitária aplicado ao cenário de entidades estudantis.
A aplicação funciona como um web site com usuários representantes das entidades cadastrandos perfis públicos, que são indexados e organizados conforme especificidades de atuação e descrição de cada entidade estudantil. Dessa forma, diferentes membros de uma comunidade acadêmica podem ter acesso de maneira rápida e centralizada a todo o cenário de entidades estundantis que se desenvolve na sua universidade.

Isso é bom para as entidades que tem outro meio de divulgação compartilhado. Isso é bom para a universidade que consegue mapear a atuação de seu corpo dicente nas atividades extra-curriculares. Isso é bom para os alunos que passam a conhecer diferentes grupos que podem atuar nas suas áreas de interesse ou  para exergar as áreas em que faltam iniciativas.

# Versão 2.0.0 

Dezembro, 2017:
- Registro de usuários (perfil de adminstração de entidades);
- Login;
- Página principal;
- Página de resultado de pesquisa de entidades;
- Página de controle de perfil;


Próximas versões:
- Informações complementares de pequisa: gráficos e estatísticas;
- Reconhecimento de entidades por outras entidade;


# Para implementar a aplicação MAPA em sua universidade:

Faça o download dos arquivos em formato .ZIP ou obtenha os arquivos através do git, pelo terminal: 

```git clone https://github.com/JKumamoto/MAPA```

Coloque os arquivos no diretório da máquina que funionará como servidor.
Inicie o banco de dados MySQL:

```mysql -u root -p```

Adicione a base de dados para a aplicação:

```mysql> CREATE DATABASE MAPA;```

Para verificar a criação da base de dados: 

```mysql> SHOW DATABASES;```

Dentro da pasta MAPA, execute o Maven para compilação dos código JAVA e execução do servidor:

```./mvnw spring-boot:run```

Acesse a aplicação localmente através do navegador em: 

localhost:8080/



