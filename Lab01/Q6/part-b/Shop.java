class Shop{
    private int totalProfit;
    public Shop(){
        totalProfit = 0;
    }

    public void earn(Customer c, int value){
        totalProfit += value;
        c.spend(value);
    }

    public void earn(Group custArray, int value){
        totalProfit += value;
        int avgValue = value/custArray.getCustomerArray().length;
        for(Customer cust: custArray.getCustomerArray()){
            cust.spend(avgValue);
        }
    }

    public int getProfit() {
        return totalProfit;
    }
}
