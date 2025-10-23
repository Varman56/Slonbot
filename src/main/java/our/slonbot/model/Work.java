package our.slonbot.model;

import java.util.TreeMap;

public record Work(
        int id,
        String title,
        String description,
        long exp,
        long time,
        int money) {
    public static TreeMap<String, Work> workMap =new TreeMap<String,Work>(){{
        put("piss", new Work(0, "Поливать цветы", "Зачем-то же хобот растёт, правда?", 5, 10, 3));
        put("slave", new Work(1, "Работать на заводе", "Мой дед работал на заводе, мой батя работал на заводе", 15, 30, 10));
    }};
}
