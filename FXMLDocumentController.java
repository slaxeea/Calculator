
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author kappe
 */
public class FXMLDocumentController implements Initializable {

    private String zahl1 = "";
    private String zahl2 = "";
    private String operation = "";
    Float resultat = null;
    boolean strPressed = false;
    boolean rclPressed = false;
    boolean powPressed = false;
    float stored[] = new float[4];

    @FXML
    private Label screen;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private Button btn6;
    @FXML
    private Button btn7;
    @FXML
    private Button btn8;
    @FXML
    private Button btn9;
    @FXML
    private Button btn0;
    @FXML
    private Button btn5;
    @FXML
    private Button btnEnter;
    @FXML
    private Button btnPlus;
    @FXML
    private Button btnMius;
    @FXML
    private Button btnMal;
    @FXML
    private Button btnGeteilt;
    @FXML
    private Button btnC;
    @FXML
    private Button btnStr;
    @FXML
    private Button btnRcl;
    @FXML
    private Button btnHoch;
    @FXML
    private Button btnQuadrat;
    @FXML
    private Button btnBack;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Not used rn. Dont need to initialize anyfin
    }

    @FXML
    private void btn1(ActionEvent event) {
        addNumb(1);
    }

    @FXML
    private void btn2(ActionEvent event) {
        addNumb(2);
    }

    @FXML
    private void btn3(ActionEvent event) {
        addNumb(3);
    }

    @FXML
    private void btn4(ActionEvent event) {
        addNumb(4);
    }

    @FXML
    private void btn5(ActionEvent event) {
        addNumb(5);
    }

    @FXML
    private void btn6(ActionEvent event) {
        addNumb(6);
    }

    @FXML
    private void btn7(ActionEvent event) {
        addNumb(7);
    }

    @FXML
    private void btn8(ActionEvent event) {
        addNumb(8);
    }

    @FXML
    private void btn9(ActionEvent event) {
        addNumb(9);
    }

    @FXML
    private void btn0(ActionEvent event) {
        addNumb(0);
    }

    // Berechnet die Resultate
    @FXML
    private void btnEnter(ActionEvent event) throws CalculatorException {
        if (zahl1 != "" && zahl2 != "") {
            try {
                if (operation == "+") {
                    resultat = Float.valueOf(zahl1) + Float.valueOf(zahl2);
                }
                if (operation == "*") {
                    resultat = Float.valueOf(zahl1) * Float.valueOf(zahl2);
                }
                if (operation == "-") {
                    resultat = Float.valueOf(zahl1) - Float.valueOf(zahl2);
                }
                if (operation == "/") {
                    resultat = Float.valueOf(zahl1) / Float.valueOf(zahl2);
                }
                if (String.valueOf(String.valueOf(zahl1) + "  " + operation + " " + String.valueOf(zahl2) + " = " + String.valueOf(resultat)).length() > 18 && String.valueOf(resultat).length() > 10) {
                    screen.setText("Too long, stupid");
                } else {
                    screen.setText(String.valueOf(zahl1) + "  " + operation + " " + String.valueOf(zahl2) + " = " + String.valueOf(resultat));
                }
            } catch (Exception e) {
                screen.setText("Syntax Error");
                System.out.println(e);
                throw new CalculatorException("Syntax Error");
            }
        } else {
            resultat = Float.valueOf(zahl1);
            screen.setText(String.valueOf(zahl1) + "  " + operation + " " + String.valueOf(zahl2) + " = " + String.valueOf(resultat));

        }

    }

    @FXML
    private void btnPlus(ActionEvent event) {
        addOper("+");
    }

    @FXML
    private void btnMinus(ActionEvent event) {
        addOper("-");
    }

    @FXML
    private void btnMal(ActionEvent event) {
        addOper("*");
    }

    @FXML
    private void btnGeteilt(ActionEvent event) {
        addOper("/");
    }

    // Backspace
    @FXML
    private void btnC(ActionEvent event) {
        zahl1 = "";
        zahl2 = "";
        operation = "";
        resultat = null;
        rclPressed = false;
        powPressed = false;
        strPressed = false;
        screen.setText("0");
    }

    // Eine Funktion für alle Nummern
    private void addNumb(Integer a) {
        if (powPressed) {
            pow(a);
        }
        if (!strPressed && !rclPressed) {
            if (resultat == null) {
                if (operation == "") {
                    zahl1 += String.valueOf(a);

                } else {
                    zahl2 += String.valueOf(a);
                }
            } else {
                zahl2 += String.valueOf(a);
            }
            screen.setText(String.valueOf(zahl1) + "  " + operation + " " + String.valueOf(zahl2));
        } else {
            strRcl(a);
        }

    }

    // Wenn ein Operator button gedrückt wird
    private void addOper(String op) {
        if (operation == "") {
            operation = op;
        } else {
            operation = op;
            zahl1 = String.valueOf(resultat);
            zahl2 = "";
        }
        screen.setText(String.valueOf(zahl1) + "  " + operation + " " + String.valueOf(zahl2));

    }
    
    // Str Button wird gedrückt
    @FXML
    private void btnStr(ActionEvent event) {
        strPressed = true;
        displayStored();
    }

    // Rcl wird gedrückt
    @FXML
    private void btnRcl(ActionEvent event) {
        rclPressed = true;
        displayStored();
    }

    // Für ^n Operationen
    @FXML
    private void btnHoch(ActionEvent event) {
        powPressed = true;
    }

    // Für ^2 Operationen
    @FXML
    private void btnQuadrat(ActionEvent event) {
        ownPow(2);
    }

    // Gespeicherte Zahlen wiedergeben
    private void displayStored() {
        String s = "| ";
        for (float a : stored) {
            s += String.valueOf(a);
            s += " | ";
        }
        screen.setText(s);
    }
    
    
    // Backspace
    @FXML
    private void btnBack(ActionEvent event) {

        if (zahl2 != "" && operation != "" && zahl1 != "") {
            zahl2 = removeOne(zahl2);
        } else if (zahl2 == "" && operation != "" && zahl1 != "") {
            operation = "";
        } else if (zahl1 != "" && zahl2 == "" && operation == "") {
            zahl1 = removeOne(zahl1);
        }
        screen.setText(String.valueOf(zahl1) + "  " + operation + " " + String.valueOf(zahl2));

    }

    // Die letzte Char eines Strings entfernen
    private String removeOne(String str) {
        String rtn;
        try {
            StringBuffer sb = new StringBuffer(str);
            sb.deleteCharAt(sb.length() - 1);
            rtn = sb == null ? "" : String.valueOf(sb);
        } catch (Exception e) {
            return "";
        }
        return rtn;
    }

    private void pow(int zahl) {
        ownPow(zahl);
    }

    private void ownPow(int pow){
        if (resultat == null) {
            if (zahl2 != "" && operation != "" && zahl1 != "") {
                zahl2 = String.valueOf(Math.pow(Double.valueOf(zahl2), pow));
            } else {
                zahl1 = String.valueOf(Math.pow(Double.valueOf(zahl1), pow));
            }
        } else if (resultat != null) {
            zahl2 = "";
            operation = "";
            zahl1 = String.valueOf(Math.pow(Double.valueOf(resultat), pow));

        }
        screen.setText(String.valueOf(zahl1) + "  " + operation + " " + String.valueOf(zahl2));

    }
    private void strRcl(int a) {
        if (strPressed) {
            stored[a - 1] = resultat;
        } else {
            if (operation == "") {
                zahl1 += String.valueOf(stored[a - 1]);

            } else {
                zahl2 += String.valueOf(stored[a - 1]);
            }
        }
        screen.setText(String.valueOf(zahl1) + "  " + operation + " " + String.valueOf(zahl2));
        strPressed = false;
        rclPressed = false;

    }

}
