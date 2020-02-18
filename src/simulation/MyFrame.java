package simulation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


/*
 * Created by Stefan Sokolovic 2018 
 */


@SuppressWarnings("serial")
public class MyFrame extends JFrame {

	
	private MyPanel panel1;
	private JPanel panel2;
	private GridBagConstraints cbc;
	private JButton button1;
	private JButton button2;

	private Thread thread;
	private ArrayList<Pigeon> pigeons;
	private Stack<Food> food;

	private boolean g_pigeon;
	
	@SuppressWarnings("serial")
	class MyPanel extends JPanel {

		public MyPanel() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void paintComponent(Graphics gr) {
			// TODO Auto-generated method stub
			super.paintComponent(gr);
			for(Pigeon pigeon : pigeons)
			{
				gr.setColor(Color.GREEN);
				gr.drawRect(pigeon.getX(), pigeon.getY(), 10, 10);
			}
			for(Food h: food)
			{
				gr.setColor(Color.BLUE);
				gr.drawOval(h.getX(),h.getY(),10,10);
			}
				
		}
		
	}
	

	
	public void initialize()
	{
		pigeons = new ArrayList<>();
		food = new Stack<>();
		g_pigeon = false;
		panel1= new MyPanel();
		panel2= new JPanel();
		cbc = new GridBagConstraints();
		this.setBounds(120,120,1200,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new GridBagLayout());
		panel1.setFocusable(true);
		cbc.fill = GridBagConstraints.BOTH;
		cbc.gridx=0;
		cbc.gridy=0;
		cbc.weightx =0.70;
		cbc.weighty = 1;
		add(panel1,cbc);
		cbc.fill = GridBagConstraints.BOTH;
		cbc.gridx=1;
		cbc.gridy=0;
		cbc.weightx =0.30;
		cbc.weighty = 1;
		add(panel2,cbc);
		panel1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		panel2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
		button1= new JButton("Insert pigeon");
		button1.setBounds(35,150,200,30);
		panel2.add(button1);
		button2= new JButton("Finish insertion");
		button2.setBounds(35,300,200,30);
		panel2.add(button2);
		panel2.setLayout(null);
		this.setVisible(true);
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				g_pigeon=true;				
			}
			
		});
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				g_pigeon=false;
				
			}
			
		});
		
		thread=new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true)
				{
					
				if(!food.isEmpty())
				{
					Food tmp=food.peek();
					for(Pigeon pigeon: pigeons)
					{
						
						pigeon.scary(panel1.getWidth(),panel1.getHeight());
						panel1.repaint();
						if(pigeon.distance(tmp))
						{
							
							pigeon.setX((int)pigeon.getX()+(pigeon.getD()*(tmp.getX()-pigeon.getX()))/
							(int)Math.sqrt(Math.pow(tmp.getX()-pigeon.getX(),2)+Math.pow(tmp.getY()-pigeon.getY(),2)));
							pigeon.setY((int)pigeon.getY()+(pigeon.getD()*(tmp.getY()-pigeon.getY()))/
							(int)Math.sqrt(Math.pow(tmp.getX()-pigeon.getX(),2)+Math.pow(tmp.getY()-pigeon.getY(),2)));
							
							//g.changeDistance(tmp);
							panel1.repaint();
						}
						else
						{
							pigeon.setX((int) (pigeon.getX()+(pigeon.getD()*(tmp.getX()-pigeon.getX()))/
							Math.sqrt(Math.pow(tmp.getX()-pigeon.getX(),2)+Math.pow(tmp.getY()-pigeon.getY(),2))));
							pigeon.setY((int) (pigeon.getY()+(pigeon.getD()*(tmp.getY()-pigeon.getY()))/
							Math.sqrt(Math.pow(tmp.getX()-pigeon.getX(),2)+Math.pow(tmp.getY()-pigeon.getY(),2))));
							//g.changeDistance(tmp);
							food.pop();
							panel1.repaint();
						}
					}
					
				}
				try {
					//panel1.repaint();;
					Thread.sleep(100);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				}
					
			}});
		
		thread.start();
		panel1.addMouseListener(new MouseListener()
				{

					@Override
					public void mouseClicked(MouseEvent e) {
					
						Random r= new Random();
						if(g_pigeon)
							pigeons.add(new Pigeon(e.getX(),e.getY(),r.nextInt(6)+1,r.nextDouble()));
						else
							food.add(new Food(e.getX(),e.getY()));
						panel1.repaint();
					
					}

					@Override
					public void mouseEntered(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mousePressed(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}
			
				});
	}
	public MyFrame()
	{
		initialize();
	}

	public static void main(String arg[])
	{
		new MyFrame();
	}
}
