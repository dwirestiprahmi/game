
public class Player {
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

    public String getName() {
        return name;
    }

    public boolean isMaximizer() {
        return isMaximizer;
    }
}
