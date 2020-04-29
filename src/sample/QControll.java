package sample;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class QControll {

    private static Stage stages;


    public void setStage(Stage stage) {
        this.stages = stage;
    }

    @FXML
    private ResourceBundle resources;


    @FXML
    public Button save;

    @FXML
    private URL location;

    private Note note = Controller.getNote();

    @FXML
    void close(ActionEvent event) throws IOException {
        Stage stage = (Stage) save.getScene().getWindow();
        stage.close();
    }

    @FXML
    void notsaved(ActionEvent event) throws IOException {
        doDeistvie();
    }

    @FXML
    void saved(ActionEvent event) throws IOException {

        FilesUtils.saved();
        doDeistvie();
    }


    void doDeistvie() throws IOException {
        if (note.getDeistvie() != null) {
            if (note.getDeistvie().equals(DoAfterAlert.CREATE)) {
                Stage stage = (Stage) save.getScene().getWindow();
                stage.close();

                Main.getPrimaryStages().setTitle(note.getName());
                note.clear();
                Main.getController().initialize(null,null);
            }
           /* if(note.getDeistvie().equals(DoAfterAlert.CLOSE)){
                Stage stage = (Stage) save.getScene().getWindow();
                stage.close();
            }*/
            if (note.getDeistvie().equals(DoAfterAlert.OPEN)) {
                Stage stage = (Stage) save.getScene().getWindow();
                stage.close();
                FilesUtils.open();
            }
        }
    }

    @FXML
    void initialize() {
       // Main.getPrimaryStages();
    }
}
