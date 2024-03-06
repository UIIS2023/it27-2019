package command;

import geometry.Circle;

public class CmdModifyCircle implements Command{
	private Circle oldState;
	private Circle newState;
	private Circle originalState = new Circle();
	private String log;
	
	

	public CmdModifyCircle(Circle oldState, Circle newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		originalState = oldState.clone();
		oldState.setCenter(newState.getCenter());
		oldState.setRadius(newState.getRadius());
		oldState.setColor(newState.getColor());
		oldState.setInnerColor(newState.getInnerColor());
		log = "modify:"  + originalState.toString() + "->" + oldState.toString();
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldState.setCenter(originalState.getCenter());
		oldState.setRadius(originalState.getRadius());
		oldState.setColor(originalState.getColor());
		oldState.setInnerColor(originalState.getInnerColor());
		log = "modify:"  + oldState.toString() + "->" + originalState.toString();
		
	}

	@Override
	public String sendToLog() {
		// TODO Auto-generated method stub
		return log;
	}

}
