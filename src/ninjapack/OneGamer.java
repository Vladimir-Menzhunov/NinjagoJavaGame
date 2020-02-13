package ninjapack;

public class OneGamer
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
        Coord oneP1 = new Coord(0,3);
        playerMap.set(oneP1 , Box.ONE);
        Coord oneP2 = new Coord(1,4);
        playerMap.set(oneP2 , Box.ONE);
        Coord oneP3 = new Coord(2,3);
        playerMap.set(oneP3 , Box.ONE);

    }

}
