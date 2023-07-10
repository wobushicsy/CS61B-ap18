package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        for(int i = 0; i < 10; i += 1) {
            arb.enqueue(i);
        }
        int a;
        for(int i = 0; i < 5; i += 1) {
            a = arb.dequeue();
            assertEquals(a, i);
        }
        for(int i = 11; i < 16; i += 1) {
            arb.enqueue(i);
        }
        for(int i = 5; i < 10; i += 1) {
            a = arb.dequeue();
            assertEquals(a, i);
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
