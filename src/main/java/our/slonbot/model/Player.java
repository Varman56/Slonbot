package our.slonbot.model;

import our.slonbot.connection.IDataWorker;

import javax.swing.text.Position;

public class Player {
    public long id;
    public AppType appType;
    public int appId;
    public String name;
    public long exp;
    public int level;
    public int money;
    public IDataWorker dataWorker;
    public Player() {

    }
    Player(long id, IDataWorker dataWorker){

    }
    Player(AppType appType, int appId, IDataWorker dataWorker) {
    }
    public boolean EatFood(String foodName) {
        if(Food.foodMap.containsKey(foodName)){
            Food food = Food.foodMap.get(foodName);
            if(dataWorker.updatePlayerExp(this.id,food.exp())){
                exp += food.exp();
                return true;
            }else{
                return false;
            }
        }else {
            return false;
        }
    }
    public boolean GoToWork(String workName) {
        if(Work.workMap.containsKey(workName)){
            Work work = Work.workMap.get(workName);
            if(dataWorker.updatePlayerExpMoney(this.id, work.exp(), work.money())){
                exp += work.exp();
                money += work.money();
                return true;
            }else{
                return false;
            }
        }else {
            return false;
        }
    }
}
