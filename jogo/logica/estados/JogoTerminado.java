package jogo.logica.estados;

import jogo.logica.dados.DadosJogo;

public class JogoTerminado extends EstadoAdapter {
    public JogoTerminado(DadosJogo dadosJogo) {
        super(dadosJogo);
    }

    @Override
    public IEstado menuInicial() {
        return new MenuSelection(getDadosJogo());
    }

    @Override
    public Situacao getSituacaoAtual() {
        if (getDadosJogo().verificaSeGanhou())
            return Situacao.VITORIA;
        return Situacao.EMPATE;
    }
}
