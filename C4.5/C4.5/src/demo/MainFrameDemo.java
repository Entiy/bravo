package demo;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import decision.DecisionTree;

/**
 * @author liuxuewei
 * @email <strong>mail to</strong><br>
 *        <a href="mailto:liu.xuewei@hotmail.com">liu.xuewei@hotmail.com</a><br>
 *		  <strong>see also</strong><关注我><br>
 *        <a href="http://www.oschina.net/liu-xuewei">开源中国@liuxuewei</a>
 * @date 2013-6-16
 * @function 
 * @versions 1.0 
 */
/**
 * @author liuxuewei
 * @email <strong>mail to</strong><br>
 *        <a href="mailto:liu.xuewei@hotmail.com">liu.xuewei@hotmail.com</a><br>
 *		  <strong>see also</strong><关注我><br>
 *        <a href="http://www.oschina.net/liu-xuewei">开源中国@liuxuewei</a>
 * @date 2013-6-16
 * @function 
 * @versions 1.0 
 */
public class MainFrameDemo {
	public static void main(String[] args) {
	ArrayList<String []>  array= new ArrayList<String[]> ();
	FileReader fileReader;
	BufferedReader bufferReader;
	String temps;
	int countDataIndex=0;//计算列数
	int countLength=0;//计算行号
	try {
		fileReader = new FileReader("data.txt");
		bufferReader= new BufferedReader(fileReader);
		while((temps=bufferReader.readLine())!=null){
			String[] tempArray=temps.split(",|，");
			if(countLength==0){
				countLength++;
				countDataIndex=tempArray.length;
				array.add(tempArray);
			}else if(tempArray.length==countDataIndex){
				countLength++;
				array.add(tempArray);
			}else{
				countLength++;
				JOptionPane.showMessageDialog(null, "<html><font color='RED'>警告：</font><br><font color='GREEN'>第"+countLength+"行的数据有漏缺，请补全数据再尝试！</font>");
				System.exit(-1);
			}
		}
	} catch (FileNotFoundException e1) {
		e1.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
		DecisionTree decisionTree = new DecisionTree();
		decisionTree.create(array.toArray(), 4);
		String processing=decisionTree.getProcess_buffer().toString();
		String result =decisionTree.getResult_buffer().toString(); 
		Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
		MainPanel panel=new MainPanel(processing,result);
		JFrame frame = new JFrame("数据挖掘C4.5算法演示系统");
		frame.setSize(screen.width, screen.height);
		frame.add(panel);
		frame.repaint();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TreePainter treeframe = new TreePainter(decisionTree.getVertexs(),decisionTree.getEdges());
		treeframe.setBounds((screen.width-600)/2, (screen.height-500)/2, 600, 500);
		treeframe.setVisible(true);
		treeframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
