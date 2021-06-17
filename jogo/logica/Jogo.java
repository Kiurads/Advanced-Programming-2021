package jogo.logica;

import jogo.logica.dados.DadosJogo;
import jogo.logica.estados.IEstado;
import jogo.logica.estados.MenuSelection;
import jogo.logica.estados.Situacao;

import java.util.List;

public class Jogo {
    DadosJogo dadosJogo;
    IEstado estado;

    public Jogo() {
        this.dadosJogo = new DadosJogo();

        this.estado = new MenuSelection(dadosJogo);
    }

    public void menuInicial() {
        setEstado(getEstado().menuInicial());
    }

    public void iniciaJogo() {
        setEstado(getEstado().iniciaJogo());
    }

    public void iniciaRegisto(String modo) {
        setEstado(getEstado().iniciaRegisto(modo));
    }

    public void jogar(String jogador1, String jogador2) {
        setEstado(getEstado().jogar(jogador1, jogador2));
    }

    private IEstado getEstado() {
        return estado;
    }

    private void setEstado(IEstado estado) {
        this.estado = estado;
    }

    public Situacao getSituacaoAtual() {
        return estado.getSituacaoAtual();
    }

    public String getPlayerName(int jogador) {
        return dadosJogo.getPlayerName(jogador);
    }

    public int getPlayerCredits(int jogador) {
        return dadosJogo.getPlayerCredits(jogador);
    }

    public boolean getPlayerIsPlaying(int jogador) {
        return dadosJogo.getPlayerIsPlaying(jogador);
    }

    public boolean getPlayerHasPecaEspecial(int jogador) {
        return dadosJogo.getPlayerHasPecaEspecial(jogador);
    }

    public boolean getPlayerCanPlayMinigame(int jogador) {
        return dadosJogo.getPlayerCanPlayMinigame(jogador);
    }

    public int getNumeroJogada() {
        return dadosJogo.getNumeroJogada();
    }

    public void jogaPeca(int coluna) {
        setEstado(getEstado().jogaPeca(coluna));
    }

    public void jogaPeca() {
        setEstado(getEstado().jogaPeca());
    }

    public char getCorPosicao(int x, int y) {
        return dadosJogo.getCorPosicao(x, y);
    }

    public String getJogadorAtualNome() {
        return dadosJogo.getJogadorAtualNome();
    }

    public char getJogadorAtualCor() {
        return dadosJogo.getJogadorAtualCor();
    }

    public char getPlayerCor(int jogador) {
        return dadosJogo.getPlayerCor(jogador);
    }

    public boolean verificaSeGanhou() {
        return dadosJogo.verificaSeGanhou();
    }

    public List<String> getDadosMinijogo() {
        return dadosJogo.getDadosMinijogo();
    }

    public void jogaMinijogo() {
        setEstado(getEstado().jogaMinijogo());
    }

    public void verificaMinijogo(List<String> respostas) {
        setEstado(getEstado().verificaMinijogo(respostas));
    }

    public boolean ganhouMinijogo() {
        return dadosJogo.ganhouMinijogo();
    }

    public long getTempoMinijogo() {
        return dadosJogo.getTempoMinijogo();
    }

    public void pecaEspecial() {
        setEstado(getEstado().pecaEspecial());
    }
}
