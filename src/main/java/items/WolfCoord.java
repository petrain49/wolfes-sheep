package items;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс волка.
 * Наследует класс координат AnimalCoord.
 * Переопределяет метод moves из класс AnimalCoord -
 * волк может ходить только в двух направлениях(вниз-влево, вниз-вправо).
 * copyWolfList - принимает список волков, возвращает список клонированных волков.
 */

public class WolfCoord extends AnimalCoord implements Cloneable {
    public WolfCoord(byte v, byte h) {
        super(v, h);
    }

    @Override
    public boolean equals(Object obj) {
        return (this.getV() == ((WolfCoord) obj).getV()
                && this.getH() == ((WolfCoord) obj).getH());
    }

    @Override
    public WolfCoord clone() {
        return new WolfCoord(getV(), getH());
    }

    @Override
    public List<AnimalCoord> moves(Field field) {
        byte posV = (byte) (getV() + 1);
        byte posH = getH();
        List<AnimalCoord> moves = new ArrayList<>();
        for (byte h = (byte) (posH - 1); h <= posH + 1; h += 2) {

            if (field.isMatch(posV, h)) {

                AnimalCoord neighbour = new AnimalCoord(posV, h);
                moves.add(neighbour);
            }
        }
        return moves;
    }

    public static List<WolfCoord> copyWolfList(List<WolfCoord> wolves) {
        List<WolfCoord> ans = new ArrayList<>();
        for (WolfCoord w: wolves) {
            WolfCoord copyCoord = w.clone();
            ans.add(copyCoord);
        }
        return ans;
    }
}
