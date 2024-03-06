package strategy;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class SaveLog implements Save {

	private DefaultListModel<String> defaultListModel;  

	public SaveLog(DefaultListModel<String> defaultListModel) {
		this.defaultListModel = defaultListModel;
	}
	

	@Override
	public void save() {
		// TODO Auto-generated method stub
		JFileChooser saveLog = new JFileChooser("C:\\Users\\DELL\\Documents");
		saveLog.setDialogTitle("Choose destination");
		if (saveLog.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = new File(saveLog.getSelectedFile().getAbsolutePath() + ".txt");

			if (file.exists()) {
				JOptionPane.showMessageDialog(null, "File name already exists!", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					PrintWriter writer = new PrintWriter(file);
					for (int i = 0; i < defaultListModel.size(); i++) {
						writer.println(defaultListModel.get(i));
					}
					writer.close();
					JOptionPane.showMessageDialog(null, "Good Job! Everything is fine.", "Process terminated",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
