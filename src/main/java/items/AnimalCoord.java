package items;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс животного.
 * getSpecies - возвращает тип(по умолчанию false)
 * moves - список доступных ходов.
 * move - двигает объект на игровом поле.
 * place - размещает объект на игровом поле.
 */

public class AnimalCoord implements Cloneable {
    private byte v;
    private byte h;

    public AnimalCoord(byte v, byte h) {
        this.v = v;
        this.h = h;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.getV() == ((AnimalCoord) obj).getV()
                && this.getH() == ((AnimalCoord) obj).getH());
    }

    @Override
    public AnimalCoord clone() {
        return new AnimalCoord(getV(), getH());
    }

    @Override
    public String toString() {
        return (this.getSpecies() + " " + getV() + " " + getH());
    }

    public byte getV() {
        return this.v;
    }

    public void setV(byte v) {
        this.v = v;
    }

    public byte getH() {
        return this.h;
    }

    public void setH(byte h) {
        this.h = h;
    }

    public boolean getSpecies() {
        return false;
    }

    public List<AnimalCoord> moves(Field field) {
        byte posV = getV();
        byte posH = getH();
        List<AnimalCoord> neighbours = new ArrayList<>();
        for (byte v = (byte) (posV - 1); v <= posV + 1; v += 2) {
            for (byte h = (byte) (posH - 1); h <= posH + 1; h += 2) {
                if (field.isMatch(v, h)) {
                    AnimalCoord neighbour = new AnimalCoord(v, h);
                    neighbours.add(neighbour);
                }
            }
        }
        return neighbours;
    }

    public boolean move(Field field, byte v, byte h) {
        if (!field.isMatch(v, h)) return false;

        field.setCoordValue((byte) 0, getV(), getH());
        setV(v);
        setH(h);
        place(field);

        return true;
    }

    public void place(Field field) {
        byte v = getV();
        byte h = getH();

        if (!field.isMatch(v, h))
            throw new IllegalArgumentException("cant place to " + v + " " + h);

        byte num;
        if (getSpecies()) num = 125; //sheep
        else num = 120; //wolf

        if ((v % 2 == 0 && h % 2 == 0) || (v % 2 != 0 && h % 2 != 0)) {
            field.setCoordValue(num, v, h);
        }
        else throw new IllegalArgumentException();
    }
}
