/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.edu.pw.ee.MyExceptions;

/**
 *
 * @author Paweł
 */
public class IllegalWeightException extends Exception {
    
    private final int lineNumber;
    
    public IllegalWeightException(int lineNumber){
        this.lineNumber=lineNumber;
    }
    public int getLineNumber(){
        return this.lineNumber;
    }
}
