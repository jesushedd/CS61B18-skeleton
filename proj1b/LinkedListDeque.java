import java.beans.ConstructorProperties;

public class LinkedListDeque <T> implements Deque<T>{

    private static class Node<Algo>{
        public Node<Algo> prev;
        public Algo item;
        public Node<Algo> next;
        public Node(Algo thing, Node<Algo> prev, Node<Algo> next ){
            this.prev = prev;
            this.next = next;
            item = thing;
        }
    }

    /*First item is at SentinelHead.next*/
    /*Last item is at SentinelTail.prev*/
    private final Node<T> SentinelHead;
    private final Node<T> SentinelTail;

    /*Save instant size of deque*/
    private int size;


    /*Constructor for LinkedListDeque*/
    public LinkedListDeque(){
        SentinelHead = new Node<T>((T) "sentinelHead", null, null);
        SentinelTail = new Node<T>((T)"sentinelTail", null, null);
        SentinelHead.next = SentinelTail;
        SentinelTail.prev = SentinelHead;
        size = 0;

    };
    @Override
    /*Add an item at front of the deque*/
    public void addFirst(T item){
        Node<T> insert = new Node<T>(item, SentinelHead, SentinelHead.next);
        SentinelHead.next.prev = insert;
        SentinelHead.next = insert;
        size++;
        /*Make SentinelTail.prev is pointing at this first item*/
        if (size == 1){
            SentinelTail.prev = SentinelHead.next;
        }
    };

    @Override
    /*Add an item at tail of the deque*/
    public void addLast(T item){
        Node<T> insert = new Node<T>(item, SentinelTail.prev, SentinelTail);
        SentinelTail.prev.next = insert;
        SentinelTail.prev = insert;
        size++;
        /*Make SentinelHead.next is pointing at this first item*/
        if (size == 1){
            SentinelHead.next = SentinelTail.prev;
        }
    }

    @Override
    /*Returns true is current deque is empty*/
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    /*Returns number of elements in current Deque*/
    public int size(){
        return size;
    }

    @Override
    /*Print deque from first to last item*/
    public void printDeque(){
        Node<T> cursor = SentinelHead.next;
        while (cursor.next != null){
            System.out.println(cursor.item);
            cursor = cursor.next;
        }
    }

    @Override
    /*Removes and returns the item at thr front of the deque, If no such item exists,returns null*/
    public T removeFirst(){
        if (isEmpty()){
            return null;
        }
        /*store first node*/
        Node<T> front = SentinelHead.next;
        //make the second item the first one
        SentinelHead.next = front.next;
        SentinelHead.next.prev = SentinelHead;
        size--;

        return front.item;
    }

    @Override
    public T removeLast(){
        if (isEmpty()){
            return null;
        }
        /*store last node*/
        Node<T> last = SentinelTail.prev;
        //make the second to last item the last one
        SentinelTail.prev = last.prev;
        SentinelTail.prev.next = SentinelTail;

        return last.item;
    }

    @Override
    /*Get item at ith index*/
    public T get(int index){

        if (index < 0){
            System.out.println("LOL index can't be negative number");
            return null;
        }
        if (isEmpty()){
            return null;
        }
        Node<T> cursor = SentinelHead.next;
        while (index != 0)
        {
            if (cursor.next == null){ //cursor.next means it reaches SentinelTail Node
                return null;
            }
            cursor = cursor.next;
            index--;
        }
        return cursor.item;
    }

    public static void main(String[] args) {
        LinkedListDeque<String> deque = new LinkedListDeque<String>();
        System.out.println(deque.isEmpty());

        deque.addFirst("Hola");
        deque.addFirst("como");
        deque.addLast("Munda");
        System.out.println(deque.isEmpty());
    }






}