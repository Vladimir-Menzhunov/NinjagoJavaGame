package ninjapack;

public class Game
{
    private static TwoGamer twoGamer; // bom
    private static OneGamer oneGamer; // flag
    private static GameState state;

    public GameState getState() {
        return state;
    }

    public Game (int cols,int rows)
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
        state = GameState.PLAYED;
    }

   public Box getBox (Coord coord)
    {
        if(oneGamer.get(coord) == Box.EMPT)
            return twoGamer.get(coord);
        else
            return oneGamer.get(coord);
    }


 /*  public void pressLeftButton(Coord coord)
    {
        //flag.setOpenedToBox(coord);

       // if(gameOver()) return;
        //openBox(coord);
        //checkWinner();

    }*/




  /*  private void checkWinner ()
    {
        if(state == GameState.PLAYED)
            if(oneGamer.getCountOfClosedBoxes() == twoGamer.getTotalBombs())
                state = GameState.WINNER;
    }

   */


/*
    private void openBox(Coord coord)
    {
        switch (oneGamer.get(coord))
        {
            case OPENED: setOpenedToClosedBoxesAroundNumber(coord); return ;
            case FLAGED: return;
            case CLOSED:
                switch (twoGamer.get(coord))
                {
                    case ZERO: openBoxesAround (coord); return;
                    case BOMB: openBombs(coord);return;
                    default: oneGamer.setOpenedToBox(coord); return;
                }
        }
    }

 */

    public void pressRightButton(Coord coord)
    {
        if(gameOver()) return;
        if(Box.ONE == oneGamer.get(coord))
            oneGamer.openGo(coord);
        if(Box.TWO == oneGamer.get(coord))
            twoGamer.openGo(coord);

    }



    private boolean gameOver()
    {
        if (state == GameState.PLAYED)
            return false;
        start();
        return true;
    }
}
