class Shop{
    private int totalProfit;
    public Shop(){
        totalProfit = 0;
    }

    public void earn(Customer c, int value){
        totalProfit += value;
        c.spend(value);
    }

    public int getProfit() {
        return totalProfit;
    }
}
