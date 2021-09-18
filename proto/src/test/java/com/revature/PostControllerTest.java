package com.revature;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.revature.controller.PostController;
import com.revature.models.JsonResponse;
import com.revature.models.Post;
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

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Tests associated with the PostController class.
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PostControllerTest {
    PostController postController;

    @Mock
    PostService postService;

    @BeforeEach
    void setUp() {
        this.postController = new PostController(postService);
    }

    /**
     * Test to compare the expected list of posts returned to the actual list of posts returned.
     */
    @Test
    void selectAllPosts(){
        //Assign
        List<Post> postList = new ArrayList<>();
        postList.add(new Post(1, null, "Here's a new post.", 1));
        JsonResponse expectedResult = new JsonResponse(true, "All posts obtained.", postList);

        //Mock post service
        Mockito.when(postService.selectAllPosts()).thenReturn(postList);

        //Act
        JsonResponse actualResult = this.postController.getAllPosts();

        //Assert
        assertEquals(expectedResult, actualResult);
    }
}
