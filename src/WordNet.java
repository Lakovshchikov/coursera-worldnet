import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

public class WordNet {

    private ST<Integer, String[]> nounST;
    private ST<Integer, Integer[]> hypernymsST;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) {
            throw new IllegalArgumentException();
        }
        nounST = new ST<Integer, String[]>();
        hypernymsST = new ST<Integer, Integer[]>();
        In inSynsets = new In(synsets);
        while (inSynsets.hasNextLine()) {
            String[] line = inSynsets.readLine().split(",");
            int key = Integer.parseInt(line[0]);
            String[] nouns = line[1].split(" ");
            nounST.put(key, nouns);
        }
        In inHypernyms = new In(hypernyms);
        while (inHypernyms.hasNextLine()) {
            String[] line = inSynsets.readLine().split(",");
            int key = Integer.parseInt(line[0]);
            Integer[] values = new Integer[line.length-1];
            for(int i = 0; i< values.length; i++ ) {
                values[i] = Integer.parseInt(line[i+1]);
            }
            hypernymsST.put(key, values);
        }
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        Queue<String> queue = new Queue<String>();
        int size = nounST.size();
        for (int i = 0; i < size; i++) {
            String [] worlds = nounST.get(i);
            for (int j = 0; j < worlds.length; j++) {
                queue.enqueue(worlds[i]);
            }
        }
        return queue;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return false;
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        return 1;
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        return "";
    }

    // do unit testing of this class
    public static void main(String[] args) {
        WordNet WN = new WordNet("./test/synsets.txt", "./test/digraph.txt");
    }
}
