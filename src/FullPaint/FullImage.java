package FullPaint;


//Clase que inicializa y crea las imagenes del array de FullPanel.

//Importar librerias.
import java.awt.Graphics;
import javax.swing.ImageIcon;
//Fin de importación de librerias.

public class FullImage {
    
    private final ImageIcon image;//Tendrá la imagen.
    private int x, y, width, height;//Tendrá las coordenadas y tamaños.
    private final String link;//Tendrá el link donde se encuentra la imagen
                       //(tiene que estar en los recursos[src] del proyecto);
    private String name;

    //Constructor que inicializa la imagen. Se le debe decir las coordenadas, tamaños y el link.
    public FullImage(int x,int y,int width,int height, String link, String name){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.link = link;
        this.name = name;
        
        image = new ImageIcon(new ImageIcon(getClass().getResource(link)).getImage());//Se inicializa la imagen.
    }   
    //Métodos get y set para coordenadas, tamaños, el link y el nombre de la imagen.    
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
    
    public String getByName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    //Fin de métodos set y get.
    //Pinta la imagen.
    public void paintImage(Graphics g){
        g.drawImage(image.getImage(), x, y, width, height, null);
    }
}
