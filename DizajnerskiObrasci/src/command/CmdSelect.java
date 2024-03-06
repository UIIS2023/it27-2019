package command;

import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdSelect implements Command{
	
	private Shape selectedShape;
	private DrawingModel model;

	public CmdSelect(Shape selectedShape, DrawingModel model) {

		this.selectedShape=selectedShape;
		this.model=model;
		
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		model.getShape(model.getIndexOfShape(selectedShape)).setSelected(true);;
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.getShape(model.getIndexOfShape(selectedShape)).setSelected(false);;
	}

	@Override
	public String sendToLog() {
		// TODO Auto-generated method stub
		return "select:" + selectedShape.toString();
	}

}
