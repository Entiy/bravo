package demo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
public class ConsoleDemo {
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
					System.err.println("警告：\n第"+countLength+"行的数据有漏缺，请补全数据再尝试！");
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
		System.out.println("--------------------C4.5決策樹--------------------");
		System.out.println(decisionTree.getResult_buffer().toString());
		System.out.println("----------------------執行過程----------------------");
		System.out.println(decisionTree.getProcess_buffer().toString());
	}
}
