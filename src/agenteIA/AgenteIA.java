package agenteIA;
//ele é o 2, for now

import Game.ConnectFourModel;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class AgenteIA {
    private ConnectFourModel modelo;

    private int PLAYER_AI = 2;
    private int PLAYER_HUMAN = 1;

    private int aiMove = 0;


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
        //     return julgaPossibilidades(colunasPossiveis);
        return ThreadLocalRandom.current().nextInt(0, modelo.getRows());
    }


    private int minMax(ConnectFourModel modeloRecebido,int depth, int jogador) {
        if (modeloRecebido.checkWin() == -1) {
            return 0;
        }
        if(modeloRecebido.checkWin() == 1){
            return -1;
        }
        if(modeloRecebido.checkWin() == 2){
            return 1;
        }
        ArrayList<Integer> colunasPossiveis = modeloRecebido.getColunasJogaveis();

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (Integer i : colunasPossiveis) {
            if (jogador == PLAYER_AI) {
                ConnectFourModel modeloJogada = modeloRecebido.simulaJogada(i,2);
                int currentScore = minMax(modeloJogada,depth + 1, PLAYER_HUMAN);
                Math.max(currentScore,max);

                if(depth == 0){
                    System.out.println("Score da coluna"+i+" = " + currentScore);
                }

            } else if (jogador == PLAYER_HUMAN) {
                ConnectFourModel modeloJogada = modeloRecebido.simulaJogada(i,1);
                int currentScore = minMax(modeloJogada,depth + 1, PLAYER_AI);
                Math.min(currentScore,min);

                if(depth == 0){
                    System.out.println("Score da coluna"+i+" = " + currentScore);
                }
            }
        }
        return

    }

    private int julgaPossibilidades(ConnectFourModel modelo, boolean max) {

        ArrayList<Integer> colunasPossiveis = modelo.getColunasJogaveis();

        int melhorColuna = 0;
        int currentUtil = 0;
        if (max) {
            currentUtil = Integer.MIN_VALUE;
        } else {
            currentUtil = Integer.MAX_VALUE;
        }

        for (Integer coluna : colunasPossiveis) {
            int[][] novoTabuleiro = modelo.simulaJogada(modelo.getGameBoard(), coluna, 2).getGameBoard();
            int myHor = modelo.checkHorizontalPieces(2, novoTabuleiro);
            int myVer = modelo.checkVerticalPieces(2, novoTabuleiro);
            int myBLTR = modelo.checkDiagonalBLTRPieces(2, novoTabuleiro);
            int myBRTL = modelo.checkDiagonalBRTLPieces(2, novoTabuleiro);
            int value = (100 * (myHor + myVer + myBLTR + myBRTL)) / 16;

            if (myHor == 4 || myVer == 4 || myBLTR == 4 || myBRTL == 4) {
                value += 1000;
            }

            System.out.println("----------------------------COLUNA" + coluna + "--------------------------------------");

            System.out.println("NUmero de pecas horizontais minha" + myHor);
            System.out.println("Numero de pecas verticais minha" + myVer);
            System.out.println("Numero de pecas BLTR minha" + myBLTR);
            System.out.println("Numero de pecas BRTL minha" + myBRTL);

            System.out.println("\n\n");

            int eneHor = modelo.checkHorizontalPieces(1, novoTabuleiro);
            int eneVer = modelo.checkVerticalPieces(1, novoTabuleiro);
            int eneBLTR = modelo.checkDiagonalBLTRPieces(1, novoTabuleiro);
            int eneBRTL = modelo.checkDiagonalBRTLPieces(1, novoTabuleiro);


            int valueEnemy = (100 * (eneHor + eneVer + eneBLTR + eneBRTL)) / 16;

            if (eneHor == 4 || eneVer == 4 || eneBLTR == 4 || eneBRTL == 4) {
                valueEnemy -= 1000;
            }

            System.out.println("Número de pecas horizontais inimigo" + eneHor);
            System.out.println("Numero de pecas verticais inimigo" + eneVer);
            System.out.println("Numero de pecas BLTR inimigo" + eneBLTR);
            System.out.println("Numero de pecas BRTL inimigo" + eneBRTL);

            System.out.println("\n\n");

            int utility = value - valueEnemy;

            if (eneHor == 3 || eneVer == 3 || eneBLTR == 3 || eneBRTL == 3) {
                utility -= 100;
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

            if (utility > currentUtil && max) {
                currentUtil = utility;
                melhorColuna = coluna;
            }
            if (utility < currentUtil && !max) {
                currentUtil = utility;
                melhorColuna = coluna;
            }

        }
        System.out.println("Escolhida a coluna " + melhorColuna);
        return melhorColuna;
    }
}
