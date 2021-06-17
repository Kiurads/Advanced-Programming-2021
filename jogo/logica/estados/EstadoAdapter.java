package jogo.logica.estados;

import jogo.logica.dados.DadosJogo;

import java.util.List;

public class EstadoAdapter implements IEstado {
    private DadosJogo dadosJogo;

    public EstadoAdapter(DadosJogo dadosJogo) {
        System.out.println(this);
        this.dadosJogo = dadosJogo;
    }

    @Override
    public IEstado iniciaJogo() {
        return this;
    }

    @Override
    public IEstado menuInicial() {
        return this;
    }

    @Override
    public IEstado iniciaRegisto(String modo) {
        return this;
    }

    @Override
    public IEstado jogar(String jogador1, String jogador2) {
        return this;
    }

    @Override
    public IEstado jogaPeca() {
        return this;
    }

    @Override
    public IEstado jogaPeca(int coluna) {
        return this;
    }

    @Override
    public IEstado jogaMinijogo() {
        return this;
    }

    @Override
    public IEstado verificaMinijogo(List<String> respostas) {
        return this;
    }

    @Override
    public IEstado pecaEspecial() {
        return this;
    }

    @Override
    public Situacao getSituacaoAtual() {
        return null;
    }

    public DadosJogo getDadosJogo() {
        return dadosJogo;
    }
}
