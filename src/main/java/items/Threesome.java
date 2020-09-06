package items;

/**
 * Класс, содержащий оценку, старое положение волка и новое положение волка.
 */
public class Threesome {
    private byte evaluate;
    private WolfCoord oldWolfCoord;
    private WolfCoord newWolfCoord;

    public Threesome(byte evaluate, WolfCoord oldWolfCoord, WolfCoord newWolfCoord) {
        this.evaluate = evaluate;
        this.oldWolfCoord = oldWolfCoord;
        this.newWolfCoord = newWolfCoord;
    }

    public byte getEvaluate() {
        return this.evaluate;
    }

    public void setEvaluate(byte evaluate) {
        this.evaluate = evaluate;
    }

    public WolfCoord getOldWolfCoord() {
        return this.oldWolfCoord;
    }

    public void setOldWolfCoord(WolfCoord oldWolfCoord) {
        this.oldWolfCoord = oldWolfCoord;
    }

    public WolfCoord getNewWolfCoord() {
        return this.newWolfCoord;
    }

    public void setNewWolfCoord(WolfCoord newWolfCoord) {
        this.newWolfCoord = newWolfCoord;
    }
}
