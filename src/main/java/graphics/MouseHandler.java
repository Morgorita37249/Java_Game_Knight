package graphics;

import java.awt.event.*;

public class MouseHandler implements MouseListener, MouseMotionListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse Clicked at: " + e.getX() + ", " + e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse Pressed at: " + e.getX() + ", " + e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Mouse Released at: " + e.getX() + ", " + e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Mouse Entered at: " + e.getX() + ", " + e.getY());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Mouse Exited at: " + e.getX() + ", " + e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("Mouse Dragged at: " + e.getX() + ", " + e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println("Mouse Moved at: " + e.getX() + ", " + e.getY());
    }
}