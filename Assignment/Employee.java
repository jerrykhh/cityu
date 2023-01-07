import java.util.ArrayList;
public class Employee implements Comparable<Employee>{
    private String name;

    public Employee(String name){
        this.name = name;
    }

    public static Employee searchEmployee(ArrayList<Employee> list, String nameToSearch) throws ExEmployeeNotFound {
        for (Employee employee : list) {
            if(employee.getName().equals(nameToSearch))
                return employee;
        }
        throw new ExEmployeeNotFound();
    }

    public String getName(){
        return name;
    }

    @Override
	public int compareTo(Employee another){
		return this.name.compareTo(another.name);
    }

    public String toString(){
        return name;
    }
}