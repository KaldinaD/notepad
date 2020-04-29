package sample;

import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilesUtils {
    public static void open() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Открытие");//Заголовок диалога
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT", "*.txt");//Расширение
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(null);//Указываем текущую сцену CodeNote.mainStage

        if (file != null) {
            if (file.length() < 1000) {
                String input = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
                Controller.getNote().setName(file.getName());
                Controller.getNote().setText(input);
                Controller.getNote().setTextOnSave(input);
                Main.getController().initialize(null, null);
            } else {
                OpenAlert.bigFileAlert(file);
            }
        }

    }

    public static void saved() throws IOException {
        Main.getController().editText(null);
        String output = Controller.getNote().getText();
        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения
        fileChooser.setTitle("Сохранить");//Заголовок диалога
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT", "*.txt");//Расширение
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(null);//Указываем текущую сцену CodeNote.mainStage
        if (file != null) {
            FileOutputStream writer = new FileOutputStream(file);
            writer.write(output.getBytes());
            String name = file.getName();
            Main.getPrimaryStages().setTitle(name);
            Controller.getNote().setName(name);
            Controller.getNote().setTextOnSave(output);
            Main.getController().initialize(null, null);
        }
    }
}
