package org.wigs;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;


class Result {

    /*
     * Complete the 'minimumWeeklyInput' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY costs
     *  2. INTEGER weeks
     */

    public static int minimumWeeklyInput(List<Integer> costs, int weeks) {
        // Write your code here

        int numberOfCosts = costs.size();
        if (weeks >= numberOfCosts) {
            return costs.stream().mapToInt(Integer::intValue).sum();
        }

        int[][] campainCostMapping = new int[weeks + 1][numberOfCosts + 1];
        for (int i = 0; i <= weeks; i++) {
            Arrays.fill(campainCostMapping[i], Integer.MAX_VALUE);
        }
        campainCostMapping[0][0] = 0;

        for (int w = 1; w <= weeks; w++) {
            for (int i = 1; i <= numberOfCosts; i++) {
                int maxCost = 0;
                for (int k = i; k >= 1; k--) {
                    maxCost = Math.max(maxCost, costs.get(k - 1));

                    if (campainCostMapping[w - 1][k - 1] != Integer.MAX_VALUE) {
                        campainCostMapping[w][i] = Math.min(campainCostMapping[w][i], campainCostMapping[w - 1][k - 1] + maxCost);
                    }
                }
            }
        }
        return campainCostMapping[weeks][numberOfCosts];
    }

}

public class MinimumCostForCampaign {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int costsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> costs = IntStream.range(0, costsCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int weeks = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.minimumWeeklyInput(costs, weeks);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
