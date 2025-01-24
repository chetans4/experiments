package org.wigs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeptAvgSalary {

    static class Department {

        public Department(int id, String name, List<Employee> emps) {
            this.id = id;
            this.name = name;
            this.emps = emps;
        }

        public int id;
        public String name;
        public List<Employee> emps;

        @Override
        public String toString() {
            return "Department{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", emps=" + emps +
                    '}';
        }
    }

    static class Employee {
        public int id;
        public String name;
        public int salary;

        public Employee(int id, String name, int salary) {
            this.id = id;
            this.name = name;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", salary=" + salary +
                    '}';
        }
    }

    public static void main(String[] args) {

        List<Department> departments = new ArrayList<>();
        departments.add(new Department(1, "One",
                Arrays.asList(new Employee(1, "emp-one", 49000),
                        new Employee(2, "emp-two", 52000))));
        departments.add(new Department(2, "One",
                Arrays.asList(new Employee(3, "emp-3", 20000),
                        new Employee(4, "emp-4", 54000))));

        departments = departments.stream().filter(
                dept -> dept.emps.stream().mapToInt(e -> e.salary).average()
                        .orElse(0.0) > 50000).toList();

        System.out.println("filtered - departments : "+departments);
    }
}
