import java.io.*;
import java.util.*;

class Dijkstra { 
    public static String[] alpha = {"A","B","C","D","E"}; 

    private static void dijkstra(int[][] matrix, int source) 
    { 
        int len = matrix[0].length; 
        int[] min_dist = new int[len];
        boolean[] visited = new boolean[len]; 
        for (int index = 0; index < len; index++) 
        { 
            min_dist[index] = 9999; 
            visited[index] = false; 
        } 
        min_dist[source] = 0; 
        int[] parents = new int[len]; 
        parents[source] = -1; 
        for (int i = 1; i < len; i++) 
        { 
            int adj_node = -1; 
            int shortest_path = 9999; 
            for (int index = 0; index < len;  index++) 
            { 
                if (!visited[index] && min_dist[index] <  shortest_path)  
                { 
                    adj_node = index; 
                    shortest_path = min_dist[index]; 
                } 
            } 
            visited[adj_node] = true; 
            for (int index = 0; index < len;  index++)  
            { 
                int weight = matrix[adj_node][index]; 
                  
                if (weight > 0  && ((shortest_path + weight) < min_dist[index]))  
                { 
                    parents[index] = adj_node; 
                    min_dist[index] = shortest_path + weight; 
                } 
            } 
        }   
        output(source, min_dist, parents); 
    } 
    private static void output(int source, int[] distances, int[] parents) 
    { 
        int len = distances.length;
          
        for (int index = 0; index < len; index++)  
        { 
            if (index != source)  
            { 
                String n1 = alpha[source];
                String n2 = alpha[index];
            	System.out.print("\n" + n1 + " -> " + n2 + " ("); 
                get_path(index, parents);
                System.out.print(": ");
                System.out.print(distances[index]); 
                System.out.print(")");
            } 
        } 
    } 
    private static void get_path(int current_node, int[] parents) 
    { 
        if (current_node == -1) 
        { 
            return; 
        } 
        get_path(parents[current_node], parents); 
        System.out.print(alpha[current_node] + ", "); 
    } 
  
    public static void main(String[] args) throws FileNotFoundException 
    {
    	System.out.print("Enter no. of nodes:\n");
        Scanner inp = new Scanner(System.in);
        int n = inp.nextInt();
    	String f = "input.txt";
    	Scanner s = new Scanner(new FileReader(f));
		int[][] adjacent = new int[n][n];
		while(s.hasNext()){
			try{
					String source = s.next();
					String dest = s.next();
					String weigh = s.next();
					adjacent[Arrays.asList(alpha).indexOf(source)][Arrays.asList(alpha).indexOf(dest)]= Integer.parseInt(weigh);
					adjacent[Arrays.asList(alpha).indexOf(dest)][Arrays.asList(alpha).indexOf(source)]= Integer.parseInt(weigh);
			}
			catch(NoSuchElementException e){
				
			}
		}
		System.out.print("Adjacency Matrix\n");
		for(int i = 0; i < n ; i++){
			for(int j=0 ; j < n ; j++){
				System.out.print(adjacent[i][j] +" ");
			}
			System.out.print("\n");
		} 
        System.out.print("Shortest Path from A"); 
		dijkstra(adjacent, 0);
        System.out.print("\nPlease Enter source node:\n");
        Scanner in = new Scanner(System.in);
        String i = in.next();
        int index = Arrays.asList(alpha).indexOf(i);
        System.out.print("Shortest Path from "+ i + ":");
        dijkstra(adjacent, index);
    } 
} 