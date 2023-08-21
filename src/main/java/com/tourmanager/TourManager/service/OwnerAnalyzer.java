package com.tourmanager.TourManager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

public class OwnerAnalyzer {



    static SendMessage chooseAction (Update update) {
        Long chatID = update.getMessage().getChatId();

        if (update.getMessage().hasText()) {
            switch (update.getMessage().getText()) {
                case "/start":
                    return messageReplayKeyboard(ReplyKeyboards.ownerStartKeyboard(), chatID, "Выберите действие");
                case "Редактировать шаблоны ответов":
                    return messageInlineKeyboard(ReplyKeyboards.ownerEditeMessageKeyboard(), chatID, "Пояснение");

            }
        }
        return null;
    }

    private static SendMessage messageInlineKeyboard(InlineKeyboardMarkup ownerEditeMessageKeyboard, Long chatID, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(ownerEditeMessageKeyboard);
        sendMessage.setChatId(chatID);
        return sendMessage;
    }

    static SendMessage messageReplayKeyboard (ReplyKeyboardMarkup keyboardMarkup, Long chatID, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(keyboardMarkup);
        sendMessage.setChatId(chatID);
        return sendMessage;
    }
}
