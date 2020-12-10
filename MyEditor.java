import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

class MyEditor  implements ActionListener
{
	public JFrame jf;
	public JButton jbrun,jbcompile;
	public JTextField jtf;
	public JTextArea jta,jta1;
	public JScrollPane jsp,jsp1;
	public JLabel jl;
	public String str = "";
	public String fname = "";
	public String className = "";
	public String result = "";
	public String result1 = "";
	public Runtime r;
	public int width;
	public int height;
	public MyCmd cmd;
	public JMenuBar menuBar;
	public JMenu menu,subMenu;
	public JMenuItem menuItem;
	public New n;
	public boolean compile = false;
	
	MyEditor()
	{
		jf = new JFrame("JAVA IDE");
		jf.setLayout(null);
		Toolkit t=jf.getToolkit();
		Dimension ScreenSize=t.getScreenSize();
		width=ScreenSize.width;
		height=ScreenSize.height;
		jf.setBounds(0,0,width,height);
		
		//cmd = new MyCmd();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame();
		jf.setVisible(true);
	}
	public static void lookAndFeel()
	{
		try
		{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){}
	}
	
	public void mainFrame() 
	{
		menuBar	= new JMenuBar();
		
		//File Menu
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);
		menuItem = new JMenuItem("New",KeyEvent.VK_N);
		menu.add(menuItem);
		KeyStroke  i=KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK);
		menuItem.setAccelerator(i);
		menu.addSeparator();
		menuItem.addActionListener(this);
		
		menuItem = new JMenuItem("Open",KeyEvent.VK_O);
		menu.add(menuItem);
		KeyStroke  i1=KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK);
		menuItem.setAccelerator(i1);
		menu.addSeparator();
		
		menuItem.addActionListener(this);
		menuItem = new JMenuItem("Rename File",KeyEvent.VK_R);
		menu.add(menuItem);
		menuItem.addActionListener(this);
		menu.addSeparator();
		
		menuItem = new JMenuItem("Save",KeyEvent.VK_S);
		menu.add(menuItem);
		menuItem.addActionListener(this);
		KeyStroke  i2=KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK);
		menuItem.setAccelerator(i2);
		menu.addSeparator();
		
		menuItem = new JMenuItem("Save as...");
		menu.add(menuItem);
		menuItem.addActionListener(this);
		menu.addSeparator();
		
		menuItem = new JMenuItem("Exit",KeyEvent.VK_E);
		menu.add(menuItem);
		KeyStroke  i3=KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK);
		menuItem.setAccelerator(i3);
		menuItem.addActionListener(this);
		
		//Edit Menu
		menu =  new JMenu("Edit");
		menu.setMnemonic(KeyEvent.VK_E);
		menuBar.add(menu);
		
		menuItem = new JMenuItem("Undo",KeyEvent.VK_U);
		menu.add(menuItem);
		menuItem.addActionListener(this);
		menu.addSeparator();
		
		menuItem = new JMenuItem("Redo",KeyEvent.VK_R);
		menu.add(menuItem);
		menuItem.addActionListener(this);
		menu.addSeparator();
		
		menuItem = new JMenuItem("Cut",KeyEvent.VK_U);
		menu.add(menuItem);
		menuItem.addActionListener(this);
		menu.addSeparator();
		
		menuItem = new JMenuItem("Copy",KeyEvent.VK_C);
		menu.add(menuItem);
		menuItem.addActionListener(this);
		menu.addSeparator();
		
		menuItem = new JMenuItem("Paste",KeyEvent.VK_P);
		menu.add(menuItem);
		menuItem.addActionListener(this);
		
		//search Menu
		menu = new JMenu("Search");
		menu.setMnemonic(KeyEvent.VK_S);
		menuBar.add(menu);
		
		menuItem = new JMenuItem("Find",KeyEvent.VK_F);
		menu.add(menuItem);
		menuItem.addActionListener(this);
		menu.addSeparator();
		
		menuItem = new JMenuItem("Find Next",KeyEvent.VK_N);
		menu.add(menuItem);
		menuItem.addActionListener(this);
		menu.addSeparator();
		
		menuItem = new JMenuItem("Find previous",KeyEvent.VK_P);
		menu.add(menuItem);
		menuItem.addActionListener(this);
		menu.addSeparator();
		
		menuItem = new JMenuItem("Replace",KeyEvent.VK_R);
		menu.add(menuItem);
		menuItem.addActionListener(this);
		menu.addSeparator();
		
		menuItem = new JMenuItem("Mark All",KeyEvent.VK_M);
		menu.add(menuItem);
		menuItem.addActionListener(this);
		menu.addSeparator();
		
		menuItem = new JMenuItem("Unmark all",KeyEvent.VK_U);
		menu.add(menuItem);
		menuItem.addActionListener(this);
		
		//Compile Menu
		menu = new JMenu("Compile");
		menu.setMnemonic(KeyEvent.VK_C);
		menuBar.add(menu);
		menuItem = new JMenuItem("Compile code",KeyEvent.VK_C);
		menu.add(menuItem);
		KeyStroke  i4=KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK);
		menuItem.setAccelerator(i4);
		menuItem.addActionListener(this);
		
		//Run Menu
		menu = new JMenu("Run");
		menu.setMnemonic(KeyEvent.VK_R);
		menuBar.add(menu);
		menuItem = new JMenuItem("Run Code",KeyEvent.VK_R);	
		menu.add(menuItem);
		KeyStroke  i5=KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK);
		menuItem.setAccelerator(i5);
		menuItem.addActionListener(this);
		
		//CMD
		menu = new JMenu("CMD");
		menu.setMnemonic(KeyEvent.VK_D);
		menuBar.add(menu);
		menuItem = new JMenuItem("Open CMD");
		menu.add(menuItem);
		KeyStroke  i6 = KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK);
		menuItem.setAccelerator(i6);
		menuItem.addActionListener(this);
		
		//Runtime menu
		r = Runtime.getRuntime();
		
		jf.setJMenuBar(menuBar);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		/*if(e.getActionCommand().equals("Open"))
		{
			new FileChose(this);
		}*/
		
		if(e.getActionCommand().equals("New"))
		{
		try{	
		n = new New(this);
	    }catch(Exception e1){}
		}
		
		if(e.getActionCommand().equals("Save"))
		{
			str = "";
			try
			{
				className = n.jtf.getText().trim();
				fname = className+".java";
				str = jta.getText();
				FileWriter fw = new FileWriter(fname);
				PrintWriter pw = new PrintWriter(fw);
				pw.println(str);
				pw.flush();
				
			}
			catch(Exception e2)
			{
				JOptionPane.showMessageDialog(jf,"Make class first ");
			}
		}
		
		if(e.getActionCommand().equals("Compile code"))
		{
			try
			{
				if(!str.equals(""))
				{
					Process error = r.exec("C:\\Program Files\\Java\\jdk-9.0.1\\bin\\javac "+fname);
					BufferedReader err = new BufferedReader(new InputStreamReader(error.getErrorStream()));
					cmd = new MyCmd();
					result = "";
					
					while(true)
					{
						String temp = err.readLine();
						if(temp!=null)
						{
							result+=temp;
							result+="\n";
						}
						else 
							break;
					}
					
					if(!result.equals(""))
					{
						cmd.cmdta.setText(result);
					}
					else
					{
						cmd.cmdta.setText("Compilation Succesful");
						compile = true;
					}
				}
				else
						JOptionPane.showMessageDialog(jf,"Nothing to compile");
				}
			catch(Exception e3)
			{
				System.out.println("Compilation menu error : "+e3);
			}
		}
		
		if(e.getActionCommand().equals("Run Code"))
		{
			try
			{
			if(compile)
			{
				Process run = r.exec("C:\\Program Files\\Java\\jdk-9.0.1\\bin\\java "+className);
				BufferedReader error = new BufferedReader(new InputStreamReader(run.getErrorStream()));
				BufferedReader output = new BufferedReader(new InputStreamReader(run.getInputStream()));
				
				while(true)
				{
					String temp = output.readLine();
					
					if(temp!=null)
					{
						result+=temp;
						result+="\n";
					}
					else
						break;
				}
				
				while(true)
				{
					String temp = error.readLine();
					
					if(temp!=null)
					{
						result1+=temp;
						result1+="\n";
					}
					else
						break;
				}
				
				new MyCmd().cmdta.setText(result+result1);
			}
			else
			{
				JOptionPane.showMessageDialog(jf,"First Compile the code Successfully");
			}
			
			}
			catch(Exception e4)
			{
				System.out.println(e4);
			}
		}
		
		if(e.getActionCommand().equals("Exit"))
		{
			jf.dispose();
		}
	}

	public void start_auto_saver(){
		TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                str = "";
			try
			{
				className = n.jtf.getText().trim();
				fname = className+".java";
				str = jta.getText();
				FileWriter fw = new FileWriter(fname);
				PrintWriter pw = new PrintWriter(fw);
				pw.println(str);
				pw.flush();
				
			}
			catch(Exception e2)
			{
				JOptionPane.showMessageDialog(jf,"Make class first ");
			}
            }
        };

        java.util.Timer timer = new java.util.Timer("MyTimer");//create a new Timer

        timer.scheduleAtFixedRate(timerTask, 500, 2000);
	}
	
	public static void main(String... s)
	{
		new MyEditor();
	}
}
