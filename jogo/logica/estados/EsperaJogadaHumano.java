package jogo.logica.estados;

import jogo.logica.dados.DadosJogo;

public class EsperaJogadaHumano extends EstadoAdapter {
    public EsperaJogadaHumano(DadosJogo dadosJogo) {
        super(dadosJogo);
    }

    @Override
    public IEstado jogaPeca(int coluna) {
        getDadosJogo().jogaPeca(coluna);

        if (getDadosJogo().verificaSeGanhou())
            return new JogoTerminado(getDadosJogo());

        if (getDadosJogo().verificaEmpate())
            return new JogoTerminado(getDadosJogo());

        if (getDadosJogo().getJogadorAtualIsHumano())
            return this;
        return new EsperaJogadaAleatoria(getDadosJogo());
    }

    @Override
    public IEstado jogaMinijogo() {
        if (getDadosJogo().getJogadorAtualJogadas() >= 4) {
            getDadosJogo().jogaMinijogo();
            return new JogaMinijogo(getDadosJogo());
        }

        return this;
    }

    @Override
    public IEstado pecaEspecial() {
        if (getDadosJogo().getJogadorAtualHasPecaEspecial())
            return new EsperaJogadaEspecial(getDadosJogo());
        return this;
    }

    @Override
    public Situacao getSituacaoAtual() {
        return Situacao.ESPERA_JOGADA_HUMANO;
    }
}
