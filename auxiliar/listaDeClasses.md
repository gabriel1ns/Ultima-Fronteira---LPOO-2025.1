# Classes

1. [Personagem](#personagem---superclasse)
2. [Item](#item---superclasse)
3. [Inventário](#inventário---classe)
4. [Evento](#evento---superclasse)
5. [GerenciadorDeEventos](#gerenciadordeeventos---classe)
6. [Ambiente](#ambiente---superclasse)
7. [GerenciadorDeAmbientes](#gerenciadordeambientes---classe)

- # Personagem - SUPERCLASSE
    - ## Atributos
        | Nome | Nome | Descrição |
        |:-:|:-:|:-:|
        | _nome_ | String | Identificação do personagem |
        | _vida_ | int | Representa a resistência do personagem. Se chegar a zero, o jogo termina |
        | _fome_ | int | Reduz a cada turno; se atingir um nível crítico, começa a afetar a vida |
        | _sede_ | int | Similar à fome, mas com uma taxa de consumo mais rápida |
        | _energia_ | int | Determina a capacidade do personagem de realizar ações; pode ser restaurada descansando |
        | _sanidade_ | int | Algumas situações podem afetar a mente do personagem, levando a alucinações ou perda de controle |
        | _inventario_ | Inventario | Espaço onde o personagem armazena recursos como comida, ferramentas e armas |
        | _localizacao_ | String | Define em qual parte do mapa o jogador se encontra |
        | _habilidadesEspeciais_ | **/** | Cada personagem pode ter habilidades únicas, como: </br>**Rastreador**: Encontra comida e água com mais facilidade </br>**Mecânico**: Conserta ferramentas e cria novas armas </br>**Médico**: Pode tratar ferimentos sem necessidade de itens raros </br>**Sobrevivente Nato**: Menos impactado por fome e sede |
    
- # Item - SUPERCLASSE
    - ## Atributos
        | Nome | Nome | Descrição |
        |:-:|:-:|:-:|
        | _nome_ | String | Identificação do item |
        | _peso_ | int | Influencia a quantidade de itens que o personagem pode carregar |
        | _durabilidade_ | int | Alguns itens se desgastam com o uso e podem quebrar |

    - ## Métodos
        | Nome | Nome | Descrição |
        |:-:|:-:|:-:|
        | _usar()_ | void | **/** |
        | _consumir()_ | void | **/** |

    - ## Subclasses
        | Nome | Atributos adicionais | Nome | Descrição | Métodos sobreescritos | Nome | Descrição |
        |:-:|:-:|:-:|:-:|:-:|:-:|:-:|
        | **Alimento** | _Valor nutricional_ | int | Pontos de fome restaurados | _consumir()_ | void | Restaura fome e pode ter efeitos colaterais (como intoxicação alimentar) |
        | | _tipo_ | String | Fruta, carne, enlatado, etc. |
        | | _prazoDeValidade_ | **/** | Alguns alimentos podem estragar |
        | **Agua** | _pureza_ | boolean | Potável, contaminada | _beber()_ | void | Restaura sede, mas pode causar doenças se for contaminada |
        | | _volume_ | int | Quantidade de consumo por unidade |
        | **Material** | _Tipo_ | String | Madeira, pedra, metal | _combinar(Material outroMaterial)_ | **/** | Permite criar novos itens ao combinar materiais diferentes |
        | | _resistencia_ | int | Impacta a durabilidade de ferramentas fabricadas com ele |
        | **Ferramenta** | _tipo_ | String | Machado, faca, isqueiro, lanterna | _usar()_ | void | Reduz durabilidade e realiza a ação correspondente, como cortar madeira ou acender fogo |
        | | _eficiencia_ | int | Impacta a rapidez ao coletar recursos |
        | **Arma** | _tipo_ | String | Corpo a corpo ou à distância | _atacar(Alvo inimigo)_ | **/** | Causa dano ao inimigo e pode consumir munição (se aplicável) |
        | | _dano_ | int | Quantidade de dano causado ao alvo |
        | | _alcance_ | int | Distância efetiva da arma |
        | **Remedio** | _tipo_ | String | Bandagem, antibiótico, analgésico | _usar()_ | void | Aplica o efeito medicinal no personagem |
        | | _efeito_ | String | Cura ferimentos, alivia dor, trata infecções |

- # Inventário - CLASSE
    - ## Atributos
        | Nome | Nome | Descrição |
        |:-:|:-:|:-:|
        | _listaDeItens_ | Item[] | Contém todos os objetos que o personagem possui |
        | _pesoTotal_ | int | Soma do peso dos itens carregados |
        | _espacoDisponivel_ | int | Capacidade máxima do inventário |

    - ## Métodos
        | Nome | Nome | Descrição |
        |:-:|:-:|:-:|
        | _adicionarItem(Item item)_ | void | Insere um novo item no inventário, se houver espaço |
        | _removerItem(String nomeItem)_ | void | Retira um item do inventário |
        | _usarItem(String nomeItem)_ | void | Ativa o efeito do item no personagem |

- # Evento - SUPERCLASSE
    - ## Atributos
        | Nome | Nome | Descrição |
        |:-:|:-:|:-:|
        | _nome_ | String | Identificação do evento |
        | _descricao_ | String | Texto explicativo sobre o evento |
        | _probabilidadeDeOcorrencia_ | **/** | Define a chance de um evento acontecer a cada turno |
        | _impacto_ | **/** | Indica quais aspectos do jogo serão alterados (vida, fome, sede, energia, sanidade, inventário, etc.) |
        | _condicaoDeAtivacao_ | **/** | Determina se o evento pode ocorrer (ex.: apenas em determinados ambientes) |

    - ## Métodos
        | Nome | Nome | Descrição |
        |:-:|:-:|:-:|
        | _executar(Personagem jogador, Ambiente local)_ | void | Define a lógica do evento e aplica seus efeitos ao personagem e ao ambiente |

    - ## Subclasses
        | Nome | Atributos adicionais | Nome | Descrição | Exemplos |
        |:-:|:-:|:-:|:-:|:-:|
        | **EventoClimatico** | _tipo_ | String | Nevasca, tempestade, calor extremo, etc. | **Nevasca**: Reduz a visibilidade e exige mais energia para se movimentar |
        | | _duracao_ | int | Quantidade de turnos que o evento permanece ativo | **Chuva Forte**: Pode encharcar roupas e reduzir temperatura corporal |
        | | _efeitoNoAmbiente_ | **/** | Pode dificultar ou facilitar certas ações, como encontrar comida ou se deslocar | **Calor Extremo**: Aumenta o consumo de água do personagem |
        | **EventoCriatura** | _tipo_ | String | Lobo, urso, cobra, corvo, etc. | **Ataque de Lobo**: O personagem perde pontos de vida e pode ter comida roubada |
        | | _nivelDePerigo_ | **/** | Define o impacto potencial no personagem | **Cobra venenosa**: Se picado, o jogador perde vida progressivamente até encontrar um antídoto |
        | | _opcoesDeAcao_ | **/** | Algumas criaturas podem ser evitadas ou combatidas | **Corvos furtivos**: Reduzem a sanidade do personagem, criando alucinações temporárias |
        | **EventoDescoberta** | _tipo_ | String | Caverna, abrigo, suplementos abandonados, etc. | **Abrigo abandonado**: O jogador pode encontrar alimentos, mas há o risco de ser ocupado por outra criatura |
        | | _recursosEncontrados_ | **/** | Pode incluir comida, água, ferramentas ou armas | **Fonte de água**: Pode fornecer água potável ou exigir filtragem antes do consumo |
        | | _condicaoEspecial_ | **/** | Algumas descobertas podem exigir habilidades específicas para serem exploradas | **Ruínas misteriosas**: Possuem itens raros, mas podem estar protegidas por armadilhas |
        | **EventoDoencaFerimento** | _tipo_ | String | Infecção, febre, desidratação, fratura, etc. | **Hipotermia**: Se o jogador não se aquecer, perderá vida gradativamente |
        | | _impacto_ | **/** | Reduz atributos como vida, energia ou sanidade | **Infecção**: Se um ferimento não for tratado, a infecção pode piorar e reduzir drasticamente os atributos |
        | | _curaDisponivel_ | **/** | Alguns eventos exigem itens específicos para serem tratados | **Desidratação**: Aumenta a fadiga e pode causar alucinações |

- # GerenciadorDeEventos - CLASSE
    - ## Atributos
        | Nome | Nome | Descrição |
        |:-:|:-:|:-:|
        | _listaDeEventosPossiveis_ | Evento[] | Contém todos os eventos disponíveis |
        | _probabilidadeDeOcorrencia_ | **/** | Define a frequência dos eventos |
        | _historicoDeEventos_ | String[] | Evita repetições excessivas |

    - ## Métodos
        | Nome | Nome | Descrição |
        |:-:|:-:|:-:|
        | _sortearEvento(Ambiente local)_ | void | Escolhe aleatoriamente um evento compatível com o ambiente atual |
        | _aplicarEvento(Personagem jogador)_ | void | Executa os efeitos do evento no personagem |
        | _removerEvento(Evento evento)_ | void | Se um evento tiver duração limitada, ele pode ser encerrado após alguns turnos |

- # Ambiente - SUPERCLASSE
    - ## Atributos
        | Nome | Nome | Descrição |
        |:-:|:-:|:-:|
        | _nome_ | String | Identificação do ambiente |
        | _descricao_ | String | Texto explicativo sobre as características do local |
        | _dificuldadeDeExploracao_ | **/** | Define se o ambiente consome mais energia ao ser percorrido |
        | _recursosDisponiveis_ | **/** | Lista de itens que podem ser coletados na área |
        | _probabilidadeDeEventos_ | **/** | Define a frequência e o nome de eventos que ocorrem no ambiente |
        | _condicoesClimaticasPredominantes_ | **/** | Influencia a jogabilidade (exemplo: florestas são úmidas, montanhas podem ser frias, desertos podem ter tempestades de areia) |

    - ## Métodos
        | Nome | Nome | Descrição |
        |:-:|:-:|:-:|
        | _explorar(Personagem jogador)_ | void | O personagem pode tentar encontrar recursos ou enfrentar desafios no ambiente |
        | _gerarEvento()_ | void | Sorteia um evento compatível com o bioma |
        | _modificarClima()_ | void | Simula mudanças climáticas no ambiente, impactando a jogabilidade |

    - ## Subclasses
        | Nome | Atributos adicionais | Nome | Descrição | Recursos disponíveis | Eventos comuns |
        |:-:|:-:|:-:|:-:|:-:|:-:|
        | **AmbienteFloresta** | _vegetacaoDensa_ | **/** | Reduz visibilidade e dificulta a movimentação | Frutas, raízes e cogumelos (alguns venenosos) | Ataque de lobo ou urso |
        | | _faunaAbundante_ | **/** | Possibilidade de caça, mas também de ataques de criaturas | Madeira para fogueiras e ferramentas | Encontro com um explorador perdido |
        | | _climaUmido_ | **/** | A umidade dificulta o acendimento de fogueiras | Pequenos animais para caça | Chuva intensa, dificultando a exploração |
        | **AmbienteMontanha** | _terrenoAcidentado_ | **/** | Exige mais energia para ser explorado | Minérios e pedras preciosas | Nevasca repentina, reduzindo drasticamente a temperatura |
        | | _climaInstavel_ | **/** | Nevascas e ventos fortes podem ocorrer repentinamente | Água de degelo, mas precisa ser purificada | Deslizamento de pedras, causando ferimentos |
        | | _baixaVegetacao_ | **/** | Pouca disponibilidade de alimentos naturais | Refúgios naturais em cavernas | Descoberta de uma caverna segura |
        | **AmbienteCaverna** | _poucaLuz_ | **/** | Exige lanterna ou tochas para exploração eficiente | Rochas e minérios raros | Encontro com uma criatura hostil |
        | | _presencaDeCriaturasDesconhecidas_ | **/** | Pode ser um refúgio seguro ou um local perigoso | Pequenos lagos subterrâneos (algumas vezes contaminados) | Descoberta de um túnel oculto |
        | | _aguaDeGotejamento_ | **/** | Possível fonte de hidratação | Ossos e vestígios de exploradores antigos | Desmoronamento parcial, bloqueando saídas |
        | **AmbienteLagoRio** | _aguaAbundante_ | **/** | Pode ser potável ou precisar de purificação | Peixes e algas comestíveis | Ataque de criatura aquática (como piranhas ou jacarés) |
        | | _possibilidadeDePesca_ | **/** | Peixes podem ser uma excelente fonte de alimento | Água doce (algumas vezes contaminada) | Tempestade, aumentando o nível da água |
        | | _terrenoLamacento_ | **/** | Pode dificultar a movimentação | Vegetação ribeirinha útil para fabricação de cordas e armadilhas | Encontro de um barco abandonado |
        | **AmbienteRuinas** | _estruturasInstaveis_ | **/** | O local pode desmoronar a qualquer momento | Ferramentas antigas e munição | Encontrar um grupo de sobreviventes (podem ser aliados ou hostis) |
        | | _presencaDeOutrosSobreviventes_ | **/** | Algumas ruínas podem estar ocupadas | Alimentos enlatados ainda comestíveis | Armadilhas deixadas por antigos ocupantes |
        | | _baixoRiscoClimatico_ | **/** | Normalmente oferecem abrigo contra o clima | Mapas e pistas sobre o ambiente ao redor | Descoberta de uma passagem secreta para outra área |

- # GerenciadorDeAmbientes - CLASSE
    - ## Atributos
        | Nome | Nome | Descrição |
        |:-:|:-:|:-:|
        | _listaDeAmbientesDisponiveis_ | Ambiente[] | Define as áreas do jogo |
        | _climaGlobal_ | **/** | Pode influenciar vários ambientes ao mesmo tempo |
        | _historicoDeMovimentacao_ | String[] | Registra onde o jogador já esteve |

    - ## Métodos
        | Nome | Nome | Descrição |
        |:-:|:-:|:-:|
        | _mudarAmbiente(Personagem jogador, Ambiente novoAmbiente)_ | void | Move o personagem para uma nova área |
        | _gerarEvento(Ambiente local)_ | void | Ativa um evento aleatório com base no ambiente atual |
        | _modificarRecursos(Ambiente local)_ | void | Atualiza a quantidade de recursos disponíveis conforme são coletados |