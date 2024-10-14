public class TwoDimensionalBoolean {
    public static void main(String args[]) {
      int n = Integer.parseInt(args[0]);
      
      boolean a[][] = new boolean [n][n];
      // fill the 2d array with random true or false values
      for(int i = 0; i < n; i++){
          for(int j = 0; j < n; j++){
              if(Math.random() < 0.5){
                  a[i][j] = true;
              }
          }
      }
    //create left indentation for column indices
    System.out.print("  ");
    //loop through column indices
        for (int i = 0; i < n; i++) {
             System.out.print(i + " ");
        } 
    //separate line
    System.out.println(" ");
//loop through row indices
for(int i = 0; i < n; i++){
        System.out.print( i+" ");
          for(int j = 0; j < n; j++){
              if(a[i][j]){
                 System.out.print("* ");
              }
              else{
                  System.out.print("  ");
              }
          }
        System.out.println(" ");
      }
      
    }
}