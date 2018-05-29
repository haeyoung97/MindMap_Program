

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.StringTokenizer;


class ButtonListener implements ActionListener{ //��ư �̺�Ʈ
	private Object O;
	private Mindmap mindmapSection;
	private StringBuffer buffer; 						//text editor ��		-�� ��ư�� ����� ������ ���ο� ��ü ����� ���� ������...
	private String tmp=new String();
	private Tree tree=new Tree(); //TREE�߰�
	
	
	ButtonListener(Object O,Mindmap mindmapSection) {
			this.O=O;
			this.mindmapSection=mindmapSection;	//���� OK
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
	
	public JLabelListener() {
		
	}

	public void mousePressed(MouseEvent e) {
		JLabel lb = (JLabel)e.getSource();
		String name = lb.getText();
		int x = e.getX();
		int y = e.getY();
		int lbX = lb.getWidth()/2; 	// width
		int lbY = lb.getHeight()/2; // height
//		fields[0] = new JTextField(name);
//		fields[1] = new JTextField(x);
//		fields[2] = new JTextField(y);
//		fields[3] = new JTextField(lbX);
//		fields[4] = new JTextField(lbY);
//		fields[5] = new JTextField();
		System.out.println("test name : " + name + " test X : " + x + " test Y : "+y);
	}
}


class SaveButtonListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}



