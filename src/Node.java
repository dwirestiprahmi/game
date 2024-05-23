
import java.util.ArrayList;
import java.util.Random;

public class Node {
    private String name;
    private int value;
    private boolean isMaximizer;
    private ArrayList<Node> children;
    private Player player;
    private Node parent = null;
    public static int counter = 0;
    public Node (String name, int value, boolean isMaximizer) {
        this.name = name;
        this.value=value;
        this.isMaximizer = isMaximizer;
    }
    //public int[] possibleMove = [2, 9];

    public Node() {

    }
    public Node (String name, boolean isMaximizer, Player player) {
        this.name = name;
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

    public Node generateChildren(int depth, Player[] player) {
        if(depth == 0){
            Node rootNode = new Node(((counter + 1) + " child"), getNextPlayer(depth, player).isMaximizer, getNextPlayer(depth, player));
            rootNode.parent = this;
            return rootNode;
        }
        else {
            Player currPlayer = getNextPlayer(depth, player);
            Node rootNode = new Node(((counter + 1) + " child"), getNextPlayer(depth, player).isMaximizer, getNextPlayer(depth, player));
            int possibleMoves = getPossibleMoves(getNextPlayer(depth, player), depth);
            for(int i= 0; i < possibleMoves; i++) {
                String name = (counter + 1) + " child";
//                Random rand = new Random();
//                int value = (rand.nextInt(11)) - 10;
                int nextPlayer = getNextPlayer(depth - 1, player).getIndexPlayer();
                Node childNode = generateChildren(depth-1, player);
                rootNode.addChild(childNode);
            }
            return rootNode;
        }
    }

    public int getPossibleMoves(Player player, int depth) {
       return 2;
    }

    public Player getNextPlayer(int depth, Player[] player) {
        if(depth == 5)
            return player[0];
        else if(depth == 4)
            return player[1];
        else if(depth == 3)
            return player[2];
        else if(depth == 2)
            return player[3];
        else
            return player[0];
    }


}
