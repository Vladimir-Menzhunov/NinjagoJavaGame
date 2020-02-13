import ninjapack.Coord;
import ninjapack.Game;
import ninjapack.Ranges;
import ninjapack.Box;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Ninjago extends JFrame
{
    private JPanel panel;
    private JLabel label;
    private final int COLS = 3;
    private final int ROWS = 5;
    private final int IMAGE_SIZE = 50;
    private Game game;



    public static void main(String[] args)
    {
        new Ninjago().setVisible(true);
    }

    private Ninjago()
    {
        game = new Game(COLS,ROWS);
        game.start();
        Ranges.setSize(new Coord(COLS, ROWS));
        setImages();
        initFrame();
        initJPanel();

    }

    private void initFrame()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("My MEGA game Ninjago");
        setResizable(false);
        setSize(new Dimension(COLS * (IMAGE_SIZE + 1),ROWS * (IMAGE_SIZE + 6)));
        setVisible(true);
        setIconImage(getImage("ninja"));
        //pack();//изменение размера для всего
        setLocationRelativeTo(null); // по центру окошко
    }

    private void initJPanel()
    {
        panel = new JPanel()
        {
            @Override
            protected void paintComponent (Graphics g)
            {
                super.paintComponent(g);
                for(Coord coord : Ranges.getAllCoords())
                {
                    g.drawImage((Image) game.getBox(coord).image, coord.x * IMAGE_SIZE, coord.y * IMAGE_SIZE, this);

                }
            }
        };



       /* panel.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coord coord = new Coord (x, y);
                if(e.getButton() == MouseEvent.BUTTON1) // нажата левая кнопка мыши проверяем.
                    game.pressLeftButton(coord);
                if (e.getButton() == MouseEvent.BUTTON3)
                    game.pressRightButton(coord);
                if (e.getButton() == MouseEvent.BUTTON2)
                    game.start();

                label.setText (getMessage());
                panel.repaint();
            }

        });

        */

        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x * IMAGE_SIZE,
                Ranges.getSize().y * IMAGE_SIZE));
        add(panel);
    }

    private Image getImage(String name)
    {
        String filename = "img/" + name + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }

    private void setImages()
    {
        for(Box box :  Box.values()) box.image = getImage(box.name().toLowerCase());
    }

}
