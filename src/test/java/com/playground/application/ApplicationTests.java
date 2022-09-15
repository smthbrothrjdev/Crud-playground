package com.playground.application;

import com.playground.application.lesson.LessonRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {

    @Autowired
    MockMvc mvc;

    @Autowired
    LessonRepo lessonRepo;


    @Test
    @Transactional
    @Rollback
    void testLessonPosts() throws Exception {

        mvc.perform(post("/").content("""
                                {
                                "name":"BJ32",
                                "deliveredOn":"Jan 20 1990"
                                }
                                """))
                .andExpect(jsonPath("$.name", equalTo("BJ32")));


        mvc.perform(get("/").contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$[0].name", equalTo("BJ32")));
    }


}
