package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TaskMapperTest {
    @Autowired
    TaskMapper taskMapper;

    Long id = 1L;
    String title = "testTitle1";
    String content = "testContent1";
    TaskDto taskDto = new TaskDto(id, title, content);
    Task task = new Task(id, title, content);

    @Test
    void mapToTask() {
        // given
        // when
        Task mappedTask = taskMapper.mapToTask(taskDto);
        // then
        assertThat(mappedTask).isNotNull();
        assertThat(mappedTask.getId()).isNotNull();
        assertThat(mappedTask.getId()).isEqualTo(id);
        assertThat(mappedTask.getTitle()).isEqualTo(title);
        assertThat(mappedTask.getContent()).isEqualTo(content);
    }

    @Test
    void mapToTaskDto() {
        //when
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        // then
        assertThat(taskDto).isNotNull();
        assertThat(taskDto.getId()).isNotNull();
        assertThat(taskDto.getId()).isEqualTo(id);
        assertThat(taskDto.getTitle()).isEqualTo(title);
        assertThat(taskDto.getContent()).isEqualTo(content);

    }

    @Test
    void mapToTaskDtoList() {
        //when
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(List.of(task));
        // then
        for (TaskDto taskDto : taskDtoList){
            assertThat(taskDto).isNotNull();
            assertThat(taskDto.getId()).isNotNull();
            assertThat(taskDto.getId()).isEqualTo(id);
            assertThat(taskDto.getTitle()).isEqualTo(title);
            assertThat(taskDto.getContent()).isEqualTo(content);
        }
    }
}