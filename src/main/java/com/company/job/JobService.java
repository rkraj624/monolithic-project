package com.company.job;

import com.company.company.Company;
import com.company.company.CompanyService;
import com.company.job.impl.JobServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService implements JobServiceImpl {
    private JobRepository jobRepository;
    private CompanyService companyService;

    public JobService(JobRepository jobRepository, CompanyService companyService) {
        this.jobRepository = jobRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    public boolean createJob(Job job) {
        jobRepository.save(job);
        Company company = companyService.getCompanyById(job.getCompany().getId());
        if (company != null) {
            jobRepository.save(job);
            return true;
        }
        return false;


    }

    @Override
    public Job getJobById(String id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(String id) {
        try{
            boolean job = jobRepository.existsById(id);
            if(job){
                jobRepository.deleteById(id);
                return true;
            }
            return false;
        }catch (Exception exception){
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateJob(String id, Job job) {
        try {
            Job dbJob = jobRepository.findById(id).orElse(null);
            if (dbJob != null) {
                dbJob.setDescription(job.getDescription());
                dbJob.setLocation(job.getLocation());
                dbJob.setTitle(job.getTitle());
                dbJob.setMaxSalary(dbJob.getMaxSalary());
                dbJob.setMinSalary(job.getMinSalary());
                jobRepository.save(dbJob);
                return true;
            }
            return false;
        }catch (Exception exception){
            exception.printStackTrace();
            return false;
        }

    }
}
