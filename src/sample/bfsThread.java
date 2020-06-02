package sample;

import java.util.Iterator;
import java.util.LinkedList;

public class bfsThread extends Thread {

    //
    FxmlController fxmlController;
    bfs2Controller bfs2Controller;


    int animation=0;


    private static int vertex,source;   // No. of vertices
    private LinkedList<Integer> adj[]; //Adjacency Lists

    Thread bfs;

    bfsThread(int v,int s,FxmlController c)   //it will generate the graph
    {
        fxmlController=c;
        vertex = v;
        source=s;
        adj = new LinkedList[v]; ///for the nodes
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList(); ///associated linked list for those nodes conected to each node of previous one

        bfs=new Thread(this);
        bfs.start();

    }

    bfsThread(int v,int s,bfs2Controller c)   //it will generate the graph
    {
        bfs2Controller=c;
        vertex = v;
        source=s;
        adj = new LinkedList[v]; ///for the nodes
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList(); ///associated linked list for those nodes conected to each node of previous one

        bfs=new Thread(this);
        bfs.start();

    }

    // Function to add an edge into the graph
    void addEdge(int v,int w)
    {
        adj[v].add(w);  ///v is part of 12Line and w is part of 14 Line
    }

    @Override
    public void run() {
        System.out.println("started");

        LinkedList<Integer> list = new LinkedList<>();

        // Mark all the vertices as not visited(By default set as false)
        boolean visited[] = new boolean[vertex];

        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();  //keep track of next pointer

        // Mark the current node as visited and enqueue it
        visited[source]=true;
        queue.add(source);

        System.out.println(queue.size());

        while (queue.size() != 0)
        {
            // Dequeue a vertex from queue and print it
            source = queue.poll();
            list.add(source);
            System.out.println(source);


            ////////circle red krbe.
            if (fxmlController!=null) {
                fxmlController.circleRed(source);
            }
            else {
                bfs2Controller.circleRed(source);
            }


            try {
                bfs.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            //System.out.print(s+" ");

            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<Integer> i = adj[source].listIterator();
            while (i.hasNext())
            {
                int n = i.next();



                /////n r s er moddhe edge er animation dekhate hobe
                if (!visited[n])
                {
                    visited[n] = true;
                    queue.add(n);
                    System.out.println("anim   "+source+"\t"+n);



                    ////////animation
                    if (fxmlController!=null) {
                        fxmlController.createEdge(source, n);
                    }
                    else {
                        bfs2Controller.createEdge(source, n);
                    }
                    /*animation++;
                    fxmlController.createEdge(animation);*/


                    try {
                        bfs.sleep(1300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            }
        }

        System.out.println(list);


    }
}
