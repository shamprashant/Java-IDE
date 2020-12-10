import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

class MyCmd
{
	public int width;
	public int height;
	public JFrame cmd;
	public JTextArea cmdta;
	
	MyCmd() throws Exception
	{
		MyEditor.lookAndFeel();
		cmd = new JFrame("Output Screen");
		cmd.setLayout(null);
		Toolkit t=cmd.getToolkit();
		Dimension ScreenSize=t.getScreenSize();
		width=ScreenSize.width;
		height=ScreenSize.height;
		cmd.setBounds(300,150,width/2,height/3);
		cmd.addWindowListener(new CmdClose(cmd));
		cmdta = new JTextArea();
		cmdta.setSize(width/2,height/3);
		cmd.add(cmdta);
		cmd.setVisible(true);
		
	}
}

class CmdClose extends WindowAdapter
{
	public JFrame cmd;
	
	public CmdClose(JFrame cmd)
	{
		this.cmd = cmd;
	}
	
	public void windowClosing(WindowEvent we)
	{
		cmd.dispose();
	}
}


