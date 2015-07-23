package Movie;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by lingyi on 7/22/15.
 */
public class CommentController implements Initializable{
    @FXML
    private TextArea commentString;
    @FXML
    private ComboBox<Integer> rating;
    private ObservableList<String> options = FXCollections.observableArrayList("10", "9", "8", "7", "6", "5", "4", "3", "2", "1");
    private ObservableList<Integer> options2 = FXCollections.observableArrayList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
    public void ButtonClick(Event event) {
        String comment = commentString.getText();
        int rate = rating.getValue();
        RatingManager ratingManager = new RatingManager();
        if (rate <= 10 && rate >=0 && comment != null) {
            ratingManager.storeRateAndComment(rate, comment, LoginController.loginUser.getUsername(), ProfileController.selectedMovie.getId(),ProfileController.selectedMovie.getTitle(), LoginController.loginUser.getMajor());
        } else if (comment != null){
            ratingManager.storeComment(comment, LoginController.loginUser.getUsername(), ProfileController.selectedMovie.getId(), ProfileController.selectedMovie.getTitle(), LoginController.loginUser.getMajor());
        }
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
        rating.setItems(options2);
    }
}
