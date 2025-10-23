package our.slonbot.presentation;

public class TextConstants {
    public static final String UNKNOWN_COMMAND_MESSAGE = "Я тебя непонимат :--(";
    public static final String UNKNOWN_ID_MESSAGE = "Id неверный";
    public static final String INTERNAL_ERROR_MESSAGE = "Внутренняя обшибка";
    public static final String WRONG_FOOD_NAME_MESSAGE = "Неправильное название еды";
    public static final String EAT_SUCCESS_MESSAGE_PREFIX = "Мммм ";
    public static final String EAT_SUCCESS_MESSAGE_SUFFIX = ". Обожаю!";
    public static final String WRONG_WORK_NAME_MESSAGE = "Неправильное название работы";
    public static final String WORK_START_MESSAGE = "Пора вкалывать...";
    public static final String WORK_END_MESSAGE = "А вот и ЗП капнула!";

    // ConsoleView messages
    public static final String WELCOME_MESSAGE = "Привет! Это слон бот! Напиши help чтобы узнать что ты можешь!";
    public static final String HELP_MESSAGE = """
                help - выводит помощь
                stat - показывает вашего слона
                eat - показывает доступную еду
                eat <id еды> - кормит определенной едой
                work - показывает доступную работу
                work <id работы> - отправляет слона на работу
                """;
    public static final String PLAYER_STAT_NAME = "Имя слона: %s ";
    public static final String PLAYER_STAT_AGE = "Возраст: %d";
    public static final String PLAYER_STAT_EXP = "Опыт: %d";
    public static final String PLAYER_STAT_MONEY = "Денег: %d";
    public static final String AVAILABLE_FOOD_HEADER = "Доступная еда";
    public static final String SEPARATOR = "----------------";
    public static final String FOOD_DESCRIPTION_EXP = "дает опыта";
    public static final String AVAILABLE_WORK_HEADER = "Доступная работа";
    public static final String WORK_DESCRIPTION_EXP = "дает опыта";
    public static final String WORK_DESCRIPTION_MONEY = "дает денег";
}
