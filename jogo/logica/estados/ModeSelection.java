package jogo.logica.estados;

import jogo.logica.dados.DadosJogo;

public class ModeSelection extends EstadoAdapter {
    public ModeSelection(DadosJogo dadosJogo) {
        super(dadosJogo);
    }

    @Override
    public IEstado menuInicial() {
        return new MenuSelection(getDadosJogo());
    }

    @Override
    public IEstado iniciaRegisto(String modo) {
        getDadosJogo().iniciaJogadores(modo);

        return new RegistarNomes(getDadosJogo());
    }

    @Override
    public Situacao getSituacaoAtual() {
        return Situacao.MODE_SELECTION;
    }
}
