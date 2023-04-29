public class OffByN implements CharacterComparator {
    private int offsetN;
    public OffByN(int N) {
        this.offsetN = N;
    }

    public boolean equalChars(char a, char b) {
        return Math.abs(a - b) < 5;
    }
}
