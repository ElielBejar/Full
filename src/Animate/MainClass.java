
package Animate;

import FullPaint.FullPaint;
import FullPaint.FullPanel;
import FullPaint.FullShape;
import Interact.Interact;
import java.awt.Color;
import java.util.ArrayList;

public class MainClass {
    
    public static void main(String args[]){
        
        FullPaint window = new FullPaint("Animate", 500, 500);
        window.createPanel(Color.white, 0, 0, 500, 500);
        
        FullPanel panel = window.getPanel(0);
        
        Interact interact = new Interact();
        
        panel.paintOval(Color.GREEN, 0, 0, 100, 100, true, "shape3");
        panel.paintOval(Color.GREEN, 10, 10, 80, 80, true, "shape2");
        panel.paintOval(Color.GREEN, 20, 20, 60, 60, true, "shape1");
        
        FullShape shape = panel.getShapeByName("shape1");
        FullShape shape2 = panel.getShapeByName("shape2");
        FullShape shape3 = panel.getShapeByName("shape3");
        
        interact.setInteractShape(panel, shape, true, true);
        
        ArrayList<FullShape> animateShapes = new ArrayList<>();
        animateShapes.add(shape);
        animateShapes.add(shape2);
        animateShapes.add(shape3);
        
        ShapesAnimation animation = new ShapesAnimation(animateShapes, 70, true);
    }
}
