package decision;


public class DecisionTreeNode {
	/**
	 * 父结点
	 */
	DecisionTreeNode parentNode;
	/**
	 * 父结点属性值
	 */
	String parentArrtibute;
	/**
	 * 当前结点名
	 */

	String nodeName;
	/**
	 * 当前结点属性集
	 */

	String[] arrtibutesArray;
	/**
	 * 孩子结点集
	 */
	DecisionTreeNode[] childNodesArray;

}