package Interact;

//Importar paquetes.
import FullPaint.FullPanel;
import FullPaint.FullShape;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//Esta clase sirve para darle a las formas pintadas las habilidades de ser arrastradas
//y que se les pueda cambiar de tamaño.
public class InteractShape extends MouseAdapter {
    
    private boolean focus, dragable, resizable;/*Estas variables nos dicen si se 
    hizo focus en la forma, si es arrastrable y si se puede cambiar de tamaño.
    */
    private final boolean final_resizable, final_dragable;
    private final FullPanel panel;//Indicará en que panel está pintada la figura.
    private final FullShape shape;//Indicará la forma que se hará interactiva.
    private Color color;//El color de la figura.
    private String direction;//Si cambia de tamaño nos dice en que dirección la estamos agrandando.
    private int widthResize, heightResize;//Van actualizando el valor de los nuevos tamaños.
    private int relativeX, relativeY;//Van actualizando las coordenadas de la figura.

    /*Inicializa las variables y hace que por default no se arrastre ni se cambie de tamaño.*/
    public InteractShape(FullPanel panel, FullShape shape, boolean final_resizable, boolean final_dragable) {
        this.shape = shape;
        this.panel = panel;
        this.color = shape.getColor();
        this.final_dragable = final_dragable;
        this.final_resizable = final_resizable;
        
        focus = false;
        resizable = false;
        dragable = false;
        direction = "d";
        
        widthResize = shape.getWidth();
        heightResize = shape.getHeight();
        relativeX = shape.getX();
        relativeY = shape.getY();
    }
    
    //Métodos get y set.
    public boolean getFocus(){
        return focus;
    }
    public void setFocus(boolean focus){
        this.focus = focus;
    }
    
    public int getX(){
        return shape.getX();
    }
    public void setX(int x){
        shape.setX(x);
    } 
    
    public int getY(){
        return shape.getY();
    }
    public void setY(int y){
        shape.setY(y);
    }
    
    public int getWidth(){
        return shape.getWidth();
    }
    public void setWidth(int width){
        shape.setWidth(width);
    }
    
    public int getHeight(){
        return shape.getHeight();
    }
    public void setHeight(int height){
        shape.setHeight(height);
    }
    
    public Color getColor(){
        return color;
    }
    public void setColor(Color color){
        this.color = color;
    }
    
    public boolean getDragable(){
        return dragable;
    }
    public void setDragable(boolean dragable){
        this.dragable = dragable;
    }
    
    public boolean getResizable(){
        return resizable;
    }
    public void setResizable(boolean resizable){
        this.resizable = resizable;
    }
    //Fin de métodos get y set.
    //Método que al cliquear se haga un focus en la figura.
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getX() > shape.getX() && e.getX() < shape.getX() + shape.getWidth() 
            && e.getY() > shape.getY() && e.getY() < shape.getY() + shape.getHeight()) {
            if (focus == false) {
                if (shape.getColor() != Color.blue) {
                    shape.setColor(Color.blue);
                } else {
                    shape.setColor(Color.black);
                }
                focus = true;
            }else{
                focus = false;
                shape.setColor(color);
            } 
        }else{
            shape.setColor(color);
            focus = false;
        }
    }
    //Método que hace el arrastrable y el cambio de tamaño.
    @Override
    public void mouseDragged(MouseEvent e) {
        //Si se puede arrastrar...
        if (focus == true && dragable == true) {
            //Si el mouse está siendo presionado...
            if (e.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK) {
                //La figura se arrastra con el mouse.
                shape.setX(e.getX()-(shape.getWidth()/2));
                shape.setY(e.getY()-(shape.getHeight()/2));
            }
        }
        //Si se puede cambiar de tamaño...
        if (focus == true && resizable == true) {
            //Si el mouse está siendo presionado...
            if (e.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK) {
                //Depende la dirección va a cambiar de tamaño hacia esa dirección.
                switch (direction) {
                    case "n"://Norte
                        if (e.getY() + panel.getY() < panel.getY()) {
                            shape.setY(0);
                        } else if(e.getY() < shape.getY() + shape.getHeight() - 10){
                            shape.setY(e.getY());
                            int relativeCursor = e.getY() - relativeY;
                            shape.setHeight(heightResize - relativeCursor);
                        }
                        break;
                    case "e"://Este
                        if (e.getX() > panel.getWidth()) {
                            shape.setWidth(panel.getWidth());
                        } else if(e.getX() > shape.getX() + 10){
                            shape.setWidth(e.getX() - shape.getX());
                        }
                        break;
                    case "s"://Sur
                        if (e.getY() > panel.getHeight()) {
                            shape.setHeight(panel.getHeight());
                        } else if(e.getY() > shape.getY() + 10){
                            shape.setHeight(e.getY() - shape.getY());
                        }
                        break;
                    case "w"://Oeste
                        if (e.getX() + panel.getX() < panel.getX()) {
                            shape.setX(0);
                        } else if(e.getX() < shape.getX() + shape.getWidth() - 10){
                            shape.setX(e.getX());
                            int relativeCursor = e.getX() - relativeX;
                            shape.setWidth(widthResize - relativeCursor);
                        }
                        break;
                    default:
                        break;
                }

            }
        }
    }
    //Método que se encarga de cambiar el icono del cursor cuando se va a cambiar de tamaño.
    @Override
    public void mouseMoved(MouseEvent e) {
        //Si se puede cambiar de tamaño...
        if (focus == true && final_resizable == true) {
            //Si el mouse esta en las coordenadas del oeste de la figura...
            if (e.getX() < shape.getX() + 10
                    && e.getX() > shape.getX() - 10
                    && e.getY() > shape.getY()
                    && e.getY() < shape.getY() + shape.getHeight()) {
                //Cambia el cursor a un icono de oeste.
                panel.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
                direction = "w";
                dragable = false;
                resizable = final_resizable;
                //Guarda el cambio de tamaño.
                widthResize = shape.getWidth();
                //Guarda el cambio de coordenada.
                relativeX = shape.getX();
              //Si el mouse está en el este de la figura...
            } else if (e.getX() < shape.getX() + shape.getWidth() + 10
                    && e.getX() > shape.getX() + shape.getWidth() - 10
                    && e.getY() > shape.getY()
                    && e.getY() < shape.getY() + shape.getHeight()) {
                panel.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
                direction = "e";
                dragable = false;
                resizable = final_resizable;
                widthResize = shape.getWidth();
                relativeX = shape.getX();
               //Si el mouse está en el norte de la figura.
            } else if (e.getY() < shape.getY() + 10
                    && e.getY() > shape.getY() - 10
                    && e.getX() > shape.getX()
                    && e.getX() < shape.getX() + shape.getWidth()) {
                panel.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
                direction = "n";
                dragable = false;
                resizable = final_resizable;
                heightResize = shape.getHeight();
                relativeY = shape.getY();
              //Si el mouse está en el sur de la figura.
            } else if (e.getY() < shape.getY() + shape.getHeight() + 10
                    && e.getY() > shape.getY() + shape.getHeight() - 10
                    && e.getX() > shape.getX()
                    && e.getX() < shape.getX() + shape.getWidth()) {
                panel.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
                direction = "s";
                dragable = false;
                resizable = final_resizable;
                heightResize = shape.getHeight();
                relativeY = shape.getY();
              //Si no está en ninguno de esos lados que el icono sea el clásico.
            } else {
                resizable = false;
                dragable = final_dragable;
                panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }
    }

}
