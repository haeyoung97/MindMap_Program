
//import javax.swing.JPanel;
import javax.swing.JTextArea;
//import javax.swing.JTextField;
//import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.StringTokenizer;


class ButtonListener implements ActionListener{ //��ư �̺�Ʈ
	private Object O;
	private Mindmap mindmapSection;
	private StringBuffer buffer; 						//text editor ��		-�� ��ư�� ����� ������ ���ο� ��ü ����� ���� ������...
//	private String [] valueArray=new String[6];			//attribute editor ��	-�� ��ư�� ����� ������ ���ο� ��ü ����� ���� ������...
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

class SaveButtonListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}



