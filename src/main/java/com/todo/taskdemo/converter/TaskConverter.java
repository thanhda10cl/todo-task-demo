package com.todo.taskdemo.converter;


import com.todo.taskdemo.dto.TaskDTO;
import com.todo.taskdemo.entity.TaskEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskConverter {
    public TaskEntity toEntity(TaskDTO dto){
        TaskEntity entity = new TaskEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    public TaskDTO toDTO(TaskEntity entity){
        TaskDTO dto = new TaskDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }
    public List<TaskDTO> toListDTO(List<TaskEntity> entities) {
        List<TaskDTO> dtos = new ArrayList<>();

        for (TaskEntity entity : entities) {
            TaskDTO dto = toDTO(entity);
            dtos.add(dto);
        }

        return dtos;
    }

}
