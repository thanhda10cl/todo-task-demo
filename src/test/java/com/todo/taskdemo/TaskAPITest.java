package com.todo.taskdemo;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.taskdemo.controller.TaskAPI;
import com.todo.taskdemo.dto.TaskDTO;
import com.todo.taskdemo.service.Impl.TaskService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TaskAPITest {
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllTask() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/task")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetById() throws Exception {
        Long taskId = 13L;
        mockMvc.perform(MockMvcRequestBuilders.get("/task/{id}", taskId)
                        .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk());
    }
    @Test
    public void testCreate() throws Exception {
        TaskDTO taskDTO = new TaskDTO("Test Task 1", "New Task");
        mockMvc.perform(MockMvcRequestBuilders.post("/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(taskDTO)))
                .andExpect(status().isOk());
    }
    @Test
    void testUpdate() throws Exception {
        TaskDTO taskDTO = new TaskDTO(1L,"Updated name", "Updated description");

        mockMvc.perform(MockMvcRequestBuilders.put("/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(taskDTO)))
                .andExpect(status().isOk());
    }
    @Test
    void testDelete() throws Exception {
        Long taskId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.delete("/task/{taskId}", taskId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(taskId)))
                .andExpect(status().isOk());
    }
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
