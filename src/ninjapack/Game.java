package ninjapack;

public class Game
{
    private TwoGamer twoGamer; // bom
    private OneGamer oneGamer; // flag
    private GameState state;

    public GameState getState() {
        return state;
    }

    public Game (int cols,int rows)
    {
        Ranges.setSize(new Coord(cols, rows));
        twoGamer = new TwoGamer();
        oneGamer = new OneGamer();
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


/*
    public void pressLeftButton(Coord coord)
    {
        //flag.setOpenedToBox(coord);

        if(gameOver()) return;
        openBox(coord);
        checkWinner();

    }


 */
/*
    private void checkWinner ()
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
/*
    public void pressRightButton(Coord coord)
    {
        if(gameOver()) return;
        oneGamer.toggleFlagedToBox(coord);
    }

 */

    private boolean gameOver()
    {
        if (state == GameState.PLAYED)
            return false;
        start();
        return true;
    }
}
