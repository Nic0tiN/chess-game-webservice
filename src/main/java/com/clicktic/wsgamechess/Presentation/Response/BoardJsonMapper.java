package com.clicktic.wsgamechess.Presentation.Response;

import Domain.Board.Exception.OutOfBoardException;
import Domain.Board.Position;
import Domain.Game;

import java.util.ArrayList;
import java.util.List;

public class BoardJsonMapper {
    private final CellJsonMapper[] board;

    public BoardJsonMapper(Game game) {
        CellJsonMapper[] results = {};

        List<CellJsonMapper> jsonResult = new ArrayList<>();
        for(int iCol = 0; iCol < game.getFigures().length; iCol++) {
            for (int iRow = 0; iRow < game.getFigures()[iCol].length; iRow++) {
                Position pos;
                try {
                    pos = new Position(iCol, iRow);
                    jsonResult.add(new CellJsonMapper(pos, game.getBoard().getFigureAtPosition(pos)));
                } catch (OutOfBoardException ignored) {}
            }
        }

        this.board = jsonResult.toArray(results);
    }

    public CellJsonMapper[] getBoard() {
        return board;
    }
}
