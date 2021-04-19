package chess.controller;

import chess.domain.command.Commands;
import chess.domain.dto.MoveResponseDto;
import chess.domain.exception.DataException;
import chess.domain.dto.MoveDto;
import chess.service.SpringChessService;
import chess.view.ModelView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/play")
public class ChessController {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final SpringChessService springChessService;

    public ChessController(SpringChessService springChessService) {
        this.springChessService = springChessService;
    }

    @GetMapping("")
    public String play(Model model) throws DataException {
        model.addAllAttributes(ModelView.startResponse(springChessService.loadHistory()));
        return "play";
    }

    @GetMapping("/new")
    public String playNewGameWithNoSave(Model model) {
        model.addAllAttributes(ModelView.newGameResponse(springChessService.initialGameInfo()));
        return "chessGame";
    }

    @GetMapping("/{name}/new")
    public String playNewGameWithSave(Model model, @PathVariable String name) throws DataException {
        model.addAllAttributes(ModelView.newGameResponse(
                springChessService.initialGameInfo(),
                springChessService.addHistory(name)
        ));
        return "chessGame";
    }

    @GetMapping("/continue")
    public String continueGame(Model model, @RequestParam("name") String name) throws DataException {
        final String id = springChessService.getIdByName(name);
        model.addAllAttributes(ModelView.commonResponseForm(springChessService.continuedGameInfo(id), id));
        return "chessGame";
    }

    @GetMapping("/end")
    public String endGame() {
        return "play";
    }

    @PostMapping("/move")
    @ResponseBody
    public MoveResponseDto move(@RequestBody MoveDto moveDto) {
        String command = makeMoveCmd(moveDto.getSource(), moveDto.getTarget());
        String historyId = moveDto.getGameId();

        try {
            springChessService.move(historyId, command, new Commands(command));
            return ModelView.moveResponse(springChessService.continuedGameInfo(historyId), historyId);
        } catch (IllegalArgumentException | SQLException e) {
            return new MoveResponseDto(e.getMessage());
        }
    }

    private String makeMoveCmd(String source, String target) {
        return String.join(" ", "move", source, target);
    }
}