package com.company.job.impl;

import com.company.job.Job;

import java.util.List;

public interface JobServiceImpl {
    List<Job> findAll();
    boolean createJob(Job job);
    Job getJobById(String id);
    boolean deleteJobById(String id);
    boolean updateJob(String id, Job job);
}
