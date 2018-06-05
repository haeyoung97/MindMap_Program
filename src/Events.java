
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.StringTokenizer;

class NewButtonListener implements ActionListener{
	JPanel drawNodePanel;
	Tree t;
	JTextArea textarea;
	
	NewButtonListener(JPanel drawNodePanel,JTextArea textarea){
		this.drawNodePanel=drawNodePanel;
		this.t=t;
		this.textarea=textarea;	
	}
	
	@Override
	public void actionPerformed(ActionEvent e2) {
		drawNodePanel.removeAll();
		textarea.setText("");
		drawNodePanel.setVisible(false);
		drawNodePanel.setVisible(true);
		textarea.setVisible(false);
		textarea.setVisible(true);
		System.out.println("*******new");
	}
}

class ButtonListener implements ActionListener{ //��ư �̺�Ʈ
	private Object O;
	private Mindmap mindmapSection;
	private StringBuffer buffer; 						//text editor ��		-�� ��ư�� ����� ������ ���ο� ��ü ����� ���� ������...
	private String tmp=new String();
//	private Tree tree=new Tree(mindmapSection.drawNodePanel); //TREE�߰�
	private Tree tree;
	
//	private JPanel panel4line;
	
	
	
	ButtonListener(Object O,Mindmap mindmapSection) {
			this.O=O;
			this.mindmapSection=mindmapSection;	//���� OK
			this.tree=new Tree(mindmapSection.drawNodePanel);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("����") || e.getActionCommand().equals("Apply")) { 		//Text Editor
			int i=0; 							//while�� ī��Ʈ
			mindmapSection.drawNodePanel.removeAll();
			
			buffer=new StringBuffer(((JTextArea)O).getText());
		    tmp=buffer.toString();
			StringTokenizer st=new StringTokenizer(tmp,"\n");	//st�� ���ڿ����� ����+���ڷ� �� �ֵ� �������� �ڸ���.
			String [] member=new String[st.countTokens()];
			
			
			while(st.hasMoreTokens()) {							//member�迭�� \n�������� �� ����. (\t��ȿ)
				member[i]=st.nextToken();
				i++;
			}
			
			if(tree.MakeTree(member)) {
				tree.print();
				tree.AddLabel(mindmapSection.drawNodePanel);
				
//				mindmapSection.drawNodePanel.getLabels2drawing((JLabel)mindmapSection.drawNodePanel.getComponent(0), (JLabel)mindmapSection.drawNodePanel.getComponent(1));
//				mindmapSection.drawNodePanel.DrawingLine(g);
				
				
				///////////////////////�� �׸���.../////////////////
				
				
				
				
				mindmapSection.drawNodePanel.setVisible(false);
				mindmapSection.drawNodePanel.setVisible(true);
				tree.Default();
			}						
		}
		
//		else if(Btn.getText().equals("����")) { //Attribute Editor 
//			JTextField[] value = new JTextField[6];
//			for(int i=1;i<=6;i++) {
//				value[i-1]=(JTextField)((JPanel)O).getComponent(i*2-1);
//				valueArray[i-1]=value[i-1].getText();
//				System.out.println(valueArray[i-1]); //0��°���� ���� �� ����. NAME�κ�.
//			}
//			
//			
//		}
		
	}
}


class JLabelListener extends MouseAdapter {
//	private JTextField[] valueArray = new JTextField[6];			//attribute editor ��	-�� ��ư�� ����� ������ ���ο� ��ü ����� ���� ������...
//	private JTextField[] fields;			//attribute editor ��	-�� ��ư�� ����� ������ ���ο� ��ü ����� ���� ������...
	private JLabel label,Plabel;
	private boolean isDragged;
	private JPanel panel;
	private int x,y;
	
	public JLabelListener(JPanel panel) {
		this.panel=panel;
//		System.out.println(panel.getName());
	}

	public void mousePressed(MouseEvent e) {
		JLabel lb = (JLabel)e.getSource();
		System.out.println("***����");
		String name = lb.getText();
		x = e.getX();
		y = e.getY();
		int lbX = x+lb.getWidth()/2; 	// width
		int lbY = y+lb.getHeight()/2; // height
		
//		System.out.println("label�� ��ġ "+lb.getLocation().x+" "+lb.getLocation().y); //���� ���ʻ�ܸ𼭸� ��!!!!
//		System.out.println("���콺 �������� ��ġ " + e.getX()+ " "+ e.getY()); //�� �ȿ��� ���콺 �������� ��ġ
//		System.out.println("�г� �� ���콺 ������ ��ġ " + (lb.getLocation().x+e.getX())+ " "+(lb.getLocation().y+e.getY()));
//		
		
//		fields[0] = new JTextField(name);
//		fields[1] = new JTextField(x);
//		fields[2] = new JTextField(y);
//		fields[3] = new JTextField(lbX);
//		fields[4] = new JTextField(lbY);
//		fields[5] = new JTextField();
		
		
		this.label=lb;
		isDragged = true;
		System.out.println("test name : " + name + " test X : " + x + " test Y : "+y);
//		System.out.println("label's loction of x "+lb.getLocation().x);
		
		
	}
	
	public void mouseReleased(MouseEvent e){//////////////////////////////////////////////
		//���콺 ��ư�� ������Ǹ� �巡�� ��� ����
		isDragged = false;
		System.out.println("FALSE");
		
		
		
		
		
	}
	
	public void mouseDragged(MouseEvent e){///////////////////////////////////////////////
		System.out.println("�巡��...");
		if(isDragged){
			
			
			label.setLocation(e.getX()+label.getX()-x,e.getY()+label.getY()-y);
			System.out.println("MOOOVE");
		}
	}
	
}


class SaveButtonListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}



class Mouser extends MouseAdapter{

	boolean isDragged;
	int offX=0, offY=0;
	JPanel panel;
	Component []labels;
	Component label;
	
	Mouser(JPanel drawNodePanel){
		this.panel=drawNodePanel;
		labels=panel.getComponents();
	}
	

	public void mousePressed(MouseEvent me){/////////////////////////////////////////////////

		System.out.println(panel.getComponentCount());

		
		
		for(int i=0;i<panel.getComponentCount();i++) {
			if((me.getX()>panel.getComponent(i).getX() &&me.getX()<(panel.getComponent(i).getWidth()+panel.getComponent(i).getX()))&&(me.getY()>panel.getComponent(i).getY()&&me.getY()<(panel.getComponent(i).getHeight()+panel.getComponent(i).getY()))){

				System.out.println("�����̿�~~");
				offX = me.getX() - panel.getComponent(i).getLocation().x;
				offY = me.getY() - panel.getComponent(i).getLocation().y;
				this.label=panel.getComponent(i);
				isDragged = true;
				
				
			}
		
		
		
		System.out.println(me.getX()+" "+me.getY());

			
		}
	}

		

	public void mouseReleased(MouseEvent me){//////////////////////////////////////////////
		//���콺 ��ư�� ������Ǹ� �巡�� ��� ����
		isDragged = false;
		System.out.println("FALSE");
	}
	public void mouseDragged(MouseEvent me){///////////////////////////////////////////////
		
		if(isDragged){

			label.setLocation(me.getX()-offX,me.getY()-offY);
			panel.setVisible(false);
			panel.setVisible(true);

			System.out.println("MOOOVE");
		}
	}
}



