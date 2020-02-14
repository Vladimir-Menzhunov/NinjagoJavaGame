package ninjapack;



public class TwoGamer
{
    private Matrix playerMap;
    private Game game;
    private int lastCoordOnY;
    private boolean boolIsMove;
    private int numMove;

    void start ()
    {
        playerMap = new Matrix(Box.EMPT);
        placeOnePlayer();
        game = new Game();
        lastCoordOnY = 2;
        numMove = 0;
        boolIsMove = true;
    }

    Box get (Coord coord)
    {
        return playerMap.get(coord);
    }

    private void placeOnePlayer() // начальное место для 2 игрока
    {
        Coord twoP1 = new Coord(1,2);
        playerMap.set(twoP1 , Box.TWO);
     /* Coord twoP2 = new Coord(1,0);
        playerMap.set(twoP2, Box.TWO);*/


    }

    public int getLastCoordOnY() {
        return lastCoordOnY;
    } // возврат последней координаты(значение по Y)

    public boolean getBooleanIsMove() {
        return boolIsMove;
    }

    public void setLastCoordOnY(int lastCoordOnY) {
        this.lastCoordOnY = lastCoordOnY;
    }

    public void openGo(Coord coord) // вывод полей по которым можно осуществить ход
    {

            if (!coord.equals(new Coord(1, 0))) {
                getMove(new Coord(coord.x, coord.y - 1));
                getMove(new Coord(coord.x, coord.y + 1));
                getMove(new Coord(coord.x - 1, coord.y));
                getMove(new Coord(coord.x + 1, coord.y));
                checkMove();

            } else {
                coord.x = 1;
                coord.y = 0;
                getMove(new Coord(coord.x, coord.y + 1));
                getMove(new Coord(coord.x - 1, coord.y + 1));
                getMove(new Coord(coord.x + 1, coord.y + 1));
                checkMove();
            }
       numMove = 0;
    }

    private void getMove(Coord coord) {

        if(!coord.equals(new Coord(0,0)) && !coord.equals(new Coord(2,0))
                && !coord.equals(new Coord(0,4)) && !coord.equals(new Coord(2,4))) {
            if (Ranges.inRange(coord)) {

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
    } // функция которая возвращает возможные ходы
    private void setClosedOnePlayer(Coord coord)
    {

        game.getOneGamer().getPlayerMap().set(coord, Box.ONENOGO);

    }

    private void setClosedTwoPlayer(Coord coord)
    {

        playerMap.set(coord, Box.TWONOGO);
    }

    public void setOpenGoBox(Coord coord)
    {
        numMove++;
        playerMap.set(coord, Box.OPENGO);
    }


    public void emptNo() // убирает лишнее на поле
    {
        for(Coord around : Ranges.getAllCoords())
        {
            if(Box.OPENGO == playerMap.get(around))
                playerMap.set(around, Box.EMPT);
            if(Box.TWONOGO == playerMap.get(around))
                playerMap.set(around, Box.TWO);
            if(Box.ONENOGO == game.getOneGamer().getPlayerMap().get(around))
                game.getOneGamer().getPlayerMap().set(around, Box.ONE);
        }
    }

    public void setLastMove(Coord lastMove)
    {
        playerMap.set(lastMove, Box.EMPT);
        emptNo();
    }

    public void setPlayerMove(Coord coord)
    {
        playerMap.set(coord, Box.TWO);
        emptNo();
    }


    public Matrix getPlayerMap()
    {
        return playerMap;
    }

    public void checkMove()
    {
        if(numMove == 0) boolIsMove = false;
    } // проверка на возможность хода 2 игрока
}
