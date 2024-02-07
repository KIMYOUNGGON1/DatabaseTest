package org.databasetest.controller;

import org.databasetest.model.Board;
import org.databasetest.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/getList")
    public String list(Model model) {
        List<Board> boards = boardService.findAll();
        model.addAttribute("boards", boards);
        return "board/list";
    }

    @GetMapping("/add")
    public String addBoardForm(Model model) {
        model.addAttribute("board", new Board());
        return "board/add";
    }

    @PostMapping("/add")
    public String addBoard(@ModelAttribute Board board) {
        boardService.insert(board);
        return "redirect:/board/getList";
    }

    @GetMapping("/edit/{id}")
    public String editBoardForm(@PathVariable("id") Long id, Model model) {
        Board board = boardService.findById(id);
        model.addAttribute("board", board);
        return "board/edit";
    }

    @PostMapping("/update")
    public String updateBoard(@ModelAttribute Board board) {
        boardService.update(board);
        return "redirect:/board/getList";
    }

    @GetMapping("/delete/{id}")
    public String deleteBoard(@PathVariable("id") Long id) {
        boardService.delete(id);
        return "redirect:/board/getList";
    }

    @GetMapping("/view/{id}")
    public String viewBoard(@PathVariable("id") Long id, Model model) {
        Board board = boardService.findById(id);
        model.addAttribute("board", board);
        return "board/view";
    }
}
