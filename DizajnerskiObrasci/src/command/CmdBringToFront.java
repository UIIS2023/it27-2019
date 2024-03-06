package command;

import javax.swing.JOptionPane;

import geometry.Shape;
import mvc.DrawingModel;



public class CmdBringToFront implements Command {
	private DrawingModel model;
	private Shape shape;
	private int index;

	public CmdBringToFront(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
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
		model.addToIndex(shape, model.getShapes().size());
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.removeFromIndex(model.getShapes().size()-1);
		model.addToIndex(shape, index);
		
	}

	@Override
	public String sendToLog() {
		// TODO Auto-generated method stub
		return "bringToFront:" + shape.toString();
	}

}
