package jogo.logica.estados;

import jogo.logica.dados.DadosJogo;

public class EsperaJogadaEspecial extends EstadoAdapter {
    public EsperaJogadaEspecial(DadosJogo dadosJogo) {
        super(dadosJogo);
    }

    @Override
    public IEstado jogaPeca(int coluna) {
        getDadosJogo().jogaPecaEspecial(coluna);

        if (getDadosJogo().getJogadorAtualIsHumano())
            return new EsperaJogadaHumano(getDadosJogo());
        return new EsperaJogadaAleatoria(getDadosJogo());
    }

    @Override
    public Situacao getSituacaoAtual() {
        return Situacao.ESPERA_JOGADA_ESPECIAL;
    }
}
