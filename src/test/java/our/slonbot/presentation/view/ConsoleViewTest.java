package our.slonbot.presentation.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import our.slonbot.model.Food;
import our.slonbot.model.Player;
import our.slonbot.model.Work;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ConsoleViewTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private ConsoleView consoleView;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        consoleView = new ConsoleView();
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void showWelcome_printsWelcomeMessage() {
        consoleView.showWelcome();
        assertTrue(outContent.toString().contains("Привет! Это слон бот! Напиши help чтобы узнать что ты можешь!"));
    }

    @Test
    void showHelp_printsHelpMessage() {
        consoleView.showHelp();
        String expectedOutput = """
                help - выводит помощь
                stat - показывает вашего слона
                eat - показывает доступную еду
                eat <id еды> - кормит определенной едой
                work - показывает доступную работу
                work <id работы> - отправляет слона на работу
                """;
        assertTrue(outContent.toString().contains(expectedOutput));
    }

    @Test
    void showStat_printsPlayerStats() {
        Player player = new Player();
        player.name = "TestPlayer";
        player.level = 5;
        player.exp = 100;
        player.money = 200;
        consoleView.showStat(player);
        String expectedOutput = String.format("""
                Имя слона: %s 
                Возраст: %d
                Опыт: %d
                Денег: %d
                """, player.name, player.level, player.exp, player.money);
        assertTrue(outContent.toString().contains(expectedOutput));
    }

    @Test
    void showFood_printsFoodList() {
        Food food1 = new Food(1, "Трава", "Зелененькая", 10);
        Food food2 = new Food(0, "Хлеб", "Вкусный", 20);
        List<Food> foodList = Arrays.asList(food1, food2);
        consoleView.showFood(foodList);

        String output = outContent.toString();
        assertTrue(output.contains("Доступная еда"));
        assertTrue(output.contains("Трава - 1\n\nЗелененькая\nдает опыта\n10"));
        assertTrue(output.contains("Хлеб - 0\n\nВкусный\nдает опыта\n20"));
    }

    @Test
    void showWork_printsWorkList() {
        Work work1 = new Work(0, "Поливать цветы", "Описание1", 5, 10, 3);
        Work work2 = new Work(1, "Работать на заводе", "Описание2", 15, 30, 10);
        List<Work> workList = Arrays.asList(work1, work2);
        consoleView.showWork(workList);

        String output = outContent.toString();
        assertTrue(output.contains("Доступная работа"));
        assertTrue(output.contains("Поливать цветы - 0\n\nОписание1\nдает опыта\n5\nдает денег\n3"));
        assertTrue(output.contains("Работать на заводе - 1\n\nОписание2\nдает опыта\n15\nдает денег\n10"));
    }

    @Test
    void showAdditional_printsAdditionalMessage() {
        String message = "Это дополнительное сообщение";
        consoleView.showAdditional(message);
        assertTrue(outContent.toString().contains(message));
    }
}
