package Animate;

import FullPaint.FullImage;
import FullPaint.FullShape;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

public class ShapesAnimation implements Runnable{
    
    private long pauseInterval;
    private final Thread thread;
    private ArrayList<FullShape> fullShapes;
    private int shapeSelector;
    private final boolean loop;
    
    public ShapesAnimation(ArrayList<FullShape> fullShapes, long pauseInterval, boolean loop){
        this.pauseInterval = pauseInterval;
        this.fullShapes = fullShapes;
        this.shapeSelector = 0;
        this.loop = loop;
        
        thread = new Thread(this);
        thread.start();
    }
    
    private void hideShapes(){
        for(int i = 0; i<fullShapes.size(); i++){
            fullShapes.get(i).hide();
        }
    }
    private void coordinateShapes(){
        int x = fullShapes.get(0).getX();
        int y = fullShapes.get(0).getY();
        
        for(int i = 1; i<fullShapes.size(); i++){
            fullShapes.get(i).setX(x);
            fullShapes.get(i).setY(y);
        }
    }
    @Override
    public void run() {
        boolean threadLoop = true;
        hideShapes();
        while(threadLoop){
            try {
                //coordinateShapes();
                if(shapeSelector == 0){
                    fullShapes.get(fullShapes.size()-1).hide();
                }else{
                    fullShapes.get(shapeSelector-1).hide();
                }
                fullShapes.get(shapeSelector).show();
                
                if(shapeSelector == fullShapes.size() - 1 && !loop){
                    threadLoop = false;                  
                }
                
                if(shapeSelector == fullShapes.size()-1){
                    shapeSelector = 0;
                }else{
                    shapeSelector++;
                }
                Thread.sleep(pauseInterval);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(ShapesAnimation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
