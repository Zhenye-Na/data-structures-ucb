/**
 * Created by Zhenye Na on Jun, 2018
 */

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import edu.princeton.cs.algs4.MinPQ;


public class BinaryTrie implements Serializable {

    private Node bt;
    Map<Character, BitSequence> ref;


    // BinaryTrie node
    private static class Node implements Serializable, Comparable<Node> {
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
            return (left == null) && (right == null);
        }

        // compare, based on frequency
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }


    public BinaryTrie(Map<Character, Integer> frequencyTable) {

        MinPQ<Node> pq = new MinPQ<>();
        for (Character key : frequencyTable.keySet()) {
            if (frequencyTable.get(key) > 0) pq.insert(new Node(key, frequencyTable.get(key), null, null));
        }


        while (pq.size() > 1) {
            Node left = pq.delMin();
            Node right = pq.delMin();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            pq.insert(parent);
        }

        bt = pq.delMin();
        ref = new HashMap<>();
    }


    /**
     * finds the longest prefix that matches the given
     * querySequence and returns a Match object for that Match
     */
    public Match longestPrefixMatch(BitSequence querySequence) {

        Node curr = bt;

        for (int i = 0; i < querySequence.length(); i += 1) {
            if (curr.isLeaf()) {
                return new Match(querySequence.firstNBits(i), curr.ch);
            } else {
                int bit = querySequence.bitAt(i);
                if (bit == 0) {
                    curr = curr.left;
                } else {
                    curr = curr.right;
                }
            }
        }

        return new Match(querySequence, curr.ch);
    }


    public Map<Character, BitSequence> buildLookupTable() {

        buildLookupTable(ref, bt, "");
        return ref;
    }

    private void buildLookupTable(Map<Character, BitSequence> lookupTable, Node x, String s) {
        if (!x.isLeaf()) {
            buildLookupTable(lookupTable, x.left, s + '0');
            buildLookupTable(lookupTable, x.right, s + '1');
        } else {
            lookupTable.put(x.ch, new BitSequence(s));
        }
    }


}
