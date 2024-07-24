package com.company.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    ResponseEntity<String> createCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    ResponseEntity<String> updateCompany(@RequestBody Company company, @PathVariable String id){
        boolean updateCompany = companyService.updateCompany(id, company);
        if(updateCompany){
            return new ResponseEntity<>("Company updated successfully with id : "+ id,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{id}")
    ResponseEntity<Company> getCompanyById(@PathVariable String id){
        Company company = companyService.getCompanyById(id);
        if(company != null){
            return new ResponseEntity<>(company,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping
    ResponseEntity<List<Company>> getAllCompanies(){
        return new ResponseEntity<>(companyService.getAllCompanies(),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteJobById(@PathVariable String id){
        boolean deleteCompanyById = companyService.deleteCompanyById(id);
        if(deleteCompanyById){
            return new ResponseEntity<>("Company deleted successfully with id : "+ id,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
