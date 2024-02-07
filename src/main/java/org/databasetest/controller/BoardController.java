package org.databasetest.controller;

import lombok.extern.slf4j.Slf4j;
import org.databasetest.model.Board;
import org.databasetest.model.UserInfo;
import org.databasetest.service.BoardService;
import org.databasetest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class BoardController {
    private final BoardService boardService;
    private final UserService userService;

    @Autowired
    public BoardController(BoardService boardService, UserService userService) {
        this.boardService = boardService;
        this.userService = userService;
    }

    @GetMapping ( "/" )
    public String getHome() {
        return "index";
    }

    @GetMapping ( "/getUserInfo" )
    public String getUserInfo(Model model) {
        List<UserInfo> allUserInfo = userService.findAllUserInfo();
        model.addAttribute("userInfo", allUserInfo);
        return "member/userInfo";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("userInfo", new UserInfo());
        return "member/login";
    }

    @PostMapping("/login")
    public String performLogin(@RequestParam String username, @RequestParam String password, Model model) {
        UserInfo user = userService.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("loginError", "Invalid username or password");
            return "member/login";
        } else {
            model.addAttribute("username", username);
            return "redirect:/getList";
        }
    }
    @GetMapping("/register")
    public String registrationForm(Model model) {
        model.addAttribute("userInfo", new UserInfo());
        return "member/register";
    }

    @PostMapping("/register")
    public String performRegistration(@ModelAttribute UserInfo userInfo, Model model) {
        UserInfo existingUser = userService.findByUsername(userInfo.getUsername());
        if (existingUser == null) {
            userService.registerUser(userInfo);
            return "redirect:/login";
        } else {
            model.addAttribute("registrationError", "Username already exists");
            return "member/register";
        }
    }

    @GetMapping("/getList")
    public String list(Model model) {
        List<Board> boards = boardService.findAll();
        model.addAttribute("boards", boards);
        return "board/list";
    }


    /**
     * Create Board
     */

    @GetMapping ( "/addBoard" )
    public String addBoardForm(Model model) {
        model.addAttribute("board", new Board());
        return "board/add";
    }

    @PostMapping ( "/addBoard" )
    public String addBoard(@ModelAttribute Board board) {
        boardService.insert(board);
        return "redirect:/getList";
    }

    /**
     * Edit Board
     */

    @GetMapping ( "/editBoard/{id}" )
    public String editBoardForm(@PathVariable ( "id" ) Long id, Model model) {
        Board board = boardService.findById(id);
        model.addAttribute("board", board);
        return "board/edit";
    }

    @PostMapping ( "/updateBoard" )
    public String updateBoard(@ModelAttribute Board board) {
        boardService.update(board);
        return "redirect:/getList";
    }

    /**
     * Delete Board
     */

    @GetMapping ( "/deleteBoard/{id}" )
    public String deleteBoard(@PathVariable ( "id" ) Long id) {
        boardService.delete(id);
        return "redirect:/getList";
    }
}
