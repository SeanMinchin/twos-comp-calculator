import java.util.Stack;
import java.util.Map;
import static java.util.Map.entry;

public class Converter {
    private static final Map<String, Character> binaryToHexMap;
    private static final Map<Character, String> hexToBinaryMap;

    static {
        binaryToHexMap = Map.ofEntries(entry("0000",'0'),
                entry("0001",'1'),
                entry("0010",'2'),
                entry("0011",'3'),
                entry("0100",'4'),
                entry("0101",'5'),
                entry("0110",'6'),
                entry("0111",'7'),
                entry("1000",'8'),
                entry("1001",'9'),
                entry("1010",'A'),
                entry("1011",'B'),
                entry("1100",'C'),
                entry("1101",'D'),
                entry("1110",'E'),
                entry("1111",'F')
        );
        hexToBinaryMap = Map.ofEntries(entry('0', "0000"),
                entry('1',"0001"),
                entry('2',"0010"),
                entry('3',"0011"),
                entry('4',"0100"),
                entry('5',"0101"),
                entry('6',"0110"),
                entry('7',"0111"),
                entry('8',"1000"),
                entry('9',"1001"),
                entry('A',"1010"),
                entry('B',"1011"),
                entry('C',"1100"),
                entry('D',"1101"),
                entry('E',"1110"),
                entry('F',"1111")
        );
    }

    private static String zeroPad(String bitString) {
        int offset = bitString.length() % 4;
        switch(offset) {
            case(1):
                bitString = "000" + bitString;
                break;
            case(2):
                bitString = "00" + bitString;
                break;
            case(3):
                bitString = "0" + bitString;
                break;
        }
        return bitString;
    }

    static BinaryNumber toBinaryUnsigned(Integer decimalNum) {
        boolean isNegative;
        Stack<Character> remaindersStack = new Stack<>();
        if(decimalNum < 0) {
            decimalNum = -decimalNum;
            isNegative = true;
        } else {
            isNegative = false;
        }
        for(int remainder = 0; decimalNum > 0; decimalNum /= 2) {
            remainder = decimalNum % 2;
            remaindersStack.push((char) (remainder + '0'));
        }
        StringBuilder binaryNum = new StringBuilder();
        while(!remaindersStack.isEmpty()) {
            binaryNum.append(remaindersStack.pop());
        }
        return new BinaryNumber(binaryNum.toString(), isNegative);
    }

    static BinaryNumber toBinaryUnsigned(String hexNum) {
        boolean isNegative;
        int index;
        if(hexNum.charAt(0) == '-') {
            isNegative = true;
            index = 1;
        } else {
            isNegative = false;
            index = 0;
        }
        StringBuilder binaryNum = new StringBuilder();
        for(; index < hexNum.length(); ++index) {
            binaryNum.append(hexToBinaryMap.get(hexNum.charAt(index)));
        }
        return new BinaryNumber(binaryNum.toString(), isNegative);
    }

    static BinaryNumber toBinaryUnsigned(TwosComplement twosCompNum) {
        if(twosCompNum.isNegative()) {
            int size = twosCompNum.getSize();
            int N = (int) Math.pow(2, size);
            int magnitude = N - Integer.parseInt(twosCompNum.toString(), 2);
            return new BinaryNumber(magnitude, true);
        } else {
            return new BinaryNumber(twosCompNum.toString(), false);
        }
    }

    static String toHex(BinaryNumber binaryNum) {
        String bitString = binaryNum.getBitString();
        bitString = zeroPad(bitString);
        StringBuilder hexNum = new StringBuilder();
        if(binaryNum.isNegative()) {
            hexNum.append('-');
        }
        for(int i = 0; i < bitString.length(); i+=4) {
            hexNum.append(binaryToHexMap.get(bitString.substring(i, i + 4)));
        }
        return hexNum.toString();
    }

    static String toHex(TwosComplement twosCompNum) {
        String bitString = twosCompNum.toString();
        bitString = zeroPad(bitString);
        StringBuilder hexNum = new StringBuilder();
        if(twosCompNum.isNegative()) {
            hexNum.append('-');
        }
        for(int i = 0; i < bitString.length(); i+=4) {
            hexNum.append(binaryToHexMap.get(bitString.substring(i, i + 4)));
        }
        return hexNum.toString();
    }

    static Integer toDecimal(String binaryNum) {
        boolean isNegative;
        int indexMSB;
        if(binaryNum.charAt(0) == '-') {
            isNegative = true;
            indexMSB = 1;
        } else {
            isNegative = false;
            indexMSB = 0;
        }
        int decNum = 0;
        for(int i = 0; i < binaryNum.length() - indexMSB; ++i) {
            int bit = binaryNum.charAt(binaryNum.length() - i - 1) - 48;
            int power = (int) Math.pow(2,i);
            decNum += bit*power;
        }
        if(isNegative) {
            decNum *= -1;
        }
        return decNum;
    }

    static TwosComplement toTwosComplemenet(int size, BinaryNumber unsignedBinaryNum) {
        if(unsignedBinaryNum.isNegative()) {
            int N = (int) Math.pow(2,size);
            int value2c = N - unsignedBinaryNum.getMagnitude();
            return new TwosComplement(size,Integer.toBinaryString(value2c));
        } else {
            return new TwosComplement(size,unsignedBinaryNum.getBitString());
        }
    }
}
