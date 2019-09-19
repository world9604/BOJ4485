import java.util.*;

public class Main {
    // https://www.acmicpc.net/problem/4485
    public static void main(String[] args) {
        final int INF = Integer.MAX_VALUE;

        int[][] rupees;
        int[][] result;
        int problemNum = 0;

        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        do {
            rupees = new int[num][num];
            result = new int[num][num];

            for (int i = 0; i < num; i++) {
                for (int k = 0; k < num; k++) {
                    rupees[i][k] = sc.nextInt();
                    result[i][k] = INF;
                }
            }

            int answer = new Dijkstra(rupees, result).start(num);
            problemNum++;
            System.out.println();
            System.out.printf("Problem %d: %d", problemNum, answer);
            num = sc.nextInt();
        } while (num != 0);
    }
}

class Dijkstra {
    final int[] dx = {0, 0, -1, 1};
    final int[] dy = {1, -1, 0, 0};
    int[][] rupees;
    int[][] result;
    Queue<Node> pq = new PriorityQueue<>();

    Dijkstra(int[][] rupees, int[][] result) {
        this.rupees = rupees;
        this.result = result;
    }

    int start(int num) {
        pq.add(new Node(0,0, rupees[0][0]));

        while (!pq.isEmpty()) {
            int curX = pq.peek().x;
            int curY = pq.peek().y;
            int cDistance = pq.peek().cost;
            pq.remove();
            for (int i = 0; i < 4; i++) {
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];

                if (nextX >= 0 && nextY >= 0 && nextX < num && nextY < num) {
                    if (result[nextX][nextY] > cDistance + rupees[nextX][nextY]) {
                        result[nextX][nextY] = cDistance + rupees[nextX][nextY];
                        pq.add(new Node(nextX, nextY, result[nextX][nextY]));
                    }
                }
            }
        }
        return result[num - 1][num - 1];
    }
}

class Node implements Comparable<Node> {
    int x;
    int y;
    int cost;

    Node(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}