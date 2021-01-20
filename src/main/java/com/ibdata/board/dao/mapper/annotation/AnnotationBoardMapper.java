package com.ibdata.board.dao.mapper.annotation;

import com.ibdata.board.dto.BoardDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface AnnotationBoardMapper {

    @Select("""
            SELECT *
              FROM BOARD
             WHERE BOARD_ID = #{boardId}
            """)
    BoardDTO findById(@Param("boardId") String boardId);
}
