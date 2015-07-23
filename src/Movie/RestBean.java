package Movie;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the bean for using REST Rotten Tomatoes API
 * @author Matt
 * @version 1.0
 */
public class RestBean {
    /**
     * data
     */
    private String data;
    /**
     * keyword
     */
    private String keyword;
    /**
     * moviedata
     */
    private List<Movie> movieData;
    /**
     * currmovie
     */
    private Movie currMovie;
    /**
     * heep_erro
     */
    private static final int HTTP_ERROR = 200;

    /**
     * this is a constructor
     */
    public RestBean() {
        System.out.println("made RestBean");
        movieData = new ArrayList<Movie>();
    }

    /**
     * this is the getter for keyWord
     * @return the keyWord
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * this is the setter for keyWord
     * @param keyword the new keyWord
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * This method make the api call and create all the needed objects
     * @param str the URL to the api call
     */
    public void apicall(String str) {
        URL url = null;
        try {
            url = new URL(str);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed: HTTP error code: " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            data = "";
            String buffer;
            while ((buffer = br.readLine()) != null) {
                data += buffer;
            }
            conn.disconnect();

        } catch (MalformedURLException e) {
            System.out.println("Malformed URL");
        } catch (IOException e) {
            System.out.println("Cannot open url");
        }
    }

    /**
     * This method is for searching movies based on key words
     * @return A list of movies
     */
    public List<Movie> search(String key) {
        keyword = key;
        keyword = URLEncoder.encode(keyword);
        String url = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=yedukp76ffytfuy24zsqk7f5&q="
                + keyword + "&page_limit=20";
        apicall(url);
        System.out.println(url);
        System.out.println(data);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        MovieResponse response = gson.fromJson(data, MovieResponse.class);
        List<Movie> movies = response.getMovies();
        movieData = movies;
        return movieData;
    }

    /**
     * get movie by id
     * @param id
     * @return
     */
    public Movie getMovieByID(String id) {
        Movie movie = null;
        try {
            String url = "http://api.rottentomatoes.com/api/public/v1.0/movies/"+id
                    +".json?apikey=yedukp76ffytfuy24zsqk7f5";
            URL newUrl = new URL(url);
            InputStreamReader isr = new InputStreamReader(newUrl.openStream(), "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            Gson gson = new Gson();
            movie = gson.fromJson(br, Movie.class);
        } catch (MalformedURLException m) {
            System.out.println(m.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return movie;
    }

    /**
     * this method search for movies which currently are in theaters
     * @return list of movies in theaters
     */
    public List<Movie> theaters() {
        String url = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/in_theaters.json?"
                + "apikey=yedukp76ffytfuy24zsqk7f5";
        apicall(url);

        Gson gson = new Gson();
        MovieResponse response = gson.fromJson(data, MovieResponse.class);
        List<Movie> movies = response.getMovies();
        movieData = movies;
        System.out.println(url);
        return movieData;
    }

    /**
     * this method search for movies which are newly released in DVDs
     * @return new movies in DVD
     */
    public List<Movie> dVD() {
        String url = "http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/new_releases.json?"
                + "apikey=yedukp76ffytfuy24zsqk7f5";
        apicall(url);
        Gson gson = new Gson();
        MovieResponse response = gson.fromJson(data, MovieResponse.class);
        List<Movie> movies = response.getMovies();
        movieData = movies;
        System.out.println(url);
        return movieData;
    }

    /**
     * get movie
     * @param movie
     * @return
     */
    public String getMovie(Movie movie) {
        currMovie = movie;
        return "movie";
    }

    /**
     * This method gets the current movie list
     * @return
     */
    public List<Movie> getMovieData() {
        return movieData;
    }

    /**
     * this method gets the number of movies
     * @return
     */
    public String getSize() {
        return "size: " + movieData.size();
    }

    /**
     * get current movie
     * @return
     */
    public Movie getCurrMovie() {
        return currMovie;
    }

    /**
     * set current movie
     * @param currMovie
     */
    public void setCurrMovie(Movie currMovie) {
        this.currMovie = currMovie;
    }
    /**
     * get data
     */
    public String getData() {
        return data;
    }
}
