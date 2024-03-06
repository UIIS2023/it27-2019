package mvc;
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.UIManager;
 
 
public class DrawingFrame extends JFrame{
 
    private DrawingView view = new DrawingView();
    private DrawingController controller;
 
    private JPanel contentPane;
    private ButtonGroup btnsShapes = new ButtonGroup();
    private  ButtonGroup btnGroup= new ButtonGroup();
    private  ButtonGroup btnGroup2 = new ButtonGroup();
    private JToggleButton tglBtnSelect = new JToggleButton("Select");
    private JButton btnActionEdit = new JButton("Modify");
    private JButton btnActionDelete = new JButton("Delete");
    private JToggleButton tglbtnPoint = new JToggleButton("Point");
    private JToggleButton tglbtnLine = new JToggleButton("Line");
    private JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
    private JToggleButton tglbtnCircle = new JToggleButton("Circle");
    private JToggleButton tglbtnDonut = new JToggleButton("Donut");
    private JToggleButton tglbtnHexagon = new JToggleButton("Hexagon");
    private JButton btnColor = new JButton("Color");
    private JButton btnEdgeColor = new JButton("Edge Color");
    private JButton btnBringToBack = new JButton("Bring To Back");
    private JButton btnBringToFront = new JButton("Bring To Front");
    private JButton btnToBack = new JButton("To Back");
    private JButton btnToFront = new JButton("To Front");
    private JList list = new JList();
    private DefaultListModel<String> defaultListModel = new DefaultListModel<String>();
    private JButton btnUndo = new JButton("Undo");
    private JButton btnRedo = new JButton("Redo");
    private final JPanel panel = new JPanel();
    private JButton btnSaveDraw = new JButton("Save draw");
    private JButton btnSaveLog = new JButton("Save log");
    private  JButton btnOpenDraw = new JButton("Open draw");
    private  JButton btnOpenLog = new JButton("Open log");
    private  JButton btnNextCommand = new JButton("Next command");
    
   
 
 
 
    
	public DrawingFrame() {
		btnNextCommand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.nextCommand();
			}
		});
		btnNextCommand.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
		btnNextCommand.setAlignmentX(0.5f);
		btnOpenLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.openLog();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnOpenLog.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
		btnOpenLog.setAlignmentX(0.5f);
		btnOpenDraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.openDraw();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnOpenDraw.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
		btnOpenDraw.setAlignmentX(0.5f);
        setTitle("Enio Kurtesi IT27/2019");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 964, 655);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(1100, 700));
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        setResizable(false);
 
        JPanel pnlBottom = new JPanel();
        contentPane.add(pnlBottom, BorderLayout.CENTER);
                    GroupLayout gl_pnlBottom = new GroupLayout(pnlBottom);
                    gl_pnlBottom.setHorizontalGroup(
                    	gl_pnlBottom.createParallelGroup(Alignment.LEADING)
                    		.addGap(0, 1084, Short.MAX_VALUE)
                    );
                    gl_pnlBottom.setVerticalGroup(
                    	gl_pnlBottom.createParallelGroup(Alignment.TRAILING)
                    		.addGap(0, 215, Short.MAX_VALUE)
                    );
              
 
                    pnlBottom.setLayout(gl_pnlBottom);
 
                    contentPane.add(panel, BorderLayout.WEST);
 
                    JPanel pnlTop = new JPanel();
                    tglbtnPoint.setSelected(true);
 
                        tglbtnPoint.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
 
                        tglbtnPoint.setAlignmentX(Component.CENTER_ALIGNMENT);
                        tglbtnPoint.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                btnColor.setEnabled(false);
                            }
                        });
 
                        tglbtnLine.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                btnColor.setEnabled(false);
                            }
                        });
                        tglbtnRectangle.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                btnColor.setEnabled(true);
                            }
                        });
                        tglbtnCircle.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                btnColor.setEnabled(true);
                            }
                        });
                        tglbtnDonut.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                btnColor.setEnabled(true);
                            }
                        });
                        tglbtnHexagon.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                btnColor.setEnabled(true);
                            }
                        });
 
                            btnsShapes.add(tglbtnPoint);
                            tglbtnLine.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
 
                            tglbtnLine.setAlignmentX(Component.CENTER_ALIGNMENT);
                            btnsShapes.add(tglbtnLine);
                            tglbtnRectangle.setFont(new Font("Lucida Handwriting", Font.PLAIN, 13));
 
                            tglbtnRectangle.setAlignmentX(Component.CENTER_ALIGNMENT);
                            btnsShapes.add(tglbtnRectangle);
                            tglbtnCircle.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
 
                            tglbtnCircle.setAlignmentX(Component.CENTER_ALIGNMENT);
                            btnsShapes.add(tglbtnCircle);
                            tglbtnDonut.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
 
                            tglbtnDonut.setAlignmentX(Component.CENTER_ALIGNMENT);
                            btnsShapes.add(tglbtnDonut);
 
                            btnsShapes.add(tglbtnHexagon);
                            tglbtnRectangle.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    btnColor.setEnabled(true);
                                }
                            });
 
                            tglbtnHexagon.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
                            btnsShapes.add(tglBtnSelect);
                            GroupLayout gl_pnlTop = new GroupLayout(pnlTop);
                            gl_pnlTop.setHorizontalGroup(
                            	gl_pnlTop.createParallelGroup(Alignment.LEADING)
                            		.addGroup(gl_pnlTop.createSequentialGroup()
                            			.addContainerGap()
                            			.addComponent(tglbtnPoint, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                            			.addPreferredGap(ComponentPlacement.UNRELATED)
                            			.addComponent(tglbtnLine, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                            			.addPreferredGap(ComponentPlacement.UNRELATED)
                            			.addComponent(tglbtnRectangle)
                            			.addGap(18)
                            			.addComponent(tglbtnDonut, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                            			.addGap(18)
                            			.addComponent(tglbtnCircle, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                            			.addGap(18)
                            			.addComponent(tglbtnHexagon, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
                            			.addContainerGap(762, Short.MAX_VALUE))
                            );
                            gl_pnlTop.setVerticalGroup(
                            	gl_pnlTop.createParallelGroup(Alignment.LEADING)
                            		.addGroup(gl_pnlTop.createSequentialGroup()
                            			.addContainerGap()
                            			.addGroup(gl_pnlTop.createParallelGroup(Alignment.LEADING, false)
                            				.addComponent(tglbtnPoint, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            				.addComponent(tglbtnLine, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            				.addGroup(gl_pnlTop.createParallelGroup(Alignment.BASELINE)
                            					.addComponent(tglbtnRectangle, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            					.addComponent(tglbtnDonut, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            					.addComponent(tglbtnCircle, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            					.addComponent(tglbtnHexagon, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)))
                            			.addContainerGap(438, Short.MAX_VALUE))
                            );
                            pnlTop.setLayout(gl_pnlTop);
                    btnColor.addActionListener(new ActionListener() {
                    	public void actionPerformed(ActionEvent e) {
                    		controller.selectInnerColor();
                    	}
                    });
 
 
                    btnColor.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
                    btnColor.setEnabled(false);
                    btnColor.setBackground(Color.WHITE);
                    btnEdgeColor.addActionListener(new ActionListener() {
                    	public void actionPerformed(ActionEvent e) {
                    		
                    		controller.selectEdgeColor();
                    	}
                    });
 

                    btnEdgeColor.setForeground(Color.WHITE);
                    btnEdgeColor.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
                    btnEdgeColor.setBackground(Color.BLACK);
 
                    JLabel lblNewLabel = new JLabel("Pick Desire Color");
                    lblNewLabel.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
 
 
 
                           tglBtnSelect.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
                           tglBtnSelect.setAlignmentX(Component.CENTER_ALIGNMENT);
                    btnActionEdit.setEnabled(false);
                    btnActionEdit.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
 
                    btnActionEdit.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
 
                                    controller.modifyShape();
 
                            }
                        }
                    );
                    btnActionEdit.setAlignmentX(Component.CENTER_ALIGNMENT);
                    btnActionDelete.setEnabled(false);
                    btnActionDelete.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (btnActionDelete.isEnabled()) {                  
                                    controller.delete();
                                    repaint();
                            }
                        }
                    });
                    btnActionDelete.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
 
 
                            btnActionDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
                    btnToFront.setEnabled(false);
                    
                    
                    btnToFront.addActionListener(new ActionListener() {
                    	public void actionPerformed(ActionEvent e) {
                    		
                    		controller.toFront();
                    		repaint();
                    	}
                    });
                    btnToFront.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
                    btnToFront.setBackground(UIManager.getColor("Button.background"));
                    btnToBack.setEnabled(false);
                    
                    
                    btnToBack.addActionListener(new ActionListener() {
                    	public void actionPerformed(ActionEvent e) {
                    		controller.toBack();
                    		repaint();
                    	}
                    });
                    btnToBack.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
                    btnToBack.setBackground(UIManager.getColor("Button.background"));
                    btnBringToFront.setEnabled(false);
                    
                    
                    btnBringToFront.addActionListener(new ActionListener() {
                    	public void actionPerformed(ActionEvent e) {
                    		controller.bringToFront();
                    		repaint();
                    	}
                    });
                    btnBringToFront.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
                    btnBringToFront.setBackground(UIManager.getColor("Button.background"));
                    btnBringToBack.setEnabled(false);
                    
                    
                    btnBringToBack.addActionListener(new ActionListener() {
                    	public void actionPerformed(ActionEvent e) {
                    		controller.bringToBack();
                    		repaint();
                    	}
                    });
                    btnBringToBack.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
                    btnBringToBack.setBackground(UIManager.getColor("Button.background"));
                    
                    JScrollPane scrollPane = new JScrollPane();
                    
                    
                    btnUndo.addActionListener(new ActionListener() {
                    	public void actionPerformed(ActionEvent e) {
                    		controller.undo();
                    		repaint();
                    	}
                    });
                    btnUndo.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
                    btnUndo.setEnabled(false);
                    btnUndo.setAlignmentX(0.5f);
                    
                    
                    btnRedo.addActionListener(new ActionListener() {
                    	public void actionPerformed(ActionEvent e) {
                    		controller.redo();
                    		repaint();
                    	}
                    });
                    btnRedo.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
                    btnRedo.setEnabled(false);
                    btnRedo.setAlignmentX(0.5f);
                    
                    
                    btnSaveDraw.addActionListener(new ActionListener() {
                    	public void actionPerformed(ActionEvent e) {
                    		controller.saveDraw();
                    	}
                    });
                    btnSaveDraw.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
                    btnSaveDraw.setAlignmentX(0.5f);
                    
                    
                    btnSaveLog.addActionListener(new ActionListener() {
                    	public void actionPerformed(ActionEvent e) {
                    		controller.saveLog();
                    	}
                    });
                    btnSaveLog.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
                    btnSaveLog.setAlignmentX(0.5f);
                    view.setBackground(Color.WHITE);
                    GroupLayout gl_view = new GroupLayout(view);
                    gl_view.setHorizontalGroup(
                        gl_view.createParallelGroup(Alignment.LEADING)
                            .addGap(0, 916, Short.MAX_VALUE)
                    );
                    gl_view.setVerticalGroup(
                        gl_view.createParallelGroup(Alignment.LEADING)
                            .addGap(0, 538, Short.MAX_VALUE)
                    );
                    view.setLayout(gl_view);
                    
                                                       view.addMouseListener(new MouseAdapter() {
                    
                                                           @Override
                                                           public void mouseClicked(MouseEvent e) {
                    
                                                               if(tglBtnSelect.isSelected()) { 
                    
                                                                   controller.select(e);
                                                                   
                                                                   repaint();
                                                               }                                  
                                                               if(tglbtnPoint.isSelected()) {
                    
                                                                   controller.drawPoint(e, btnEdgeColor.getBackground());
                                                                   
                    
                                                                   repaint();
                                                               }
                                                               if(tglbtnLine.isSelected()) {
                    
                                                                   controller.drawLine(e,btnEdgeColor.getBackground());
                                                                   
                    
                                                                   repaint();
                                                               }
                                                               if(tglbtnRectangle.isSelected()) {
                    
                                                                   controller.drawRectangle(e,btnColor.getBackground(), btnEdgeColor.getBackground());
                                                                   
                    
                                                                   repaint();
                                                               }
                                                               if(tglbtnCircle.isSelected()) {
                    
                                                                   controller.drawCircle(e,btnColor.getBackground(), btnEdgeColor.getBackground());
                                                                   
                    
                                                                   repaint();
                                                               }
                    
                                                               if(tglbtnDonut.isSelected()) {
                    
                                                                   controller.drawDonut(e);
                                                                   
                    
                                                                   repaint();
                                                               }
                                                               
                                                               if(tglbtnHexagon.isSelected()) {
                                                               	controller.drawHexagon(e,btnColor.getBackground(), btnEdgeColor.getBackground());
                                                                   
                    
                                                                   repaint();
                                                               }
                    
                    
                                                           }                                                           
                                                       });
                    GroupLayout gl_panel = new GroupLayout(panel);
                    gl_panel.setHorizontalGroup(
                    	gl_panel.createParallelGroup(Alignment.LEADING)
                    		.addGroup(gl_panel.createSequentialGroup()
                    			.addContainerGap()
                    			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                    				.addGroup(gl_panel.createSequentialGroup()
                    					.addGap(10)
                    					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
                    						.addComponent(btnSaveDraw, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
                    						.addComponent(btnSaveLog, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
                    						.addComponent(btnOpenDraw, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
                    						.addComponent(btnOpenLog, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
                    						.addGroup(gl_panel.createSequentialGroup()
                    							.addComponent(btnNextCommand)
                    							.addPreferredGap(ComponentPlacement.RELATED))))
                    				.addGroup(gl_panel.createSequentialGroup()
                    					.addGap(11)
                    					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
                    						.addComponent(btnRedo, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
                    						.addComponent(btnUndo, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)))
                    				.addComponent(btnToFront, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
                    				.addComponent(btnBringToFront, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
                    				.addComponent(btnToBack, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
                    				.addComponent(btnBringToBack, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
                    			.addGap(18)
                    			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                    				.addComponent(pnlTop, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
                    					.addGroup(gl_panel.createSequentialGroup()
                    						.addComponent(view, GroupLayout.PREFERRED_SIZE, 694, GroupLayout.PREFERRED_SIZE)
                    						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
                    					.addGroup(gl_panel.createSequentialGroup()
                    						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 712, GroupLayout.PREFERRED_SIZE)
                    						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                    							.addGroup(gl_panel.createSequentialGroup()
                    								.addGap(33)
                    								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
                    									.addComponent(tglBtnSelect, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    									.addComponent(btnActionEdit, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    									.addComponent(btnActionDelete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    							.addGroup(gl_panel.createSequentialGroup()
                    								.addGap(18)
                    								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
                    									.addComponent(btnEdgeColor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    									.addComponent(btnColor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)))))))
                    			.addGap(32))
                    );
                    gl_panel.setVerticalGroup(
                    	gl_panel.createParallelGroup(Alignment.LEADING)
                    		.addGroup(gl_panel.createSequentialGroup()
                    			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                    				.addGroup(gl_panel.createSequentialGroup()
                    					.addComponent(pnlTop, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                    					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
                    						.addGroup(gl_panel.createSequentialGroup()
                    							.addGap(3)
                    							.addComponent(view, GroupLayout.PREFERRED_SIZE, 406, GroupLayout.PREFERRED_SIZE)
                    							.addGap(2))
                    						.addGroup(gl_panel.createSequentialGroup()
                    							.addGap(62)
                    							.addComponent(tglBtnSelect, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                    							.addGap(18)
                    							.addComponent(btnActionEdit, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                    							.addGap(18)
                    							.addComponent(btnActionDelete, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                    							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    							.addComponent(lblNewLabel)
                    							.addGap(18)
                    							.addComponent(btnColor, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                    							.addPreferredGap(ComponentPlacement.RELATED)))
                    					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                    						.addGroup(gl_panel.createSequentialGroup()
                    							.addGap(60)
                    							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
                    						.addGroup(gl_panel.createSequentialGroup()
                    							.addPreferredGap(ComponentPlacement.UNRELATED)
                    							.addComponent(btnEdgeColor, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))))
                    				.addGroup(gl_panel.createSequentialGroup()
                    					.addGap(25)
                    					.addComponent(btnUndo, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                    					.addPreferredGap(ComponentPlacement.UNRELATED)
                    					.addComponent(btnRedo, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                    					.addGap(27)
                    					.addComponent(btnToFront, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                    					.addPreferredGap(ComponentPlacement.UNRELATED)
                    					.addComponent(btnBringToFront, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                    					.addPreferredGap(ComponentPlacement.RELATED)
                    					.addComponent(btnToBack, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                    					.addPreferredGap(ComponentPlacement.UNRELATED)
                    					.addComponent(btnBringToBack, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                    					.addGap(44)
                    					.addComponent(btnSaveDraw, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                    					.addPreferredGap(ComponentPlacement.UNRELATED)
                    					.addComponent(btnSaveLog, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                    					.addPreferredGap(ComponentPlacement.UNRELATED)
                    					.addComponent(btnOpenDraw, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                    					.addPreferredGap(ComponentPlacement.UNRELATED)
                    					.addComponent(btnOpenLog, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                    					.addPreferredGap(ComponentPlacement.RELATED)
                    					.addComponent(btnNextCommand, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)))
                    			.addContainerGap())
                    );
                    
                    
                    scrollPane.setViewportView(list);
                    list.setModel(defaultListModel);
                    panel.setLayout(gl_panel);
    }
    public DrawingView getView() {
        return view;
    }
 
    public void setController(DrawingController controller) {
        this.controller = controller;
    }
    public JToggleButton getTglbtnPoint() {
        return tglbtnPoint;
    }
    public void setTglbtnPoint(JToggleButton tglbtnPoint) {
        this.tglbtnPoint = tglbtnPoint;
    }
    public JButton getBtnActionDelete() {
        return btnActionDelete;
    }
    public JToggleButton getTglbtnLine() {
        return tglbtnLine;
    }
    public JToggleButton getTglbtnRectangle() {
        return tglbtnRectangle;
    }
    public JButton getBtnBringToBack() {
		return btnBringToBack;
	}
	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}
	public JButton getBtnToBack() {
		return btnToBack;
	}
	public JButton getBtnToFront() {
		return btnToFront;
	}
	public JToggleButton getTglbtnCircle() {
        return tglbtnCircle;
    }
    public JToggleButton getTglbtnDonut() {
        return tglbtnDonut;
    }
    public JButton getbtnColor() {
        return btnColor;
    }
    public JToggleButton getTglbtnHexagon() {
        return tglbtnHexagon;
    }
    public JButton getBtnEdgeColor() {
        return btnEdgeColor;
    }
    public JButton getBtnActionEdit() {
        return btnActionEdit;
    }
	public JList getList() {
		return list;
	}
	public void setList(JList list) {
		this.list = list;
	}
	public DefaultListModel<String> getDefaultListModel() {
		return defaultListModel;
	}
	public void setDefaultListModel(DefaultListModel<String> defaultListModel) {
		this.defaultListModel = defaultListModel;
	}
	public JButton getBtnUndo() {
		return btnUndo;
	}
	public JButton getBtnRedo() {
		return btnRedo;
	}
	public JButton getBtnNextCommand() {
		return btnNextCommand;
	}
	
}