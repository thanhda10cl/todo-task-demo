package com.todo.taskdemo.service;

import com.todo.taskdemo.dto.TaskDTO;
import com.todo.taskdemo.entity.TaskEntity;


import java.util.List;

public interface ITaskService {
    List<TaskDTO> getAllTask();
    TaskEntity save(TaskDTO taskDTO);
    void delete(Long id);

    TaskDTO getTask(Long id);
}
