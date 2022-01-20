package cloud.acog.fightmc.library.bukkit;

public class Pair<T, V> {
    private final T x;
    private final V y;

    public Pair(T x, V y) {
        this.x = x;
        this.y = y;
    }

    public T getX(){
        return x;
    }

    public V getY(){
        return y;
    }
}