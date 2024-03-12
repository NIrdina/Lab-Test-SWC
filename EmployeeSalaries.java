
/**
 * Program Description: Lab Test
 * Programmer: Nurin
 * Date:12 Mac 2024
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class EmployeeSalaries 
{
    public static void main(String[] args) 
    {
        ArrayList<Employee> employees = new ArrayList<>();

        try 
        {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\User\\Documents\\swc_project\\EmployeeSalaries.txt"));
            String line;
            while ((line = reader.readLine()) != null) 
            {
                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                String name = tokenizer.nextToken();
                int salary = Integer.parseInt(tokenizer.nextToken());
                int yearsOfService = Integer.parseInt(tokenizer.nextToken());
                employees.add(new Employee(name, salary, yearsOfService));
            }
            reader.close();
        } catch (IOException e) 
        {
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage());
            return;
        }

        for (Employee employee : employees) 
        {
            int annualSalary = calculateAnnualSalary(employee.getSalary(), employee.getYearsOfService());
            JOptionPane.showMessageDialog(null, employee.getName() + " - Annual Salary: " + annualSalary);
        }

        Employee topPerformingEmployee = employees.stream()
            .max(Comparator.comparing(Employee::getAnnualSalary))
            .orElse(null);

        if (topPerformingEmployee != null) 
        {
            JOptionPane.showMessageDialog(null, "Top-performing employee: " + topPerformingEmployee.getName() + " - Annual Salary: " + topPerformingEmployee.getAnnualSalary());
        } else {
            JOptionPane.showMessageDialog(null, "No employees found.");
        }
    }

    private static int calculateAnnualSalary(int salary, int yearsOfService) 
    {
        double increment = salary * 0.05;
        return salary + (int) Math.round(increment * yearsOfService);
    }
}

class Employee 
{
    private String name;
    private int salary;
    private int yearsOfService;
    private int annualSalary;

    public Employee(String name, int salary, int yearsOfService) 
    {
        this.name = name;
        this.salary = salary;
        this.yearsOfService = yearsOfService;
        this.annualSalary = calculateAnnualSalary(salary, yearsOfService);
    }

    public String getName() 
    {
        return name;
    }

    public int getSalary() 
    {
        return salary;
    }

    public int getYearsOfService() 
    {
        return yearsOfService;
    }

    public int getAnnualSalary() 
    {
        return annualSalary;
    }

    private static int calculateAnnualSalary(int salary, int yearsOfService) 
    {
        double increment = salary * 0.05;
        return salary + (int) Math.round(increment * yearsOfService);
    }
}


