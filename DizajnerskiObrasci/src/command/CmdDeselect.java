package command;

import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdDeselect implements Command{
	private Shape selectedShape;
	private DrawingModel model = new DrawingModel();
	

	public CmdDeselect(Shape selectedShape, DrawingModel model) {
		
		this.selectedShape = selectedShape;
		this.model = model;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		int index = model.getIndexOfShape(selectedShape);
		model.getShape(index).setSelected(false);
		
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		int index = model.getIndexOfShape(selectedShape);
		model.getShape(index).setSelected(true);
	}

	@Override
	public String sendToLog() {
		// TODO Auto-generated method stub
		return "deselect:" + selectedShape.toString();
	}

}
