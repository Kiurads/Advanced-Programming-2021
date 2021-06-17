package jogo.logica.dados;

import java.util.List;

public abstract class MiniJogo {
    private long limiteTempo;
    private long jogoTermina;
    private long jogoInicia;
    private int acertos;

    MiniJogo() {
        iniciaJogo();

        acertos = 0;
    }

    public void iniciaJogo() {
        jogoInicia = System.currentTimeMillis();
    }

    public void acertou() {
        acertos++;
    }

    public void setAcertos(int acertos) {
        this.acertos = acertos;
    }

    public long getJogoInicia()
    {
        return jogoInicia;
    }

    public long getJogoTermina() {
        return jogoTermina;
    }

    public long getLimiteTempo() {
        return limiteTempo;
    }

    public void setLimiteTempo(long limiteTempo) {
        this.limiteTempo = limiteTempo;
    }

    public void terminaJogo() {
        jogoTermina = System.currentTimeMillis();
    }

    public boolean ganhou() {
        return acertos == 5 && getLimiteTempo() > getJogoTermina() - getJogoInicia();
    }

    public abstract boolean verifica(List<String> respostas);

    public abstract List<String> getDados();
}
