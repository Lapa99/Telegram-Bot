import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class PingTask {
    public static void main(String[] args) throws IOException {
        while (true) {
            String status = getStatus("https://google.com");
            System.out.println(status);
        }
    }

    public static String getStatus(String url) throws IOException {
        String result = "";
        try {
            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(100);
            connection.connect();

            int code = connection.getResponseCode();
            if (code == 200) {
                result = "On";
            }
        } catch (Exception e) {
            result = "Off";
        }
        return result;
    }
}
