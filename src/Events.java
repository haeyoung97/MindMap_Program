

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.StringTokenizer;


class ButtonListener implements ActionListener{ //��ư �̺�Ʈ
	private Object O;
	private Mindmap mindmapSection;
	//private JLabel [] MindMapNode;
	private StringBuffer buffer; 						//text editor ��		-�� ��ư�� ����� ������ ���ο� ��ü ����� ���� ������...
	private String [] valueArray=new String[6];			//attribute editor ��	-�� ��ư�� ����� ������ ���ο� ��ü ����� ���� ������...
	private String tmp=new String();
	private Tree tree=new Tree(); //TREE�߰�
	
	
	
	ButtonListener(Object O,Mindmap mindmapSection) {
			this.O=O;
			this.mindmapSection=mindmapSection;	//���� OK
	}
	
	
	public void actionPerformed(ActionEvent e) {
		JButton Btn=(JButton)e.getSource();
		
		if(Btn.getText().equals("����")) { 		//Text Editor
			int i=0; 							//while�� ī��Ʈ
			mindmapSection.drawNodePanel.removeAll();
			
			
			
			buffer=new StringBuffer(((JTextArea)O).getText());
		    tmp=buffer.toString();
			StringTokenizer st=new StringTokenizer(tmp,"\n");	//st�� ���ڿ����� ����+���ڷ� �� �ֵ� �������� �ڸ���.
			String [] member=new String[st.countTokens()];
			//MindMapNode=new JLabel[st.countTokens()];
//			mindmapSection.Node=new JLabel[st.countTokens()];
			//System.out.println(st.countTokens());
			
//			mindmapSection.drawNodePanel = new JPanel();//�߰�
//			mindmapSection.drawNodePanel.setLayout(new FlowLayout());
//			mindmapSection.drawNodePanel.setBackground(Color.white);
			
			while(st.hasMoreTokens()) {							//member�迭�� \n�������� �� ����. (\t��ȿ)
				member[i]=st.nextToken();
//				System.out.println(member[i]);
//				mindmapSection.Node[i]=new JLabel(member[i]);				//MindMapNode�迭 ���� OK
//				mindmapSection.Node[i].setOpaque(true);
//				mindmapSection.Node[i].setBackground(Color.CYAN);
//				mindmapSection.drawNodePanel.add(mindmapSection.Node[i]);					//���Ⱑ ����... ����� ..�ȵȴ�..
				i++;
			}
			
			
			tree.MakeTree(member); //Ʈ�������...
			tree.print();
			tree.AddLabel(mindmapSection.drawNodePanel);
			mindmapSection.drawNodePanel.setVisible(false);
			mindmapSection.drawNodePanel.setVisible(true);
			
			
			tree.Default();
			
//			mindmapSection.mindMapPanel.add(mindmapSection.drawNodePanel, BorderLayout.CENTER); //�߰�
//			System.out.println("�� "+mindmapSection.drawNodePanel.getComponentCount()+"���� ���");
		//	mindmapSection.drawNodes();
//			mindmapSection.drawNodePanel.setBackground(Color.white);
//			mindmapSection.mindMapPanel.add(mindmapSection.drawNodePanel,BorderLayout.CENTER);
//			mindmapSection.drawNodePanel.pack();
			
		}
		
		else if(Btn.getText().equals("����")) { //Attribute Editor 
			JTextField[] value = new JTextField[6];
			for(int i=1;i<=6;i++) {
				value[i-1]=(JTextField)((JPanel)O).getComponent(i*2-1);
				valueArray[i-1]=value[i-1].getText();
				System.out.println(valueArray[i-1]); //0��°���� ���� �� ����. NAME�κ�.
			}
			
			
		}
		
	}
}



