package com.ibdata.board.dto;

import lombok.Value;

@Value
public class PostCommandDTO {

    private String postId;
    private String title;
    private String contents;
    private String writer;
    private String password;
}
