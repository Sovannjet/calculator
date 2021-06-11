package Calculator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import java.io.IOException;

/**
 * FXML Controller class
 *
 * @author rajesh
 */
public class CalcUIController implements Initializable {

    Double temp = 0.0, sum = 0.0;
    boolean isOperatorPressed;
    String operatorPressed = "";

    private String expression = "";
    private double num1;
    private double num2;

    @FXML
    TextField outputTF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        outputTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*([\\.]\\d*)?")) {
                    outputTF.setText(oldValue);
                }
            }
        });
    }

    //If operator is pressed set outputTF to the button press, else set outputTF to what's in outputTF and button press
    @FXML
    private void onNumberClick(ActionEvent event) {
        Button btn = (Button) event.getSource();
        if (!btn.getText().equals(".") || expression.indexOf(".")<0) { // don't allow a decimal if there already is one
            expression += btn.getText();
            outputTF.setText(expression);
        }
    }

    /*
        Checks if outputTF is not empty
        Checks which button was pressed and performs the correct operation on sum
        Checks if button was "=" or "%"; if so, sets outputTF to the sum & operatorPressed to default
            if not, sets outputTF to default and operatorPressed to the button pressed
        flags operator pressed to true
     */
    @FXML
    private void onOperatorClick(ActionEvent event) {
        Button btn = (Button) event.getSource();
        if (outputTF.getText().length() > 0) {
            num1 = Double.parseDouble(expression); // save num1 to a variable
            operatorPressed = btn.getText(); // save operator to a variable
        }
        expression = ""; // reset expression & outputTF
        outputTF.setText(expression);
    }

    //If outputTF is not empty remove 1 char from the outputTF
    @FXML
    private void onDELClick(ActionEvent event) {
        expression = expression.substring(0, expression.length()-1);
        outputTF.setText(expression);
    }

    //Reset all values to their default states
    @FXML
    private void onCEClick(ActionEvent event) {
        num1 = 0;
        operatorPressed = "";
        num2 = 0;
        expression = "";
        outputTF.setText(expression);
    }

    @FXML
    private void onEQClick(ActionEvent event) {
        Button btn = (Button) event.getSource();
        if (outputTF.getText().length() > 0) {
            double answer = Integer.MIN_VALUE;
            num2 = Double.parseDouble(expression);
            switch(operatorPressed) {
                case "+": answer = num1 + num2; break;
                case "-": answer = num1 - num2; break;
                case "*": answer = num1 * num2; break;
                case "/": answer = num1 / num2; break;
                case "%": answer = num1 % num2; break;
            }

            if (answer == Integer.MIN_VALUE) {
                outputTF.setText("Error");
            }
            else {
                expression = Double.toString(answer);
                outputTF.setText(expression);
            }

            operatorPressed = ""; // reset operator
        }
    }


    // methods to change CSS based on hover

    /* @FXML
    private void onMouseEnter(ActionEvent event) {
        Button btn = (Button) event.getSource();
        btn.setStyle("-fx-background-color: red");
    }

    @FXML
    private void onMouseExit(ActionEvent event) {
        Button btn = (Button) event.getSource();
        btn.setStyle("-fx-background-color: green");
    } */

}
