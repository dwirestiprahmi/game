
import java.lang.reflect.Array;
import java.util.*;

public class Node {
    private String name;
    private int value;
    private boolean isMaximizer;
    private ArrayList<Node> children;
    private Player player;
    private Node parent = null;
    public static int counter = 0;
    public int[] stonePosition;
    public ArrayList<Integer>stoneSteps;
    public Map<String, ArrayList<Integer>> elimStone;
    public ArrayList<ArrayList<Node>> possibleMoves;
    public Node (String name, int value, boolean isMaximizer) {
        this.name = name;
        this.value=value;
        this.isMaximizer = isMaximizer;
    }
    //public int[] possibleMove = [2, 9];

    public Node() {

    }
    public Node (boolean isMaximizer, Player player) {
//        this.name = name;
        this.isMaximizer = isMaximizer;
        this.player = player;
        this.children = new ArrayList<>();
    }

    public void addChild(Node child){
        this.children.add(child);
    }

    public void addChildren(ArrayList<Node>children){
        this.children.addAll(children);
    }

    public ArrayList<Node> getChildren() {
        return this.children;
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {

        this.value = value;
    }

    public void addPossibleMoves(ArrayList<Node> possibleMoves) {
        this.possibleMoves.add(possibleMoves);
    }

    public boolean isMaximizer() {
        return isMaximizer;
    }

    public void setMaximizer(boolean maximizer) {
        isMaximizer = maximizer;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Node.counter = counter;
    }
    public Node getParent() {
        return parent;
    }

//    public Node generateChildren(int depth, Player[] player) {
//        if(depth == 0){
//            Node rootNode = new Node(((counter + 1) + " child"), getNextPlayer(depth, player).isMaximizer, getNextPlayer(depth, player));
//            rootNode.parent = this;
//            return rootNode;
//        }
//        else {
//            Player currPlayer = getNextPlayer(depth, player);
//            Node rootNode = new Node(((counter + 1) + " child"), getNextPlayer(depth, player).isMaximizer, getNextPlayer(depth, player));
//            int possibleMoves = getPossibleMoves(getNextPlayer(depth, player), depth);
//            for(int i= 0; i < possibleMoves; i++) {
//                String name = (counter + 1) + " child";
////                Random rand = new Random();
////                int value = (rand.nextInt(11)) - 10;
//                int nextPlayer = getNextPlayer(depth - 1, player).getIndexPlayer();
//                Node childNode = generateChildren(depth-1, player);
//                rootNode.addChild(childNode);
//            }
//            return rootNode;
//        }
//    }

    public List<Node> generatePossibleMoves(ArrayList<ArrayList<String>> currConfig) {
        int[][] pairs;

        Map<String, List<int[]>> valueToIndicesMap = new HashMap<>();
        List<int[]> indices = new ArrayList<>();
        List<Node> legalMoves = new ArrayList<>();
        String value = player.getName();
        //Iterate through the array and populate the HashMap
        for(int i = 0; i < currConfig.size(); i++){
            ArrayList<String> row = currConfig.get(i);
            for(int j = 0; j < row.size(); j++) {
//                int[] indices = new int[]{i, j};
//                if(!valueToIndicesMap.containsKey(value)){
//                    valueToIndicesMap.put(value, new ArrayList<>());
//                }
                if(currConfig.get(i).get(j).equals(value)){
                    indices.add(new int[]{i, j});
                }
//                valueToIndicesMap.get(value).add(indices);
            }
        }

//        for(Map.Entry<String, List<Integer[]>> entry: valueToIndicesMap.entrySet()){
        for(int[] index : indices) {
//            String val = entry.getKey();
//            List<Integer[]> indices = entry.getValue();
//            Map<int[], List<int[]>> possibleMoves = new HashMap<>();
            Player nextPlayer = getNextPlayer(player);
            boolean diagonalTaken = false;
//            possibleMoves.put(index, new ArrayList<>());
            int currPosX = index[0];
            int currPosY = index[1];
            int frontY = currPosY + 1;
            int leftDiagonalX = currPosX - 1;
            int leftDiagonalY = currPosY + 1;
            int rightDiagonalX = currPosX + 1;
            int rightDiagonalY = currPosY + 1;
            int nextPos = 0;
            // if pos = 42 - 48 -> am Rand -> kann nicht nach vorne gehen
//            if (!(nextPos > 49)) {
                // check the front position of current pos
                if (currConfig.get(currPosX).get(frontY).equals("")) {
                    // check if the cross position not empty
                    if (!currConfig.get(leftDiagonalX).get(leftDiagonalY).equals("") && !(currConfig.get(leftDiagonalX).get(leftDiagonalY).equals(value)) && leftDiagonalX >0 && leftDiagonalY > 0) {
                        // check if the second cross is empty
                        if (currConfig.get(leftDiagonalX-1).get(leftDiagonalY+1).equals("")) {
                            Node node = new Node(nextPlayer.isMaximizer, nextPlayer);
                            node.stonePosition = index;
                           node.stoneSteps.add(-2);
                            node.stoneSteps.add(2);
                            legalMoves.add(node);
                        }
                    }
                    // check if the cross position not empty
                    if (!currConfig.get(rightDiagonalX).get(rightDiagonalY).equals("") && !(currConfig.get(rightDiagonalX).get(rightDiagonalY).equals(value)) && rightDiagonalX < 7 && rightDiagonalY < 7) {
                        // check if the second cross is empty
                        if (currConfig.get(rightDiagonalX+1).get(rightDiagonalY+1).equals("")) {
                            Node node = new Node(nextPlayer.isMaximizer, nextPlayer);
                            node.stonePosition = index;
                            node.stoneSteps.add(2);
                            node.stoneSteps.add(2);
                            legalMoves.add(node);
                        }
                    }
                    else if(currPosX < 7 && currPosY < 7){
                        Node node = new Node(nextPlayer.isMaximizer, nextPlayer);
                        node.stonePosition = index;
                        node.stoneSteps.add(1);
                        node.stoneSteps.add(0);
                        legalMoves.add(node);
                    }
                }
//            }
//        }
        }
        return legalMoves;
        // if empty -> choose the cross empty
        // else -> update choose front position

    }

    public int convertStep(ArrayList<Integer> oldStep){
        int newStep = 0;
        if((oldStep.get(0) == 0) && oldStep.get(1) == 1)
            newStep = 7;
        else if((oldStep.get(0) == -2) && (oldStep.get(1) == 2))
            newStep = 12;
        else if((oldStep.get(0) == 2) && (oldStep.get(1) == 2))
            newStep = 16;
        return newStep;
    }

    public int getPossibleMoves(Player player, int depth) {
       return 2;
    }

//    public Player getNextPlayer(int depth, Player[] player) {
public Player getNextPlayer(Player currPlayer) {
//        if(depth == 5)
//            return player[0];
//        else if(depth == 4)
//            return player[1];
//        else if(depth == 3)
//            return player[2];
//        else if(depth == 2)
//            return player[3];
//        else
//            return player[0];
    switch (currPlayer.name) {
        case "p1":
            return Player.getPlayer1();
        case "p2":
            return Player.getPlayer2();
        case "p3":
            return Player.getPlayer3();
        default:
            return Player.getPlayer4();
    }
    }


}
