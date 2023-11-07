package com.todo.taskdemo.controller;


import com.todo.taskdemo.dto.TaskDTO;
import com.todo.taskdemo.entity.TaskEntity;
import com.todo.taskdemo.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TaskAPI {
    @Autowired
    private ITaskService taskService;

    @GetMapping(value = "/task")
    public ResponseEntity<List<TaskDTO>> getAllTask() {
        List<TaskDTO> listTask = taskService.getAllTask();
        return ResponseEntity.ok().body(listTask);
    }
    @GetMapping(value = "/demo")
    public ResponseEntity<String> demo() {
        return ResponseEntity.ok().body("cicd dc oiii");
    }

    @GetMapping(value = "/task/{id}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable("id") Long id) {
        TaskDTO taskDTO = taskService.getTask(id);
        return ResponseEntity.ok().body(taskDTO);
    }

    @PostMapping(value = "/task")
    public ResponseEntity<String> create(@RequestBody TaskDTO model) {
        taskService.save(model);
        return ResponseEntity.ok().body("Tạo task thành công");
    }

    @PutMapping(value = "/task")
    public ResponseEntity<String> update(@RequestBody TaskDTO model) {
        taskService.save(model);
        return ResponseEntity.ok().body("Update task thành công");
    }
    @DeleteMapping(value = "/task/{id}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        taskService.delete(id);
        return ResponseEntity.ok("{\"status\": \"Delete Successful\"}");
    }
}
