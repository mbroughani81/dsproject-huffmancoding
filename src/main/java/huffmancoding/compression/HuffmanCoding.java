package huffmancoding.compression;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HuffmanCoding {
    private int[] cnt;
    private HuffmanNode root;

    public HuffmanCoding(int[] cnt) {
        this.cnt = cnt;
        this.root = null;
    }

    public void code() {
        int n = 256;

        PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>(n, new ImplementComparator());

        for (int i = 0; i < n; i++) {
            HuffmanNode hn = new HuffmanNode();

            hn.c = (char)i;
            hn.freq = cnt[i];

            hn.left = null;
            hn.right = null;

            q.add(hn);
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

    public void printCode(HuffmanNode node, String s)) {
        if (root.left == null && root.right == null && Character.isLetter(root.c)) {

            System.out.println(root.c + "   |  " + s);
      
            return;
          }
          printCode(root.left, s + "0");
          printCode(root.right, s + "1");
    }

    public int getNumberOfBitsInEncoded() {
        return 0;
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