package translator.domain;

public class Vocab {
    private String textFrom;
    private String textTo;
    private String gender;


    public Vocab(String split[]) {
        textFrom = split[0];
        textTo = split[1];
        if (split.length > 2)
            gender = split[2];
    }

    public String getTextFrom() {
        return textFrom;
    }

    public String getTextTo() {
        return textTo;
    }

    public String getGender() {
        return gender;
    }
}
