package com.todo.taskdemo.dto;


import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractDTO {
    private Long id;
//    private String updatedDate;
//    private String createdDate;
}
