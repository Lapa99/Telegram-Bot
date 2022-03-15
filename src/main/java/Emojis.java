import com.vdurmont.emoji.EmojiParser;

public enum Emojis {
    CANDLE(":candle:"),
    FLEUR(":fleur_de_lis:"),
    CHAINS(":chains:"),
    EYE(":eye_in_speech_bubble:"),
    SCULL(":skeleton:");


    private String value;

    public String get() {
        return EmojiParser.parseToUnicode(value);
    }

    Emojis(String value) {
        this.value = value;
    }
}
