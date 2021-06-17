package jogo.iu.grafico;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import jogo.logica.JogoObservavel;
import jogo.logica.estados.Situacao;

public class PainelTabuleiro extends GridPane {
    private JogoObservavel jogoObservavel;
    private Circle[][] circles;
    private Button[] buttons;

    public PainelTabuleiro(JogoObservavel jogoObservavel) {
        this.jogoObservavel = jogoObservavel;

        circles = new Circle[6][7];
        buttons = new Button[7];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                circles[i][j] = new Circle(35, Color.WHITE);

                add(circles[i][j], j, i);
            }
        }

        for (int i = 0; i < 7; i++) {
            int coluna = i;

            buttons[i] = new Button(String.valueOf(i));

            buttons[i].setAlignment(Pos.CENTER);
            buttons[i].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

            buttons[i].setOnAction(event -> {
                jogoObservavel.jogaPeca(coluna);
            });

            add(buttons[i], i, 6);
        }

        setGridLinesVisible(true);

        setStyle("-fx-background-color: gray;");
        setAlignment(Pos.CENTER);

        registaObservers();
    }

    private void registaObservers() {
        jogoObservavel.addPropertyChangeListener(Propriedades.PROPRIEDADE_JOGAR, evt -> {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    switch (jogoObservavel.getCorPosicao(i, j)) {
                        case 'R':
                            circles[i][j].setFill(Color.RED);

                            break;
                        case 'Y':
                            circles[i][j].setFill(Color.YELLOW);

                            break;
                        case ' ':
                            circles[i][j].setFill(Color.WHITE);

                            break;
                    }
                }
            }

            if (jogoObservavel.getSituacaoAtual() == Situacao.ESPERA_JOGADA_ALEATORIA && !jogoObservavel.verificaSeGanhou())
                jogoObservavel.jogaPeca();
        });
    }
}
