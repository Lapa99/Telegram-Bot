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
                    sendMenu(chatId);
                    break;
                case "\uD83D\uDD6FPortfolio":
                case "/portfolio":
                    sendPortfolioAndSketches(chatId, "E:\\IdeaProjects\\telegramBot\\src\\main\\resources\\v1.mp4", "More works >>>", "https://t.me/+mstMzKeuFzQ0MmNi");
                    break;
                case "⛓Free sketches":
                case "/sketches":
                    sendPortfolioAndSketches(chatId, "E:\\IdeaProjects\\telegramBot\\src\\main\\resources\\v1.mp4", "More sketches >>>", "https://t.me/sketchtattooNekta");
                    break;
                case "⛓Sign up for a session":
                case "/recording":
                    sendPhotoWithCaption(chatId);
                    break;
                case "\uD83D\uDC41\u200D\uD83D\uDDE8About me":
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
        KeyboardRow row3 = new KeyboardRow();
        row1.add(new KeyboardButton(Emojis.EYE.get() + "About me"));
        row1.add(new KeyboardButton(Emojis.CANDLE.get() + "Portfolio"));
        row2.add(new KeyboardButton(Emojis.CHAINS.get() + "Free sketches"));
        row2.add(new KeyboardButton(Emojis.FLEUR.get() + "Sign up for a session"));
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
        keyboardButtonsRow.add(new InlineKeyboardButton().setText("Instagram").setUrl("https://www.instagram.com/n3ktagram/"));
        keyboardButtonsRow.add(new InlineKeyboardButton().setText("Telegram").setUrl("https://t.me/Nektabrat"));
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
    public void sendMenu(Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Look");
        sendMessage.setReplyMarkup(getReplayKeyBoardMarkup());
        execute(sendMessage);
    }

    @SneakyThrows
    public void sendPhotoWithCaption(Long chatId) {
        File image = new File("E:\\IdeaProjects\\telegramBot\\src\\main\\resources\\recording.jpg");
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setPhoto(image);
        sendPhoto.setChatId(chatId);
        sendPhoto.setCaption("If you would like to book a session or suggest ideas for creating your own sketch, please email me.");
        sendPhoto.setReplyMarkup(getSecondInlineKeyboardMarkup());
        execute(sendPhoto);
    }

    @SneakyThrows
    private void aboutMe(Long chatId) {
        SendMessage message = new SendMessage();
        message.setText("Greetings N.\n" +
                "Tattoo artist, an artist who has been working under the nickname Nekta for the last couple of years. And this is my author's bot. Here you can immediately check out in the best quality my Art, tattoo portfolio for the last couple of years or sit down for a session.\n" +
                "In tattooing, I do not show myself to one style, but several main directions can be identified - graphics, ornamental, abstract.\n" +
                "But why do we need borders, art tattoo has reached a new level and I'm always open for cooperation with your ideas.\n" +
                "It's all the little things, more to come!");
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

