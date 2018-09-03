package com.referminds.userpostservice.controller;

import java.net.URI;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.referminds.userpostservice.Configuration;
import com.referminds.userpostservice.exception.JobPostNotFoundException;
import com.referminds.userpostservice.model.JobPost;
import com.referminds.userpostservice.repository.JobPostRepository;

@RestController
@RequestMapping(path="users/{userId}")
public class JobPostController {

	@Autowired
	JobPostRepository jobPostRepository;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	Configuration config;

	@GetMapping(path = "/config")
	public String getConfig() {
		return config.getExpiresAfter()+"----"+config.getMaxPostsByUser();
		//return jobPostRepository.findAll();
	}
	@GetMapping(path = "/job-posts")
	public Iterable<JobPost> getAllJobPosts(@PathVariable String userId) {
		System.out.println(userId);
		return jobPostRepository.findAll();
	}

	@PostMapping(path = "/job-posts")
	public ResponseEntity<Object> createJobPost(@RequestBody JobPost jobPost) {
		JobPost savedJobPost = jobPostRepository.save(jobPost);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedJobPost.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping(path = "/job-posts/{id}")
	public JobPost getJobPost(@PathVariable String id) {
		return jobPostRepository.findById(id).orElseThrow(() -> new JobPostNotFoundException("No JobPost found for Id - " + id));
	}

	@DeleteMapping(path = "/job-posts/{id}")
	public String deleteJobPost(@PathVariable String id) {
		JobPost jobPost = jobPostRepository.findById(id).orElseThrow(() -> new JobPostNotFoundException("No JobPost found for Id - " + id));
		jobPostRepository.delete(jobPost);
		return "";
	}

	@PutMapping(path = "/job-posts/{id}")
	public JobPost updateJobPost(@PathVariable String id, @RequestBody JobPost jobPost) {
		JobPost exJobPost = jobPostRepository.findById(id).orElseThrow(() -> new JobPostNotFoundException("No JobPost found for Id - " + id));
		mapper.map(jobPost, exJobPost);
		jobPostRepository.save(exJobPost);
		return exJobPost;
	}

}
