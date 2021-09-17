package com.revature;

import com.revature.repository.LikeDao;
import com.revature.service.LikeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class LikeServiceTest {
    LikeService likeService;

    @Mock
    LikeDao likeDao;

    @BeforeEach
    void setUp(){
        this.likeService = new LikeService(likeDao);
    }

}
