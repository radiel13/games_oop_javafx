package ru.job4j.chess.firuges.black;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.chess.Chess;
import ru.job4j.chess.ChessExceptions.FigureNotFoundException;
import ru.job4j.chess.ChessExceptions.ImpossibleMoveException;
import ru.job4j.chess.ChessExceptions.OccupiedCellException;
import ru.job4j.chess.Logic;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static org.junit.Assert.*;

public class BishopBlackTest {

    @Test
    public void whenPosition() {
        Figure bp = new BishopBlack(Cell.C1);
        Assert.assertEquals(bp.position(), Cell.C1);
    }

    @Test
    public void whenCopy() {
        Figure bp = new BishopBlack(Cell.C1);
        Figure bp2 = bp.copy(Cell.E2);
        Assert.assertEquals(bp2.position(), Cell.E2);
    }

    @Test
    public void whenWay() {
        Figure bp = new BishopBlack(Cell.G1);
        Cell[] steps1 = bp.way(Cell.G1, Cell.C5);
        Cell[] steps = new Cell[4];
        steps[0] = Cell.F2;
        steps[1] = Cell.E3;
        steps[2] = Cell.D4;
        steps[3] = Cell.C5;
        Assert.assertArrayEquals(steps1, steps);
    }

    @Test(expected = Exception.class)
    public void whenIMException() {
        Figure bp = new BishopBlack(Cell.G1);
        Cell[] steps1 = bp.way(Cell.G1, Cell.A1);
    }

    @Test(expected = Exception.class)
    public void whenOccupiedException() throws ImpossibleMoveException, OccupiedCellException, FigureNotFoundException {
        Logic l = new Logic();
        Figure bp = new BishopBlack(Cell.A1);
        Figure kb = new KingBlack(Cell.B2);
        l.add(bp);
        l.move(Cell.A1, Cell.C3);
        Cell[] steps1 = bp.way(Cell.A1, Cell.C3);
    }

    @Test(expected = Exception.class)
    public void whenNFException() throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic l = new Logic();
        Figure bp = new BishopBlack(Cell.A2);
        l.add(bp);
        l.move(Cell.A1, Cell.C3);

    }
}