public class ArrayDeque <T> {
    private T[] items;

    private int size;

    /*index of next left (front) item insertion*/
    private int front;

    /*index of next right (back) item insertion*/
    private int rear;

    /*Constructor with initial capacity of 8*/
    public ArrayDeque(){
        this.items = (T[]) new Object[8];
        this.size = 0;
        this.front = -1;
        this.rear = -1;
    }
    /*add item to front of queue*/
    public void addFirst(T item){
        //check if empty
        if (isEmpty()){
            front = rear = 0;
            items[front] = item;
            return;
        }
        /*TODO
        //check if full
            //if full resize()*/
        int prevIndex = front - 1;
        if (prevIndex < 0){
            front = items.length + prevIndex;
        } else {
            front = prevIndex;
        }
        items[front] = item;
    }

    public void addLast(T item){
        //check if empty
        if (isEmpty()){
            front = rear = 0;
        }
        //TODO
        //check if full
            //if full resize()
        this.rear = (this.rear + 1) % this.items.length;
        items[rear] = item;
        size ++;

    }

    private boolean isFull(){
        return ((rear + 1 ) % items.length) == front;
    }

    public boolean isEmpty(){
        return front == -1;
    }

    public void printDeque(){

    }

    public T removeFirst(){
        return  (T) (new  Object());
    }

    public T removeLast(){
        return (T) (new Object());
    }

    public T get(int index){
        return items[index];
    }


}