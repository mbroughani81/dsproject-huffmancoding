package huffmancoding;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import com.github.javafaker.Faker;

public class TestGenerator {
    public static void run() {
        TestGenerator tg = new TestGenerator();
        for(int i = 0; i < 10; i++) {
            tg.createRandomTestFile(i + ".txt");
        }
    }
    private void createRandomTestFile(String filename) {
        String dir = "src/main/resources/" + filename;
        File file = new File(dir);
        try {
            file.createNewFile();
            PrintWriter printWriter = new PrintWriter(file);
            StringBuilder sb = new StringBuilder();
            
            // Random random = new Random();
            Faker faker = new Faker();
            for (int i = 0; i < 100; i++) {
                sb.append(faker.book().title());
            }

            printWriter.write(sb.toString());
            printWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
