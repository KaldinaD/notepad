package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ResourceBundle;

public class Controller implements Initializable {
    private static Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem create;

    @FXML
    public MenuItem open;

    @FXML
    public TextArea l;

    private static final Note note = new Note();

    public static Note getNote() {
        return note;
    }

    @FXML
    private void saved(ActionEvent event) throws IOException {
        FilesUtils.saved();
    }

    @FXML
    void editText(InputEvent event) {
        note.setText(l.getText());
    }


    @FXML
    void created(ActionEvent event) throws IOException {
        if (note.isCanSaveText()) {
            note.setDeistvie(DoAfterAlert.CREATE);
            OpenAlert.openAlert();

        } else {
            note.clear();
            l.setText(note.getText());
            Main.getPrimaryStages().setTitle(note.getName());
        }

    }


    @FXML
    private void opened(ActionEvent event) throws IOException {
        if (note.isCanSaveText()) {
            note.setDeistvie(DoAfterAlert.OPEN);
            OpenAlert.openAlert();
        } else {
            FilesUtils.open();
        }


    }


    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        if (note.getText() != null) {
            l.setText(note.getText());
        }
        if (stage != null && note.getName() != null && !note.getName().trim().isEmpty()) {
            stage.setTitle(note.getName());
        }
    }
}