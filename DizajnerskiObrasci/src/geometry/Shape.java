package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.Observable;

import mvc.DrawingModel;

public abstract class Shape extends Observable implements Moveable, Cloneable, Comparable<Object> , Serializable {

	private boolean selected;
	private Color color = Color.BLACK;
	private DrawingModel model;
	
	
	
	public Shape() {
		
	}
	
	public Shape(Color color) {
		this.color = color;
	}
	
	public Shape(Color color, boolean selected) {
		this(color);
		this.selected = selected;
	}
	
	public abstract boolean contains(int x, int y);
	public abstract void draw(Graphics g);
	
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
		setChanged();
		notifyObservers();
	}
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	
}