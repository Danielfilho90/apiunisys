package com.daniel.unisystest.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.unisystest.dto.TaskRequest;
import com.daniel.unisystest.dto.TaskResponse;
import com.daniel.unisystest.entity.Task;
import com.daniel.unisystest.exceptions.business.TaskNotFoundException;
import com.daniel.unisystest.mappers.TaskMapper;
import com.daniel.unisystest.repository.TaskRepository;

@Service
public class TaskService {

	private static final String TASK_NOT_FOUND = "TASK NOT FOUND";

	@Autowired
	private TaskMapper taskMapper;

	@Autowired
	private TaskRepository taskRepository;

	@Transactional
	public TaskResponse save(TaskRequest request) {

		Task map = taskMapper.map(request);

		Task save = taskRepository.save(map);

		return taskMapper.map(save);
	}

	public void findByTasksId(List<Long> tasksIds) {

		List<Task> tasks = taskRepository.findAllById(tasksIds);

		if (tasks.size() != tasksIds.size()) {
			throw new TaskNotFoundException(TASK_NOT_FOUND);
		}

	}

}
