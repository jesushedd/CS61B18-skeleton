public class ArrayDequeTest {

    public static void testEmpty(){
        ArrayDeque<Integer> dq = new ArrayDeque<Integer>();
        boolean actual = dq.isEmpty();
        boolean expected = true;
        if (actual == expected){
            System.out.println("Test empty passed!!");
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


    public static void main(String[] args) {
        testEmpty();
        testAddFirst();
        testAddLast();


    }
}