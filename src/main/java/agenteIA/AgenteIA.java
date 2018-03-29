package agenteIA;
//ele é o 2, for now

import game.ConnectFourModel;

import java.util.*;

public class AgenteIA {
    private ConnectFourModel modelo;

    private int PLAYER_AI;
    private int PLAYER_HUMAN;

    Map<Integer, Integer> colunasEResultados = new HashMap<>();

    public AgenteIA(ConnectFourModel modelo) {
        if(modelo.isPlayerTurn()) {
            PLAYER_AI = 1;
            PLAYER_HUMAN = 2;
        }else{
            PLAYER_AI = 2;
            PLAYER_HUMAN = 1;
        }
        this.modelo = modelo;
    }

    public int fazerJogada() {
        minMax(modelo, 5, PLAYER_AI);
        int resu = Collections.max(colunasEResultados.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
        colunasEResultados = new HashMap<>();
        modelo.addLastJogada(resu);
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
                ConnectFourModel modeloJogada = modeloRecebido.simulaJogada(i, PLAYER_AI);
                currentScore = minMax(modeloJogada, depth - 1, PLAYER_HUMAN);
                max = Math.max(currentScore, max);
                if (depth == 5) {
                    System.out.println("Score da coluna" + i + " = " + currentScore);
                    colunasEResultados.put(i, currentScore);
                }

            }
            return max;
        } else {
            for (Integer i : colunasPossiveis) {
                ConnectFourModel modeloJogada = modeloRecebido.simulaJogada(i, PLAYER_HUMAN);
                currentScore = minMax(modeloJogada, depth - 1, PLAYER_AI);
                min = Math.min(currentScore, min);
                if (depth == 5) {
                    System.out.println("Score da coluna" + i + " = " + currentScore);
                    colunasEResultados.put(i, currentScore);
                }

            }
            return min;
        }
    }

    private int rateBoard(int[][] novoTabuleiro) {
        int myHor = modelo.checkHorizontalPieces(PLAYER_AI, novoTabuleiro);
        int myVer = modelo.checkVerticalPieces(PLAYER_AI, novoTabuleiro);
        int myBLTR = modelo.checkDiagonalBLTRPieces(PLAYER_AI, novoTabuleiro);
        int myBRTL = modelo.checkDiagonalBRTLPieces(PLAYER_AI, novoTabuleiro);
        int value = (100 * (myHor + myVer + myBLTR + myBRTL)) / 16;
        int eneHor = modelo.checkHorizontalPieces(PLAYER_HUMAN, novoTabuleiro);
        int eneVer = modelo.checkVerticalPieces(PLAYER_HUMAN, novoTabuleiro);
        int eneBLTR = modelo.checkDiagonalBLTRPieces(PLAYER_HUMAN, novoTabuleiro);
        int eneBRTL = modelo.checkDiagonalBRTLPieces(PLAYER_HUMAN, novoTabuleiro);
        int valueEnemy = (100 * (eneHor + eneVer + eneBLTR + eneBRTL)) / 16;
        int utility = value - valueEnemy;
        return utility;
    }
}
