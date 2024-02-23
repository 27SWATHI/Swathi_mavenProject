package com.controller;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model.Employee;

public class EmployeeController {
	@RequestMapping("/home")
    public String homePage() {
        return "home"; // WEB-INF/pages/welcome.html
    }
	@RequestMapping("/showemployees")
    public String showEmployees(Model data) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        try {
           
            Query<Employee> q1 = session.createQuery("from Employee", Employee.class);
            List<Employee> employee = q1.getResultList();
            data.addAttribute("employee", employee);
        } catch (Exception ex) {
            System.out.println("Hibernate error : " + ex.getMessage());
        }
        return "showemployees";
    }

    @RequestMapping("/addemployees")
    public String addEmployeeForm() {
        return "addemployees"; // WEB-INF/pages/add-car-form.html
    }

    @PostMapping("/employeesinsert")
    public String addEmployeeInsert(@RequestParam int id, @RequestParam String employeeName, @RequestParam String employeeAddress,
    @RequestParam int employeePhone, @RequestParam double employeeSalary, Model data) {

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        try {
            Transaction tx = session.beginTransaction();

            // All the database work
            Employee e1 = new Employee(id, employeeName, employeeAddress, employeePhone, employeeSalary);
            session.save(e1); // Object converted to insert query

            tx.commit();
        } catch (Exception ex) {
            System.out.println("Hibernate error : " + ex.getMessage());
        }
        Query<Employee> q2 = session.createQuery("from Employee", Employee.class);
List<Employee> employees = q2.getResultList();
data.addAttribute("employees", employees);
        return "showemployees";
    }

   
    @GetMapping("/updateemployees")
    public String updateEmployeeForm(@RequestParam int id, Model data) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        try {
           
        Employee updateEmp = session.get(Employee.class, id);
            data.addAttribute("employees", updateEmp);
        } catch (Exception ex) {
            System.out.println("Hibernate error : " + ex.getMessage());
        }
        return "updateemployees";
    }

    @PostMapping("/updated")
    public String updatedEmployee(@RequestParam int id, @RequestParam String employeeName, @RequestParam String employeeAddress,
    @RequestParam int employeePhone, @RequestParam double employeeSalary, Model data) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        try {
            Transaction tx = session.beginTransaction();
           
            Employee e1 = new Employee(id, employeeName, employeeAddress, employeePhone, employeeSalary);
            session.update(e1);
            tx.commit();
        } catch (Exception ex) {
            System.out.println("Hibernate error : " + ex.getMessage());
        }
        Query<Employee> q2 = session.createQuery("from Employee", Employee.class);
List<Employee> employees = q2.getResultList();
data.addAttribute("employees", employees);
        return "showemployees";
    }

    @GetMapping("/deleteemployees")
    public String deleteCar(@RequestParam int id, Model data) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        try {
            Transaction tx = session.beginTransaction();
            // All the database work
            Employee deleteEmp = new Employee(id, null, null, 0, 0);
            session.delete(deleteEmp);
            tx.commit();
        } catch (Exception ex) {
            System.out.println("Hibernate error : " + ex.getMessage());
        }
        Query<Employee> q2 = session.createQuery("from Employee", Employee.class);
List<Employee> employees = q2.getResultList();
data.addAttribute("employees", employees);
        return "showemployees";
    }
   
}


	


