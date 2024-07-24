package com.company.job;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    ResponseEntity<String> createJob(@RequestBody Job job){
        boolean isCreated = jobService.createJob(job);
        if(isCreated){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Company with this id doesn't exist ",HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}")
    ResponseEntity<String> updateJob(@RequestBody Job job, @PathVariable String id){
        boolean updateJob = jobService.updateJob(id, job);
        if(updateJob){
            return new ResponseEntity<>("Job updated successfully with id : "+ id,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping
    ResponseEntity<List<Job>> getAllJobs(){
        return new ResponseEntity<>(jobService.findAll(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    ResponseEntity<Job> getJobById(@PathVariable String id){
        Job jobById = jobService.getJobById(id);
        if(jobById != null){
            return new ResponseEntity<>(jobById,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteJobById(@PathVariable String id){
        boolean deleteJobById = jobService.deleteJobById(id);
        if(deleteJobById){
            return new ResponseEntity<>("Job deleted successfully with id : "+ id,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
