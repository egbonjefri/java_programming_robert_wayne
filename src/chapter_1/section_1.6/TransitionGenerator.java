import java.util.Random;

public class TransitionGenerator {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java TransitionGenerator <pageCount> <linkCount>");
            return;
        }

        int pageCount = Integer.parseInt(args[0]);
        int linkCount = Integer.parseInt(args[1]);
        Random rand = new Random();

        // Print the page count
        System.out.println(pageCount);

        // Generate and print m random links
        for (int i = 0; i < pageCount; i++) {
            int pairs = rand.nextInt(linkCount);
            if(pairs%2>0){pairs--;};
            for(int j = 0; j <= pairs; j++){
                int fromPage = rand.nextInt(pageCount);
                int toPage = rand.nextInt(pageCount);
                if(fromPage != toPage){
                System.out.print(fromPage + " " + toPage + " ");
                }
        }
        System.out.println(" ");
    }
    }
}
