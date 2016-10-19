package demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
public class MainPanel extends JPanel {
	private static final long serialVersionUID = 8920997326735022832L;
	private JTextArea processText = new JTextArea();
	private JScrollPane processScrollPane = new JScrollPane(processText,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private JTextArea resultText = new JTextArea();
	private JScrollPane resultScrollPane = new JScrollPane(resultText,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	public MainPanel(String processing,String result) {
		this.setLayout(new GridLayout(2,1));
		processScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "处理过程"));
		resultScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED), "处理结果"));
		this.add(processScrollPane);
		this.add(resultScrollPane);
		processText.setEditable(false);
		resultText.setEditable(false);
		Font font = new Font(Font.SERIF, 0, 16);
		processText.setFont(font);
		resultText.setFont(font);
		processText.setText(processing);
		resultText.setText(result);
	}
}
