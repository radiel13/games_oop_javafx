package ru.job4j.chess.ChessExceptions;

public class ImpossibleMoveException extends Exception{
    public ImpossibleMoveException(String message) {
        super(message);
    }
}
