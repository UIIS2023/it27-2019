package mvc;
 
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
 
import javax.swing.DefaultListModel;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
 
//import org.omg.CORBA.PRIVATE_MEMBER;
 
import Drawing.DlgCircle;
import Drawing.DlgDonut;
import Drawing.DlgHexagon;
import Drawing.DlgLine;
import Drawing.DlgPoint;
import Drawing.DlgRectangle;
import adapter.HexagonAdapter;
import command.CmdAddShape;
import command.CmdBringToBack;
import command.CmdBringToFront;
import command.CmdDeselect;
import command.CmdDeselectAll;
import command.CmdModifyCircle;
import command.CmdModifyDonut;
import command.CmdModifyHexagon;
import command.CmdModifyLine;
import command.CmdModifyPoint;
import command.CmdModifyRectangle;
import command.CmdRemoveShape;
import command.CmdSelect;
import command.CmdToBack;
import command.CmdToFront;
import command.Command;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import observer.buttonSwitch;
import strategy.SaveDraw;
import strategy.SaveLog;
import strategy.SaveManager;
 
 
public class DrawingController {
    
    private DrawingModel model;
    private DrawingFrame frame;
    
   private int counter = 1;
   private Point firstPoint = new Point();
   private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private int i;
    ArrayList<Shape> selectedShapes = new ArrayList<Shape>();
    private Color edgeColor = Color.BLACK;
    private Color innerColor = Color.WHITE;
    private DefaultListModel<String> consoleLog;
    private ArrayList<Command> historyList = new ArrayList<Command>();
    private int historyListIndex;
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    
 
    public DrawingController(DrawingModel model, DrawingFrame frame) {
        this.model = model;
        this.frame = frame;
        consoleLog = frame.getDefaultListModel();
    }
        
        public void drawPoint(MouseEvent click, Color edgeColor) {
        
            Point point = new Point(click.getX(), click.getY());
            point.setColor(edgeColor);
            point.addObserver(new buttonSwitch(model,frame));
            CmdAddShape cas = new CmdAddShape(point,model);
            cas.execute();
            consoleLog.addElement(cas.sendToLog());
            addCommandsToList(cas);
             
                
            
          
        }
 
    public void drawLine(MouseEvent click, Color color) {
    
        
        if(counter==1)
        { 
        firstPoint=    new Point(click.getX(),click.getY());
        counter++; 
        
        } 
        else {
            Line line = new Line(firstPoint,new Point(click.getX(),click.getY()),false,color);
            line.addObserver(new buttonSwitch(model,frame));
            counter = 1;
            CmdAddShape cas = new CmdAddShape(line,model);
            cas.execute();
            consoleLog.addElement(cas.sendToLog());
            addCommandsToList(cas);
            }
    }
 
    public void drawRectangle(MouseEvent click, Color color, Color edgeColor) {
    
        DlgRectangle dlgRectangle = new DlgRectangle();
        dlgRectangle.setPoint(new Point(click.getX(),click.getY()));
        dlgRectangle.setColors(edgeColor, color);
        dlgRectangle.getBtnEdgeColor().setBackground(edgeColor);
        dlgRectangle.getBtnInnerColor().setBackground(innerColor);
        dlgRectangle.setVisible(true);
        
        if(dlgRectangle.getRectangle() != null) {
            Rectangle rectangle = dlgRectangle.getRectangle();
            CmdAddShape cas = new CmdAddShape(rectangle,model);
            cas.execute();
            consoleLog.addElement(cas.sendToLog());
            addCommandsToList(cas);
            rectangle.addObserver(new buttonSwitch(model,frame));
        }
        return;
    }
 
    
 
    public void drawCircle(MouseEvent click, Color color, Color edgeColor) {
    
        DlgCircle dlgCircle = new DlgCircle();
        dlgCircle.setPoint(new Point(click.getX(),click.getY()));
        dlgCircle.setColors(color, edgeColor);
        dlgCircle.getBtnEdgeColor().setBackground(edgeColor);
        dlgCircle.getBtnInnerColor().setBackground(innerColor);
        dlgCircle.setVisible(true);
        
        if(dlgCircle.getCircle() != null) {
            Circle circle = dlgCircle.getCircle();
            CmdAddShape cas = new CmdAddShape(circle,model);
            cas.execute();
            consoleLog.addElement(cas.sendToLog());
            addCommandsToList(cas);
            circle.addObserver(new buttonSwitch(model,frame));
        }
        return;
            
        }
    
    
    public void drawHexagon(MouseEvent click, Color color, Color edgeColor) {
        
        DlgHexagon dlgHexagon = new DlgHexagon();
        dlgHexagon.setPoint(new Point(click.getX(),click.getY()));
        dlgHexagon.setColors(color, edgeColor);
        dlgHexagon.getBtnEdgeColor().setBackground(edgeColor);
        dlgHexagon.getBtnInnerColor().setBackground(innerColor);
        dlgHexagon.setVisible(true);
        
        if(dlgHexagon.getHexagon() != null)
        {
            HexagonAdapter hexagon = dlgHexagon.getHexagon();
            CmdAddShape cas = new CmdAddShape(hexagon,model);
            cas.execute();
            consoleLog.addElement(cas.sendToLog());
            addCommandsToList(cas);
            hexagon.addObserver(new buttonSwitch(model,frame));
        }
        return;
            
        }
    
    public void drawDonut(MouseEvent click) {
        DlgDonut dlgDonut = new DlgDonut();
        dlgDonut.setPoint(new Point(click.getX(),click.getY()));
        dlgDonut.setColors(edgeColor, innerColor);
        dlgDonut.getBtnEdgeColor().setBackground(edgeColor);
        dlgDonut.getBtnInnerColor().setBackground(innerColor);
        dlgDonut.setVisible(true);
        
        if(dlgDonut.getDonut() != null) {
            Donut donut = dlgDonut.getDonut();
            CmdAddShape cas = new CmdAddShape(donut,model);
            cas.execute();
            consoleLog.addElement(cas.sendToLog());
            addCommandsToList(cas);
            donut.addObserver(new buttonSwitch(model,frame));
        }
        return;
    }
    
    
    public void selectEdgeColor()
    {
        Color pickedColor = JColorChooser.showDialog(null, "Choose edge color:", edgeColor);
        if(pickedColor != null)
        {
            edgeColor = pickedColor;
            frame.getBtnEdgeColor().setBackground(edgeColor);
        }
    }
    
    public void selectInnerColor()
    {
        Color pickedColor = JColorChooser.showDialog(null, "Choose inner color:", innerColor);
        if(pickedColor != null)
        {
            innerColor = pickedColor;
            frame.getbtnColor().setBackground(innerColor);
        }
    }
    
    
    public void removeSelected() {
        shapes.removeIf(shape -> shape.isSelected());
        frame.repaint();
    }
    
   
    public void select(MouseEvent e) {
        boolean flag = false;
        
    
        
        for (int i = model.getShapes().size()-1; i >= 0; i--) {
            if (model.getShape(i).contains(e.getX(), e.getY())) {
                if(!model.getShape(i).isSelected()) {
                    
                
                CmdSelect cmd =new CmdSelect(model.getShape(i), model);
                cmd.execute();
                consoleLog.addElement(cmd.sendToLog());
                addCommandsToList(cmd);
                flag = true;
                return;
                
                
            }
                else {
                    CmdDeselect cmd_deselect = new CmdDeselect(model.getShape(i), model);
                    cmd_deselect.execute();
                    consoleLog.addElement(cmd_deselect.sendToLog());
                    addCommandsToList(cmd_deselect);                    
                    flag  = true;
                    return;
                }
                
            }
        }
        
        
        if(flag == false) {
            ArrayList<Shape> selectedShapes = new ArrayList<>();
            for (Shape shape : model.getShapes()) {
                if(shape.isSelected()) {
                    
                    selectedShapes.add(shape);
                }
            }
            if(!selectedShapes.isEmpty()) {
            CmdDeselectAll cmd_deselectAll = new CmdDeselectAll(selectedShapes, model);
            cmd_deselectAll.execute();
            consoleLog.addElement(cmd_deselectAll.sendToLog());
            addCommandsToList(cmd_deselectAll);
            }
        }
        
        
 
        }
    
    public int getSelected() {
        for (i = model.getShapes().size()-1; i >= 0; i--) {
            if (model.getShape(i).isSelected()) {
                return i;
            }
        }
        return -1;
    }
    
    public void modifyShape() {
        int index = getSelected();
        System.out.println(index);
        if (index == -1) return;
        
        Shape shape = model.getShape(index);
        System.out.println(shape.toString());
        
        if (shape instanceof Point) {
            Point oldState = (Point) shape;
            DlgPoint dlgPoint = new DlgPoint();
            dlgPoint.setPoint((Point)shape);
            dlgPoint.getBtnEdgeColor().setBackground(shape.getColor());
            dlgPoint.setVisible(true);
            
            if(dlgPoint.getPoint() != null) {
                Point newState = dlgPoint.getPoint();
                CmdModifyPoint cmp = new CmdModifyPoint(oldState, newState);
                cmp.execute();
                consoleLog.addElement(cmp.sendToLog());
                addCommandsToList(cmp);
                frame.repaint();
            }
        } else if (shape instanceof Line) {
            Line oldState = (Line) shape;
            DlgLine dlgLine = new DlgLine();
            dlgLine.setLine((Line)shape);
            dlgLine.getBtnColor().setBackground(shape.getColor());
            dlgLine.setVisible(true);
            
            if(dlgLine.getLine() != null) {
                Line newState = dlgLine.getLine();
                CmdModifyLine cml = new CmdModifyLine(oldState, newState);
                cml.execute();
                consoleLog.addElement(cml.sendToLog());
                addCommandsToList(cml);
                frame.repaint();
            }
        } else if (shape instanceof Rectangle) {
            Rectangle oldState = (Rectangle) shape;
            DlgRectangle dlgRectangle = new DlgRectangle();
            dlgRectangle.setRectangle((Rectangle)shape);
            dlgRectangle.getBtnEdgeColor().setBackground(oldState.getColor());
            dlgRectangle.getBtnInnerColor().setBackground(oldState.getInnerColor());
            dlgRectangle.setVisible(true);
            
            if(dlgRectangle.getRectangle() != null) {
                Rectangle newState = dlgRectangle.getRectangle();
                CmdModifyRectangle cmr = new CmdModifyRectangle(oldState, newState);
                cmr.execute();
                consoleLog.addElement(cmr.sendToLog());
                addCommandsToList(cmr);
                frame.repaint();
            }
        
        }else if (shape instanceof Donut) {
                Donut oldState = (Donut) shape;
                DlgDonut dlgDonut = new DlgDonut();
                dlgDonut.setDonut((Donut)shape);
                dlgDonut.getBtnEdgeColor().setBackground(oldState.getColor());
                dlgDonut.getBtnInnerColor().setBackground(oldState.getInnerColor());
                dlgDonut.setVisible(true);
                
                if(dlgDonut.getDonut() != null) {
                    Donut newState = dlgDonut.getDonut();
                    CmdModifyDonut cmd = new CmdModifyDonut(oldState, newState);
                    cmd.execute();
                    consoleLog.addElement(cmd.sendToLog());
                    addCommandsToList(cmd);
                    frame.repaint();
                }
        } else if (shape instanceof Circle) {
            Circle oldState = (Circle) shape;
            DlgCircle dlgCircle = new DlgCircle();
            dlgCircle.setCircle((Circle)shape);
            dlgCircle.getBtnEdgeColor().setBackground(oldState.getColor());
            dlgCircle.getBtnInnerColor().setBackground(oldState.getInnerColor());
            dlgCircle.setVisible(true);
            
            if(dlgCircle.getCircle() != null) {
                Circle newState = dlgCircle.getCircle();
                CmdModifyCircle cmc = new CmdModifyCircle(oldState, newState);
                cmc.execute();
                consoleLog.addElement(cmc.sendToLog());
                addCommandsToList(cmc);
                frame.repaint();
            }
        }
        else if (shape instanceof HexagonAdapter) {
            HexagonAdapter oldState = (HexagonAdapter) shape;
            DlgHexagon dlgHexagon = new DlgHexagon();
            dlgHexagon.setHexagon((HexagonAdapter)shape);
            dlgHexagon.getBtnEdgeColor().setBackground(oldState.getEdgeColor());
            dlgHexagon.getBtnInnerColor().setBackground(oldState.getInnerColor());
            dlgHexagon.setVisible(true);
            
            if(dlgHexagon.getHexagon() != null) {
                HexagonAdapter newState = dlgHexagon.getHexagon();
                CmdModifyHexagon cmh = new CmdModifyHexagon(oldState, newState);
                cmh.execute();
                consoleLog.addElement(cmh.sendToLog());
                addCommandsToList(cmh);
                frame.repaint();
            }
        }
        
    }
    
    public void delete() {
        ArrayList<Shape> shapes = new ArrayList<>();
        if(frame.getBtnActionDelete().isEnabled()) {
            if(JOptionPane.showConfirmDialog(null, "Are you sure you want to remove selected shape?", "Warning!", JOptionPane.YES_NO_OPTION) == 0) {
                for(int i =0;i<model.getShapes().size();i++) {
                    
                    if(model.getShape(i).isSelected()) {
                            
                        shapes.add(model.getShape(i));
                        model.getShape(i).setSelected(false);
                        
                    }
                    
                }
                CmdRemoveShape crs = new CmdRemoveShape(shapes, model);
                crs.execute();
                consoleLog.addElement(crs.sendToLog());
                addCommandsToList(crs);          
            }          
    }
        }
    
    public void toFront() {
        for (Shape shape : model.getShapes()) {
            if (shape.isSelected()) {
                int index = model.getIndexOfShape(shape);
                if(index==model.getShapes().size()-1) {
                     JOptionPane.showMessageDialog(null, "Already done. Shape is in front!", "Error!", JOptionPane.ERROR_MESSAGE);
                        return;
                 }
                CmdToFront ctf = new CmdToFront(shape, model);
                ctf.execute();
                consoleLog.addElement(ctf.sendToLog());
                addCommandsToList(ctf);
                
                break;
            }
        }
    }
    
    public void toBack() {
        for (Shape shape : model.getShapes()) {
            if (shape.isSelected()) {
                int index = model.getIndexOfShape(shape);
                if(index==0) {
                     JOptionPane.showMessageDialog(null, "Already done. Shape has back position!", "Error!", JOptionPane.ERROR_MESSAGE);
                        return;
                 }
                CmdToBack ctb = new CmdToBack(shape, model);
                ctb.execute();
                consoleLog.addElement(ctb.sendToLog());
                addCommandsToList(ctb);
                break;
            }
        }
    }
    
    public void bringToBack() {
        for (Shape shape : model.getShapes()) {
            if (shape.isSelected()) {
                int index = model.getIndexOfShape(shape);
                if(index==0) {
                     JOptionPane.showMessageDialog(null, "Already done. Shape has back position", "Error!", JOptionPane.ERROR_MESSAGE);
                        return;
                 }
                CmdBringToBack cbtb = new CmdBringToBack(model,shape);
                cbtb.execute();
                consoleLog.addElement(cbtb.sendToLog());
                addCommandsToList(cbtb);
                break;
            }
        }
    }
    
    public void bringToFront() {
        for (Shape shape : model.getShapes()) {
            if (shape.isSelected()) {
                int index = model.getIndexOfShape(shape);
                if(index==model.getShapes().size()-1) {
                     JOptionPane.showMessageDialog(null, "Already done. Shape is in front!", "Error!", JOptionPane.ERROR_MESSAGE);
                        return;
                 }
                CmdBringToFront cbtf = new CmdBringToFront(model,shape);
                cbtf.execute();
                consoleLog.addElement(cbtf.sendToLog());
                addCommandsToList(cbtf);
                break;
            }
        }
    }
    
    
    public void addCommandsToList(Command command) {
        if(frame.getBtnRedo().isEnabled()){
            historyList.subList(historyListIndex+1, historyList.size()).clear();
            historyList.trimToSize();
            System.out.println(historyList.size());
            frame.getBtnRedo().setEnabled(false);
        }
        historyList.add(command);
        //historyList.subList(historyListIndex+1, historyList.size()).clear();
        System.out.println(historyList.size());
        
        
        historyListIndex = historyList.size() - 1;
        
        frame.getBtnUndo().setEnabled(true);
        
        
    }
    
    
    public void undo() {
        frame.getBtnRedo().setEnabled(true);
        historyList.get(historyListIndex).unexecute(); 
        consoleLog.addElement("undo:" + historyList.get(historyListIndex).sendToLog());
        historyListIndex--; 
        if (historyListIndex < 0) { 
            frame.getBtnUndo().setEnabled(false);
        }
    }
    
    
    public void redo() {
        historyListIndex++; 
        historyList.get(historyListIndex).execute();
        consoleLog.addElement("redo:" + historyList.get(historyListIndex).sendToLog());
        frame.getBtnUndo().setEnabled(true);
        if (historyListIndex == historyList.size() - 1) {
            frame.getBtnRedo().setEnabled(false);
            
        }
    }
    
    
    public void saveDraw() { 
 
        SaveManager draw = new SaveManager(new SaveDraw(model.getShapes()));
        draw.save();
            
        
    }
    
    public void saveLog() { 
 
        SaveManager log = new SaveManager(new SaveLog(frame.getDefaultListModel()));
        log.save();
            
        
    }
    
    public void openDraw() throws IOException, ClassNotFoundException {
         
         JFileChooser openDraw = new JFileChooser();
         openDraw.setCurrentDirectory(new java.io.File("C:\\Users\\DELL\\Documents"));
         openDraw.setDialogTitle("Choose file to open desired draw");
         
         if (openDraw.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) { 
            
            File file = (openDraw.getSelectedFile());
            
             if(file.getName().toLowerCase().endsWith(".ser")){
                 
            FileInputStream fileInput = new FileInputStream(file);
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            model.getShapes().clear();
            ArrayList<Shape> shapes = (ArrayList<Shape>) objectInput.readObject();
            
            for (int i = 0; i < shapes.size(); i++) {
                model.add(shapes.get(i));
                
            }
            frame.repaint();
            
            objectInput.close();
            fileInput.close();
            
            } else {
                JOptionPane.showMessageDialog(null, "Wrong file extension choosen", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
        } else {
            return;
        } 
     }
    
    
    public void openLog() throws IOException {
        JFileChooser openLog = new JFileChooser();
 
        if (openLog.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = (openLog.getSelectedFile());
 
            if (file.getAbsolutePath().endsWith(".txt")) {
                model.getShapes().clear();
                
 
                fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);
 
                
    }
        }
        else
        {
            return;
        }
    }
    
    
    public void nextCommand() {
       
 
        String line;
        try {
            if ((line = bufferedReader.readLine()) != null) { 
 
                
                
                if (line.equals("deselectAllShapes")){
                    ArrayList<Shape> selectedShapes = new ArrayList<>();
                    for (Shape shape : model.getShapes()) {
                        if(shape.isSelected()) {
                            
                            selectedShapes.add(shape);
                        }
                    }
                    if(!selectedShapes.isEmpty()) {
                    CmdDeselectAll cmd_deselectAll = new CmdDeselectAll(selectedShapes, model);
                    cmd_deselectAll.execute();
                    consoleLog.addElement(cmd_deselectAll.sendToLog());
                    addCommandsToList(cmd_deselectAll);
                    frame.repaint();
                    }
                    
                    return;
                }
                String[] lineElements = line.split(":");
                if(lineElements[1].equals("deselectAllShapes")) {
                    if (lineElements[0].equals("undo") || lineElements[0].equals("redo")) { 
                        String command = line.substring(5);
                        for (Command c : historyList) {
                            if (c.sendToLog().equals(command)) {
                                if (lineElements[0].equals("undo"))
                                    undo();
                                else
                                    redo();
                            }
                            frame.repaint();
                        }
                        return;
                    }
                }
                 String valuesLine = lineElements[2].replaceAll("[^0-9,.]", "");
                String[] values = valuesLine.split(",");
                
                
                System.out.println(lineElements[0]);
                if (lineElements[0].equals("added")) {
                    System.out.println(lineElements[0]);
                    if (lineElements[1].equals("Point")) {
                        Point point = new Point(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                        point.setColor(parseColor(values[2]));
                        CmdAddShape cas = new CmdAddShape(point,model);
                        cas.execute();
                        frame.repaint();
                        consoleLog.addElement(cas.sendToLog());
                        addCommandsToList(cas);
                    } else if (lineElements[1].equals("Line")) {
                        Point start = new Point(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                        Point end = new Point(Integer.parseInt(values[2]), Integer.parseInt(values[3]));
 
                        Line l = new Line(start, end,false, parseColor(values[4]));
                        CmdAddShape cas = new CmdAddShape(l,model);
                        cas.execute();
                        frame.repaint();
 
                        consoleLog.addElement(cas.sendToLog());
                        addCommandsToList(cas);
 
                        
                    } else if (lineElements[1].equals("Rectangle")) {
                        Point upperLeft = new Point(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                        int height = Integer.parseInt(values[2]);
                        int width = Integer.parseInt(values[3]);
 
                        Rectangle r = new Rectangle(upperLeft, width, height,false, parseColor(values[4]),
                                parseColor(values[5]));
                        CmdAddShape cas = new CmdAddShape(r,model);
                        cas.execute();
                        frame.repaint();
 
                        consoleLog.addElement(cas.sendToLog());
                        addCommandsToList(cas);
                    }  else if (lineElements[1].equals("Circle")) {
 
                        Point center = new Point(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                        int radius = Integer.parseInt(values[2]);
 
                        Circle circle=
                                new Circle(center, radius,false, parseColor(values[3]), parseColor(values[4]));
                        CmdAddShape cas = new CmdAddShape(circle,model);
                        cas.execute();
                        frame.repaint();
 
                        consoleLog.addElement(cas.sendToLog());
                        addCommandsToList(cas);
                    }
                    else if (lineElements[1].equals("Donut")) {
                         
                        Point center = new Point(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                        int radius = Integer.parseInt(values[2]);
                        int innerRadius = Integer.parseInt(values[3]);
 
                        Donut donut=
                                new Donut(center, radius,innerRadius, false, parseColor(values[4]), parseColor(values[5]));
                        CmdAddShape cas = new CmdAddShape(donut,model);
                        cas.execute();
                        frame.repaint();
 
                        consoleLog.addElement(cas.sendToLog());
                        addCommandsToList(cas);
                    } 
                    else if (lineElements[1].equals("Hexagon")) {
                        int x = Integer.parseInt(values[0]);
                        int y = Integer.parseInt(values[1]);
                        int r = Integer.parseInt(values[2]);
                        HexagonAdapter hexagonAdapter =
                                new HexagonAdapter(new Point(x, y), r, parseColor(values[4]), parseColor(values[3]),false);
                    
                        CmdAddShape cas = new CmdAddShape(hexagonAdapter,model);
                        cas.execute();
                        frame.repaint();
 
                        consoleLog.addElement(cas.sendToLog());
                        addCommandsToList(cas);
                        }
 
                } else if (lineElements[0].equals("select")) {
                    if (lineElements[1].equals("Point")) {
                        Point point = new Point(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                        point.setColor(parseColor(values[2]));
                               
                        CmdSelect cas = new CmdSelect(point,model);
                        cas.execute();
                        frame.repaint();
                        consoleLog.addElement(cas.sendToLog());
                        addCommandsToList(cas);
                    } 
                    else if (lineElements[1].equals("Line")) {
                        Point start = new Point(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                        Point end = new Point(Integer.parseInt(values[2]), Integer.parseInt(values[3]));
 
                        Line l = new Line(start, end,false, parseColor(values[4]));
                        CmdSelect cas = new CmdSelect(l,model);
                        cas.execute();
                        frame.repaint();
 
                        consoleLog.addElement(cas.sendToLog());
                        addCommandsToList(cas);                      
                    }
                    else if (lineElements[1].equals("Rectangle")) {
                        Point upperLeft = new Point(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                        int height = Integer.parseInt(values[2]);
                        int width = Integer.parseInt(values[3]);
 
                        Rectangle r = new Rectangle(upperLeft, width, height,false, parseColor(values[4]),
                                parseColor(values[5]));
                        CmdSelect cas = new CmdSelect(r,model);
                        cas.execute();
                        frame.repaint();
 
                        consoleLog.addElement(cas.sendToLog());
                        addCommandsToList(cas);
                    }  else if (lineElements[1].equals("Circle")) {
 
                        Point center = new Point(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                        int radius = Integer.parseInt(values[2]);
 
                        Circle circle=
                                new Circle(center, radius,false, parseColor(values[3]), parseColor(values[4]));
                        CmdSelect cas = new CmdSelect(circle,model);
                        cas.execute();
                        frame.repaint();
 
                        consoleLog.addElement(cas.sendToLog());
                        addCommandsToList(cas);
                    }
                    else if (lineElements[1].equals("Donut")) {
                         
                        Point center = new Point(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                        int radius = Integer.parseInt(values[2]);
                        int innerRadius = Integer.parseInt(values[3]);
 
                        Donut donut=
                                new Donut(center, radius,innerRadius, false, parseColor(values[4]), parseColor(values[5]));
                        CmdSelect cas = new CmdSelect(donut,model);
                        cas.execute();
                        frame.repaint();
 
                        consoleLog.addElement(cas.sendToLog());
                        addCommandsToList(cas);
                    } 
                    else if (lineElements[1].equals("Hexagon")) {
                        int x = Integer.parseInt(values[0]);
                        int y = Integer.parseInt(values[1]);
                        int r = Integer.parseInt(values[2]);
                        HexagonAdapter hexagonAdapter =
                                new HexagonAdapter(new Point(x, y), r, parseColor(values[4]), parseColor(values[3]),false);
                    
                        CmdSelect cas = new CmdSelect(hexagonAdapter,model);
                        cas.execute();
                        frame.repaint();
 
                        consoleLog.addElement(cas.sendToLog());
                        addCommandsToList(cas);
                        }
                    
                    
                }
                else if (lineElements[0].equals("deselect")) {
                    if (lineElements[1].equals("Point")) {
                        Point point = new Point(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                        point.setColor(parseColor(values[2]));
                               
                        CmdDeselect cas = new CmdDeselect(point,model);
                        cas.execute();
                        frame.repaint();
                        consoleLog.addElement(cas.sendToLog());
                        addCommandsToList(cas);
                    } 
                    else if (lineElements[1].equals("Line")) {
                        Point start = new Point(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                        Point end = new Point(Integer.parseInt(values[2]), Integer.parseInt(values[3]));
 
                        Line l = new Line(start, end,false, parseColor(values[4]));
                        CmdDeselect cas = new CmdDeselect(l,model);
                        cas.execute();
                        frame.repaint();
 
                        consoleLog.addElement(cas.sendToLog());
                        addCommandsToList(cas);                      
                    }
                    else if (lineElements[1].equals("Rectangle")) {
                        Point upperLeft = new Point(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                        int height = Integer.parseInt(values[2]);
                        int width = Integer.parseInt(values[3]);
 
                        Rectangle r = new Rectangle(upperLeft, height, width,false, parseColor(values[4]),
                                parseColor(values[5]));
                        CmdDeselect cas = new CmdDeselect(r,model);
                        cas.execute();
                        frame.repaint();
 
                        consoleLog.addElement(cas.sendToLog());
                        addCommandsToList(cas);
                    }  else if (lineElements[1].equals("Circle")) {
 
                        Point center = new Point(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                        int radius = Integer.parseInt(values[2]);
 
                        Circle circle=
                                new Circle(center, radius,false, parseColor(values[3]), parseColor(values[4]));
                        CmdDeselect cas = new CmdDeselect(circle,model);
                        cas.execute();
                        frame.repaint();
 
                        consoleLog.addElement(cas.sendToLog());
                        addCommandsToList(cas);
                    }
                    else if (lineElements[1].equals("Donut")) {
                         
                        Point center = new Point(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                        int radius = Integer.parseInt(values[2]);
                        int innerRadius = Integer.parseInt(values[3]);
 
                        Donut donut=
                                new Donut(center, radius,innerRadius, false, parseColor(values[4]), parseColor(values[5]));
                        CmdDeselect cas = new CmdDeselect(donut,model);
                        cas.execute();
                        frame.repaint();
 
                        consoleLog.addElement(cas.sendToLog());
                        addCommandsToList(cas);
                    } 
                    else if (lineElements[1].equals("Hexagon")) {
                        int x = Integer.parseInt(values[0]);
                        int y = Integer.parseInt(values[1]);
                        int r = Integer.parseInt(values[2]);
                        HexagonAdapter hexagonAdapter =
                                new HexagonAdapter(new Point(x, y), r, parseColor(values[4]), parseColor(values[3]),false);
                    
                        CmdDeselect cas = new CmdDeselect(hexagonAdapter,model);
                        cas.execute();
                        frame.repaint();
 
                        consoleLog.addElement(cas.sendToLog());
                        addCommandsToList(cas);
                        }
                    
                    
                }else if (lineElements[0].equals("modify")) {
                   
                    String newValuesLine = lineElements[3].replaceAll("[^0-9,.]", "");
                    String[] newValues = newValuesLine.split(",");
 
                    if (lineElements[1].equals("Point")) {
                
                        Point oldState = new Point(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                         oldState.setColor(parseColor(values[2]));
                        Point newState = new Point(Integer.parseInt(newValues[0]), Integer.parseInt(newValues[1]));
                        newState.setColor(parseColor(newValues[2]));
 
                        for (Shape s : model.getShapes()) {
                            if (s.equals(oldState)) {
                                CmdModifyPoint cmp = new CmdModifyPoint((Point)s, newState);
                                cmp.execute();
                                consoleLog.addElement(cmp.sendToLog());
                                addCommandsToList(cmp);
                                frame.repaint();
                                break;
                            }
                        }
                    } else if (lineElements[1].equals("Line")) {
 
                        Line oldState = new Line(new Point(Integer.parseInt(values[0]), Integer.parseInt(values[1])),
                                new Point(Integer.parseInt(values[2]), Integer.parseInt(values[3])),true,
                                parseColor(values[4]));
                        Line newState = new Line(
                                new Point(Integer.parseInt(newValues[0]), Integer.parseInt(newValues[1])),
                                new Point(Integer.parseInt(newValues[2]), Integer.parseInt(newValues[3])),true,
                                parseColor(newValues[4]));
 
                        for (Shape s : model.getShapes()) {
                            if (s.equals(oldState)) {
                                CmdModifyLine cml = new CmdModifyLine((Line)s, newState);
                                cml.execute();
                                consoleLog.addElement(cml.sendToLog());
                                addCommandsToList(cml);
                                frame.repaint();
                                break;
                            }
                        }
                    }  else if (lineElements[1].equals("Rectangle")) {
                        Rectangle oldState = new Rectangle(
                                new Point(Integer.parseInt(values[0]), Integer.parseInt(values[1])),
                                Integer.parseInt(values[3]), Integer.parseInt(values[2]),true, parseColor(values[4]),
                                parseColor(values[5]));
                        Rectangle newState = new Rectangle(
                                new Point(Integer.parseInt(newValues[0]), Integer.parseInt(newValues[1])),
                                Integer.parseInt(newValues[3]), Integer.parseInt(newValues[2]),true,
                                parseColor(newValues[4]), parseColor(newValues[5]));
 
                        for (Shape s : model.getShapes()) {
                            if (s.equals(oldState)) {
                                CmdModifyRectangle cmr = new CmdModifyRectangle((Rectangle)s, newState);
                                cmr.execute();
                                consoleLog.addElement(cmr.sendToLog());
                                addCommandsToList(cmr);
                                frame.repaint();
                                break;
                            }
                        }
                    } else if (lineElements[1].equals("Circle")) {
 
                        Circle oldState = new Circle(
                                new Point(Integer.parseInt(values[0]), Integer.parseInt(values[1])),
                                Integer.parseInt(values[2]),true, parseColor(values[3]), parseColor(values[4]));
                        Circle newState = new Circle(
                                new Point(Integer.parseInt(newValues[0]), Integer.parseInt(newValues[1])),
                                Integer.parseInt(newValues[2]),true, parseColor(newValues[3]), parseColor(newValues[4]));
 
                        for (Shape s : model.getShapes()) {
                            if (s.equals(oldState)) {
                                CmdModifyCircle cmc = new CmdModifyCircle((Circle)s, newState);
                                cmc.execute();
                                consoleLog.addElement(cmc.sendToLog());
                                addCommandsToList(cmc);
                                frame.repaint();
                                break;
                            }
                        }
                    }
                    else if (lineElements[1].equals("Donut")) {
                         
                        Donut oldState = new Donut(
                                new Point(Integer.parseInt(values[0]), Integer.parseInt(values[1])),
                                Integer.parseInt(values[2]),Integer.parseInt(values[3]), true, parseColor(values[4]), parseColor(values[5]));
                        Donut newState = new Donut(
                                new Point(Integer.parseInt(newValues[0]), Integer.parseInt(newValues[1])),
                                Integer.parseInt(newValues[2]),Integer.parseInt(newValues[3]), true,  parseColor(newValues[4]), parseColor(newValues[5]));
 
                        for (Shape s : model.getShapes()) {
                            if (s.equals(oldState)) {
                                CmdModifyDonut cmd = new CmdModifyDonut((Donut)s, newState);
                                cmd.execute();
                                consoleLog.addElement(cmd.sendToLog());
                                addCommandsToList(cmd);
                                frame.repaint();
                                break;
                            }
                        }
                    } 
                    else if (lineElements[1].equals("Hexagon")) {
 
                        HexagonAdapter oldState = new HexagonAdapter(new Point(Integer.parseInt(values[0]),
                                Integer.parseInt(values[1])), Integer.parseInt(values[2]), parseColor(values[3]),
                                parseColor(newValues[4]),true);
                        HexagonAdapter newState = new HexagonAdapter(new Point(Integer.parseInt(newValues[0]),
                                Integer.parseInt(newValues[1])), Integer.parseInt(newValues[2]),
                                parseColor(newValues[3]), parseColor(newValues[4]),true);
 
                        for (Shape s : model.getShapes()) {
 
                            if (s.equals(oldState)) {
                                CmdModifyHexagon cmh = new CmdModifyHexagon((HexagonAdapter)s, newState);
                                cmh.execute();
                                consoleLog.addElement(cmh.sendToLog());
                                addCommandsToList(cmh);
                                frame.repaint();
                                break;
                            }
                        }
                    }
                }
                else if (lineElements[0].equals("remove")) {
                    
                    ArrayList<Shape> shapesToDelete = new ArrayList<Shape>();
 
                    for (int i = 1; i < lineElements.length; i++) {
                        if (lineElements[i].equals("Point")) {
                            String shapeValues = lineElements[i+1].replaceAll("[^0-9,.]", "");
                            String[] sValues = shapeValues.split(",");
 
                            Point p = new Point(Integer.parseInt(sValues[0]), Integer.parseInt(sValues[1]));
                                   p.setColor(parseColor(sValues[2]));
                            shapesToDelete.add(p);
                        } else if (lineElements[i].equals("Line")) {
                            String shapeValues = lineElements[i+1].replaceAll("[^0-9,.]", "");
                            String[] sValues = shapeValues.split(",");
                            System.out.println(sValues[0]);
 
                            Point start = new Point(Integer.parseInt(sValues[0]), Integer.parseInt(sValues[1]));
                            Point end = new Point(Integer.parseInt(sValues[2]), Integer.parseInt(sValues[3]));
                            Color foreground = parseColor(sValues[4]);
 
                            Line l = new Line(start, end);
                            l.setColor(foreground);
                            shapesToDelete.add(l);
                        } else if (lineElements[i].equals("Rectangle")) {
                            String shapeValues = lineElements[i + 1].replaceAll("[^0-9,.]", "");
                            String[] sValues = shapeValues.split(",");
 
                            Point start = new Point(Integer.parseInt(sValues[0]), Integer.parseInt(sValues[1]));
                            int height = Integer.parseInt(sValues[2]);
                            int width = Integer.parseInt(sValues[3]);
                            Color foreground = parseColor(sValues[4]);
                            Color background = parseColor(sValues[5]);
 
                            Rectangle r = new Rectangle(start, height, width, true,foreground, background);
                            shapesToDelete.add(r);
                        } else if (lineElements[i].equals("Circle")) {
                            String shapeValues = lineElements[i + 1].replaceAll("[^0-9,.]", "");
                            String[] sValues = shapeValues.split(",");
 
                            Point center = new Point(Integer.parseInt(sValues[0]), Integer.parseInt(sValues[1]));
                            int radius = Integer.parseInt(sValues[2]);
                            Color foreground = parseColor(sValues[3]);
                            Color background = parseColor(sValues[4]);
 
                            Circle c = new Circle(center, radius,true, foreground, background);
                            shapesToDelete.add(c);
                        }
                        else if (lineElements[i].equals("Donut")) {
                            String shapeValues = lineElements[i + 1].replaceAll("[^0-9,.]", "");
                            String[] sValues = shapeValues.split(",");
 
                            Point center = new Point(Integer.parseInt(sValues[0]), Integer.parseInt(sValues[1]));
                            int radius = Integer.parseInt(sValues[2]);
                            int innerRadius = Integer.parseInt(sValues[3]);
                            Color foreground = parseColor(sValues[4]);
                            Color background = parseColor(sValues[5]);
 
                            Donut d = new Donut(center, radius,innerRadius, true, foreground, background);
                            shapesToDelete.add(d);
                        }
                        else if (lineElements[i].equals("Hexagon")) {
                            String shapeValues = lineElements[i + 1].replaceAll("[^0-9,.]", "");
                            String[] sValues = shapeValues.split(",");
 
                            int radius = Integer.parseInt(sValues[2]);
                            Color foreground = parseColor(sValues[3]);
                            Color background = parseColor(sValues[4]);
 
                            HexagonAdapter h = new HexagonAdapter(new Point(Integer.parseInt(sValues[0]),
                                    Integer.parseInt(sValues[1])), Integer.parseInt(sValues[2]), foreground, background,true);
                            shapesToDelete.add(h);
                        }
                    }
 
                    CmdRemoveShape crs = new CmdRemoveShape(shapesToDelete, model);
                    System.out.println(shapesToDelete.toString());
                    crs.execute();
                    frame.repaint();
                    consoleLog.addElement(crs.sendToLog());
                    addCommandsToList(crs);          
 
                } else if ((lineElements[0].equals("toBack")) || (lineElements[0].equals("toFront"))
                        || (lineElements[0].equals("bringToBack")) || (lineElements[0].equals("bringToFront"))) {
                    System.out.println(lineElements[1]);
                    if (lineElements[1].equals("Point")) {
                        Color foreground = parseColor(values[2]);
                        Point p = new Point(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                       p.setColor(foreground);
   
                                if (lineElements[0].equals("toBack")){
                                CmdToBack ctb = new CmdToBack(p, model);
                                ctb.execute();
                                consoleLog.addElement(ctb.sendToLog());
                                addCommandsToList(ctb);
                                }
                                else if (lineElements[0].equals("toFront"))
                                {
                                    CmdToFront ctf = new CmdToFront(p, model);
                                    ctf.execute();
                                    consoleLog.addElement(ctf.sendToLog());
                                    addCommandsToList(ctf);
                                }
                                else if (lineElements[0].equals("bringToBack"))
                                {
                                    CmdBringToBack cbtb = new CmdBringToBack(model, p);
                                    cbtb.execute();
                                    consoleLog.addElement(cbtb.sendToLog());
                                    addCommandsToList(cbtb);
                                }
                                else if (lineElements[0].equals("bringToFront"))
                                {
                                    CmdBringToBack cbtf = new CmdBringToBack(model, p);
                                    cbtf.execute();
                                    consoleLog.addElement(cbtf.sendToLog());
                                    addCommandsToList(cbtf);
                                }
                          
                    } else if (lineElements[1].equals("Line")) {
 
                         Point start = new Point(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                         Point end = new Point(Integer.parseInt(values[2]), Integer.parseInt(values[3]));
  
                         Line l = new Line(start, end,false, parseColor(values[4]));
 
                   
                            if (lineElements[0].equals("toBack")){
                                CmdToBack ctb = new CmdToBack(l, model);
                                ctb.execute();
                                consoleLog.addElement(ctb.sendToLog());
                                addCommandsToList(ctb);
                                }
                                else if (lineElements[0].equals("toFront"))
                                {
                                    CmdToFront ctf = new CmdToFront(l, model);
                                    ctf.execute();
                                    consoleLog.addElement(ctf.sendToLog());
                                    addCommandsToList(ctf);
                                }
                                else if (lineElements[0].equals("bringToBack"))
                                {
                                    CmdBringToBack cbtb = new CmdBringToBack(model, l);
                                    cbtb.execute();
                                    consoleLog.addElement(cbtb.sendToLog());
                                    addCommandsToList(cbtb);
                                }
                                else if (lineElements[0].equals("bringToFront"))
                                {
                                    CmdBringToFront cbtf = new CmdBringToFront(model, l);
                                    cbtf.execute();
                                    consoleLog.addElement(cbtf.sendToLog());
                                    addCommandsToList(cbtf);
                                }       
                            frame.repaint();
                        }
                     else if (lineElements[1].equals("Rectangle")) {
                        System.out.println("alo bre");
                        Point start = new Point(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                        int height = Integer.parseInt(values[2]);
                        int width = Integer.parseInt(values[3]);
                        Color outline = parseColor(values[4]);
                        Color inside = parseColor(values[5]);
 
                        Rectangle r = new Rectangle(start, height, width, true,outline, inside);
                        System.out.println(r.toString());
                       
                                if (lineElements[0].equals("toBack")){
                                    CmdToBack ctb = new CmdToBack(r, model);
                                    ctb.execute();
                                    consoleLog.addElement(ctb.sendToLog());
                                    addCommandsToList(ctb);
                                    }
                                    else if (lineElements[0].equals("toFront"))
                                    {
                                        CmdToFront ctf = new CmdToFront(r, model);
                                        ctf.execute();
                                        consoleLog.addElement(ctf.sendToLog());
                                        addCommandsToList(ctf);
                                    }
                                    else if (lineElements[0].equals("bringToBack"))
                                    {
                                        CmdBringToBack cbtb = new CmdBringToBack(model, r);
                                        cbtb.execute();
                                        consoleLog.addElement(cbtb.sendToLog());
                                        addCommandsToList(cbtb);
                                    }
                                    else if (lineElements[0].equals("bringToFront"))
                                    {
                                        CmdBringToFront cbtf = new CmdBringToFront(model, r);
                                        cbtf.execute();
                                        consoleLog.addElement(cbtf.sendToLog());
                                        addCommandsToList(cbtf);
                                    }
                                    frame.repaint();
                                   
                       
                    } else if (lineElements[1].equals("Circle")) {
 
                        Point center = new Point(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                        int radius = Integer.parseInt(values[2]);
                        Color outline = parseColor(values[3]);
                        Color inside = parseColor(values[4]);
 
                        Circle c = new Circle(center, radius, true,outline, inside);
 
                        
                                if (lineElements[0].equals("toBack")){
                                    CmdToBack ctb = new CmdToBack(c, model);
                                    ctb.execute();
                                    consoleLog.addElement(ctb.sendToLog());
                                    addCommandsToList(ctb);
                                    }
                                    else if (lineElements[0].equals("toFront"))
                                    {
                                        CmdToFront ctf = new CmdToFront(c, model);
                                        ctf.execute();
                                        consoleLog.addElement(ctf.sendToLog());
                                        addCommandsToList(ctf);
                                    }
                                    else if (lineElements[0].equals("bringToBack"))
                                    {
                                        CmdBringToBack cbtb = new CmdBringToBack(model, c);
                                        cbtb.execute();
                                        consoleLog.addElement(cbtb.sendToLog());
                                        addCommandsToList(cbtb);
                                    }
                                    else if (lineElements[0].equals("bringToFront"))
                                    {
                                        CmdBringToFront cbtf = new CmdBringToFront(model, c);
                                        cbtf.execute();
                                        consoleLog.addElement(cbtf.sendToLog());
                                        addCommandsToList(cbtf);
                                    }
                                frame.repaint();
                                
                    }
                    else if (lineElements[1].equals("Donut")) {
                         
                        Point center = new Point(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                        int radius = Integer.parseInt(values[2]);
                        int innerRadius = Integer.parseInt(values[3]);
                        Color outline = parseColor(values[4]);
                        Color inside = parseColor(values[5]);
 
                        Donut d = new Donut(center, radius, innerRadius, true,outline, inside);
                        System.out.println(d.toString());
 
                     
                                if (lineElements[0].equals("toBack")){
                                    CmdToBack ctb = new CmdToBack(d, model);
                                    ctb.execute();
                                    consoleLog.addElement(ctb.sendToLog());
                                    addCommandsToList(ctb);
                                    }
                                    else if (lineElements[0].equals("toFront"))
                                    {
                                        CmdToFront ctf = new CmdToFront(d, model);
                                        ctf.execute();
                                        consoleLog.addElement(ctf.sendToLog());
                                        addCommandsToList(ctf);
                                    }
                                    else if (lineElements[0].equals("bringToBack"))
                                    {
                                        CmdBringToBack cbtb = new CmdBringToBack(model, d);
                                        cbtb.execute();
                                        consoleLog.addElement(cbtb.sendToLog());
                                        addCommandsToList(cbtb);
                                    }
                                    else if (lineElements[0].equals("bringToFront"))
                                    {
                                        CmdBringToFront cbtf = new CmdBringToFront(model, d);
                                        cbtf.execute();
                                        consoleLog.addElement(cbtf.sendToLog());
                                        addCommandsToList(cbtf);
                                    }
                                frame.repaint();
                                
                    }
                    else if (lineElements[1].equals("Hexagon")) {
 
                        int x = Integer.parseInt(values[0]);
                        int y = Integer.parseInt(values[1]);
                        int r = Integer.parseInt(values[2]);
                        HexagonAdapter h = new HexagonAdapter(new Point(x, y), r, parseColor(values[4]), parseColor(values[3]),true);
 
                        
                                if (lineElements[0].equals("toBack")){
                                    CmdToBack ctb = new CmdToBack(h, model);
                                    ctb.execute();
                                    consoleLog.addElement(ctb.sendToLog());
                                    addCommandsToList(ctb);
                                    }
                                    else if (lineElements[0].equals("toFront"))
                                    {
                                        CmdToFront ctf = new CmdToFront(h, model);
                                        ctf.execute();
                                        consoleLog.addElement(ctf.sendToLog());
                                        addCommandsToList(ctf);
                                    }
                                    else if (lineElements[0].equals("bringToBack"))
                                    {
                                        CmdBringToBack cbtb = new CmdBringToBack(model, h);
                                        cbtb.execute();
                                        consoleLog.addElement(cbtb.sendToLog());
                                        addCommandsToList(cbtb);
                                    }
                                    else if (lineElements[0].equals("bringToFront"))
                                    {
                                        CmdBringToFront cbtf = new CmdBringToFront(model, h);
                                        cbtf.execute();
                                        consoleLog.addElement(cbtf.sendToLog());
                                        addCommandsToList(cbtf);
                                    }
                         frame.repaint();
                        
                    }
                }
                 
        else if (lineElements[0].equals("undo") || lineElements[0].equals("redo")) { 
                    String command = line.substring(5);
                    for (Command c : historyList) {
                        if (c.sendToLog().equals(command)) {
                            if (lineElements[0].equals("undo"))
                                undo();
                            else
                                redo();
                        }
                        frame.repaint();
                    }
                }
 
            } else {
                frame.getBtnNextCommand().setVisible(false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public Color parseColor(String text) {
        String[] rgb = text.split("\\.");
        int red = Integer.parseInt(rgb[0]);
        int green = Integer.parseInt(rgb[1]);
        int blue = Integer.parseInt(rgb[2]);
 
        return new Color(red, green, blue);
    }
    
    
 
    
    
    
    
    
    
 
    
    
    
    
    
    
    
    
    
    }
 