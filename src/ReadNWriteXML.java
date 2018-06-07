
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class WriteXMLFile {
	private String Name, filePath;
	private Data start, last;
	
 	public WriteXMLFile(String filePath, String Name, Data start, Data last) {
		this.Name = Name;
		this.filePath = filePath;
		this.start = start;
		this.last = last;
	}

	boolean WriteFile() {
		System.out.println("연결된당!");
		System.out.println(filePath);
		System.out.println(Name);
		
		try{
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// 루트 엘리먼트
			Document doc = docBuilder.newDocument();
			Element fileName = doc.createElement(Name);
			doc.appendChild(fileName);
			Data k = start;
			
			if(k != null) {
				// value 엘리먼트
				Element node = doc.createElement("node");
				fileName.appendChild(node);
				
				// string 엘리먼트
				Element Value = doc.createElement("Value");
				Value.appendChild(doc.createTextNode(k.getValue()));
				node.appendChild(Value);
				
				// X 엘리먼트
				Element X = doc.createElement("X");
				X.appendChild(doc.createTextNode(k.getStrX()));
				node.appendChild(X);

				// Y 엘리먼트
				Element Y = doc.createElement("Y");
				Y.appendChild(doc.createTextNode(k.getStrY()));
				node.appendChild(Y);

				// R 엘리먼트
				Element R = doc.createElement("R");
				R.appendChild(doc.createTextNode(k.getStrHeight()));
				node.appendChild(R);
				
				// H 엘리먼트
				Element H = doc.createElement("H");
				H.appendChild(doc.createTextNode(k.getStrH()));
				node.appendChild(H);
							
				// W 엘리먼트
				Element W = doc.createElement("W");
				W.appendChild(doc.createTextNode(k.getStrW()));
				node.appendChild(W);

				// Color 엘리먼트
				Element Color = doc.createElement("Color");
				Color.appendChild(doc.createTextNode("100000"));
				node.appendChild(Color);
				
				while(true) {
					if(k.getChild() != null) {
						k = k.getChild();
					}
					else if(k.getSibling() != null) {
						k = k.getSibling();
					}
					else {
						if(k == last) {
							break;
						}
						while(true) {
							k=k.getParent();
							if(k.getSibling()!=null) {
								k=k.getSibling();
								break;
							}
						}
					}
					// value 엘리먼트
					node = doc.createElement("node");
					fileName.appendChild(node);
					
					// string 엘리먼트
					Value = doc.createElement("Value");
					Value.appendChild(doc.createTextNode(k.getValue()));
					node.appendChild(Value);
					
					// X 엘리먼트
					X = doc.createElement("X");
					X.appendChild(doc.createTextNode(k.getStrX()));
					node.appendChild(X);

					// Y 엘리먼트
					Y = doc.createElement("Y");
					Y.appendChild(doc.createTextNode(k.getStrY()));
					node.appendChild(Y);

					// R 엘리먼트
					R = doc.createElement("R");
					R.appendChild(doc.createTextNode(k.getStrHeight()));
					node.appendChild(R);
					
					// H 엘리먼트
					H = doc.createElement("H");
					H.appendChild(doc.createTextNode(k.getStrH()));
					node.appendChild(H);
								
					// W 엘리먼트
					W = doc.createElement("W");
					W.appendChild(doc.createTextNode(k.getStrW()));
					node.appendChild(W);

					// Color 엘리먼트
					Color = doc.createElement("Color");
					Color.appendChild(doc.createTextNode("100000"));
					node.appendChild(Color);
				}
			}
			

			// XML 파일로 쓰기
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();

			transformer.setOutputProperty(OutputKeys.ENCODING, "euc-KR");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");	
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new FileOutputStream(new File(filePath + ".xml")));

			// 파일로 쓰지 않고 콘솔에 찍어보고 싶을 경우 다음을 사용 (디버깅용)
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");
			
		}
		catch (ParserConfigurationException pce)
		{
			pce.printStackTrace();
			return false;
		}
		catch (TransformerException tfe)
		{
			tfe.printStackTrace();
			return false;
		}
		catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			return false;
		}
		return true;
	}
	
}

class SaveButtonListener implements ActionListener{
	
	private JFileChooser chooser;
	private Data start, last;
	private boolean isSave = false;
	private WriteXMLFile writeXML;
	private String filePath, fileName;
	
	public SaveButtonListener() {
		chooser = new JFileChooser();
	}
	
	public SaveButtonListener(Data start, Data last) {
		this.start = start;
		this.last = last;
		System.out.println("start : " + start);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(!isSave) {
			chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("XML 문서 파일", "xml");
			chooser.setFileFilter(filter);
			if(e.getActionCommand().equals("Save")) {
				chooser.setDialogTitle("Save");
				chooser.setApproveButtonText("Save");
			}
			else {
				chooser.setDialogTitle("Save as...");
				chooser.setApproveButtonText("Save as...");
			}
			
			int ret = chooser.showSaveDialog(null);
			if(ret != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "해당 데이터를 저장하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
				return;
			}
		
			filePath = chooser.getSelectedFile().getAbsolutePath(); // 파일 경로명 리턴
			fileName = chooser.getSelectedFile().getName(); // 파일의 이름.확장자
			writeXML = new WriteXMLFile(filePath, fileName, start, last);
			isSave = writeXML.WriteFile();
		}
		else
			isSave = writeXML.WriteFile();
		
		
	}
}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

class ReadXMLFile {
	private String path;
	private JTextArea textEdit;
	private StringBuffer textAreaStr = new StringBuffer();
	private Mindmap mindmapSection;
	
	public ReadXMLFile(String path, JTextArea textEdit, Mindmap mindmapSection) {
		this.path = path;
		this.textEdit = textEdit;
		this.mindmapSection = mindmapSection;
		ReadFile();
	}
	
    void ReadFile() {
  
      try {
        File fXmlFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
  
        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
        NodeList nList = doc.getElementsByTagName("node");
        System.out.println("-----------------------");
  
        for (int temp = 0; temp < nList.getLength(); temp++) {
        	Node nNode = nList.item(temp);
        	if (nNode.getNodeType() == Node.ELEMENT_NODE) {
        		Element eElement = (Element) nNode;
        		int rank = Integer.parseInt(getTagValue("R", eElement));
        		while(true) {
        			if(rank == 0) {
        				break;
        			}
        			rank--;
        			textAreaStr = textAreaStr.append("\t");
        			
        		}
            	textAreaStr = textAreaStr.append(getTagValue("Value", eElement)+"\n");
              
  
            	System.out.println("Value : " + getTagValue("Value", eElement));
            	System.out.println("X : " + getTagValue("X", eElement));
            	System.out.println("Y : " + getTagValue("Y", eElement));
            	System.out.println("R : " + getTagValue("R", eElement));
            	System.out.println("H : " + getTagValue("H", eElement));
            	System.out.println("W : " + getTagValue("W", eElement));
            	System.out.println("Color : " + getTagValue("Color", eElement));
  
           }
        	System.out.println(textAreaStr);
        	String fixedStr = textAreaStr.toString();
        	textEdit.setText(fixedStr);
        	new ButtonListener(textEdit, mindmapSection).ApplyButtonFunc();
//        	System.out.println("tree.start : " + start);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
  }
  
    private static String getTagValue(String sTag, Element eElement) {
    	NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        return nValue.getNodeValue();
    }
}

class OpenButtonListener implements ActionListener{
	
	private JFileChooser chooser;
	private JTextArea textEdit;
	private Mindmap mindmapSection;
	
	public OpenButtonListener(JTextArea textEdit, Mindmap mindmapSection) {
		chooser = new JFileChooser();
		chooser.setDialogTitle("Open");
		chooser.setApproveButtonText("Open");
		this.textEdit = textEdit;
		this.mindmapSection = mindmapSection;
	}
	
	public void actionPerformed(ActionEvent e) {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML 문서 파일", "xml");
		chooser.setFileFilter(filter);
		
		int ret = chooser.showOpenDialog(null);
		if(ret != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		String filePath = chooser.getSelectedFile().getPath(); // 파일 경로명 리턴
		new ReadXMLFile(filePath, textEdit, mindmapSection);
//		String fileName = chooser.getSelectedFile().getName(); // 파일의 이름.확장자
		
	}
}