package jogo.iu.grafico;

import javafx.scene.layout.BorderPane;
import jogo.logica.JogoObservavel;

import java.io.FileNotFoundException;

public class VistaPrincipal extends BorderPane {
    private JogoObservavel jogoObservavel;
    private PainelInicial painelInicial;
    private PainelJogo painelJogo;

    public VistaPrincipal(JogoObservavel jogoObservavel) throws FileNotFoundException {
        this.jogoObservavel = jogoObservavel;

        setStyle("-fx-border-color: black");

        painelInicial = new PainelInicial(jogoObservavel);
        painelJogo = new PainelJogo(jogoObservavel);

        this.setCenter(painelInicial);

        registaObservers();
    }

    private void registaObservers() {
        jogoObservavel.addPropertyChangeListener(Propriedades.PROPRIEDADE_MENU_SELECTION, evt -> {
            setCenter(painelInicial);
        });

        jogoObservavel.addPropertyChangeListener(Propriedades.PROPRIEDADE_JOGAR, evt -> {
            setCenter(painelJogo);
        });
    }
}
