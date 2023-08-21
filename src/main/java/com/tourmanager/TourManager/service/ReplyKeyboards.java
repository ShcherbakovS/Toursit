package com.tourmanager.TourManager.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class ReplyKeyboards {

    private static List<String> directions = new ArrayList<>(List.of("Турция", "ОАЭ", "Египет", "Мальдивы",
            "Таиланд", "Венесуэла", "Греция", "Любое направление"));

    static String[] countries = {"Турция", "ОАЭ", "Египет", "Мальдивы",
            "Таиланд", "Венесуэла", "Греция", "Любое направление"};

    private static List<String> persons = new ArrayList<>(List.of("Один взрослый", "Двое взрослых", "Двое взрослых  +1 ребенок до 12 лет",
            "Двое взрослых + 2 и более детей до 12 лет", "Другое"));
    private static List<String> budget = new ArrayList<>(List.of("от 100 000 рублей", "до 200 000 рублей", "от 200 000 рублей",
            "Неважно/Любой"));

   static InlineKeyboardMarkup startCommand() {


       InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();

       List<List<InlineKeyboardButton>> rowsLines = new ArrayList<>();

       List<InlineKeyboardButton> rowInline = new ArrayList<>();

       InlineKeyboardButton firstButton = new InlineKeyboardButton();
       firstButton.setText("Турция");
       firstButton.setCallbackData("Турция");


       InlineKeyboardButton secondButton = new InlineKeyboardButton();
       secondButton.setText("ОАЭ");
       secondButton.setCallbackData("ОАЭ");

       rowInline.add(firstButton);
       rowInline.add(secondButton);

       List<InlineKeyboardButton> secondRowInline = new ArrayList<>();
       InlineKeyboardButton third = new InlineKeyboardButton();
       third.setText("Египет");
       third.setCallbackData("Египет");
       InlineKeyboardButton fourth = new InlineKeyboardButton();
       fourth.setText("Мальдивы");
       fourth.setCallbackData("Мальдивы");

       secondRowInline.add(third);
       secondRowInline.add(fourth);

       List<InlineKeyboardButton> thirdRowInline = new ArrayList<>();
       InlineKeyboardButton fifth = new InlineKeyboardButton();
       fifth.setText("Таиланд");
       fifth.setCallbackData("Таиланд");
       InlineKeyboardButton six = new InlineKeyboardButton();
       six.setText("Венесуэла");
       six.setCallbackData("Венесуэла");

       thirdRowInline.add(fifth);
       thirdRowInline.add(six);


       List<InlineKeyboardButton> fourthRowInline = new ArrayList<>();
       InlineKeyboardButton seventh = new InlineKeyboardButton();
       seventh.setText("Греция");
       seventh.setCallbackData("Греция");
       InlineKeyboardButton eighth = new InlineKeyboardButton();
       eighth.setText("Любое");
       eighth.setCallbackData("Любое направление");

       fourthRowInline.add(seventh);
       fourthRowInline.add(eighth);

       List<InlineKeyboardButton> fifthRowInline = new ArrayList<>();
       InlineKeyboardButton ninth = new InlineKeyboardButton();
       ninth.setText("Продолжить");
       ninth.setCallbackData("страна");

       fifthRowInline.add(ninth);

       rowsLines.add(rowInline);
       rowsLines.add(secondRowInline);
       rowsLines.add(thirdRowInline);
       rowsLines.add(fourthRowInline);
       rowsLines.add(fifthRowInline);

       markupInLine.setKeyboard(rowsLines);



       return markupInLine;
   }

   static InlineKeyboardMarkup choosePeople() {

       InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();

       List<List<InlineKeyboardButton>> rowsLines = new ArrayList<>();

       List<InlineKeyboardButton> firstInline = new ArrayList<>();

       InlineKeyboardButton firstButton = new InlineKeyboardButton();
       firstButton.setText("Один взрослый");
       firstButton.setCallbackData("Один");

       firstInline.add(firstButton);

       List<InlineKeyboardButton> secondRowInline = new ArrayList<>();
       InlineKeyboardButton second = new InlineKeyboardButton();
       second.setText("Двое взрослых");
       second.setCallbackData("Двое");

       secondRowInline.add(second);

       List<InlineKeyboardButton> thirdRowInline = new ArrayList<>();
       InlineKeyboardButton third = new InlineKeyboardButton();
       third.setText("Двое взрослых  + 1 ребенок до 12 лет");
       third.setCallbackData("Двое взрослых/1");

       thirdRowInline.add(third);


       List<InlineKeyboardButton> fourthRowInline = new ArrayList<>();
       InlineKeyboardButton forth = new InlineKeyboardButton();
       forth.setText("Двое взрослых + 2 и более детей до 12 лет");
       forth.setCallbackData("Двое взрослых/2");

       fourthRowInline.add(forth);

       List<InlineKeyboardButton> fifthRowInline = new ArrayList<>();
       InlineKeyboardButton fifth = new InlineKeyboardButton();
       fifth.setText("Другое");
       fifth.setCallbackData("Другое");

       fifthRowInline.add(fifth);

       rowsLines.add(firstInline);
       rowsLines.add(secondRowInline);
       rowsLines.add(thirdRowInline);
       rowsLines.add(fourthRowInline);
       rowsLines.add(fifthRowInline);

       markupInLine.setKeyboard(rowsLines);

       return markupInLine;

   }

    static InlineKeyboardMarkup budget() {

        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rowsLines = new ArrayList<>();

        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        InlineKeyboardButton firstButton = new InlineKeyboardButton();
        firstButton.setText("до 50 000 рублей");
        firstButton.setCallbackData("50 000");

        rowInline.add(firstButton);


        List<InlineKeyboardButton> secondRowInline = new ArrayList<>();
        InlineKeyboardButton second = new InlineKeyboardButton();
        second.setText("до 100 000 рублей");
        second.setCallbackData("100 000");

        secondRowInline.add(second);

        List<InlineKeyboardButton> thirdRowInline = new ArrayList<>();
        InlineKeyboardButton third = new InlineKeyboardButton();
        third.setText("до 200 000 рублей");
        third.setCallbackData("200 000");

        thirdRowInline.add(third);



        List<InlineKeyboardButton> fourthRowInline = new ArrayList<>();
        InlineKeyboardButton fifth = new InlineKeyboardButton();
        fifth.setText("Неважно/Любой");
        fifth.setCallbackData("Неважно/Любой");

        fourthRowInline.add(fifth);


        rowsLines.add(rowInline);
        rowsLines.add(secondRowInline);
        rowsLines.add(thirdRowInline);
        rowsLines.add(fourthRowInline);

        markupInLine.setKeyboard(rowsLines);

        return markupInLine;
    }
// клавиатура для администратора
     static ReplyKeyboardMarkup ownerStartKeyboard() {

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(createRow(new String[]{"Создать адресную рассылку", "Редактировать шаблоны ответов"}));
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setKeyboard(keyboardRows);
        keyboardMarkup.setResizeKeyboard(true);
        return keyboardMarkup;
    }
    public static InlineKeyboardMarkup ownerEditeMessageKeyboard() {
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(createRow(new String[]{"Создать адресную рассылку", "Редактировать шаблоны сообщений"}));
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setKeyboard(keyboardRows);
        keyboardMarkup.setResizeKeyboard(true);
        return null;
    }
    static ReplyKeyboardMarkup clientActionButtons() {
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(createRow(new String[]{"Получить индивидуальное предложение", "Настройки"}));
        keyboardRows.add(createRow(new String[]{"Позвать менеджера"}));
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setKeyboard(keyboardRows);
        keyboardMarkup.setResizeKeyboard(true);
        return keyboardMarkup;
    }
    private static  KeyboardRow createRow(String[] keyRow) {

        KeyboardRow row = new KeyboardRow();
        for (String key : keyRow) {
            row.add(key);
        }
        return row;
    }


    public static ReplyKeyboardMarkup settingsKeyboard() {
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(createRow(new String[]{"Остановить рассылку", "Сбросить настройки"}));
        keyboardRows.add(createRow(new String[]{"Назад"}));
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setKeyboard(keyboardRows);
        keyboardMarkup.setResizeKeyboard(true);
        return keyboardMarkup;
    }


}
