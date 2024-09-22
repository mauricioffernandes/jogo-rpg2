# API Rest com Spring Boot, Jogo rpg 

# Jogo RPG utilizando Spring Boot com banco de dados Postgre.
# O jogo, será duelado em turnos. Escolha o seu nome e personagem favorito.
# O seu oponente sempre será um monstro, e ele é criado de forma aleatória.

# Funcionalidades da API: CRUD de Personagem, jogador e batalha.

# O jogo é inicializado com a criação do personagem, informado o nome do personagem. EX: GUERREIRO, BARBARO ou CAVALEIRO.

# Em seguida é criado um jogado, informando o nome para o Jogador e o ID do personagem já cadastrado anteriormente.

# O terceiro passo é criar uma batalha enformando o ID do seu jogado que você cadastrou no campom, cod_jogador.
# Ao criar a batalha um oponente será criado pelo sistema sendo do tipo monstro.
# O sistema jogará os dados para os dois jogadores para ver quem inicia a batalha.

# Com o resultado de seus dados, o jogador ou o oponente que é o computador, inicializa a batalha.

# Assim o jogo começa com um jogador atacando e o outro defendendo.

# Após o iniciante da batalha fazer o ataque usando o endpoint ataque.

# O oponete usa o endpoint defesa para realizar a defesa.

# Em seguida é só fazer o calculo do ataque usando o endpoint de, calcular Dano.

# Assim o iniciante faz o ataque e o defensor faz a defeza e caso o ataque seja maior que a defesa, o dano é calculado.

# Ao sofre dano, é subtraido o valor do dano dos pontos de vida do personagem.

# O personagem que ficar com zero ou menos de pontos de vida, será o perdedor e a luta terminará.

# Esse é um pequeno jogo beck-end que tem muitas melhorias a fazer.

# Instalação
# Ao importar o projeto como um projeto Maven.
# Rode o docker-compose que se encontra na raiz do projeto para criar o Banco de Dados, PostgreSql. 
# comando: docker-compose up -d

# Ao iniciado o sistema pode ser acessado através do Swagger.
# Url acesso Api RPG Swagger, http://localhost:8081/swagger-ui/index.html

# 1. Primeiro crie Personagens.
# Exemplo de Json para criar um personagem.
# {
# "id": 0,
# "tipo": "Cavaleiro",
# "qtdVidas": 26,
# "poder": 6,
# "defesa": 8,
# "agilidade": 3,
# "qtdDado": 2,
# "tolalFaces": 6
# }

# 2. Segundo crie Jogadores.
# Exemplo de Json para criar um personagem.
# {
#  "createdAt": "2024-09-22T12:42:33.717Z",
#  "nome": "Astrogildo PS5",
#  "personagem": {
#    "id": 1,
#    "tipo": "string",
#    "qtdVidas": 0,
#    "poder": 0,
#    "defesa": 0,
#    "agilidade": 0,
#    "qtdDado": 0,
#    "tolalFaces": 0
#  },
#  "saldo": 0
# }

# 3. Terceiro Crie a Batalha
# No Request body do POST Batalha, informe o ID do Jogador já cadastrado

# O projeto se encontra em fase inicial.



