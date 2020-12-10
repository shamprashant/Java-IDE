import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

class New implements ActionListener
{
	public JFrame newFrame;
	public JTextField jtf;
	public JButton okay,cancel;
	public JLabel classLabel;
	public JCheckBox mainCheck;
	public int width;
	public int height;
	public MyEditor me;
	
	New(MyEditor me) throws Exception
	{
		this.me = me;
		MyEditor.lookAndFeel();
		newFrame = new JFrame();
		newFrame.setLayout(null);
		Toolkit t = newFrame.getToolkit();
        Dimension screenSize = 	t.getScreenSize();
		width = screenSize.width;
		height = screenSize.height;
		newFrame.setBounds(width/3,height/3,260,145);
		classLabel = new JLabel("Class Name");
		classLabel.setBounds(10,10,85,25);
		newFrame.add(classLabel);
		jtf = new JTextField();
		jtf.setBounds(100,10,140,30);
		newFrame.add(jtf);
		mainCheck = new JCheckBox("Include main");
		mainCheck.setBounds(10,50,85,30);
		mainCheck.addActionListener(this);
		newFrame.add(mainCheck);
		okay = new JButton("okay");
		okay.setBounds(100,50,60,30);
		newFrame.add(okay);
		okay.addActionListener(this);
		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		cancel.setBounds(165,50,65,30);
		newFrame.add(cancel);
		newFrame.setVisible(true);
		
		//newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==okay && !jtf.getText().equals(""))
		{
			//TextArea Field
		me.jta = new JTextArea(50,50);
		me.jsp = new JScrollPane(me.jta);
		me.jsp.setBounds(0,0,width-15,height-85);
		me.jf.add(me.jsp);
		me.jta.setFont(new Font("serif",Font.PLAIN,20));
		me.start_auto_saver();
		if(mainCheck.isSelected())
			me.jta.setText("public class "+jtf.getText()+"\n"+"{"+"\n"+"public static void main(String... s)"+"\n"+"{"+"\n"+""+"\n"+"}"+"\n"+"}");
		else
			me.jta.setText("public class "+jtf.getText()+"\n"+"{"+"\n"+"}");
		newFrame.dispose();
		}
		else if(e.getSource()==cancel)
			newFrame.dispose();
	}
}