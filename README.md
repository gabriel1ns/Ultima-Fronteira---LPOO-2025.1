# Bem vindo à ULTIMA FRONTEIRA!
ULTIMA FRONTEIRA é um jogo RPG baseado em turnos, onde você se encontra longe de toda a civilização. Sua única opção é explorar os ambientes do terreno que se encontra, procurando por recursos para manter suas necessidades e se defender de criaturas hostis e outras adversidades.

## Sumário
1. [Gameplay](#gameplay)
2. [Como rodar o jogo](#como-rodar-o-jogo)
    - [Programas necessários](#programas-necessários)
    - [A partir do pacote compilado](#a-partir-do-pacote-compilado)
    - [A partir do código-fonte (compilar localmente)](#a-partir-do-código-fonte-compilar-localmente)
3. [Estrutura do código](#estrutura-do-código)
4. [Créditos e licença](#créditos-e-licença)

# Gameplay

Todo o jogo se dará por meio do terminal do computador, e suas ações serão dadas por escolhas dentre as ações disponíveis:

- <b>Configuração de personagem:</b> escolha o nome do aventureiro e sua classe. Cada classe terá atributos máximos, itens iniciais e habilidades especiais diferentes

- <b>Ações entre turnos:</b> a cada turno, seu personagem terá diferentes ações disponíveis, como
    - Trsansitar para um ambiente aleatório
    - Gerenciar seus recursos (descartar itens, consumir alimentos, combinar materiais)
    - Explorar o ambiente atual em busca de recursos

- <b>Gerencie seus status:</b> 
    - Caso a vida do seu personagem chegue a 0, ele morrerá! 
    - Caso a fome ou a sede dele cheguem a 0, ele começará a perder vida lentamente. Tome cuidado, pois alimentos podem estragar, e algumas fontes de água pode ser impróprias para consumo
    - Caso ele não tenha energia suficiente, não será possível explorar nem mudar de ambiente
    - Caso seu inventário atinja a capacidade máxima, não será possível passar de turno até que o excesso seja retirado

- <b>Eventos aleatórios:</b> cada vez que você decidir explorar o ambiente, um evento randomicamente escolhido daquele ambiente irá acontecer! Você poderá
    - Ativar eventos climáticos exclusivos àquele ambiente
    - Descobrir recursos, como comida, ferramentas, armas ou materiais. Alguns recursos necessitarão de ferramentas para serem extraídos
    - Encontrar com criaturas hostis que batalharão com você
    - Contrair ferimentos ou infecções que duram vários turnos

- <b>Combine materiais:</b> existem combinações entre materiais que geram itens novos! Entretanto, você só descobrirá quais combinações são válidas testando você mesmo (uma combinação possível são 2x madeiras e 2x pedras, resultando num machado)

- <b>Condições de vitória:</b> você vencerá caso
    - Consiga sobreviver por 30 turnos
    - Ou construa uma jangada com os materiais do terreno, para conseguir velejar de volta à civilização

    (Caso atinja alguma condição de vitória, é possível continuar o jogo no modo infinito)

# Como rodar o jogo
- ## Programas necessários:
    - Java 23 ou acima
    
        (Para verificar a versão da sua instalação Java, abra o terminal e coloque `java -version` no windows ou `java --version` no linux) 

    - Maven (opicional, caso deseje [compilar o jogo localmente](#a-partir-do-código-fonte-compilar-localmente)
    
    - Git (opicional, caso deseje [compilar o jogo localmente](#a-partir-do-código-fonte-compilar-localmente)

- ## A partir do pacote compilado

    O pacote pré-compilado .jar está disponível na [página de Releases](https://github.com/gabriel1ns/Ultima-Fronteira---LPOO-2025.1/releases).
    
    Depois de baixá-lo, abra o terminal na mesma pasta em que o pacote se localiza e digite
    
    `java -jar ULTIMA-FRONTEIRA.jar`

- ## A partir do código-fonte (compilar localmente)

    Caso deseje compilar o projeto localmente, primeiro clone o repositório na sua máquina

    `git clone https://github.com/gabriel1ns/Ultima-Fronteira---LPOO-2025.1.git`

    ou baixe o código fonte.

    Após, abra o terminal na mesma pasta do projeto e digite

    `mvn clean package`
    
    para empacotar o projeto em um arquivo .jar, ou

    `mvn exec:java`

    para rodar o jogo imediatamente.

# Estrutura do código
O código feito em Java está separado pelos diretórios da seguinte maneira:
```
.../jogo/
    |
    |----construtores/
    |       |
    |       |----eventos/
    |       |----itens/
    |----enums/
    |       |----eventos/
    |       |----itens/
    |       |----personagem/
    |----gerenciadores/
    |----sistema/
    |       |----eventos/
    |       |----itens/
    |----utils/

```

- <b>jogo:</b> diretório root
- <b>construtores:</b> classes responsáveis por instanciar objetos que podem assumir uma série de atributos pré-determinados, definidos em <b>enums</b>
- <b>enums:</b> classes no formato Enum, que definem listagens para determinados atributos para cada instancia de uma certa classe (Ex.: Arma do tipo Espada, Comida do tipo Carne)
- <b>gerenciadores:</b> classes de 1 instância que gerenciam o funcionamento/acionamento/execução dos objetos de suas respectivas classes (Evento, Ambiente e Inventário)
- <b>sistema:</b> classes que compõem o modelo geral do jogo

# Créditos e licença

O projeto foi desenvolvido ao longo da cadeira de Linguagens de Programação Orientda a Objetos, do curso de Engenharia da Computção pela Escola Politécnica de Pernambuco, por
    
- [Nicholas Saraiva](https://github.com/gabriel1ns)
- [Gabriel Lins](https://github.com/Agiliis)

sob a tutela do professor Bruno Fernandes e do mestrando Agostinho Freire.

O repositório está sob a [licença do MIT](./LICENSE)
