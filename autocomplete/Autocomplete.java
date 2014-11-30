import java.lang.*;
//import BinarySearchDeluxe;
//import Term;
import java.util.Comparator;
import java.util.Arrays;

public class Autocomplete {
	public Term[] quries;

    // Initialize the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
    	if (terms == null) {
    		throw new java.lang.NullPointerException();
    	}
    	this.quries = terms;
    }

    // Return all terms that start with the given prefix, in descending order of weight.
    public Term[] allMatches(String prefix) {
    	if (prefix == null) {
    		throw new java.lang.NullPointerException();
    	}
    	Term temp = new Term(prefix, 0);
    	Arrays.sort(quries, Term.byPrefixOrder(prefix.length()));
    	//for (int j = 0; j < 50000 ; j += 2000 ) {
    	//	StdOut.println(quries[j].query);
    	//}
    	StdOut.println("Binarysearch try");
    	StdOut.println(Term.byPrefixOrder(3).compare(new Term("sadf", 0), new Term("saef", 0)));
    	

    	int i = BinarySearchDeluxe.firstIndexOf(quries, temp, Term.byPrefixOrder(prefix.length()));
    	int j = BinarySearchDeluxe.lastIndexOf(quries, temp, Term.byPrefixOrder(prefix.length()));
    	StdOut.println(i);
    	StdOut.println(j);	
    	StdOut.println(quries[i].query);
    	StdOut.println(quries[j].query);
		Term[] matches = new Term[j - i + 1];
		matches = Arrays.copyOfRange(quries, i, j);
		Arrays.sort(matches, Term.byReverseWeightOrder());
		return matches;

    }

    // Return the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
    	if (prefix == null) {
    		throw new java.lang.NullPointerException();
    	}
    	Term temp = new Term(prefix, 0);
    	int i = BinarySearchDeluxe.firstIndexOf(quries, temp, Term.byPrefixOrder(prefix.length()));
    	int j = BinarySearchDeluxe.lastIndexOf(quries, temp, Term.byPrefixOrder(prefix.length()));
		return j - i + 1;
    }

    public static void main(String[] args) {

    // read in the terms from a file
    String filename = args[0];
    In in = new In(filename);
    int N = in.readInt();
    Term[] terms = new Term[N];
    for (int i = 0; i < N; i++) {
        double weight = in.readDouble();       // read the next weight
        in.readChar();                         // scan past the tab
        String query = in.readLine();          // read the next query
        terms[i] = new Term(query, weight);    // construct the term
    }

    // read in queries from standard input and print out the top k matching terms
    int k = Integer.parseInt(args[1]);
    Autocomplete autocomplete = new Autocomplete(terms);
    while (StdIn.hasNextLine()) {
        String prefix = StdIn.readLine();
        Term[] results = autocomplete.allMatches(prefix);
        for (int i = 0; i < Math.min(k, results.length); i++)
            StdOut.println(results[i]);
    }
}
}