package com.ibdata.board.dao.mapper.annotation;

import com.ibdata.board.dto.BoardDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface BoardMapper {

    @Select("""
            SELECT *
              FROM BOARD
             WHERE ID = #{id}
            """)
    BoardDTO findById(@Param("id") String id);
}
