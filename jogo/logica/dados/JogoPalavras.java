package jogo.logica.dados;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JogoPalavras extends MiniJogo {
    List<String> palavrasAMostrar;

    public JogoPalavras() {
        super();

        String s;
        ArrayList<String> palavras = new ArrayList<>();
        BufferedReader ficheiro;

        //abrir ficheiro para leitura
        try {
            ficheiro = abreFTextoLeitura("100palavras.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        while (true) {
            try {
                if (!((s = ficheiro.readLine()) != null)) break;
            } catch (IOException e) {
                s = null;
                e.printStackTrace();
            }

            palavras.add(s.toLowerCase());
        }

        palavrasAMostrar = new ArrayList<>();

        int numeroCaracteres = 0;

        while (palavrasAMostrar.size() < 5) {
            String palavrasAleatoria = palavras.remove((int) Math.round(Math.random() * palavras.size()));
            palavrasAMostrar.add(palavrasAleatoria);

            numeroCaracteres += palavrasAleatoria.length();
        }

        setLimiteTempo((numeroCaracteres / 2) * 1000L);
        this.iniciaJogo();
    }

    public boolean verifica(List<String> respostas) {
        terminaJogo();

        if (respostas == null)
            return false;

        setAcertos(0);

        for (int i = 0; i < respostas.size(); i++) {
            acertou();

            if (!respostas.get(i).equals(palavrasAMostrar.get(i)))
                return false;
        }

        return getJogoTermina() - getJogoInicia() < getLimiteTempo();
    }

    private BufferedReader abreFTextoLeitura(String nomeFicheiro) throws FileNotFoundException {
        BufferedReader in;
        try {
            in = new BufferedReader(
                    new FileReader(nomeFicheiro));
            return in;
        } catch (IOException e) {
            System.out.println("Erro ao ler o ficheiro: " + nomeFicheiro);
            throw e;
        }
    }

    @Override
    public List<String> getDados() {
        return palavrasAMostrar;
    }
}