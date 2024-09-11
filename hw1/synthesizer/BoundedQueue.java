package synthesizer;
public interface BoundedQueue <T> {
    //Return size of the buffer
    int capacity();

    //Return number of items currently in the buffer
    int fillCount();

    //add item x to the end
    void enqueue(T x);

    //Delete and return item from the front
    T dequeue();

    //return , but no delete, item from the front
    T peek();

    // is the buffer empty?
    default boolean isEmpty(){
         return fillCount() == 0;
    }

    // is the buffer full
    default boolean isFull(){
        return fillCount() == capacity();
    }
}
