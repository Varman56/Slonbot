package our.slonbot.connection;

import our.slonbot.model.AppType;
import our.slonbot.model.Food;
import our.slonbot.model.Player;
import our.slonbot.model.PlayerByAppId;
import our.slonbot.model.Work;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateDataManager implements IDataManager {

    @Override
    public Player getPlayer(AppType appType, long playerIdValue) {
        PlayerByAppId playerKey = new PlayerByAppId(playerIdValue, appType);
        Player player = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            player = session.get(Player.class, playerKey);

            if (player == null) {
                Transaction transaction = null;
                try {
                    transaction = session.beginTransaction();
                    player = new Player(playerKey, "New Player", 0, 1, 100);
                    session.persist(player);
                    transaction.commit();
                } catch (Exception e) {
                    if (transaction != null) {
                        transaction.rollback();
                    }
                    e.printStackTrace();
                }
            }
        }
        return player;
    }

    @Override
    public Food getFood(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(SQLQueries.JPQL_SELECT_FOOD_BY_NAME, Food.class)
                    .setParameter("name", name)
                    .uniqueResult();
        }
    }

    @Override
    public Work getWork(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(SQLQueries.JPQL_SELECT_WORK_BY_NAME, Work.class)
                    .setParameter("name", name)
                    .uniqueResult();
        }
    }

    @Override
    public List<Food> getAllFood() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(SQLQueries.JPQL_SELECT_ALL_FOOD, Food.class).list();
        }
    }

    @Override
    public List<Work> getAllWork() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(SQLQueries.JPQL_SELECT_ALL_WORK, Work.class).list();
        }
    }

    @Override
    public boolean updatePlayerExp(AppType appType, long playerIdValue, long deltaExp) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            PlayerByAppId playerKey = new PlayerByAppId(playerIdValue, appType);
            Player player = session.get(Player.class, playerKey);
            if (player != null) {
                player.setExp(player.getExp() + deltaExp);
                session.merge(player);
                transaction.commit();
                return true;
            }
            if (transaction != null) {
                transaction.rollback();
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePlayerMoney(AppType appType, long playerIdValue, int deltaMoney) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            PlayerByAppId playerKey = new PlayerByAppId(playerIdValue, appType);
            Player player = session.get(Player.class, playerKey);
            if (player != null) {
                player.setMoney(player.getMoney() + deltaMoney);
                session.merge(player);
                transaction.commit();
                return true;
            }
            if (transaction != null) {
                transaction.rollback();
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePlayerExpAndMoney(AppType appType, long playerIdValue, long deltaExp, int deltaMoney) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            PlayerByAppId playerKey = new PlayerByAppId(playerIdValue, appType);
            Player player = session.get(Player.class, playerKey);
            if (player != null) {
                player.setExp(player.getExp() + deltaExp);
                player.setMoney(player.getMoney() + deltaMoney);
                session.merge(player);
                transaction.commit();
                return true;
            }
            if (transaction != null) {
                transaction.rollback();
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}
