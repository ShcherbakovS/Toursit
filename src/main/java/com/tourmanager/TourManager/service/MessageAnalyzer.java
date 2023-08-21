package com.tourmanager.TourManager.service;
import java.util.ArrayList;
import java.util.List;

public class MessageAnalyzer {

    private static List<String> directions = new ArrayList<>(List.of("Турция", "ОАЭ", "Египет", "Мальдивы", "Таиланд", "Венесуэла", "Греция", "Любое направление"));

    private static List<String> budget = new ArrayList<>(List.of("от 100 000 рублей", "до 200 000 рублей", "от 200 000 рублей",
            "Неважно/Любой"));

    static String textAnalyzer(String text) {
        for(String s : directions){
            if(text.contains(s)) {
                return s;
            }
        }
        return null;
    }

    static boolean selectCountyButton(String text) {
        if (text.equals("страна")){
            return false;
        }
        return directions.stream().anyMatch(s -> s.equals(text));
    }
    static String selectBudgetButton(String text) {
        switch (text) {
            case "50 000":
                return "до 50 000 рублей";
            case "100 000":
                return "до 100 000 рублей";

            case "200 000":
                return "до 200 000 рублей";
            case "Неважно/Любой":
                return "Неважно/Любой";
        }
        return null;
    }
    static String selectPersonsButton(String text) {
       switch (text) {
           case "Один":
               return "Один взрослый";
           case "Двое":
               return "Двое взрослых";

           case "Двое взрослых/1":
               return "Двое взрослых  +1 ребенок до 12 лет";
           case "Двое взрослых/2":
               return "Двое взрослых + 2 и более детей до 12 лет";
           case "Другое":
               return "Другое количество отдыхающих";
       }
       return null;
    }

    public static boolean containsDirection(String text) {
        for(String s : directions){
            if(text.contains(s)) {
                return true;
            }
        }
        return false;
    }
}
