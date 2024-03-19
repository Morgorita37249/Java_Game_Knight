package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GraphicWindow extends Frame
{

    public GraphicWindow()
    {
        super("Graphics sample");
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension mySize = new Dimension(610, 700);
        setLocation((d.width - mySize.width) / 2,
                (d.height - mySize.height) / 2);
        setSize(mySize);

        addWindowListener(new WindowAdapter()
        {

            public void windowClosing(WindowEvent e)
            {
                GraphicWindow.this.setVisible(false);
                GraphicWindow.this.dispose();
            }
        });

    }

    public void paint(Graphics g)
    {
        g.setColor(Color.GRAY);
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++) {
                g.fillRect(40+j*200, 60+i*200, 133, 150);
            }
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Courier", Font.BOLD | Font.ITALIC, 20));
        g.drawString("GameKnight", 250, 50);
    }



}