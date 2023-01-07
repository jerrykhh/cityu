import java.util.List;
import java.util.ArrayList;

public class Company {

    private List<Employee> allEmployee;
    private static Company instance = new Company();
    public static Company getInstance(){
        return instance;
    }

    private Company(){
        allEmployee = new ArrayList<>();
    }

    public void listEmployees(){
        for (Employee employee : allEmployee) {
            System.out.println(employee);
        }
    }

    public Employee findEmployee(String name) throws ExEmployeeNotFound{
        for (Employee employee : allEmployee) {
            if(employee.getName().equals(name)){
                return employee;
            }
        }
        throw new ExEmployeeNotFound();
    }

    public void addEmployee(Employee e){
        allEmployee.add(e);
    }

    
}
