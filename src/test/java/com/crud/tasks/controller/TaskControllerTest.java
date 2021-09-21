package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    TaskController taskController;

    Long id = 1L;
    String title = "testTitle1";
    String content = "testContent1";
    TaskDto taskDto = new TaskDto(id, title, content);

    @Test
    void shouldGetEmptyTaskList() throws Exception {
        // given
        when(taskController.getTasks()).thenReturn(List.of());
        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/task/getTasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));

    }

    @Test
    void shouldGetTaskList() throws Exception {
        // given
        when(taskController.getTasks()).thenReturn(List.of(taskDto));
        // when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/task/getTasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.equalTo(id.intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.equalTo(title)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.equalTo(content)));
    }

    @Test
    void shouldGetTaskOfId() throws Exception {
        //given
        when(taskController.getTask(anyLong())).thenReturn(taskDto);
        // when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/task/getTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("taskId", id.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(id.intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.equalTo(content)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.equalTo(title)));
    }

    @Test
    void shouldDeleteTaskOfId() throws Exception {
        // given
        doNothing().when(taskController).deleteTask(anyLong());
        // when & then
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/task/deleteTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("taskId", id.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldUpdateTask() throws Exception {
        // given
        when(taskController.updateTask(any(TaskDto.class))).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonStringContent = gson.toJson(taskDto);
        // when & then
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/task/updateTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonStringContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(id.intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.equalTo(title)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.equalTo(content)));
    }

    @Test
    void shouldCreateTask() throws Exception {
        // given
        when(taskController.createTask(any(TaskDto.class))).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonStringContent = gson.toJson(taskDto);
        // when & then
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/task/createTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonStringContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(id.intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.equalTo(title)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.equalTo(content)));
    }
}