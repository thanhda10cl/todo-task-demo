package com.todo.taskdemo.service.Impl;


import com.todo.taskdemo.converter.TaskConverter;
import com.todo.taskdemo.dto.TaskDTO;
import com.todo.taskdemo.entity.TaskEntity;
import com.todo.taskdemo.repository.TaskRepository;
import com.todo.taskdemo.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TaskService implements ITaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskConverter taskConverter;

    public List<TaskDTO> getAllTask() {
        return taskConverter.toListDTO(taskRepository.findAll());
    }
    public TaskDTO getTask(Long id) {
        TaskEntity task = taskRepository.findById(id).orElse(null);
        assert task != null;
        return taskConverter.toDTO(task);
    }

    public TaskEntity save(TaskDTO taskDTO) {
        return taskRepository.save(taskConverter.toEntity(taskDTO));
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
