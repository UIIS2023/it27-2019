package adapter;

import java.awt.Color;
import java.awt.Graphics;

import geometry.Circle;
import geometry.Point;
import geometry.Shape;
import hexagon.Hexagon;


public class HexagonAdapter extends Shape {
	
	private Hexagon hexagon;
	
	public HexagonAdapter() {
		
	}
	public HexagonAdapter(Point center, int radius, Color innerColor, Color edgeColor, boolean selected)
	{
		this.hexagon = new Hexagon(center.getX(), center.getY(), radius);
		this.setEdgeColor(edgeColor);
		this.setInnerColor(innerColor);
		this.setSelected(selected);
		
	}
	
	
	@Override
	public void moveBy(int byX, int byY) {
		hexagon.setX(hexagon.getX() + byX);
		hexagon.setY(hexagon.getY() + byY);
		
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof HexagonAdapter)
			return this.getRadius() - ((HexagonAdapter) o).getRadius();
		return 0;
	}

	@Override
	public boolean contains(int x, int y) {
		if(hexagon.doesContain(x, y))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean equals(Object obj) {
		if (obj instanceof HexagonAdapter) {
			HexagonAdapter h = (HexagonAdapter) obj;
			if (this.getXCoordinate()==h.getXCoordinate()&&this.getYCoordinate()==h.getYCoordinate() &&this.getRadius()==h.getRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public void draw(Graphics g) {
		hexagon.paint(g);
		
		
	}
	
	
	public int getRadius() {
		return hexagon.getR();
	}
	
	public void setRadius(int r) {
		hexagon.setR(r);
		
	}
	
	public void setEdgeColor(Color color) {
		hexagon.setBorderColor(color);
	}
	
	public Color getEdgeColor() {
		return hexagon.getBorderColor();
	}
	
	public void setInnerColor(Color color) {
		hexagon.setAreaColor(color);
	}
	
	public Color getInnerColor() {
		return hexagon.getAreaColor();
	}
	
	public int getXCoordinate() {
		return hexagon.getX();
	}
	
	public void setXCoordinate(int x) {
		hexagon.setX(x);
	}
	
	public int getYCoordinate() {
		return hexagon.getY();
	}
	
	public void setYCoordinate(int y) {
		hexagon.setY(y);
	}
	
	public void setSelected(boolean selected) {
		hexagon.setSelected(selected);
		setChanged();
		notifyObservers();
	} 
	
	public boolean isSelected()
	
	{
		return hexagon.isSelected();
	}
	
	@Override
	public HexagonAdapter clone() {
		return new HexagonAdapter(new Point(this.getXCoordinate(), this.getYCoordinate()), 
				this.getRadius(),  this.getInnerColor(), this.getEdgeColor(), true);
		
		
	}
	
	public String toString() {
		// Center=(x,y), radius= radius
		return "Hexagon:center"+"["+this.getXCoordinate()+","+this.getYCoordinate()+"]"+",radius("+this.getRadius()+"),"+"foreground["+getEdgeColor().getRed()+"."+getEdgeColor().getGreen()+"."+getEdgeColor().getBlue()+"]"+","+"background["+getInnerColor().getRed()+"."+getInnerColor().getGreen()+"."+getInnerColor().getBlue()+"]";
	}
	
	
	
	
	
	
	
	

	

}
