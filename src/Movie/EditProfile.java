package Movie;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by lingyi on 7/22/15.
 */
public class EditProfile implements Initializable {
    @FXML
    private ComboBox<String> Rmajor;
    @FXML
    private TextField Rfirstname;
    @FXML
    private TextField Rlastname;
    @FXML
    private TextField Remail;
    @FXML
    private TextField Rpassword;
    private ObservableList<String> options = FXCollections.observableArrayList("CS", "CM", "BME", "CmpE", "EE", "EA");
    private User user;
    private UserManager userManager;
    private User loginUser;
    @FXML
    private void SubmitClick(ActionEvent event) {
        String password = Rpassword.getText();
        String firstname = Rfirstname.getText();
        String lastname = Rlastname.getText();
        String email = Remail.getText();
        String major = Rmajor.getValue();
        user = new User(loginUser.getUsername(), password, firstname, lastname, email, major);
        userManager = new UserManager();
        userManager.updateProfile(user);
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Rmajor.setItems(options);
    }
    public void setCurrentUser(User myUser) {
        this.loginUser = myUser;
    }
}
