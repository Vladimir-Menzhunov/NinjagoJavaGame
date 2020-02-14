package ninjapack;

public class Game
{

    private static TwoGamer twoGamer;
    private static OneGamer oneGamer;
    private static GameState state;
    private static Coord lastMove;

    public GameState getState() {
        return state;
    }

    public Game (int cols,int rows) // конструктор с параметрами для запуска проццессов игры
    {
        Ranges.setSize(new Coord(cols, rows));
        oneGamer = new OneGamer();
        twoGamer = new TwoGamer();


    }

    public Game()
    {

    }

    public void start()
    {
        twoGamer.start();
        oneGamer.start();

        state = GameState.PLAYEDONE;
    }

   public Box getBox (Coord coord) // получаем Box c одного из полей
    {
        if(oneGamer.get(coord) == Box.EMPT)
            return twoGamer.get(coord);
        else
            return oneGamer.get(coord);
    }


     public void pressLeftButton(Coord coord) // отслеживание нажатия левой кнопки мыши с последующей проверкой на состояние статуса игры
    {

        if(gameOver()) return;
        switch (getBox(coord))
        {
            case OPENGO: {
                makeMove(coord);
                if (state == GameState.PLAYEDONE) {
                    state = GameState.PLAYEDTWO;
                } else if (state == GameState.PLAYEDTWO) {
                    state = GameState.PLAYEDONE;
                }
                break;
            }


            default: {
                twoGamer.emptNo();
                oneGamer.emptNo();

                break;
            }

        }
        checkWinner();
    }

    private void makeMove(Coord coord)
    {
        if(getBox(lastMove) == Box.ONE)
        {
            oneGamer.setLastCoord(lastMove);
            oneGamer.setPlayerMove(coord);
            oneGamer.setListCoord(coord);

        }

        if(getBox(lastMove) == Box.TWO)
        {
            twoGamer.setLastMove(lastMove);
            twoGamer.setPlayerMove(coord);
            twoGamer.setLastCoordOnY(coord.y);
        }

    }


    private void checkWinner ()
    {
        if(state == GameState.PLAYEDONE || state == GameState.PLAYEDTWO)
        {
            if(!twoGamer.getBooleanIsMove()) {
                state = GameState.WINNERONE;
                return;
            }
            int max = twoGamer.getLastCoordOnY();
            for(Coord coord : Ranges.getAllCoords())
            {
                if(oneGamer.get(coord) == Box.ONE)
                {
                    if(max < coord.y) max = coord.y;
                    else if(max == coord.y) max = coord.y + 1;
                }
            }
            if(max == twoGamer.getLastCoordOnY()) state = GameState.WINNERTWO;
        }
    }




    public void pressRightButton(Coord coord) // отслеживание нажатия правой кнопки мыши учитывая 3 условия(в клетке стоит 1, 2 или она пустая)
    {
        if(gameOver()) return;
        if(Box.ONE == oneGamer.get(coord) && state == GameState.PLAYEDONE)
        {
            lastMove = new Coord(coord.x, coord.y);
            oneGamer.showOpenGo(coord);

        }
        if(Box.TWO == twoGamer.get(coord) && state == GameState.PLAYEDTWO)
        {
            lastMove = new Coord(coord.x, coord.y);
            twoGamer.openGo(coord);

        }

        if(Box.EMPT == oneGamer.get(coord) && Box.EMPT == twoGamer.get(coord))
        {
            twoGamer.emptNo();
            oneGamer.emptNo();
        }

    }



    private boolean gameOver() // отслеживани конца игры
    {
        if (state == GameState.PLAYEDONE || state == GameState.PLAYEDTWO)
            return false;

        start();
        return true;
    }

    public OneGamer getOneGamer()
    {
        return oneGamer;
    }

    public TwoGamer getTwoGamer()
    {
        return twoGamer;
    }
}
