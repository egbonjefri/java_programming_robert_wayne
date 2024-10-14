import lib.ST;
import lib.StdRandom;
import lib.StdOut;
import lib.StdIn;
import lib.In;

/*
 * This ST client reads key–value pairs from a comma-separated file, then prints values corre-
sponding to keys on standard input. Both keys and values are strings
 */
public class Lookup {
    public static void main(String[] args) {
        if (args.length != 3) {
            StdOut.println("Usage: java Lookup <filename> <keyField> <valField>");
            System.exit(1);
        }

        In filename = new In(args[0]);
        int keyField = Integer.parseInt(args[1]);
        int valField = Integer.parseInt(args[2]);

        String[] database = filename.readAllLines();
        StdRandom.shuffle(database);

        ST<String, String> dictionary = new ST<String, String>();

        for(String str : database){
            String[] tokens = str.split(",");
            String key = tokens[keyField];
            String val = tokens[valField];
            dictionary.put(key, val);
        }

        while (!StdIn.isEmpty()){
            String s = StdIn.readString();
            String value = dictionary.get(s);
            StdOut.println(value != null ? value : "Key not found");
        }
        
    }
}