package jogo.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jogo.personagem.Personagem;
import jogo.ambiente.Ambiente; //
import jogo.gerenciadores.GerenciadorDeAmbientes; //
import jogo.gerenciadores.GerenciadorDeEventos; //
import jogo.eventos.Evento; //
import jogo.eventos.criatura.EventoCriatura; //
// EventoCriaturaLobo and EventoCriaturaUrso are not directly instantiated here anymore for simulation
// as the GerenciadorDeEventos will pick them based on Ambiente.

public class TelaDeEscolha extends Application {

    // ... (all existing constants remain the same)
    private static final String FAMILIA_FONTE_MEDIEVAL = "Georgia";
    private static final String COR_TEXTO_MARROM_ESCURO = "#4A3B31";
    private static final String FUNDO_PERGAMINHO = "#F5EACE;";
    private static final String FUNDO_SECAO_PAINEL = "#E8DCC6;";
    private static final String FUNDO_PAINEL_INTERNO = "#F0E6D1;";
    private static final String COR_BORDA_MARROM_SUTIL = "#8B7967;";
    private static final String FUNDO_BOTAO_MARROM = "#7A6A5A;";
    private static final String TEXTO_BOTAO_CLARO = "#F0E6D1;";
    private static final String FUNDO_BOTAO_HOVER_MARROM_ESCURO = "#6A5A4A;";
    private static final String COR_TOGGLE_SELECIONADO = "#5A4A3A;";
    private static final String FUNDO_BOTAO_CONFIRMAR = "#556B2F";
    private static final String FUNDO_BOTAO_CONFIRMAR_HOVER = "#455A25";

    private static final String COR_TRILHA_BARRA_PROGRESSO = "#C6B8A9;";
    private static final String FUNDO_SLOT_INVENTARIO = "#A08C78";
    private static final String FUNDO_PLACEHOLDER_AMBIENTE = "#D7C7B0";
    private static final String COR_VIDA = "#8C1C1C;";
    private static final String COR_FOME = "#D2691E;";
    private static final String COR_SEDE = "#4682B4;";
    private static final String COR_ENERGIA = "#556B2F;";

    private static final double LARGURA_JANELA_ESCOLHA = 900;
    private static final double ALTURA_JANELA_ESCOLHA = 700;
    private static final double VALOR_PADDING_ESCOLHA = 20;
    private static final int TAMANHO_FONTE_CABECALHO_ESCOLHA = 22;
    private static final int TAMANHO_FONTE_SUB_CABECALHO_ESCOLHA = 18;
    private static final int TAMANHO_FONTE_CORPO_ESCOLHA = 14;
    private static final int TAMANHO_FONTE_PEQUENA_ESCOLHA = 12;
    private static final double LARGURA_ARTE_CLASSE = 300;
    private static final double ALTURA_ARTE_CLASSE = 200;
    private static final double LARGURA_BOTAO_CRIAR = 200;
    private static final double ALTURA_BOTAO_CRIAR = 45;

    private static final double VALOR_PADDING_JOGO = 18;
    private static final int TAMANHO_FONTE_CABECALHO_JOGO = 20;
    private static final int TAMANHO_FONTE_ROTULO_JOGO = 13;
    private static final double LARGURA_BARRA_PROGRESSO_MIN_JOGO = 150;
    private static final int ALTURA_BARRA_PROGRESSO_JOGO = 24;
    private static final int COLS_INVENTARIO_JOGO = 4;
    private static final double LARGURA_MIN_BOTAO_ACAO_JOGO = 160;
    private static final double ALTURA_PREF_BOTAO_ACAO_JOGO = 45;
    private static final double TAMANHO_MIN_IMAGEM_AMBIENTE_JOGO = 200;


    private static class InfoClasseDisplay {
        String nomeDisplay;
        String nomeReal;
        String textoIcone;
        String caminhoImagem;
        String vida;
        String fome;
        String sede;
        String energia;
        String itensIniciaisDescricao;
        String habilidadeEspecialDescricao;

        public InfoClasseDisplay(String nomeDisplay, String nomeReal, String textoIcone, String caminhoImagem, String vida, String fome, String sede, String energia, String itensIniciaisDescricao, String habilidadeEspecialDescricao) {
            this.nomeDisplay = nomeDisplay;
            this.nomeReal = nomeReal;
            this.textoIcone = textoIcone;
            this.caminhoImagem = caminhoImagem;
            this.vida = vida;
            this.fome = fome;
            this.sede = sede;
            this.energia = energia;
            this.itensIniciaisDescricao = itensIniciaisDescricao;
            this.habilidadeEspecialDescricao = habilidadeEspecialDescricao;
        }
    }

    private List<InfoClasseDisplay> classesParaExibicao;
    private InfoClasseDisplay classeExibicaoSelecionada;


    private Label nomeClasseExibidoLabel;
    private StackPane arteClassePainel;
    private Label classeVidaLabel, classeFomeLabel, classeSedeLabel, classeEnergiaLabel, classeItensLabel;
    private TextArea classeHabilidadeArea;
    private TextField nomePersonagemCampo;
    private ToggleGroup classeToggleGrupo;

    private Stage palcoPrincipal;

    // Game logic instances
    private Personagem personagem;
    private GerenciadorDeAmbientes gerenciadorDeAmbientes;
    private Ambiente ambienteAtual;
    private GerenciadorDeEventos gerenciadorDeEventos;

    // GUI Elements for the main game screen that need to be updated
    private Button botaoAcaoPrincipal;
    private String nomePersonagemParaAcoes;
    private TextArea areaEventosJogo;
    private Label rotuloNomeAmbienteAtual; // To display current environment name
    private ImageView visualizadorImagemAmbiente; // To display environment image

    @Override
    public void start(Stage palcoPrincipalArgumento) {
        this.palcoPrincipal = palcoPrincipalArgumento;
        this.palcoPrincipal.setTitle("Seleção de Personagem - Ultima Fronteira");

        inicializarClassesParaExibicao();

        BorderPane painelRaizEscolha = new BorderPane();
        painelRaizEscolha.setPadding(new Insets(VALOR_PADDING_ESCOLHA));
        painelRaizEscolha.setStyle("-fx-background-color: " + FUNDO_PERGAMINHO);

        Label tituloTela = new Label("Escolha seu Sobrevivente");
        estilizarRotulo(tituloTela, TAMANHO_FONTE_CABECALHO_ESCOLHA, true, Pos.CENTER, false);
        BorderPane.setAlignment(tituloTela, Pos.CENTER);
        painelRaizEscolha.setTop(tituloTela);
        BorderPane.setMargin(tituloTela, new Insets(0, 0, VALOR_PADDING_ESCOLHA, 0));

        VBox painelEsquerdo = criarPainelEsquerdoSelecao();
        painelEsquerdo.setStyle(obterEstiloPainelPrincipal());
        painelRaizEscolha.setLeft(painelEsquerdo);
        BorderPane.setMargin(painelEsquerdo, new Insets(0, VALOR_PADDING_ESCOLHA, 0, 0));

        VBox painelDireito = criarPainelDireitoSelecao();
        painelDireito.setStyle(obterEstiloPainelPrincipal());
        painelRaizEscolha.setCenter(painelDireito);

        if (!classesParaExibicao.isEmpty()) {
            if (classeToggleGrupo != null && !classeToggleGrupo.getToggles().isEmpty()) {
                ToggleButton primeiroBotao = (ToggleButton) classeToggleGrupo.getToggles().get(0);
                primeiroBotao.setSelected(true);
                selecionarClasseParaExibicao((InfoClasseDisplay) primeiroBotao.getUserData());
            }
        }

        Scene cenaEscolha = new Scene(painelRaizEscolha, LARGURA_JANELA_ESCOLHA, ALTURA_JANELA_ESCOLHA);
        this.palcoPrincipal.setScene(cenaEscolha);
        this.palcoPrincipal.centerOnScreen();
        this.palcoPrincipal.show();
    }

    private void inicializarClassesParaExibicao() {
        classesParaExibicao = new ArrayList<>();
        String basePathImagens = "/img/personagens/";


        classesParaExibicao.add(new InfoClasseDisplay(
                "Lenhador",
                "Lenhador", //
                "\uD83E\uDE93",
                basePathImagens + "Lenhador.png",
                "100", "100", "100", "100", // Assuming default values from PersonagemLenhador
                "Machado", // Placeholder, actual items depend on game start logic
                "Habilidade: Cortes Precisos (Maior chance de obter madeira extra)."
        ));


        classesParaExibicao.add(new InfoClasseDisplay(
                "Sobrevivente",
                "Sobrevivente", //
                "\uD83D\uDEB6",
                basePathImagens + "Sobrevivente.png",
                "100", "100", "100", "100", // Assuming default values from PersonagemSobrevivente
                "Faca de Sobrevivência", // Placeholder
                "Habilidade: Resistente (Menor consumo de fome e sede)."
        ));
    }

    private String obterEstiloPainelPrincipal() {
        return "-fx-background-color: " + FUNDO_SECAO_PAINEL +
                " -fx-border-color: " + COR_BORDA_MARROM_SUTIL +
                " -fx-border-width: 2px;" +
                " -fx-padding: " + VALOR_PADDING_ESCOLHA / 1.5 + "px;" +
                " -fx-border-radius: 3px;" +
                " -fx-background-radius: 3px;";
    }

    private String obterEstiloPainelInterno() {
        return "-fx-background-color: " + FUNDO_PAINEL_INTERNO +
                " -fx-border-color: " + COR_BORDA_MARROM_SUTIL +
                " -fx-border-width: 1px;" +
                " -fx-padding: " + VALOR_PADDING_ESCOLHA / 2 + "px;" +
                " -fx-border-radius: 2px;" +
                " -fx-background-radius: 2px;";
    }

    private VBox criarPainelEsquerdoSelecao() {
        VBox painel = new VBox(VALOR_PADDING_ESCOLHA * 1.2);
        painel.setAlignment(Pos.TOP_LEFT);
        painel.setPrefWidth(LARGURA_JANELA_ESCOLHA * 0.40);

        Label rotuloNomePersonagem = new Label("Nome do Aventureiro:");
        estilizarRotulo(rotuloNomePersonagem, TAMANHO_FONTE_SUB_CABECALHO_ESCOLHA, true, Pos.CENTER_LEFT, false);
        nomePersonagemCampo = new TextField();
        nomePersonagemCampo.setFont(Font.font(FAMILIA_FONTE_MEDIEVAL, TAMANHO_FONTE_CORPO_ESCOLHA));
        nomePersonagemCampo.setStyle("-fx-text-fill: " + COR_TEXTO_MARROM_ESCURO + "; -fx-background-color: " + FUNDO_PAINEL_INTERNO + "; -fx-border-color: " + COR_BORDA_MARROM_SUTIL + ";");
        nomePersonagemCampo.setPromptText("Digite o nome aqui");

        Label rotuloEscolhaClasse = new Label("Escolha sua Vocação:");
        estilizarRotulo(rotuloEscolhaClasse, TAMANHO_FONTE_SUB_CABECALHO_ESCOLHA, true, Pos.CENTER_LEFT, false);

        GridPane gradeBotoesClasse = new GridPane();
        gradeBotoesClasse.setHgap(10);
        gradeBotoesClasse.setVgap(10);
        classeToggleGrupo = new ToggleGroup();

        int coluna = 0;
        int linha = 0;
        for (InfoClasseDisplay infoClasse : classesParaExibicao) {
            ToggleButton botaoClasse = new ToggleButton(infoClasse.textoIcone);
            botaoClasse.setFont(Font.font(FAMILIA_FONTE_MEDIEVAL, FontWeight.BOLD, 22));
            botaoClasse.setToggleGroup(classeToggleGrupo);
            botaoClasse.setUserData(infoClasse);
            estilizarBotaoToggle(botaoClasse);
            botaoClasse.setPrefSize(70, 60);
            botaoClasse.setOnAction(event -> {
                if (botaoClasse.isSelected()) {
                    selecionarClasseParaExibicao((InfoClasseDisplay) botaoClasse.getUserData());
                }
            });
            gradeBotoesClasse.add(botaoClasse, coluna, linha);
            coluna++;
            if (coluna >= 3) {
                coluna = 0;
                linha++;
            }
        }

        nomeClasseExibidoLabel = new Label("<Nome da Vocação>");
        estilizarRotulo(nomeClasseExibidoLabel, TAMANHO_FONTE_CORPO_ESCOLHA, true, Pos.CENTER_LEFT, false);
        nomeClasseExibidoLabel.setPadding(new Insets(10, 0, 5, 0));

        Button botaoCriarPersonagem = new Button("Criar Personagem");
        botaoCriarPersonagem.setPrefSize(LARGURA_BOTAO_CRIAR, ALTURA_BOTAO_CRIAR);
        botaoCriarPersonagem.setFont(Font.font(FAMILIA_FONTE_MEDIEVAL, FontWeight.BOLD, TAMANHO_FONTE_CORPO_ESCOLHA));
        String estiloBaseConfirmar = "-fx-background-color: " + FUNDO_BOTAO_CONFIRMAR + ";" + " -fx-text-fill: " + TEXTO_BOTAO_CLARO + ";" + " -fx-border-color: " + COR_TEXTO_MARROM_ESCURO + ";" + " -fx-border-width: 1.5px;" + " -fx-background-radius: 3px;" + " -fx-border-radius: 3px;";
        String estiloHoverConfirmar = "-fx-background-color: " + FUNDO_BOTAO_CONFIRMAR_HOVER + ";" + " -fx-text-fill: " + TEXTO_BOTAO_CLARO + ";" + " -fx-border-color: " + COR_TEXTO_MARROM_ESCURO + ";" + " -fx-border-width: 1.5px;" + " -fx-background-radius: 3px;" + " -fx-border-radius: 3px;";
        botaoCriarPersonagem.setStyle(estiloBaseConfirmar);
        botaoCriarPersonagem.setOnMouseEntered(e -> botaoCriarPersonagem.setStyle(estiloHoverConfirmar));
        botaoCriarPersonagem.setOnMouseExited(e -> botaoCriarPersonagem.setStyle(estiloBaseConfirmar));

        botaoCriarPersonagem.setOnAction(event -> {
            String nome = nomePersonagemCampo.getText();
            if (nome == null || nome.trim().isEmpty()) {
                logEventoTelaEscolha("Por favor, digite um nome para o aventureiro.");
                return;
            }
            if (classeExibicaoSelecionada == null) {
                logEventoTelaEscolha("Por favor, selecione uma vocação.");
                return;
            }

            int indiceClasseReal = -1;

            if (Personagem.CLASSES != null) {
                for (int i = 0; i < Personagem.CLASSES.length; i++) {
                    if (Personagem.CLASSES[i].equals(classeExibicaoSelecionada.nomeReal)) {
                        indiceClasseReal = i; // Personagem.novoPersonagem uses index+1 for 1-based input, so adjust if needed or ensure consistency
                        // The Personagem.novoPersonagem switch uses 1-based, so Personagem.CLASSES[0] (Lenhador) is choice 1.
                        // So if indiceClasseReal is 0, it maps to choice 1.
                        break;
                    }
                }
            }
            // Adjust for 1-based indexing if Personagem.novoPersonagem expects it.
            // Current Personagem.novoPersonagem: case 1 -> Lenhador. If CLASSES = {"Lenhador", "Sobrevivente"}, Lenhador is at index 0.
            // The original code passes 'escolhaClassePersonagem' which is index based.
            // InputOutput.decisaoEmIntervalo returns 0 for first option.
            // So, if Personagem.novoPersonagem uses the direct index +1, or if it takes 0 for Lenhador...
            // Let's assume Personagem.novoPersonagem maps index 0 from Personagem.CLASSES to its internal case 0 or 1.
            // The Main.java calls: Personagem.novoPersonagem(nomePersonagem, escolhaClassePersonagem);
            // escolhaClassePersonagem = io.decisaoEmIntervalo("Decida sua classe", Personagem.CLASSES, Personagem.CLASSES.length);
            // decisaoEmIntervalo returns 0 for the first option.
            // Personagem.novoPersonagem: case 1: new PersonagemLenhador(nome); default: new PersonagemSobrevivente(nome);
            // This means if indiceClasseReal is 0 (Lenhador), we should pass 1. If 1 (Sobrevivente), pass 0 or other for default.
            // This is a bit inconsistent. Let's align with Main.java's use of decisaoEmIntervalo (0-indexed for Personagem.CLASSES array)
            // and Personagem.novoPersonagem's expectation.
            // If classeExibicaoSelecionada.nomeReal is Personagem.CLASSES[0] ("Lenhador"), then indiceClasseReal = 0. We should pass 1.
            // If classeExibicaoSelecionada.nomeReal is Personagem.CLASSES[1] ("Sobrevivente"), then indiceClasseReal = 1. We should pass anything else (e.g. 0 or 2 for default)

            int escolhaParaNovoPersonagem;
            if (classeExibicaoSelecionada.nomeReal.equals(Personagem.CLASSES[0])) { // Lenhador
                escolhaParaNovoPersonagem = 1;
            } else { // Default to Sobrevivente or other classes
                escolhaParaNovoPersonagem = 0; // Or any value that hits the default in novoPersonagem
            }


            if (indiceClasseReal == -1 && !classeExibicaoSelecionada.nomeReal.equals(Personagem.CLASSES[1])) { // Allow Sobrevivente to be default
                System.err.println("Erro: Classe selecionada na UI ('" + classeExibicaoSelecionada.nomeReal + "') não corresponde a uma classe de jogo válida em Personagem.CLASSES.");
                return;
            }

            this.nomePersonagemParaAcoes = nome.trim();
            this.personagem = Personagem.novoPersonagem(this.nomePersonagemParaAcoes, escolhaParaNovoPersonagem);

            System.out.println("Aventureiro Criado: " + this.personagem.getNome() + " (" + classeExibicaoSelecionada.nomeDisplay + ")");
            System.out.println("Atributos Iniciais: Vida=" + this.personagem.getVida() + ", Fome=" + this.personagem.getFome() + ", Sede=" + this.personagem.getSede() + ", Energia=" + this.personagem.getEnergia());

            configurarTelaPrincipalJogo();
        });
        VBox.setMargin(botaoCriarPersonagem, new Insets(20, 0, 0, 0));

        painel.getChildren().addAll(rotuloNomePersonagem, nomePersonagemCampo, rotuloEscolhaClasse, gradeBotoesClasse, nomeClasseExibidoLabel, botaoCriarPersonagem);
        return painel;
    }

    private void logEventoTelaEscolha(String mensagem) {
        // Placeholder for showing alerts on the character selection screen if needed
        System.out.println("GUI Info: " + mensagem);
        // Example: new Alert(AlertType.WARNING, mensagem).showAndWait();
    }


    private VBox criarPainelDireitoSelecao() {
        VBox painel = new VBox(VALOR_PADDING_ESCOLHA);
        painel.setAlignment(Pos.TOP_CENTER);

        arteClassePainel = new StackPane();
        arteClassePainel.setPrefSize(LARGURA_ARTE_CLASSE, ALTURA_ARTE_CLASSE);
        arteClassePainel.setMinSize(LARGURA_ARTE_CLASSE, ALTURA_ARTE_CLASSE);
        arteClassePainel.setStyle(obterEstiloPainelInterno() + "-fx-alignment: center;");

        VBox painelAtributos = new VBox(5);
        painelAtributos.setStyle(obterEstiloPainelInterno());
        Label tituloAtributos = new Label("Atributos Base:");
        estilizarRotulo(tituloAtributos, TAMANHO_FONTE_CORPO_ESCOLHA, true, Pos.CENTER_LEFT, false);
        classeVidaLabel = criarRotuloAtributoSelecao("Vida: ");
        classeFomeLabel = criarRotuloAtributoSelecao("Fome: ");
        classeSedeLabel = criarRotuloAtributoSelecao("Sede: ");
        classeEnergiaLabel = criarRotuloAtributoSelecao("Energia: ");
        classeItensLabel = criarRotuloAtributoSelecao("Equipamento Inicial: ");
        classeItensLabel.setWrapText(true);
        painelAtributos.getChildren().addAll(tituloAtributos, classeVidaLabel, classeFomeLabel, classeSedeLabel, classeEnergiaLabel, classeItensLabel);

        VBox painelHabilidade = new VBox(5);
        painelHabilidade.setStyle(obterEstiloPainelInterno());
        Label tituloHabilidade = new Label("Habilidade Especial:");
        estilizarRotulo(tituloHabilidade, TAMANHO_FONTE_CORPO_ESCOLHA, true, Pos.CENTER_LEFT, false);
        classeHabilidadeArea = new TextArea();
        classeHabilidadeArea.setWrapText(true);
        classeHabilidadeArea.setEditable(false);
        classeHabilidadeArea.setFont(Font.font(FAMILIA_FONTE_MEDIEVAL, TAMANHO_FONTE_PEQUENA_ESCOLHA));
        classeHabilidadeArea.setStyle("-fx-control-inner-background: " + FUNDO_PAINEL_INTERNO + ";" + "-fx-text-fill: " + COR_TEXTO_MARROM_ESCURO + ";" + "-fx-border-color: " + COR_BORDA_MARROM_SUTIL + ";");
        classeHabilidadeArea.setPrefRowCount(4);
        painelHabilidade.getChildren().addAll(tituloHabilidade, classeHabilidadeArea);

        HBox caixaDetalhes = new HBox(VALOR_PADDING_ESCOLHA / 2, painelAtributos, painelHabilidade);
        HBox.setHgrow(painelAtributos, Priority.ALWAYS);
        HBox.setHgrow(painelHabilidade, Priority.ALWAYS);

        painel.getChildren().addAll(arteClassePainel, caixaDetalhes);
        return painel;
    }

    private Label criarRotuloAtributoSelecao(String prefixo) {
        Label rotulo = new Label(prefixo);
        estilizarRotulo(rotulo, TAMANHO_FONTE_PEQUENA_ESCOLHA, false, Pos.CENTER_LEFT, false);
        return rotulo;
    }

    private void selecionarClasseParaExibicao(InfoClasseDisplay infoClasse) {
        classeExibicaoSelecionada = infoClasse;
        atualizarDetalhesClasseExibicao();
    }

    private void atualizarDetalhesClasseExibicao() {
        if (classeExibicaoSelecionada == null) return;

        nomeClasseExibidoLabel.setText("< " + classeExibicaoSelecionada.nomeDisplay + " >");

        arteClassePainel.getChildren().clear();
        try {
            if (classeExibicaoSelecionada.caminhoImagem != null && !classeExibicaoSelecionada.caminhoImagem.isEmpty()) {
                InputStream streamImagem = getClass().getResourceAsStream(classeExibicaoSelecionada.caminhoImagem);
                Image imagem;

                if (streamImagem != null) {
                    imagem = new Image(streamImagem);
                } else {
                    System.err.println("Recurso de imagem não encontrado: " + classeExibicaoSelecionada.caminhoImagem);
                    throw new RuntimeException("Recurso de imagem não encontrado: " + classeExibicaoSelecionada.caminhoImagem);
                }

                if (imagem.isError()) {
                    throw new RuntimeException("Erro ao carregar imagem: " + classeExibicaoSelecionada.caminhoImagem + (imagem.getException() != null ? " - " + imagem.getException().getMessage() : ""));
                }


                ImageView visualizadorImagem = new ImageView(imagem);
                visualizadorImagem.setFitWidth(LARGURA_ARTE_CLASSE - 10);
                visualizadorImagem.setFitHeight(ALTURA_ARTE_CLASSE - 10);
                visualizadorImagem.setPreserveRatio(true);
                visualizadorImagem.setSmooth(true);
                arteClassePainel.getChildren().add(visualizadorImagem);

            } else {
                Label arteTextoAlternativo = new Label("Arte para\n" + classeExibicaoSelecionada.nomeDisplay + "\nindisponível");
                arteTextoAlternativo.setFont(Font.font(FAMILIA_FONTE_MEDIEVAL, FontWeight.BOLD, 18));
                arteTextoAlternativo.setTextFill(Color.web(COR_TEXTO_MARROM_ESCURO));
                arteTextoAlternativo.setTextAlignment(TextAlignment.CENTER);
                arteTextoAlternativo.setWrapText(true);
                StackPane.setAlignment(arteTextoAlternativo, Pos.CENTER);
                arteClassePainel.getChildren().add(arteTextoAlternativo);
            }
        } catch (Exception e) {
            System.err.println("Falha ao carregar arte da classe '" + classeExibicaoSelecionada.nomeDisplay + "': " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging image loading issues
            Label arteTextoAlternativo = new Label("Arte Indisponível\n" + classeExibicaoSelecionada.nomeDisplay);
            arteTextoAlternativo.setFont(Font.font(FAMILIA_FONTE_MEDIEVAL, FontWeight.BOLD, 18));
            arteTextoAlternativo.setTextFill(Color.web(COR_TEXTO_MARROM_ESCURO));
            arteTextoAlternativo.setTextAlignment(TextAlignment.CENTER);
            arteTextoAlternativo.setWrapText(true);
            StackPane.setAlignment(arteTextoAlternativo, Pos.CENTER);
            arteClassePainel.getChildren().add(arteTextoAlternativo);
        }


        classeVidaLabel.setText("Vida: " + classeExibicaoSelecionada.vida);
        classeFomeLabel.setText("Fome: " + classeExibicaoSelecionada.fome);
        classeSedeLabel.setText("Sede: " + classeExibicaoSelecionada.sede);
        classeEnergiaLabel.setText("Energia: " + classeExibicaoSelecionada.energia);
        classeItensLabel.setText("Equipamento Inicial: " + classeExibicaoSelecionada.itensIniciaisDescricao);
        classeHabilidadeArea.setText(classeExibicaoSelecionada.habilidadeEspecialDescricao);
    }

    private void estilizarRotulo(Label rotulo, int tamanhoFonte, boolean negrito, Pos alinhamento, boolean paraJogoPrincipal) {
        String familiaFonte = FAMILIA_FONTE_MEDIEVAL;
        rotulo.setFont(Font.font(familiaFonte, negrito ? FontWeight.BOLD : FontWeight.NORMAL, tamanhoFonte));
        rotulo.setTextFill(Color.web(COR_TEXTO_MARROM_ESCURO));
        rotulo.setAlignment(alinhamento);
        if (alinhamento == Pos.CENTER || alinhamento == Pos.CENTER_LEFT || alinhamento == Pos.CENTER_RIGHT) {
            rotulo.setMaxWidth(Double.MAX_VALUE);
        }
    }

    private void estilizarBotaoToggle(ToggleButton botao) {
        String estiloBase = "-fx-background-color: " + FUNDO_BOTAO_MARROM + ";" + " -fx-text-fill: " + TEXTO_BOTAO_CLARO + ";" + " -fx-border-color: " + COR_TEXTO_MARROM_ESCURO + ";" + " -fx-border-width: 1.5px;" + " -fx-background-radius: 3px;" + " -fx-border-radius: 3px;";
        String estiloHover = "-fx-background-color: " + FUNDO_BOTAO_HOVER_MARROM_ESCURO + ";" + " -fx-text-fill: " + TEXTO_BOTAO_CLARO + ";" + " -fx-border-color: " + COR_TEXTO_MARROM_ESCURO + ";" + " -fx-border-width: 1.5px;" + " -fx-background-radius: 3px;" + " -fx-border-radius: 3px;";
        String estiloSelecionado = "-fx-background-color: " + COR_TOGGLE_SELECIONADO + ";" + " -fx-text-fill: " + TEXTO_BOTAO_CLARO + ";" + " -fx-border-color: " + COR_TEXTO_MARROM_ESCURO + ";" + " -fx-border-width: 1.5px;" + " -fx-background-radius: 3px;" + " -fx-border-radius: 3px;";

        botao.setStyle(estiloBase);
        botao.setOnMouseEntered(e -> { if (!botao.isSelected()) botao.setStyle(estiloHover); });
        botao.setOnMouseExited(e -> { if (!botao.isSelected()) botao.setStyle(estiloBase); });
        botao.selectedProperty().addListener((obs, foiSelecionado, estaSelecionado) -> {
            if (estaSelecionado) {
                botao.setStyle(estiloSelecionado);
            } else {
                botao.setStyle(estiloBase);
            }
        });
        botao.setPrefSize(70, 60);
    }


    private void configurarTelaPrincipalJogo() {
        this.palcoPrincipal.setTitle("Ultima Fronteira - O Jogo");

        // Initialize game logic managers
        this.gerenciadorDeAmbientes = new GerenciadorDeAmbientes(); //
        this.ambienteAtual = this.gerenciadorDeAmbientes.sortearAmbiente(); //
        this.gerenciadorDeEventos = new GerenciadorDeEventos(
                this.ambienteAtual.getEventosPossiveis(), //
                this.ambienteAtual.getProbabilidadeDeEventos() //
        ); //


        BorderPane painelRaizJogo = new BorderPane();
        painelRaizJogo.setPadding(new Insets(VALOR_PADDING_JOGO));
        painelRaizJogo.setStyle("-fx-background-color: " + FUNDO_PERGAMINHO);

        VBox secaoAtributosJogo = criarSecaoAtributosJogo();
        VBox secaoAmbienteJogo = criarSecaoAmbienteJogo();
        VBox secaoInventarioJogo = criarSecaoInventarioJogo();
        VBox secaoAcoesJogo = criarSecaoAcoesJogo();
        VBox containerEventosJogo = criarContainerEventosJogo();

        String estiloSecaoJogo = "-fx-background-color: " + FUNDO_SECAO_PAINEL +
                " -fx-border-color: " + COR_BORDA_MARROM_SUTIL +
                " -fx-border-width: 1px;" +
                " -fx-padding: 10px;" +
                " -fx-border-radius: 3px;" +
                " -fx-background-radius: 3px;";

        secaoAtributosJogo.setStyle(estiloSecaoJogo);
        secaoAtributosJogo.setPrefWidth(280);
        secaoAtributosJogo.setMaxWidth(350);
        secaoAmbienteJogo.setStyle(estiloSecaoJogo);
        secaoInventarioJogo.setStyle(estiloSecaoJogo);
        secaoInventarioJogo.setPrefWidth(320);
        secaoInventarioJogo.setMaxWidth(400);
        secaoAcoesJogo.setStyle(estiloSecaoJogo);
        containerEventosJogo.setStyle(estiloSecaoJogo);

        painelRaizJogo.setLeft(secaoAtributosJogo);
        BorderPane.setMargin(secaoAtributosJogo, new Insets(0, VALOR_PADDING_JOGO / 2, 0, 0));
        painelRaizJogo.setCenter(secaoAmbienteJogo);
        painelRaizJogo.setRight(secaoInventarioJogo);
        BorderPane.setMargin(secaoInventarioJogo, new Insets(0, 0, 0, VALOR_PADDING_JOGO / 2));

        HBox areaInferiorJogo = new HBox(VALOR_PADDING_JOGO);
        areaInferiorJogo.setAlignment(Pos.TOP_LEFT);
        areaInferiorJogo.getChildren().addAll(secaoAcoesJogo, containerEventosJogo);
        HBox.setHgrow(secaoAcoesJogo, Priority.NEVER);
        HBox.setHgrow(containerEventosJogo, Priority.ALWAYS);

        painelRaizJogo.setBottom(areaInferiorJogo);
        BorderPane.setMargin(areaInferiorJogo, new Insets(VALOR_PADDING_JOGO, 0, 0, 0));

        Scene cenaPrincipalJogo = new Scene(painelRaizJogo, 1100, 750);
        this.palcoPrincipal.setScene(cenaPrincipalJogo);
        this.palcoPrincipal.setMinWidth(800);
        this.palcoPrincipal.setMinHeight(600);
        this.palcoPrincipal.centerOnScreen();

        logEvento("Bem-vindo(a) à Ultima Fronteira, " + this.nomePersonagemParaAcoes + "!");
        logEvento("Ambiente inicial: " + this.ambienteAtual.getNome()); //
        atualizarRotuloAmbiente();
        atualizarBotaoAcaoPrincipal(this.gerenciadorDeEventos.getEventoCriaturaAtivo()); //
    }

    private void atualizarRotuloAmbiente() {
        if (this.rotuloNomeAmbienteAtual != null && this.ambienteAtual != null) {
            this.rotuloNomeAmbienteAtual.setText(" Ambiente Atual: " + this.ambienteAtual.getNome()); //
        }
        // TODO: Update environment image in visualizadorImagemAmbiente
        // Example:
        // String imagePath = getImagePathForEnvironment(this.ambienteAtual.getNome());
        // if (imagePath != null) {
        // try (InputStream stream = getClass().getResourceAsStream(imagePath)) {
        // if (stream != null) {
        // visualizadorImagemAmbiente.setImage(new Image(stream));
        // } else {
        // visualizadorImagemAmbiente.setImage(null); // Or a default placeholder
        // }
        // } catch (Exception e) {
        // visualizadorImagemAmbiente.setImage(null);
        // e.printStackTrace();
        // }
        // } else {
        // visualizadorImagemAmbiente.setImage(null);
        // }
    }


    private VBox criarSecaoAtributosJogo() {
        VBox caixaAtributos = new VBox(10);
        caixaAtributos.setAlignment(Pos.TOP_LEFT);
        VBox.setVgrow(caixaAtributos, Priority.NEVER);

        Label rotuloTituloAtributos = new Label("Atributos de " + this.nomePersonagemParaAcoes);
        estilizarRotulo(rotuloTituloAtributos, TAMANHO_FONTE_CABECALHO_JOGO, true, Pos.CENTER_LEFT, true);

        ProgressBar barraVida = new ProgressBar(this.personagem != null ? (double)this.personagem.getVida() / 100.0 : 0.85);
        ProgressBar barraFome = new ProgressBar(this.personagem != null ? (double)this.personagem.getFome() / 100.0 : 0.65);
        ProgressBar barraSede = new ProgressBar(this.personagem != null ? (double)this.personagem.getSede() / 100.0 : 0.70);
        ProgressBar barraEnergia = new ProgressBar(this.personagem != null ? (double)this.personagem.getEnergia() / 100.0 : 0.90);
        Label rotuloSanidade = new Label(this.personagem != null ? String.valueOf(this.personagem.getSanidade()) : "100");
        estilizarRotulo(rotuloSanidade, TAMANHO_FONTE_ROTULO_JOGO, true, Pos.CENTER_LEFT, true);
        Label valorStatus = new Label(this.personagem != null ? obterStatusPersonagem(this.personagem) : "<status>");
        estilizarRotulo(valorStatus, TAMANHO_FONTE_ROTULO_JOGO, true, Pos.CENTER_LEFT, true);


        configurarBarraProgressoJogo(barraVida, COR_VIDA);
        configurarBarraProgressoJogo(barraFome, COR_FOME);
        configurarBarraProgressoJogo(barraSede, COR_SEDE);
        configurarBarraProgressoJogo(barraEnergia, COR_ENERGIA);


        caixaAtributos.getChildren().addAll(
                rotuloTituloAtributos,
                criarLinhaAtributoJogo("Vida:", barraVida),
                criarLinhaAtributoJogo("Fome:", barraFome),
                criarLinhaAtributoJogo("Sede:", barraSede),
                criarLinhaAtributoJogo("Energia:", barraEnergia),
                criarLinhaAtributoJogo("Sanidade:", rotuloSanidade),
                criarLinhaAtributoJogo("Status:", valorStatus)
        );
        return caixaAtributos;
    }

    private String obterStatusPersonagem(Personagem p) { //
        if (p == null) return "<status>";
        if (p.getVida() <= 0) return "Morto";
        if (p.getVida() < 25) return "Em Perigo";
        if (p.getFome() < 10 || p.getSede() < 10) return "Esgotado";
        return "Normal";
    }


    private HBox criarLinhaAtributoJogo(String textoRotulo, Node controle) {
        HBox linha = new HBox(8);
        linha.setAlignment(Pos.CENTER_LEFT);
        Label rotulo = new Label(textoRotulo);
        estilizarRotulo(rotulo, TAMANHO_FONTE_ROTULO_JOGO, false, Pos.CENTER_LEFT, true);
        rotulo.setMinWidth(70);

        if (controle instanceof ProgressBar) {
            HBox.setHgrow(controle, Priority.ALWAYS);
            ((ProgressBar) controle).setMaxWidth(Double.MAX_VALUE);
        }
        linha.getChildren().addAll(rotulo, controle);
        return linha;
    }

    private void configurarBarraProgressoJogo(ProgressBar bp, String corDestaque) {
        bp.setPrefHeight(ALTURA_BARRA_PROGRESSO_JOGO);
        bp.setMinWidth(LARGURA_BARRA_PROGRESSO_MIN_JOGO);
        bp.setStyle("-fx-control-inner-background: " + COR_TRILHA_BARRA_PROGRESSO + ";" +
                " -fx-border-color: " + COR_BORDA_MARROM_SUTIL + ";" +
                " -fx-border-width: 1px;" +
                " -fx-accent: " + corDestaque + ";");
    }

    private VBox criarSecaoAmbienteJogo() {
        VBox containerAmbiente = new VBox(10);
        containerAmbiente.setAlignment(Pos.TOP_CENTER);
        VBox.setVgrow(containerAmbiente, Priority.ALWAYS);

        this.rotuloNomeAmbienteAtual = new Label(" Ambiente Atual: <Carregando...>");
        estilizarRotulo(this.rotuloNomeAmbienteAtual, TAMANHO_FONTE_CABECALHO_JOGO, true, Pos.CENTER, true);

        StackPane painelExibicaoAmbiente = new StackPane();
        painelExibicaoAmbiente.setAlignment(Pos.CENTER);
        VBox.setVgrow(painelExibicaoAmbiente, Priority.ALWAYS);
        painelExibicaoAmbiente.setStyle("-fx-background-color: " + FUNDO_PLACEHOLDER_AMBIENTE + ";" +
                " -fx-border-color: " + COR_BORDA_MARROM_SUTIL + ";" +
                " -fx-border-width: 1px; -fx-border-style: dashed;");
        painelExibicaoAmbiente.setMinSize(TAMANHO_MIN_IMAGEM_AMBIENTE_JOGO, TAMANHO_MIN_IMAGEM_AMBIENTE_JOGO);

        this.visualizadorImagemAmbiente = new ImageView();
        this.visualizadorImagemAmbiente.setFitHeight(TAMANHO_MIN_IMAGEM_AMBIENTE_JOGO - 10); // Example size
        this.visualizadorImagemAmbiente.setFitWidth(TAMANHO_MIN_IMAGEM_AMBIENTE_JOGO * 1.5); // Example size
        this.visualizadorImagemAmbiente.setPreserveRatio(true);
        this.visualizadorImagemAmbiente.setSmooth(true);

        Label textoAlternativo = new Label("\"Visão do Ambiente\""); // Fallback if image is null
        textoAlternativo.setFont(Font.font(FAMILIA_FONTE_MEDIEVAL, FontWeight.NORMAL, 18));
        textoAlternativo.setTextFill(Color.web(COR_TEXTO_MARROM_ESCURO));

        // Show text if image is not loaded
        this.visualizadorImagemAmbiente.imageProperty().addListener((obs, oldImg, newImg) -> {
            if (newImg == null) {
                if (!painelExibicaoAmbiente.getChildren().contains(textoAlternativo)) {
                    painelExibicaoAmbiente.getChildren().add(textoAlternativo);
                }
            } else {
                painelExibicaoAmbiente.getChildren().remove(textoAlternativo);
            }
        });
        painelExibicaoAmbiente.getChildren().addAll(this.visualizadorImagemAmbiente, textoAlternativo);


        containerAmbiente.getChildren().addAll(this.rotuloNomeAmbienteAtual, painelExibicaoAmbiente);
        return containerAmbiente;
    }

    private VBox criarSecaoInventarioJogo() {
        VBox containerInventario = new VBox(10);
        containerInventario.setAlignment(Pos.TOP_CENTER);
        VBox.setVgrow(containerInventario, Priority.ALWAYS);

        Label rotuloTituloInventario = new Label("Inventário");
        estilizarRotulo(rotuloTituloInventario, TAMANHO_FONTE_CABECALHO_JOGO, true, Pos.CENTER, true);

        GridPane gradeInventario = new GridPane();
        gradeInventario.setHgap(8);
        gradeInventario.setVgap(8);
        gradeInventario.setAlignment(Pos.TOP_CENTER);
        VBox.setVgrow(gradeInventario, Priority.ALWAYS);

        int capacidadeInventario = (this.personagem != null && this.personagem.getInventario() != null)
                ? this.personagem.getInventario().getCapacidadeMaxima() : 20;
        int numLinhas = (int) Math.ceil((double) capacidadeInventario / COLS_INVENTARIO_JOGO);

        for (int i = 0; i < COLS_INVENTARIO_JOGO; i++) {
            ColumnConstraints restricaoColuna = new ColumnConstraints();
            restricaoColuna.setHgrow(Priority.SOMETIMES);
            restricaoColuna.setPercentWidth(100.0 / COLS_INVENTARIO_JOGO);
            gradeInventario.getColumnConstraints().add(restricaoColuna);
        }
        for (int i = 0; i < numLinhas; i++) {
            RowConstraints restricaoLinha = new RowConstraints();
            restricaoLinha.setVgrow(Priority.SOMETIMES);
            gradeInventario.getRowConstraints().add(restricaoLinha);
        }


        for (int linhaIdx = 0; linhaIdx < numLinhas; linhaIdx++) {
            for (int colIdx = 0; colIdx < COLS_INVENTARIO_JOGO; colIdx++) {
                if ((linhaIdx * COLS_INVENTARIO_JOGO + colIdx) >= capacidadeInventario) break;
                StackPane celula = new StackPane();
                celula.setAlignment(Pos.CENTER);

                Rectangle espacoItem = new Rectangle();
                espacoItem.setFill(Color.web(FUNDO_SLOT_INVENTARIO));
                espacoItem.setStroke(Color.web(COR_TEXTO_MARROM_ESCURO));
                espacoItem.setArcWidth(5);
                espacoItem.setArcHeight(5);

                espacoItem.widthProperty().bind(celula.widthProperty().subtract(2));
                espacoItem.heightProperty().bind(celula.heightProperty().subtract(2));


                celula.getChildren().add(espacoItem);
                gradeInventario.add(celula, colIdx, linhaIdx);
            }
        }
        containerInventario.getChildren().addAll(rotuloTituloInventario, gradeInventario);
        return containerInventario;
    }

    private VBox criarSecaoAcoesJogo() {
        VBox caixaAcoes = new VBox(10);
        caixaAcoes.setAlignment(Pos.TOP_LEFT);
        VBox.setVgrow(caixaAcoes, Priority.NEVER);

        Label rotuloPerguntaAcao = new Label(" O que " + this.nomePersonagemParaAcoes + " fará?");
        estilizarRotulo(rotuloPerguntaAcao, TAMANHO_FONTE_CABECALHO_JOGO, true, Pos.CENTER_LEFT, true);
        rotuloPerguntaAcao.setWrapText(true);


        GridPane gradeAcoes = new GridPane();
        gradeAcoes.setHgap(10);
        gradeAcoes.setVgap(10);


        Button botaoMudarAmbiente = criarBotaoMedievalJogo("Mudar Ambiente");
        botaoMudarAmbiente.setOnAction(e -> {
            logEvento(this.nomePersonagemParaAcoes + " decide mudar de ambiente!");
            if (this.gerenciadorDeAmbientes != null && this.gerenciadorDeEventos != null) {
                this.ambienteAtual = this.gerenciadorDeAmbientes.sortearAmbiente(); //
                this.gerenciadorDeEventos.setEventosPossiveis(this.ambienteAtual.getEventosPossiveis()); //
                this.gerenciadorDeEventos.setProbabilidadeDeEventos(this.ambienteAtual.getProbabilidadeDeEventos()); //
                logEvento("Novo ambiente: " + this.ambienteAtual.getNome()); //
                atualizarRotuloAmbiente();
                // Clear any existing creature event from old environment before changing button
                atualizarBotaoAcaoPrincipal(null); // Reset button as new environment might not have creature immediately
            }
        });

        Button botaoDescansar = criarBotaoMedievalJogo("Descansar");
        botaoDescansar.setOnAction(e -> {
            logEvento(this.nomePersonagemParaAcoes + " decide descansar.");
            // TODO: Implement logic for resting - affects Personagem stats
        });

        Button botaoGerenciarInventario = criarBotaoMedievalJogo("Inventário");
        botaoGerenciarInventario.setOnAction(e -> {
            logEvento(this.nomePersonagemParaAcoes + " abre o inventário.");
            if (this.personagem != null && this.personagem.getInventario() != null) {
                logEvento(this.personagem.getInventario().toString()); //
            }
        });

        this.botaoAcaoPrincipal = criarBotaoMedievalJogo("Explorar");
        // The actual action for "Explorar" is set in atualizarBotaoAcaoPrincipal

        definirRestricoesGradeBotoesJogo(botaoMudarAmbiente, botaoDescansar, botaoGerenciarInventario, this.botaoAcaoPrincipal);

        gradeAcoes.add(botaoMudarAmbiente, 0, 0);
        gradeAcoes.add(botaoDescansar, 1, 0);
        gradeAcoes.add(botaoGerenciarInventario, 0, 1);
        gradeAcoes.add(this.botaoAcaoPrincipal, 1, 1);

        ColumnConstraints ccAcao = new ColumnConstraints();
        ccAcao.setPercentWidth(50);
        ccAcao.setHgrow(Priority.ALWAYS);
        gradeAcoes.getColumnConstraints().addAll(ccAcao, ccAcao);


        caixaAcoes.getChildren().addAll(rotuloPerguntaAcao, gradeAcoes);
        return caixaAcoes;
    }

    private void atualizarBotaoAcaoPrincipal(EventoCriatura criaturaAtiva) { //
        if (this.botaoAcaoPrincipal == null) return;

        if (criaturaAtiva != null) {
            String nomeCriatura = criaturaAtiva.getNome(); //
            this.botaoAcaoPrincipal.setText("Batalhar " + nomeCriatura);
            this.botaoAcaoPrincipal.setOnAction(e -> {
                // Battle Action
                if (this.gerenciadorDeEventos != null && this.personagem != null && this.ambienteAtual != null) {
                    EventoCriatura criaturaEmBatalha = this.gerenciadorDeEventos.getEventoCriaturaAtivo(); //
                    if (criaturaEmBatalha != null) {
                        logEvento(this.nomePersonagemParaAcoes + " enfrenta " + criaturaEmBatalha.getNome() + "!"); //

                        // Simplified battle: creature is defeated
                        // In a real scenario, this would involve weapon choice, damage calculation etc.
                        // from Turno.faseDeAtaque
                        criaturaEmBatalha.diminuirVida(criaturaEmBatalha.getVida() + 1); //
                        logEvento(criaturaEmBatalha.getNome() + " foi derrotado(a)!");

                        // Process events: this will execute effects and remove the defeated creature (duration 0)
                        // This call is critical as it updates the GerenciadorDeEventos internal state
                        this.gerenciadorDeEventos.executarEventos(this.ambienteAtual, this.personagem); //
                    }
                    // Update button based on the new state of active creature events
                    atualizarBotaoAcaoPrincipal(this.gerenciadorDeEventos.getEventoCriaturaAtivo()); //
                }
            });
        } else {
            // Default "Explorar" Action
            this.botaoAcaoPrincipal.setText("Explorar");
            this.botaoAcaoPrincipal.setOnAction(e -> {
                if (this.gerenciadorDeEventos != null && this.personagem != null && this.ambienteAtual != null) {
                    logEvento(this.nomePersonagemParaAcoes + " explora " + this.ambienteAtual.getNome() + "..."); //

                    // Simulate energy cost if character has enough energy
                    // if (personagem.getEnergia() == 0) { logEvento(...); return; } // As in Turno

                    this.gerenciadorDeEventos.adicionarEventoAleatorio(); //
                    EventoCriatura criaturaEncontrada = this.gerenciadorDeEventos.getEventoCriaturaAtivo(); //

                    if (criaturaEncontrada != null) {
                        logEvento("Uma criatura selvagem apareceu: " + criaturaEncontrada.getNome() + "!"); //
                    } else {
                        // Log other non-creature events if any were triggered
                        // GerenciadorDeEventos.executarEventos also prints to console.
                        // We can call it here to process and log them via GUI as well.
                        // This will also apply their effects.
                        logEvento("Investigando os arredores...");
                        this.gerenciadorDeEventos.executarEventos(this.ambienteAtual, this.personagem); //
                        // Note: The above call might print "não encontrou nada" to console if no active non-creature events.
                        // We might need a way to get a summary from executarEventos for GUI logging.
                    }
                    atualizarBotaoAcaoPrincipal(criaturaEncontrada);
                }
            });
        }
    }

    private void definirRestricoesGradeBotoesJogo(Button... botoes) {
        for (Button botao : botoes) {
            GridPane.setHgrow(botao, Priority.ALWAYS);
            botao.setMaxWidth(Double.MAX_VALUE);
        }
    }


    private Button criarBotaoMedievalJogo(String texto) {
        Button botao = new Button(texto);
        botao.setMinWidth(LARGURA_MIN_BOTAO_ACAO_JOGO / 2.5);
        botao.setPrefHeight(ALTURA_PREF_BOTAO_ACAO_JOGO);
        botao.setMaxWidth(Double.MAX_VALUE);
        botao.setFont(Font.font(FAMILIA_FONTE_MEDIEVAL, FontWeight.BOLD, TAMANHO_FONTE_ROTULO_JOGO));

        String estiloBase = "-fx-background-color: " + FUNDO_BOTAO_MARROM + ";" +
                " -fx-text-fill: " + TEXTO_BOTAO_CLARO + ";" +
                " -fx-border-color: " + COR_TEXTO_MARROM_ESCURO + ";" +
                " -fx-border-width: 1.5px;" +
                " -fx-background-radius: 3px;" +
                " -fx-border-radius: 3px;";
        String estiloHover = "-fx-background-color: " + FUNDO_BOTAO_HOVER_MARROM_ESCURO + ";" +
                " -fx-text-fill: " + TEXTO_BOTAO_CLARO + ";" +
                " -fx-border-color: " + COR_TEXTO_MARROM_ESCURO + ";" +
                " -fx-border-width: 1.5px;" +
                " -fx-background-radius: 3px;" +
                " -fx-border-radius: 3px;";

        botao.setStyle(estiloBase);
        botao.setOnMouseEntered(e -> botao.setStyle(estiloHover));
        botao.setOnMouseExited(e -> botao.setStyle(estiloBase));
        return botao;
    }

    private VBox criarContainerEventosJogo() {
        VBox envolveEventos = new VBox(10);
        envolveEventos.setAlignment(Pos.TOP_LEFT);
        VBox.setVgrow(envolveEventos, Priority.ALWAYS);


        Label rotuloTituloEventos = new Label("Diário de Bordo");
        estilizarRotulo(rotuloTituloEventos, TAMANHO_FONTE_CABECALHO_JOGO, true, Pos.CENTER, true);

        this.areaEventosJogo = new TextArea();
        this.areaEventosJogo.setPromptText("Eventos e ações serão registrados aqui...");
        this.areaEventosJogo.setEditable(false);
        this.areaEventosJogo.setWrapText(true);
        this.areaEventosJogo.setFont(Font.font(FAMILIA_FONTE_MEDIEVAL, FontWeight.NORMAL, TAMANHO_FONTE_ROTULO_JOGO -1));
        this.areaEventosJogo.setStyle("-fx-control-inner-background: #EFE5D2;" +
                " -fx-text-fill: " + COR_TEXTO_MARROM_ESCURO + ";" +
                " -fx-border-color: " + COR_BORDA_MARROM_SUTIL + ";" +
                " -fx-border-width: 1px;");

        VBox.setVgrow(this.areaEventosJogo, Priority.ALWAYS);
        this.areaEventosJogo.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.areaEventosJogo.setPrefHeight(150);

        envolveEventos.getChildren().addAll(rotuloTituloEventos, this.areaEventosJogo);
        return envolveEventos;
    }

    private void logEvento(String mensagem) {
        if (this.areaEventosJogo != null) {
            this.areaEventosJogo.appendText(mensagem + "\n");
        } else {
            System.out.println("Log GUI (areaEventosJogo is null): " + mensagem);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}