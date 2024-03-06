package command;

import geometry.Point;

public class CmdModifyPoint implements Command {
	
	private Point oldState;
	private Point newState;
	private Point originalState = new Point();
	private String log;
	
	public CmdModifyPoint(Point oldState, Point newState) {
		
		this.oldState = oldState;
		this.newState = newState;
		log ="modify:" + oldState.toString() + "->" + newState.toString();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		originalState = oldState.clone();
		
		oldState.setX(newState.getX());
		oldState.setY(newState.getY());
		oldState.setColor(newState.getColor());		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldState.setX(originalState.getX());
		oldState.setY(originalState.getY());
		oldState.setColor(originalState.getColor());		
	}

	@Override
	public String sendToLog() {
		// TODO Auto-generated method stub
		return log;
	}

}
