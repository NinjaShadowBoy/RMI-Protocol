package com.sji.rmiprotocoldemo.client;

import com.sji.rmiprotocoldemo.common.ComputeEngineInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorController {

    @FXML
    private Label display;

    private String currentInput = "0";

    private final Short MAX_INPUT_LENGTH = 100;

    private final String IP_ADD = "127.0.0.1";

    @FXML
    private void initialize() {
        display.setText(currentInput);
    }

    // Number buttons
    @FXML
    private void print0() {
        appendNumber("0");
    }

    @FXML
    private void print1() {
        appendNumber("1");
    }

    @FXML
    private void print2() {
        appendNumber("2");
    }

    @FXML
    private void print3() {
        appendNumber("3");
    }

    @FXML
    private void print4() {
        appendNumber("4");
    }

    @FXML
    private void print5() {
        appendNumber("5");
    }

    @FXML
    private void print6() {
        appendNumber("6");
    }

    @FXML
    private void print7() {
        appendNumber("7");
    }

    @FXML
    private void print8() {
        appendNumber("8");
    }

    @FXML
    private void print9() {
        appendNumber("9");
    }

    // Decimal point
    @FXML
    private void printDot() {
        appendNumber(".");
    }

    // Operators
    @FXML
    private void add() {
        appendOperator("+");
    }

    @FXML
    private void subtract() {
        appendOperator("-");
    }

    @FXML
    private void multiply() {
        appendOperator("*");
    }

    @FXML
    private void divide() {
        appendOperator("/");
    }

    // Functions
    @FXML
    private void clear() {
        currentInput = "";
        display.setText("0");
    }

    @FXML
    private void delete() {
        if (!currentInput.isEmpty()) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
            display.setText(currentInput.isEmpty() ? "0" : currentInput);
        }
    }

    @FXML
    private void negate() {
        if (!currentInput.isEmpty()) {
            if (currentInput.startsWith("-")) currentInput = currentInput.substring(1);
            else currentInput = "-" + currentInput;
            display.setText(currentInput);
        }
    }

    @FXML
    private void percent() {
        appendOperator("%");
    }

    // Equals (you can expand this with proper expression evaluation)
    @FXML
    private void calculate() throws RemoteException, NotBoundException {
        // RMI client call
        try {
            Registry registry = LocateRegistry.getRegistry(IP_ADD, 1099);

            ComputeEngineInterface stub = (ComputeEngineInterface) registry.lookup("compute");

            currentInput = stub.computeRequest(currentInput);
            display.setText(currentInput);
        } catch (Exception e) {
            display.setText(e.getMessage());
        }
    }

    // Helper methods
    private void appendNumber(String num) {
        if (currentInput.length() < MAX_INPUT_LENGTH) currentInput += num;
        display.setText(currentInput);
    }

    private void appendOperator(String op) {
        if (currentInput.length() < MAX_INPUT_LENGTH - 2) currentInput += op;
        display.setText(currentInput);
    }

}
