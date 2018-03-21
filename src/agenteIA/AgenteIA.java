package agenteIA;
//ele é o 2, for now

import Game.ConnectFourModel;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class AgenteIA {
    private ConnectFourModel modelo;

    private static int[][] tabelaValores =
            {{3, 4, 5, 7, 5, 4, 3},
            {4, 6, 8, 10, 8, 6, 4},
            {5, 8, 11, 13, 11, 8, 5},
            {5, 8, 11, 13, 11, 8, 5},
            {4, 6, 8, 10, 8, 6, 4},
            {3, 4, 5, 7, 5, 4, 3}};

    public AgenteIA(ConnectFourModel modelo) {
        this.modelo = modelo;
    }

    public int fazerJogada() {
        ArrayList<Integer> colunasPossiveis = modelo.getColunasJogaveis();
        return julgaPossibilidades(colunasPossiveis);
//        return ThreadLocalRandom.current().nextInt(0, modelo.getRows());
    }

    private int julgaPossibilidades(ArrayList<Integer> colunasPossiveis) {
        int melhorColuna = 0;
        int biggerUtil = 0;

        for (Integer coluna : colunasPossiveis) {
            int[][] novoTabuleiro = modelo.simulaJogada(coluna);
            int utility = 138;
            int sum = 0;
            for (int i = 0; i < modelo.getRows(); i++) {
                for (int j = 0; j < modelo.getCols(); j++) {
                    if (novoTabuleiro[i][j] == 2)
                        sum += tabelaValores[i][j];
                    else if (novoTabuleiro[i][j] == 1)
                        sum -= tabelaValores[i][j];
                }


            }
            System.out.println("na coluna "+coluna+" soma-se um valor de "+(utility + sum));

            if((utility + sum)>biggerUtil){
                biggerUtil = (utility + sum);
                melhorColuna = coluna;
            }

        }
        System.out.println("Escolhida a coluna "+melhorColuna);
        return melhorColuna;
    }
}
