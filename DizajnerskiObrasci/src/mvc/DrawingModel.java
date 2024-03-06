package mvc;
 
import java.util.ArrayList;
 
import geometry.Shape;
 
public class DrawingModel {
 
private ArrayList<Shape> shapes = new ArrayList<Shape>();
    
    public void add(Shape shape) {
        shapes.add(shape);
    }
    
    public void remove(Shape shape) {
        shapes.remove(shape);
    }
    
    public Shape getShape(int i) {
        return shapes.get(i);
    }
    
    public int getIndexOfShape(Shape shape) {
        return shapes.indexOf(shape);
    }
    
    public ArrayList<Shape> getShapes() {
        return shapes;
    }
 
    public void setShapes(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }
    
    public void setShape(int index, Shape shape) {
		shapes.set(index, shape);
	}
    
    
    public void addToIndex(Shape shape, int index) {
    	shapes.add(index,shape);
    }
    
    public void removeFromIndex(int i) {
    	shapes.remove(i);
    }
    
    
    
    
    
    
    
}
