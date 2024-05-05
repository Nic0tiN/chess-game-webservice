package com.clicktic.wsgamechess.Application.Service;

import Domain.Board.Exception.OutOfBoardException;
import Domain.Exception.WrongMoveException;
import Domain.Game;
import com.clicktic.wsgamechess.Application.Command.MoveCommand;
import com.clicktic.wsgamechess.Domain.GameInstance;
import com.clicktic.wsgamechess.Application.GameRepository;
import lombok.SneakyThrows;

public class GameService {
    private final GameRepository repository;

    public GameService(GameRepository repository) {
        this.repository = repository;
    }

    @SneakyThrows
    public Game init(String gameId) {
        if (this.repository.findById(gameId).isEmpty()) {
            init(gameId, "white", "black");
        }

        GameInstance gameInstance = this.repository.findById(gameId).get();

        Game game = new Game();
        gameInstance.getMoves().forEach(move -> {
            try {
                game.Move(move.from, move.to);
            } catch (OutOfBoardException | WrongMoveException e) {
                throw new RuntimeException(e);
            }
        });

        return game;
    }

    @SneakyThrows
    public Game init(String gameId, String playerWhite, String playerBlack) {
        GameInstance gameInstance = GameInstance.of(playerWhite, playerBlack);
        gameInstance.setId(gameId);
        this.repository.save(gameInstance);

        return new Game();
    }

    public Game move(String gameId, MoveCommand moveCommand) throws WrongMoveException, OutOfBoardException {
        Game game = this.init(gameId);

        game.Move(moveCommand.from, moveCommand.to);

        GameInstance gameInstance = this.repository.findById(gameId).get();
        gameInstance.getMoves().add(moveCommand);
        this.repository.save(gameInstance);

        return game;
    }
}
