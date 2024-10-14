/*
 * Write a ﬁlter that reads a sequence of domain
names from standard input and prints the reverse domain names in sorted order.
For example, the reverse domain name of cs.princeton.edu is edu.princeton.
cs. This computation is useful for web log analysis. To do so, create a data type
Domain that implements the Comparable interface (using reverse-domain-name
order).
 */


 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.List;
 import lib.StdIn;
 import lib.StdOut;

public class Domain implements Comparable<Domain> {
    private String domainName;
    private String reversedDomainName;

    public Domain(String domainName) {
        this.domainName = domainName;
        this.reversedDomainName = reverseDomainName(domainName);
    }

    // Method to reverse the domain name
    private String reverseDomainName(String domainName) {
        String[] parts = domainName.split("\\.");
        StringBuilder reversed = new StringBuilder();
        for (int i = parts.length - 1; i >= 0; i--) {
            reversed.append(parts[i]);
            if (i > 0) {
                reversed.append(".");
            }
        }
        return reversed.toString();
    }

    // Implement the compareTo method
    @Override
    public int compareTo(Domain other) {
        return this.reversedDomainName.compareTo(other.reversedDomainName);
    }

    @Override
    public String toString() {
        return domainName;
    }

    // Getter for the reversed domain name (for testing purposes)
    public String getReversedDomainName() {
        return reversedDomainName;
    }
    public static void main(String[] args) {
        List<Domain> domains = new ArrayList<>();

        // Read domains from standard input
        while (!StdIn.isEmpty()) {
            String domainName = StdIn.readLine();
            if (!domainName.isEmpty()) {
                domains.add(new Domain(domainName));
            }
        }

        // Sort the domains based on reversed domain name order
        Collections.sort(domains);

        // Print the sorted domains
        for (Domain domain : domains) {
            StdOut.println(domain);
        }

    }
}
