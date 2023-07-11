package synthesizer;

//Make sure this class is public
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final means
     * the values cannot be changed at runtime. We'll discuss this and other topics
     * in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        buffer = new ArrayRingBuffer<Double>((int) Math.round(SR / frequency));
    }

    private boolean isIn(double r, double[] arr, int size) {
        boolean flag = false;
        for (int i = 0; i < size; i += 1) {
            if (r == arr[i]) {
                flag = true;
                break;
            }
        }

        return flag;
    }

    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        while (!buffer.isEmpty()) {
            buffer.dequeue();
        }

        double[] arr = new double[buffer.capacity()];
        double a;

        for (int i = 0; i < buffer.capacity(); i += 1) {
            a = Math.random() - 0.5;
            while (isIn(a, arr, buffer.fillCount())) {
                a = Math.random() - 0.5;
            }
            arr[i] = a;
            buffer.enqueue(a);
        }

    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm. 
     */
    public void tic() {
        double a, b;
        a = buffer.dequeue();
        b = buffer.peek();
        buffer.enqueue(DECAY * (a + b) * 0.5);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }
}
