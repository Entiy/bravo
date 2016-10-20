import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class TreeNode {

	int id;//分裂的属性id
	Map<Integer, Integer> label;//实例类别以及对应个数
	List<Integer> attrlist;//含有的待分类属性
	TreeEdge edges[];//树节点含有的边数
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Map<Integer, Integer> getLabel() {
		return label;
	}
	public void setLabel(Map<Integer, Integer> label) {
		this.label = label;
	}
	
	public TreeEdge[] getEdges() {
		return edges;
	}
	public void setEdges(TreeEdge[] edges) {
		this.edges = edges;
	}
	public List<Integer> getAttrlist() {
		return attrlist;
	}
	public void setAttrlist(List<Integer> attrlist) {
		this.attrlist = attrlist;
	}
	
}
