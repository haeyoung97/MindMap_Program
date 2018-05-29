

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.StringTokenizer;


class ButtonListener implements ActionListener{ //버튼 이벤트
	private Object O;
	private Mindmap mindmapSection;
	private StringBuffer buffer; 						//text editor 값		-이 버튼이 실행될 때마다 새로운 객체 생기는 것이 찜찜함...
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


class JLabelListener extends MouseAdapter {
//	private JTextField[] valueArray = new JTextField[6];			//attribute editor 값	-이 버튼이 실행될 때마다 새로운 객체 생기는 것이 찜찜함...
//	private JTextField[] fields;			//attribute editor 값	-이 버튼이 실행될 때마다 새로운 객체 생기는 것이 찜찜함...
	
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



