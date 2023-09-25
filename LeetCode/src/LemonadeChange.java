public class LemonadeChange {
    public boolean lemonadeChange(int[] bills) {
        int fiveBills = 0;
        int tenBills = 0;

        for (int i=0; i<bills.length; i++) {
            int bill = bills[i];
            if (bill == 5) {
                ++fiveBills; 
                continue;
            }

            if (bill == 10) {
                if (fiveBills > 0) {
                    --fiveBills;
                    ++tenBills;
                    continue;
                } 
                return false;
            } 

            if (bill == 20) {
                if (tenBills > 0) {
                    --tenBills;
                    bill = 5;
                } else {
                    bill = 15;
                }

                if (bill/ 5 <= fiveBills) {
                    fiveBills -= bill/5;
                    continue;
                }
                return false;
            }
        }

        return true;
    }
}
