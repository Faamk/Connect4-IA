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
        int biggerUtil = Integer.MIN_VALUE;

        for (Integer coluna : colunasPossiveis) {
            int[][] novoTabuleiro = modelo.simulaJogada(coluna,2);
            int myHor = modelo.checkHorizontalPieces(2, novoTabuleiro);
            int myVer = modelo.checkVerticalPieces(2, novoTabuleiro);
            int myBLTR = modelo.checkDiagonalBLTRPieces(2, novoTabuleiro);
            int myBRTL = modelo.checkDiagonalBRTLPieces(2, novoTabuleiro);
            int value = (100* (myHor + myVer + myBLTR + myBRTL) )/16;

            int eneHor = modelo.checkHorizontalPieces(1, novoTabuleiro);
            int eneVer = modelo.checkVerticalPieces(1, novoTabuleiro);
            int eneBLTR = modelo.checkDiagonalBLTRPieces(1, novoTabuleiro);
            int eneBRTL = modelo.checkDiagonalBRTLPieces(1, novoTabuleiro);
            int valueEnemy = (100* (eneHor + eneVer+ eneBLTR+ eneBRTL) )/16;

            int utility = value-valueEnemy;

            if(eneHor==3||eneVer==3||eneBLTR==3||eneBRTL==3){
                utility-=100;
            }

//            int sum = 0;
//            for (int i = 0; i < modelo.getRows(); i++) {
//                for (int j = 0; j < modelo.getCols(); j++) {
//                    if (novoTabuleiro[i][j] == 1)
//                        sum += tabelaValores[i][j];
//                    else if (novoTabuleiro[i][j] == 2)
//                        sum -= tabelaValores[i][j];
//                }
//
//
//            }
//            System.out.println("na coluna " + coluna + " soma-se um valor de " + (utility + sum));
//
//            if ((utility + sum) > biggerUtil) {
//                biggerUtil = (utility + sum);
//                melhorColuna = coluna;
//            }

            if (utility > biggerUtil) {
                biggerUtil = utility;
                melhorColuna = coluna;
            }

        }
        System.out.println("Escolhida a coluna " + melhorColuna);
        return melhorColuna;
    }
}
