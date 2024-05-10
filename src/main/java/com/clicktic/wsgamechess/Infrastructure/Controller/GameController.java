package com.clicktic.wsgamechess.Infrastructure.Controller;

import Domain.Board.Exception.OutOfBoardException;
import Domain.Exception.WrongMoveException;
import Domain.Game;
import com.clicktic.wsgamechess.Application.Command.MoveCommand;
import com.clicktic.wsgamechess.Application.GameRepository;
import com.clicktic.wsgamechess.Application.Service.GameService;
import com.clicktic.wsgamechess.GameChessWsApplication;
import com.clicktic.wsgamechess.Infrastructure.Response.BoardJsonMapper;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {
    private static final Log LOG = LogFactory.getLog(GameChessWsApplication.class);
    private GameService gameService = null;

    @Autowired
    GameRepository gameRepository;

    @GetMapping("/init")
    public BoardJsonMapper init(
            HttpSession session
    ) {
        String gameId = session.getId();
        Game game = this.getService().init(gameId);

        LOG.info(String.format("Game ID %s (re)initiated.", gameId));

        return new BoardJsonMapper(game);
    }

    @GetMapping("/start")
    public BoardJsonMapper start(
            @RequestParam(value = "p1", defaultValue = "Player 1") String firstPlayer,
            @RequestParam(value = "p2", defaultValue = "Player 2") String secondPlayer,
            HttpSession session
            ) {
        String gameId = session.getId();
        Game game = this.getService().init(gameId, firstPlayer, secondPlayer);

        LOG.info(String.format("Game initiated with ID %s.", gameId));

        return new BoardJsonMapper(game);
    }

    @PostMapping("/play")
    public BoardJsonMapper play(
            @RequestBody MoveCommand moveCommand,
            HttpSession session
    ) throws WrongMoveException, OutOfBoardException {
        String gameId = session.getId();
        Game game = this.getService().move(gameId, moveCommand);

        LOG.info(String.format("Game ID %s - Player moved from %s to %s", gameId, moveCommand.from, moveCommand.to));

        return new BoardJsonMapper(game);
    }

    private GameService getService() {
        if (gameService == null) {
            gameService = new GameService(gameRepository);
        }

        return gameService;
    }
}
