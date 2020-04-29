package sample;

public class Note {
    private String name;
    private String text;
    private String textOnSave;
    private DoAfterAlert deistvie;

    public Note() {
        clear();
    }

    public boolean isCanSaveText() {
        return !text.equals(textOnSave);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTextOnSave(String textOnSave) {
        this.textOnSave = textOnSave;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDeistvie(DoAfterAlert deistvie) {
        this.deistvie = deistvie;
    }

    public void clear() {
        this.name = "Новый файл";
        this.text = "";
        this.textOnSave = "";
        deistvie = null;
    }

    public DoAfterAlert getDeistvie() {
        return deistvie;
    }
}
