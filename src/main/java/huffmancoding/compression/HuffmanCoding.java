package huffmancoding.compression;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HuffmanCoding {
    private static int ALPHABET_SIZE = 256;

    private int[] cnt;
    private HuffmanNode root;

    public HuffmanCoding(int[] cnt) {
        this.cnt = cnt;
        this.root = null;
    }

    public void code() {
        PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>(ALPHABET_SIZE, new ImplementComparator());

        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if(this.cnt[i] != 0) {    
                HuffmanNode hn = new HuffmanNode();

                hn.c = (char)i;
                hn.freq = cnt[i];

                hn.left = null;
                hn.right = null;

                q.add(hn);
            }
        }

        while (q.size() > 1) {
            HuffmanNode x = q.peek();
            q.poll();

            HuffmanNode y = q.peek();
            q.poll();

            HuffmanNode f = new HuffmanNode();

            f.freq = x.freq + y.freq;
            f.c = '-';
            f.left = x;
            f.right = y;
            this.root = f;

            q.add(f);
        }
    }

    private int printCode(HuffmanNode node, String curCodeword) {
        if (node == null)
            return 0;
        if (node.left == null && node.right == null) {
            System.out.println(node.c + " ==> " + curCodeword);
            return curCodeword.length() * node.freq;
        }
        int sum = 0;
        sum += printCode(node.left, curCodeword + "0");
        sum += printCode(node.right, curCodeword + "1");
        return sum;
    }

    public int getNumberOfBitsInEncoded() {
        return printCode(this.root, "");
    }
}

class HuffmanNode {
  int freq;
  char c;
  HuffmanNode left;
  HuffmanNode right;
}

class ImplementComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y) {
      return x.freq - y.freq;
    }
}