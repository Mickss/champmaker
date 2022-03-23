package org.micks.champmaker;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    @RequestMapping("employees")
    public List<Employee> getEmployees() {

        List<Employee> employees=new ArrayList<Employee>();
        employees.add(new Employee(1, "Michal", "Guz"));
        employees.add(new Employee(2, "Pieter", "Parker"));
        employees.add(new Employee(3, "Tony", "Stark"));
        employees.add(new Employee(4, "Hulk", "Green"));
        employees.add(new Employee(5, "Batman", "Robin"));
        employees.add(new Employee(6, "Kamil", "Brzezinski"));

        return employees;

    }
}
