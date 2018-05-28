
//import javax.swing.JPanel;
import javax.swing.JTextArea;
//import javax.swing.JTextField;
//import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.StringTokenizer;


class ButtonListener implements ActionListener{ //버튼 이벤트
	private Object O;
	private Mindmap mindmapSection;
	private StringBuffer buffer; 						//text editor 값		-이 버튼이 실행될 때마다 새로운 객체 생기는 것이 찜찜함...
//	private String [] valueArray=new String[6];			//attribute editor 값	-이 버튼이 실행될 때마다 새로운 객체 생기는 것이 찜찜함...
	private String tmp=new String();
	private Tree tree=new Tree(); //TREE추가
	
	
	ButtonListener(Object O,Mindmap mindmapSection) {
			this.O=O;
			this.mindmapSection=mindmapSection;	//연결 OK
	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("적용") || e.getActionCommand().equals("Apply")) { 		//Text Editor
			int i=0; 							//while문 카운트
			mindmapSection.drawNodePanel.removeAll();
			
			buffer=new StringBuffer(((JTextArea)O).getText());
		    tmp=buffer.toString();
			StringTokenizer st=new StringTokenizer(tmp,"\n");	//st에 문자열에서 개행+문자로 된 애들 기준으로 자르기.
			String [] member=new String[st.countTokens()];
			
			
			while(st.hasMoreTokens()) {							//member배열에 \n기준으로 값 저장. (\t유효)
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
		
//		else if(Btn.getText().equals("변경")) { //Attribute Editor 
//			JTextField[] value = new JTextField[6];
//			for(int i=1;i<=6;i++) {
//				value[i-1]=(JTextField)((JPanel)O).getComponent(i*2-1);
//				valueArray[i-1]=value[i-1].getText();
//				System.out.println(valueArray[i-1]); //0번째에는 현재 빈 공간. NAME부분.
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



