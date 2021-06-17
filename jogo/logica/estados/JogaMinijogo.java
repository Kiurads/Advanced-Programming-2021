package jogo.logica.estados;

import jogo.logica.dados.DadosJogo;

import java.util.List;

public class JogaMinijogo extends EstadoAdapter {
    public JogaMinijogo(DadosJogo dadosJogo) {
        super(dadosJogo);
    }

    @Override
    public IEstado verificaMinijogo(List<String> respostas) {
        if (getDadosJogo().verificaMinijogo(respostas)) {
            getDadosJogo().ganhaMinijogo();
            return new EsperaJogadaHumano(getDadosJogo());
        }

        if (getDadosJogo().verificaTempoMinijogo()) {
            return this;
        }

        getDadosJogo().passaTurno();

        if (getDadosJogo().getJogadorAtualIsHumano())
            return new EsperaJogadaHumano(getDadosJogo());
        return new EsperaJogadaAleatoria(getDadosJogo());
    }

    @Override
    public Situacao getSituacaoAtual() {
        return Situacao.MINI_JOGO;
    }
}
