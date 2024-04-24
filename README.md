# API Rest com Spring Boot do jogo rpg 

# Jogo RPG utilizando Spring Boot com banco de dados H@.
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

# Esse é um pequeno jogo beck-end que tem muitas melhorias a fazer, ex. salvar em banco, MySql, Oracle ou PostgreSQL.

# O projeto se encontra em fase inicial.



