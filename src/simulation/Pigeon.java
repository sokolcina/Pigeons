package simulation;

import java.util.Random;

public class Pigeon {
	private int x,y,d;
	private double p;

	public Pigeon(int x, int y, int d,double p) {
		super();
		this.x = x;
		this.y = y;
		this.d = d;
		this.p = p;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}
	public boolean distance(Food h)
	{
		double pom = Math.sqrt(Math.pow((double)x-h.getX(),2)+Math.pow((double)y-h.getY(),2));
		if(pom>d)
			return true;
		else return false;
	}
	
	
	public void changeDistance(Food h)
	{
		x=(int) (x+(d*((h.getX()-x)/Math.sqrt(Math.pow(h.getX()-x,2)+Math.pow(h.getY()-y, 2)))));
		y=(int) (y+(d*((h.getY()-y)/Math.sqrt(Math.pow(h.getX()-x,2)+Math.pow(h.getY()-y, 2)))));
	}
	public void scary(int opsX, int opsY)
	{
		Random r=new Random();
	
		if(r.nextDouble()>this.p)
		{
			int xx,yy,dd;
			xx=r.nextInt(opsX);
			yy=r.nextInt(opsY);
			dd=3*d;
			x= (int) (x+(dd*((xx-x)/Math.sqrt(Math.pow(xx-x,2)+Math.pow(yy-y, 2)))));
			y= (int) (y+(dd*((yy-y)/Math.sqrt(Math.pow(xx-x,2)+Math.pow(yy-y, 2)))));
		}
	}
	public double getP() {
		return p;
	}

	public void setP(double p) {
		this.p = p;
	}

	@Override
	public String toString() {
		return "Pigeon [x=" + x + ", y=" + y + ", d=" + d + ", p=" + p + "]";
	}
	
}
