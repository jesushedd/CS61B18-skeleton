import static org.junit.Assert.*;

import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;
public class TestArrayDequeGold {
    @Test
    public void  aTest(){
        StudentArrayDeque<Integer> badDq = new StudentArrayDeque<Integer>();

        ArrayDequeSolution<Integer> goodDq = new ArrayDequeSolution<Integer>();
        //My Solution
        //ArrayDeque<Integer> badDq = new ArrayDeque<Integer>();


        boolean lastOne = false;
        boolean firstOne = false;
        for (int i = 0; i < 100; i++) {
            double rdm = StdRandom.uniform();

            if (rdm < 0.5d ){
                if (i == 100 -1){
                    firstOne = true;
                }
                badDq.addFirst(i);
                goodDq.addFirst(i);
            } else {
                if (i == 100 -1){
                    lastOne = true;
                }
                badDq.addLast(i);
                goodDq.addLast(i);
            }
        }

        if (firstOne){
            assertEquals(goodDq.removeFirst(), badDq.removeFirst());
        } else if (lastOne) {
            assertEquals(goodDq.removeLast(), badDq.removeLast());
        }
        for (int i = 0; i < 100; i++) {
            double n = StdRandom.uniform();
            if (n < 0.5){
                assertEquals(goodDq.removeFirst(), badDq.removeFirst());
            }
        }



    }
}
