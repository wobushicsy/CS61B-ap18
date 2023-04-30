import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    static StudentArrayDeque<Integer> src = new StudentArrayDeque<>();
    static ArrayDequeSolution<Integer> cmp = new ArrayDequeSolution<>();

    @Test
    public void testStudentArrayDeque() {
        Integer src_remove;
        Integer cmp_remove;
        int opcode;
        int num;
        while (true) {
            if (src.size() > 100) {
                if (src.size() == cmp.size()) {
                    System.out.println("test passed");
                    break;
                } else {
                    System.out.println("test failed");
                    break;
                }
            }
            opcode = StdRandom.uniform(4);
            if (opcode == 0) {
                num = StdRandom.uniform(100);
                src.addFirst(num);
                cmp.addFirst(num);
            } else if (opcode == 1) {
                num = StdRandom.uniform(100);
                src.addLast(num);
                cmp.addLast(num);
            } else if (opcode == 2) {
                if (src.isEmpty() || cmp.isEmpty())
                    continue;
                src_remove = src.removeFirst();
                cmp_remove = cmp.removeFirst();
                assertEquals(src_remove, cmp_remove);
            } else if (opcode == 3) {
                if (src.isEmpty() || cmp.isEmpty())
                    continue;
                src_remove = src.removeFirst();
                cmp_remove = cmp.removeFirst();
                assertEquals(src_remove, cmp_remove);
            }
        }
    }
}
