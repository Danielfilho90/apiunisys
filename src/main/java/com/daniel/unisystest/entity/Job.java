package com.daniel.unisystest.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_job")
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;

	private Boolean active;

    @ManyToMany(fetch =  FetchType.LAZY)
    @JoinTable(
            name = "tb_task_job", 
            joinColumns = { @JoinColumn(name = "job_id") }, 
            inverseJoinColumns = { @JoinColumn(name = "task_id") }
        )
	private List<Task> tasks = new ArrayList<>();

	@OneToOne
	@JoinColumn(name = "parentjob_id")
	private Job parentJob;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	
	public List<Task> getTasks() {
		return tasks;
	}

	public Job getParentJob() {
		return parentJob;
	}

	public void setParentJob(Job parentJob) {
		this.parentJob = parentJob;
	}

	public void addTask(Task task) {
		this.tasks.add(task);
		task.getJobs().add(this);

	}

}
