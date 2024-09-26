/***
 * Question :- Conversions of all from base to others and Arithmetic operations
 * Date :- 26/09/2024
 * Owner :- Dikshant Goswami
 */

import java.util.Scanner;

public class CombinedConversionCalculation {

    // Power functions is used where the base and the power which defines how much time a value will multiply itself passes as  parameters
    public static int powerfunction(int base, int exponent) {
        int result = 1;
        for (int start = 0; start < exponent; start++) {
            result = result * base;
        }
        return result;
    }

    // Binary to Decimal conversion the binary number entered from user is passed as a parameter and return as the decimal
    public static int forDecimal(String binary) {
        int decimal = 0;
        for (int endoflength = binary.length() - 1, powervalue = 0; endoflength >= 0; endoflength--, powervalue++) {
            int bit = binary.charAt(endoflength) - '0';
            if (bit == 1) {
                decimal += powerfunction(2, powervalue);
            }
        }
        return decimal;
    }

    // Decimal to Binary conversion
    // here decimal is sent as an parameter of integer type and result will send our binary
    public static String ForBinary(int decimal) {
        int Remainder;
        String Binary = "";
        while (decimal > 0) {
            Remainder = decimal % 2;
            Binary = Remainder + Binary;
            decimal = decimal / 2;
        }
        return Binary;
    }

    // Decimal to Octal conversion
    // here decimal is sent as our parameter and it will pass the octal value
    public static String ForOctal(int decimal) {
        int Remainder;
        String Octal = "";
        while (decimal != 0) {
            Remainder = decimal % 8;
            Octal = Remainder + Octal;
            decimal = decimal / 8;
        }
        return Octal;
    }

    // Decimal to Hexadecimal conversion
    // Here we receive the decimal as a parameter and get the Hexadecimal as a result
    public static String ForHexadecimal(int decimal) {
        int Remainder;
        String Hexadecimal = "";
        char HexadecimalArray[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        while (decimal != 0) {
            Remainder = decimal % 16;
            Hexadecimal = HexadecimalArray[Remainder] + Hexadecimal;
            decimal = decimal / 16;
        }
        return Hexadecimal;
    }

    // Hexadecimal to Decimal conversion
    // Here hexadecimal is passed as a parameter and decimal is received as a result output
    public static int HexatoDecimal(String hexadecimal) {
        int decimal = 0;
        int exponent = 0;
        int lengthofhexadecimal = hexadecimal.length();

        for (int end = lengthofhexadecimal - 1; end >= 0; end--) {
            char hexChar = hexadecimal.charAt(end); //--> here hexchar defines the Hexadecimal particular single characters

            if (hexChar >= '0' && hexChar <= '9') {
                decimal += (hexChar - '0') * powerfunction(16, exponent);
            } else if (hexChar >= 'A' && hexChar <= 'F') {
                decimal += (hexChar - 'A' + 10) * powerfunction(16, exponent);
            } else if (hexChar >= 'a' && hexChar <= 'f') {
                decimal += (hexChar - 'a' + 10) * powerfunction(16, exponent);
            } else {
                System.out.println(Constants.INVALID_HEXCHAR + hexChar);
                return -1;
            }
            exponent++;
        }
        return decimal;
    }

    // Octal to Decimal conversion
    //  Here the Octal value is passed as a parameters and result will get the decimal
    public static int octaltodecimal(int octal) {
        int Remainder = 0;
        int iteration = 0, decimal = 0, fixedvalue = 8; //--> Here iteration defines how many times our value will be multiplied with it own se:f
        while (octal != 0) {
            Remainder = octal % 10;
            decimal = decimal + powerfunction(fixedvalue, iteration) * Remainder;
            octal = octal / 10;
            iteration++;
        }
        return decimal;
    }

    // Arithmetic Operations function
    // Here our user will choose the options between the different conversion types and also choose the Arithmetic operation the he/she will give the inout which is used in calculation
    public static void arithmeticOperations(int systemChoice, int operationChoice, Scanner inputfromuser) {
        int num1Decimal = 0, num2Decimal = 0, resultDecimal = 0;
        String result = "";

        if (systemChoice == 1) {
            System.out.print(Constants.ENTER_FBINARY);
            String binary1 = inputfromuser.nextLine();
            System.out.print(Constants.ENTER_SBINARY);
            String binary2 = inputfromuser.nextLine();
            num1Decimal = forDecimal(binary1);
            num2Decimal = forDecimal(binary2);

        } else if (systemChoice == 2) {
            System.out.print(Constants.ENTER_FDECIMAL);
            num1Decimal = inputfromuser.nextInt();
            System.out.print(Constants.ENTER_SDECIMAL);
            num2Decimal = inputfromuser.nextInt();
        } else if (systemChoice == 3) {
            System.out.print(Constants.ENTER_FOCTAL);
            int octal1 = inputfromuser.nextInt();
            System.out.print(Constants.ENTER_SOCTAL);
            int octal2 = inputfromuser.nextInt();
            num1Decimal = octaltodecimal(octal1);
            num2Decimal = octaltodecimal(octal2);

        } else if (systemChoice == 4) {
            System.out.print(Constants.ENTER_FHEXADECIMAL);
            String hex1inputfromuser = inputfromuser.nextLine();
            System.out.print(Constants.ENTER_SHEXADECIMAL);
            String hex2inputfromuser = inputfromuser.nextLine();

            num1Decimal = HexatoDecimal(hex1inputfromuser);  // Convert first hexadecimal to decimal
            num2Decimal = HexatoDecimal(hex2inputfromuser);  // Convert second hexadecimal  to decimal

            // Check if the conversion was successful
            if (num1Decimal == -1 || num2Decimal == -1) {
                System.out.println(Constants.INVALID_HEXADECIMAL);
                return;
            }
        }

        // Perform operation
        // Here the result of the decimal values are done
        switch (operationChoice) {
            case 1:  // Addition
                resultDecimal = num1Decimal + num2Decimal;
                break;
            case 2:  // Subtraction
                resultDecimal = num1Decimal - num2Decimal;
                break;
            case 3:  // Multiplication
                resultDecimal = num1Decimal * num2Decimal;
                break;
            case 4:  // Division
                if (num2Decimal != 0) {
                    resultDecimal = num1Decimal / num2Decimal;
                } else {
                    System.out.println(Constants.DIVISION_NOT);
                    return;
                }
                break;
        }

        // Convert result back to original system
        // we will re convert the original values so that desire values can be obtained by our user
        if (systemChoice == 1) {
            result = ForBinary(resultDecimal);
            System.out.println(Constants.R_BINARY + result);
        } else if (systemChoice == 2) {
            System.out.println(Constants.R_DECIMAL + resultDecimal);
        } else if (systemChoice == 3) {
            result = ForOctal(resultDecimal);
            System.out.println(Constants.R_OCTAL + result);
        } else if (systemChoice == 4) {
            result = ForHexadecimal(resultDecimal);
            System.out.println(Constants.R_HEXADECIMAL + result);
        }
    }

    // Here all  conversions and arithmetic operation will take p;ace
    public static void main(String[] args) {
        Scanner inputFromUser = new Scanner(System.in);
        boolean exitProgram = false;

        while (!exitProgram) {
            System.out.println(Constants.MENU);
            System.out.println(Constants.B_TO_D);
            System.out.println(Constants.B_TO_O);
            System.out.println(Constants.B_TO_H);
            System.out.println(Constants.D_TO_B);
            System.out.println(Constants.D_TO_C);
            System.out.println(Constants.D_TO_H);
            System.out.println(Constants.O_TO_D);
            System.out.println(Constants.O_TO_B);
            System.out.println(Constants.O_TO_H);
            System.out.println(Constants.H_TO_D);
            System.out.println(Constants.H_TO_B);
            System.out.println(Constants.H_TO_O);
            System.out.println(Constants.ARITHMETIC_OP);
            System.out.println(Constants.EXIT);
            System.out.print(Constants.ENTER_CHOICE);
            int choice = inputFromUser.nextInt();
            inputFromUser.nextLine();  // Consume the newline

            switch (choice) {
                case 1:  // Binary to Decimal
                    System.out.print(Constants.ENTER_B_NUM);
                    String binary = inputFromUser.nextLine();
                    int decimalFromBinary = forDecimal(binary);
                    System.out.println(Constants.DECIMAL_VALUE + decimalFromBinary);
                    break;

                case 2:  // Binary to Octal
                    System.out.print(Constants.ENTER_B_NUM);
                    binary = inputFromUser.nextLine();
                    decimalFromBinary = forDecimal(binary);
                    String octalFromBinary = ForOctal(decimalFromBinary);
                    System.out.println(Constants.OCTAL_VALUE + octalFromBinary);
                    break;

                case 3:  // Binary to Hexadecimal
                    System.out.print(Constants.ENTER_B_NUM);
                    binary = inputFromUser.nextLine();
                    decimalFromBinary = forDecimal(binary);
                    String hexFromBinary = ForHexadecimal(decimalFromBinary);
                    System.out.println(Constants.HEXADECIMAL_VALUE + hexFromBinary);
                    break;

                case 4:  // Decimal to Binary
                    System.out.print(Constants.ENTER_DECIMAL);
                    int decimal = inputFromUser.nextInt();
                    String binaryFromDecimal = ForBinary(decimal);
                    System.out.println(Constants.BINARY_VALUE + binaryFromDecimal);
                    break;

                case 5:  // Decimal to Octal
                    System.out.print(Constants.ENTER_DECIMAL);
                    decimal = inputFromUser.nextInt();
                    String octalFromDecimal = ForOctal(decimal);
                    System.out.println(Constants.OCTAL_VALUE + octalFromDecimal);
                    break;

                case 6:  // Decimal to Hexadecimal
                    System.out.print(Constants.ENTER_DECIMAL);
                    decimal = inputFromUser.nextInt();
                    String hexFromDecimal = ForHexadecimal(decimal);
                    System.out.println(Constants.HEXADECIMAL_VALUE + hexFromDecimal);
                    break;

                case 7:  // Octal to Decimal
                    System.out.print(Constants.ENTER_OCTAL);
                    int octal = inputFromUser.nextInt();
                    int decimalFromOctal = octaltodecimal(octal);
                    System.out.println(Constants.DECIMAL_VALUE + decimalFromOctal);
                    break;

                case 8:  // Octal to Binary
                    System.out.print(Constants.ENTER_OCTAL);
                    octal = inputFromUser.nextInt();
                    decimalFromOctal = octaltodecimal(octal);
                    String binaryFromOctal = ForBinary(decimalFromOctal);
                    System.out.println(Constants.BINARY_VALUE + binaryFromOctal);
                    break;

                case 9:  // Octal to Hexadecimal
                    System.out.print(Constants.ENTER_OCTAL);
                    octal = inputFromUser.nextInt();
                    decimalFromOctal = octaltodecimal(octal);
                    String hexFromOctal = ForHexadecimal(decimalFromOctal);
                    System.out.println(Constants.HEXADECIMAL_VALUE + hexFromOctal);
                    break;

                case 10:  // Hexadecimal to Decimal
                    System.out.print(Constants.ENTER_HEXADECIMAL);
                    String hexadecimal = inputFromUser.nextLine();
                    int decimalFromHexadecimal = HexatoDecimal(hexadecimal);
                    System.out.println(Constants.DECIMAL_VALUE + decimalFromHexadecimal);
                    break;

                case 11:  // Hexadecimal to Binary
                    System.out.print(Constants.ENTER_HEXADECIMAL);
                    hexadecimal = inputFromUser.nextLine();
                    decimalFromHexadecimal = HexatoDecimal(hexadecimal);
                    String binaryFromHexadecimal = ForBinary(decimalFromHexadecimal);
                    System.out.println(Constants.BINARY_VALUE + binaryFromHexadecimal);
                    break;

                case 12:  // Hexadecimal to Octal
                    System.out.print(Constants.ENTER_HEXADECIMAL);
                    hexadecimal = inputFromUser.nextLine();
                    decimalFromHexadecimal = HexatoDecimal(hexadecimal);
                    String octalFromHexadecimal = ForOctal(decimalFromHexadecimal);
                    System.out.println(Constants.OCTAL_VALUE + octalFromHexadecimal);
                    break;

                case 13:  // Arithmetic Operations
                    System.out.println(Constants.CHOOSE_N_S);
                    System.out.println(Constants.B);
                    System.out.println(Constants.D);
                    System.out.println(Constants.O);
                    System.out.println(Constants.H);
                    System.out.print(Constants.ENTER_CHOICE);
                    int systemChoice = inputFromUser.nextInt();
                    inputFromUser.nextLine();  // Consume newline

                    System.out.println(Constants.CHOOSE_OPERATION);
                    System.out.println(Constants.ADD);
                    System.out.println(Constants.SUB);
                    System.out.println(Constants.MULTI);
                    System.out.println(Constants.DIV);
                    System.out.print(Constants.ENTER_CHOICE);
                    int operationChoice = inputFromUser.nextInt();
                    inputFromUser.nextLine();  // Consume newline

                    arithmeticOperations(systemChoice, operationChoice, inputFromUser);
                    break;

                case 14:  // Exit
                    exitProgram = true;
                    break;

                default:
                    System.out.println(Constants.INVALID);
            }
        }

        inputFromUser.close();
    }
}
