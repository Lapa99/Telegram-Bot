import org.apache.log4j.BasicConfigurator;
import org.telegram.telegrambots.ApiContextInitializer;

public class App {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        ApiContextInitializer.init();
        Bot bot = new Bot("@AuthorTattooNekta_bot", "5024373472:AAG1FumRgVcbgQ9dCUIMgGuhtTzQxltYfK8");
        bot.botConnect();
    }
}