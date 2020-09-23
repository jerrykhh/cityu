public class Group{

    private Customer[] cusArray;
    private int index = -1;

    public Group(){
        cusArray = new Customer[2];
    }

    public void add(Customer c){
        cusArray[++index] = c; 
    }

    public Customer[] getCustomerArray(){
        return cusArray;
    }
}