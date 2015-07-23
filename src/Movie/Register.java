package Movie;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by lingyi on 7/20/15.
 */
public class Register implements Initializable{
    @FXML
    private Label label;
    @FXML
    private TextField Rusername;
    @FXML
    private TextField Rpassword;
    @FXML
    private TextField Rfirstname;
    @FXML
    private TextField Rlastname;
    @FXML
    private TextField Remail;
    @FXML
    private ComboBox<String> Rmajor;
    private User user;
    private UserManager userManager;

    @FXML
    private void ClickRegister(ActionEvent event) throws IOException{
        String username = Rusername.getText();
        String password = Rpassword.getText();
        String firstname = Rfirstname.getText();
        String lastname = Rlastname.getText();
        String email = Remail.getText();
        String major = Rmajor.getValue();
        user = new User(username, password, firstname, lastname, email, major);
        userManager = new UserManager();
        if (userManager.addUser(username, password, firstname, lastname, email, major)) {
            ((Node)(event.getSource())).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("home.fxml"));
            loader.load();
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Home");
            ProfileController profileController = loader.getController();
            profileController.setCurrentUser(user);
            stage.show();
        }
    }
    ObservableList<String> options = FXCollections.observableArrayList("CS", "CM", "BME", "CmpE", "EE", "EA");
    public void initialize(URL location, ResourceBundle resources) {
       Rmajor.setItems(options);
    }
}
