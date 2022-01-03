package com.daniel.unisystest.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.unisystest.dto.JobRequest;
import com.daniel.unisystest.dto.JobResponse;
import com.daniel.unisystest.entity.Job;
import com.daniel.unisystest.service.JobService;

@RestController
@RequestMapping("/jobs" )
public class JobController {
	
	@Autowired
	private JobService jobService;

	@GetMapping
	public ResponseEntity<List<JobResponse>> list(@RequestParam(required = false) final String name) {

		List<JobResponse> jobs = jobService.findAll(name);

		return ResponseEntity.ok(jobs);
	}

	@GetMapping("/{id}")
	public ResponseEntity<JobResponse> get(@PathVariable(value = "id") final Long id) {

		JobResponse job = jobService.findById(id);

		return ResponseEntity.ok(job);
	}

	@PutMapping("/{id}")
	public ResponseEntity<JobResponse> update(@PathVariable(value = "id") final Long id, @RequestBody final Job job) {

		JobResponse updatedJob = jobService.update(id, job);

		return ResponseEntity.ok(updatedJob);
	}

	@PostMapping
	public ResponseEntity<JobResponse> save(@RequestBody JobRequest job) {

		JobResponse save = jobService.save(job);

		URI uri = URI.create(save.getId().toString());

		return ResponseEntity.created(uri).body(save);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(value = "id") final Long id) {
		jobService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);

	}

}