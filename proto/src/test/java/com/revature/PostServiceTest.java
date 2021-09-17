package com.revature;

import com.revature.models.Post;
import com.revature.repository.PostDao;
import com.revature.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
    PostService postService;

    @Mock
    PostDao postDao;

    @BeforeEach
    void setUp(){
        this.postService = new PostService(postDao);
    }

    @Test
    void selectAllPosts(){
        //Assign
        List<Post> expectedResult = new ArrayList<>();
        expectedResult.add(new Post(1, null, "Hello World!", 1));
        Mockito.when(postDao.findAll()).thenReturn(expectedResult);

        //Act
        List<Post> actualResult = this.postService.selectAllPosts();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void selectPostByUserId(){
        //Assign
        Integer userIdFk = 1;
        List<Post> expectedResult = new ArrayList<>();
        expectedResult.add(new Post(1, null, "UserIdFk 1 first post.", 1));
        Mockito.when(postDao.findPostByUserIdFk(userIdFk)).thenReturn(expectedResult);

        //Act
        List<Post> actualResult = this.postService.selectPostByUserId(userIdFk);

        //Assert
        assertEquals(expectedResult, actualResult);
    }
}
