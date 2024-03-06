package strategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import geometry.Shape;



public class SaveDraw implements Save{

	private ArrayList<Shape> shapes;

	public SaveDraw(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		JFileChooser saveDraw = new JFileChooser("C:\\Users\\DELL\\Documents");
		saveDraw.setDialogTitle("Choose destination to save");
		if (saveDraw.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) { 
			File file = new File(saveDraw.getSelectedFile().getAbsolutePath());

			if (file.exists()) { 
				JOptionPane.showMessageDialog(null, "File name already exists!", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				FileOutputStream fileOutput;
				ObjectOutputStream objectOutput;
				try { 
					fileOutput = new FileOutputStream(file + ".ser");
					objectOutput = new ObjectOutputStream(fileOutput);

					objectOutput.writeObject(shapes);
					objectOutput.close();
					fileOutput.close();

					JOptionPane.showMessageDialog(null, "Good Job! Everything is fine.", "Process terminated",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
