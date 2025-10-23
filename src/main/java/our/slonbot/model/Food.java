package our.slonbot.model;

import java.util.TreeMap;

public record Food(
        int id,
        String title,
        String description,
        int exp
) {

    public static TreeMap<String, Food> foodMap = new TreeMap<String,Food>() {{
            put("grass", new Food(0, "Трава", "Зелененькая африканская (легальная)", 1));
            put("bread", new Food(1, "Хлеб", "Отобранный у туристов особенно вкусный", 2));
        }};

}
