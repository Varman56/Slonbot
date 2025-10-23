package our.slonbot.presentation.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import our.slonbot.model.Food;
import our.slonbot.model.Player;
import our.slonbot.model.Work;
import our.slonbot.presentation.TextConstants;

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
        assertTrue(outContent.toString().contains(TextConstants.WELCOME_MESSAGE));
    }

    @Test
    void showHelp_printsHelpMessage() {
        consoleView.showHelp();
        assertTrue(outContent.toString().contains(TextConstants.HELP_MESSAGE));
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
                %s
                %s
                %s
                %s
                """,
                String.format(TextConstants.PLAYER_STAT_NAME, player.name),
                String.format(TextConstants.PLAYER_STAT_AGE, player.level),
                String.format(TextConstants.PLAYER_STAT_EXP, player.exp),
                String.format(TextConstants.PLAYER_STAT_MONEY, player.money));
        assertTrue(outContent.toString().contains(expectedOutput));
    }

    @Test
    void showFood_printsFoodList() {
        Food food1 = new Food(1, "Трава", "Зелененькая", 10);
        Food food2 = new Food(0, "Хлеб", "Вкусный", 20);
        List<Food> foodList = Arrays.asList(food1, food2);
        consoleView.showFood(foodList);

        String output = outContent.toString();
        assertTrue(output.contains(TextConstants.AVAILABLE_FOOD_HEADER));
        assertTrue(output.contains(String.format("Трава - %d\n\nЗелененькая\n%s\n10\n%s", food1.id(), TextConstants.FOOD_DESCRIPTION_EXP, TextConstants.SEPARATOR)));
        assertTrue(output.contains(String.format("Хлеб - %d\n\nВкусный\n%s\n20\n%s", food2.id(), TextConstants.FOOD_DESCRIPTION_EXP, TextConstants.SEPARATOR)));
    }

    @Test
    void showWork_printsWorkList() {
        Work work1 = new Work(0, "Поливать цветы", "Описание1", 5, 10, 3);
        Work work2 = new Work(1, "Работать на заводе", "Описание2", 15, 30, 10);
        List<Work> workList = Arrays.asList(work1, work2);
        consoleView.showWork(workList);

        String output = outContent.toString();
        assertTrue(output.contains(TextConstants.AVAILABLE_WORK_HEADER));
        assertTrue(output.contains(String.format("Поливать цветы - %d\n\nОписание1\n%s\n5\n%s\n3\n%s", work1.id(), TextConstants.WORK_DESCRIPTION_EXP, TextConstants.WORK_DESCRIPTION_MONEY, TextConstants.SEPARATOR)));
        assertTrue(output.contains(String.format("Работать на заводе - %d\n\nОписание2\n%s\n15\n%s\n10\n%s", work2.id(), TextConstants.WORK_DESCRIPTION_EXP, TextConstants.WORK_DESCRIPTION_MONEY, TextConstants.SEPARATOR)));
    }

    @Test
    void showAdditional_printsAdditionalMessage() {
        String message = "Это дополнительное сообщение";
        consoleView.showAdditional(message);
        assertTrue(outContent.toString().contains(message));
    }
}
