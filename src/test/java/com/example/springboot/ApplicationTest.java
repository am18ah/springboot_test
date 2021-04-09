package com.example.springboot;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test

    void test() throws Exception{
        //posts the post
        mockMvc.perform( MockMvcRequestBuilders.post("/api?post_input_text=testing")).andReturn();
        //deletes
        mockMvc.perform( MockMvcRequestBuilders.post("/delete?post_text=testing")).andReturn();
        //checks history
        mockMvc.perform( MockMvcRequestBuilders.get("/history").contentType(MediaType.ALL)).andExpect(content().string(not(containsString("testing"))));
    }

    @Test
    void deleteCapTest() throws  Exception{

        mockMvc.perform( MockMvcRequestBuilders.post("/api?post_input_text=testing")).andReturn();

        mockMvc.perform( MockMvcRequestBuilders.post("/delete?post_text=TESTING").contentType(MediaType.ALL)).andExpect(content().string(containsString("The requested post has been deleted from history.")));


    }


}