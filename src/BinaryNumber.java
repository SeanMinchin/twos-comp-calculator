public class BinaryNumber {
    private int magnitude;
    private boolean isNegative;

    public BinaryNumber(String value, boolean isNegative) {
        this.isNegative = isNegative;
        magnitude = Integer.parseInt(value, 2);
    }

    public BinaryNumber(int value, boolean isNegative) {
        this.isNegative = isNegative;
        magnitude = value;
    }

    public BinaryNumber(String binaryNum) {
        if(binaryNum.charAt(0) == '-') {
            isNegative = true;
            magnitude = Integer.parseInt(binaryNum.substring(1),2);
        } else {
            isNegative = false;
            magnitude = Integer.parseInt(binaryNum,2);
        }
    }

    public boolean isNegative() {
        return isNegative;
    }

    public int getMagnitude() {
        return magnitude;
    }

    public String getBitString() {
        return Integer.toBinaryString(magnitude);
    }

    @Override
    public String toString()    {
        String sign = isNegative ? "-" : "";
        return sign + getBitString();
    }
}
