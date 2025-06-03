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

    O pacote pré-compilado .jar está disponível na [página de Releases](https://github.com/gabriel1ns/Ultima-Fronteira---LPOO-2025.1/releases) e [aqui](https://github.com/gabriel1ns/Ultima-Fronteira---LPOO-2025.1/releases/download/CHECKPOINT-4/ULTIMA-FRONTEIRA-1.0.jar).
    
    Depois de baixá-lo, abra o terminal na mesma pasta em que o pacote se localiza e digite
    
    `java -jar ULTIMA-FRONTEIRA.jar`

- ## A partir do código-fonte (compilar localmente)

    Caso deseje compilar o projeto localmente, primeiro clone o repositório na sua máquina

    `git clone https://github.com/gabriel1ns/Ultima-Fronteira---LPOO-2025.1.git`

    ou baixe o código fonte [aqui](https://github.com/gabriel1ns/Ultima-Fronteira---LPOO-2025.1/archive/refs/tags/CHECKPOINT-4.zip).

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

Um diagrama de classes foi feito em código Mermaid com o auxílio de inteligência artificial, [e pode ser visualizado aqui](https://www.mermaidchart.com/play?utm_source=mermaid_live_editor&utm_medium=share#pako:eNrNGttu2zj2V4Q8KeO62H01igKeODM10KRpmhYLTAYFLTGOdiTSQ1GZtjOz_7KP-7Bf0R_bQ1ISLzqUZHcXWKBIbZ0Lz_0cHvn3s4zn9Gx1tlwu71nG2UOxX92zJJGPtKKrZEdqqr6WnP-yShjl-gv5zBu5Smj5i_qalaSuNVGSPBY5vawO8vMVrXZU1N_zT6tEigaYmBMU7qYge0Gqe5YXgmay4Cx5fWvoNTy5IgVLfr9n91I9W1TwNX0nRcH2P_18nqySJ17kBv9Pl-wGDuSM7GlliZeMKz0Mdf9Q4w8fP5JdURY5AR3qA80KUg5QCHzdNZLXq6Rg8qefe0DBniiTRBR8lWz7zz14TwVlwDHnYkO3Du6POGBEpgvOy5z_xrQEvZGs8q2pniXh_4A-8mcraQXm7RnuqbTipMrsQ-kWVZMTsW5tkloZukf1JWsqfYD1W0taU3ka4X6aUNE4xoEgEBUpiy9EfCAlny-wa-CmJuLVwBkpJt41xJwG-MGjQBF3a-yJUFDkFzpwI7xfRUMlDZWpR7Exq0tuDvTOHqSgldpJwYwcSKZPuiKfior4vvm1IUxqKEQgq31gYR6thSCfXxe1k3ALx3ho_FJVzqQgQgFS9Sc0whDDzYcdWIMSFhKRvMigYgVsPVMJWvEnahA6fgqp5Yji9adjiKSUFMR821tqY2yFOorWklw80jamQlYK-oF8iUBVzmvGCtgbfQhuz0UxLgJvD2IPcN76PsfCM0T5hpiUbkNYvH2_vr7bbtaby49X679tr9ZhqUD6hSwOfPDwQGseC2VfHe3hWDU-94up1TuNFL-R6nIHckZANyDtuCvGvfAtDlD6Q6mFIvNES8cVOc0gB3QWbxrR16MUjWnDIA8CF_X2Gqq9U39ywgJHkTIjLPO9tMwdEYb-UzzTiU56_t_UzLpowCFwkgePBM0GbIC5f20sMQDNT60fKFQBrapr8lFbWhLcov-vdpxvlCsCFRtaqmOS7WZoiA5tygwervvFH9c2VquBVBec1U3lJ2A_za4fqISxY5VEBqKegD7QQvrJtLCcETVGJyxspGtF0ZpMSKPILrVAA29mRibhTHgjFwerwXrfuGF8aAT9QgbBtHziZVPRmBkUk7QlCRQdlctG4o0-N3VGkBDjg5Yglu0tfSQPWtrTUt5RsyxUZrqT3pOarq8boNUzUukX2IMgX_iGwgiO1IUh35Fqe5xBJ2vD7CqjjBeoiBXVG1_Rb7b0LQiXuyP1YgCbqiDzjrx8ChyKTkM5rTNQnwxHIlX29XNXW8MUm3-cYf0TzRow_5gbAy_BOcdevjad4DG4wxbpDQp0elswZriA4CayyDhSheu29tXRyufsG0wlDtcQi-AUfOqMcn9muB3nl6PUh6YlwZRuzYAcCQaxwbyWwx2DqKsxFlkdz3R033GcTjUVawkOz0naDX-u2bAioooyqJJGdh16AIvBOvVi9VwzRgpNbKqzDE8rPcawKl34jgpvstM38h6iAtBcvF2E6ppmtK7bZZg3_Qe-s0cMvOdF5LOAzf8uQjecsozAhDpobibjrukeMuspCFBhqvANEeQCQnGVtGXZn1fQE0bC1mVynMb9ZNSJi0XJrS-0RkHlnme_dbUrQCF6evug2j71Da9rcHIBsRUUvYPgu749b6ix52AVmxcPRdaULc6nAzTsYVfqpJ2Ku3BO_Zb2gsuFueYysITG8Y1hBg3MHh1DB3eeB70FZMvM7VOtyVa9q51dSBeKbu8KHbtWkVhjeyPs5LQ7xau8w0WcQV9DMZa8W6X681-bMK55wur90OyL_ui-o_hfB4x3TZ11bDukdZ9t_nPvrtPFnl6iBbbUi_EOofuADvl24-9flpBAMpaPrPXqEK1HGbkzeR7rxMSiBfztnrYsGDiOrmUTXA8WOMf0HLOcUcXXggtJiYjYdlx-dG1dkQPZ0AsOPBjJuFLkFakfr8jhH3Pjfvyl0CL2MgAJ92VGSqgdRGzzDelkgtrhLgPMyzG3LUWIWoIimPqCMmTX0-2G1gvAzLATPa85KENpPQL1iqVv8WnQ8THkfjE3kagerm6tMwmc3cYcHGx8c64t3YVwA5nWXnJdsw1i9K4RjOOra_h39_72-s27jzfr2_XHD9u7N7fbYJU9EZxdgrapOKjpezQfV5HMx8n6Do0VeNuw39E8XMb-wKvw0SWjYh_eAbSNnIx5lti2gUsKsyQ7NPJNI-GvrS4Fg35ChOEXenH5QGr11kXhRKFr7Erawq4Ia6Sa-6IYbnfCmEvya0MnAr03vgheLUYCzDGEU_igqTEwNQwK5oPlXsK4Ub7m-72Cmri34IXDzana0F1-dOjSjmyQKAeYSmQ7jo0CvXdzYVfUQkQGMAOzJwQYkKlFTfhltYUwEWqX1R_4Zvd3mrk1EjGlhMr26GTrgf_mvf7D6SJ3ccVnBNe8B45j6qIdhaoyGIe6VwAUoY3UKXi_hJjGbMN5EtFeF6dR_VvWiDG6uhDFcEaAKI7XUlGMNpkiJ5icQIHvmSxkSXPtcwdDBdqLF2RXS0EkeflSX8TNw0IF8APJqHo6uH97RLZ7DUDGkvftWzb9o5z7s7_enyXL5cv2k-lQqwR64QiW88OcVQJXpDHcyFw5SdffPlWiqUqufu6RwAhiaIygo4J1tXOMwDmFqLY5hhvVpDfWDLruFjZJ5ZZyB9nR0OB9t1z2FP3Qu0oOcNNsikma2NQ8wUCJGSt0WtiE6n3DDGK38s0i1a__8aBwFHAV_cvz598p-xhC9RuRr_-KUbQnMFwgFN1WX4uepAciSHLTsEden7fE6vwXf4BI3eiKPbfvUjFoN2yHMDfrNcR_Rf_ij-fPw1MjGIPzEbzhux2D67yTDKRSr9AmcWbyavvZAG1OaBK1kTeU7bsRzTzYskcR-rs_DrftLIrhdzEPDZMfE9m-cjhJY2cLPEiqXMMEncb3o2KVCArTrPDogsWrJfYGEoy0f6vgC6h_BqLnLipAVZv3aIVFKkBraNsaknaRleidaj3JTae7bRkcbmTAo5pFF3PqBFmkD0xQuSNdWMTmkQbT3olMvEHwNB7hjHgiF2x8HGVlOzwSSU4Q9LHUL8iSvKgPnH39t9r0zuDsRtUMwdAmhE4-idlXff2nwlUNaS43bxLAYy5CNx2sEUJz2zqKKJzjj7davNNP0_o3hBPODkvoMeay1fAoe_W98ii6oChjFdh1vCXsrkjOaThif1-KyWVTBrLQu0Dds7M__wM-QE0w).

#  Créditos e licença

O projeto foi desenvolvido ao longo da cadeira de Linguagens de Programação Orientda a Objetos, do curso de Engenharia da Computção pela Escola Politécnica de Pernambuco, por
    
- [Nicholas Saraiva](https://github.com/Agiliis)
- [Gabriel Lins](https://github.com/gabriel1ns)

sob a tutela do professor Bruno Fernandes e do doutorando Agostinho Freire.

O repositório está sob a [licença do MIT](./LICENSE)
