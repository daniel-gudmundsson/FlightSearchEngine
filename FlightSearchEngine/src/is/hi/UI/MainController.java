/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.UI;

import is.hi.UI.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author dantg
 */
public class MainController implements Initializable {

    @FXML
    private ComboBox<?> fromComboBox;
    @FXML
    private ComboBox<?> toComboBox;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Slider priceSlider;
    @FXML
    private TextField priceTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
