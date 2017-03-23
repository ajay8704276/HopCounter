package com.company;

import java.util.Scanner;

public class MinHopCount {

    private String[] inputStringArray;
    private String input;
    private String output;
    public static int hopCount = 0; // default value init
    private Scanner mScanner = new Scanner(System.in);
    /*private Queue inputCharArrayQueue = new LinkedList();
    private Queue outputCharArrayQueue = new LinkedList();*/

    public static void main(String[] args) {

        // Read input from console

        MinHopCount mMinHopCount = new MinHopCount();
        mMinHopCount.readInput();
        System.out.println("Total Hop Count : " + hopCount);
    }

    private void readInput() {

        int inputLength;

        System.out.println("Enter the length of String Array");
        inputLength = mScanner.nextInt();
        inputStringArray = new String[inputLength];
        System.out.println("Enter the three character string like cat , mat ,xat etc");
        for (int i = 0; i < inputLength; i++) {
            Scanner sc = new Scanner(System.in);
            inputStringArray[i] = sc.nextLine();
        }

        //Validate first input i.e only existing string can be taken as input other than that doesnot exist exception
        boolean isValidInput = validateFirstInput(inputStringArray);
        boolean isValidOutput = validateOutput(inputStringArray);

        //If both input/output String are valid then convert both input and output String into character
        //character array using java
        if (isValidInput && isValidOutput) {

            //Converting input array into char array and then add to queue
            char[] inputCharArray = input.toCharArray();

            //Converting output array into char array and then add to queue
            char[] outputCharArray = output.toCharArray();

            //Logic to count the minimum hop/jump
            countTheHop(inputCharArray, outputCharArray);
        }
    }

    /**
     * @param inputCharArray  input char array
     * @param outputCharArray output char array
     */
    private void countTheHop(char[] inputCharArray, char[] outputCharArray) {
        int charArraylength = inputCharArray.length;
        for (int i = 0; i < charArraylength; i++) {
            char tempInputChar = inputCharArray[i];
            char tempOutputChar = outputCharArray[i];
            if (tempInputChar != tempOutputChar) {
                // replace input character with output
                inputCharArray[i] = outputCharArray[i];
                char[] tempCharArray = inputCharArray;
                //now check input array with word exist or not
                String input = String.valueOf(tempCharArray);
                for (int j = 0; j < inputStringArray.length; j++) {
                    //if input array exist in String array
                    if (inputStringArray[j].contains(input)) {
                        // increase the hop count
                        hopCount++;
                    }
                }
            }
        }
    }

    /**
     * @param inputStringArray String Array
     * @return return true if output  is different from input string
     */
    private boolean validateOutput(String[] inputStringArray) {
        System.out.println("Enter the output String");
        Scanner sc = new Scanner(System.in);
        output = sc.nextLine();
        for (int i = 0; i < inputStringArray.length; i++) {
            if (inputStringArray[i].contains(output)) {
                System.out.println("output String found !!");
                if (!input.contains(output)) {
                    return true;
                } else {
                    try {
                        throw new InvalidInputOutputStringException("Input output String cannot be same");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return false;
                }
            } else {
                try {
                    throw new InvalidInputStringException("output String not found");
                } catch (InvalidInputStringException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    private boolean validateFirstInput(String[] inputStringArray) {

        System.out.println("Enter the first input String");
        Scanner sc = new Scanner(System.in);
        input = sc.next();
        for (int i = 0; i < inputStringArray.length; i++) {
            if (inputStringArray[i].contains(input)) {
                System.out.println("Input String found !!");
                return true;
            } /*else {
                try {
                    throw new InvalidInputStringException("Input String not found");
                } catch (InvalidInputStringException e) {
                    e.printStackTrace();
                }
            }*/
        }
        return false;
    }
}
