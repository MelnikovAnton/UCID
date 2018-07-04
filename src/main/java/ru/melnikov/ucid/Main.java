package ru.melnikov.ucid;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));

        Parent root = loader.load();

        primaryStage.setTitle("Ucid ");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        primaryStage.setResizable(false);

        MainController controller = loader.getController();

        primaryStage.setOnCloseRequest((event) -> controller.close());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
