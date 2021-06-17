package jogo.logica.dados;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class DadosJogo implements Serializable {
    public static final long serialVersionUID = 1L;

    public static final int NUM_LINHAS = 6;
    public static final int NUM_COLUNAS = 7;

    private char[][] tabuleiro;
    private ArrayList<Jogada> jogadas;
    private Jogador jogador1;
    private Jogador jogador2;
    private Jogador jogadorAtual;
    private MiniJogo miniJogo;
    private int numeroJogada;

    public DadosJogo() {
        this.tabuleiro = new char[NUM_LINHAS][NUM_COLUNAS];
        this.jogadas = new ArrayList<>();
    }

    public void iniciaJogadores(String modo) {
        switch (modo) {
            case Modos.JVJ:
                jogador1 = new Jogador(true, 'Y');
                jogador2 = new Jogador(true, 'R');

                break;

            case Modos.JVM:
                jogador1 = new Jogador(true, 'Y');
                jogador2 = new Jogador(false, 'R');

                break;

            case Modos.MVM:
                jogador1 = new Jogador(false, 'Y');
                jogador2 = new Jogador(false, 'R');

                break;
        }
    }

    public void iniciaDados(String jogador1, String jogador2) {
        numeroJogada = (int) Math.round(Math.random()); //math.round retorna long dai usar cast para int

        this.jogador1.setNome(jogador1);
        this.jogador2.setNome(jogador2);

        mudaJogador();

        for (int i = 0; i < NUM_LINHAS; i++) {
            for (int j = 0; j < NUM_COLUNAS; j++) {
                tabuleiro[i][j] = ' ';
            }
        }

        jogadas.clear();
    }

    public void jogaPeca(int coluna) {
        Jogada jogada;

        if (coluna < 0 || coluna > NUM_COLUNAS) {
            return;
        }

        for (int i = NUM_LINHAS - 1; i >= 0; i--) {
            if (this.tabuleiro[i][coluna] == ' ') {
                this.tabuleiro[i][coluna] = jogadorAtual.getCor();
                jogadorAtual.incrementaJogadas();
                jogada = new Jogada(jogadorAtual, coluna, i, numeroJogada);
                this.jogadas.add(jogada);

                if (!verificaSeGanhou())
                    passaTurno();

                break;
            }
        }
    }

    private void mudaJogador() {
        if (numeroJogada % 2 == 0)
            jogadorAtual = jogador1;
        else
            jogadorAtual = jogador2;
    }

    public void jogaPecaEspecial(int coluna) {
        jogadorAtual.setPecaEspecial(false);

        for (int i = 0; i < NUM_LINHAS; i++) {
            tabuleiro[i][coluna] = ' ';
        }

        passaTurno();
    }

    public boolean verificaSeGanhou() {
        if (jogadas.isEmpty())
            return false;

        int linha = jogadas.get(jogadas.size() - 1).getLinha();
        int coluna = jogadas.get(jogadas.size() - 1).getColuna();
        int consecutivo = 0;
        char celula = getCorPosicao(linha, coluna);

        for (int i = 0; i < NUM_COLUNAS; i++) {
            if (celula == getCorPosicao(linha, i)) {
                consecutivo++;
            } else {
                consecutivo = 0;
            }
            if (consecutivo == 4) {
                return true;
            }
        }

        consecutivo = 0;

        for (int i = 0; i < NUM_LINHAS; i++) {
            if (celula == getCorPosicao(i, coluna)) {
                consecutivo++;
            } else {
                consecutivo = 0;
            }
            if (consecutivo == 4) {
                return true;
            }
        }

        consecutivo = 0;

        for (int i = -2; i < 4; i++) {
            for (int j = 0; j < NUM_LINHAS; j++) {
                if (j + i >= NUM_COLUNAS || j + i < 0)
                    continue;

                if (celula == getCorPosicao(j, j + i))
                    consecutivo++;
                else
                    consecutivo = 0;

                if (consecutivo == 4)
                    return true;
            }

            consecutivo = 0;
        }

        consecutivo = 0;

        for (int i = -2; i < 4; i++) {
            for (int j = NUM_LINHAS - 1; j >= 0; j--) {
                if (NUM_LINHAS - 1 - j + i >= NUM_COLUNAS || NUM_LINHAS - 1 - j + i < 0)
                    continue;

                if (celula == getCorPosicao(j, NUM_LINHAS - 1 - j + i))
                    consecutivo++;
                else
                    consecutivo = 0;

                if (consecutivo == 4)
                    return true;
            }

            consecutivo = 0;
        }

        return false;
    }

    public Set<Integer> jogadasPossiveis() {

        Set<Integer> solucoesPossiveis = new HashSet<>();
        for (int i = 0; i < NUM_COLUNAS; i++) {
            if (tabuleiro[0][i] == ' ') {
                solucoesPossiveis.add(i);
            }
        }
        return solucoesPossiveis;
    }

    public void jogaPecaAleatoria() {
        Set<Integer> jogadasPossiveis = jogadasPossiveis();
        List<Integer> listaJogadas = new ArrayList<>(jogadasPossiveis);
        int tamanhoDaLista = listaJogadas.size();

        if (tamanhoDaLista == 0) {
            return;
        }

        int posicaoNaLista = (int) Math.round(Math.random() * (tamanhoDaLista - 1));
        int coluna = listaJogadas.get(posicaoNaLista);

        jogaPeca(coluna);
    }

    //TODO
    void guardaEmFicheiro() { //Guarda/escreve em ficheiro
        try {
            File Save1 = new File("save1.txt");
            if (Save1.createNewFile()) {
                System.out.println("jogo guardado: " + Save1.getName());
            } else {
                System.out.println("ficheiro já existente");
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro");
            e.printStackTrace();
        }
        // jogada, tabuleiro ou seja matriz e arraylist da jogada
    }

    void lerDeFicheiro() {
        //permite ler o ficheiro e recriar o tabuleiro como antes
        try {
            File myObj = new File("save1.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ocorreu um erro");
            e.printStackTrace();
        }
    }

    public char[][] getTabuleiro() {
        return tabuleiro;
    }

    public ArrayList<Jogada> getJogadas() {
        return jogadas;
    }

    @Override
    public String toString() {
        int contadorc = 0;
        int contadorl = 0;
        String resultado = "";
        while (contadorl < DadosJogo.NUM_LINHAS) {
            contadorc = 0;
            while (contadorc < NUM_COLUNAS) {
                resultado = resultado + (this.tabuleiro[contadorl][contadorc]) + " ";
                contadorc++;
            }
            resultado = resultado + "\n";
            contadorl++;
        }
        return resultado;
    }

    public String getPlayerName(int jogador) {
        if (jogador == 0)
            return jogador1.getNome();
        return jogador2.getNome();
    }

    public int getPlayerCredits(int jogador) {
        if (jogador == 0)
            return jogador1.getCreditos();
        return jogador2.getCreditos();
    }

    public boolean getPlayerIsPlaying(int jogador) {
        return numeroJogada % 2 == jogador;
    }

    public boolean getPlayerHasPecaEspecial(int jogador) {
        if (jogador == 0)
            return jogador1.hasPecaEspecial();
        return jogador2.hasPecaEspecial();
    }

    public boolean getPlayerCanPlayMinigame(int jogador) {
        if (jogador == 0)
            return jogador1.getJogadas() >= 4;
        return jogador2.getJogadas() >= 4;
    }

    public int getNumeroJogada() {
        return numeroJogada;
    }

    public boolean getJogadorAtualIsHumano() {
        return jogadorAtual.isHumano();
    }

    public char getCorPosicao(int x, int y) {
        return tabuleiro[x][y];
    }

    public String getJogadorAtualNome() {
        return jogadorAtual.getNome();
    }

    public char getJogadorAtualCor() {
        return jogadorAtual.getCor();
    }

    public char getPlayerCor(int jogador) {
        if (jogador == 0)
            return jogador1.getCor();
        return jogador2.getCor();
    }

    public List<String> getDadosMinijogo() {
        return miniJogo.getDados();
    }

    public void jogaMinijogo() {
        if (jogadorAtual.getMinijogo() == 0)
            miniJogo = new JogoCalculo();
        else
            miniJogo = new JogoPalavras();

        jogadorAtual.jogaMinijogo();
    }

    public int getJogadorAtualJogadas() {
        return jogadorAtual.getJogadas();
    }

    public boolean verificaMinijogo(List<String> respostas) {
        return miniJogo.verifica(respostas);
    }

    public boolean verificaTempoMinijogo() {
        return miniJogo.getLimiteTempo() > System.currentTimeMillis() - miniJogo.getJogoInicia();
    }

    public void passaTurno() {
        numeroJogada++;

        mudaJogador();
    }

    public boolean ganhouMinijogo() {
        return miniJogo.ganhou();
    }

    public void ganhaMinijogo() {
        jogadorAtual.setPecaEspecial(true);
    }

    public long getTempoMinijogo() {
        return miniJogo.getLimiteTempo() - (System.currentTimeMillis() - miniJogo.getJogoInicia());
    }

    public boolean getJogadorAtualHasPecaEspecial() {
        return jogadorAtual.hasPecaEspecial();
    }

    public boolean verificaEmpate() {
        for (int i = 0; i < NUM_COLUNAS; i++) {
            if (tabuleiro[0][i] == ' ')
                return false;
        }

        return true;
    }
}

