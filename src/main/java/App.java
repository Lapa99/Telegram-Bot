import org.apache.log4j.BasicConfigurator;
import org.telegram.telegrambots.ApiContextInitializer;

public class App {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        ApiContextInitializer.init();
        Bot bot = new Bot("@SHAININKCORE_bot", "5057834757:AAEvJb-2TTa8Nwom6L693VVMcE3rWFWQPmw");
        bot.botConnect();
    }
}