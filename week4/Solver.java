import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.HashSet;
import java.util.Set;

public class Solver 
{
    private boolean solvable;
    private Stack<Board> solution = null;
    private SearchNode searchNode;

    private static class SearchNode implements Comparable<SearchNode> 
    {
        private Board board;
        private int moves;
        private SearchNode previous;

        public SearchNode(Board board, SearchNode previous) 
        {
            this.board = board;
            if (previous != null)
            {
                this.moves = previous.moves++;
                this.previous = previous;
            } 
            else
            {
                this.moves++;
            }
        }

        @Override
        public int compareTo(SearchNode o) 
        {
            return (this.board.manhattan() + this.moves)
                    - (o.board.manhattan() + o.moves);
        }
    }

    public Solver(Board initial) 
    {
        MinPQ<SearchNode> nodes = new MinPQ<SearchNode>();
        MinPQ<SearchNode> twinNodes = new MinPQ<SearchNode>();
        
        Set<Integer> hashes = new HashSet<Integer>();
        Set<Integer> twinHashes = new HashSet<Integer>();
        
        SearchNode searchTwinNode;

        boolean solved = false;
        Stopwatch watch = new Stopwatch();

        searchNode = new SearchNode(initial, null);
        nodes.insert(searchNode);

        searchTwinNode = new SearchNode(initial.twin(), null);
        twinNodes.insert(searchTwinNode);

        while (true) 
        {
            if (!nodes.isEmpty())
            {
                searchNode = nodes.delMin();
            }
            else
            {
                break;
            }

            if (searchNode.board.isGoal())
            {
                solvable = true;
                break;
            }

            if (!twinNodes.isEmpty())
            {
                searchTwinNode = twinNodes.delMin();
            }
            else
            {
                break;
            }

            if (searchTwinNode.board.isGoal()) 
            {
                break;
            }

            for (Board neighbor : searchNode.board.neighbors()) 
            {
                insertNeighboringSearchNodes(nodes, hashes, neighbor);
            }

            for (Board neighbor : searchTwinNode.board.neighbors()) 
            {
                insertNeighboringSearchNodes(twinNodes, twinHashes, neighbor);
            }

            if (watch.elapsedTime() > 7)
            {
                break;
            }
        }
    }

    private void insertNeighboringSearchNodes(
        MinPQ<SearchNode> nodes, 
        Set<Integer> hashes, 
        Board neighbor
    ) 
    {
        int hashCode;
        hashCode = neighbor.toString().hashCode();
        
        if (!hashes.contains(hashCode)) 
        {
            nodes.insert(new SearchNode(neighbor, searchNode));
            hashes.add(hashCode);
        }
    }

    public boolean isSolvable() 
    {
        return solvable;
    }

    public int moves() 
    {
        if (solvable) 
        {
            return createStackSolution().size() - 1;
        } 
        else
        {
            return -1;
        }
    }

    public Iterable<Board> solution() 
    {
        if (isSolvable())
        {
            return createStackSolution();
        }
        
        return null;
    }

    private Stack<Board> createStackSolution() 
    {
        if (solution == null)
        {
            solution = new Stack<Board>();

            while (searchNode != null) 
            {
                solution.push(searchNode.board);
                searchNode = searchNode.previous;
            }
        }
        
        return solution;
    }

    public static void main(String[] args) 
    {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                blocks[i][j] = in.readInt();
            }
        }
        
        Board initial = new Board(blocks);

        Solver solver = new Solver(initial);

        if (!solver.isSolvable())
        {
            StdOut.println("No solution possible");
        }
        else 
        {
            StdOut.println("Minimum number of moves = " + solver.moves());
            
            for (Board board : solver.solution())
            {
                StdOut.println(board);
            }
        }
    }
}
