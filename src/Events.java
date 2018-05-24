

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


class ButtonListener implements ActionListener{ //버튼 이벤트
	private Object O;
	private Mindmap mindmapSection;
	//private JLabel [] MindMapNode;
	private StringBuffer buffer; 						//text editor 값		-이 버튼이 실행될 때마다 새로운 객체 생기는 것이 찜찜함...
	private String [] valueArray=new String[6];			//attribute editor 값	-이 버튼이 실행될 때마다 새로운 객체 생기는 것이 찜찜함...
	private String tmp=new String();
	private Tree tree=new Tree(); //TREE추가
	
	
	
	ButtonListener(Object O,Mindmap mindmapSection) {
			this.O=O;
			this.mindmapSection=mindmapSection;	//연결 OK
	}
	
	
	public void actionPerformed(ActionEvent e) {
		JButton Btn=(JButton)e.getSource();
		
		if(Btn.getText().equals("적용")) { 		//Text Editor
			int i=0; 							//while문 카운트
			mindmapSection.drawNodePanel.removeAll();
			
			
			
			buffer=new StringBuffer(((JTextArea)O).getText());
		    tmp=buffer.toString();
			StringTokenizer st=new StringTokenizer(tmp,"\n");	//st에 문자열에서 개행+문자로 된 애들 기준으로 자르기.
			String [] member=new String[st.countTokens()];
			//MindMapNode=new JLabel[st.countTokens()];
//			mindmapSection.Node=new JLabel[st.countTokens()];
			//System.out.println(st.countTokens());
			
//			mindmapSection.drawNodePanel = new JPanel();//추가
//			mindmapSection.drawNodePanel.setLayout(new FlowLayout());
//			mindmapSection.drawNodePanel.setBackground(Color.white);
			
			while(st.hasMoreTokens()) {							//member배열에 \n기준으로 값 저장. (\t유효)
				member[i]=st.nextToken();
//				System.out.println(member[i]);
//				mindmapSection.Node[i]=new JLabel(member[i]);				//MindMapNode배열 저장 OK
//				mindmapSection.Node[i].setOpaque(true);
//				mindmapSection.Node[i].setBackground(Color.CYAN);
//				mindmapSection.drawNodePanel.add(mindmapSection.Node[i]);					//여기가 문제... 출력이 ..안된다..
				i++;
			}
			
			
			tree.MakeTree(member); //트리만들기...
			tree.print();
			tree.AddLabel(mindmapSection.drawNodePanel);
			mindmapSection.drawNodePanel.setVisible(false);
			mindmapSection.drawNodePanel.setVisible(true);
			
			
			tree.Default();
			
//			mindmapSection.mindMapPanel.add(mindmapSection.drawNodePanel, BorderLayout.CENTER); //추가
//			System.out.println("총 "+mindmapSection.drawNodePanel.getComponentCount()+"개의 요소");
		//	mindmapSection.drawNodes();
//			mindmapSection.drawNodePanel.setBackground(Color.white);
//			mindmapSection.mindMapPanel.add(mindmapSection.drawNodePanel,BorderLayout.CENTER);
//			mindmapSection.drawNodePanel.pack();
			
		}
		
		else if(Btn.getText().equals("변경")) { //Attribute Editor 
			JTextField[] value = new JTextField[6];
			for(int i=1;i<=6;i++) {
				value[i-1]=(JTextField)((JPanel)O).getComponent(i*2-1);
				valueArray[i-1]=value[i-1].getText();
				System.out.println(valueArray[i-1]); //0번째에는 현재 빈 공간. NAME부분.
			}
			
			
		}
		
	}
}



