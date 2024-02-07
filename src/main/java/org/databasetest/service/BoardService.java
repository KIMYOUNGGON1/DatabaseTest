package org.databasetest.service;

import org.databasetest.mapper.BoardMapper;
import org.databasetest.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardMapper boardMapper;

    @Autowired
    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    public List<Board> findAll() {
        return boardMapper.findAll();
    }

    public Board findById(Long id) {
        return boardMapper.findById(id);
    }

    public void insert(Board board) {
        boardMapper.insert(board);
    }

    public void update(Board board) {
        boardMapper.update(board);
    }

    public void delete(Long id) {
        boardMapper.delete(id);
    }

}
