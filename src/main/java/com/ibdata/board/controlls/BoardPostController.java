package com.ibdata.board.controlls;

import com.ibdata.board.dto.PostCommandDTO;
import com.ibdata.board.services.PostCommandService;
import com.ibdata.board.services.PostEventService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/boardPost")
public class BoardPostController {

    private PostCommandService postCommandService;
    private PostEventService postEventService;

    public BoardPostController(PostCommandService postCommandService, PostEventService postEventService) {
        this.postCommandService = postCommandService;
        this.postEventService = postEventService;
    }

    @PostMapping
    @RequestMapping("/registrationPost")
    public CompletableFuture registrationPost(@RequestBody PostCommandDTO postCommandDTO) {
        return postCommandService.registrationPost(postCommandDTO);
    }
}
