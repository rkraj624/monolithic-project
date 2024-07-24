package com.company.company;

import com.company.company.impl.CompanyServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService implements CompanyServiceImpl {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean updateCompany(String id, Company company) {
        try {
            Company dbCompany = companyRepository.findById(id).orElse(null);
            if(dbCompany != null){
                dbCompany.setDescription(company.getDescription());
                dbCompany.setName(company.getName());
                dbCompany.setJobs(company.getJobs());
                companyRepository.save(dbCompany);
                return true;
            }
            return false;
        }catch (Exception exception){
            exception.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean deleteCompanyById(String id) {
        try {
            boolean company = companyRepository.existsById(id)
;            if(company){
                companyRepository.deleteById(id);
                return true;
            }
            return false;
        }catch (Exception exception){
            exception.printStackTrace();
            return false;
        }

    }

    @Override
    public Company getCompanyById(String id) {
        return companyRepository.findById(id).orElse(null);
    }
}
