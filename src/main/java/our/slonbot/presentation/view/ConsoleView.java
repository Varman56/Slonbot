package our.slonbot.presentation.view;

import our.slonbot.model.Food;
import our.slonbot.model.Player;
import our.slonbot.model.Work;

import java.util.List;

public class ConsoleView implements IView {
    public void showWelcome() {
        System.out.println("Привет! Это слон бот! Напиши help чтобы узнать что ты можешь!");
    }
    public void showHelp() {
        System.out.println("""
                help - выводит помощь
                stat - показывает вашего слона
                eat - показывает доступную еду
                eat <id еды> - кормит определенной едой
                work - показывает доступную работу
                work <id работы> - отправляет слона на работу
                """);
    }
    public void showStat(Player player) {
        String formattedString = String.format("""
                Имя слона: %s 
                Возраст: %d
                Опыт: %d
                Денег: %d
                """, player.name, player.level, player.exp, player.money);
        System.out.println(formattedString);
    }
    public void showFood(Iterable<Food> foodList) {
        System.out.println("Доступная еда");
        System.out.println("----------------");
        for (Food food : foodList) {
            String formattedString = String.format("""
                %s - %d
                
                %s
                дает опыта
                %d
                ----------------
                """, food.title(), food.id(), food.description(), food.exp());
            System.out.println(formattedString);
        }
    }
    public void showWork(Iterable<Work> workList) {
        System.out.println("Доступная работа");
        System.out.println("----------------");
        for (Work work : workList) {
            String formattedString = String.format("""
                %s - %d
                
                %s
                дает опыта
                %d
                дает денег
                %d
                ----------------
                """, work.title(), work.id(), work.description(), work.exp(), work.money());
            System.out.println(formattedString);
        }
    }
    public void showAdditional(String s){
        System.out.println(s);
    }
}