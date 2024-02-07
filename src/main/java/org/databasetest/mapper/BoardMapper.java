package org.databasetest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.databasetest.model.Board;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface BoardMapper {
    List<Board> findAll();

    Board findById(Long id);
    void insert(Board board);

    void update(Board board);

    void delete(Long id);
}
