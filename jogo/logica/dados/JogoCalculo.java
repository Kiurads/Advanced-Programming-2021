package jogo.logica.dados;

import java.util.ArrayList;
import java.util.List;

public class JogoCalculo extends MiniJogo {
    List<Conta> contas;

    public JogoCalculo() {
        super();

        contas = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            contas.add(new Conta());
        }

        setLimiteTempo(30000);
    }

    @Override
    public boolean verifica(List<String> respostas) {
        terminaJogo();

        if (respostas == null)
            return false;

        setAcertos(0);

        for (int i = 0; i < contas.size(); i++) {
            acertou();

            try {
                if (!(Integer.parseInt(respostas.get(i)) == contas.get(i).getResultado()))
                    return false;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return getJogoTermina() - getJogoInicia() < getLimiteTempo();
    }

    @Override
    public List<String> getDados() {
        List<String> contasTexto = new ArrayList<>();

        for (Conta conta:contas) {
            contasTexto.add(conta.toString());
        }

        return contasTexto;
    }
}

