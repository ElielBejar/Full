//Clase principal. 
/*Esta clase se encarga de crear una ventana o no a elección.
Tanto si se crea o no una ventana se pueden crear paneles y dentro de estos
crear figuras e imagenes de manera mucho mas sencilla que con la clase jframe y jpanel*/
package FullPaint;

//Importar las clases.
import java.awt.Canvas;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
//Fin de las importaciones.
public class FullPaint extends Canvas{
    
    private final JFrame window;//Esta sera la ventana en caso de crearla.
    
    private ArrayList<FullPanel> panels;//Array que contendra todos los paneles que se creen.
    
    //Primer constructor. Inicializa la ventana y por parametros se le pasa un titulo y sus medidas.
    public FullPaint(String title, int WIDTH, int HEIGHT){
       window = new JFrame();
       window.setLayout(null);
       window.setSize(WIDTH, HEIGHT);
       window.setLocationRelativeTo(null);
       window.setResizable(false);
       window.setTitle(title);
       window.setDefaultCloseOperation(EXIT_ON_CLOSE);
       window.setVisible(true);
       //Ventana creada.
       //Inicializa el array de los paneles.
       panels = new ArrayList<>();
    }
    //Segundo constructor. No se crea la ventana pero si se inicializa el array de los paneles.
    //Estos paneles despues pueden ser agregados a otra ventana que no pertenezca a esta clase.
    public FullPaint(){
        window = null;
        panels = new ArrayList<>();
    }
    //Metodo que crea un panel. Se le deben pasar las medidas y el color.
    public void createPanel(Color color, int x, int y, int width, int height){
        FullPanel panel = new FullPanel(color,x,y,width,height);//Inicializa el panel.
        if(window != null){//Si esta inicializada la ventana se agrega a esta.
        window.add(panel);
        }
        panels.add(panel);//Este panel se agrega al array de los paneles.
        repaint();//Se dibuja el panel en pantalla.
    }
    //Métodos set y get.
    //Se puede asignar un array de paneles a este.
    public void setPanels(ArrayList<FullPanel> panels){
        this.panels = panels;
    }
    //Obtiene el array de los paneles.
    public ArrayList<FullPanel> getPanels(){
        return panels;
    }
    //Obtiene el panel pasandole cual número de panel es.
    public FullPanel getPanel(int index){
        return panels.get(index);
    }
    //Obtiene el panel por el nombre asignado a este.
    public FullPanel getPanelByName(String name){
        int iName = 0;
        for(int i = 0; i<panels.size(); i++){
            if(name == panels.get(i).getByName()){
                iName = i;
            }
        }
        return panels.get(iName);
    }
    

    
}
