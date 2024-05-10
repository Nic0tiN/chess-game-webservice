package com.clicktic.wsgamechess.Application.Command;

public class MoveCommand {
    public final String from;
    public final String to;

    public MoveCommand(String from, String to) {
        this.from = from;
        this.to = to;
    }
}
