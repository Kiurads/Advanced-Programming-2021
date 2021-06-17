package jogo.logica;

import jogo.iu.grafico.Propriedades;
import jogo.logica.estados.Situacao;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class JogoObservavel implements Propriedades {
    private PropertyChangeSupport propertyChangeSupport;
    private Jogo jogo;

    public JogoObservavel() {
        this.jogo = new Jogo();

        propertyChangeSupport = new PropertyChangeSupport(jogo);
    }

    public void iniciaJogo() {
        jogo.iniciaJogo();

        //Causar mudanças na GUI
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_MODE_SELECTION, null, null);
    }

    public void menuInicial() {
        jogo.menuInicial();

        //Causar mudanças na GUI
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_MENU_SELECTION, null, null);
    }

    public void iniciaRegisto(String modo) {
        jogo.iniciaRegisto(modo);

        //Causar mudanças na GUI
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_REGISTAR_NOMES, null, null);
    }

    public void jogar(String jogador1, String jogador2) {
        jogo.jogar(jogador1, jogador2);

        //Causar mudanças na GUI
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_JOGAR, null, null);
    }

    public void jogaPeca(int coluna) {
        jogo.jogaPeca(coluna);

        //Causar mudanças na GUI
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_JOGAR, null, null);
    }

    public void jogaPeca() {
        jogo.jogaPeca();

        //Causar mudanças na GUI
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_JOGAR, null, null);
    }

    public void jogaMinijogo() {
        jogo.jogaMinijogo();

        //Causar mudanças na GUI
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_MINIJOGO, null, null);
    }

    public void verificaMinijogo(List<String> respostas) {
        jogo.verificaMinijogo(respostas);

        //Causar mudanças na GUI
        if (getSituacaoAtual() == Situacao.MINI_JOGO)
            propertyChangeSupport.firePropertyChange(PROPRIEDADE_MINIJOGO, null, null);
        else
            propertyChangeSupport.firePropertyChange(PROPRIEDADE_JOGAR, null, null);
    }

    public void pecaEspecial() {
        jogo.pecaEspecial();

        //Causar mudanças na GUI
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_JOGAR, null, null);
    }

    public void addPropertyChangeListener(String propriedade, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(propriedade, listener);
    }

    public int getNumeroJogada() {
        return jogo.getNumeroJogada();
    }

    public Situacao getSituacaoAtual() {
        return jogo.getSituacaoAtual();
    }

    public String getPlayerName(int jogador) {
        return jogo.getPlayerName(jogador);
    }

    public int getPlayerCredits(int jogador) {
        return jogo.getPlayerCredits(jogador);
    }

    public boolean getPlayerIsPlaying(int jogador) {
        return jogo.getPlayerIsPlaying(jogador);
    }

    public boolean getPlayerHasPecaEspecial(int jogador) {
        return jogo.getPlayerHasPecaEspecial(jogador);
    }

    public boolean getPlayerCanPlayMinigame(int jogador) {
        return jogo.getPlayerCanPlayMinigame(jogador);
    }

    public char getCorPosicao(int x, int y) {
        return jogo.getCorPosicao(x, y);
    }

    public String getJogadorAtualNome() {
        return jogo.getJogadorAtualNome();
    }

    public char getJogadorAtualCor() {
        return jogo.getJogadorAtualCor();
    }

    public char getPlayerCor(int jogador) {
        return jogo.getPlayerCor(jogador);
    }

    public boolean verificaSeGanhou() {
        return jogo.verificaSeGanhou();
    }

    public List<String> getDadosMinijogo() {
        return jogo.getDadosMinijogo();
    }

    public boolean ganhouMinijogo() {
        return jogo.ganhouMinijogo();
    }

    public long getTempoMinijogo() {
        return jogo.getTempoMinijogo();
    }
}
