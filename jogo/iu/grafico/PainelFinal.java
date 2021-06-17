package jogo.iu.grafico;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jogo.logica.JogoObservavel;
import jogo.logica.estados.Situacao;

public class PainelFinal extends VBox {
    private JogoObservavel jogoObservavel;

    private Text textFinal;
    private HBox hBoxButtons;
    private Button buttonSair;
    private Button buttonMenu;

    public PainelFinal(JogoObservavel jogoObservavel) {
        this.jogoObservavel = jogoObservavel;

        textFinal = new Text();
        hBoxButtons = new HBox();

        buttonMenu = new Button("Voltar ao Menu");
        buttonSair = new Button("Sair");

        hBoxButtons.getChildren().addAll(buttonMenu, buttonSair);

        hBoxButtons.setAlignment(Pos.CENTER);
        hBoxButtons.setPadding(new Insets(10));
        hBoxButtons.setSpacing(10);

        getChildren().addAll(textFinal, hBoxButtons);

        setAlignment(Pos.CENTER);

        VBox.setVgrow(textFinal, Priority.ALWAYS);

        registaListeners();
        registaObservers();
    }

    private void registaListeners() {
        buttonMenu.setOnAction(event -> jogoObservavel.menuInicial());
        buttonSair.setOnAction(event -> {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        });
    }

    private void registaObservers() {
        jogoObservavel.addPropertyChangeListener(Propriedades.PROPRIEDADE_JOGAR, evt -> {
            if (jogoObservavel.getSituacaoAtual() == Situacao.VITORIA) {
                textFinal.setText("GANHOU O JOGADOR " + jogoObservavel.getJogadorAtualNome());

                if (jogoObservavel.getJogadorAtualCor() == 'R')
                    this.setStyle("-fx-background-color: rgba(100, 0, 0, 0.5);");
                else
                    this.setStyle("-fx-background-color: rgba(100, 100, 0, 0.5);");
            }
            if (jogoObservavel.getSituacaoAtual() == Situacao.EMPATE) {
                textFinal.setText("EMPATE");

                this.setStyle("-fx-background-color: rgba(100, 100, 100, 0.5);");
            }
        });
    }
}
