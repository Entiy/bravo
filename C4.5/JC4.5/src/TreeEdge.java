
public class TreeEdge {
	int value=-1;//权值
	TreeNode childNoe=null;//边指向的下一个节点
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public TreeNode getChildNoe() {
		return childNoe;
	}
	public void setChildNoe(TreeNode childNoe) {
		this.childNoe = childNoe;
	}

}
