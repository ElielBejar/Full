package Interact;

import FullPaint.FullImage;
import FullPaint.FullPanel;
import FullPaint.FullShape;
import java.util.ArrayList;

public class Interact{
    
    private ArrayList<InteractShape> interactShapes;
    private ArrayList<InteractImage> interactImages;
    
    public Interact(){
        interactShapes = new ArrayList<>();
        interactImages = new ArrayList<>();
    }
    public InteractShape getInteractShape(int index){
        return interactShapes.get(index);
    }
    public void setInteractShape(FullPanel panel,FullShape shape, boolean dragable, boolean resizable){
        InteractShape interactShape = new InteractShape(panel, shape, dragable, resizable);
        interactShapes.add(interactShape);
        panel.addMouseMotionListener(interactShape);
        panel.addMouseListener(interactShape);
    }
    public InteractImage getInteractImage(int index){
        return interactImages.get(index);
    }
    public void setInteractImage(FullPanel panel, FullImage image, boolean resizable, boolean dragable){
        InteractImage interactImage = new InteractImage(panel, image, resizable, dragable);
        interactImages.add(interactImage);
        panel.addMouseMotionListener(interactImage);
        panel.addMouseListener(interactImage);
    }
    
    public ArrayList<InteractShape> getInteractShapes(){
        return interactShapes;
    }
    public void setInteractShapes(ArrayList<InteractShape> interactShapes){
        this.interactShapes = interactShapes;
    }
    
    public ArrayList<InteractImage> getInteractImages(){
        return interactImages;
    }
    public void setInteractImages(ArrayList<InteractImage> interactImages){
        this.interactImages = interactImages;
    }
}
