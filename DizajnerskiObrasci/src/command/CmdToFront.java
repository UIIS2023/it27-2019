package command;

import javax.swing.JOptionPane;

import geometry.Shape;
import mvc.DrawingModel;



public class CmdToFront implements Command {

	private Shape shape;
	private DrawingModel model;
	private int index;
	
	
	public CmdToFront(Shape shape, DrawingModel model) {
		
		this.shape = shape;
		this.model = model;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		index = model.getIndexOfShape(shape);
		if(index==model.getShapes().size()-1) {
			 JOptionPane.showMessageDialog(null, "Already done. Shape is in front!", "Error!", JOptionPane.ERROR_MESSAGE);
				return;
		 }
		model.removeFromIndex(index);
		model.addToIndex(shape, index + 1);
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.removeFromIndex(index + 1);
		model.addToIndex(shape, index);
		
	}

	@Override
	public String sendToLog() {
		// TODO Auto-generated method stub
		return "toFront:" + shape.toString();
	}

}
