package chess.controller;

import chess.entity.RoomEntity;
import chess.service.CommandService;
import chess.service.RoomService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RoomController {

    private final RoomService roomService;
    private final CommandService commandService;

    public RoomController(RoomService roomService, CommandService commandService) {
        this.roomService = roomService;
        this.commandService = commandService;
    }

    @GetMapping
    public String index(Model model, @RequestParam(required = false) String message) {
        model.addAttribute("message", message);
        model.addAttribute("rooms", roomService.findAllRooms());
        return "index";
    }

    @GetMapping("/form/room-create")
    public String createRoomPage() {
        return "createRoom";
    }

    @PostMapping("/room")
    public String createRoom(@RequestParam String name, @RequestParam String password) {
        roomService.create(name, password);
        return "redirect:/";
    }

    @GetMapping("/form/room-delete/{roomId}")
    public ModelAndView deleteRoomPage(@PathVariable Long roomId) {
        ModelAndView modelAndView = new ModelAndView("deleteRoom");
        modelAndView.addObject("id", roomId);
        commandService.checkRunning(roomId);
        return modelAndView;
    }

    @PostMapping("/room/{roomId}")
    public String deleteRoom(RedirectAttributes redirectAttributes, @PathVariable Long roomId, @RequestParam String password) {
        RoomEntity roomEntity = roomService.findById(roomId);
        roomService.delete(roomId, password);
        redirectAttributes.addAttribute("message", roomEntity.getName() +
                "방이 삭제되었습니다.");
        return "redirect:/";
    }
}
