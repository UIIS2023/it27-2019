package observer;

import java.util.Observable;
import java.util.Observer;

import geometry.Shape;
import mvc.DrawingFrame;
import mvc.DrawingModel;

public class buttonSwitch implements Observer {
	
	private DrawingModel model;
	private DrawingFrame frame;
	
	public buttonSwitch(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
	}
	
	
	@Override
	public void update(Observable arg0, Object arg1) {

		int count = 0;
		for(Shape shape : model.getShapes()) 
		{
			if(shape.isSelected())
			{
				count++;
			}
		}
				
		if(count == 0) {
			frame.getBtnActionEdit().setEnabled(false);
			frame.getBtnActionDelete().setEnabled(false);
			
			frame.getBtnToBack().setEnabled(false);
			frame.getBtnBringToBack().setEnabled(false);
			frame.getBtnToFront().setEnabled(false);
			frame.getBtnBringToFront().setEnabled(false);
			
		}
		else if (count > 1){
			frame.getBtnActionEdit().setEnabled(false);
			frame.getBtnActionDelete().setEnabled(true);
			frame.getBtnToBack().setEnabled(false);
			frame.getBtnBringToBack().setEnabled(false);
			frame.getBtnToFront().setEnabled(false);
			frame.getBtnBringToFront().setEnabled(false);


		}
		else {
			frame.getBtnActionEdit().setEnabled(true);
			frame.getBtnActionDelete().setEnabled(true);
			frame.getBtnToBack().setEnabled(true);
			frame.getBtnBringToBack().setEnabled(true);
			frame.getBtnToFront().setEnabled(true);
			frame.getBtnBringToFront().setEnabled(true);


		}
	}
	
	

}
