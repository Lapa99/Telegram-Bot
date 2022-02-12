import lombok.*;
import lombok.extern.slf4j.Slf4j;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.io.File;
import java.util.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Getter
@Setter
public class Bot extends TelegramLongPollingBot {
    String userName;
    String token;

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        log.debug("Receive new Update. updateID: " + update.getUpdateId());
        Long chatId = update.getMessage().getChatId();
        String inputText = update.getMessage().getText();
        if (update.hasMessage()) {
            switch (inputText) {
                case "/start":
                    sendCaptionWithMenu(chatId);
                    break;
                case "⚜Портфолио":
                case "/portfolio":
                    sendPortfolioAndSketches(chatId, "home\\user\\Desktop\\telegramBot\\v1.mp4", "Больше работ >>>", "https://t.me/+mstMzKeuFzQ0MmNi");
                    break;
                case "\uD83D\uDD6FСвободные эскизы":
                case "/sketches":
                    sendPortfolioAndSketches(chatId, "home\\user\\Desktop\\telegramBot\\v2.mp4", "Больше эскизов >>>", "https://t.me/sketchtattooNekta");
                    break;
                case "⛓Запись на сеанс":
                case "/recording":
                    sendPhotoWithCaption(chatId);
                    break;
                case "\uD83D\uDC41\u200D\uD83D\uDDE8Обо мне":
                case "/aboutme":
                    aboutMe(chatId);
                    break;
            }
        }
    }

    public ReplyKeyboardMarkup getReplayKeyBoardMarkup() {
        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        row1.add(new KeyboardButton(Emojis.FLEUR.get() + "Портфолио"));
        row1.add(new KeyboardButton(Emojis.CANDLE.get() + "Свободные эскизы"));
        row2.add(new KeyboardButton(Emojis.CHAINS.get() + "Запись на сеанс"));
        row2.add(new KeyboardButton(Emojis.EYE.get() + "Обо мне"));
        keyboard.add(row1);
        keyboard.add(row2);
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }

    public InlineKeyboardMarkup getInlineKeyboardMarkup(String text, String url) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        keyboardButtonsRow.add(new InlineKeyboardButton().setText(text).setUrl(url));
        rowList.add(keyboardButtonsRow);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup getSecondInlineKeyboardMarkup() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        keyboardButtonsRow.add(new InlineKeyboardButton().setText("Инстаграм").setUrl("https://www.instagram.com/n3ktagram/"));
        keyboardButtonsRow.add(new InlineKeyboardButton().setText("Телеграм").setUrl("https://t.me/Nektagram"));
        rowList.add(keyboardButtonsRow);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    @SneakyThrows
    public void sendPortfolioAndSketches(Long chatId, String path, String textButton, String pathButton) {
        File gif = new File(path);
        SendVideo sendVideo = new SendVideo();
        sendVideo.setVideo(gif);
        sendVideo.setChatId(chatId);
        sendVideo.setReplyMarkup(getInlineKeyboardMarkup(textButton, pathButton));
        execute(sendVideo);
    }

    @SneakyThrows
    public void sendCaptionWithMenu(Long chatId) {
        File gif = new File("home\\user\\Desktop\\telegramBot\\Uill.mp4");
        SendVideo sendVideo = new SendVideo();
        sendVideo.setVideo(gif);
        sendVideo.setChatId(chatId);
        sendVideo.setCaption("Приветствую тебя, я Тату-Бот \uD83E\uDD16 Не хочешь татуировку в уникальном стиле?");
        sendVideo.setReplyMarkup(getReplayKeyBoardMarkup());
        execute(sendVideo);
    }

    @SneakyThrows
    public void sendPhotoWithCaption(Long chatId) {
        File image = new File("home\\user\\Desktop\\telegramBot\\recording.jpg");
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setPhoto(image);
        sendPhoto.setChatId(chatId);
        sendPhoto.setCaption("Если хочешь записаться на сеанс или предложить идеи для создания своего собственного эскиза, то напиши мне");
        sendPhoto.setReplyMarkup(getSecondInlineKeyboardMarkup());
        execute(sendPhoto);
    }

    @SneakyThrows
    private void aboutMe(Long chatId) {
        SendMessage message = new SendMessage();
        message.setText("Приветствует N.\n" +
                "Тату артист, художник работающим под ником Некта последние пару лет. А это мой авторский бот. Здесь ты можешь напрямую заценить в лучшем качестве мой Арт, тату портфолио за последние пару лет или записаться на сеанс. \n" +
                "\n" +
                "В татуировке я не привязан к одному стилю, но можно определить несколько основных направлений - графика, орнаметал, абстракт.\n" +
                "Но зачем нам границы, художественная татуировка вышла на новый уровень и я всегда открыт для кооперации с вашими идеями. \n" +
                "\n" +
                "это все мелочи, дальше больше!");
        message.setChatId(chatId);
        execute(message);
    }

    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @SneakyThrows
    public void botConnect() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(this);
            log.info("TelegramAPI started. Look for messages");
        } catch (TelegramApiRequestException e) {
            log.error("Cant Connect. Pause " + 10000 / 1000 + "sec and try again. Error: " + e.getMessage());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
                return;
            }
            botConnect();
        }
    }
}

