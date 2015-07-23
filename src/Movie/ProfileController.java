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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by lingyi on 7/19/15.
 */
public class ProfileController implements Initializable{
    @FXML
    private Label username;
    @FXML
    private Label firstname;
    @FXML
    private Label lastname;
    @FXML
    private Label email;
    @FXML
    private Label major;
    @FXML
    private TextField Rsearch;
    @FXML
    private Text userId;
    private UserManager userManager;
    private User loginUser;
    private ArrayList<String> nameList;
    private ArrayList<Movie> movieArrayList;
    private List<Movie> dvdList;
    private HashMap<String, Movie> dvdMap;
    private List<Movie> newList;
    private HashMap<String, Movie> newMap;
    private List<Movie> resultList;
    private HashMap<String, Movie> resultMap;
    static Movie selectedMovie;
    @FXML
    private javafx.scene.control.ListView<String> listView;
    @FXML
    private ListView<String> dvdlistView;
    @FXML
    private ListView<String> releaseListView;
    @FXML
    private ListView<String> searchListView;

    public void showProfile(Event event) {
        userManager = new UserManager();
        User searched = userManager.searchUser(loginUser.getUsername(), loginUser.getPassword());
        username.setText(searched.getUsername());
        firstname.setText(searched.getFirstName());
        lastname.setText(searched.getLastName());
        email.setText(searched.getEmail());
        major.setText(searched.getMajor());
    }
    public void editPro(Event event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("editProfile.fxml"));
        loader.load();
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Edit Your Profile");
        EditProfile profileController = loader.getController();
        profileController.setCurrentUser(loginUser);
        stage.show();
    }
    public void showNewDVD(Event event) {
        RestBean restBean = new RestBean();
        nameList = new ArrayList<>();
        dvdList = restBean.dVD();
        dvdMap = new HashMap<>();
        for (Movie m : dvdList) {
            dvdMap.put(m.getTitle(), m);
            nameList.add(m.getTitle());
        }
        ObservableList<String> items = FXCollections.observableArrayList(nameList);
        dvdlistView.setItems(items);
    }
    public void showNewRelease(Event event) {
        RestBean restBean = new RestBean();
        nameList = new ArrayList<>();
        newList = restBean.theaters();
        newMap = new HashMap<>();
        for (Movie m : newList) {
            newMap.put(m.getTitle(), m);
            nameList.add(m.getTitle());
        }
        ObservableList<String> items = FXCollections.observableArrayList(nameList);
        releaseListView.setItems(items);
    }
    public void showMayLike(Event event) {
        RatingManager ratingManager = new RatingManager();
        nameList = new ArrayList<>();
        String major = LoginController.loginUser.getMajor();
        movieArrayList = ratingManager.getRecommendation(major);
        for (Movie m : movieArrayList) {
            nameList.add(m.getTitle());
        }
        ObservableList<String> observableList = FXCollections.observableArrayList(nameList);
        listView.setItems(observableList);
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
        userId.setFont(new Font(15));
        userId.setText("Welcome " + LoginController.loginUser.getUsername());
    }
    public void DVDSelectMovie(Event event) throws IOException{
        String selectedItem = dvdlistView.getSelectionModel().getSelectedItem();
        selectedMovie = dvdMap.get(selectedItem);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("moviePage.fxml"));
        loader.load();
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        Scene scene = new Scene(parent,700,700);
        stage.setScene(scene);
        stage.setTitle(selectedMovie.getTitle());
        MoviePageController userController = loader.<MoviePageController>getController();
        stage.show();
    }
    public void NewReleaseSelectMovie(Event event) throws IOException {
        String selectedItem = releaseListView.getSelectionModel().getSelectedItem();
        selectedMovie = newMap.get(selectedItem);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("moviePage.fxml"));
        loader.load();
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        Scene scene = new Scene(parent, 700,700);
        stage.setScene(scene);
        stage.setTitle(selectedMovie.getTitle());
        MoviePageController userController = loader.<MoviePageController>getController();
        stage.show();
    }
    public void searchMovie(Event event) throws IOException{
        String result = Rsearch.getText();
        RestBean restBean = new RestBean();
        resultList = restBean.search(result);
        nameList = new ArrayList<>();
        resultMap = new HashMap<>();
        for (Movie m : resultList) {
            nameList.add(m.getTitle());
            resultMap.put(m.getTitle(), m);
        }
        ObservableList<String> items = FXCollections.observableArrayList(nameList);
        searchListView.setItems(items);
    }
    public void selectSearchedMovie (Event event) throws IOException{
        String selectedItem = searchListView.getSelectionModel().getSelectedItem();
        selectedMovie = resultMap.get(selectedItem);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("moviePage.fxml"));
        loader.load();
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        Scene scene = new Scene(parent, 700,700);
        stage.setScene(scene);
        stage.setTitle(selectedMovie.getTitle());
        MoviePageController userController = loader.<MoviePageController>getController();
        stage.show();
    }
    public void logOut(Event event) throws IOException{
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
    public void setCurrentUser(User myUser) {
        this.loginUser = myUser;
    }
}
