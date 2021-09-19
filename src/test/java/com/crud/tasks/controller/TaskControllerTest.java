package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {
    @InjectMocks
    TaskController taskController;
    @Mock
    DbService dbService;
    @Mock
    TaskMapper taskMapper;

    Long id = 1L;
    String title = "testTitle1";
    String content = "testContent1";
    TaskDto taskDto = new TaskDto(id, title, content);
    Task task = new Task(id, title, content);

    @Test
    void getTasks() {
        // given
        when(taskController.getTasks()).thenReturn(List.of(taskDto));
        // when
        List<TaskDto> result = taskController.getTasks();
        // then
        assertThat(result).isEqualTo(List.of(taskDto));
    }

    @Test
    void getTask() throws TaskNotFoundException{
        //given
        when(dbService.getTask(id)).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        //when
        var result = taskController.getTask(id);
        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getTitle()).isEqualTo(title);
        assertThat(result.getContent()).isEqualTo(content);
    }

    @Test
    void updateTask() {
        //given
        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        // when
        var result = taskController.updateTask(taskDto);
        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getTitle()).isEqualTo(title);
        assertThat(result.getContent()).isEqualTo(content);

    }

    @Test
    void createTask() {
        //given
        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        //when
        var result = taskController.createTask(taskDto);
        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getTitle()).isEqualTo(title);
        assertThat(result.getContent()).isEqualTo(content);

    }
}