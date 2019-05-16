import java.util.BitSet;

public class TwosComplement {
    private final int size;
    private BitSet bits;

    public TwosComplement(int size, String bitString) {
        this.size = size;
        bits = new BitSet(size);
        for(int i = 0; i < bitString.length(); ++i) {
            if(bitString.charAt(bitString.length() - i - 1) == '1') {
                bits.set(size - i - 1);
            }
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isNegative() {
        return bits.get(0);
    }

    @Override
    public String toString() {
        StringBuilder bitString = new StringBuilder(size);
        for(int i = 0; i < size; ++i) {
            char bit = bits.get(i) ? '1' : '0';
            bitString.append(bit);
        }
        return bitString.toString();
    }
}
