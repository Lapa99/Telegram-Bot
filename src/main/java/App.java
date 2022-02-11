import org.apache.log4j.BasicConfigurator;
import org.telegram.telegrambots.ApiContextInitializer;

public class App {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        ApiContextInitializer.init();
        Bot bot = new Bot("@AuthorNekta_bot", "5014035256:AAEXTOWLWGK0pqsHbNP4fk1CUagQkz6cCCk");
        bot.botConnect();
    }
}