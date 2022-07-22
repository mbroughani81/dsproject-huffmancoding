package huffmancoding;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

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

            HuffmanCoding huffmanCoding = new HuffmanCoding(cnt);
            huffmanCoding.code();
            int sizeWithCompression = huffmanCoding.getNumberOfBitsInEncoded();

            System.out.println(">>>>Number of bits in uncompressed file: " + sizeWithoutCompression);
            System.out.println(">>>>Number of bits in compressed file: " + sizeWithCompression);
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
