package ticketSales;

public class Ticket {
    private Buyer buyer;

    public Ticket(Buyer buyer) {
        this.buyer = buyer;
    }

    public double getDiscountRate(Category item_cat, int item_quan) {
        double rate = 1.00;

        if (item_quan < 1) {
            return -1.00;
        }

        if (item_cat == Category.Children) {
            rate = 0.80;;
        } else if (item_cat == Category.Senior) {
            if (item_quan > 10) {
                rate = 0.50;
            } else if (item_quan > 3) {
                rate = 0.60; // This statement is labeled as L1
            } else {
                rate = 0.65;
            }
        } else if (item_cat == Category.Student) {
            if (item_quan > 6) {
                rate = 0.55; // This statement is labeled as L2
            } else {
                rate = 0.65; 
            }
        } else {
            rate = 1.00;
        }

        if (buyer.isBirthdayThisWeek()) {
            if (item_quan > 5) { // This statement is labeled as T1
                rate = 0.50 * rate; 
            } else {
                rate = 0.85 * rate;
            }
        }

        return rate;
    }

}
