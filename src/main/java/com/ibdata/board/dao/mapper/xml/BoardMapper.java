package com.ibdata.board.dao.mapper.xml;

import com.ibdata.board.dto.BoardDTO;

public interface BoardMapper {

    BoardDTO findById(String id);
}
