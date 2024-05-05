package com.clicktic.wsgamechess.Infrastructure.Response;

import Domain.Board.Position;
import Domain.Figure.Figure;
import lombok.Getter;

import java.util.Optional;

@Getter
public class CellJsonMapper {
    private final String position;
    private final FigureJsonMapper figure;

    public CellJsonMapper(Position position, Optional<Figure> figure) {
        this.position = position.toString();
        this.figure = new FigureJsonMapper(figure);
    }
}
