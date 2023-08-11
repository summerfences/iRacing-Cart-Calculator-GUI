/*
Java program written with a Swing GUI that allows the
user to calculate the cost of an iRacing cart. Currently,
only USD is supported, and no support for tax is included.
Written by Frank T Passantino: v1.1, 8/10/2023
 */
public class Main {
    public static void main(String[] args) {
        CalculatorGUI gui = new CalculatorGUI();
        gui.build();
    }
}

// TODO: add tax functionality, EUR/CAD support
