public class Employee{
    private String name;
    private int salary;
    private int annualLeaves;

    public Employee(String name, int salary, int annualLeaves){
        this.name = name;
        this.salary = salary;
        this.annualLeaves = annualLeaves;
    }

    public String getName(){
        return name;
    }

    public void exchangeSalaries(Employee e1, Employee e2){
        int cacheSalaryE1 = e1.getSalary();
        e1.setSalary(e2.getSalary());
        e2.setSalary(cacheSalaryE1);
    }

    public void addSalary(int increase){
        this.salary += increase;
    }

    public int getSalary(){
        return salary;
    }

    public void setSalary(int salary){
        this.salary = salary;
    }

    public void changeAnnualLeaves(int annualLeaves){
        this.annualLeaves = annualLeaves;
    }

    public int getAnnualLeaves(){
        return annualLeaves;
    }

    public String toString(){
        return name + " ($" + salary + ", " + annualLeaves + " days)";
    }
}