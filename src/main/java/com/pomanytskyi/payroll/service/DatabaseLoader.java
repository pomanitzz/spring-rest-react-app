package com.pomanytskyi.payroll.service;

import com.pomanytskyi.payroll.domain.Manager;
import com.pomanytskyi.payroll.repository.EmployeeRepository;
import com.pomanytskyi.payroll.domain.Employee;
import com.pomanytskyi.payroll.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final ManagerRepository managerRepository;

    @Autowired
    public DatabaseLoader(EmployeeRepository employeeRepository, ManagerRepository managerRepository) {
        this.employeeRepository = employeeRepository;
        this.managerRepository = managerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Manager anton = this.managerRepository.save(new Manager("anton", "12345", "ROLE_MANAGER"));
        Manager gibon = this.managerRepository.save(new Manager("gibon", "12345", "ROLE_MANAGER"));

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("anton", "doesn't matter",
                        AuthorityUtils.createAuthorityList("ROLE_MANAGER")
                )
        );

        this.employeeRepository.save(new Employee("Anton", "Pomanytskyi", "Learn", anton));
        this.employeeRepository.save(new Employee("Example", "Example", "Description", anton));
        this.employeeRepository.save(new Employee("First", "First", "FirstDesc", anton));
        this.employeeRepository.save(new Employee("Second", "Second", "SecondDesc", anton));
        this.employeeRepository.save(new Employee("Third", "Third", "ThirdDesc", anton));

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("gibon", "doesn't matter",
                        AuthorityUtils.createAuthorityList("ROLE_MANAGER")
                )
        );

        this.employeeRepository.save(new Employee("FirstGib", "FirstGib", "FirstDescGib", gibon));
        this.employeeRepository.save(new Employee("FirstGib", "FirstGib", "FirstDescGib", gibon));

        SecurityContextHolder.clearContext();
    }
}
