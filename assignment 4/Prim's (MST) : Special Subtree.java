import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;


    class Node
    {
        int dest;
        int edge;
        Node(int x,int y)
        {
            dest=x;
            edge=y;
        }
    }

public class Solution{

    // Complete the prims function below

    public static class Comp implements Comparator
{
    public int  compare(Object o1,Object o2){
            ArrayList<Integer> al1=(ArrayList<Integer>)o1;
            ArrayList<Integer> al2=(ArrayList<Integer>)o2;
            return al1.get(2)-al2.get(2);
    }
}


    static ArrayList<Integer> add_list(int n,int m, int q)
    {
        ArrayList<Integer> al=new ArrayList<>();
        al.add(n);
        al.add(m);
        al.add(q);
        return al;

    }

    static int prims(int n, int[][] edges, int start) {
        ArrayList<ArrayList<Node>> list = new ArrayList<>();
        for(int i=0;i<=n;i++)
        {
            list.add(new ArrayList<Node>());
        }
        // PriorityQueue<ArrayList<Integer>> pq=new PriorityQueue<>(new Comp());
        int i,j;
        for(i=0;i<edges.length;i++)
        {
            int p=edges[i][0];
            int q=edges[i][1];
            int edge=edges[i][2];
            list.get(p).add(new Node(q,edge));
            list.get(q).add(new Node(p,edge));
            // pq.add(add_list(p,q,edge));
        }
        ArrayList<Integer> al=new ArrayList<>();
        int cost=0;
        // System.out.println(pq);
        boolean vis[]=new boolean[n+1];
        // while(!pq.isEmpty())
        // {
        //     al=pq.poll();
        //     if(!vis[al.get(0)] || !vis[al.get(1)]){
        //         vis[al.get(0)]=true;
        //         vis[al.get(1)]=true;
        //         // System.out.println(al);
        //         cost+=al.get(2);
        //     }
        //     else
        //         i-=1;
        // }
        int[] dist=new int[n+1];
        for(i=0;i<n+1;i++)
            dist[i]=Integer.MAX_VALUE;
        dist[start]=0;
        for(i=0;i<n-1;i++)
        {
            int minInd = 0;
            int min = Integer.MAX_VALUE;
            for(j = 0;j<=n; j++){
                if(!vis[j] && min>dist[j]){
                    min=dist[j];
                    minInd = j;
                }
            }
            vis[minInd]=true;
            for(Node tmp:list.get(minInd)){
                if(!vis[tmp.dest] && tmp.edge<dist[tmp.dest])
                    dist[tmp.dest]=tmp.edge;
            }
        }
        for(i=1;i<=n;i++)
        cost+=dist[i];
        return cost;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] edges = new int[m][3];

        for (int i = 0; i < m; i++) {
            String[] edgesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int edgesItem = Integer.parseInt(edgesRowItems[j]);
                edges[i][j] = edgesItem;
            }
        }

        int start = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int result = prims(n, edges, start);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
