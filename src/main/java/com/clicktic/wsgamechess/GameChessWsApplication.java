package com.clicktic.wsgamechess;

import Domain.Board.Exception.OutOfBoardException;
import Domain.Exception.WrongMoveException;
import Domain.Game;
import com.clicktic.wsgamechess.Player.Player;
import com.clicktic.wsgamechess.Presentation.Response.BoardJsonMapper;
import com.clicktic.wsgamechess.Presentation.Command.MoveCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;


@SpringBootApplication
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class GameChessWsApplication {
	private Player white;
	private Player black;
	private Player currentPlayer;
	private Game game;

	public static void main(String[] args) {
		SpringApplication.run(GameChessWsApplication.class, args);
	}

	@GetMapping("/start")
	public BoardJsonMapper start(
		@RequestParam(value = "p1", defaultValue = "Player 1") String firstPlayer,
		@RequestParam(value = "p2", defaultValue = "Player 2") String secondPlayer
	) {
		white = new Player(firstPlayer);
		black = new Player(secondPlayer);
		currentPlayer = white;

		try {
			game = new Game();
		} catch (OutOfBoardException ignored) {}

		return new BoardJsonMapper(this.game);
	}

	@PostMapping("/play")
	public BoardJsonMapper play(
			@RequestBody MoveCommand moveCommand
	) throws WrongMoveException, OutOfBoardException {
		game.Move(moveCommand.from, moveCommand.to);
		if (currentPlayer == white) {
			currentPlayer = black;
		} else {
			currentPlayer = white;
		}

		return new BoardJsonMapper(this.game);
	}
}
