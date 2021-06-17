package jogo.logica.dados;

import java.util.Random;

public class Conta {
    private int numero1;
    private int numero2;
    private char operador;

    private int resultado;

    public Conta() {
        numero1 = new Random().nextInt(100);
        numero2 = new Random().nextInt(100);

        switch (new Random().nextInt(4)) {
            case 0:
                operador = '+';

                resultado = numero1 + numero2;

                break;
            case 1:
                operador = '-';

                resultado = numero1 - numero2;

                break;
            case 2:
                operador = '*';

                resultado = numero1 * numero2;

                break;
            case 3:
                operador = '/';

                resultado = numero1 / numero2;

                break;
        }
    }

    public int getResultado() {
        return resultado;
    }

    @Override
    public String toString() {
        return numero1 + " " + operador + " " + numero2;
    }
}
