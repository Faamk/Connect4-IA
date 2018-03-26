package agenteIA;
//ele é o 2, for now

import game.ConnectFourModel;

import java.util.*;

public class AgenteIA {
    private ConnectFourModel modelo;

    private int PLAYER_AI = 2;
    private int PLAYER_HUMAN = 1;

    Map<Integer, Integer> colunasEResultados = new HashMap<>();

    public AgenteIA(ConnectFourModel modelo) {
        this.modelo = modelo;
    }

    public int fazerJogada() {
        minMax(modelo, 4, PLAYER_AI);
        int resu = Collections.max(colunasEResultados.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
        colunasEResultados = new HashMap<>();
        return resu;

    }


    private int minMax(ConnectFourModel modeloRecebido, int depth, int jogador) {
        if (modeloRecebido.checkWin() == -1) {
            return -500;
        }
        if (modeloRecebido.checkWin() == 1) {
            return -1000;
        }
        if (modeloRecebido.checkWin() == 2) {
            return 1000;
        }
        if (depth == 0) {
            return rateBoard(modeloRecebido.repeteTabuleiro());
        }
        ArrayList<Integer> colunasPossiveis = modeloRecebido.getColunasJogaveis();

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int currentScore = 0;

        if (jogador == PLAYER_AI) {
            for (Integer i : colunasPossiveis) {
                ConnectFourModel modeloJogada = modeloRecebido.simulaJogada(i, 2);
                currentScore = minMax(modeloJogada, depth - 1, PLAYER_HUMAN);
                max = Math.max(currentScore, max);
                if (depth == 4) {
                    System.out.println("Score da coluna" + i + " = " + currentScore);
                    colunasEResultados.put(i, currentScore);
                }

            }
            return max;
        } else {
            for (Integer i : colunasPossiveis) {
                ConnectFourModel modeloJogada = modeloRecebido.simulaJogada(i, 1);
                currentScore = minMax(modeloJogada, depth - 1, PLAYER_AI);
                min = Math.min(currentScore, min);
                if (depth == 4) {
                    System.out.println("Score da coluna" + i + " = " + currentScore);
                    colunasEResultados.put(i, currentScore);
                }

            }
            return min;
        }
    }

    private int rateBoard(int[][] novoTabuleiro) {
        int myHor = modelo.checkHorizontalPieces(2, novoTabuleiro);
        int myVer = modelo.checkVerticalPieces(2, novoTabuleiro);
        int myBLTR = modelo.checkDiagonalBLTRPieces(2, novoTabuleiro);
        int myBRTL = modelo.checkDiagonalBRTLPieces(2, novoTabuleiro);
        int value = (100 * (myHor + myVer + myBLTR + myBRTL)) / 16;
        int eneHor = modelo.checkHorizontalPieces(1, novoTabuleiro);
        int eneVer = modelo.checkVerticalPieces(1, novoTabuleiro);
        int eneBLTR = modelo.checkDiagonalBLTRPieces(1, novoTabuleiro);
        int eneBRTL = modelo.checkDiagonalBRTLPieces(1, novoTabuleiro);
        int valueEnemy = (100 * (eneHor + eneVer + eneBLTR + eneBRTL)) / 16;
        int utility = value - valueEnemy;
        return utility;
    }
}
