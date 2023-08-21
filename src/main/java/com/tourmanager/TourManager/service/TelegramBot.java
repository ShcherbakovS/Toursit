package com.tourmanager.TourManager.service;
import com.tourmanager.TourManager.config.BotConfig;
import com.tourmanager.TourManager.entity.AddressMailing;
import com.tourmanager.TourManager.entity.Client;
import com.tourmanager.TourManager.entity.Country;
import com.tourmanager.TourManager.entity.GreetingMessages;
import com.tourmanager.TourManager.model.ClientRepository;
import com.tourmanager.TourManager.model.CountryRepository;
import com.tourmanager.TourManager.model.GreetingMessagesRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@Component
@Slf4j

public class TelegramBot extends TelegramLongPollingBot {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    GreetingMessagesRepository greetingMessagesRepository;

    final BotConfig botConfig;
    private boolean editCountryMessage = false;
    private boolean editMessageImage = false;

    private boolean messageTextFlag = false;

    Client newClient = new Client();
    Country direction = new Country();
    GreetingMessages greetingMessage = new GreetingMessages();
    AddressMailing addressMailing = new AddressMailing();


    public TelegramBot(BotConfig botConfig) {
        this.botConfig = botConfig;

        List<BotCommand> listOfCommands = new ArrayList(); // Класс BotCommand определен в телеграм библиотеке
        listOfCommands.add(new BotCommand("/start", "Начальная страница")); // конструктор принимает 2 аргумента 1 название команды 2 описание

        try {
            this.execute(new SetMyCommands(listOfCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Error setting bot`s commands list: " + e);
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
 //блок для администратора- ветка действий различается



        if(update.hasMessage() && update.getMessage().hasText() && !update.getMessage().getChatId().equals(botConfig.getOwnerId())) {

            switch (update.getMessage().getText()) {
                case "/start":
                startCommand(update.getMessage().getChatId());

                break;
                case "Настройки":
                   settingsCommand(update.getMessage().getChatId());
                   break;
                case "Сбросить настройки":
                    clearAllUserData(update.getMessage().getChatId());
                    startCommand(update.getMessage().getChatId());
                    break;
                case "Остановить рассылку":
                    clearAllUserData(update.getMessage().getChatId());
                    stopNotification(update.getMessage().getChatId());
                    break;
            }
        }
        // ответы по прикрепленным
        if(update.hasCallbackQuery()){
            String callbackData = update.getCallbackQuery().getData();
            Long chatId = update.getCallbackQuery().getMessage().getChatId();
            switch (callbackData) {
                case "страна":
                    choosePeople(chatId);
                    break;
            }
            if (MessageAnalyzer.selectCountyButton(callbackData)) {
                direction.setClientChat(chatId);
                direction.setName(callbackData);
                countryRepository.save(direction);
            }
            if (MessageAnalyzer.selectPersonsButton(callbackData) != null) {
                newClient.setNumberOfPeople(MessageAnalyzer.selectPersonsButton(callbackData));
                chooseBudget(chatId);
            }
            if(MessageAnalyzer.selectBudgetButton(callbackData) != null) {
                newClient.setBudget(MessageAnalyzer.selectBudgetButton(callbackData));
                clientRepository.save(newClient);
                endOfReg(chatId);
            }
        }
        if (update.hasMessage() && update.getMessage().getChatId().equals(botConfig.getOwnerId())) {
            switch(update.getMessage().getText()) {
                case "/start":
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setReplyMarkup(ReplyKeyboards.ownerStartKeyboard());
                    sendMessage.setText("Выберите действия");
                    sendMessage.setChatId(update.getMessage().getChatId());
                    sendMessage(sendMessage);
                    break;
                case "Создать адресную рассылку":
                    messageTextFlag = true;
                    prepareMessage("Введите текст сообщения", update.getMessage().getChatId());
                    break;
                case "Редактировать шаблоны ответов":

                    break;

            }
            if(update.getMessage().hasText()) {

                if(messageTextFlag && MessageAnalyzer.containsDirection(update.getMessage().getText())) {
                    String text = update.getMessage().getText();
                    String name = MessageAnalyzer.textAnalyzer(text);
                    endNewsletter(name, text);
                }
            }
        }

    }
    private void endNewsletter(String name, String text) {

        List<Country> countries = countryRepository.findCountriesByName(name);
        countries.stream().forEach(o -> prepareMessage(text, o.getClientChat()));
    }

    private void sendClientMessageToOwner(Long chatId, String text) {

        SendMessage sendMessage = new SendMessage("805108451", "Запрос от клиента" + text);

    }

    private void stopNotification(Long chatID) {
        SendMessage sendMessage = new SendMessage(String.valueOf(chatID), "Когда снова будете готовы получать индивидуальныe предложения, " +
                "просто нажмите старт в меню бота!");
        sendMessage(sendMessage);
    }

    private void clearAllUserData(Long chatID) {
        clientRepository.deleteAllByChatId(chatID);
        countryRepository.deleteAllByClientChat(chatID);
    }

    private void settingsCommand(Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setReplyMarkup( ReplyKeyboards.settingsKeyboard());
        sendMessage.setChatId(chatId);
        sendMessage.setText("Меню настроек");
        sendMessage(sendMessage);
    }

    private  void startCommand (Long chatID) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Рад Вас видеть \n" +
                "Выбирайте направления, а я буду подбирать для Вас выгодные предложения  \n" +
                "Для этого ответьте всего на несколько вопросов.\n" +
                "Выберите какое направление Вас интересует\n");
        sendMessage.setChatId(chatID);
        sendMessage.setReplyMarkup(ReplyKeyboards.startCommand());
        sendMessage(sendMessage);
    }
    private void choosePeople(Long chatID ) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Количество путешествующих:");
        sendMessage.setChatId(chatID);
        sendMessage.setReplyMarkup(ReplyKeyboards.choosePeople());
        sendMessage(sendMessage);
    }
    private void chooseBudget(Long chatID ) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Укажите желаемый бюджет на человека:");
        sendMessage.setChatId(chatID);
        sendMessage.setReplyMarkup(ReplyKeyboards.budget());
        sendMessage(sendMessage);
    }
    // как вариант при окончании опроса отсылать писмо в битрикс. + запись клиента в базу
    private void endOfReg(Long chatID) {
        SendMessage sendMessage = new SendMessage(String.valueOf(chatID), "Спасибо за ответы.\n" +
                "Теперь я могу присылать Вам персональные предложения для Вашего идеального отдыха \n");
        sendMessage.setReplyMarkup(ReplyKeyboards.clientActionButtons());
        sendMessage(sendMessage);
    }
    void prepareMessage(String text, Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setChatId(chatId);
        sendMessage(sendMessage);
    }
    private void getFileUrl(String fileId) throws IOException {

        URL url = new URL("https://api.telegram.org/bot"+ this.getBotToken()+"/getFile?file_id=" + fileId);
        BufferedReader in = new BufferedReader(new InputStreamReader( url.openStream()));
        String res = in.readLine();
        JSONObject result = new JSONObject(res);
        JSONObject path = result.getJSONObject("result");
        String file_path = path.getString("file_path");
        URL download = new URL("https://api.telegram.org/file/bot" + this.getBotToken() + "/" + file_path);
    }


    void sendMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    public void setEditCountryMessage(boolean editCountryMessage) {
        this.editCountryMessage = editCountryMessage;
    }

    public void setEditMessageImage(boolean editMessageImage) {
        this.editMessageImage = editMessageImage;
    }
}
