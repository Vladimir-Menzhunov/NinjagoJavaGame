package ninjapack;

public class TwoGamer
{
    private Matrix playerMap;

    void start ()
    {
        playerMap = new Matrix(Box.EMPT);
        placeOnePlayer();
    }

    Box get (Coord coord)
    {
        return playerMap.get(coord);
    }

    private void placeOnePlayer()
    {
        Coord twoP1 = new Coord(1,2);
        playerMap.set(twoP1 , Box.TWO);
        Coord twoP2 = new Coord(1,0);
        playerMap.set(twoP2, Box.TWO);


    }

    public void openGo(Coord coord)
    {
        coord.y--;
        if (!coord.equals(new Coord(0, 0))) //TODO
            switch (playerMap.get(coord))
            {
                //case FLAGED: setClosedToBox(coord); break;
                case EMPT: setFlagetToBox(coord); break;
            }
    }

    private void setFlagetToBox(Coord coord)
    {
        coord.y++;

    }
}
