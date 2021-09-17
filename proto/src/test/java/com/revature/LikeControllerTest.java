package com.revature;

import com.revature.controller.LikeController;
import com.revature.service.LikeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class LikeControllerTest {
    LikeController likeController;

    @Mock
    LikeService likeService;

    @BeforeEach
    void setUp(){
        this.likeController = new LikeController(likeService);
    }
}
