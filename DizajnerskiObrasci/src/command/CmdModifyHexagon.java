package command;

import adapter.HexagonAdapter;

public class CmdModifyHexagon implements Command{
	private HexagonAdapter oldState;
	private HexagonAdapter newState;
	private HexagonAdapter originalState;
	private String log;
	

	public CmdModifyHexagon(HexagonAdapter oldState, HexagonAdapter newState) {
		
		this.oldState = oldState;
		this.newState = newState;
		log ="modify:" + oldState.toString() + "->" + newState.toString();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		originalState = oldState.clone();
		oldState.setXCoordinate(newState.getXCoordinate());
		oldState.setYCoordinate(newState.getYCoordinate());
		oldState.setRadius(newState.getRadius());
		oldState.setEdgeColor(newState.getEdgeColor());
		oldState.setInnerColor(newState.getInnerColor());
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldState.setXCoordinate(originalState.getXCoordinate());
		oldState.setYCoordinate(originalState.getYCoordinate());
		oldState.setRadius(originalState.getRadius());
		oldState.setEdgeColor(originalState.getEdgeColor());
		oldState.setInnerColor(originalState.getInnerColor());	
	}

	@Override
	public String sendToLog() {
		// TODO Auto-generated method stub
		return log;
	}

}
