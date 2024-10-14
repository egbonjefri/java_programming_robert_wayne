/*
 * You have to sequence the order of n jobs that are num-
bered from 0 to n-1 on a server. Some of the jobs must complete before others can
begin. Write a program TopologicalSorter that takes a command-line argument
n and a sequence on standard input of ordered pairs of jobs i j, and then prints a
sequence of integers such that for each pair i j in the input, job i appears before
job j. Use the following algorithm: First, from the input, build, for each job, (i ) a
queue of the jobs that must follow it and (ii ) its indegree (the number of jobs that
must come before it). Then, build a queue of all nodes whose indegree is 0 and
repeatedly delete any job with a 0 indegree, maintaining all the data structures.
This process has many applications. For example, you can use it to model course
prerequisites for your major so that you can ﬁnd a sequence of courses to take so
that you can graduate.
 */

 //Topological sorting is a linear ordering of the vertices
 // in a directed graph where each vertex comes before its adjacent vertices.
 //Topological sorting is applicable only to directed acyclic graphs (DAGs).
 //Helps in scheduling tasks or processes with dependencies in a logical order.


 /*
  * This program uses
  Kahn's algorithm for topological sorting
  */
import lib.StdOut;
import lib.ArrayList;
import lib.Queue;
import lib.StdIn;

  public class TopologicalSorter {
      public static void main(String[] args) {
          int n = Integer.parseInt(args[0]); // Number of jobs
          ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
          for (int i = 0; i < n; i++) {
              adj.add(new ArrayList<>());
          }
  
          // Read pairs of jobs
          while (!StdIn.isEmpty()) {
              int i = StdIn.readInt();
              int j = StdIn.readInt();
              adj.get(i).add(j);
          }
  
          // Perform topological sort
          topologicalSort(adj, n);
      }
  
      public static void topologicalSort(ArrayList<ArrayList<Integer>> adj, int n) {
          int[] indegree = new int[n];
  
          // Calculate indegrees
          for (int i = 0; i < n; i++) {
              for (int j : adj.get(i)) {
                  indegree[j]++;
              }
          }
  
          // Initialize queue with jobs having indegree 0
          Queue<Integer> queue = new Queue<>();
          for (int i = 0; i < n; i++) {
              if (indegree[i] == 0) {
                  queue.enqueue(i);
              }
          }
  
          ArrayList<Integer> result = new ArrayList<>();
  
          // Process jobs
          while (!queue.isEmpty()) {
              int job = queue.dequeue();
              result.add(job);
  
              // Decrease indegree of neighbors
              for (int neighbor : adj.get(job)) {
                  indegree[neighbor]--;
                  if (indegree[neighbor] == 0) {
                      queue.enqueue(neighbor);
                  }
              }
          }
  
          // Print the result
          for (int job : result) {
              StdOut.print(job + " ");
          }
      }
  }

/*
 *
Take the number of jobs n as a command-line argument.
Read pairs of jobs from standard input, where each pair i j indicates that job i must be completed before job j.
Use an adjacency list to represent the graph of job dependencies.
Maintain an array to keep track of the indegree of each job.
Initialize a queue with all jobs that have an indegree of 0.
Remove a job from the queue and add it to the result list.
Decrease the indegree of all its neighboring jobs.
If any neighboring job's indegree becomes 0, add it to the queue.
Print the jobs in the topologically sorted order.
 */