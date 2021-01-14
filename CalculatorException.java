/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kappe
 */
public class CalculatorException extends Exception {

    /**
     * Creates a new instance of <code>CalculatorException</code> without detail
     * message.
     */
    public CalculatorException() {
    }

    /**
     * Constructs an instance of <code>CalculatorException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CalculatorException(String msg) {
        super(msg);
        System.out.println(msg);
    }
}
