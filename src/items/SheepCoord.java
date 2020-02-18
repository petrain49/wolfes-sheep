package items;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Класс овцы.
 * Наследует класс координат AnimalCoord.
 * getSpecies - возвращает true.
 * pathToTop - волновой алгоритм, возвращающий длину кратчайшего пути от
 * овцы к верхней строке игрового поля.
 * userMove - принимает управление из консоли и двигает овцу в одном
 * из четырех направлений.
 */

public class SheepCoord extends AnimalCoord implements Cloneable {
    public SheepCoord(byte v, byte h) {
        super(v, h);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        SheepCoord sec = (SheepCoord) obj;
        return (this.getV() == sec.getV()
                && this.getH() == sec.getH());
    }

    @Override
    public SheepCoord clone() {
        return new SheepCoord(getV(), getH());
    }

    @Override
    public boolean getSpecies() {
        return true;
    }

    public byte pathToTop(Field field) {
        if (getV() == 0) return 0;

        byte[][] done = field.getCopyMatrix();
        List<AnimalCoord> que = new ArrayList<>();
        que.add(new AnimalCoord(getV(), getH()));
        done[getV()][getH()] = 125;

        byte count = 1;
        boolean finish = false;

        while (!finish) {
            finish = true;
            for (ListIterator<AnimalCoord> iterator = que.listIterator(); iterator.hasNext();){
                List<AnimalCoord> moves = iterator.next().moves(field);
                for (AnimalCoord m : moves) {
                    if (done[m.getV()][m.getH()] == 0) {
                        done[m.getV()][m.getH()] = count;
                        iterator.add(m);
                        finish = false;
                    }
                }
            }
            ++count;
        }

        byte min = (byte) 127;
        for (byte m: new byte[] {done[0][0], done[0][2], done[0][4], done[0][6]}) {
            if (m > 0 && m < min) min = m;
        }
        return min;
    }

    public boolean userMove(Field field, String updown, String leftright) {
        byte newV = getV();
        byte newH = getH();

        if (updown.equalsIgnoreCase("up")) {
            --newV;
            if (leftright.equalsIgnoreCase("left")) --newH;
            else if (leftright.equalsIgnoreCase("right")) ++newH;
            else return false;
        }
        else if (updown.equalsIgnoreCase("down")) {
            ++newV;
            if (leftright.equalsIgnoreCase("left")) --newH;
            else if (leftright.equalsIgnoreCase("right")) ++newH;
            else return false;
        }
        else return false;

        return move(field, newV, newH);
    }
}
