import lombok.*;
import lombok.extern.slf4j.Slf4j;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.io.File;
import java.util.*;

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
        switch (inputText) {
            case "/start":
                sendPhotoWithCaptionWithMenu(chatId, "Приветствую тебя, я Тату-Бот. Не хочешь татуировку в уникальном стиле?", "E:\\IdeaProjects\\telegramBot\\src\\main\\resources\\start.jpg");
                break;
            case "⚜Портфолио⚜":
                sendPortfolio(chatId);
                break;
            case "\uD83D\uDD6FСвободные эскизы\uD83D\uDD6F":
                sendSketches(chatId);
            case "⛓Запись на сеанс⛓":
                sendPhotoWithCaption(chatId, "Если хочешь записаться на сеанс или предложить идеи для создания своего собственного эскиза, то напиши [мне](https://t.me/Nektagram/).\nА вот мой [инстаграм](https://www.instagram.com/n3ktagram/).", "E:\\IdeaProjects\\telegramBot\\src\\main\\resources\\recording.jpg");
                break;
            default:
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
        row1.add(new KeyboardButton(Emojis.FLEUR.get() + "Портфолио" + Emojis.FLEUR.get()));
        row2.add(new KeyboardButton(Emojis.CANDLE.get() + "Свободные эскизы" + Emojis.CANDLE.get()));
        row3.add(new KeyboardButton(Emojis.CHAINS.get() + "Запись на сеанс" + Emojis.CHAINS.get()));
        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }

    /*public InlineKeyboardMarkup getInlineKeyboardMarkup() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        keyboardButtonsRow.add(new InlineKeyboardButton().setText("Конечно хочу").setCallbackData("ofCourse"));
        rowList.add(keyboardButtonsRow);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }*/

    @SneakyThrows
    public void sendPortfolio(Long chatId) {
        InputMediaPhoto photo1 = new InputMediaPhoto("https://d.radikal.ru/d34/2112/bc/45cc338796f9.jpg", "1");
        InputMediaPhoto photo2 = new InputMediaPhoto("https://b.radikal.ru/b42/2112/91/c1f679c564e0.jpg", "2");
        InputMediaPhoto photo3 = new InputMediaPhoto("https://a.radikal.ru/a23/2112/78/1b72ebb77b7e.jpg", "3");
        InputMediaPhoto photo4 = new InputMediaPhoto("https://c.radikal.ru/c43/2112/00/ebc247430f00.jpg", "4");
        InputMediaPhoto photo5 = new InputMediaPhoto("https://d.radikal.ru/d24/2112/e1/78ea6ec26d80.jpg", "5");
        InputMediaPhoto photo6 = new InputMediaPhoto("https://a.radikal.ru/a36/2112/8c/cb6d0b45a8db.jpg", "6");
        InputMediaPhoto photo7 = new InputMediaPhoto("https://c.radikal.ru/c13/2112/d0/a8c78761c553.jpg", "7");
        InputMediaPhoto photo8 = new InputMediaPhoto("https://c.radikal.ru/c29/2112/f1/d7ed4a1ea465.jpg", "8");
        InputMediaPhoto photo9 = new InputMediaPhoto("https://c.radikal.ru/c10/2112/e4/bed92510de17.jpg", "9");
        InputMediaPhoto photo10 = new InputMediaPhoto("https://c.radikal.ru/c43/2112/31/92f8e3c390f9.jpg", "10");
        List<InputMedia> list = new ArrayList<>();
        list.add(photo1);
        list.add(photo2);
        list.add(photo3);
        list.add(photo4);
        list.add(photo5);
        list.add(photo6);
        list.add(photo7);
        list.add(photo8);
        list.add(photo9);
        list.add(photo10);
        SendMediaGroup mediaGroup = new SendMediaGroup();
        mediaGroup.setChatId(chatId);
        mediaGroup.setMedia(list);
        execute(mediaGroup);
    }
    @SneakyThrows
    public void sendSketches(Long chatId) {
        InputMediaPhoto photo1 = new InputMediaPhoto("https://c.radikal.ru/c43/2112/64/dad40f44aed7.jpg", "1");
        InputMediaPhoto photo2 = new InputMediaPhoto("https://b.radikal.ru/b36/2112/81/9dd52c01b127.jpg", "2");
        InputMediaPhoto photo3 = new InputMediaPhoto("https://c.radikal.ru/c23/2112/f0/e754009a5965.jpg", "3");
        InputMediaPhoto photo4 = new InputMediaPhoto("https://d.radikal.ru/d41/2112/76/47dc9ee9428d.jpg", "4");
        InputMediaPhoto photo5 = new InputMediaPhoto("https://c.radikal.ru/c21/2112/60/578df1e398b6.jpg", "5");
        InputMediaPhoto photo6 = new InputMediaPhoto("https://d.radikal.ru/d33/2112/7e/22177d6caa27.jpg", "6");
        InputMediaPhoto photo7 = new InputMediaPhoto("https://d.radikal.ru/d17/2112/63/07e799bdf403.jpg", "7");
        InputMediaPhoto photo8 = new InputMediaPhoto("https://d.radikal.ru/d23/2112/a0/9b2f24feaa53.jpg", "8");
        InputMediaPhoto photo9 = new InputMediaPhoto("https://c.radikal.ru/c06/2112/89/e22066115480.jpg", "9");
        InputMediaPhoto photo10 = new InputMediaPhoto("https://b.radikal.ru/b18/2112/78/5ec0eee7ce5d.jpg", "10");
        List<InputMedia> list = new ArrayList<>();
        list.add(photo1);
        list.add(photo2);
        list.add(photo3);
        list.add(photo4);
        list.add(photo5);
        list.add(photo6);
        list.add(photo7);
        list.add(photo8);
        list.add(photo9);
        list.add(photo10);
        SendMediaGroup mediaGroup = new SendMediaGroup();
        mediaGroup.setChatId(chatId);
        mediaGroup.setMedia(list);
        execute(mediaGroup);
    }

    @SneakyThrows
    public void sendPhotoWithCaptionWithMenu(Long chatId, String imageCaption, String path) {
        File image = new File(path);
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setPhoto(image);
        sendPhoto.setChatId(chatId);
        sendPhoto.setCaption(imageCaption);
        sendPhoto.setReplyMarkup(getReplayKeyBoardMarkup());
        execute(sendPhoto);
    }

    @SneakyThrows
    public void sendPhotoWithCaption(Long chatId, String imageCaption, String path) {
        File image = new File(path);
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setPhoto(image);
        sendPhoto.setChatId(chatId);
        sendPhoto.setParseMode("Markdown");
        sendPhoto.setCaption(imageCaption);
        execute(sendPhoto);
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

