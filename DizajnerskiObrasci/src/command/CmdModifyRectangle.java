package command;

import geometry.Rectangle;

public class CmdModifyRectangle implements Command{
	private Rectangle oldState;
	private Rectangle newState;
	private Rectangle originalState = new Rectangle();
	private String log;

	public CmdModifyRectangle(Rectangle oldState, Rectangle newState) {
		
		this.oldState = oldState;
		this.newState = newState;
		log ="modify:" + oldState.toString() + "->" + newState.toString();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		originalState = oldState.clone();
		oldState.setUpperLeftPoint(newState.getUpperLeftPoint());
		oldState.setHeight(newState.getHeight());
		oldState.setWidth(newState.getWidth());
		oldState.setColor(newState.getColor());
		oldState.setInnerColor(newState.getInnerColor());		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldState.setUpperLeftPoint(originalState.getUpperLeftPoint());
		oldState.setHeight(originalState.getHeight());
		oldState.setWidth(originalState.getWidth());
		oldState.setColor(originalState.getColor());
		oldState.setInnerColor(originalState.getInnerColor());		
	}

	@Override
	public String sendToLog() {
		// TODO Auto-generated method stub
		return log;
	}

}
