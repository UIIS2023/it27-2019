package command;

import javax.swing.JOptionPane;

import geometry.Shape;
import mvc.DrawingModel;



public class CmdBringToBack implements Command{
	
	private DrawingModel model;
	private Shape shape;
	private int index;

	public CmdBringToBack(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		index = model.getIndexOfShape(shape);
		if(index==0) {
			 JOptionPane.showMessageDialog(null, "Already done. Shape has back position", "Error!", JOptionPane.ERROR_MESSAGE);
				return;
		 }
		model.removeFromIndex(index);            
		model.addToIndex(shape, 0);
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.removeFromIndex(0); 
		model.addToIndex(shape, index);
		
	}

	@Override
	public String sendToLog() {
		// TODO Auto-generated method stub
		return "bringToBack:" + shape.toString();
	}

}
