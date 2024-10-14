/*
 * Create a data type ChemicalElement for entries in the
Periodic Table of Elements. Include data-type values for element, atomic number,
symbol, and atomic weight, and accessor methods for each of these values. Then
create a data type PeriodicTable that reads values from a ﬁle to create an array of
ChemicalElement objects (you can ﬁnd the ﬁle and a description of its formation
on the booksite) and responds to queries on standard input so that a user can type
a molecular equation like H2O and the program responds by printing the molecular
weight. Develop APIs and implementations for each data type.
 */
import lib.In;



public class ChemicalElement {
    private String element;
    private int atomicNumber;
    private String symbol; 
    private double atomicWeight;
    private double boilingPoint;
    private double meltingPoint;
    private double density; 
    private double vapour;
    private double fusion;

    public ChemicalElement(String element, int atomicNumber, String symbol, 
                           double atomicWeight, double boilingPoint, double meltingPoint,
                           double density, double vapour, double fusion) {
        this.element = element;
        this.atomicNumber = atomicNumber;
        this.symbol = symbol;
        this.atomicWeight = atomicWeight;
        this.boilingPoint = boilingPoint;
        this.meltingPoint = meltingPoint;
        this.density = density;
        this.vapour = vapour;
        this.fusion = fusion;
    }
    // toString method
    @Override
    public String toString() {
        return String.format("Element: %s, Symbol: %s, Atomic Number: %d, Atomic Weight: %.2f, Density: %.2f",
                             element, symbol, atomicNumber, atomicWeight, density);
    }

    public static void main (String[] args) {
         In in = new In(args[0]);
         
            ChemicalElement[] periodicTable = new ChemicalElement[104];
            //read headers
            in.readLine();  
            
            for (int i = 0; i < periodicTable.length; i++) {
                if (in.hasNextLine()) {
                    String line = in.readLine();
                    String[] data = line.split(",");
                    if (data.length != 9) {
                        System.out.println("Invalid data line, skipping: " + line);
                        continue;
                    }
                    String el = data[0];
                    int num = Integer.parseInt(data[1]);
                    String sy = data[2];
                    double aw =  Double.parseDouble(data[3]);
                    double bp = Double.parseDouble(data[4]);
                    double mp = Double.parseDouble(data[5]);
                    double den = Double.parseDouble(data[6]);
                    double vp = Double.parseDouble(data[7]);
                    double fu = Double.parseDouble(data[8]);
        
                    
                    periodicTable[i] = new ChemicalElement(el, num, sy, aw, bp, mp, den, vp, fu);
                }
            }
            
        } 
    }
