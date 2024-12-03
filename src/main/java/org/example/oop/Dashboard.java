package org.example.oop;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class Dashboard implements Initializable {
    private double x;
    private double y;
    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle rb) {
        showAvailableBoooks();
        username();
    }
    @FXML
    private Button arrow_btn;

    @FXML
    private Button availableBooks_btn;

    @FXML
    private AnchorPane availableBooks_form;

    @FXML
    private ImageView availableBooks_imageView;

    @FXML
    private TableView<AvailableBooks> availableBooks_tableView;

    @FXML
    private Label availableBooks_title;

    @FXML
    private Button bars_btn;

    @FXML
    private Circle circle_image;

    @FXML
    private Button close;

    @FXML
    private TableColumn<AvailableBooks, String> col_ab_author;

    @FXML
    private TableColumn<AvailableBooks, String> col_ab_bookTitle;

    @FXML
    private TableColumn<AvailableBooks, String> col_ab_bookType;

    @FXML
    private TableColumn<AvailableBooks, Date> col_ab_publishedDate;

    @FXML
    private TableColumn<?, ?> col_return_Author;

    @FXML
    private TableColumn<?, ?> col_return_BookTitle;

    @FXML
    private TableColumn<?, ?> col_return_bookType;

    @FXML
    private TableColumn<?, ?> col_return_dateIssue;

    @FXML
    private TableColumn<?, ?> col_saveAuthor;

    @FXML
    private TableColumn<?, ?> col_saveDate;

    @FXML
    private TableColumn<?, ?> col_saveGenre;

    @FXML
    private TableColumn<?, ?> col_saveTitle;

    @FXML
    private Label currentForm_label;

    @FXML
    private Button edit_btn;

    @FXML
    private FontAwesomeIcon edit_icon;

    @FXML
    private Button halfNav_availableBtn;

    @FXML
    private AnchorPane halfNav_form;

    @FXML
    private Button halfNav_returnBtn;

    @FXML
    private Button halfNav_saveBtn;

    @FXML
    private Button halfNav_takeBtn;

    @FXML
    private Button issueBooks_btn;

    @FXML
    private AnchorPane issue_form;

    @FXML
    private Button logout_btn;

    @FXML
    private AnchorPane mainCenter_form;

    @FXML
    private Button minimize;

    @FXML
    private AnchorPane nav_form;

    @FXML
    private AnchorPane returnBook_form;

    @FXML
    private Button returnBooks_btn;

    @FXML
    private Button return_button;

    @FXML
    private ImageView return_imageView;

    @FXML
    private TableView<?> return_tableView;

    @FXML
    private Button save_btn;

    @FXML
    private ImageView save_imageView;

    @FXML
    private TableView<?> save_tableView;

    @FXML
    private AnchorPane savedBook_form;

    @FXML
    private Button savedBooks_btn;

    @FXML
    private Circle smallCircle_image;

    @FXML
    private Label studentNumber_label;

    @FXML
    private TextField take_BookTitle;

    @FXML
    private TextField take_FirstName;

    @FXML
    private ComboBox<?> take_Gender;

    @FXML
    private Label take_IssuedDate;

    @FXML
    private TextField take_LastName;

    @FXML
    private Label take_StudentNumber;

    @FXML
    private Label take_authorLabel;

    @FXML
    private Button take_btn;

    @FXML
    private Button take_clearBtn;

    @FXML
    private Label take_dateLabel;

    @FXML
    private Label take_genreLabel;

    @FXML
    private ImageView take_imageView;

    @FXML
    private Button take_takeBtn;

    @FXML
    private Label take_titleLabel;

    @FXML
    private Button unsaveBtn;

    public void logout(ActionEvent event) {
        try {
            if (event.getSource() == logout_btn) {
                Parent root = FXMLLoader.load(getClass().getResource("/org/example/oop/hello-view.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent e) -> {
                    x = e.getSceneX();
                    y = e.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent e) -> {
                    stage.setX(e.getScreenX() - x);
                    stage.setY(e.getScreenY() - y);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();

                logout_btn.getScene().getWindow().hide();
            }
        } catch (IOException e) {
            System.err.println("Failed to load FXML file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void close(ActionEvent event) {
        try {
            Stage stage = (Stage) close.getScene().getWindow();
            stage.close();
            System.out.println("Window closed successfully.");
        } catch (Exception e) {
            System.err.println("Error occurred while closing the window: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void minimize(ActionEvent event) {
        try {
            Stage stage = (Stage) minimize.getScene().getWindow();
            stage.setIconified(true);
            System.out.println("Window minimized successfully.");
        } catch (Exception e) {
            System.err.println("Error occurred while minimizing the window: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean isNavOpen = false;

    public void slideBar() {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.millis(300));
        slide.setNode(nav_form);

        TranslateTransition mainCenterSlide = new TranslateTransition();
        mainCenterSlide.setDuration(Duration.millis(300));
        mainCenterSlide.setNode(mainCenter_form);

        if (isNavOpen) {
            // Close nav_form and move main_center to the center
            slide.setFromX(-224);
            slide.setToX(0);

            mainCenterSlide.setFromX(-110);
            mainCenterSlide.setToX(0);

        } else {
            // Open nav_form and move main_center to the right
            slide.setFromX(0);
            slide.setToX(-224);

            mainCenterSlide.setFromX(0);
            mainCenterSlide.setToX(-110);
        }

        slide.setCycleCount(1);
        slide.setAutoReverse(false);
        mainCenterSlide.setCycleCount(1);
        mainCenterSlide.setAutoReverse(false);

        slide.play();
        mainCenterSlide.play();

        slide.setOnFinished(event -> isNavOpen = !isNavOpen);
    }
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    public ObservableList<AvailableBooks> dataList() {
        ObservableList<AvailableBooks> listBooks =FXCollections.observableArrayList();
        String sql = "SELECT * From book";
        connect = Database.connectDB();
        try {
            AvailableBooks abooks;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while(result.next()) {
                abooks = new AvailableBooks(result.getString("bookTitle"), result.getString("author"), result.getString("bookType"),
                        result.getString("image"),result.getDate("date"));
                listBooks.add(abooks);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listBooks;
    }

    private ObservableList<AvailableBooks> listBook;
    public void showAvailableBoooks() {
        listBook = dataList();
        col_ab_bookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_ab_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        col_ab_bookType.setCellValueFactory(new PropertyValueFactory<>("genre"));
        col_ab_publishedDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        availableBooks_tableView.setItems(listBook);
    }
    private Image image;
    public void selectAvailableBooks() {
        AvailableBooks bookData = availableBooks_tableView.getSelectionModel().getSelectedItem();
        int num = availableBooks_tableView.getSelectionModel().getSelectedIndex();
        if((num - 1) < -1) {
            return;
        }
        availableBooks_title.setText(bookData.getTitle());
        String url = "file:" + bookData.getImage();
        image = new Image(url, 134, 171, false, true);
        availableBooks_imageView.setImage(image);
        availableBooks_title.setText(bookData.getTitle());
    }

    public void username() {
        studentNumber_label.setText(getData.username);
    }

    public void abTakeButton(ActionEvent event) {
        if(event.getSource() == take_btn) {
            issue_form.setVisible(true);
            availableBooks_form.setVisible(false);
            savedBook_form.setVisible(false);
            returnBook_form.setVisible(false);
        }
    }

    public void navButtonDesign(ActionEvent event) {
        if(event.getSource() == availableBooks_btn) {
            issue_form.setVisible(false);
            availableBooks_form.setVisible(true);
            savedBook_form.setVisible(false);
            returnBook_form.setVisible(false);

            availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");

            currentForm_label.setText("Available Books");
        } else if (event.getSource() == issueBooks_btn) {
            issue_form.setVisible(true);
            availableBooks_form.setVisible(false);
            savedBook_form.setVisible(false);
            returnBook_form.setVisible(false);

            issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");

            currentForm_label.setText("Issue Books");
        } else if (event.getSource() == savedBooks_btn) {
            issue_form.setVisible(false);
            availableBooks_form.setVisible(false);
            savedBook_form.setVisible(true);
            returnBook_form.setVisible(false);

            savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");

            currentForm_label.setText("Saved Books");
        } else if (event.getSource() == returnBooks_btn) {
            issue_form.setVisible(false);
            availableBooks_form.setVisible(false);
            savedBook_form.setVisible(false);
            returnBook_form.setVisible(true);

            returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");

            currentForm_label.setText("Return Books");
        }
    }
}
