public class SpecialNumber2844 {
    public int minimumOperations(String num) {
        int n = num.length();
        int mo = n;
        for (int i=0; i<n; i++) {
            int lastDigit = Integer.parseInt(num.charAt(n-i-1) + "");
            if (lastDigit % 5  == 0) {
                int count = i;
                boolean found = false;
                for (int j=n-i-2; j>=0; j--) {
                    int sum = 10 * Integer.parseInt(num.charAt(j) + "") + lastDigit;
                    if (sum % 25 != 0) {
                        ++count;
                        found = true;
                        continue;
                    } 
                    break;
                }
                
                if (!found && lastDigit != 0) {
                    count = n;
                }

                mo = mo > count ? count : mo;
            }
        }

        return mo;
    }
}
