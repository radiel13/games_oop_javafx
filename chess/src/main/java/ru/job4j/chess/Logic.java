package ru.job4j.chess;

import ru.job4j.chess.ChessExceptions.ImpossibleMoveException;
import ru.job4j.chess.ChessExceptions.OccupiedCellException;
import ru.job4j.chess.ChessExceptions.FigureNotFoundException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.util.Arrays;
import java.util.Optional;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) throws OccupiedCellException, ImpossibleMoveException, FigureNotFoundException {
        boolean rst = false;
        int index = this.findBy(source);
        if (index != -1) {
            Cell[] steps;
            try {
              steps = this.figures[index].way(source, dest);
            } catch (IllegalStateException e) {
                throw new ImpossibleMoveException(
                        String.format("Could not move by diagonal from %s to %s", source, dest));
            }

            if (free(steps)) {
                if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                    rst = true;
                    this.figures[index] = this.figures[index].copy(dest);
                }

            }

        } else {
            throw new FigureNotFoundException("No figures");
        }
        return rst;

    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) throws FigureNotFoundException {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {

            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    @Override
    public String toString() {
        return "Logic{" +
                "figures=" + Arrays.toString(this.figures) +
                '}';
    }

    private boolean free(Cell[] steps) throws OccupiedCellException, FigureNotFoundException {
        for (int index = 0; index <steps.length; index++) {
            if (findBy(steps[index]) != -1) {
                throw new OccupiedCellException(String.format("The way is blocked."));
            }

        }
        return true;
    }
}
