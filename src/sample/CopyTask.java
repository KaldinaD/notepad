package sample;

import javafx.concurrent.Task;

import java.io.*;

public class CopyTask extends Task<String> {
    private File file;

    public CopyTask(File file) {
        this.file = file;
    }

    @Override
    protected String call() {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            long count = file.length();
            int i = 0;
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                stringBuilder.append('\n');
                i += line.getBytes().length;
                copy(line);
                updateProgress(i, count);
                line = reader.readLine();
            }
        } catch (Exception ignore) {
        }
        return stringBuilder.toString();
    }

    private void copy(String line) throws InterruptedException {
        updateMessage("Файл читается");

        Thread.sleep(1);

    }
}
