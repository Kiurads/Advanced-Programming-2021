package jogo.logica.estados;

import jogo.logica.dados.DadosJogo;

public class RegistarNomes extends EstadoAdapter {
    public RegistarNomes(DadosJogo dadosJogo) {
        super(dadosJogo);
    }

    @Override
    public IEstado menuInicial() {
        return new MenuSelection(getDadosJogo());
    }

    @Override
    public IEstado jogar(String jogador1, String jogador2) {
        getDadosJogo().iniciaDados(jogador1, jogador2);

        if (getDadosJogo().getJogadorAtualIsHumano())
            return new EsperaJogadaHumano(getDadosJogo());
        return new EsperaJogadaAleatoria(getDadosJogo());
    }

    @Override
    public Situacao getSituacaoAtual() {
        return Situacao.REGISTAR_NOMES;
    }
}
