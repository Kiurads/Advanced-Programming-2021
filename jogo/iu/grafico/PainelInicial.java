package jogo.iu.grafico;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jogo.logica.JogoObservavel;
import jogo.logica.dados.Modos;
import jogo.logica.estados.Situacao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Optional;

public class PainelInicial extends VBox implements Propriedades {
    private JogoObservavel jogoObservavel;

    ImageView imagem4emlinha;
    Text titulo;
    Button buttonInicio;
    Button buttonContinua;
    Button buttonHistorico;
    Button buttonSair;

    public PainelInicial(JogoObservavel jogoObservavel) throws FileNotFoundException {
        this.jogoObservavel = jogoObservavel;

        setStyle("-fx-border-color: black");

        imagem4emlinha = new ImageView(
                new Image(new FileInputStream("src/jogo/iu/grafico/imagens/imagem4emlinha.png")));

        imagem4emlinha.setFitHeight(150);
        imagem4emlinha.setFitWidth(150);

        titulo = new Text("4 em Linha");

        titulo.setFont(Font.font((25)));

        buttonInicio = new Button("Iniciar Jogo");
        buttonContinua = new Button("Continuar Jogo");
        buttonHistorico = new Button("Mostrar Histórico");
        buttonSair = new Button("Sair");

        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        this.getChildren().addAll(imagem4emlinha, titulo, buttonInicio, buttonContinua, buttonHistorico, buttonSair);

        registaListeners();
        registaObservadores();
    }

    private void registaListeners() {
        buttonInicio.setOnAction(event -> jogoObservavel.iniciaJogo());

        buttonContinua.setOnAction(event -> jogoObservavel.menuInicial());

        buttonSair.setOnAction(event -> {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        });
    }

    private void registaObservadores() {
        this.jogoObservavel.addPropertyChangeListener(PROPRIEDADE_MODE_SELECTION, evt -> {
            atualizaBotoes();

            String [] escolhas = {Modos.JVJ, Modos.JVM, Modos.MVM};

            Optional<String> resultado = new PopUpModo(Arrays.asList(escolhas)).showAndWait();

            if (resultado.isPresent()) {
                jogoObservavel.iniciaRegisto(resultado.get());
            } else {
                jogoObservavel.menuInicial();
            }
        });

        this.jogoObservavel.addPropertyChangeListener(PROPRIEDADE_MENU_SELECTION, evt -> atualizaBotoes());

        this.jogoObservavel.addPropertyChangeListener(PROPRIEDADE_REGISTAR_NOMES, evt -> {
            atualizaBotoes();

            RegistarNomesJanela registarNomesJanela = new RegistarNomesJanela();

            registarNomesJanela.show();

            if (registarNomesJanela.isRegistado()) {
                jogoObservavel.jogar(registarNomesJanela.getJogador1(), registarNomesJanela.getJogador2());
            } else {
                jogoObservavel.menuInicial();
            }
        });
    }

    private void atualizaBotoes() {
        buttonInicio.setDisable(jogoObservavel.getSituacaoAtual() != Situacao.MENU_SELECTION);
        buttonContinua.setDisable(jogoObservavel.getSituacaoAtual() != Situacao.MENU_SELECTION);
        buttonHistorico.setDisable(jogoObservavel.getSituacaoAtual() != Situacao.MENU_SELECTION);
        buttonSair.setDisable(jogoObservavel.getSituacaoAtual() != Situacao.MENU_SELECTION);
    }
}
