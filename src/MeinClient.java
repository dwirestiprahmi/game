import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MeinClient {

    public static int count = 0;
    public static int [] values = {4, 6, 7, 9, 1, 2, 0, 1, 8, 1, 9, 2, 6, 4, 7, 9, 3, 4, 1, 5, 5, 2, 3, 1, 1, 2, 2, 4, 2, 4, 1, 2};
    // spielen in Uhrzeigersinn
    public static ArrayList<String> boardConfig = new ArrayList<>(
            Arrays.asList(
                    "", "", "p1", "p1", "p1", "", "",
                    "", "", "", "p1", "", "", "",
                    "p2", "", "", "", "", "", "p4",
                    "p2", "p2", "", "", "", "p4", "p4",
                    "p2", "", "", "", "", "", "p4",
                    "", "", "", "p3", "", "", "",
                    "", "", "p3", "p3", "p3", "", ""
            ));

    public static ArrayList<String> rotateClockwise(ArrayList<String> board, int size) {
        ArrayList<String> newBoard = new ArrayList<>(Arrays.asList(new String[size * size]));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int newIndex = j * size + (size - 1 - i);
                int oldIndex = i * size + j;
                newBoard.set(newIndex, board.get(oldIndex));
            }
        }
        return newBoard;
    }

    public static void main(String[] args) throws IOException {

        Player player_1 = new Player(1, "p1", true);
        Player player_2 = new Player(2, "p2", false);
        Player player_3 = new Player(3, "p3", false);
        Player player_4 = new Player(4, "p4", false);
        int depth = 5;
        Player[] players = {player_1, player_2, player_3, player_4};
        Node node = new Node();
        Node NodeTree = node.generateChildren(depth, players);
        int resultEvaluation = alphaBeta(NodeTree, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, true);

        System.out.println("Best Move: " + resultEvaluation);
        printChildren(5, NodeTree);

//        int [] values = {3, 4, 5, 1, 3, 2, 3, 1, 4, 3, 5, 2, 1, 4, 2, 2, 3, 4, 1, 5, 5, 2, 3, 1, 1, 2, 2, 4, 2, 4, 1, 2};
        int [] values = {4, 6, 7, 9, 1, 2, 0, 1, 8, 1, 9, 2, 6, 4, 7, 9, 8, 1, 9, 2, 1, 2, 0, 1, 4, 6, 7, 9, 7, 6, 9, 4};
        String[][] board = {
                {"", "", "p1", "p1", "p1", "", ""},
                {"", "", "", "p1", "", "", ""},
                {"p2", "", "", "", "", "", "p4"},
                {"p2", "p2", "", "", "", "p4", "p4"},
                {"p2", "", "", "", "", "", "p4"},
                {"", "", "", "p3", "", "", ""},
                {"", "", "p3", "p3", "p3", "", ""}
        };
        ArrayList<ArrayList<String>> gameBoard = new ArrayList<>();

        // Initialize each row and add it to the board
        gameBoard.add(new ArrayList<>(Arrays.asList("", "", "p1", "p1", "p1", "", "")));
        gameBoard.add(new ArrayList<>(Arrays.asList("", "", "", "p1", "", "", "")));
        gameBoard.add(new ArrayList<>(Arrays.asList("p2", "", "", "", "", "", "p4")));
        gameBoard.add(new ArrayList<>(Arrays.asList("p2", "p2", "", "", "", "p4", "p4")));
        gameBoard.add(new ArrayList<>(Arrays.asList("p2", "", "", "", "", "", "p4")));
        gameBoard.add(new ArrayList<>(Arrays.asList("", "", "", "p3", "", "", "")));
        gameBoard.add(new ArrayList<>(Arrays.asList("", "", "p3", "p3", "p3", "", "")));
        // check alpha beta
        // konfigurasi (array)
        // rotate konfigurasi di eval
        // bikin possible moves
        // function buat update konfigurasi / balikkin
        // function buat check possible moves: kalo move nya valid -> simpen move nya di node -> add child
        // genialen move berechnen
        // bester Zug -> di convert ke 1D dulu
//            move = new Move(2, 9);
//            client.sendMove(move);
//            move = new Move(9, 16);
//            client.sendMove(move);
//            move = new Move(16, 23);
//            client.getMyPlayerNumber();
        int boardSize = 7; // Since the board is 7x7
        ArrayList<String> rotatedBoard = rotateClockwise(boardConfig, boardSize);

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print(rotatedBoard.get(i * boardSize + j) + "\t");
            }
            System.out.println();
        }
        Integer num = 2;
        test(num);
        System.out.println(num);
    }

    public static int alphaBeta(Node node, int depth, int alpha, int beta, boolean maximizingPlayer) {
        if (depth == 0) {
            int eval = evaluate(node);
//            node.setValue(eval, conf);
            //node.setValue(eval);
            return eval;
        }

        if (maximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;

            for (Node child : node.getChildren()) {
                int eval = alphaBeta(child, depth - 1, alpha, beta, child.isMaximizer());
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);

                if (beta <= alpha) {
                    break;
                }child.setValue(maxEval);
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (Node child : node.getChildren()) {
                int eval = alphaBeta(child, depth - 1, alpha, beta, child.isMaximizer());
//                node.setValue(eval);
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);

                if (beta <= alpha) {
                    break;
                }child.setValue(minEval);
            }
            return minEval;
        }
    }

    boolean isGameOver(Node nodePosition) {
        ArrayList<Node> children = nodePosition.getChildren();
        Random random = new Random();
        return random.nextBoolean();
    }

    // Evaluate the state of board in a node
    static int evaluate(Node nodePosition){
        //Random rand = new Random();
        //int value = (rand.nextInt(5)+1);
        int value = values[count];
        nodePosition.setValue(value);
        System.out.println(nodePosition.getValue());
        count++;
        return value;
    }

    public static void test(int number){
        Integer number2 = 0;
        number = 0 + 5;
    }

    public static void printChildren(int depth, Node nodeTree) {
        System.out.println("---------- Node ----------");
        System.out.println("Depth: " + depth);
        System.out.println("No. children: " + nodeTree.getChildren().size());
        System.out.println("Minimax value: " + nodeTree.getValue());
        if (depth == 0) {
            return;
        } else {
            int i = 0;
            for (Node child: nodeTree.getChildren()) {
                printChildren(depth - 1, child);
                System.out.println("Child no. : " +(i+1));
                i++;
            }
        }
    }
}
