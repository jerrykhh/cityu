import java.util.ArrayList;

public class Employee implements Comparable<Employee> {
    private String name;

    public Employee(String n) {
        this.name = n;
    }

    public String getName() { return name;};

    public static Employee searchEmployee(ArrayList<Employee> list, String nameToSearch) {
        Employee result = null;
        for (Employee employee : list) {
            if(employee.getName().equals(nameToSearch))
                result = employee;
        }
        return result;
    }

    @Override
	public int compareTo(Employee another){
		return this.name.compareTo(another.name);
	}

}
