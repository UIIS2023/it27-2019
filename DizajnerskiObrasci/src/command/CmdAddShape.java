package command;


import geometry.Shape;
import mvc.DrawingModel;

public class CmdAddShape implements Command {
	
	private Shape shape;
	private DrawingModel model;
	

	public CmdAddShape(Shape shape, DrawingModel model) {
	
		this.shape = shape;
		this.model = model;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		model.getShapes().add(shape);
		
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.getShapes().remove(shape);
		
	}

	@Override
	public String sendToLog() {
		// TODO Auto-generated method stub
		return "added:" + shape.toString();
	}

}
