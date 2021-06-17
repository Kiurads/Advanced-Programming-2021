package jogo.iu.grafico;

import javafx.geometry.Insets;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import jogo.logica.JogoObservavel;

public class PainelLateral extends VBox {
    private JogoObservavel jogoObservavel;

    private PainelJogador painelJogador1;
    private PainelJogador painelJogador2;

    private Text textTurno;

    public PainelLateral(JogoObservavel jogoObservavel) {
        this.jogoObservavel = jogoObservavel;

        painelJogador1 = new PainelJogador(jogoObservavel, 0);
        painelJogador2 = new PainelJogador(jogoObservavel, 1);

        textTurno = new Text("Turno: 0");

        setPadding(new Insets(10));
        setSpacing(10);

        getChildren().addAll(painelJogador1, textTurno, painelJogador2);

        setVgrow(painelJogador1, Priority.ALWAYS);
        setVgrow(painelJogador2, Priority.ALWAYS);

        registaObservers();
    }

    private void registaObservers() {
        jogoObservavel.addPropertyChangeListener(Propriedades.PROPRIEDADE_JOGAR, evt -> {
            textTurno.setText("Turno: " + jogoObservavel.getNumeroJogada());
        });
    }
}
