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


    }
}
