import java.util.*;
/*
 * In the game of bridge, four players are dealt hands of 13
cards each. An important statistic is the distribution of the number of cards in each
suit in a hand. Which is the most likely, 5–3-3–2, 4–4-3–2, or 4–3–3–3?
 */
public class BridgeHands {
    private static final int NUM_TRIALS = 1000000;

    public static void main(String[] args) {
        Map<String, Integer> distributionCounts = new HashMap<>();
        distributionCounts.put("5-3-3-2", 0);
        distributionCounts.put("4-4-3-2", 0);
        distributionCounts.put("4-3-3-3", 0);

        Random random = new Random();

        for (int i = 0; i < NUM_TRIALS; i++) {
            int[] suits = dealHand(random);
            Arrays.sort(suits);

            String distribution = getDistribution(suits);
            distributionCounts.put(distribution, distributionCounts.getOrDefault(distribution, 0) + 1);
        }

        System.out.println("5-3-3-2: " + distributionCounts.get("5-3-3-2"));
        System.out.println("4-4-3-2: " + distributionCounts.get("4-4-3-2"));
        System.out.println("4-3-3-3: " + distributionCounts.get("4-3-3-3"));

        String mostLikelyDistribution = Collections.max(distributionCounts.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println("Most likely distribution: " + mostLikelyDistribution);
    }

    private static int[] dealHand(Random random) {
        int[] suits = new int[4];
        for (int i = 0; i < 13; i++) {
            suits[random.nextInt(4)]++;
        }
        return suits;
    }

    private static String getDistribution(int[] suits) {
        if (suits[0] == 2 && suits[1] == 3 && suits[2] == 3 && suits[3] == 5) {
            return "5-3-3-2";
        } else if (suits[0] == 2 && suits[1] == 3 && suits[2] == 4 && suits[3] == 4) {
            return "4-4-3-2";
        } else if (suits[0] == 3 && suits[1] == 3 && suits[2] == 3 && suits[3] == 4) {
            return "4-3-3-3";
        }
        return "other";
    }
}
