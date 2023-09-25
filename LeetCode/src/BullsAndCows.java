public class BullsAndCows {
    public String getHint(String secret, String guess) {
        int[] secretCount = new int[10];
        int[] guessCount = new int[10];

        int cows = 0;
        int bulls = 0;

        for (int i=0; i<secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                ++cows;
            } else {
                ++secretCount[(int)secret.charAt(i)];
                ++guessCount[(int)guess.charAt(i)];
            }
        }

        for (int i=0; i<10; i++) {
            bulls += Math.min(secretCount[i], guessCount[i]);
        }

        return cows + "A" + bulls + "B";
    }
}
