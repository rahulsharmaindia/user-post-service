package com.referminds.userpostservice.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.referminds.userpostservice.model.JobPost;

public interface JobPostRepository extends CrudRepository<JobPost, String> {
	@Override
	Optional<JobPost> findById(String id);

	@Override
	void delete(JobPost deleted);
}
