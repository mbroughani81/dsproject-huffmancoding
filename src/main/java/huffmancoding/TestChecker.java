package huffmancoding;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

import huffmancoding.compression.EntropyCalculator;
import huffmancoding.compression.HuffmanCoding;

public class TestChecker {
    public static void run() {
        TestChecker tc = new TestChecker();
        for(int i = 0; i < 10; i++) {
            int[] cnt = tc.checkTestFile(i + ".txt");
            int sum = 0;
            for (int t = 0; t < cnt.length; t++) {
                sum += cnt[t];
            }

            int sizeWithoutCompression = 8 * sum;

            HuffmanCoding losslessHuffmanCoding = new HuffmanCoding(cnt);
            losslessHuffmanCoding.code(false);
            int sizeWithLosslessCompression = losslessHuffmanCoding.getNumberOfBitsInEncoded(false);

            HuffmanCoding lossyHuffmanCoding = new HuffmanCoding(cnt);
            lossyHuffmanCoding.code(true);
            int sizeWithLossyCompression = lossyHuffmanCoding.getNumberOfBitsInEncoded(false);            

            System.out.printf("#### Results for test %d ####%n", i);
            System.out.printf(">>>>Entropy of given file: %.5f bits/symbol%n", EntropyCalculator.calculateEntropy(cnt));
            System.out.printf(">>>>Number of bits in uncompressed file: %d, %.5f bits/symbol%n", sizeWithoutCompression, ((double)sizeWithoutCompression)/sum);
            System.out.printf(">>>>Number of bits in lossless compressed file: %d, %.5f bits/symbol%n", sizeWithLosslessCompression, ((double)sizeWithLosslessCompression)/sum);
            System.out.printf(">>>>compression ratio for lossless version is : %.2f:1 %n", ((double)sizeWithoutCompression)/sizeWithLosslessCompression);
            System.out.printf(">>>>Number of bits in lossy compressed file: %d, %.5f bits/symbol%n", sizeWithLossyCompression, ((double)sizeWithLossyCompression)/sum);
            System.out.printf(">>>>compression ratio for lossy version is : %.2f:1 %n", ((double)sizeWithoutCompression)/sizeWithLossyCompression);
        }
    }
    private int[] checkTestFile(String filename) {
        String dir = "src/main/resources/" + filename;
        File file = new File(dir);
        Reader fileReader = null;

        int[] cnt = new int[256];
        try {   
            fileReader = new FileReader(file);

            int data = fileReader.read();
            if (data >= cnt.length) {
                System.out.println("!!!!!!!!!!!!!!PROBLEM!!!!!!!!!!!!!!!!!");
            }
            while(data != -1) {
                cnt[data]++;
                data = fileReader.read();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

        return cnt;
    }
}
