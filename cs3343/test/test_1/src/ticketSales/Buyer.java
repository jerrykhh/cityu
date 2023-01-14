package ticketSales;

import java.util.*;

public class Buyer {
    private String buyer_name;
    private int buyer_birth_day;
    private int buyer_birth_month;

    public Buyer(String name, int b_day, int b_month) {
        buyer_name = name;
        buyer_birth_day = b_day;
        buyer_birth_month = b_month;
    }

    public Buyer() {
        this("Jackson", 26, 1);
    }

    public boolean isBirthdayThisWeek() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        if (cal.get(Calendar.MONTH) + 1 == buyer_birth_month) {
            if (buyer_birth_day >= cal.get(Calendar.DAY_OF_MONTH)
                    && buyer_birth_day < cal.get(Calendar.DAY_OF_MONTH) + 7) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
