package jogo.iu.grafico;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import jogo.logica.JogoObservavel;

public class PainelJogador extends VBox {
    private JogoObservavel jogoObservavel;
    private int jogador;

    private Text textNome;
    private Text textCreditos;
    private Button buttonMiniJogo;
    private Button buttonPecaEspecial;

    public PainelJogador(JogoObservavel jogoObservavel, int jogador) {
        this.jogoObservavel = jogoObservavel;
        this.jogador = jogador;

        textNome = new Text("Nome");
        textCreditos = new Text("Créditos");
        buttonMiniJogo = new Button("Mini - Jogo");
        buttonPecaEspecial = new Button("Peça Especial");

        buttonMiniJogo.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        buttonPecaEspecial.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        setVgrow(buttonMiniJogo, Priority.ALWAYS);
        setVgrow(buttonPecaEspecial, Priority.ALWAYS);

        setPadding(new Insets(5));
        setSpacing(5);

        getChildren().addAll(textNome, textCreditos, buttonMiniJogo, buttonPecaEspecial);

        registaListeners();
        registaObservers();
    }

    private void registaListeners() {
        buttonMiniJogo.setOnAction(event -> {
            jogoObservavel.jogaMinijogo();
        });

        buttonPecaEspecial.setOnAction(event -> {
            jogoObservavel.pecaEspecial();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Selecione uma coluna para apagar");
            alert.showAndWait();
        });
    }

    private void registaObservers() {
        jogoObservavel.addPropertyChangeListener(Propriedades.PROPRIEDADE_JOGAR, evt -> {
            textNome.setText(jogoObservavel.getPlayerName(jogador));
            textCreditos.setText("Créditos: " + jogoObservavel.getPlayerCredits(jogador));

            buttonPecaEspecial.setDisable(!jogoObservavel.getPlayerIsPlaying(jogador) ||
                    !jogoObservavel.getPlayerHasPecaEspecial(jogador));

            buttonMiniJogo.setDisable(!jogoObservavel.getPlayerIsPlaying(jogador) ||
                    !jogoObservavel.getPlayerCanPlayMinigame(jogador) ||
                    jogoObservavel.getPlayerHasPecaEspecial(jogador));

            if (jogoObservavel.getPlayerCor(jogador) == 'R')
                this.setStyle("-fx-background-color: rgba(100, 0, 0, 0.5);");
            else
                this.setStyle("-fx-background-color: rgba(100, 100, 0, 0.5);");
        });
        jogoObservavel.addPropertyChangeListener(Propriedades.PROPRIEDADE_MINIJOGO, evt -> {
            buttonMiniJogo.setDisable(!jogoObservavel.getPlayerIsPlaying(jogador) ||
                    !jogoObservavel.getPlayerCanPlayMinigame(jogador) ||
                    jogoObservavel.getPlayerHasPecaEspecial(jogador));
        });
    }
}
