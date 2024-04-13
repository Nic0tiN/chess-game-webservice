package com.clicktic.wsgamechess.Presentation.Response;

import Domain.Figure.Figure;

import java.util.Optional;

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

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }
}
