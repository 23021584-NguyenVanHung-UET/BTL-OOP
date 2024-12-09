package org.example.oop;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Dashboard implements Initializable {
    private final String[] comboBox = {"Male", "Female", "Others"};
    boolean check = false;
    ObservableList<returnBook> retBook;
    private double x;
    private double y;
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
    private TableColumn<returnBook, String> col_return_Author;
    @FXML
    private TableColumn<returnBook, String> col_return_BookTitle;
    @FXML
    private TableColumn<returnBook, String> col_return_bookType;
    @FXML
    private TableColumn<returnBook, Date> col_return_dateIssue;
    @FXML
    private TableColumn<saveBook, String> col_saveAuthor;
    @FXML
    private TableColumn<saveBook, String> col_saveDate;
    @FXML
    private TableColumn<saveBook, String> col_saveGenre;
    @FXML
    private TableColumn<saveBook, String> col_saveTitle;
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
    private TableView<returnBook> return_tableView;
    @FXML
    private Button save_btn;
    @FXML
    private ImageView save_imageView;
    @FXML
    private TableView<saveBook> save_tableView;
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
    private boolean isNavOpen = false;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private ObservableList<AvailableBooks> listBook;
    private Image image;
    private ObservableList<returnBook> returnData;
    private ObservableList<saveBook> sBookList;

    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle rb) {
        showAvailableBoooks();
        username();
        gender();
        showSaveBooks();

        try {
            showBookReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    public ObservableList<AvailableBooks> dataList() {
        ObservableList<AvailableBooks> listBooks = FXCollections.observableArrayList();
        String sql = "SELECT * From book";
        connect = Database.connectDB();
        try {
            AvailableBooks abooks;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                abooks = new AvailableBooks(result.getString("bookTitle"), result.getString("author"), result.getString("bookType"),
                        result.getString("image"), result.getDate("date"));
                listBooks.add(abooks);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listBooks;
    }

    public void showAvailableBoooks() {
        listBook = dataList();
        col_ab_bookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_ab_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        col_ab_bookType.setCellValueFactory(new PropertyValueFactory<>("genre"));
        col_ab_publishedDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        availableBooks_tableView.setItems(listBook);
    }

    public void selectAvailableBooks() {
        AvailableBooks bookData = availableBooks_tableView.getSelectionModel().getSelectedItem();
        int num = availableBooks_tableView.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }
        availableBooks_title.setText(bookData.getTitle());
        String url = "file:" + bookData.getImage();
        image = new Image(url, 134, 171, false, true);
        availableBooks_imageView.setImage(image);
        availableBooks_title.setText(bookData.getTitle());

        getData.takeBookTitle = bookData.getTitle();

        getData.savedTitle = bookData.getTitle();
        getData.savedAuthor = bookData.getAuthor();
        getData.savedGenre = bookData.getGenre();
        getData.savedDate = bookData.getDate();
        getData.savedImage = bookData.getImage();

    }

    public void username() {
        studentNumber_label.setText(getData.user);
    }

    public void abTakeButton(ActionEvent event) {
        if (event.getSource() == take_btn) {
            issue_form.setVisible(true);
            availableBooks_form.setVisible(false);
            savedBook_form.setVisible(false);
            returnBook_form.setVisible(false);

            issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");

            halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            halfNav_availableBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");

            currentForm_label.setText("Issue Books");
        }
    }

    public void navButtonDesign(ActionEvent event) {
        if (event.getSource() == availableBooks_btn) {
            issue_form.setVisible(false);
            availableBooks_form.setVisible(true);
            savedBook_form.setVisible(false);
            returnBook_form.setVisible(false);

            availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");

            halfNav_availableBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");

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

            halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            halfNav_availableBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");

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

            halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            halfNav_availableBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");

            currentForm_label.setText("Saved Books");

            showSaveBooks();
        } else if (event.getSource() == returnBooks_btn) {
            issue_form.setVisible(false);
            availableBooks_form.setVisible(false);
            savedBook_form.setVisible(false);
            returnBook_form.setVisible(true);

            returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");

            halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_availableBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");

            currentForm_label.setText("Return Books");
            showBookReturn();
        }
    }

    public void sideNavButtonDesign(ActionEvent event) {
        if (event.getSource() == halfNav_availableBtn) {

            availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");

            halfNav_availableBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");

        } else if (event.getSource() == halfNav_returnBtn) {
            returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");

            halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            halfNav_availableBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            showBookReturn();

        } else if (event.getSource() == halfNav_saveBtn) {
            savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");

            halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            halfNav_availableBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
        } else if (event.getSource() == halfNav_takeBtn) {

            issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");

            halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
            halfNav_availableBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4eb3a3);");
        }
    }

    public void gender() {
        List<String> combo = new ArrayList<>();
        Collections.addAll(combo, comboBox);
        ObservableList list = FXCollections.observableArrayList(combo);
        take_Gender.setItems(list);
    }

    public void findBook(ActionEvent event) {
        //clearFindData();
        String sql = "SELECT * FROM book WHERE bookTitle = '" + take_BookTitle.getText() + "'";
        connect = Database.connectDB();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            if (take_titleLabel.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Admin Message");
                alert.setHeaderText("Null");
                alert.setContentText("Please select the book");
                alert.showAndWait();
            } else {
                while (result.next()) {
                    take_titleLabel.setText(result.getString("bookTitle"));
                    take_authorLabel.setText(result.getString("author"));
                    take_genreLabel.setText(result.getString("bookType"));
                    take_dateLabel.setText(result.getString("date"));
                    getData.path = result.getString("image");
                    String url = "file:" + getData.path;
                    image = new Image(url, 134, 171, false, true);
                    take_imageView.setImage(image);
                    check = true;
                }


                if (!check) {
                    take_titleLabel.setText("The book is not available!");
                    take_authorLabel.setText(result.getString(""));
                    take_genreLabel.setText(result.getString(""));
                    take_dateLabel.setText(result.getString(""));
                    take_imageView.setImage(null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearFindData() {
        take_BookTitle.setText("");
        take_titleLabel.setText("");
        take_authorLabel.setText("");
        take_genreLabel.setText("");
        take_dateLabel.setText("");
        take_imageView.setImage(null);
    }

    public void takeBook(ActionEvent event) {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "INSERT INTO take VALUES  (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        connect = Database.connectDB();
        try {
            Alert alert;
            if (take_FirstName.getText().isEmpty() ||
                    take_LastName.getText().isEmpty() ||
                    take_Gender.getSelectionModel().isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Admin Message");
                alert.setHeaderText("Null");
                alert.setContentText("Please fill all the fields");
                alert.showAndWait();
            } else if (take_BookTitle.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Admin Message");
                alert.setHeaderText("Null");
                alert.setContentText("Please select the book");
                alert.showAndWait();
            } else {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, take_StudentNumber.getText());
                prepare.setString(2, take_FirstName.getText());
                prepare.setString(3, take_LastName.getText());
                prepare.setString(4, (String) take_Gender.getSelectionModel().getSelectedItem());
                prepare.setString(5, take_titleLabel.getText());
                prepare.setString(6, take_authorLabel.getText());
                prepare.setString(7, take_genreLabel.getText());
                prepare.setString(8, getData.path);
                prepare.setDate(9, sqlDate);

                String check = "Not Return";
                prepare.setString(10, check);

                int result = prepare.executeUpdate();

                if (result > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Admin Message");
                    alert.setHeaderText("Success");
                    alert.setContentText("Book has been taken successfully");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ObservableList<returnBook> returnBook() {

        ObservableList<returnBook> bookReturnData = FXCollections.observableArrayList();

        String check = "Not Return";

        String sql = "SELECT * FROM take WHERE checkReturn = '" + check + "'";

        connect = Database.connectDB();

        try {

            returnBook rBook;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {

                rBook = new returnBook(result.getString("bookTitle"), result.getString("author"),
                        result.getString("bookType"), result.getString("image"),
                        result.getDate("date"));
                bookReturnData.add(rBook);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookReturnData;

    }

    public void selectReturnBook() {

        returnBook rBook = return_tableView.getSelectionModel().getSelectedItem();
        int num = return_tableView.getSelectionModel().getFocusedIndex();

        if ((num - 1) < -1) {
            return;
        }

        String uri = "file:" + rBook.getImage();

        image = new Image(uri, 143, 181, false, true);
        return_imageView.setImage(image);

        getData.takeBookTitle = rBook.getTitle();
    }

    public void returnBooks() {

        String sql = "UPDATE take SET checkReturn = 'Returned' WHERE bookTitle = '" + getData.takeBookTitle + "'";

        connect = Database.connectDB();

        Alert alert;

        try {

            if (return_imageView.getImage() == null) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Admin Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the book you want to return");
                alert.showAndWait();

            } else {

                statement = connect.createStatement();
                statement.executeUpdate(sql);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Admin Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully returned!");
                alert.showAndWait();

                showBookReturn();

                return_imageView.setImage(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showBookReturn() {

        retBook = returnBook();

        col_return_BookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_return_Author.setCellValueFactory(new PropertyValueFactory<>("author"));
        col_return_bookType.setCellValueFactory(new PropertyValueFactory<>("genre"));
        col_return_dateIssue.setCellValueFactory(new PropertyValueFactory<>("date"));

        return_tableView.setItems(retBook);
    }

    public ObservableList<saveBook> saveBooksData() {
        ObservableList<saveBook> listSaveData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM save";
        connect = Database.connectDB();
        try {
            saveBook sBook;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                sBook = new saveBook(result.getString("bookTitle"), result.getString("author")
                        , result.getString("bookType"), result.getString("image"), result.getDate("date"));

                listSaveData.add(sBook);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSaveData;
    }

    public void saveBooks() {
        String sql = "INSERT INTO save VALUES (?, ?, ?, ?, ?, ?)";

        connect = Database.connectDB();

        try {
            Alert alert;
            if (availableBooks_title.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Admin Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the book you want to save");
                alert.showAndWait();
            } else {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, getData.user);
                prepare.setString(2, getData.savedTitle);
                prepare.setString(3, getData.savedAuthor);
                prepare.setString(4, getData.savedGenre);
                prepare.setString(5, getData.savedImage);
                prepare.setDate(6, (java.sql.Date) getData.savedDate);
                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Admin Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully saved!");
                alert.showAndWait();

                showSaveBooks();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showSaveBooks() {
        sBookList = saveBooksData();
        col_saveTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_saveAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        col_saveDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_saveGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));

        save_tableView.setItems(sBookList);
    }

    public void selectSaveBooks() {
        saveBook sBook = save_tableView.getSelectionModel().getSelectedItem();
        int num = save_tableView.getSelectionModel().getFocusedIndex();

        if ((num - 1) < -1) {
            return;
        }
        String uri = "file:" + sBook.getImage();
        image = new Image(uri, 117, 148, false, true);
        save_imageView.setImage(image);

        getData.savedImage = sBook.getImage();
        getData.savedTitle = sBook.getTitle();

    }

    public void unsaveBooks() {
        String sql = "DELETE FROM save WHERE bookTitle = '" + getData.savedTitle + "' and user = '" + getData.user + "'";
        connect = Database.connectDB();
        Alert alert;
        try {
            if (save_imageView.getImage() == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Admin Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the book you want to unsave");
                alert.showAndWait();
            } else {
                statement = connect.prepareStatement(sql);
                statement.executeUpdate(sql);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Admin Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully unsaved!");
                alert.showAndWait();

                showSaveBooks();
                save_imageView.setImage(null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
