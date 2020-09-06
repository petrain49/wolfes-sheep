package items;

import java.util.List;

/**
 * Класс оценки, реализует алгоритм минимакс глубиной 3.
 */
public class Eva04 {
    public static Threesome eva04(byte depth,
                          Field field,
                          SheepCoord sheep,
                          List<WolfCoord> wolves,
                          Threesome ans) {

        if (sheep.getV() == 0 || depth == 0) return ans;

        Field testField = field.clone(); //клонированное поле
        SheepCoord copySheep = sheep.clone(); //клонированная овца
        byte evaluation = ans.getEvaluate(); //оценка
        WolfCoord oldWolf = ans.getOldWolfCoord().clone(); //старое место волка
        WolfCoord bestWolf = ans.getNewWolfCoord().clone(); //лучшее состояние волка
        List<WolfCoord> copyWolves = WolfCoord.copyWolfList(wolves); //список клонированных волков

        WolfCoord prevWolf;

        SheepCoord prevSheep;
        byte max = Byte.MIN_VALUE; //-128
        for (WolfCoord wolf: copyWolves) { //для каждого волка
            if (wolf.moves(testField).isEmpty()) { //если ходов нет - пропускаем волка
                continue;
            }

            prevWolf = wolf.clone(); //сохраняем текущее состояние волка

            for (AnimalCoord newWolfCoord: wolf.moves(testField)) { //для каждого возможного хода текущего волка
                wolf.move(testField, newWolfCoord.getV(), newWolfCoord.getH()); //двигаем волка

                if (copySheep.moves(testField).isEmpty()) { //если у овцы нет ходов, возвращаем оценку и волка
                    return new Threesome(evaluation, prevWolf, wolf);
                }

                byte min = Byte.MAX_VALUE; //127
                prevSheep = copySheep.clone(); //сохраняем текущее состояние овцы
                for (AnimalCoord newSheepCoord: copySheep.moves(testField)) { //для каждого возможного хода овцы
                    copySheep.move(testField, newSheepCoord.getV(), newSheepCoord.getH()); //двигаем овцу

                    if (depth == 1) { //оценка на глубине 1
                        byte pathLen = copySheep.pathToTop(testField);

                        if (pathLen < min) {
                            min = pathLen;
                        }
                    }
                    else { //оценка на глубине выше 1
                        Threesome recursion = eva04((byte) (depth - 1),
                                                    testField,
                                                    copySheep, copyWolves,
                                                    new Threesome(min, prevWolf, wolf));

                        if (recursion.getEvaluate() < min) {
                            min = recursion.getEvaluate();
                        }
                    }

                    copySheep.move(testField, prevSheep.getV(), prevSheep.getH()); //двигаем овцу обратно
                }

                if (min > max) {
                    max = min;
                    evaluation = max;
                    oldWolf = prevWolf.clone();
                    bestWolf = wolf.clone();
                }

                wolf.move(testField, prevWolf.getV(), prevWolf.getH()); //двигаем волка обратно
            }
        }

        return new Threesome(evaluation, oldWolf, bestWolf);
    }
}
