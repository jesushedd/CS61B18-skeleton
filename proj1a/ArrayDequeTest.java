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


    public static void testSize(){
        System.out.println("Testing size()....");
        ArrayDeque<String> dq = createFullDeque();
        int expected = 8;
        int actual = dq.size();
        if (expected == actual){
            System.out.println("Test size()  Passed!!");
        } else {
            System.out.println("Fail!!!.  size() returned " + actual + " Expected: " + expected);
        }
    }


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


    public static ArrayDeque<String> createFullDeque(){
        ArrayDeque<String> dq = new ArrayDeque<String>();
        //fill deque
        for (int i = 0; i < 8 ; i++) {
            String toAdd = String.valueOf(i);
            dq.addLast(toAdd);
        }
        return dq;
    }


    public static void testRemoveFirst(){
        System.out.println("Testing removeFirst()...");
        ArrayDeque<String> dq = createFullDeque();
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
        ArrayDeque<String> dq = createFullDeque();
        System.out.println("Printing full deque with all 8 items added");
        dq.printDeque();


        System.out.println("test print non continuous array...");
        dq.removeFirst();
        dq.removeFirst();
        dq.addLast("8");
        dq.addLast("9");
        dq.printDeque();
    }

    public static void testRemoveLast(){
        System.out.println("Testing removeLast()....");
        ArrayDeque<String> dq = createFullDeque();
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

    public static void testGet(){
        System.out.println("Testing get() method ....");
        ArrayDeque<String> dq = createFullDeque();
        //Test existing i item
        String actual =  dq.get(0);
        String expected = "0";
        if (expected.equals(actual)){
            System.out.println("Test get() ,  Passed!!");
        } else {
            System.out.println("Fail!!!. get() returned " + actual + " Expected: " + expected);
        }
        //Test when i item doesn't exist, must return null
        dq.removeFirst();
        //expected = null;
        actual = dq.get(0);
        if (actual == null){
            System.out.println("Test get() ,  Passed!!");
        } else {
            System.out.println("Fail!!!. get() returned " + actual + " Expected: " + "null");
        }

    }


    public static void testResizing(){
        System.out.println("Testing resizing.....");
        System.out.println("\tTesting continuous resizing...");
        ArrayDeque<String> dq = createFullDeque();
        dq.addFirst("-1");
        String expected = "-1";
        String actual = dq.get(15);
        if (expected.equals(actual)){
            System.out.println("Test resizing I success! ,  Passed!!");
        } else {
            System.out.println("Fail!!!. get(15) returned " + actual + " Expected: " + expected);
        }
        dq.addFirst("-2");
        expected = "-2";
        actual = dq.get(14);
        if (expected.equals(actual)){
            System.out.println("Test resizing II success! ,  Passed!!");
        } else {
            System.out.println("Fail!!!. get(14) returned " + actual + " Expected: " + expected);
        }


        // Test resizing non continuous array
        System.out.println("\tTesting non continuous resizing...");
        dq = createFullDeque();
        dq.removeFirst();
        dq.removeFirst();
        dq.addLast("8");
        dq.addLast("9");
        dq.printDeque();
        dq.addFirst("1");
        dq.addFirst("0");
        expected = "0";
        actual = dq.get(14);
        if (expected.equals(actual)){
            System.out.println("Test resizing III success! ,  Passed!!");
        } else {
            System.out.println("Fail!!!. get(14) returned " + actual + " Expected: " + expected);
        }



        System.out.println("\tTesting non continuous resizing...");
        dq = createFullDeque();
        dq.removeFirst();
        dq.removeFirst();
        dq.addLast("8");
        dq.addLast("9");
        dq.printDeque();
        dq.addLast("10");
        dq.addLast("11");
        expected = "11";
        actual = dq.get(9);
        if (expected.equals(actual)){
            System.out.println("Test resizing IV success! ,  Passed!!");
        } else {
            System.out.println("Fail!!!. get(9) returned " + actual + " Expected: " + expected);
        }


        System.out.println("Testing resizing when removing items ....");
        dq = createFullDeque();
        dq.addLast("8");
        for (int i = 0; i < 5; i++) {
            dq.removeLast();
        }
        dq.printDeque();
        dq.removeLast();
        dq.removeLast();
        dq.removeLast();
        dq.removeLast();
        actual = dq.removeLast();

    }




    public static void main(String[] args) {
        testEmpty();
        testAddFirst();
        testAddLast();
        testPrint();
        testRemoveFirst();
        testRemoveLast();
        testGet();
        testSize();
        testResizing();


    }
}