package command;

import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdRemoveShape implements Command {
	
	private ArrayList<Shape> selectedShapesForRemove = new ArrayList<Shape>();
	private DrawingModel model;
	
	

	public CmdRemoveShape(ArrayList<Shape> selectedShapesForRemove, DrawingModel model) {
		
		this.selectedShapesForRemove = selectedShapesForRemove;
		this.model = model;
	}

	private String log() {
		StringBuilder sBuilder = new StringBuilder();
		for(int i = 0; i < selectedShapesForRemove.size(); i++) {
			sBuilder.append(selectedShapesForRemove.get(i).toString() + ":");
		}		
		return sBuilder.substring(0, sBuilder.length() - 1).toString();

	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		for(int i = 0; i < selectedShapesForRemove.size(); i++) {
			model.getShapes().remove(selectedShapesForRemove.get(i));
		}
		
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		for(int i = 0; i < selectedShapesForRemove.size(); i++) {
			model.getShapes().add(selectedShapesForRemove.get(i));
			selectedShapesForRemove.get(i).setSelected(true);
		}
	}

	@Override
	public String sendToLog() {
		// TODO Auto-generated method stub
		return "remove:" + log();
	}

}
