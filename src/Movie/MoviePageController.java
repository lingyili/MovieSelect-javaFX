package Movie;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by lingyi on 7/22/15.
 */
public class MoviePageController implements Initializable{
    @FXML
    private ImageView imageView;
    @FXML
    private Text jieshao;
    @FXML
    private ListView comment;
    private ArrayList<String> commentList;
    private Movie myMovie;
    private Image image;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myMovie = ProfileController.selectedMovie;
        RatingManager ratingManager = new RatingManager();
        commentList = new ArrayList<>();
        commentList = ratingManager.getComment(myMovie.getId());
        ObservableList<String> items = FXCollections.observableArrayList(commentList);
        comment.setItems(items);
        jieshao.setWrappingWidth(200);
        jieshao.setTextAlignment(TextAlignment.JUSTIFY);
        jieshao.setText(myMovie.getSynopsis());
        image = new Image(myMovie.getPosters().getOriginal());
        imageView.setImage(image);

    }
    public void CommentIt(Event event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("commentPage.fxml"));
        loader.load();
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Comment");
        stage.show();
    }
}
