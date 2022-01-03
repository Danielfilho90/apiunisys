package com.daniel.unisystest.dto;

import java.util.List;

import com.daniel.unisystest.entity.Job;

public class JobRequest {

	private String name;

	private Boolean active;

	private Job parentJob;

	private List<Long> tasks;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Job getParentJob() {
		return parentJob;
	}

	public void setParentJob(Job parentJob) {
		this.parentJob = parentJob;
	}

	public List<Long> getTasks() {
		return tasks;
	}

	public void setTasks(List<Long> tasks) {
		this.tasks = tasks;
	}

}
