package huffmancoding.compression;

public class EntropyCalculator {
    public static double calculateEntropy(int[] cnt) {
        int sum = 0;
        for(int i = 0; i < cnt.length; i++) {
            sum += cnt[i];
        }

        double ans = 0;
        for(int i = 0; i < cnt.length; i++) {
            if (cnt[i] > 0) {
                double p = ((double)cnt[i]) / sum;
                double log2 = Math.log(p) / Math.log(2);
                ans -= p * log2;
            }
        }

        return ans;
    }
}
