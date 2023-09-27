package dsb.intervju.sondre.BaccaratGame.controller;

import dsb.intervju.sondre.BaccaratGame.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @Operation(summary = "Play Baccarat Once", description = "Plays one round of Baccarat.")
    @GetMapping("/play")
    public String playBaccaratOnce() {
        return gameService.playBaccaratOnce();
    }

    @Operation(
            summary = "Play Baccarat",
            description = "Plays multiple rounds of Baccarat with options.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
    })
    @PostMapping("/play")
    public ResponseEntity<String> playBaccarat(
            @Parameter(
                    description = "Player name (default: 'Player').",
                    example = "Alice",
                    schema = @Schema(implementation = String.class)
            )
            @RequestParam(required = false, defaultValue = "Player") String playerName,
            @Parameter(
                    description = "Number of rounds to play.",
                    example = "5",
                    schema = @Schema(implementation = Integer.class)
            )
            @RequestParam int numberOfRounds,
            @Parameter(
                    description = "Whether to use a new deck for each round (default: false).",
                    example = "false",
                    schema = @Schema(implementation = Boolean.class)
            )
            @RequestParam(required = false, defaultValue = "false") boolean newDeckEachRound) {

        return gameService.playBaccarat(playerName, numberOfRounds, newDeckEachRound);
    }

}
