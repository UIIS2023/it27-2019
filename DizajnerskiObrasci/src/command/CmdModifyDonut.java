package command;

import geometry.Donut;

public class CmdModifyDonut implements Command {
	
	private Donut oldState;
	private Donut newState;
	private Donut originalState = new Donut();
	private String log;
	
	

	public CmdModifyDonut(Donut oldState, Donut newState) {
	
		this.oldState = oldState;
		this.newState = newState;
		log ="modify:" + oldState.toString() + " ->" + newState.toString();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		originalState=oldState.clone();
		oldState.setCenter(newState.getCenter());
		oldState.setRadius(newState.getRadius());
		oldState.setInnerRadius(newState.getInnerRadius());
		oldState.setColor(newState.getColor());
		oldState.setInnerColor(newState.getInnerColor());		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldState.setCenter(originalState.getCenter());
		oldState.setRadius(originalState.getRadius());
		oldState.setInnerRadius(originalState.getInnerRadius());
		oldState.setColor(originalState.getColor());
		oldState.setInnerColor(originalState.getInnerColor());		
	}

	@Override
	public String sendToLog() {
		// TODO Auto-generated method stub
		return log;
	}
	

}
