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



    /*Print all content in deque*/
    public void printDeque(){
        int cursor = front;
        for (int i = 0; i <= size ; i++, cursor=next(cursor)) {
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