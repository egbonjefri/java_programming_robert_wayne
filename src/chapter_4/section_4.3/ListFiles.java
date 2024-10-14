import java.io.File;
import lib.Queue;
import lib.StdOut;
/*
 * A folder is a list of ﬁles and folders. Write a program that takes
the name of a folder as a command-line argument and prints all of the ﬁles con-
tained in that folder, with the contents of each folder recursively listed (indented)
under that folder’s name. Hint : Use a queue, and see java.io.File.
 */
public class ListFiles {

    public static void main(String[] args) {
        if (args.length != 1) {
            StdOut.println("Usage: java ListFilesWithQueue <directory>");
            return;
        }

        File startingDir = new File(args[0]);
        if (!startingDir.exists() || !startingDir.isDirectory()) {
            StdOut.println("The specified path is not a directory or does not exist.");
            return;
        }

        listFiles(startingDir);
    }

    private static void listFiles(File startingDir) {
        Queue<File> queue = new Queue<>();
        Queue<Integer> levelQueue = new Queue<>();
        
        queue.enqueue(startingDir);
        levelQueue.enqueue(0);

        while (!queue.isEmpty()) {
            File currentDir = queue.dequeue();
            int currentLevel = levelQueue.dequeue();

            File[] files = currentDir.listFiles();
            if (files == null) {
                StdOut.println("An error occurred accessing directory: " + currentDir.getName());
                continue;
            }

            for (File file : files) {
                StdOut.printIndented(file.getName(), currentLevel);

                if (file.isDirectory()) {
                    queue.enqueue(file);
                    levelQueue.enqueue(currentLevel + 1);
                }
            }
        }
    }


}
