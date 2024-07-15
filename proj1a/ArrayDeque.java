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
        front = prev(front);
        items[front] = item;
        size++;
    }

    public void addLast(T item){
        //check if empty
        if (isEmpty()){
            front = rear = 0;
            items[rear] = item;
            return;
        }
        //TODO
        //check if full
            //if full resize()
        this.rear = next(rear);
        items[rear] = item;
        size ++;

    }

    private boolean isFull(){
        return next(rear) == front;
    }

    public boolean isEmpty(){
        return front == -1;
    }


    /*Returns next index from index of current rear item */
    private int next(int index){
        return (index + 1) % items.length;
    }

    /*Returns previous index from index of current front item*/
    private int prev(int index){
        return (index - 1 + items.length) % items.length;
    }



    //TODO
    public void printDeque(){
        int cursor = front;
        while (cursor != next(rear)) {
            System.out.println(items[cursor]);
            cursor = next(cursor);
        }
    }

    //TODO
    public T removeFirst(){

        return  (T) (new  Object());
    }

    //TODO
    public T removeLast(){
        return (T) (new Object());
    }

    public T get(int index){
        return items[index];
    }


}