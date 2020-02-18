package items;

/**
 * Класс, содержащий оценку, старое положение волка и новое положение волка.
 */
public class Threesome {
    private byte evaluate;
    private WolfCoord oldWolf;
    private WolfCoord newWolf;

    public Threesome(byte evaluate, WolfCoord oldWolf, WolfCoord newWolf) {
        this.evaluate = evaluate;
        this.oldWolf = oldWolf;
        this.newWolf = newWolf;
    }

    public byte getEvaluate() {
        return this.evaluate;
    }

    public void setEvaluate(byte evaluate) {
        this.evaluate = evaluate;
    }

    public WolfCoord getOldWolfCoord() {
        return this.oldWolf;
    }

    public void setOldWolf(WolfCoord oldWolf) {
        this.oldWolf = oldWolf;
    }

    public WolfCoord getNewWolfCoord() {
        return this.newWolf;
    }

    public void setNewWolf(WolfCoord newWolf) {
        this.newWolf = newWolf;
    }
}
