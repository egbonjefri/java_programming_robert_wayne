/*
 * Write a BST client that uses the data ﬁle ip-to-
country.csv found on the booksite to determine the source country of a given
IP address. The data ﬁle has ﬁve ﬁelds: beginning of IP address range, end of IP
address range, two-character country code, three-character country code, and
country name. The IP addresses are non-overlapping. Such a database tool can be
used for credit card fraud detection, spam ﬁltering, auto-selection of language on a
website, and web-server log analysis.
 */

import lib.StdIn;
import lib.StdOut;
import lib.In;


public class IPToCountry {

    // Node class for the Binary Search Tree
    static class Node {
        long startIP;
        long endIP;
        String countryCode;
        String countryName;
        Node left, right;

        Node(long startIP, long endIP, String countryCode, String countryName) {
            this.startIP = startIP;
            this.endIP = endIP;
            this.countryCode = countryCode;
            this.countryName = countryName;
            this.left = this.right = null;
        }
    }

    // Binary Search Tree (BST) class
    static class BST {
        private Node root;

        // Insert a new IP range into the BST
        public void insert(long startIP, long endIP, String countryCode, String countryName) {
            root = insertRec(root, startIP, endIP, countryCode, countryName);
        }

        private Node insertRec(Node root, long startIP, long endIP, String countryCode, String countryName) {
            if (root == null) {
                root = new Node(startIP, endIP, countryCode, countryName);
                return root;
            }

            if (startIP < root.startIP) {
                root.left = insertRec(root.left, startIP, endIP, countryCode, countryName);
            } else if (startIP > root.startIP) {
                root.right = insertRec(root.right, startIP, endIP, countryCode, countryName);
            }

            return root;
        }

        // Search for the country by IP address
        public String search(long ipAddress) {
            return searchRec(root, ipAddress);
        }

        private String searchRec(Node root, long ipAddress) {
            if (root == null) {
                return "IP not found";
            }

            if (ipAddress >= root.startIP && ipAddress <= root.endIP) {
                return root.countryName + " (" + root.countryCode + ")";
            }

            if (ipAddress < root.startIP) {
                return searchRec(root.left, ipAddress);
            }

            return searchRec(root.right, ipAddress);
        }
    }

    // Convert IP address to numeric form
    public static long ipToLong(String ipAddress) {
        String[] ipAddressInArray = ipAddress.split("\\.");
        long result = 0;

        for (int i = 0; i < ipAddressInArray.length; i++) {
            int power = 3 - i;
            int ip = Integer.parseInt(ipAddressInArray[i]);
            result += ip * Math.pow(256, power);
        }

        return result;
    }

    public static void main(String[] args) {
        BST bst = new BST();
        In in = new In(args[0]);
         while(!in.isEmpty()){
            in.readLine(); // read headers
            String line = in.readLine();
            String[] fields = line.split(",");
            long startIPNum = Long.parseLong(fields[2]);
            long endIPNum = Long.parseLong(fields[3]);
            String countryCode = fields[4];
            String countryName = fields[5];

            // Insert IP range and country data into the BST
            bst.insert(startIPNum, endIPNum, countryCode, countryName);

         }

        StdOut.println("Enter an IP address:");
        String ipAddress = StdIn.readLine();
        long ipNumber = ipToLong(ipAddress);

        // Search in the BST for the country
        String country = bst.search(ipNumber);
        StdOut.println("Country: " + country);

    }
}
