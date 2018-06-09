import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import edu.princeton.cs.algs4.MinPQ;


/**
 * Created by Zhenye Na on Jun, 2018
 */


public class BinaryTrie implements Serializable {

    private Node bt;

    // trie node
    private static class Node implements Comparable<Node> {
        private final char ch;
        private final int freq;
        private final Node left, right;

        public Node(char ch, int freq, Node left, Node right) {
            this.ch    = ch;
            this.freq  = freq;
            this.left  = left;
            this.right = right;
        }

        // is the node a leaf node?
        private boolean isLeaf() {
            assert ((left == null) && (right == null)) || ((left != null) && (right != null));
            return (left == null) && (right == null);
        }

        // compare, based on frequency
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }


//    /**
//     *  find the key which has the min value in the map
//     */
//    private Character getMinKey(Map<Character, Integer> map) {
//        Character minKey = null;
//        int minValue = Integer.MAX_VALUE;
//        List<Character> keys = new ArrayList<>(map.keySet());
//
//        for(Character key : keys) {
//            int value = map.get(key);
//            if(value < minValue) {
//                minValue = value;
//                minKey = key;
//            }
//        }
//        return minKey;
//    }


//    private Map<Character, Integer> sortMap(Map<Character, Integer> frequencyTable) {
//
//        HashMap duplicate = new HashMap();
//        duplicate = (HashMap) ((HashMap) frequencyTable).clone();
//        Character minKey = getMinKey(duplicate);
//
//    }



    public BinaryTrie(Map<Character, Integer> frequencyTable) {

        MinPQ<Node> pq = new MinPQ<>();
        for (Character key : frequencyTable.keySet()) {
            if (frequencyTable.get(key) > 0) pq.insert(new Node(key, frequencyTable.get(key), null, null));
        }

        if (pq.size() == 1) {
            pq.insert(new Node('\0', 0, null, null));
        }

        while (pq.size() > 1) {
            Node left = pq.delMin();
            Node right = pq.delMin();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            pq.insert(parent);
        }

        bt = pq.delMin();
    }


    /**
     * finds the longest prefix that matches the given
     * querySequence and returns a Match object for that Match
     */
    public Match longestPrefixMatch(BitSequence querySequence) {

        Match mt = new Match();




    }







    public Map<Character, BitSequence> buildLookupTable() {


        // make a lookup table from symbols and their encodings
//        private static void buildCode(String[] st, Node x, String s) {
//            if (!x.isLeaf()) {
//                buildCode(st, x.left,  s + '0');
//                buildCode(st, x.right, s + '1');
//            }
//            else {
//                st[x.ch] = s;
//            }
//        }

        Map<Character, BitSequence> ref = new HashMap<>();

        while (bt.ch != '\0') {
            Character symbol;
            BitSequence sequence;



        }



    }
}
