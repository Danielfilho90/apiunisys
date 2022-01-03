package com.daniel.unisystest.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.daniel.unisystest.dto.JobRequest;
import com.daniel.unisystest.dto.JobResponse;
import com.daniel.unisystest.entity.Job;
import com.daniel.unisystest.entity.Task;
import com.daniel.unisystest.exceptions.business.JobNameAlreadyExistsExpception;
import com.daniel.unisystest.exceptions.business.JobNotFoundException;
import com.daniel.unisystest.mappers.JobMapper;
import com.daniel.unisystest.repository.JobRepository;
import com.daniel.unisystest.repository.specification.JobEspecification;

@Service
public class JobService {

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private SessionFactory factory;

	@Autowired
	private JobMapper jobMapper;

	@Transactional(readOnly = true)
	public List<JobResponse> findAll(String name) {

		Specification<Job> jobSpecification = JobEspecification.findAll(name);

		List<Job> jobs = jobRepository.findAll(jobSpecification);

		return jobMapper.mapList(jobs);

	}

	@Transactional(readOnly = true)
	public JobResponse findById(final Long id) {

		Job job = findJobById(id);

		return jobMapper.map(job);
	}

	private Job findJobById(final Long id) {
		return jobRepository.findById(id)
				.orElseThrow(() -> new JobNotFoundException(String.format("Job NOT FOUND %s", id)));
	}

	@Transactional
	public JobResponse update(Long id, Job job) {

		// TODO VALIDATED IF UPDATED JOB NAME

		findJobById(id);

		job.setId(id);

		Job savedJob = jobRepository.save(job);
		
		factory.getCurrentSession().clear();
		
		factory.getCurrentSession().flush();

		return jobMapper.map(savedJob);

	}

	@Transactional
	public JobResponse save(JobRequest job) {

		validateJobByName(job);

		validateJobIfParentJobExists(job.getParentJob());

		taskService.findByTasksId(job.getTasks());

		Job map = jobMapper.map(job);

		Job save = jobRepository.save(map);

		return jobMapper.map(save);
	}


	private void validateJobIfParentJobExists(Job parentJob) {
		if (parentJob != null && parentJob.getId() != null) {
			findJobById(parentJob.getId());
		}

	}

	private void validateJobByName(JobRequest job) {
		Optional<Job> foundJob = jobRepository.findByName(job.getName());

		if (foundJob.isPresent()) {
			throw new JobNameAlreadyExistsExpception("JOB NAME ALREADY EXISTS");
		}
	}

	@Transactional
	public void delete(Long id) {
		findJobById(id);

		jobRepository.deleteById(id);

	}

}