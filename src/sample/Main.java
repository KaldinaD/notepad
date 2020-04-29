package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage primaryStages;
    private static Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tex.fxml"));
        Parent root = loader.load();
        // получить ссылку на контроллер
        controller = loader.getController();
        // передать stage в контроллер
        controller.setStage(primaryStage);
        primaryStages = primaryStage;
        primaryStage.setTitle(new Note().getName());
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static Stage getPrimaryStages() {
        return primaryStages;
    }

    public static Controller getController() {
        return controller;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
