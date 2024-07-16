public class ArrayDeque <T> {
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
        front = prev(front);
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
        this.rear = next(rear);
        items[rear] = item;
        size ++;

    }


    private void resize(){


    }

    /*Resize the items array to twice it's current length
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

    private void shrink(){

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
        T item = items[front];//save front item
        items[front] = null;//delete permanently front item from array
        front = next(front);//update front index
        size--;
        return  item;
    }

    /*Returns last item(rear, tail)  from deque , deletes from array*/
    public T removeLast(){
        T item = items[rear];
        items[rear] = null;
        rear = prev(rear);
        size--;
        return item;
    }

    public T get(int index){
        return items[index];
    }


}