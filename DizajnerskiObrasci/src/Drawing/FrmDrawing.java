package Drawing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import Drawing.DlgCircle;
import Drawing.DlgDonut;
import Drawing.DlgLine;
import Drawing.DlgPoint;
import Drawing.DlgRectangle;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ButtonGroup;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

public class FrmDrawing extends JFrame {

	private final int OPERATION_DRAWING = 1;
	private final int OPERATION_EDIT_DELETE = 0;
	
	private int activeOperation = OPERATION_DRAWING;
	
	private PnlDrawing pnlDrawing = new PnlDrawing();
	private ButtonGroup btnsShapes = new ButtonGroup();
	
	private JButton btnDrawing = new JButton("Draw");
	private JToggleButton btnOperationEditOrDelete = new JToggleButton("Select");
	private JButton btnActionEdit = new JButton("Modify");
	private JButton btnActionDelete = new JButton("Delete");
	private JToggleButton tglbtnPoint = new JToggleButton("Point");
	private JToggleButton tglbtnLine = new JToggleButton("Line");
	private JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
	private JToggleButton tglbtnCircle = new JToggleButton("Circle");
	private JToggleButton tglbtnDonut = new JToggleButton("Donut");
	
	private Color edgeColor = Color.BLACK, innerColor = Color.WHITE;
	
	private boolean lineWaitingForSecondPoint = false;
	private Point lineFirstPoint;
	
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDrawing frame = new FrmDrawing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrmDrawing() {
		setTitle("Enio Kurtesi,IT27/2019");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 964, 655);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(1100, 700));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		activeOperation=OPERATION_DRAWING;
		
		
		JPanel pnlTop = new JPanel();
		contentPane.add(pnlTop, BorderLayout.NORTH);
		tglbtnPoint.setFont(new Font("Tw Cen MT", Font.PLAIN, 17));
		
		tglbtnPoint.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsShapes.add(tglbtnPoint);
		tglbtnLine.setFont(new Font("Tw Cen MT", Font.PLAIN, 17));
		
		tglbtnLine.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsShapes.add(tglbtnLine);
		tglbtnRectangle.setFont(new Font("Tw Cen MT", Font.PLAIN, 17));
		
		tglbtnRectangle.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsShapes.add(tglbtnRectangle);
		tglbtnCircle.setFont(new Font("Tw Cen MT", Font.PLAIN, 17));
		
		tglbtnCircle.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsShapes.add(tglbtnCircle);
		tglbtnDonut.setFont(new Font("Tw Cen MT", Font.PLAIN, 17));
		
		tglbtnDonut.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsShapes.add(tglbtnDonut);
		
				pnlDrawing.addMouseListener(pnlDrawingClickListener());
		
		JPanel pnlBottom = new JPanel();
		btnActionEdit.setFont(new Font("Tw Cen MT", Font.PLAIN, 17));
		
		btnActionEdit.addActionListener(btnActionEditClickListener());
		btnDrawing.setFont(new Font("Tw Cen MT", Font.PLAIN, 17));
		
		btnDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activeOperation = OPERATION_DRAWING;				
				pnlDrawing.deselect();				
			}
		});
		btnDrawing.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnOperationEditOrDelete.setFont(new Font("Tw Cen MT", Font.PLAIN, 17));
		
		btnOperationEditOrDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activeOperation = OPERATION_EDIT_DELETE;
			}
		});
		btnOperationEditOrDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsShapes.add(btnOperationEditOrDelete);
		btnActionEdit.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnActionDelete.setFont(new Font("Tw Cen MT", Font.PLAIN, 17));
		
		btnActionDelete.addActionListener(btnActionDeleteClickListener());
		btnActionDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
		GroupLayout gl_pnlTop = new GroupLayout(pnlTop);
		gl_pnlTop.setHorizontalGroup(
			gl_pnlTop.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTop.createSequentialGroup()
					.addGroup(gl_pnlTop.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlTop.createSequentialGroup()
							.addGap(129)
							.addComponent(tglbtnPoint, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addGap(117)
							.addComponent(tglbtnLine, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addGap(121)
							.addComponent(tglbtnRectangle)
							.addGap(88)
							.addComponent(tglbtnDonut, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
							.addGap(77)
							.addComponent(tglbtnCircle, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlTop.createSequentialGroup()
							.addContainerGap()
							.addComponent(pnlDrawing, GroupLayout.PREFERRED_SIZE, 1084, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlTop.createSequentialGroup()
							.addContainerGap()
							.addComponent(pnlBottom, GroupLayout.PREFERRED_SIZE, 1084, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_pnlTop.setVerticalGroup(
			gl_pnlTop.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTop.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlTop.createParallelGroup(Alignment.BASELINE)
						.addComponent(tglbtnCircle, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(tglbtnDonut, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(tglbtnRectangle, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(tglbtnLine, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(tglbtnPoint, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(pnlDrawing, GroupLayout.PREFERRED_SIZE, 527, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(pnlBottom, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
		);
		GroupLayout gl_pnlBottom = new GroupLayout(pnlBottom);
		gl_pnlBottom.setHorizontalGroup(
			gl_pnlBottom.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlBottom.createSequentialGroup()
					.addGap(245)
					.addComponent(btnDrawing, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addGap(78)
					.addComponent(btnOperationEditOrDelete, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGap(85)
					.addComponent(btnActionEdit, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addGap(101)
					.addComponent(btnActionDelete, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
					.addGap(137))
		);
		gl_pnlBottom.setVerticalGroup(
			gl_pnlBottom.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlBottom.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_pnlBottom.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnActionDelete, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnActionEdit, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnOperationEditOrDelete, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDrawing, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		pnlBottom.setLayout(gl_pnlBottom);
		pnlTop.setLayout(gl_pnlTop);
	}	
	private MouseAdapter pnlDrawingClickListener() {
		return new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Point mouseClick = new Point(e.getX(), e.getY());
				if (!tglbtnLine.isSelected()) lineWaitingForSecondPoint = false;
				
				if (activeOperation == OPERATION_EDIT_DELETE) {
					pnlDrawing.select(mouseClick);
					return;
				}
				if (tglbtnPoint.isSelected()) {
					DlgPoint dlgPoint = new DlgPoint();
					dlgPoint.setPoint(mouseClick);
					dlgPoint.setColors(edgeColor);
					dlgPoint.setVisible(true);
					if(dlgPoint.getPoint() != null) pnlDrawing.addShape(dlgPoint.getPoint());
					return;
				} else if (tglbtnLine.isSelected()) {
					if (lineWaitingForSecondPoint) {
						pnlDrawing.addShape(new Line(lineFirstPoint, mouseClick,false, edgeColor));
						lineWaitingForSecondPoint = false;
						return;
					}					
					lineFirstPoint = mouseClick;
					lineWaitingForSecondPoint = true;
					return;
				} else if (tglbtnRectangle.isSelected()) {
					DlgRectangle dlgRectangle = new DlgRectangle();
					dlgRectangle.setPoint(mouseClick);
					dlgRectangle.setColors(edgeColor, innerColor);
					dlgRectangle.setVisible(true);
					
					if(dlgRectangle.getRectangle() != null) pnlDrawing.addShape(dlgRectangle.getRectangle());
					return;
				} else if (tglbtnCircle.isSelected()) {
					DlgCircle dlgCircle = new DlgCircle();
					dlgCircle.setPoint(mouseClick);
					dlgCircle.setColors(innerColor, edgeColor);
					dlgCircle.setVisible(true);
					
					if(dlgCircle.getCircle() != null) pnlDrawing.addShape(dlgCircle.getCircle());
					return;
				} else if (tglbtnDonut.isSelected()) {
					DlgDonut dlgDonut = new DlgDonut();
					dlgDonut.setPoint(mouseClick);
					dlgDonut.setColors(edgeColor, innerColor);
					dlgDonut.setVisible(true);
					
					if(dlgDonut.getDonut() != null) pnlDrawing.addShape(dlgDonut.getDonut());
					return;
				}
			}
		};
	}
	private ActionListener btnActionEditClickListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = pnlDrawing.getSelected();
				if (index == -1) return;
				
				Shape shape = pnlDrawing.getShape(index);
				
				if (shape instanceof Point) {
					DlgPoint dlgPoint = new DlgPoint();
					dlgPoint.setPoint((Point)shape);
					dlgPoint.setVisible(true);
					
					if(dlgPoint.getPoint() != null) {
						pnlDrawing.setShape(index, dlgPoint.getPoint());
						pnlDrawing.repaint();
					}
				} else if (shape instanceof Line) {
					DlgLine dlgLine = new DlgLine();
					dlgLine.setLine((Line)shape);
					dlgLine.setVisible(true);
					
					if(dlgLine.getLine() != null) {
						pnlDrawing.setShape(index, dlgLine.getLine());
						pnlDrawing.repaint();
					}
				} else if (shape instanceof Rectangle) {
					DlgRectangle dlgRectangle = new DlgRectangle();
					dlgRectangle.setRectangle((Rectangle)shape);
					dlgRectangle.setVisible(true);
					
					if(dlgRectangle.getRectangle() != null) {
						pnlDrawing.setShape(index, dlgRectangle.getRectangle());
						pnlDrawing.repaint();
					}
				
				}else if (shape instanceof Donut) {
						DlgDonut dlgDonut = new DlgDonut();
						dlgDonut.setDonut((Donut)shape);
						dlgDonut.setVisible(true);
						
						if(dlgDonut.getDonut() != null) {
							pnlDrawing.setShape(index, dlgDonut.getDonut());
							pnlDrawing.repaint();
						}
				} else if (shape instanceof Circle) {
					DlgCircle dlgCircle = new DlgCircle();
					dlgCircle.setCircle((Circle)shape);
					dlgCircle.setVisible(true);
					
					if(dlgCircle.getCircle() != null) {
						pnlDrawing.setShape(index, dlgCircle.getCircle());
						pnlDrawing.repaint();
					}
				} 
			}
		};
	}
	private ActionListener btnActionDeleteClickListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pnlDrawing.isEmpty()) return;
				if (JOptionPane.showConfirmDialog(null, "Do you really want to delete the selected shape?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) pnlDrawing.removeSelected();
			}
		};
	}
}