import com.vdurmont.emoji.EmojiParser;

public enum Emojis {
    CANDLE(":candle:"),
    FLEUR(":fleur_de_lis:"),
    CHAINS(":chains:");


    private String value;

    public String get() {
        return EmojiParser.parseToUnicode(value);
    }

    Emojis(String value) {
        this.value = value;
    }
}
