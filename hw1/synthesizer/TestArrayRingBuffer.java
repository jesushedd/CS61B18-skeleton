package synthesizer;
import org.junit.Test;
import org.junit.Assert;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void isFullTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<Integer>(3);
        arb.enqueue(2);
        arb.enqueue(1);
        arb.enqueue(0);
        Assert.assertTrue(arb.isFull());
    }


    @Test
    public void tTest(){
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(3);
        arb.enqueue(9);
        arb.enqueue(8);
        arb.enqueue(7);
        Assert.assertEquals(arb.peek(), (Integer) 9);
        Assert.assertEquals(arb.dequeue(), (Integer) 9);
        Assert.assertEquals(arb.dequeue(), (Integer) 8);
        Assert.assertEquals(arb.dequeue(), (Integer) 7);
        Assert.assertTrue(arb.isEmpty());

        arb.enqueue(1);
        Assert.assertEquals(arb.peek(), (Integer) 1);

    }

    @Test
    public void foreachTest(){
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(5);
        arb.enqueue(5);
        arb.enqueue(4);
        arb.enqueue(3);
        arb.enqueue(2);
        arb.enqueue(1);

        for( Integer i : arb){
            System.out.println(i);
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
