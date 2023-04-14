package dungeonmania.entities.buildables;

public class MidnightArmour extends Buildable {
    private int extraAttack;
    private int extraDefence;

    public MidnightArmour(int durability, int attackBuff, int defenceBuff) {
        super(durability);
        this.extraAttack = attackBuff;
        this.extraDefence = defenceBuff;
    }

    public int getAttackBuff() {
        return extraAttack;
    }

    public int getDefenceBuff() {
        return extraDefence;
    }
}
