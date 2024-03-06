package command;

import java.util.ArrayList;
import java.util.Collections;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdDeselectAll implements Command{
	
	private ArrayList<Shape> selectedShapes = new ArrayList<Shape>();
	private DrawingModel model = new DrawingModel();
	

	public CmdDeselectAll(ArrayList<Shape> selectedShapes, DrawingModel model) {
		
		this.selectedShapes = selectedShapes;
		this.model = model;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
		selectedShapes.forEach(shape -> shape.setSelected(false));
		
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		selectedShapes.forEach(shape -> shape.setSelected(true));
	}

	@Override
	public String sendToLog() {
		// TODO Auto-generated method stub
		return "deselectAllShapes";
	}

}
