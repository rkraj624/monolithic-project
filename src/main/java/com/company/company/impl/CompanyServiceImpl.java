package com.company.company.impl;

import com.company.company.Company;


import java.util.List;

public interface CompanyServiceImpl {
    List<Company> getAllCompanies();
    void createCompany(Company company);
    boolean updateCompany(String id, Company company);
    boolean deleteCompanyById(String id);
    Company getCompanyById(String id);
}
