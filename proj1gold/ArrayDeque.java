public class ArrayDeque<T> {
    private T[] items;

    private final double minUsageFactor = 0.25;
    private double getCurrentUsageFactor(){
        return (double) size / items.length;
    }

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
            size++;
            return;
        }
        if (isFull()){
            items = expand();
        }
        front = prev(front);//previous, ensuring circular array
        items[front] = item;
        size++;
    }


    public int size(){
        return size;
    }


    /*Add an item to  next index of current rear item*/
    public void addLast(T item){
        //check if empty
        if (isEmpty()){
            front = rear = 0;
            items[rear] = item;
            size++;
            return;
        }
        if (isFull())
        {
            items = expand();
        }
        this.rear = next(rear);//next, ensuring circular array
        items[rear] = item;
        size ++;

    }


    /*Returns a resized array to twice the items current length
    * The items are arranged in the new array starting at 0 index(front) and end at size - 1 index(rear), regardless of original index*/
    private T[] expand(){
        T[] resized = (T[]) new Object[2 * items.length];
        int cursor = front;
        for (int i = 0; i < size  ; i++, cursor = next(cursor)) {
            resized[i] = items[cursor];
        }
        front = 0;
        rear = size - 1;
        return resized;
    }

    private T[] shrink(){
        T[] resized = (T[]) new Object[items.length / 2];
        int cursor = front;
        for (int i = 0; i < size; i++, cursor = next(cursor)) {
            resized[i] = items[cursor];
        }
        front = 0;
        rear = size - 1;
        return resized;
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



    /*Print all content in deque*/
    public void printDeque(){
        int cursor = front;
        for (int i = 0; i < size ; i++, cursor=next(cursor)) {
            System.out.println(items[cursor]);
        }
    }

    /*Returns first item(head,front)  from deque , deletes from array*/
    public T removeFirst(){
        if (isEmpty()){
            return null;
        }
        T item = items[front];//save front item
        items[front] = null;//delete permanently front item from array
        if (size == 1){
            front = rear = -1;
        }
        else {
            front = next(front);//update front index
        }
        size--;
        if (getCurrentUsageFactor() < minUsageFactor && items.length >= 16){
            items = shrink();
        }
        return  item;
    }

    /*Returns last item(rear, tail)  from deque , deletes from array*/
    public T removeLast(){
        if (isEmpty()){
            return null;
        }
        T item = items[rear];
        items[rear] = null;
        rear = prev(rear);
        size--;
        if (size == 0) {
            rear = front = -1;
        }
        if (getCurrentUsageFactor() < minUsageFactor && items.length >= 16){
            items = shrink();
        }
        return item;
    }

    public T get(int index){
        return items[index];
    }


}