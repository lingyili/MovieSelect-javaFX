package Movie;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{
    UserManager userManager;
    static User loginUser;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    public void clickLogin(ActionEvent event) throws IOException {
        userManager = new UserManager();
        String pass = password.getText().toString();
        String user = username.getText().toString();
        loginUser = userManager.searchUser(user, pass);
        if (loginUser != null) {
            if (loginUser.getStatus().equalsIgnoreCase("admin")) {
                ((Node)(event.getSource())).getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("admin.fxml"));
                loader.load();
                Parent parent = loader.getRoot();
                Stage stage = new Stage();
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.setTitle("Administration");
                stage.show();
            } else {
                ((Node) (event.getSource())).getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("home.fxml"));
                loader.load();
                Parent parent = loader.getRoot();
                Stage stage = new Stage();
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.setTitle("Home");
                ProfileController profileController = loader.<ProfileController>getController();
                profileController.setCurrentUser(loginUser);
                stage.show();
            }
        } else {
            System.out.print("wrong");
        }

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

    }

}
