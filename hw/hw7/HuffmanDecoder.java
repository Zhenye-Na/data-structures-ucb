import java.util.ArrayList;

/**
 * Created by Zhenye Na on Jun, 2018
 */




public class HuffmanDecoder {

    public static void main(String[] args) {

        // 1: Read the Huffman coding trie.
        // 2: If applicable, read the number of symbols.
        // 3: Read the massive bit sequence corresponding to the original txt.
        // 4: Repeat until there are no more symbols:
        //     4a: Perform a longest prefix match on the massive sequence.
        //     4b: Record the symbol in some data structure.
        //     4c: Create a new bit sequence containing the remaining unmatched bits.
        // 5: Write the symbols in some data structure to the specified file.

        // Utility Methods available
        // 1/2/3: ObjectReader's readObject method.
        // 4c: BitSequence has methods that may be useful to you.
        // 5: FileUtils.writeCharArray(String filename, char[] chars)

        // String filename = args[0];
        // String newFilename = args[1];


        String filename = "watermelonsugar.txt.huf";
        String newFilename = "originalwatermelon.txt";


        ObjectReader or = new ObjectReader(filename);

        /* Read first object from the file. */
        Object x = or.readObject();
        /* Read second object from the file. */
        Object y = or.readObject();
        /* Read third object from the file. */
        Object z = or.readObject();

        BinaryTrie bt;
        int inputSymbolsLength;
        BitSequence masterSequence;

        if (z == null) {
            System.out.println("There is no number of symbols.");
            bt = (BinaryTrie) x;
            masterSequence = (BitSequence) y;
            inputSymbolsLength = masterSequence.length();


        } else {

            bt = (BinaryTrie) x;
            inputSymbolsLength = (int) y;
            masterSequence = (BitSequence) z;

        }

        char[] symbols = new char[inputSymbolsLength];

        for (int i = 0; i < inputSymbolsLength; i += 1) {
            Match match = bt.longestPrefixMatch(masterSequence);
            symbols[i] = match.getSymbol();
            masterSequence = masterSequence.allButFirstNBits(match.getSequence().length());
        }


        FileUtils.writeCharArray(newFilename, symbols);



    }



}
