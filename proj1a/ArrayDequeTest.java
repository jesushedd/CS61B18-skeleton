public class ArrayDequeTest {

    public static void testEmpty(){
        ArrayDeque<Integer> dq = new ArrayDeque<Integer>();
        boolean actual = dq.isEmpty();
        boolean expected = true;
        if (actual == expected){
            System.out.println("Test empty passed!!");
        }
    }

    /*
    public static void testFull(){
        ArrayDeque<String> dq = new ArrayDeque<String>();
        for (int i = 0; i < 8 ; i++) {
            String toAdd = String.valueOf(i);
            dq.addLast(toAdd);
        }

        boolean expected = true;
        boolean actual = dq.isFull();
    }*/


    public static void testAddFirst(){
        ArrayDeque<String> dq = new ArrayDeque<String>();
        //test first item
        dq.addFirst("Hola Munda");
        String actual = dq.get(0);
        String expected = "Hola Munda";
        if (expected.equals(actual)){
            System.out.println("Test addFirst when first item added and get(0) Passed!!");
        } else {
            System.out.println("get(0) returned " + actual + " Expected: " +expected);
        }

        //test subsequent items at front when starting from index 0
        dq.addFirst("Greca");
        actual = dq.get(7);
        expected = "Greca";
        if (expected.equals(actual)){
            System.out.println("Test addFirst when subsequents items added and get(i) Passed!!");
        } else {
            System.out.println("get(7) returned " + actual + " Expected: " +expected);
        }

        dq.addFirst("Safira");
        actual = dq.get(6);
        expected = "Safira";
        if (expected.equals(actual)){
            System.out.println("Test addFirst when subsequents items added and get(i) Passed!!");
        } else {
            System.out.println("get(6) returned " + actual + " Expected: " +expected);
        }
    }

    public static void testAddLast(){
        ArrayDeque<String> dq = new ArrayDeque<String >();
        dq.addLast("Primero");
        dq.addLast("Segundo");
        dq.addLast("Tercero");
        String actual = dq.get(2);
        String expected = "Tercero";

        if (expected.equals(actual)){
            System.out.println("Test addLast() , get(i) Passed!!");
        } else {
            System.out.println("get(2) returned " + actual + " Expected: " +expected);
        }
    }


    public static void testRemoveFirst(){
        System.out.println("Testing removeFirst()...");
        ArrayDeque<String> dq = new ArrayDeque<String>();
        for (int i = 0; i < 8 ; i++) {
            String toAdd = String.valueOf(i);
            dq.addLast(toAdd);
        }
        dq.removeFirst();
        dq.removeFirst();
        String expected = "2";
        String actual = dq.removeFirst();
        if (expected.equals(actual)){
            System.out.println("Test removeFirst() ,  Passed!!");
        } else {
            System.out.println("removeFirst() returned " + actual + " Expected: " + expected);
        }

    }

    public static void testPrint(){
        ArrayDeque<String> dq = new ArrayDeque<String>();
        //fill deque
        for (int i = 0; i < 8 ; i++) {
            String toAdd = String.valueOf(i);
            dq.addLast(toAdd);
            if (i==3){
                System.out.println("Printing full deque with first 4 items added");
                dq.printDeque();
            }
        }
        System.out.println("Printing full deque with all 8 items added");
        dq.printDeque();


        System.out.println("test print non continuous array...");
        dq.removeFirst();
        dq.removeFirst();
        dq.addLast("8");
        dq.addLast("9");
        dq.printDeque();
    }

    public void testRemoveLast(){
        System.out.println("Testing removeLast()....");
        ArrayDeque<String> dq = new ArrayDeque<String>();
        //fill deque
        for (int i = 0; i < 8 ; i++) {
            String toAdd = String.valueOf(i);
            dq.addLast(toAdd);
        }
        dq.removeLast();
        dq.removeLast();
        String actual =  dq.removeLast();
        String expected = "5";
        if (expected.equals(actual)){
            System.out.println("Test removeLast() ,  Passed!!");
        } else {
            System.out.println("Fail!!!. removeLast() returned " + actual + " Expected: " + expected);
        }


    }


    public static void main(String[] args) {
        testEmpty();
        testAddFirst();
        testAddLast();
        testPrint();
        testRemoveFirst();


    }
}