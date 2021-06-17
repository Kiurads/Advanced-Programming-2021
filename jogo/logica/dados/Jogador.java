package jogo.logica.dados;

import java.util.Random;

public class Jogador {
    private String nome;
    private char cor;
    private int creditos;
    private int jogadas;
    private boolean humano;
    private boolean pecaEspecial;
    private int minijogo;

    public Jogador(boolean humano, char cor) {
        if (humano == true) {
            this.creditos = 5;
        } else {
            this.creditos = 0;
        }

        this.cor = cor;

        this.humano = humano;
        this.pecaEspecial = false;

        this.jogadas = 0;

        this.minijogo = new Random().nextInt(2);
    }

    public boolean temCreditos() {
        if (this.creditos > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean decrementaCredito() {
        if (temCreditos()) {
            this.creditos--;
            return true;
        }
        return false;
    }

    public void incrementaJogadas() {
        setJogadas(getJogadas() + 1);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getCor() {
        return cor;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public boolean isHumano() {
        return humano;
    }

    public void setHumano(boolean humano) {
        this.humano = humano;
    }

    public boolean hasPecaEspecial() {
        return pecaEspecial;
    }

    public void setPecaEspecial(boolean pecaEspecial) {
        this.pecaEspecial = pecaEspecial;
    }

    public int getJogadas() {
        return jogadas;
    }

    public void setJogadas(int jogadas) {
        this.jogadas = jogadas;
    }

    public void jogaMinijogo() {
        setJogadas(0);
        minijogo++;
    }

    public int getMinijogo() {
        return minijogo % 2;
    }

    @Override
    public String toString() {
        return nome;
    }
}
