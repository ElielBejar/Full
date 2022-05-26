//Clase en donde se maneja los paneles que están en el array de FullPaint.
/*Esta clase sirve para crear figuras e imagenes dentro de un panel ya creado
y esas figuras e imagenes pueden ser modificadas sus coordenadas y tamaños*/
package FullPaint;
//Se importan librerias.
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.*;
//Fin de la importación de librerias.

public class FullPanel extends JPanel{
    
    private int x, y, width, height;//Indicaran las coordenadas y tamaños del panel.
    
    private Color color;//Indicaran el color del panel.
   
    private String name;//Se le podría asignar un nombre al panel.
    
    private ArrayList<FullShape> shapes;//El array que contendra las figuras.
    private ArrayList<FullImage> images;//El array que contendra las imagenes.
    
    //Constructor que inicializa el panel asignandole coordenadas, tamaños y el color.
    public FullPanel(Color color, int x,int y,int width,int height){
        setLayout(null);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        
        setOpaque(true);
        setBounds(x,y,width,height);
        setBackground(color);
        setVisible(true);
        
       shapes = new ArrayList<>();//Se inicializa el array de las figuras.
       images = new ArrayList<>();//Se inicializa el array de las imagenes.
       
    }
 
    
    //Métodos get y set para las cordenadas, tamaños, color, arrays, figuras e imagenes
    //(tambien se les puede asignar nombre).
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
    
    public Color getColor(){
        return color;
    }
    public void setColor(Color color){
        this.color = color;
    }
    
    public ArrayList<FullShape> getShapes(){
        return shapes;
    }
    public void setShapes(ArrayList<FullShape> shapes){
        this.shapes = shapes;
    }
    
    public ArrayList<FullImage> getImages(){
        return images;
    }
    public void setImages(ArrayList<FullImage> images){
        this.images = images;
    }
    
    public String getByName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    
    public FullShape getShape(int index){
        return shapes.get(index);
    }
    
    public FullShape getShapeByName(String name){
        int iName = 0;
        for(int i = 0; i<shapes.size(); i++){
            if(name == shapes.get(i).getByName()){
                iName = i;
            }
        }
        return shapes.get(iName);
    }
    
     public FullImage getImage(int index){
        return images.get(index);
    }
     public FullImage getImageByName(String name){
        int iName = 0;
        for(int i = 0; i<images.size(); i++){
            if(name == images.get(i).getByName()){
                iName = i;
            }
        }
        return images.get(iName);
    }
    //Fin de métodos get y set.
    
   //Pinta un rectangulo, se le debe aclarar color, coordenadas, tamaños y el fill es si es relleno.
   public  void paintRect(Color color, int x, int y, int width, int height, boolean fill, String name){
       FullShape shape = new FullShape("rect",color,fill,x,y,width,height, name);
       shapes.add(shape);//Agregar el rectangulo a las figuras.
   } 
   //Pinta un ovalo o circulo.
   public void paintOval(Color color, int x, int y, int width, int height, boolean fill, String name){
       FullShape shape = new FullShape("oval",color,fill,x,y,width,height, name);
       shapes.add(shape);//Agrega el ovalo a las figuras.
   }
   //Pinta un triangulo.
   public void paintTriangle(Color color, int x, int y, int width, int height,boolean fill, String name){
       FullShape shape = new FullShape("triangle", color,fill, x, y, width, height, name);
       shapes.add(shape);//Agrega el triangulo a las figuras.
   }
   //Pinta una linea.
   public void paintLine(Color color, int x, int y, int x2, int y2,boolean fill, String name){
       FullShape shape = new FullShape("line", color,fill, x, y, x2, y2, name);
       shapes.add(shape);//Agrega la linea a las figuras.
   }
   //Pinta una imagen se le debe aclarar el link donde está la imagen.
   public void paintImage(int x, int y, int width, int height, String link, String name){
       FullImage image = new FullImage(x,y,width,height,link, name);
       images.add(image);//Agrega la imagen a las imagenes.
   }
   //Pinta todas las figuras e imagenes.
   @Override
    public void paint(Graphics g){
          super.paintComponent(g);
          super.paintComponents(g);
          /*for(FullShape objShape : shapes){
              objShape.paintShape(g);
          }*/
          /*for(FullImage objImage: images){
              objImage.paintImage(g);
          }*/
          for(int i = 0; i<shapes.size(); i++){
              shapes.get(i).paintShape(g);
          }
          
          for(int i = 0; i<images.size(); i++){
              images.get(i).paintImage(g);
          }
          repaint();
          g.dispose();
    }
}
