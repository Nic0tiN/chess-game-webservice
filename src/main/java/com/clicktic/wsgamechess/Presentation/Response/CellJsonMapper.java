package com.clicktic.wsgamechess.Presentation.Response;

import Domain.Board.Position;
import Domain.Figure.Figure;

import java.util.Optional;

public class CellJsonMapper {
    private final String position;
    private final FigureJsonMapper figure;

    public CellJsonMapper(Position position, Optional<Figure> figure) {
        this.position = position.toString();
        this.figure = new FigureJsonMapper(figure);
    }

    public String getPosition() {
        return this.position;
    }

    public FigureJsonMapper getFigure() {
        return figure;
    }
}
