package jogo.logica.estados;

import jogo.logica.dados.DadosJogo;

public class MenuSelection extends EstadoAdapter {
    public MenuSelection(DadosJogo dadosJogo) {
        super(dadosJogo);
    }

    @Override
    public IEstado iniciaJogo() {
        return new ModeSelection(getDadosJogo());
    }

    @Override
    public Situacao getSituacaoAtual() {
        return Situacao.MENU_SELECTION;
    }
}
