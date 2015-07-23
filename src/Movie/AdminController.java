package Movie;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by lingyi on 7/22/15.
 */
public class AdminController implements Initializable {
    @FXML
    private ListView<String> listView;
    private AdminManager adminManager;
    private ObservableList<String> items;
    private ArrayList<String> userNameList;
    private ArrayList<User> userList;
    private HashMap<String, User> map;
    static User selectedUser;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adminManager = new AdminManager();
        userList = adminManager.getUserList();
        userNameList = new ArrayList<>();
        map = new HashMap<>();
        for ( User u : userList) {
            map.put(u.getUsername(), u);
            userNameList.add(u.getUsername());
        }
        ObservableList<String> items = FXCollections.observableArrayList(userNameList);
        listView.setItems(items);
    }
    @FXML
    private void Selection(Event event) throws IOException{
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        selectedUser = map.get(selectedItem);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("userPage.fxml"));
        loader.load();
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("View User");
        UserPageController userController = loader.<UserPageController>getController();
        stage.show();

    }
    public void logout(Event event) throws IOException{
        ((Node)(event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Welcome.fxml"));
        loader.load();
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Hello Movie");
        stage.show();
    }
}
