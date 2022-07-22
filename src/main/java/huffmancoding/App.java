package huffmancoding;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args){
        if(args.length > 0) {
            if(args[0].equals("gen")) {
                System.out.println(">>generating random test files...");
                TestGenerator.run();
            }
            else
            if(args[0].equals("test")) {
                System.out.println(">>running huffman algorithm on test files:");
                TestChecker.run();
            }
            else {
                System.out.println(">>Invalid command");
            }

        }
    }
}
