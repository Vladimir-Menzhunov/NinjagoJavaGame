package ninjapack;

public class OneGamer
{

    private Matrix playerMap;
    private Game game = new Game();

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
        Coord oneP4 = new Coord(0,1);
        playerMap.set(oneP4 , Box.ONE);

    }

    public void openGo(Coord coord)
    {

        if(!coord.equals(new Coord(0, 1)) && !coord.equals(new Coord(2, 1)))
        {
            coord.y--;
            if (!coord.equals(new Coord(0, 0)) && !coord.equals(new Coord(2, 0)))
                switch (game.getBox(coord)) {
                    case TWO: setClosedToBox(coord); break;
                    case EMPT:
                        setOpenGoBox(coord);
                        break;
                }
        }
        else
        {
            coord.x = 1;
            coord.y = 0;
            switch (game.getBox(coord)) {
                case TWO: setClosedToBox(coord); break;
                case EMPT: setOpenGoBox(coord); break;
            }
        }

    }

    private void setClosedToBox(Coord coord)
    {
        playerMap.set(coord, Box.TWONOGO);
    }

    public void setOpenGoBox(Coord coord)
    {
        playerMap.set(coord, Box.OPENGO);
    }



}
