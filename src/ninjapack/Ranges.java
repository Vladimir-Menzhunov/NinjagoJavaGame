package ninjapack;

import java.util.ArrayList;
import java.util.Random;

public class Ranges
{
    private static Coord size;
    private static ArrayList<Coord> allCoords;
    private static Random random = new Random();

    public static void setSize(Coord _size) // Фиксирует нужные координаты
    {
        size = _size;
        allCoords = new ArrayList<Coord>();

        allCoords.add(new Coord(1, 0));
        for (int y = 1; y < size.y - 1; y++)
            for (int x = 0; x < size.x; x++)
                allCoords.add(new Coord(x,y));
        allCoords.add(new Coord(1, 4));
    }

    public static Coord getSize() {
        return size;
    }

    public static ArrayList<Coord> getAllCoords() {

        return allCoords;
    } // возвращает все известные координаты

    static boolean inRange(Coord coord) // проверяет выход за пределы поля
    {
        return  coord.x >= 0 && coord.x < size.x && coord.y >=0 && coord.y < size.y;
    }


}
