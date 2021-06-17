package jogo.logica.estados;

import jogo.logica.dados.DadosJogo;

public class EsperaJogadaAleatoria extends EstadoAdapter {
    public EsperaJogadaAleatoria(DadosJogo dadosJogo) {
        super(dadosJogo);
    }

    @Override
    public IEstado jogaPeca() {
        getDadosJogo().jogaPecaAleatoria();

        if (getDadosJogo().verificaSeGanhou())
            return new JogoTerminado(getDadosJogo());

        if (getDadosJogo().getJogadorAtualIsHumano())
            return new EsperaJogadaHumano(getDadosJogo());
        return this;
    }

    @Override
    public Situacao getSituacaoAtual() {
        return Situacao.ESPERA_JOGADA_ALEATORIA;
    }
}
