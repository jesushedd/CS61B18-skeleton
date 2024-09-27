package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    abstract void  moveTo(double deltaX, double deltaY);
    protected int fillCount;
    protected int capacity;
    public int capacity(){
        return capacity;
    }
    public int fillCount() {
        return fillCount;
    }
}
