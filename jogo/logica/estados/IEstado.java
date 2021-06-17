package jogo.logica.estados;

import java.util.List;

public interface IEstado {
    IEstado iniciaJogo();
    IEstado menuInicial();
    IEstado iniciaRegisto(String modo);
    IEstado jogar(String jogador1, String jogador2);
    IEstado jogaPeca();
    IEstado jogaPeca(int coluna);
    IEstado jogaMinijogo();
    IEstado verificaMinijogo(List<String> respostas);
    IEstado pecaEspecial();
    Situacao getSituacaoAtual();

}
