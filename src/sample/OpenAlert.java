package sample;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class OpenAlert {
    public static void openAlert() throws IOException {
        Stage newWindow = new Stage();
        Parent root = FXMLLoader.load(OpenAlert.class.getResource("tex2.fxml"));
        newWindow.setTitle("Сохранить");
        newWindow.setScene(new Scene(root, 400, 200));
        newWindow.initModality(Modality.WINDOW_MODAL);
        Stage primaryStage = Main.getPrimaryStages();
        newWindow.initOwner(primaryStage);
        newWindow.setX(primaryStage.getX() + 200);
        newWindow.setY(primaryStage.getY() + 100);
        newWindow.setResizable(false);
        newWindow.show();
    }

    public static void bigFileAlert(File file){
        Stage newWindow = new Stage();
        CopyTask copyTask = new CopyTask(file);
        Label label = new Label("Файл слишком большой, хотите его прочитать?");
        ProgressBar progressBar = new ProgressBar(0);
        ProgressIndicator progressIndicator = new ProgressIndicator(0);

        Button startButton = new Button("Начать");
        Button cancelButton = new Button("Отмена");

        final Label statusLabel = new Label();
        statusLabel.setMinWidth(250);
        statusLabel.setTextFill(Color.BLUE);

        startButton.setOnAction(event -> {
            startButton.setDisable(true);
            progressBar.setProgress(0);
            progressIndicator.setProgress(0);
            cancelButton.setDisable(false);

            progressBar.progressProperty().unbind();
            progressBar.progressProperty().bind(copyTask.progressProperty());
            progressIndicator.progressProperty().unbind();
            progressIndicator.progressProperty().bind(copyTask.progressProperty());
            statusLabel.textProperty().unbind();
            statusLabel.textProperty().bind(copyTask.messageProperty());
            copyTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, //
                    t -> {
                        String copied = copyTask.getValue();
                        newWindow.close();
                        Controller.getNote().setName(file.getName());
                        Controller.getNote().setText(copied);
                        Controller.getNote().setTextOnSave(copied);
                        Main.getController().initialize(null, null);
                    });

            // Start the Task.
            new Thread(copyTask).start();

        });


        cancelButton.setOnAction(event -> {
            startButton.setDisable(false);
            cancelButton.setDisable(true);
            copyTask.cancel(true);
            progressBar.progressProperty().unbind();
            progressIndicator.progressProperty().unbind();
            statusLabel.textProperty().unbind();

            progressBar.setProgress(0);
            progressIndicator.setProgress(0);
            newWindow.close();
        });

        FlowPane root = new FlowPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);

        root.getChildren().addAll(label, progressBar, progressIndicator, //
                statusLabel, startButton, cancelButton);
        Scene scene = new Scene(root, 320, 120, Color.WHITE);
        newWindow.setTitle("Открыть большой файл");
        newWindow.setResizable(false);
        newWindow.setScene(scene);
        newWindow.show();
    }

}
