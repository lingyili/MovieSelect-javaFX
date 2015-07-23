package Movie;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by lingyi on 7/22/15.
 */
public class UserPageController implements Initializable {
    @FXML
    private Label Rusername;
    @FXML
    private Label Rfirstname;
    @FXML
    private Label Rlastname;
    @FXML
    private Label Rstatus;
    @FXML
    private Label Rmajor;
    @FXML
    private Label Remail;
    @FXML
    private Button Bban;
    @FXML
    private Button Block;

    private UserManager userManager;
    private User myUser;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userManager = new UserManager();
        myUser = AdminController.selectedUser;
        Rusername.setText(AdminController.selectedUser.getUsername());
        Rfirstname.setText(AdminController.selectedUser.getFirstName());
        Rlastname.setText(AdminController.selectedUser.getLastName());
        Remail.setText(AdminController.selectedUser.getEmail());
        Rmajor.setText(AdminController.selectedUser.getMajor());
        Rstatus.setText(AdminController.selectedUser.getStatus());
        if (AdminController.selectedUser.getStatus().equalsIgnoreCase("banned")) {
            Bban.setText("Unban");
        } else {
            Bban.setText("Ban");
        }
        if (AdminController.selectedUser.getStatus().equalsIgnoreCase("locked")) {
            Block.setText("Unlock");
        } else {
            Block.setText("Lock");
        }
    }
    @FXML
    private void BanButtonclick(Event event) {
        if (myUser.getStatus().equalsIgnoreCase("banned")) {
            myUser.setStatus("active");
            userManager.updateStatus(myUser);
        } else {
            myUser.setStatus("banned");
            userManager.updateStatus(myUser);
        }
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    @FXML
    private void LockButtonClick(Event event) {
        if (myUser.getStatus().equalsIgnoreCase("locked")) {
            myUser.setStatus("active");
            userManager.updateStatus(myUser);
        } else {
            myUser.setStatus("locked");
            userManager.updateStatus(myUser);
        }
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
