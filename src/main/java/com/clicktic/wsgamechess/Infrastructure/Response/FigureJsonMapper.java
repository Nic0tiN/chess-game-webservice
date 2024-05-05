package com.clicktic.wsgamechess.Infrastructure.Response;

import Domain.Figure.Figure;
import lombok.Getter;

import java.util.Optional;

@Getter
public class FigureJsonMapper {
    private final String type;
    private final String color;

    public FigureJsonMapper(Optional<Figure> figure) {
        if (figure.isPresent()) {
            this.type = figure.get().figure.toString();
            this.color = figure.get().color.toString();
        } else {
            this.color = null;
            this.type = null;
        }
    }
}
