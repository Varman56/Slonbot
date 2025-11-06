package our.slonbot.connection;

import our.slonbot.model.Food;
import our.slonbot.model.Work;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DatabaseInitializer {

    public static void initialize() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();

                if (session.createQuery(SQLQueries.JPQL_COUNT_FOOD, Long.class).uniqueResult() == 0) {
                    Food grass = new Food("Трава", "Зелененькая африканская (легальная)", 1);
                    Food bread = new Food("Хлеб", "Отобранный у туристов особенно вкусный", 2);
                    session.persist(grass);
                    session.persist(bread);
                }

                if (session.createQuery(SQLQueries.JPQL_COUNT_WORK, Long.class).uniqueResult() == 0) {
                    Work waterFlowers = new Work("Поливание", "Зачем-то же хобот растёт, правда?", 5, 3, 10);
                    Work factoryWork = new Work("Завод", "Мой дед работал на заводе, мой батя работал на заводе", 15, 10, 30);
                    session.persist(waterFlowers);
                    session.persist(factoryWork);
                }

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }
}
