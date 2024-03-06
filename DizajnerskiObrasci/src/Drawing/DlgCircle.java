package Drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;

import geometry.Circle;
import geometry.Point;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DlgCircle extends JDialog {
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtRadius;
	
	private Circle circle = null;
	private Color  innerColor = null;
	private Color edgeColor = null;
	private JLabel lblX;
	private JLabel lblY;
	private JLabel lblRadius;
	private JButton btnInnerColor;
	private JButton btnEdgeColor = new JButton("Edge Color");

	public DlgCircle() {
		setResizable(false);
		setTitle("Enio Kurtesi,IT27/2019");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 300, 198);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
		{
			JPanel pnlCenter = new JPanel();
			getContentPane().add(pnlCenter, BorderLayout.CENTER);
			{
				lblX = new JLabel("X coordinate", SwingConstants.CENTER);
			}
			{
				txtX = new JTextField();
				txtX.setColumns(10);
			}
			{
				lblY = new JLabel("Y coordinate");
				lblY.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				txtY = new JTextField();
				txtY.setColumns(10);
			}
			{
				lblRadius = new JLabel("Radius");
				lblRadius.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				txtRadius = new JTextField();
				txtRadius.setColumns(10);
			}
			{
				btnInnerColor = new JButton("Inner Color");
				btnInnerColor.setBackground(Color.WHITE);
				btnInnerColor.setHorizontalAlignment(SwingConstants.CENTER);
				btnInnerColor.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						innerColor = JColorChooser.showDialog(null, "Choose inner color", innerColor);
						
						if (innerColor == null) {
							 innerColor = btnInnerColor.getBackground();
						}
						else {
							btnInnerColor.setBackground(innerColor);
						}
						
					}
				});
				btnEdgeColor.setBackground(Color.BLACK);
				btnEdgeColor.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						edgeColor = JColorChooser.showDialog(null, "Choose edge color", edgeColor);
						if (edgeColor == null) {
							 edgeColor = btnEdgeColor.getBackground();
						}
						else {
							btnEdgeColor.setBackground(edgeColor);
						}
						
					}
				});
			}
			
			
			btnEdgeColor.setHorizontalAlignment(SwingConstants.CENTER);
			GroupLayout gl_pnlCenter = new GroupLayout(pnlCenter);
			gl_pnlCenter.setHorizontalGroup(
				gl_pnlCenter.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_pnlCenter.createSequentialGroup()
						.addComponent(lblX, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_pnlCenter.createSequentialGroup()
						.addComponent(lblY, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_pnlCenter.createSequentialGroup()
						.addComponent(lblRadius, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_pnlCenter.createSequentialGroup()
						.addContainerGap()
						.addComponent(btnInnerColor, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
						.addGap(18)
						.addComponent(btnEdgeColor, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
						.addGap(23))
			);
			gl_pnlCenter.setVerticalGroup(
				gl_pnlCenter.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_pnlCenter.createSequentialGroup()
						.addGap(1)
						.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
							.addComponent(lblX, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtX, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
							.addComponent(lblY, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtY, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
							.addComponent(lblRadius, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnInnerColor)
							.addComponent(btnEdgeColor))
						.addGap(16))
			);
			pnlCenter.setLayout(gl_pnlCenter);
		}
		{
			JPanel pnlSouth = new JPanel();
			getContentPane().add(pnlSouth, BorderLayout.SOUTH);
			pnlSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton btnOk = new JButton("OK");
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							int newX = Integer.parseInt(txtX.getText());
							int newY = Integer.parseInt(txtY.getText());
							int newRadius = Integer.parseInt(txtRadius.getText());

							if(newX < 0 || newY < 0 || newRadius < 1) {
								JOptionPane.showMessageDialog(null, "You have entered incorrect informations!", "ERROR!", JOptionPane.ERROR_MESSAGE);
								return;
							}
							if (innerColor == null)
							{
								JOptionPane.showMessageDialog(null, "Choose a color!!", "ERROR!", JOptionPane.ERROR_MESSAGE);
	                        return;
							}
							
							circle = new Circle(new Point(newX, newY), newRadius,false, edgeColor, innerColor);
							dispose();
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "You have entered incorrect informations!", "ERROR!", JOptionPane.ERROR_MESSAGE);
						}
						
					}
				});
				pnlSouth.add(btnOk);
			}
			{
				JButton btnExit = new JButton("EXIT");
				btnExit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				pnlSouth.add(btnExit);
			}
		}
	}

	public Circle getCircle() {
		return circle;
	}
	
	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}

	public JButton getBtnEdgeColor() {
		return btnEdgeColor;
	}

	public void setPoint(Point point) {
		txtX.setText("" + point.getX());
		txtY.setText("" + point.getY());
	}
	
	public void setColors(Color innerColor, Color edgeColor) {
		
		this.innerColor = innerColor;
		this.edgeColor = edgeColor;
		
	}
	
	public void setCircle(Circle circle) {
		txtX.setText("" + circle.getCenter().getX());
		txtY.setText("" + circle.getCenter().getY());
		txtRadius.setText("" + circle.getRadius());
		
		innerColor = circle.getInnerColor();
		edgeColor = circle.getColor();
	}
}
