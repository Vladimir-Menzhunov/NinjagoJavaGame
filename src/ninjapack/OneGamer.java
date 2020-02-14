package ninjapack;

import java.util.ArrayList;

public class OneGamer
{

    private Matrix playerMap;
    private Game game;
    private ArrayList<Coord> listCoord;

    void start ()
    {
        playerMap = new Matrix(Box.EMPT);
        listCoord = new ArrayList<Coord>();
        placeOnePlayer();
        game = new Game();


    }

    public ArrayList<Coord> getListCoord()
    {
        return listCoord;
    }

    public void setListCoord(Coord coord)
    {
        listCoord.add(coord);
    }

    Box get (Coord coord)
    {
        return playerMap.get(coord);
    }

    private void placeOnePlayer() // начальное место первого Ninja
    {

        Coord oneP1 = new Coord(0,3);
        playerMap.set(oneP1 , Box.ONE);
        listCoord.add(new Coord(oneP1.x,oneP1.y));
        Coord oneP2 = new Coord(1,4);
        playerMap.set(oneP2 , Box.ONE);
        listCoord.add(new Coord(oneP2.x,oneP2.y));
        Coord oneP3 = new Coord(2,3);
        playerMap.set(oneP3 , Box.ONE);
        listCoord.add(new Coord(oneP3.x,oneP3.y));
       /* Coord oneP4 = new Coord(0,1);
        playerMap.set(oneP4 , Box.ONE);*/

    }

    public void showOpenGo(Coord coord) // показ ячеек в которые можно ходить
    {

        if(Ranges.inRange(new Coord(coord.x, coord.y - 1)))
        {
            if (!coord.equals(new Coord(0, 1)) && !coord.equals(new Coord(2, 1))) {
                coord.y--;
                if (!coord.equals(new Coord(0, 0)) && !coord.equals(new Coord(2, 0)))
                    switch (game.getBox(coord)) {
                        case TWO:
                            setClosedTwoPlayer(coord);
                            break;
                        case EMPT:
                            setOpenGoBox(coord);
                            break;
                        case ONE:
                            setClosedOnePlayer(coord);
                            break;
                    }
            } else {
                coord.x = 1;
                coord.y = 0;
                switch (game.getBox(coord)) {
                    case TWO:
                        setClosedTwoPlayer(coord);
                        break;
                    case EMPT:
                        setOpenGoBox(coord);
                        break;
                    case ONE:
                        setClosedOnePlayer(coord);
                        break;

                }
            }
        }

    }

    private void setClosedOnePlayer(Coord coord) // инициализирует ячейку в которую нельзя ходить
    {
        emptNo();
        playerMap.set(coord, Box.ONENOGO);

    }

    private void setClosedTwoPlayer(Coord coord) // инициализирует ячейку в которую нельзя ходить
    {
        emptNo();
        game.getTwoGamer().getPlayerMap().set(coord, Box.TWONOGO);
    }

    public void setOpenGoBox(Coord coord) // показывает ячейку в которую разрешён вход
    {
        emptNo();
        playerMap.set(coord, Box.OPENGO);
    }


    public void emptNo() //Обновляет не нужые(такие как подсветка мест куда можно ходить, а куда нет) ячейки поля
    {
        for(Coord around : Ranges.getAllCoords())
        {
            if(Box.OPENGO == playerMap.get(around))
                playerMap.set(around, Box.EMPT);
            if(Box.TWONOGO == game.getTwoGamer().getPlayerMap().get(around))
                game.getTwoGamer().getPlayerMap().set(around, Box.TWO);
            if(Box.ONENOGO == playerMap.get(around))
                playerMap.set(around, Box.ONE);
        }
    }

    public void setLastCoord(Coord lastMove) // фиксирует предыдущую координату
    {
        playerMap.set(lastMove, Box.EMPT);
        emptNo();
    }

    public void setPlayerMove(Coord coord) // фиксирует игрока по последней координате
    {
        playerMap.set(coord, Box.ONE);
        emptNo();
    }

    public Matrix getPlayerMap()
    {
        return playerMap;
    } //
}
