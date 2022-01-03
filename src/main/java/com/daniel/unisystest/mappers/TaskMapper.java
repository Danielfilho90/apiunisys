package com.daniel.unisystest.mappers;

import java.time.LocalDate;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import com.daniel.unisystest.dto.TaskRequest;
import com.daniel.unisystest.dto.TaskResponse;
import com.daniel.unisystest.entity.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {
	
	Task map(TaskRequest source);
	
	TaskResponse map(Task source);
	
	@AfterMapping
	default void configure(@MappingTarget Task task) {
		task.setCreatedAt(LocalDate.now());
	}

}	
