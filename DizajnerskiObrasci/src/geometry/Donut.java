package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Donut extends Circle {

	private int innerRadius;
	
	public Donut() {
		
	}
	
	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius); 
		this.innerRadius = innerRadius;
	}
	 
	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		setSelected(selected);
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color) {
		this(center, radius, innerRadius, selected);
		setColor(color);
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color, Color innerColor) {
		this(center, radius, innerRadius, selected, color);
		setInnerColor(innerColor);
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Donut) {
			return (int) (this.area() - ((Donut) o).area());
		}
		return 0;
	}

	public void draw(Graphics g) {
		Ellipse2D outerCircle = new Ellipse2D.Double(this.getCenter().getX() - this.getRadius(), this.getCenter().getY() - this.getRadius(), this.getRadius()*2, this.getRadius()*2);
		Ellipse2D innerCircle = new Ellipse2D.Double(this.getCenter().getX() - this.getInnerRadius(), this.getCenter().getY() - this.getInnerRadius(), this.getInnerRadius()*2, this.getInnerRadius()*2);
		Area donut = new Area(outerCircle);
		donut.subtract(new Area(innerCircle));
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(getInnerColor());
		g2d.fill(donut);
		g2d.setColor(getColor());
		g2d.draw(donut);
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() - 3, 6, 6);
			g.drawRect(this.getCenter().getX() - getRadius() - 3, this.getCenter().getY() - 3, 6, 6);
			g.drawRect(this.getCenter().getX() + getRadius() - 3, this.getCenter().getY() - 3, 6, 6);
			g.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() - getRadius()  - 3, 6, 6);
			g.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() + getRadius() - 3, 6, 6);
		}
	}

	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		super.fill(g);
		g.setColor(Color.LIGHT_GRAY);
		g.fillOval(getCenter().getX() - this.innerRadius,
					getCenter().getY() - this.innerRadius,
					this.innerRadius * 2 - 2,
					this.innerRadius * 2 - 2);
	}

	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}
	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut pomocni = (Donut) obj;
			if (this.getCenter().equals(pomocni.getCenter()) &&
					this.getRadius() == pomocni.getRadius() &&
					this.innerRadius == pomocni.getInnerRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean contains(int x, int y) {
		double dFromCenter = this.getCenter().distance(x, y);
		return super.contains(x, y) && dFromCenter > innerRadius;
	}
	
	public boolean contains(Point p) {
		double dFromCenter = this.getCenter().distance(p.getX(), p.getY());
		return super.contains(p.getX(), p.getY()) && dFromCenter > innerRadius;
	}
	
	public int getInnerRadius() {
		return innerRadius;
	}
	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}

	public String toString() {
		return "Donut:center"+"["+this.getCenter().getX()+","+this.getCenter().getY()+"]"+",radius("+this.getRadius()+"),"+"innerRadius("+this.getInnerRadius()+"),"+"foreground["+getColor().getRed()+"."+getColor().getGreen()+"."+getColor().getBlue()+"]"+","+"background["+getInnerColor().getRed()+"."+getInnerColor().getGreen()+"."+getInnerColor().getBlue()+"]";

	}
	
	@Override
	public Donut clone() {
		return new Donut(new Point(this.getCenter().getX(), this.getCenter().getY()), 
				this.getRadius(), this.getInnerRadius(), true, this.getColor(), this.getInnerColor());
		
		
	}
	
}