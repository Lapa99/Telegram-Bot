import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class PingTask {
    public static void main(String[] args) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    URL urlObj = new URL("https://google.com");
                    HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
                    connection.connect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        Timer timer = new Timer("timer");
        timer.schedule(timerTask, 1200, 1200000);
    }
}
