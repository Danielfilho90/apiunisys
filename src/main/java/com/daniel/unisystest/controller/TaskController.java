package com.daniel.unisystest.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.unisystest.dto.TaskRequest;
import com.daniel.unisystest.dto.TaskResponse;
import com.daniel.unisystest.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	
	@PostMapping
	public ResponseEntity<TaskResponse> save(@RequestBody TaskRequest request) {

		TaskResponse save = taskService.save(request);

		URI uri = URI.create(save.getId().toString());

		return ResponseEntity.created(uri).body(save);
	}

}
