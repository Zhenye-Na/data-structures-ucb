/**
 * Created by Zhenye Na on Jun, 2018
 */



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HuffmanEncoder {

    public static Map<Character, Integer> buildFrequencyTable(char[] inputSymbols) {
        Map<Character, Integer> freq = new HashMap<>();

        for (Character letter : inputSymbols ) {
            List<Character> keys = new ArrayList<>(freq.keySet());

            if (!keys.contains(letter)) {
                freq.put(letter, 1);
            } else {
                freq.put(letter, freq.get(letter) + 1);
            }

        }

        return freq;
    }

    /**
     *  The main method should open the file given as the 0th command line
     *  argument (args[0]), and write a new file with the name args[0] + ".huf"
     *  that contains a huffman encoded version of the original file.
     *
     *  For example java HuffmanEncoder `watermelonsugar.txt` should generate a
     *  new Huffman encoded version of `watermelonsugar.txt` that contains
     *  `watermelonsugar.txt.huf`
     *
     */
    public static void main(String[] args) {

        // 1: Read the file as 8 bit symbols.
        // 2: Build frequency table.
        // 3: Use frequency table to construct a binary decoding trie.
        // 4: Write the binary decoding trie to the .huf file.
        // 5: (optional: write the number of symbols to the .huf file)
        // 6: Use binary trie to create lookup table for encoding.
        // 7: Create a list of bitsequences.
        // 8: For each 8 bit symbol:
        //        Lookup that symbol in the lookup table.
        //        Add the appropriate bit sequence to the list of bitsequences.
        // 9: Assemble all bit sequences into one huge bit sequence.
        // 10: Write the huge bit sequence to the .huf file.

        // Utility Methods available
        // 1: char[] FileUtils.readFile(String filename)
        // 4/5/10: ObjectWriter's writeObject method.
        // 9: BitSequence BitSequence.assemble(List<BitSequence>)

        // String filename = args[0];
        // String newFilename = args[0] + ".huf";


         String test = "watermelonsugar.txt";
         String filename = test;
         String newFilename = test + ".huf";

        char[] inputSymbols = FileUtils.readFile(filename);
        Map<Character, Integer> frequencyTable = buildFrequencyTable(inputSymbols);
        BinaryTrie bt = new BinaryTrie(frequencyTable);

        ObjectWriter ow = new ObjectWriter(newFilename);
        ow.writeObject(bt);
        ow.writeObject(inputSymbols.length);

        Map<Character, BitSequence> lookupTable = bt.buildLookupTable();

        List<BitSequence> bitseq = new ArrayList<>();
        for (Character symbol : inputSymbols) {
            bitseq.add(lookupTable.get(symbol));
        }

        BitSequence masterSequence = BitSequence.assemble(bitseq);
        ow.writeObject(masterSequence);


    }



}
