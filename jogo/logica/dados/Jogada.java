package jogo.logica.dados;

public class Jogada {
    private Jogador jogador;
    private int coluna;
    private int linha;
    private int numJogada;

    public Jogada(Jogador jogador, int coluna, int linha, int numJogada) {
        this.jogador = jogador;
        this.coluna = coluna;
        this.linha = linha;
        this.numJogada=numJogada;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public int getColuna() {
        return coluna;
    }

    public int getLinha() {
        return linha;
    }

    public int getNumJogada() {
        return numJogada;
    }
}
