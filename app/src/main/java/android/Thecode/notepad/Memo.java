package android.Thecode.notepad;

public class Memo {
    String mainText;
    int ID;

    public Memo(int ID, String mainText) {
        this.mainText = mainText;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public Memo(String mainText) {
        this.mainText = mainText;
    }
}