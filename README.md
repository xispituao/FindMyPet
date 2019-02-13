# FindMyPet
Projeto FInal 3°Periodo.
APlicativo criado com o intuito de facilitar o porcesso de busca do pet(seja cachorro ou gato) perdido. 


## Classes Modelos
- **Usuarios**
  - Atributos
    - id;
    - nome;
    - email;
    - senha;
    - sexo;
    - telefone;
    - usuarioLogado;
  - Métodos
    - getters e setters.
- **Publicacao**
  - Atributos
    - id;
    - usuario;
    - animal;
    - comentarios;
  - Métodos
    - getters e setters.
- **Comentario**
  - Atributos
    - id;
    - usuario;
    - conteudo;
  - Métodos
    - getters e setters.
- **Animal**
  - Atributos
    - id;
    - nome;
    - cor_pelo;
    - cor_olhos;
    - raca;
    - sexo;
    - caracteristicasAdicionais;
  - Métodos
    - getters e setters.

## Relacionamentos
O usuário ao entrar no aplicativo tem a capacidade de criar suas listas de jogos, os separando e organizando-as em categorias que desejar. Alem disso todos os usuários podem deixar reviews nos jogos para expor seus pensamentos sobre o jogo para o resto da comunidade. Todo jogo tambem tem um desenvolvedor. 
## Persistência de dados
Object Box.
## Diagrama de Classe simplificado
![](diagram3.png	)
## Funcionalidades
- Cadastro e login de usuários;
- Criar, apagar publicção do animal perdido;
- Comentar na publição de outros usuários;


