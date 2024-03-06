package command;

import geometry.Line;

public class CmdModifyLine implements Command {
	
	private Line oldState;
	private Line newState;
	private Line originalState = new Line();
	private String log;
	

	public CmdModifyLine(Line oldState, Line newState) {
		
		this.oldState = oldState;
		this.newState = newState;
		log ="modify:" + oldState.toString() + "->" + newState.toString();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		originalState = oldState.clone();
		oldState.setStartPoint(newState.getStartPoint());
		oldState.setEndPoint(newState.getEndPoint());
		oldState.setColor(newState.getColor());
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldState.setStartPoint(originalState.getStartPoint());
		oldState.setEndPoint(originalState.getEndPoint());
		oldState.setColor(originalState.getColor());
		
	}

	@Override
	public String sendToLog() {
		// TODO Auto-generated method stub
		return log;
	}

}
