package week6.assigment_problems;

class CompanyEmployee {
    String empName;
    double salary;
    static String companyName = "Bright Horizon Technologies";
    static int employeeCount = 0;

    public CompanyEmployee(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }

    public static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }
}

public class CompanyInfoManager {
    public static void main(String[] args) {
        new CompanyEmployee("Rohan", 45000);
        new CompanyEmployee("Sneha", 52000);
        new CompanyEmployee("Vikram", 60000);

        CompanyEmployee.printCompanyInfo();
    }
}