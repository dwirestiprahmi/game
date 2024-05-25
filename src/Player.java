
public class Player {
    private static Player instance1 = new Player(1, "p1", true);
    private static Player instance2 = new Player(2, "p2", false);
    private static Player instance3 = new Player(3, "p3", false);
    private static Player instance4 = new Player(4, "p4", false);
    public int indexPlayer;
    public String name;
    public boolean isMaximizer;

    public Player(int indexPlayer, String name, boolean isMaximizer){
        this.indexPlayer = indexPlayer;
        this.name = name;
        this.isMaximizer = isMaximizer;
    }

    public int getIndexPlayer() {
        return indexPlayer;
    }

    public static Player getPlayer1() {
        return instance1;
    }

    public static Player getPlayer2() {
        return instance2;
    }

    public static Player getPlayer3() {
        return instance3;
    }

    public static Player getPlayer4() {
        return instance4;
    }

    public String getName() {
        return name;
    }

    public boolean isMaximizer() {
        return isMaximizer;
    }
}
