package jogo.gui;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.text.Normalizer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
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
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import jogo.utils.InputOutput;
import jogo.construtores.ConstrutorItem;
import jogo.construtores.ConstrutorPersonagem;
import jogo.construtores.itens.ConstrutorArma;
import jogo.construtores.itens.ConstrutorMaterial;
import jogo.enums.ItensEnum;
import jogo.enums.itens.ArmasEnum;
import jogo.enums.itens.FerramentasEnum;
import jogo.enums.itens.MateriaisEnum;
import jogo.enums.personagem.PersonagemAtributosEnum;
import jogo.enums.personagem.PersonagemClassesEnum;
import jogo.enums.CombinacoesEnum;
import jogo.gerenciadores.GerenciadorDeAmbientes;
import jogo.gerenciadores.GerenciadorDeEventos;
import jogo.sistema.Ambiente;
import jogo.sistema.Personagem;
import jogo.sistema.Turno;
import jogo.sistema.eventos.EventoCriatura;
import jogo.sistema.itens.Item;
import jogo.sistema.itens.ItemArma;
import jogo.sistema.itens.ItemFerramenta;
import jogo.sistema.itens.ItemMaterial;
import jogo.sistema.itens.consumiveis.Consumivel;
import jogo.sistema.itens.consumiveis.ConsumivelAlimento;


public class TelaDeEscolha extends Application {

    private static final String FAMILIA_FONTE_MEDIEVAL = "Georgia";
    private static final String COR_TEXTO_MARROM_ESCURO = "#4A3B31";
    private static final String FUNDO_PERGAMINHO = "#F5EACE";
    private static final String FUNDO_SECAO_PAINEL = "#E8DCC6";
    private static final String FUNDO_PAINEL_INTERNO = "#F0E6D1";
    private static final String COR_BORDA_MARROM_SUTIL = "#8B7967";
    private static final String FUNDO_BOTAO_MARROM = "#7A6A5A";
    private static final String TEXTO_BOTAO_CLARO = "#F0E6D1";
    private static final String FUNDO_BOTAO_HOVER_MARROM_ESCURO = "#6A5A4A";
    private static final String COR_TOGGLE_SELECIONADO = "#5A4A3A";
    private static final String FUNDO_BOTAO_CONFIRMAR = "#556B2F";
    private static final String FUNDO_BOTAO_CONFIRMAR_HOVER = "#455A25";

    private static final String COR_TRILHA_BARRA_PROGRESSO = "#C6B8A9";
    private static final String FUNDO_SLOT_INVENTARIO = "#A08C78";
    private static final String FUNDO_PLACEHOLDER_AMBIENTE = "#D7C7B0";
    private static final String COR_VIDA = "#8C1C1C";
    private static final String COR_FOME = "#D2691E";
    private static final String COR_SEDE = "#4682B4";
    private static final String COR_ENERGIA = "#556B2F";

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


    private static final String FONT_PATH_START_SCREEN = "/res/PressStart2P-Regular.ttf";


    private static final int TAMANHO_FONTE_TITULO_INICIO_CUSTOM = 36;
    private static final int TAMANHO_FONTE_BOTAO_INICIO_CUSTOM = 24;


    private static final String BASE_PATH_IMAGENS_AMBIENTE = "/res/imgAmbientes/";


    private ProgressBar barraVida;
    private ProgressBar barraFome;
    private ProgressBar barraSede;
    private ProgressBar barraEnergia;
    private Label rotuloValorSanidade;
    private Label rotuloStatusPersonagem;


    private static class InfoClasseDisplay {
        String nomeDisplay;
        String nomeReal;
        String textoIcone;
        String caminhoImagem;
        String vida;
        String fome;
        String sede;
        String energia;
        String sanidade;
        String itensIniciaisDescricao;
        String habilidadeEspecialDescricao;

        public InfoClasseDisplay(String nomeDisplay, String nomeReal, String textoIcone, String caminhoImagem,
                                 String vida, String fome, String sede, String energia, String sanidade,
                                 String itensIniciaisDescricao, String habilidadeEspecialDescricao) {
            this.nomeDisplay = nomeDisplay;
            this.nomeReal = nomeReal;
            this.textoIcone = textoIcone;
            this.caminhoImagem = caminhoImagem;
            this.vida = vida;
            this.fome = fome;
            this.sede = sede;
            this.energia = energia;
            this.sanidade = sanidade;
            this.itensIniciaisDescricao = itensIniciaisDescricao;
            this.habilidadeEspecialDescricao = habilidadeEspecialDescricao;
        }
    }

    private List<InfoClasseDisplay> classesParaExibicao;
    private InfoClasseDisplay classeExibicaoSelecionada;


    private Label nomeClasseExibidoLabel;
    private StackPane arteClassePainel;
    private Label classeVidaLabel, classeFomeLabel, classeSedeLabel, classeEnergiaLabel, classeSanidadeLabel, classeItensLabel;
    private TextArea classeHabilidadeArea;
    private TextField nomePersonagemCampo;
    private ToggleGroup classeToggleGrupo;

    private Stage palcoPrincipal;

    private Personagem personagem;
    private GerenciadorDeAmbientes gerenciadorDeAmbientes;
    private Ambiente ambienteAtual;
    private GerenciadorDeEventos gerenciadorDeEventos;

    private Button botaoAcaoPrincipal;
    private Button botaoMudarAmbiente;
    private Button botaoDescansar;
    private Button botaoUsarHabilidadeEspecial;


    private String nomePersonagemParaAcoes;
    private TextArea areaEventosJogo;
    private Label rotuloNomeAmbienteAtual;
    private ImageView visualizadorImagemAmbiente;
    private GridPane gradeInventario;


    private int maxVidaPersonagem;
    private int maxFomePersonagem;
    private int maxSedePersonagem;
    private int maxEnergiaPersonagem;
    private int maxSanidadePersonagem;

    private int turnoAtual = 0;
    private boolean jogoFinalizado = false;
    private boolean modoInfinitoAtivo = false;

    private void logMensagemDoJogo(String mensagem) {
        logEvento(mensagem);
    }

    @Override
    public void start(Stage palcoPrincipalArgumento) {
        this.palcoPrincipal = palcoPrincipalArgumento;
        mostrarTelaDeInicio(this.palcoPrincipal);
    }

    private void mostrarTelaDeInicio(Stage stage) {
        stage.setTitle("Ultima Fronteira");

        StackPane rootInicio = new StackPane();


        try {

            InputStream backgroundStream = getClass().getResourceAsStream("/res/ImagemBGInicial.png");
            if (backgroundStream != null) {
                Image backgroundImage = new Image(backgroundStream);
                ImageView backgroundImageView = new ImageView(backgroundImage);
                backgroundImageView.setPreserveRatio(false);
                backgroundImageView.setSmooth(true);


                backgroundImageView.fitWidthProperty().bind(rootInicio.widthProperty());
                backgroundImageView.fitHeightProperty().bind(rootInicio.heightProperty());

                rootInicio.getChildren().add(backgroundImageView);
            } else {
                System.err.println("Error: Could not find background image: /res/PressStart2P-Regular.ttf");
                rootInicio.setStyle("-fx-background-color: #2c3e50;");
            }
        } catch (Exception e) {
            System.err.println("Error loading background image: " + e.getMessage());
            e.printStackTrace();
            rootInicio.setStyle("-fx-background-color: #2c3e50;");
        }


        Font titleCustomFont = null;
        try {
            URL fontUrl = getClass().getResource(FONT_PATH_START_SCREEN);
            if (fontUrl != null) {
                titleCustomFont = Font.loadFont(fontUrl.toExternalForm(), TAMANHO_FONTE_TITULO_INICIO_CUSTOM);
            } else {
                System.err.println("Error: Could not find font resource for title: " + FONT_PATH_START_SCREEN);
            }
        } catch (Exception e) {
            System.err.println("Error loading title font: " + e.getMessage());
        }
        if (titleCustomFont == null) {
            titleCustomFont = Font.font("Monospaced", FontWeight.BOLD, TAMANHO_FONTE_TITULO_INICIO_CUSTOM);
        }

        Text tituloJogo = new Text("ULTIMA FRONTEIRA");
        tituloJogo.setFont(titleCustomFont);
        tituloJogo.setFill(Color.WHITE);


        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);
        dropShadow.setRadius(5);
        dropShadow.setOffsetX(3);
        dropShadow.setOffsetY(3);
        tituloJogo.setEffect(dropShadow);


        StartScreenMenuItem botaoJogar = new StartScreenMenuItem("JOGAR", () -> mostrarTelaDeSelecaoPersonagem(stage));

        VBox layoutVertical = new VBox(60);
        layoutVertical.getChildren().addAll(tituloJogo, botaoJogar);
        layoutVertical.setAlignment(Pos.CENTER);


        rootInicio.getChildren().add(layoutVertical);
        StackPane.setAlignment(layoutVertical, Pos.CENTER);


        Scene cenaInicio = new Scene(rootInicio, LARGURA_JANELA_ESCOLHA, ALTURA_JANELA_ESCOLHA);
        stage.setScene(cenaInicio);
        stage.centerOnScreen();
        stage.show();
    }



    public static class StartScreenMenuItem extends StackPane {
        private Text textNode;
        private Rectangle bgNode;
        private static Font menuItemFont = null;

        static {
            try {

                URL fontUrl = StartScreenMenuItem.class.getResource(FONT_PATH_START_SCREEN);
                if (fontUrl != null) {
                    menuItemFont = Font.loadFont(fontUrl.toExternalForm(), TAMANHO_FONTE_BOTAO_INICIO_CUSTOM);
                } else {
                    System.err.println("Error: Could not find font resource for menu items: " + FONT_PATH_START_SCREEN);
                }
            } catch (Exception e) {
                System.err.println("Error loading menu item font: " + e.getMessage());
            }
            if (menuItemFont == null) {
                menuItemFont = Font.font("Monospaced", FontWeight.BOLD, TAMANHO_FONTE_BOTAO_INICIO_CUSTOM);
            }
        }

        public StartScreenMenuItem(String name, Runnable action) {
            bgNode = new Rectangle(250, 60);
            bgNode.setOpacity(0.7);
            bgNode.setFill(Color.BLACK);
            bgNode.setArcWidth(15);
            bgNode.setArcHeight(15);

            textNode = new Text(name);
            textNode.setFont(menuItemFont);
            textNode.setFill(Color.WHITE);

            setAlignment(Pos.CENTER);
            getChildren().addAll(bgNode, textNode);

            setOnMouseEntered(event -> {
                bgNode.setFill(Color.WHITE);
                textNode.setFill(Color.BLACK);
                setCursor(Cursor.HAND);
            });

            setOnMouseExited(event -> {
                bgNode.setFill(Color.BLACK);
                textNode.setFill(Color.WHITE);
                setCursor(Cursor.DEFAULT);
            });

            setOnMousePressed(event -> bgNode.setFill(Color.DARKGRAY));

            setOnMouseReleased(event -> {

                bgNode.setFill(Color.BLACK);
                textNode.setFill(Color.WHITE);
                action.run();
            });
        }
    }


    private void mostrarTelaDeSelecaoPersonagem(Stage stage) {
        stage.setTitle("Seleção de Personagem - Ultima Fronteira");
        inicializarClassesParaExibicao();

        BorderPane painelRaizEscolha = new BorderPane();
        painelRaizEscolha.setPadding(new Insets(VALOR_PADDING_ESCOLHA));
        painelRaizEscolha.setStyle("-fx-background-color: " + FUNDO_PERGAMINHO + ";");

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

        if (classesParaExibicao != null && !classesParaExibicao.isEmpty()) {

            if (classeToggleGrupo != null && classeToggleGrupo.getToggles() != null && !classeToggleGrupo.getToggles().isEmpty()) {
                ToggleButton primeiroBotao = (ToggleButton) classeToggleGrupo.getToggles().get(0);
                if (primeiroBotao != null) {
                    primeiroBotao.setSelected(true);
                    if (primeiroBotao.getUserData() instanceof InfoClasseDisplay) {
                        selecionarClasseParaExibicao((InfoClasseDisplay) primeiroBotao.getUserData());
                    }
                }
            }
        }

        Scene cenaEscolha = new Scene(painelRaizEscolha, LARGURA_JANELA_ESCOLHA, ALTURA_JANELA_ESCOLHA);
        stage.setScene(cenaEscolha);
        stage.centerOnScreen();
    }


    private String slugify(String text) {
        if (text == null) return "";
        String nfdNormalizedString = Normalizer.normalize(text, Normalizer.Form.NFD);
        return nfdNormalizedString.replaceAll("[^\\p{ASCII}]", "").replaceAll("[^a-zA-Z0-9_]", "");
    }


    private void inicializarClassesParaExibicao() {
        classesParaExibicao = new ArrayList<>();
        String basePathImagens = "/res/imgPersonagens/";

        for (PersonagemClassesEnum classeEnum : PersonagemClassesEnum.values()) {
            String nomeDisplay = capitalizeString(classeEnum.name().replace("_", " "));
            String nomeReal = classeEnum.name();
            String textoIcone = nomeDisplay.length() > 0 ? nomeDisplay.substring(0, 1) : "?";
            String nomeImagemCorrigido = capitalizePrimeLetterOnly(classeEnum.name()) + ".png";
            String caminhoImagem = basePathImagens + nomeImagemCorrigido;


            String itensIniciaisStr = "Nenhum";
            if (classeEnum.getItensIniciais() != null && classeEnum.getItensIniciais().length > 0) {
                itensIniciaisStr = java.util.Arrays.stream(classeEnum.getItensIniciais())
                        .map(item -> item.getNome() + (item.getQuantidade() > 1 ? " (x" + item.getQuantidade() + ")" : ""))
                        .collect(Collectors.joining(", "));
            }

            classesParaExibicao.add(new InfoClasseDisplay(
                    nomeDisplay,
                    nomeReal,
                    textoIcone,
                    caminhoImagem,
                    String.valueOf(classeEnum.getMaxVida()),
                    String.valueOf(classeEnum.getMaxFome()),
                    String.valueOf(classeEnum.getMaxSede()),
                    String.valueOf(classeEnum.getMaxEnergia()),
                    String.valueOf(classeEnum.getMaxSanidade()),
                    itensIniciaisStr,
                    classeEnum.getHabiliadeEspecial()
            ));
        }
    }

    private String capitalizePrimeLetterOnly(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }


    private String capitalizeString(String str) {
        if (str == null || str.trim().isEmpty()) {
            return str == null ? "" : str;
        }
        StringBuilder capitalizedString = new StringBuilder();
        for (String word : str.trim().toLowerCase().split("\\s+")) {
            if (word.length() > 0) {
                capitalizedString.append(Character.toUpperCase(word.charAt(0)));
                if (word.length() > 1) {
                    capitalizedString.append(word.substring(1));
                }
                capitalizedString.append(" ");
            }
        }
        return capitalizedString.toString().trim();
    }

    private String obterEstiloPainelPrincipal() {
        return "-fx-background-color: " + FUNDO_SECAO_PAINEL + ";" +
                " -fx-border-color: " + COR_BORDA_MARROM_SUTIL + ";" +
                " -fx-border-width: 2px;" +
                " -fx-padding: " + (VALOR_PADDING_ESCOLHA / 1.5) + "px;" +
                " -fx-border-radius: 3px;" +
                " -fx-background-radius: 3px;";
    }

    private String obterEstiloPainelInterno() {
        return "-fx-background-color: " + FUNDO_PAINEL_INTERNO + ";" +
                " -fx-border-color: " + COR_BORDA_MARROM_SUTIL + ";" +
                " -fx-border-width: 1px;" +
                " -fx-padding: " + (VALOR_PADDING_ESCOLHA / 2) + "px;" +
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
        nomePersonagemCampo.setStyle("-fx-text-fill: " + COR_TEXTO_MARROM_ESCURO + ";" +
                " -fx-background-color: " + FUNDO_PAINEL_INTERNO + ";" +
                " -fx-border-color: " + COR_BORDA_MARROM_SUTIL + ";");
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
                mostrarAlerta("Entrada Inválida", "O nome do aventureiro não pode estar vazio.");
                return;
            }
            if (classeExibicaoSelecionada == null) {
                logEventoTelaEscolha("Por favor, selecione uma vocação.");
                mostrarAlerta("Seleção Necessária", "Por favor, selecione uma vocação para o seu aventureiro.");
                return;
            }

            this.nomePersonagemParaAcoes = nome.trim();
            PersonagemClassesEnum classeEscolhida = PersonagemClassesEnum.valueOf(classeExibicaoSelecionada.nomeReal);
            this.personagem = ConstrutorPersonagem.construirPersonagem(nomePersonagemParaAcoes, classeEscolhida);

            this.maxVidaPersonagem = classeEscolhida.getMaxVida();
            this.maxFomePersonagem = classeEscolhida.getMaxFome();
            this.maxSedePersonagem = classeEscolhida.getMaxSede();
            this.maxEnergiaPersonagem = classeEscolhida.getMaxEnergia();
            this.maxSanidadePersonagem = classeEscolhida.getMaxSanidade();

            System.out.println("Aventureiro Criado: " + this.personagem.getNome() + " (" + classeExibicaoSelecionada.nomeDisplay + ")");
            configurarTelaPrincipalJogo();
        });
        VBox.setMargin(botaoCriarPersonagem, new Insets(20, 0, 0, 0));

        painel.getChildren().addAll(rotuloNomePersonagem, nomePersonagemCampo, rotuloEscolhaClasse, gradeBotoesClasse, nomeClasseExibidoLabel, botaoCriarPersonagem);
        return painel;
    }

    private void logEventoTelaEscolha(String mensagem) {
        System.out.println("GUI Info (Tela Escolha): " + mensagem);
    }

    private VBox criarPainelDireitoSelecao() {
        VBox painel = new VBox(VALOR_PADDING_ESCOLHA);
        painel.setAlignment(Pos.TOP_CENTER);

        arteClassePainel = new StackPane();
        arteClassePainel.setPrefSize(LARGURA_ARTE_CLASSE, ALTURA_ARTE_CLASSE);
        arteClassePainel.setMinSize(LARGURA_ARTE_CLASSE, ALTURA_ARTE_CLASSE);
        arteClassePainel.setStyle(obterEstiloPainelInterno() + " -fx-alignment: center;");


        VBox painelAtributos = new VBox(5);
        painelAtributos.setStyle(obterEstiloPainelInterno());
        Label tituloAtributos = new Label("Atributos Base:");
        estilizarRotulo(tituloAtributos, TAMANHO_FONTE_CORPO_ESCOLHA, true, Pos.CENTER_LEFT, false);
        classeVidaLabel = criarRotuloAtributoSelecao("Vida: ");
        classeFomeLabel = criarRotuloAtributoSelecao("Fome: ");
        classeSedeLabel = criarRotuloAtributoSelecao("Sede: ");
        classeEnergiaLabel = criarRotuloAtributoSelecao("Energia: ");
        classeSanidadeLabel = criarRotuloAtributoSelecao("Sanidade: ");
        classeItensLabel = criarRotuloAtributoSelecao("Equipamento Inicial: ");
        classeItensLabel.setWrapText(true);
        painelAtributos.getChildren().addAll(tituloAtributos, classeVidaLabel, classeFomeLabel, classeSedeLabel, classeEnergiaLabel, classeSanidadeLabel, classeItensLabel);

        VBox painelHabilidade = new VBox(5);
        painelHabilidade.setStyle(obterEstiloPainelInterno());
        Label tituloHabilidade = new Label("Habilidade Especial:");
        estilizarRotulo(tituloHabilidade, TAMANHO_FONTE_CORPO_ESCOLHA, true, Pos.CENTER_LEFT, false);
        classeHabilidadeArea = new TextArea();
        classeHabilidadeArea.setWrapText(true);
        classeHabilidadeArea.setEditable(false);
        classeHabilidadeArea.setFont(Font.font(FAMILIA_FONTE_MEDIEVAL, TAMANHO_FONTE_PEQUENA_ESCOLHA));
        classeHabilidadeArea.setStyle("-fx-control-inner-background: " + FUNDO_PAINEL_INTERNO + ";" +
                "-fx-text-fill: " + COR_TEXTO_MARROM_ESCURO + ";" +
                "-fx-border-color: " + COR_BORDA_MARROM_SUTIL + ";");
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
                    if (imagem.isError()) {
                        System.err.println("Erro ao carregar imagem: " + classeExibicaoSelecionada.caminhoImagem +
                                (imagem.getException() != null ? " - " + imagem.getException().getMessage() : " (Causa desconhecida)"));
                        Label arteTextoAlternativo = new Label("Erro na Imagem\n" + classeExibicaoSelecionada.nomeDisplay);
                        configurarTextoAlternativoImagem(arteTextoAlternativo);
                        arteClassePainel.getChildren().add(arteTextoAlternativo);
                    } else {
                        ImageView visualizadorImagem = new ImageView(imagem);
                        visualizadorImagem.setFitWidth(LARGURA_ARTE_CLASSE - 10);
                        visualizadorImagem.setFitHeight(ALTURA_ARTE_CLASSE - 10);
                        visualizadorImagem.setPreserveRatio(true);
                        visualizadorImagem.setSmooth(true);
                        arteClassePainel.getChildren().add(visualizadorImagem);
                    }
                } else {
                    System.err.println("Recurso de imagem não encontrado (stream nulo): " + classeExibicaoSelecionada.caminhoImagem);
                    Label arteTextoAlternativo = new Label("Imagem não encontrada\n" + classeExibicaoSelecionada.nomeDisplay);
                    configurarTextoAlternativoImagem(arteTextoAlternativo);
                    arteClassePainel.getChildren().add(arteTextoAlternativo);
                }
            } else {
                System.err.println("Caminho da imagem nulo ou vazio para: " + classeExibicaoSelecionada.nomeDisplay);
                Label arteTextoAlternativo = new Label("Arte Indisponível\n" + classeExibicaoSelecionada.nomeDisplay);
                configurarTextoAlternativoImagem(arteTextoAlternativo);
                arteClassePainel.getChildren().add(arteTextoAlternativo);
            }
        } catch (Exception e) {
            System.err.println("Falha EXCEPCIONAL ao carregar arte da classe '" + classeExibicaoSelecionada.nomeDisplay + "' de '" + classeExibicaoSelecionada.caminhoImagem +"': " + e.getMessage());
            e.printStackTrace(System.err);
            Label arteTextoAlternativo = new Label("Erro ao carregar Arte\n" + classeExibicaoSelecionada.nomeDisplay);
            configurarTextoAlternativoImagem(arteTextoAlternativo);
            arteClassePainel.getChildren().add(arteTextoAlternativo);
        }

        classeVidaLabel.setText("Vida: " + classeExibicaoSelecionada.vida);
        classeFomeLabel.setText("Fome: " + classeExibicaoSelecionada.fome);
        classeSedeLabel.setText("Sede: " + classeExibicaoSelecionada.sede);
        classeEnergiaLabel.setText("Energia: " + classeExibicaoSelecionada.energia);
        classeSanidadeLabel.setText("Sanidade: " + classeExibicaoSelecionada.sanidade);
        classeItensLabel.setText("Equipamento Inicial: " + classeExibicaoSelecionada.itensIniciaisDescricao);
        classeHabilidadeArea.setText(classeExibicaoSelecionada.habilidadeEspecialDescricao);
    }

    private void configurarTextoAlternativoImagem(Label label) {
        label.setFont(Font.font(FAMILIA_FONTE_MEDIEVAL, FontWeight.BOLD, 18));
        label.setTextFill(Color.web(COR_TEXTO_MARROM_ESCURO));
        label.setTextAlignment(TextAlignment.CENTER);
        label.setWrapText(true);
        StackPane.setAlignment(label, Pos.CENTER);
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
        InputOutput.setGlobalLogger(this::logMensagemDoJogo);

        this.palcoPrincipal.setTitle("Ultima Fronteira - O Jogo");
        this.jogoFinalizado = false;
        this.turnoAtual = 0;
        this.modoInfinitoAtivo = false;


        this.gerenciadorDeAmbientes = new GerenciadorDeAmbientes();
        this.ambienteAtual = this.gerenciadorDeAmbientes.sortearAmbiente();
        if (this.ambienteAtual == null) {
            mostrarAlerta("Erro Crítico", "Não foi possível carregar um ambiente inicial. O jogo não pode continuar.");
            Platform.exit();
            return;
        }
        this.gerenciadorDeEventos = new GerenciadorDeEventos(
                ambienteAtual,
                personagem
        );

        BorderPane painelRaizJogo = new BorderPane();
        painelRaizJogo.setPadding(new Insets(VALOR_PADDING_JOGO));
        painelRaizJogo.setStyle("-fx-background-color: " + FUNDO_PERGAMINHO + ";");


        VBox secaoAtributosJogo = criarSecaoAtributosJogo();
        VBox secaoAmbienteJogo = criarSecaoAmbienteJogo();
        VBox secaoInventarioJogo = criarSecaoInventarioJogo();
        VBox secaoAcoesJogo = criarSecaoAcoesJogo();
        VBox containerEventosJogo = criarContainerEventosJogo();

        String estiloSecaoJogo = "-fx-background-color: " + FUNDO_SECAO_PAINEL + ";" +
                " -fx-border-color: " + COR_BORDA_MARROM_SUTIL + ";" +
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
        logEvento("Você é um " + capitalizeString(personagem.getClasse()) + ".");
        if (this.ambienteAtual != null) {
            logEvento("Você se encontra em: " + this.ambienteAtual.getNome());
        } else {
            logEvento("Você se encontra em um local desconhecido (erro ao carregar ambiente).");
        }


        atualizarRotuloAmbiente();
        atualizarAtributosGUI();
        atualizarExibicaoInventario();
        aplicarEfeitosDeFimDeTurno(false);
    }

    private void atualizarRotuloAmbiente() {
        String nomeAmbienteParaExibir = "Desconhecido";
        String nomeArquivoImagem = null;

        if (this.ambienteAtual != null) {
            nomeAmbienteParaExibir = this.ambienteAtual.getNome();

            String nomeBase = capitalizePrimeLetterOnly(this.ambienteAtual.getNome().replaceAll("/", "_"));
            nomeArquivoImagem = BASE_PATH_IMAGENS_AMBIENTE + nomeBase + ".png";
        }

        if (this.rotuloNomeAmbienteAtual != null) {
            this.rotuloNomeAmbienteAtual.setText(" Ambiente Atual: " + nomeAmbienteParaExibir);
        }

        if (this.visualizadorImagemAmbiente != null) {
            if (nomeArquivoImagem != null) {
                try {
                    InputStream streamImagem = getClass().getResourceAsStream(nomeArquivoImagem);
                    if (streamImagem != null) {
                        Image imagemAmbiente = new Image(streamImagem);
                        if (imagemAmbiente.isError()) {
                            System.err.println("Erro ao carregar imagem do ambiente: " + nomeArquivoImagem +
                                    (imagemAmbiente.getException() != null ? " - " + imagemAmbiente.getException().getMessage() : " (Causa desconhecida)"));
                            this.visualizadorImagemAmbiente.setImage(null);
                        } else {
                            this.visualizadorImagemAmbiente.setImage(imagemAmbiente);
                        }
                    } else {
                        System.err.println("Recurso de imagem do ambiente não encontrado (stream nulo): " + nomeArquivoImagem);
                        this.visualizadorImagemAmbiente.setImage(null);
                    }
                } catch (Exception e) {
                    System.err.println("Falha EXCEPCIONAL ao carregar imagem do ambiente '" + nomeAmbienteParaExibir + "' de '" + nomeArquivoImagem + "': " + e.getMessage());
                    e.printStackTrace(System.err);
                    this.visualizadorImagemAmbiente.setImage(null);
                }
            } else {
                this.visualizadorImagemAmbiente.setImage(null);
            }
        }
    }


    private void atualizarAtributosGUI() {
        if (personagem == null) {
            return;
        }
        if (barraVida == null || barraFome == null || barraSede == null || barraEnergia == null ||
                rotuloValorSanidade == null || rotuloStatusPersonagem == null) {
            return;
        }

        barraVida.setProgress(maxVidaPersonagem > 0 ? (double)personagem.getAtributo(PersonagemAtributosEnum.VIDA) / maxVidaPersonagem : 0);
        barraFome.setProgress(maxFomePersonagem > 0 ? (double)personagem.getAtributo(PersonagemAtributosEnum.FOME) / maxFomePersonagem : 0);
        barraSede.setProgress(maxSedePersonagem > 0 ? (double)personagem.getAtributo(PersonagemAtributosEnum.SEDE) / maxSedePersonagem : 0);
        barraEnergia.setProgress(maxEnergiaPersonagem > 0 ? (double)personagem.getAtributo(PersonagemAtributosEnum.ENERGIA) / maxEnergiaPersonagem : 0);

        rotuloValorSanidade.setText(String.valueOf(personagem.getAtributo(PersonagemAtributosEnum.SANIDADE)) + "/" + maxSanidadePersonagem);
        rotuloStatusPersonagem.setText(obterStatusPersonagem(personagem));
    }


    private VBox criarSecaoAtributosJogo() {
        VBox caixaAtributos = new VBox(10);
        caixaAtributos.setAlignment(Pos.TOP_LEFT);
        VBox.setVgrow(caixaAtributos, Priority.NEVER);

        Label rotuloTituloAtributos = new Label("Atributos de " + (this.nomePersonagemParaAcoes != null ? this.nomePersonagemParaAcoes : "Aventureiro"));
        estilizarRotulo(rotuloTituloAtributos, TAMANHO_FONTE_CABECALHO_JOGO, true, Pos.CENTER_LEFT, true);

        double vidaProg = (personagem != null && maxVidaPersonagem > 0) ? (double)personagem.getAtributo(PersonagemAtributosEnum.VIDA) / maxVidaPersonagem : 1.0;
        double fomeProg = (personagem != null && maxFomePersonagem > 0) ? (double)personagem.getAtributo(PersonagemAtributosEnum.FOME) / maxFomePersonagem : 1.0;
        double sedeProg = (personagem != null && maxSedePersonagem > 0) ? (double)personagem.getAtributo(PersonagemAtributosEnum.SEDE) / maxSedePersonagem : 1.0;
        double energiaProg = (personagem != null && maxEnergiaPersonagem > 0) ? (double)personagem.getAtributo(PersonagemAtributosEnum.ENERGIA) / maxEnergiaPersonagem : 1.0;
        String sanidadeText = (personagem != null) ? personagem.getAtributo(PersonagemAtributosEnum.SANIDADE) + "/" + maxSanidadePersonagem : "100/100";
        String statusText = (personagem != null) ? obterStatusPersonagem(personagem) : "Normal";


        this.barraVida = new ProgressBar(vidaProg);
        this.barraFome = new ProgressBar(fomeProg);
        this.barraSede = new ProgressBar(sedeProg);
        this.barraEnergia = new ProgressBar(energiaProg);

        this.rotuloValorSanidade = new Label(sanidadeText);
        estilizarRotulo(this.rotuloValorSanidade, TAMANHO_FONTE_ROTULO_JOGO, true, Pos.CENTER_LEFT, true);
        this.rotuloStatusPersonagem = new Label(statusText);
        estilizarRotulo(this.rotuloStatusPersonagem, TAMANHO_FONTE_ROTULO_JOGO, true, Pos.CENTER_LEFT, true);

        configurarBarraProgressoJogo(this.barraVida, COR_VIDA);
        configurarBarraProgressoJogo(this.barraFome, COR_FOME);
        configurarBarraProgressoJogo(this.barraSede, COR_SEDE);
        configurarBarraProgressoJogo(this.barraEnergia, COR_ENERGIA);

        caixaAtributos.getChildren().addAll(
                rotuloTituloAtributos,
                criarLinhaAtributoJogo("Vida:", this.barraVida),
                criarLinhaAtributoJogo("Fome:", this.barraFome),
                criarLinhaAtributoJogo("Sede:", this.barraSede),
                criarLinhaAtributoJogo("Energia:", this.barraEnergia),
                criarLinhaAtributoJogo("Sanidade:", this.rotuloValorSanidade),
                criarLinhaAtributoJogo("Status:", this.rotuloStatusPersonagem)
        );
        return caixaAtributos;
    }

    private String obterStatusPersonagem(Personagem p) {
        if (p == null) return "<status>";
        if (p.getAtributo(PersonagemAtributosEnum.VIDA) <= 0) return "Morto";
        if (maxVidaPersonagem > 0 && p.getAtributo(PersonagemAtributosEnum.VIDA) < maxVidaPersonagem * 0.25) return "Em Perigo";
        if ((maxFomePersonagem > 0 && p.getAtributo(PersonagemAtributosEnum.FOME) < maxFomePersonagem * 0.1) ||
                (maxSedePersonagem > 0 && p.getAtributo(PersonagemAtributosEnum.SEDE) < maxSedePersonagem * 0.1)) return "Esgotado";
        if (maxSanidadePersonagem > 0 && p.getAtributo(PersonagemAtributosEnum.SANIDADE) < maxSanidadePersonagem * 0.25) return "Instável";
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

        this.visualizadorImagemAmbiente.fitWidthProperty().bind(painelExibicaoAmbiente.widthProperty());
        this.visualizadorImagemAmbiente.fitHeightProperty().bind(painelExibicaoAmbiente.heightProperty());
        this.visualizadorImagemAmbiente.setPreserveRatio(true);
        this.visualizadorImagemAmbiente.setSmooth(true);

        Label textoAlternativo = new Label("\"Visão do Ambiente\"");
        textoAlternativo.setFont(Font.font(FAMILIA_FONTE_MEDIEVAL, FontWeight.NORMAL, 18));
        textoAlternativo.setTextFill(Color.web(COR_TEXTO_MARROM_ESCURO));


        this.visualizadorImagemAmbiente.imageProperty().addListener((obs, oldImg, newImg) -> {
            boolean hasText = painelExibicaoAmbiente.getChildren().contains(textoAlternativo);
            boolean hasImage = painelExibicaoAmbiente.getChildren().contains(this.visualizadorImagemAmbiente);

            if (newImg == null) {
                if (hasImage) painelExibicaoAmbiente.getChildren().remove(this.visualizadorImagemAmbiente);
                if (!hasText) painelExibicaoAmbiente.getChildren().add(textoAlternativo);
            } else {
                if (hasText) painelExibicaoAmbiente.getChildren().remove(textoAlternativo);
                if (!hasImage) painelExibicaoAmbiente.getChildren().add(this.visualizadorImagemAmbiente);
            }
        });


        if (this.visualizadorImagemAmbiente.getImage() == null) {
            painelExibicaoAmbiente.getChildren().add(textoAlternativo);
        } else {
            painelExibicaoAmbiente.getChildren().add(this.visualizadorImagemAmbiente);
        }

        containerAmbiente.getChildren().addAll(this.rotuloNomeAmbienteAtual, painelExibicaoAmbiente);
        return containerAmbiente;
    }


    private VBox criarSecaoInventarioJogo() {
        VBox containerInventario = new VBox(10);
        containerInventario.setAlignment(Pos.TOP_CENTER);
        VBox.setVgrow(containerInventario, Priority.ALWAYS);

        Label rotuloTituloInventario = new Label("Inventário");
        estilizarRotulo(rotuloTituloInventario, TAMANHO_FONTE_CABECALHO_JOGO, true, Pos.CENTER, true);

        this.gradeInventario = new GridPane();
        this.gradeInventario.setHgap(8);
        this.gradeInventario.setVgap(8);
        this.gradeInventario.setAlignment(Pos.TOP_CENTER);
        VBox.setVgrow(this.gradeInventario, Priority.ALWAYS);

        this.gradeInventario.getColumnConstraints().clear();
        for (int i = 0; i < COLS_INVENTARIO_JOGO; i++) {
            ColumnConstraints restricaoColuna = new ColumnConstraints();
            restricaoColuna.setHgrow(Priority.SOMETIMES);
            restricaoColuna.setPercentWidth(100.0 / COLS_INVENTARIO_JOGO);
            this.gradeInventario.getColumnConstraints().add(restricaoColuna);
        }

        containerInventario.getChildren().addAll(rotuloTituloInventario, this.gradeInventario);
        return containerInventario;
    }

    private void atualizarExibicaoInventario() {
        if (this.gradeInventario == null) {
            return;
        }
        if (this.personagem == null || this.personagem.getInventario() == null) {
            this.gradeInventario.getChildren().clear();
            return;
        }

        List<Item> itensAtuais = this.personagem.getInventario().getItens();

        this.gradeInventario.getChildren().clear();

        int capacidadeInventario = this.personagem.getInventario().getCapacidadeMaxima();
        int numLinhas = (int) Math.ceil((double) capacidadeInventario / COLS_INVENTARIO_JOGO);

        if (this.gradeInventario.getRowConstraints().size() != numLinhas) {
            this.gradeInventario.getRowConstraints().clear();
            for (int i = 0; i < numLinhas; i++) {
                RowConstraints restricaoLinha = new RowConstraints();
                restricaoLinha.setVgrow(Priority.SOMETIMES);
                restricaoLinha.setMinHeight(50);
                restricaoLinha.setPrefHeight(60);
                this.gradeInventario.getRowConstraints().add(restricaoLinha);
            }
        }
        if (this.gradeInventario.getColumnConstraints().isEmpty()){
            for (int i = 0; i < COLS_INVENTARIO_JOGO; i++) {
                ColumnConstraints restricaoColuna = new ColumnConstraints();
                restricaoColuna.setPercentWidth(100.0 / COLS_INVENTARIO_JOGO);
                this.gradeInventario.getColumnConstraints().add(restricaoColuna);
            }
        }

        for (int slotIndex = 0; slotIndex < capacidadeInventario; slotIndex++) {
            int linhaIdx = slotIndex / COLS_INVENTARIO_JOGO;
            int colIdx = slotIndex % COLS_INVENTARIO_JOGO;

            StackPane celula = new StackPane();
            celula.setAlignment(Pos.CENTER);
            GridPane.setHgrow(celula, Priority.ALWAYS);
            GridPane.setVgrow(celula, Priority.ALWAYS);

            Rectangle espacoItem = new Rectangle();
            espacoItem.setFill(Color.web(FUNDO_SLOT_INVENTARIO));
            espacoItem.setStroke(Color.web(COR_TEXTO_MARROM_ESCURO));
            espacoItem.setArcWidth(5);
            espacoItem.setArcHeight(5);

            espacoItem.widthProperty().bind(celula.widthProperty().subtract(4));
            espacoItem.heightProperty().bind(celula.heightProperty().subtract(4));

            celula.getChildren().add(espacoItem);

            if (slotIndex < itensAtuais.size()) {
                Item item = itensAtuais.get(slotIndex);
                String itemText = item.getNome();
                itemText += " (x" + item.getQuantidade() + ")";

                Label itemLabel = new Label(itemText);
                itemLabel.setFont(Font.font(FAMILIA_FONTE_MEDIEVAL, FontWeight.NORMAL, TAMANHO_FONTE_PEQUENA_ESCOLHA - 2));
                itemLabel.setTextFill(Color.web(TEXTO_BOTAO_CLARO));
                itemLabel.setWrapText(true);
                itemLabel.setTextAlignment(TextAlignment.CENTER);
                itemLabel.setPadding(new Insets(3));
                celula.getChildren().add(itemLabel);
            }
            this.gradeInventario.add(celula, colIdx, linhaIdx);
        }
    }

    private VBox criarSecaoAcoesJogo() {
        VBox caixaAcoes = new VBox(10);
        caixaAcoes.setAlignment(Pos.TOP_LEFT);
        VBox.setVgrow(caixaAcoes, Priority.NEVER);

        Label rotuloPerguntaAcao = new Label(" O que " + (this.nomePersonagemParaAcoes != null ? this.nomePersonagemParaAcoes : "Aventureiro") + " fará?");
        estilizarRotulo(rotuloPerguntaAcao, TAMANHO_FONTE_CABECALHO_JOGO, true, Pos.CENTER_LEFT, true);
        rotuloPerguntaAcao.setWrapText(true);

        GridPane gradeAcoes = new GridPane();
        gradeAcoes.setHgap(10);
        gradeAcoes.setVgap(10);

        this.botaoMudarAmbiente = criarBotaoMedievalJogo("Mudar Ambiente");
        this.botaoDescansar = criarBotaoMedievalJogo("Descansar");
        Button botaoGerenciarInventario = criarBotaoMedievalJogo("Inventário");
        this.botaoAcaoPrincipal = criarBotaoMedievalJogo("Explorar");
        this.botaoUsarHabilidadeEspecial = criarBotaoMedievalJogo("Habilidade");


        this.botaoMudarAmbiente.setOnAction(e -> {
            if (this.jogoFinalizado) return;
            if (this.gerenciadorDeEventos != null && this.gerenciadorDeEventos.buscarEventoCriaturaAtivo() != null) {
                logEvento(this.personagem.getNome() + " está em combate e não pode mudar de ambiente!");
                return;
            }
            int custoEnergiaMudarAmbiente = (this.ambienteAtual != null ? (5 * this.ambienteAtual.getDificuldadeDeExploracao()) : 15);
            if (this.personagem == null || this.personagem.getAtributo(PersonagemAtributosEnum.ENERGIA) < custoEnergiaMudarAmbiente) {
                logEvento( (this.personagem != null ? this.personagem.getNome() : "Personagem") + " está cansado demais para viajar! (Requer " + custoEnergiaMudarAmbiente + " energia)");
                return;
            }

            personagem.mudarAtributo(PersonagemAtributosEnum.ENERGIA, -custoEnergiaMudarAmbiente);
            personagem.mudarAtributo(PersonagemAtributosEnum.SEDE , -10);
            personagem.mudarAtributo(PersonagemAtributosEnum.FOME, -25);

            InputOutput io = new InputOutput();
            io.print(this.nomePersonagemParaAcoes + " viaja para um novo local...");
            this.ambienteAtual = this.gerenciadorDeAmbientes.sortearAmbiente();
            if (this.ambienteAtual == null) {
                logEvento("ERRO CRÍTICO: Novo ambiente não pôde ser carregado!");
                finalizarJogo("Erro ao mudar de ambiente.");
                return;
            }
            this.gerenciadorDeEventos.setAmbiente(ambienteAtual);
            io.print("Chegou em: " + this.ambienteAtual.getNome());

            atualizarRotuloAmbiente();
            aplicarEfeitosDeFimDeTurno(true);
        });

        this.botaoDescansar.setOnAction(e -> {
            if (this.jogoFinalizado) return;
            if (this.gerenciadorDeEventos != null && this.gerenciadorDeEventos.buscarEventoCriaturaAtivo() != null) {
                logEvento(this.personagem.getNome() + " não pode descansar durante um combate! Use 'Tentar Fugir'.");
                return;
            }
            logEvento(this.nomePersonagemParaAcoes + " decide descansar.");
            if (this.personagem != null) {
                personagem.mudarAtributo(PersonagemAtributosEnum.ENERGIA, +15);
            }
            aplicarEfeitosDeFimDeTurno(true);
        });

        botaoGerenciarInventario.setOnAction(e -> {
            if (this.jogoFinalizado) return;
            logEvento(this.nomePersonagemParaAcoes + " acessa o inventário.");
            abrirDialogoGerenciamentoInventario();
        });


        this.botaoUsarHabilidadeEspecial.setOnAction(e -> {
            if (this.jogoFinalizado) return;
            if (this.gerenciadorDeEventos.buscarEventoCriaturaAtivo() != null) {
                logEvento(personagem.getNome() + " não pode usar habilidade em combate!");
                return;
            }
            if (personagem == null || personagem.getHabilidadeEspecialCooldown() > 0) {
                logEvento((personagem != null ? personagem.getNome() : "Personagem") + " não pode usar a habilidade especial ainda! Cooldown: " + (personagem != null ? personagem.getHabilidadeEspecialCooldown() : "N/A") + " turnos.");
                return;
            }
            personagem.usarHabilidadeEspecial();
            aplicarEfeitosDeFimDeTurno(true);
        });

        definirRestricoesGradeBotoesJogo(this.botaoMudarAmbiente, this.botaoDescansar, botaoGerenciarInventario, this.botaoAcaoPrincipal, this.botaoUsarHabilidadeEspecial);

        gradeAcoes.add(this.botaoMudarAmbiente, 0, 0);
        gradeAcoes.add(this.botaoDescansar, 1, 0);
        gradeAcoes.add(botaoGerenciarInventario, 0, 1);
        gradeAcoes.add(this.botaoAcaoPrincipal, 1, 1);
        gradeAcoes.add(this.botaoUsarHabilidadeEspecial, 0, 2);
        GridPane.setColumnSpan(this.botaoUsarHabilidadeEspecial, 2);

        ColumnConstraints ccAcao = new ColumnConstraints();
        ccAcao.setPercentWidth(50);
        ccAcao.setHgrow(Priority.ALWAYS);
        gradeAcoes.getColumnConstraints().addAll(ccAcao, ccAcao);

        caixaAcoes.getChildren().addAll(rotuloPerguntaAcao, gradeAcoes);
        return caixaAcoes;
    }

    private void atualizarInterfaceAcoes() {
        if (this.jogoFinalizado) {
            botaoAcaoPrincipal.setDisable(true);
            botaoMudarAmbiente.setDisable(true);
            botaoDescansar.setDisable(true);
            botaoUsarHabilidadeEspecial.setDisable(true);
            return;
        }

        if (this.botaoAcaoPrincipal == null || this.gerenciadorDeEventos == null || this.personagem == null) {
            return;
        }

        botaoAcaoPrincipal.setDisable(false);
        botaoMudarAmbiente.setDisable(false);
        botaoDescansar.setDisable(false);

        EventoCriatura criaturaAtiva = this.gerenciadorDeEventos.buscarEventoCriaturaAtivo();
        boolean emCombate = (criaturaAtiva != null);

        if (emCombate) {
            String nomeCriatura = criaturaAtiva.getNome();
            this.botaoAcaoPrincipal.setText("Batalhar " + nomeCriatura);
            this.botaoAcaoPrincipal.setOnAction(e -> {
                if (this.jogoFinalizado) return;
                abrirDialogoSelecaoArma(criaturaAtiva);
            });

            this.botaoMudarAmbiente.setDisable(true);
            this.botaoDescansar.setText("Tentar Fugir");
            this.botaoDescansar.setOnAction(e -> {
                if (this.jogoFinalizado) return;
                this.gerenciadorDeEventos.fugirDeEventoCriatura(criaturaAtiva);
                aplicarEfeitosDeFimDeTurno(true);
            });
            this.botaoUsarHabilidadeEspecial.setDisable(true);
        } else {
            this.botaoAcaoPrincipal.setText("Explorar");
            this.botaoAcaoPrincipal.setOnAction(e -> {
                try {
                    if (this.jogoFinalizado) return;
                    if (this.personagem == null) { logEvento("ERRO: Personagem não definido."); return; }
                    if (this.ambienteAtual == null) { logEvento("ERRO: Ambiente atual não definido."); this.botaoAcaoPrincipal.setDisable(true); return; }
                    if (this.gerenciadorDeEventos == null) { logEvento("ERRO: Gerenciador de eventos não definido."); return; }

                    int custoEnergiaExplorar = this.ambienteAtual.getDificuldadeDeExploracao();
                    if (this.personagem.getAtributo(PersonagemAtributosEnum.ENERGIA) < custoEnergiaExplorar) {
                        logEvento(this.personagem.getNome() + " está cansado demais para explorar! (Requer " + custoEnergiaExplorar + " energia)");
                        return;
                    }
                    this.personagem.mudarAtributo(PersonagemAtributosEnum.ENERGIA, -custoEnergiaExplorar);
                    logEvento(this.nomePersonagemParaAcoes + " explora " + this.ambienteAtual.getNome() + "...");
                    this.gerenciadorDeEventos.adicionarEventoAleatorio();
                    aplicarEfeitosDeFimDeTurno(true);
                } catch (Exception ex) {
                    logEvento("Ocorreu um erro inesperado durante a ação de explorar: " + ex.getClass().getSimpleName());
                    System.err.println("--- DETALHES DO ERRO EM 'Explorar OnAction' ---");
                    ex.printStackTrace(System.err);
                }
            });

            boolean desabilitarExplorar = (this.ambienteAtual == null || (this.personagem != null && this.personagem.getAtributo(PersonagemAtributosEnum.ENERGIA) < this.ambienteAtual.getDificuldadeDeExploracao()) || this.personagem == null);
            this.botaoAcaoPrincipal.setDisable(desabilitarExplorar);

            int custoEnergiaMudarAmbiente = (this.ambienteAtual != null ? (5 * this.ambienteAtual.getDificuldadeDeExploracao()) : 15);
            this.botaoMudarAmbiente.setDisable(this.personagem == null || this.personagem.getAtributo(PersonagemAtributosEnum.ENERGIA) < custoEnergiaMudarAmbiente );

            this.botaoDescansar.setText("Descansar");
            this.botaoDescansar.setOnAction(ev -> {
                if (this.jogoFinalizado) return;
                if (this.gerenciadorDeEventos != null && this.gerenciadorDeEventos.buscarEventoCriaturaAtivo() != null) {
                    logEvento(this.personagem.getNome() + " não pode descansar durante um combate!");
                    return;
                }
                logEvento(this.nomePersonagemParaAcoes + " decide descansar.");
                if (this.personagem != null) {
                    personagem.mudarAtributo(PersonagemAtributosEnum.ENERGIA, +15);
                }
                aplicarEfeitosDeFimDeTurno(true);
            });

            this.botaoUsarHabilidadeEspecial.setDisable(this.personagem == null || this.personagem.getHabilidadeEspecialCooldown() > 0);
        }
    }

    private void aplicarEfeitosDeFimDeTurno(boolean acaoDoJogador) {
        if (jogoFinalizado && acaoDoJogador && turnoAtual > 0) {
            atualizarAtributosGUI();
            atualizarExibicaoInventario();
            atualizarInterfaceAcoes();
            return;
        }

        if (acaoDoJogador || turnoAtual == 0) {
            turnoAtual++;
        }
        logEvento("--- Processando Turno " + turnoAtual + " ---");

        if (this.gerenciadorDeEventos != null) {
            this.gerenciadorDeEventos.executarEventos();
        }

        if (personagem != null && !jogoFinalizado) {
            if (acaoDoJogador) {
                personagem.mudarAtributo(PersonagemAtributosEnum.FOME, -5);
                personagem.mudarAtributo(PersonagemAtributosEnum.SEDE, -2);
            }

            if (personagem.getHabilidadeEspecialCooldown() > 0) {
                personagem.setHabilidadeEspecialCooldown(personagem.getHabilidadeEspecialCooldown() - 1);
                if (personagem.getHabilidadeEspecialCooldown() == 0) {
                    logEvento("Habilidade especial está pronta para ser usada novamente!");
                }
            }
            if (personagem.getAtributo(PersonagemAtributosEnum.FOME) <= 0) {
                logEvento(personagem.getNome() + " está faminto e perde saúde!");
                personagem.mudarAtributo(PersonagemAtributosEnum.VIDA, -5);
            }
            if (personagem.getAtributo(PersonagemAtributosEnum.SEDE) <= 0) {
                logEvento(personagem.getNome() + " está desidratado e perde saúde!");
                personagem.mudarAtributo(PersonagemAtributosEnum.VIDA, -2);
            }
        }

        if(personagem != null) atualizarAtributosGUI();

        if (personagem != null && !jogoFinalizado) {
            if (personagem.getAtributo(PersonagemAtributosEnum.VIDA) <= 0) {
                finalizarJogo("Fim de Jogo: " + personagem.getNome() + " não sobreviveu."); return;
            }

            if (!this.modoInfinitoAtivo && turnoAtual >= Turno.QUANTIDADE_DE_TURNOS_PARA_VITORIA) {
                finalizarJogoComOpcaoDeContinuar("Vitória por Sobrevivência!", personagem.getNome() + " sobreviveu " + Turno.QUANTIDADE_DE_TURNOS_PARA_VITORIA + " turnos!");
                if(jogoFinalizado) return;
            }

            Item jangadaParaVerificar = ConstrutorItem.construir(FerramentasEnum.JANGADA, 1);
            if (personagem.getInventario().encontrarItem(jangadaParaVerificar) != -1) {
                finalizarJogoComOpcaoDeContinuar("Vitória por Fuga!", personagem.getNome() + " construiu uma Jangada e escapou!");
                personagem.getInventario().removerItem(jangadaParaVerificar, 1);
                if(jogoFinalizado && !modoInfinitoAtivo) return;
            }

            while (personagem.getInventario().estaCheio()) {
                int diff = personagem.getInventario().getQuantidadeItens() - personagem.getInventario().getCapacidadeMaxima();
                String itemOuItens = (diff == 1 ? " item" : " itens");
                logEvento("!!! INVENTÁRIO CHEIO !!!");
                logEvento(personagem.getNome() + " precisa descartar " + diff + itemOuItens + " para continuar.");

                mostrarAlerta("Inventário Lotado – Ação Necessária",
                        "Seu inventário excedeu a capacidade em " + diff + itemOuItens + ".\n" +
                                "Você DEVE descartar itens para prosseguir com o jogo.");

                abrirDialogoDescartarItem();

                atualizarAtributosGUI();
                atualizarExibicaoInventario();


                if (!personagem.getInventario().estaCheio()) {
                    logEvento("Inventário não está mais superlotado. O turno pode ser finalizado.");
                } else {
                    logEvento("Inventário AINDA está superlotado. É necessário descartar mais.");
                }
            }
        }

        atualizarExibicaoInventario();
        atualizarAtributosGUI();
        atualizarInterfaceAcoes();
        if (!jogoFinalizado) {
            logEvento("--- Aguardando sua próxima ação ---");
        }
    }

    private void finalizarJogo(String mensagem) {
        this.jogoFinalizado = true;
        logEvento(mensagem);
        mostrarAlerta("Jogo Encerrado", mensagem);
        atualizarInterfaceAcoes();
    }

    private void finalizarJogoComOpcaoDeContinuar(String tituloVitoria, String mensagemVitoria) {
        if (this.modoInfinitoAtivo) {
            logEvento(mensagemVitoria + " - O jogo continua no modo infinito.");
            this.jogoFinalizado = false;
            atualizarInterfaceAcoes();
            return;
        }

        this.jogoFinalizado = true;
        logEvento(mensagemVitoria);

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle(tituloVitoria);
        dialog.setHeaderText(mensagemVitoria);
        dialog.setContentText("O que deseja fazer?");

        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.setStyle(obterEstiloPainelInterno() + " -fx-font-family: '" + FAMILIA_FONTE_MEDIEVAL + "';");

        ButtonType finalizarButton = new ButtonType("Finalizar o Jogo", ButtonBar.ButtonData.OK_DONE);
        ButtonType continuarButton = new ButtonType("Continuar no Modo Infinito", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(finalizarButton, continuarButton);

        ((Button)dialogPane.lookupButton(finalizarButton)).setFont(Font.font(FAMILIA_FONTE_MEDIEVAL, FontWeight.BOLD, TAMANHO_FONTE_CORPO_ESCOLHA));
        ((Button)dialogPane.lookupButton(continuarButton)).setFont(Font.font(FAMILIA_FONTE_MEDIEVAL, TAMANHO_FONTE_CORPO_ESCOLHA));

        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get() == continuarButton) {
            this.jogoFinalizado = false;
            this.modoInfinitoAtivo = true;
            logEvento("Modo infinito ativado! O jogo continua...");
        } else {
            logEvento("Jogo finalizado pelo jogador após vitória.");
        }
        atualizarInterfaceAcoes();
    }

    private void mostrarAlerta(String titulo, String conteudo) {
        Dialog<Void> alerta = new Dialog<>();
        alerta.initModality(Modality.APPLICATION_MODAL);
        alerta.setTitle(titulo);
        DialogPane dialogPane = alerta.getDialogPane();
        dialogPane.setStyle(obterEstiloPainelInterno() + " -fx-font-family: '" + FAMILIA_FONTE_MEDIEVAL + "';");
        dialogPane.setHeaderText(conteudo);
        dialogPane.getButtonTypes().add(ButtonType.OK);
        ((Button)dialogPane.lookupButton(ButtonType.OK)).setFont(Font.font(FAMILIA_FONTE_MEDIEVAL, TAMANHO_FONTE_CORPO_ESCOLHA));
        alerta.showAndWait();
    }


    private void abrirDialogoSelecaoArma(EventoCriatura criaturaAlvo) {
        if (personagem == null || personagem.getInventario() == null) {
            logEvento("Erro: Personagem ou inventário não disponível.");
            aplicarEfeitosDeFimDeTurno(true);
            return;
        }

        ArrayList<Item> itensArmaDoInventario = personagem.getInventario().getItens(ItensEnum.ARMA.getIndice());
        ObservableList<ItemArma> armasObservaveis = FXCollections.observableArrayList();
        for (Item item : itensArmaDoInventario) {
            if (item instanceof ItemArma) {
                armasObservaveis.add((ItemArma) item);
            }
        }

        ItemArma punhos = ConstrutorArma.construirArma(ArmasEnum.PUNHOS, 1);
        boolean temApenasPunhosOuNenhumaArma = armasObservaveis.isEmpty() || (armasObservaveis.size() == 1 && armasObservaveis.get(0).getNome().equals(punhos.getNome()));

        if (temApenasPunhosOuNenhumaArma) {
            int indicePunhosNaListaDeArmas = -1;
            ArrayList<Item> listaRealDeArmas = personagem.getInventario().getItens(ItensEnum.ARMA.getIndice());
            for(int i=0; i < listaRealDeArmas.size(); i++) {
                if(listaRealDeArmas.get(i).getNome().equals(ArmasEnum.PUNHOS.getNome())) {
                    indicePunhosNaListaDeArmas = i;
                    break;
                }
            }

            if (indicePunhosNaListaDeArmas != -1) {
                personagem.getGerenciadorDeInventario().usarItemArma(indicePunhosNaListaDeArmas, criaturaAlvo);
            } else {
                logEvento("ERRO: 'Punhos' não encontrados na lista de armas do inventário para ataque automático.");
            }
            aplicarEfeitosDeFimDeTurno(true);
            return;
        }

        Dialog<ItemArma> dialog = new Dialog<>();
        dialog.setTitle("Escolha sua Arma");
        dialog.setHeaderText("Atacar " + criaturaAlvo.getNome() + ". Qual arma usar?");

        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.setMinWidth(380);
        dialogPane.setMinHeight(250);
        dialogPane.setStyle(obterEstiloPainelInterno() + " -fx-font-family: '" + FAMILIA_FONTE_MEDIEVAL + "';");

        ListView<ItemArma> listViewArmas = new ListView<>(armasObservaveis);
        listViewArmas.setCellFactory(lv -> new ListCell<ItemArma>() {
            @Override
            protected void updateItem(ItemArma arma, boolean empty) {
                super.updateItem(arma, empty);
                if (empty || arma == null) {
                    setText(null);
                } else {
                    setText(arma.getNome() + " (Dano: " + arma.getDano() + ", Dur: " + arma.getDurabilidade() + ")");
                    setFont(Font.font(FAMILIA_FONTE_MEDIEVAL, TAMANHO_FONTE_CORPO_ESCOLHA));
                }
            }
        });
        listViewArmas.setPrefHeight(180);

        VBox contentVBox = new VBox(10, listViewArmas);
        contentVBox.setPadding(new Insets(10));
        dialogPane.setContent(contentVBox);

        ButtonType atacarButtonType = new ButtonType("Atacar", ButtonBar.ButtonData.OK_DONE);
        dialogPane.getButtonTypes().addAll(atacarButtonType, ButtonType.CANCEL);

        Node atacarButtonNode = dialogPane.lookupButton(atacarButtonType);
        atacarButtonNode.setDisable(true);
        ((Button)atacarButtonNode).setFont(Font.font(FAMILIA_FONTE_MEDIEVAL, FontWeight.BOLD, TAMANHO_FONTE_CORPO_ESCOLHA));
        ((Button)dialogPane.lookupButton(ButtonType.CANCEL)).setFont(Font.font(FAMILIA_FONTE_MEDIEVAL, TAMANHO_FONTE_CORPO_ESCOLHA));

        listViewArmas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            atacarButtonNode.setDisable(newSelection == null);
        });

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == atacarButtonType) {
                return listViewArmas.getSelectionModel().getSelectedItem();
            }
            return null;
        });

        Optional<ItemArma> resultado = dialog.showAndWait();

        if (resultado.isPresent()) {
            ItemArma armaEscolhida = resultado.get();
            int indiceArmaNaListaDeArmas = -1;
            ArrayList<Item> listaDeArmasDoInventario = personagem.getInventario().getItens(ItensEnum.ARMA.getIndice());
            for(int i = 0; i < listaDeArmasDoInventario.size(); i++) {
                if(listaDeArmasDoInventario.get(i) == armaEscolhida) {
                    indiceArmaNaListaDeArmas = i;
                    break;
                }
            }

            if(indiceArmaNaListaDeArmas != -1) {
                personagem.getGerenciadorDeInventario().usarItemArma(indiceArmaNaListaDeArmas, criaturaAlvo);
            } else {
                logEvento("ERRO: Arma selecionada ("+ armaEscolhida.getNome() +") não encontrada na lista específica de armas do inventário para ataque.");
            }
        } else {
            logEvento("Seleção de arma cancelada.");
        }
        aplicarEfeitosDeFimDeTurno(true);
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
        String estiloDesabilitado = "-fx-background-color: #9E9E9E;" +
                " -fx-text-fill: #E0E0E0;" +
                " -fx-border-color: #757575;" +
                " -fx-border-width: 1.5px;" +
                " -fx-background-radius: 3px;" +
                " -fx-border-radius: 3px;" +
                " -fx-opacity: 0.7;";

        botao.setStyle(estiloBase);
        botao.setOnMouseEntered(ev -> { if(!botao.isDisabled()) botao.setStyle(estiloHover);});
        botao.setOnMouseExited(ev -> { if(!botao.isDisabled()) botao.setStyle(estiloBase);});
        botao.disabledProperty().addListener((obs, eraDesabilitado, estaDesabilitado) -> {
            if (estaDesabilitado) {
                botao.setStyle(estiloDesabilitado);
            } else {
                botao.setStyle(estiloBase);
            }
        });
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
        if (mensagem == null) {
            mensagem = "[Log: mensagem nula recebida]";
        }
        final String finalMensagem = mensagem;
        if (Platform.isFxApplicationThread()) {
            atualizarLogUI(finalMensagem);
        } else {
            Platform.runLater(() -> atualizarLogUI(finalMensagem));
        }
    }

    private void atualizarLogUI(String mensagem) {
        if (this.areaEventosJogo != null) {
            String timestamp = "[T" + turnoAtual + "] ";
            String cleanMensagem = mensagem.startsWith("\n") ? mensagem.substring(1) : mensagem;

            this.areaEventosJogo.appendText(timestamp + cleanMensagem + "\n");
            this.areaEventosJogo.setScrollTop(Double.MAX_VALUE);
        } else {
            System.out.println("Diario de Bordo (GUI Log Issue) [T" + turnoAtual + "]: " + mensagem);
        }

        if (this.areaEventosJogo != null && this.areaEventosJogo.getParent() instanceof VBox) {
            VBox parentVBox = (VBox) this.areaEventosJogo.getParent();
            if (!parentVBox.getChildren().isEmpty() && parentVBox.getChildren().get(0) instanceof Label) {
                Label titleLabel = (Label) parentVBox.getChildren().get(0);
                titleLabel.setText("Diário de Bordo (Turno " + turnoAtual + ")");
            }
        }
    }


    private void abrirDialogoGerenciamentoInventario() {
        if (personagem == null) {
            logEvento("Personagem não disponível para gerenciar inventário.");
            return;
        }

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Gerenciar Inventário");
        dialog.setHeaderText("O que " + personagem.getNome() + " irá fazer?");
        dialog.getDialogPane().setStyle(obterEstiloPainelInterno());
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        if (stage != null) {
            stage.getIcons().clear();
        }


        ButtonType usarConsumivelButtonType = new ButtonType("Usar Consumível");
        ButtonType descartarItensButtonType = new ButtonType("Descartar Itens");
        ButtonType combinarMateriaisButtonType = new ButtonType("Combinar Materiais");
        ButtonType sairButtonType = new ButtonType("Sair", ButtonBar.ButtonData.CANCEL_CLOSE);

        dialog.getDialogPane().getButtonTypes().addAll(usarConsumivelButtonType, descartarItensButtonType, combinarMateriaisButtonType, sairButtonType);

        for(ButtonType bt : dialog.getDialogPane().getButtonTypes()){
            Button b = (Button) dialog.getDialogPane().lookupButton(bt);
            if (b != null) {
                b.setFont(Font.font(FAMILIA_FONTE_MEDIEVAL, FontWeight.BOLD, TAMANHO_FONTE_CORPO_ESCOLHA));
            }
        }

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == usarConsumivelButtonType) return "USAR";
            if (dialogButton == descartarItensButtonType) return "DESCARTAR";
            if (dialogButton == combinarMateriaisButtonType) return "COMBINAR";
            return null;
        });

        Optional<String> resultado = dialog.showAndWait();
        resultado.ifPresent(acao -> {
            switch (acao) {
                case "USAR":
                    abrirDialogoUsarConsumivel();
                    break;
                case "DESCARTAR":
                    abrirDialogoDescartarItem();
                    break;
                case "COMBINAR":
                    abrirDialogoCombinarMateriais_RecipeBook();
                    break;
            }
        });
    }

    private void abrirDialogoUsarConsumivel() {
        ArrayList<Item> consumiveisDoInventario = personagem.getInventario().getItens(ItensEnum.CONSUMIVEL.getIndice());
        ObservableList<Consumivel> consumiveisObservaveis = FXCollections.observableArrayList();
        for (Item item : consumiveisDoInventario) {
            if (item instanceof Consumivel) {
                consumiveisObservaveis.add((Consumivel) item);
            }
        }

        if (consumiveisObservaveis.isEmpty()) {
            mostrarAlerta("Usar Consumível", "Nenhum item consumível no inventário.");
            logEvento("Nenhum consumível para usar.");
            return;
        }

        Dialog<Consumivel> dialog = new Dialog<>();
        dialog.setTitle("Usar Consumível");
        dialog.setHeaderText("Escolha um item para consumir:");
        dialog.getDialogPane().setStyle(obterEstiloPainelInterno());
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        if (stage != null) stage.getIcons().clear();


        ListView<Consumivel> listView = new ListView<>(consumiveisObservaveis);
        listView.setCellFactory(lv -> new ListCell<Consumivel>() {
            @Override
            protected void updateItem(Consumivel item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNome() + " (x" + item.getQuantidade() + ")");
                if (item != null) setFont(Font.font(FAMILIA_FONTE_MEDIEVAL, TAMANHO_FONTE_PEQUENA_ESCOLHA));
            }
        });

        dialog.getDialogPane().setContent(listView);
        ButtonType usarButtonType = new ButtonType("Usar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(usarButtonType, ButtonType.CANCEL);

        Node usarButtonNode = dialog.getDialogPane().lookupButton(usarButtonType);
        usarButtonNode.setDisable(true);
        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> usarButtonNode.setDisable(newVal == null));

        for(ButtonType bt : dialog.getDialogPane().getButtonTypes()){
            Button b = (Button) dialog.getDialogPane().lookupButton(bt);
            if (b != null) b.setFont(Font.font(FAMILIA_FONTE_MEDIEVAL, FontWeight.BOLD, TAMANHO_FONTE_CORPO_ESCOLHA));
        }

        dialog.setResultConverter(dialogButton -> dialogButton == usarButtonType ? listView.getSelectionModel().getSelectedItem() : null);

        Optional<Consumivel> resultado = dialog.showAndWait();
        resultado.ifPresent(consumivelSelecionado -> {
            int indiceConsumivelNaSubLista = consumiveisObservaveis.indexOf(consumivelSelecionado);


            if (indiceConsumivelNaSubLista != -1) {
                personagem.getGerenciadorDeInventario().usarItemConsumivel(indiceConsumivelNaSubLista);
                atualizarAtributosGUI();
                atualizarExibicaoInventario();
            } else {
                logEvento("Erro ao tentar usar " + consumivelSelecionado.getNome() + ": consumível não encontrado na lista de seleção.");
            }
        });
    }

    private void abrirDialogoDescartarItem() {
        List<Item> todosOsItens = new ArrayList<>(personagem.getInventario().getItens());
        if (todosOsItens.isEmpty()) {
            mostrarAlerta("Descartar Item", "Inventário vazio. Nada para descartar.");
            logEvento("Inventário vazio, nada para descartar.");
            return;
        }

        ObservableList<Item> itensObservaveis = FXCollections.observableArrayList(todosOsItens);

        Dialog<Item> dialog = new Dialog<>();
        dialog.setTitle("Descartar Item");
        dialog.setHeaderText("Escolha um item para descartar:");
        dialog.getDialogPane().setStyle(obterEstiloPainelInterno());
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        if (stage != null) stage.getIcons().clear();


        ListView<Item> listView = new ListView<>(itensObservaveis);
        listView.setCellFactory(lv -> new ListCell<Item>() {
            @Override
            protected void updateItem(Item item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNome() + " (x" + item.getQuantidade() + ")");
                if (item != null) setFont(Font.font(FAMILIA_FONTE_MEDIEVAL, TAMANHO_FONTE_PEQUENA_ESCOLHA));
            }
        });
        dialog.getDialogPane().setContent(listView);

        ButtonType descartarButtonType = new ButtonType("Descartar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(descartarButtonType, ButtonType.CANCEL);

        Node descartarButtonNode = dialog.getDialogPane().lookupButton(descartarButtonType);
        descartarButtonNode.setDisable(true);
        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> descartarButtonNode.setDisable(newVal == null));

        for(ButtonType bt : dialog.getDialogPane().getButtonTypes()){
            Button b = (Button) dialog.getDialogPane().lookupButton(bt);
            if (b != null) b.setFont(Font.font(FAMILIA_FONTE_MEDIEVAL, FontWeight.BOLD, TAMANHO_FONTE_CORPO_ESCOLHA));
        }

        dialog.setResultConverter(dialogButton -> dialogButton == descartarButtonType ? listView.getSelectionModel().getSelectedItem() : null);

        Optional<Item> resultadoItem = dialog.showAndWait();
        resultadoItem.ifPresent(itemSelecionado -> {
            TextInputDialog qtdDialog = new TextInputDialog("1");
            qtdDialog.setTitle("Quantidade para Descartar");
            qtdDialog.setHeaderText("Descartar " + itemSelecionado.getNome());
            qtdDialog.setContentText("Quantidade (máx " + itemSelecionado.getQuantidade() + "):");
            Stage qtdStage = (Stage) qtdDialog.getDialogPane().getScene().getWindow();
            if(qtdStage != null) qtdStage.getIcons().clear();
            ((Button) qtdDialog.getDialogPane().lookupButton(ButtonType.OK)).setFont(Font.font(FAMILIA_FONTE_MEDIEVAL, FontWeight.BOLD, TAMANHO_FONTE_CORPO_ESCOLHA));
            ((Button) qtdDialog.getDialogPane().lookupButton(ButtonType.CANCEL)).setFont(Font.font(FAMILIA_FONTE_MEDIEVAL, FontWeight.BOLD, TAMANHO_FONTE_CORPO_ESCOLHA));


            Optional<String> resultadoQtd = qtdDialog.showAndWait();
            resultadoQtd.ifPresent(s -> {
                try {
                    int quantidade = Integer.parseInt(s);
                    if (quantidade > 0 && quantidade <= itemSelecionado.getQuantidade()) {
                        boolean removido = personagem.getInventario().removerItem(itemSelecionado, quantidade);
                        if (removido) {
                            logEvento(personagem.getNome() + " descartou " + quantidade + " de " + itemSelecionado.getNome() + ".");
                            atualizarExibicaoInventario();
                        } else {
                            logEvento("Falha ao descartar " + itemSelecionado.getNome() + " (item não encontrado ou quantidade insuficiente após seleção).");
                        }
                    } else {
                        mostrarAlerta("Quantidade Inválida", "Por favor, insira um número válido entre 1 e " + itemSelecionado.getQuantidade() + ".");
                        logEvento("Tentativa de descarte com quantidade inválida.");
                    }
                } catch (NumberFormatException ex) {
                    mostrarAlerta("Entrada Inválida", "Por favor, insira um número para a quantidade.");
                    logEvento("Tentativa de descarte com entrada não numérica.");
                }
            });
        });
    }

    private void abrirDialogoCombinarMateriais_RecipeBook() {
        if (personagem == null || personagem.getInventario() == null) {
            logEvento("Não é possível combinar materiais: personagem ou inventário não disponível.");
            return;
        }

        ObservableList<CombinacoesEnum> receitasDisponiveis = FXCollections.observableArrayList(CombinacoesEnum.values());

        if (receitasDisponiveis.isEmpty()) {
            mostrarAlerta("Combinar Materiais", "Nenhuma receita de combinação definida no jogo.");
            logEvento("Nenhuma receita de combinação disponível.");
            return;
        }

        Dialog<CombinacoesEnum> dialog = new Dialog<>();
        dialog.setTitle("Combinar Materiais - Livro de Receitas");
        dialog.setHeaderText("Escolha uma receita para criar:");
        dialog.getDialogPane().setStyle(obterEstiloPainelInterno());
        dialog.getDialogPane().setPrefWidth(550);
        dialog.getDialogPane().setPrefHeight(400);
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        if (stage != null) stage.getIcons().clear();

        ListView<CombinacoesEnum> listViewReceitas = new ListView<>(receitasDisponiveis);
        listViewReceitas.setCellFactory(lv -> new ListCell<CombinacoesEnum>() {
            @Override
            protected void updateItem(CombinacoesEnum receita, boolean empty) {
                super.updateItem(receita, empty);
                if (empty || receita == null) {
                    setText(null);
                    setStyle("");
                } else {
                    StringBuilder sb = new StringBuilder();
                    String nomeItemResultante = "Item Desconhecido";
                    Enum<?> itemResultanteEnum = receita.getItemResultanteEnum();

                    if (itemResultanteEnum instanceof ArmasEnum) nomeItemResultante = ((ArmasEnum)itemResultanteEnum).getNome();
                    else if (itemResultanteEnum instanceof FerramentasEnum) nomeItemResultante = ((FerramentasEnum)itemResultanteEnum).getNOME();
                    else if (itemResultanteEnum instanceof MateriaisEnum) nomeItemResultante = ((MateriaisEnum)itemResultanteEnum).getNome();
                    else nomeItemResultante = capitalizeString(itemResultanteEnum.name().toLowerCase().replace("_", " "));


                    sb.append("Criar: ").append(capitalizeString(nomeItemResultante.toLowerCase()));
                    sb.append(" (x").append(receita.getQuantidade()).append(")\n");
                    sb.append("   Requer: ");
                    for (int i = 0; i < receita.getMateriaisCombinados().length; i++) {
                        sb.append(receita.getQuantidades()[i]).append(" ")
                                .append(capitalizeString(receita.getMateriaisCombinados()[i].getNome().toLowerCase()));
                        if (i < receita.getMateriaisCombinados().length - 1) {
                            sb.append(", ");
                        }
                    }
                    setText(sb.toString());
                    setFont(Font.font(FAMILIA_FONTE_MEDIEVAL, TAMANHO_FONTE_PEQUENA_ESCOLHA));

                    boolean podeCriar = true;
                    if (personagem != null && personagem.getInventario() != null) {
                        for (int i = 0; i < receita.getMateriaisCombinados().length; i++) {
                            MateriaisEnum matEnum = receita.getMateriaisCombinados()[i];
                            int qtdNecessaria = receita.getQuantidades()[i];
                            if (personagem.getInventario().getQuantidadeTotalDeMaterial(matEnum) < qtdNecessaria) {
                                podeCriar = false;
                                break;
                            }
                        }
                    } else {
                        podeCriar = false;
                    }

                    if (!podeCriar) {
                        setTextFill(Color.DARKRED);
                    } else {
                        setTextFill(Color.web(COR_TEXTO_MARROM_ESCURO));
                    }
                }
            }
        });

        dialog.getDialogPane().setContent(listViewReceitas);

        ButtonType criarButtonType = new ButtonType("Criar Selecionado", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(criarButtonType, ButtonType.CANCEL);

        Node criarButtonNode = dialog.getDialogPane().lookupButton(criarButtonType);
        criarButtonNode.setDisable(true);

        listViewReceitas.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null) {
                criarButtonNode.setDisable(true);
            } else {
                boolean podeCriar = true;
                if (personagem != null && personagem.getInventario() != null) {
                    for (int i = 0; i < newVal.getMateriaisCombinados().length; i++) {
                        MateriaisEnum matEnum = newVal.getMateriaisCombinados()[i];
                        int qtdNecessaria = newVal.getQuantidades()[i];
                        if (personagem.getInventario().getQuantidadeTotalDeMaterial(matEnum) < qtdNecessaria) {
                            podeCriar = false;
                            break;
                        }
                    }
                } else {
                    podeCriar = false;
                }
                criarButtonNode.setDisable(!podeCriar);
            }
        });

        for(ButtonType bt : dialog.getDialogPane().getButtonTypes()){
            Button b = (Button) dialog.getDialogPane().lookupButton(bt);
            if (b != null) b.setFont(Font.font(FAMILIA_FONTE_MEDIEVAL, FontWeight.BOLD, TAMANHO_FONTE_CORPO_ESCOLHA));
        }

        dialog.setResultConverter(dialogButton -> dialogButton == criarButtonType ? listViewReceitas.getSelectionModel().getSelectedItem() : null);

        Optional<CombinacoesEnum> resultadoReceita = dialog.showAndWait();
        resultadoReceita.ifPresent(receitaSelecionada -> {
            ItemMaterial[] materiaisParaConsumir = new ItemMaterial[receitaSelecionada.getMateriaisCombinados().length];
            for (int i = 0; i < receitaSelecionada.getMateriaisCombinados().length; i++) {
                materiaisParaConsumir[i] = ConstrutorMaterial.construirMaterial(
                        receitaSelecionada.getMateriaisCombinados()[i],
                        receitaSelecionada.getQuantidades()[i]
                );
            }
            personagem.getGerenciadorDeInventario().combinarMateriais(materiaisParaConsumir);

            atualizarExibicaoInventario();
            atualizarAtributosGUI();
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}