package strategy;



public class SaveManager implements Save {

	private Save strategyOfSave;
	
	public SaveManager(Save strategyOfSave) {
		this.strategyOfSave = strategyOfSave;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		strategyOfSave.save();
	}
}
