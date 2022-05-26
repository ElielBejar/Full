package Interact;

import FullPaint.FullImage;
import FullPaint.FullPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class InteractImage extends MouseAdapter {

    private boolean focus, dragable, resizable;
    private final boolean final_dragable, final_resizable;
    private FullImage image;
    private FullPanel panel;
    private String direction;
    private int widthResize, heightResize;
    private int relativeX, relativeY;

    public InteractImage(FullPanel panel, FullImage image, boolean final_resizable, boolean final_dragable) {
        this.image = image;
        this.panel = panel;
        this.final_dragable = final_dragable;
        this.final_resizable = final_resizable;

        focus = false;
        dragable = true;
        resizable = true;
        direction = "d";
        widthResize = image.getWidth();
        heightResize = image.getHeight();
    }

    public boolean getFocus() {
        return focus;
    }

    public void setFocus(boolean focus) {
        this.focus = focus;
    }

    public boolean getDragable() {
        return dragable;
    }

    public void setDragable(boolean dragable) {
        this.dragable = dragable;
    }

    public boolean getResizable() {
        return resizable;
    }

    public void setResizable(boolean resizable) {
        this.resizable = resizable;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getX() > image.getX() && e.getX() < image.getX() + image.getWidth()
                && e.getY() > image.getY() && e.getY() < image.getY() + image.getHeight()) {
            if (focus == false) {
                focus = true;
            } else {
                focus = false;
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (focus == true && dragable == true) {
            if (e.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK) {
                image.setX(e.getX() - (image.getWidth() / 2));
                image.setY(e.getY() - (image.getHeight() / 2));
            }
        }
        if (focus == true && resizable == true) {
            if (e.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK) {
                switch (direction) {
                    case "n":
                        if (e.getY() + panel.getY() < panel.getY()) {
                            image.setY(0);
                        } else if(e.getY() < image.getY() + image.getHeight() - 10){
                            image.setY(e.getY());
                            int relativeCursor = e.getY() - relativeY;
                            image.setHeight(heightResize - relativeCursor);
                        }
                        break;
                    case "e":
                        if (e.getX() > panel.getWidth()) {
                            image.setWidth(panel.getWidth());
                        } else if(e.getX() > image.getX() + 10){
                            image.setWidth(e.getX() - image.getX());
                        }
                        break;
                    case "s":
                        if (e.getY() > panel.getHeight()) {
                            image.setHeight(panel.getHeight());
                        } else if(e.getY() > image.getY() + 10){
                            image.setHeight(e.getY() - image.getY());
                        }
                        break;
                    case "w":
                        if (e.getX() + panel.getX() < panel.getX()) {
                            image.setX(0);
                        } else if(e.getX() < image.getX() + image.getWidth() - 10){
                            image.setX(e.getX());
                            int relativeCursor = e.getX() - relativeX;
                            image.setWidth(widthResize - relativeCursor);
                        }
                        break;
                    default:
                        break;
                }

            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (focus == true && final_resizable == true) {
            if (e.getX() < image.getX() + 10
                    && e.getX() > image.getX() - 10
                    && e.getY() > image.getY()
                    && e.getY() < image.getY() + image.getHeight()) {
                panel.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
                direction = "w";
                dragable = false;
                widthResize = image.getWidth();
                relativeX = image.getX();
            } else if (e.getX() < image.getX() + image.getWidth() + 10
                    && e.getX() > image.getX() + image.getWidth() - 10
                    && e.getY() > image.getY()
                    && e.getY() < image.getY() + image.getHeight()) {
                panel.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
                direction = "e";
                dragable = false;
                widthResize = image.getWidth();
                relativeX = image.getX();
            } else if (e.getY() < image.getY() + 10
                    && e.getY() > image.getY() - 10
                    && e.getX() > image.getX()
                    && e.getX() < image.getX() + image.getWidth()) {
                panel.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
                direction = "n";
                dragable = false;
                heightResize = image.getHeight();
                relativeY = image.getY();
            } else if (e.getY() < image.getY() + image.getHeight() + 10
                    && e.getY() > image.getY() + image.getHeight() - 10
                    && e.getX() > image.getX()
                    && e.getX() < image.getX() + image.getWidth()) {
                panel.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
                direction = "s";
                dragable = false;
                heightResize = image.getHeight();
                relativeY = image.getY();
            } else {
                resizable = false;
                dragable = final_dragable;
                panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }
    }
}
