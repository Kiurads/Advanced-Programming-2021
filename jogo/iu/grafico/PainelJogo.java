package jogo.iu.grafico;

import javafx.scene.layout.BorderPane;
import jogo.logica.JogoObservavel;
import jogo.logica.estados.Situacao;

public class PainelJogo extends BorderPane {
    private JogoObservavel jogoObservavel;
    private PainelTabuleiro painelTabuleiro;
    private PainelLateral painelLateral;

    private PainelFinal painelFinal;

    public PainelJogo(JogoObservavel jogoObservavel) {
        this.jogoObservavel = jogoObservavel;

        painelTabuleiro = new PainelTabuleiro(jogoObservavel);
        painelLateral = new PainelLateral(jogoObservavel);
        painelFinal = new PainelFinal(jogoObservavel);

        setCenter(painelTabuleiro);
        setRight(painelLateral);

        registarObservers();
    }

    private void registarObservers() {
        jogoObservavel.addPropertyChangeListener(Propriedades.PROPRIEDADE_JOGAR, evt -> {
            if (jogoObservavel.getSituacaoAtual() == Situacao.VITORIA || jogoObservavel.getSituacaoAtual() == Situacao.EMPATE) {
                setRight(painelFinal);
            } else {
                setCenter(painelTabuleiro);
                setRight(painelLateral);
            }
        });
        jogoObservavel.addPropertyChangeListener(Propriedades.PROPRIEDADE_MINIJOGO, evt -> {
            setCenter(new PainelMinijogo(jogoObservavel));
        });
    }
}
