package com.daniel.unisystest.mappers;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.util.CollectionUtils;

import com.daniel.unisystest.dto.JobRequest;
import com.daniel.unisystest.dto.JobResponse;
import com.daniel.unisystest.entity.Job;
import com.daniel.unisystest.entity.Task;

@Mapper(componentModel = "spring", uses = { TaskMapper.class })
public interface JobMapper {

	JobResponse map(Job source);

	@Mapping(target = "tasks", ignore = true)
	Job map(JobRequest source);

	List<JobResponse> mapList(List<Job> source);

	@AfterMapping
	default void configure(JobRequest source, @MappingTarget Job entity) {

		List<Long> tasks = source.getTasks();

		if (!CollectionUtils.isEmpty(tasks)) {
			tasks.forEach(task -> {

				Task taskEntity = new Task();
				taskEntity.setId(task);
				entity.addTask(taskEntity);
			});
		}

	}

}
