package org.wigs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EmpDept {

    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(30, new Dept("IT")));
        employees.add(new Employee(20, new Dept("Admin")));
        employees.add(new Employee(40, new Dept("Security")));
        employees.add(new Employee(50, new Dept("Managemt")));

        employees = employees.stream().filter(e -> e.age > 30).sorted(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.dept.name.compareTo(o2.dept.name);
            }
        }).toList();

        System.out.println("result employees : "+employees);

    }

}

class Employee {
    Integer age;
    Dept dept;

    Employee(Integer age, Dept dept){
        this.age = age;
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "age=" + age +
                ", dept=" + dept +
                '}';
    }
}

class Dept {
    String name;

    Dept(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "name='" + name + '\'' +
                '}';
    }
}