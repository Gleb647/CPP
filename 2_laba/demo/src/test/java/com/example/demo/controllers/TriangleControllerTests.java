package com.example.demo.controllers;

import com.example.demo.TriangleController.TriangleController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TriangleController.class)
public class TriangleControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testPerimeter() throws Exception{
        mvc.perform(get("/volume/perimeter?first=3&second=4&third=5"))
                .andExpect(status().isOk())
                .andExpect(content().string("12"));
    }

    @Test
    public void testPerimeterWithoutParam() throws Exception{
        mvc.perform(get("/volume/perimeter?&second=4&third=5"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testPerimeterWithBadParams() throws Exception{
        mvc.perform(get("/volume/perimeter?&first=30&second=4&third=5"))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void testSquare() throws Exception{
        mvc.perform(get("/volume/square?first=3&second=4&third=5"))
                .andExpect(status().isOk())
                .andExpect(content().string("6.0"));
    }
}
