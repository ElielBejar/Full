//Clase que inicializa y crea las figuras del array de FullPanel.
package FullPaint;

//Importar librerias.
import java.awt.Color;
import java.awt.Graphics;
//Fin de importación de librerias.

public class FullShape {
    
    private String shape;//Tiene la figura.
    private final String shapeDefault; //Si se oculta la figura shape cambiara de valor y shapeDefault mantiene el valor.
    private final boolean fill;//Determina si se pina el relleno o no.
    private Color color;//Tiene el color.
    private int x, y, width, height;//Tiene las coordenadas y tamaños de la figura.
    private String name;//Puede tener el nombre de la figura.
    
    //Constructor que inicializa la figura. Se le debe indicar el tipo de figura, el color, el relleno,
    //las coordenadas y el tamaño.
    public FullShape(String shape,Color color,boolean fill, int x, int y, int width, int height, String name){
        this.shape = shape;
        this.shapeDefault = shape;
        this.color = color;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.fill = fill;
        this.name = name;
    }
    //Métodos get y set para las coordenadas, tamaños, el color y nombre de la figura.
    public int getX(){
        return x;
    }   
    public void setX(int x){
        this.x = x;
    }
    
    public int getY(){
        return y;
    }   
    public void setY(int y){
        this.y = y;
    }
    
    public int getWidth(){
        return width;
    }   
    public void setWidth(int width){
        this.width = width;
    }
    
    public int getHeight(){
        return height;
    }   
    public void setHeight(int height){
        this.height = height;
    }
    
    public String getTypeOfShape(){
        return shape;
    }
    public void setTypeOfShape(String shape){
        this.shape = shape;
    }
    
    public String getByName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    
    public Color getColor(){
        return color;
    }
    public void setColor(Color color){
        this.color = color;
    }
    
    public void hide(){
        this.shape = "";
    }
    public void show(){
        this.shape = shapeDefault;
    }
    
    //Dibuja la figura.
    public void paintShape(Graphics g){
        g.setColor(color);
        if(shape == "rect"){
            if(fill == true){
            g.fillRect(x, y, width, height);
            }else{
            g.drawRect(x, y, width, height);
            }
        }
        if(shape == "oval"){
            if(fill == true){
            g.fillOval(x, y, width, height);
            }else{
            g.drawOval(x, y, width, height);
            }
        }
        if(shape == "triangle"){
            int xPoints[] = {x, x + (width/2), x + width};
            int yPoints[] = {y + height, y, y + height};
            if(fill == true){
            g.fillPolygon(xPoints, yPoints, 3);
            }else{
            g.drawPolygon(xPoints, yPoints, 3);
            }
        }
        if(shape == "line"){
            g.drawLine(x, y, width, height);
        }
       
    }
}
