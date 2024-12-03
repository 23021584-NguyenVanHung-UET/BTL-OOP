package org.example.oop;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    private double x;
    private double y;
    @FXML
    private Button close;

    @FXML
    private Button loginButton;

    @FXML
    private Button minimize;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void exit() {
        System.exit(0);
    }

    @FXML
    public void minimize() {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    public void close() {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    public void login() {
        String sql = "SELECT * FROM student WHERE User = ? AND Password = ?";
        connect = Database.connectDB();
        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());
            result = prepare.executeQuery();
            if (result.next()) {
                System.out.println("Login Success");
                getData.username = username.getText();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Đăng nhập thành công");
                alert.setHeaderText(null);
                alert.setContentText("Chào mừng bạn đến với Library!");
                alert.showAndWait();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/oop/Dashboard.fxml"));
                Parent dashboardRoot = loader.load();

                Stage stage = new Stage();
                Scene dashboardScene = new Scene(dashboardRoot);

                dashboardRoot.setOnMousePressed((MouseEvent e) -> {
                    x = e.getSceneX();
                    y = e.getSceneY();
                });

                dashboardRoot.setOnMouseDragged((MouseEvent e) -> {
                    stage.setX(e.getScreenX() - x);
                    stage.setY(e.getScreenY() - y);
                });

                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(dashboardScene);

                stage.centerOnScreen();
                stage.show();

                loginButton.getScene().getWindow().hide();

            } else {
                System.out.println("Login Fail");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Đăng nhập thất bại");
                alert.setHeaderText(null);
                alert.setContentText("Sai tài khoản hoặc mật khẩu.");
                alert.showAndWait();
            }
        } catch (IOException e) {
            System.err.println("Failed to load Dashboard.fxml: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}