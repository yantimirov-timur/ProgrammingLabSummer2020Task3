package model;

import view.Tetris;
import java.util.Random;

/**
 * Класс "фигура". Отвечает на описание и поведение фигур.
 */
public class Figure extends GameField {
    public Figure() {
        //добавление в граф сцены
        Tetris tetris = new Tetris();
        getChildren().addAll(tetris.getCanvas());
    }

    /**
     * Движение фигуры вниз
     */
    public void moveDown() {
        for (int i = 0; i < 4; i++) {
            shape[1][i]++;
        }
    }

    static Elements elements;
    Random randomShape = new Random();
    static int[][] shape;

    public void setShape() {
        switch (randomShape.nextInt(7)) {
            case 0:
                shape = new int[][]{{5, 6, 7, 8}, {0, 0, 0, 0}};
                elements = Elements.FigureI;
                break;
            case 1:
                shape = new int[][]{{5, 6, 5, 6}, {0, 0, 1, 1}};
                elements = Elements.FigureO;
                break;
            case 2:
                shape = new int[][]{{6, 7, 8, 8}, {1, 1, 1, 0}};
                elements = Elements.FigureL;
                break;
            case 3:
                shape = new int[][]{{3, 3, 4, 4}, {1, 2, 2, 3}};
                elements = Elements.FigureS;
                break;
            case 4:
                shape = new int[][]{{3, 4, 4, 4}, {2, 1, 2, 3}};
                elements = Elements.FigureT;
                break;
            case 5:
                shape = new int[][]{{6, 7, 8, 8}, {1, 1, 1, 2}};
                elements = Elements.FigureJ;
                break;
            case 6:
                shape = new int[][]{{4, 4, 3, 3}, {1, 2, 2, 3}};
                elements = Elements.FigureZ;
                break;
        }
    }

    /**
     * Проверка возможности движения влево
     * и само движение
     */
    public void moveLeft() {
        for (int i = 0; i < 4; i++) {
            shape[0][i]--;
        }
        if (findIntersect()) {
            for (int j = 0; j < 4; j++) {
                shape[0][j]++;
            }
        }
    }

    /**
     * Проверка возможности движения вправо
     * и само движение
     */
    public void moveRight() {
        for (int i = 0; i < 4; i++) {
            shape[0][i]++;
        }
        if (findIntersect()) {
            for (int j = 0; j < 4; j++) {
                shape[0][j]--;
            }
        }
    }

    /**
     * Поиск каких либо пересечений. С краями поля или с другой фигурой
     */
    public boolean findIntersect() {
        boolean intersect = false;
        for (int i = 0; i < 4; i++) {
            if (shape[1][i] >= 24 || shape[0][i] >= 16 || shape[1][i] < 0 || shape[0][i] < 0 ||
                    getGameField()[shape[1][i]][shape[0][i]] != Elements.EmptyCell) {
                intersect = true;
            }
        }
        return intersect;
    }

    public void turningShape() {
        int maxX = 0;
        int maxY = 0;

        for (int i = 0; i < 4; i++) {
            if (shape[0][i] > maxY) {
                maxY = shape[0][i];
            }
            if (shape[1][i] > maxX) {
                maxX = shape[1][i];
            }
        }
        for (int i = 0; i < 4; i++) {
            int temp = shape[0][i];
            shape[0][i] = maxY - (maxX - shape[1][i]);//- 1;
            shape[1][i] = maxX - ((maxY - temp)+ 1) ;
        }
    }

    /**
     * ^обьединить
     *
     * @return
     */
    public boolean stop() {
        if (findIntersect()) {
            if (elements == Elements.FigureI) {
                for (int i = 0; i < 4; i++) {
                    getGameField()[shape[1][i] - 1][shape[0][i]] = Elements.FigureI;
                }
            } else if (elements == Elements.FigureL) {
                for (int i = 0; i < 4; i++) {
                    getGameField()[shape[1][i] - 1][shape[0][i]] = Elements.FigureL;
                }
            } else if (elements == Elements.FigureO) {
                for (int i = 0; i < 4; i++) {
                    getGameField()[shape[1][i] - 1][shape[0][i]] = Elements.FigureO;
                }
            } else if (elements == Elements.FigureS) {
                for (int i = 0; i < 4; i++) {
                    getGameField()[shape[1][i] - 1][shape[0][i]] = Elements.FigureS;
                }
            } else if (elements == Elements.FigureT) {
                for (int i = 0; i < 4; i++) {
                    getGameField()[shape[1][i] - 1][shape[0][i]] = Elements.FigureT;
                }
            } else if (elements == Elements.FigureJ) {
                for (int i = 0; i < 4; i++) {
                    getGameField()[shape[1][i] - 1][shape[0][i]] = Elements.FigureJ;
                }
            } else if (elements == Elements.FigureZ) {
                for (int i = 0; i < 4; i++) {
                    getGameField()[shape[1][i] - 1][shape[0][i]] = Elements.FigureZ;
                }
            }

        }
        return findIntersect();
    }
}
