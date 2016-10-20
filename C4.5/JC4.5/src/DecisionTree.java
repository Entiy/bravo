import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class DecisionTree {
	
	// 12:22:54 PM Oct 18, 2016
	static int dataSet[][]={
				{0,0,0,0,0},
				{0,0,0,1,0},
				{1,0,0,0,1},
				{2,1,0,0,1},
				{2,2,1,0,1},
				{2,2,1,1,0},
				{1,2,1,1,1},
				{0,1,0,0,0},
				{0,2,1,0,1},
				{2,1,1,0,1},
				{0,1,1,1,1},
				{1,1,0,1,1},
				{1,0,1,0,1},
				{2,1,0,1,0}
			};
			
	static DTUtil help=null;
	public static void main(String[] args) {
		
		DecisionTree tree=new DecisionTree();
		help=new DTUtil(dataSet);
		TreeNode root=new TreeNode();
		tree.createTree(root);
	}
	
	public void createTree(TreeNode node) {
		
		int k=0;
		HashMap<Integer, HashMap<Integer,Integer>> maxMap=null;
		List<Integer> attrlist=new ArrayList<Integer>();//属性编号数组，用编号代替名称
		double maxGain=0.0;//所有属性中最大的信息增益率
		for (int j = 0; j < dataSet[0].length-1; j++) {
			attrlist.add(j);
			HashMap<Integer, HashMap<Integer,Integer>> targetMap =new HashMap<Integer,HashMap<Integer,Integer>>();
			double tp=help.getMaxGainRation(j,targetMap);
			if(maxGain<tp){
				maxGain=tp;
				k=j;
				maxMap=targetMap;
			}
		}
		attrlist.remove((Integer)k);
		TreeEdge[] edges=new TreeEdge[maxMap.size()];
		Iterator iter=maxMap.entrySet().iterator();
		node.setId(k);
		int n=0;
		while (iter.hasNext()) {
			Map.Entry entry= (Map.Entry) iter.next();
			HashMap<Integer, Integer> item=(HashMap<Integer, Integer>)entry.getValue();
			TreeNode childNoe=new TreeNode();
			childNoe.setAttrlist(attrlist);
			childNoe.setLabel(item);
			edges[n]=new TreeEdge();
			edges[n].setChildNoe(childNoe);
			edges[n++].setValue((int)entry.getKey());
		}
		node.setEdges(edges);
		insertNode(node);
	}
	
	public void insertNode(TreeNode node) {
		
		int sum=0;
		for (int i = 0; i < node.getEdges().length; i++) {
			TreeNode currentNode=node.getEdges()[i].childNoe;
			if(currentNode.getLabel().size()<=1){
				return;
			}else{
			Iterator it=currentNode.getLabel().entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry entry=(Map.Entry)it.next();
				sum+=(int)entry.getValue();
			}
			
			help.dataSet=help.pickUpSubArray(node.getEdges()[i].getValue(),sum,node.getId());
			int k=0;
			HashMap<Integer, HashMap<Integer,Integer>> maxMap=null;
			List<Integer> attrlist=new ArrayList<Integer>();//属性编号数组，用编号代替名称
			double maxGain=0.0;//所有属性中最大的信息增益率
			for (int j = 0; j < currentNode.getAttrlist().size(); j++) {
				attrlist.add(currentNode.getAttrlist().get(j));
				HashMap<Integer, HashMap<Integer,Integer>> temp=new HashMap<Integer, HashMap<Integer,Integer>>();
				double tp=help.getMaxGainRation(currentNode.getAttrlist().get(j),temp);
				if(maxGain<tp){
					maxGain=tp;
					k=currentNode.getAttrlist().get(j);
					maxMap=temp;
				}
			}
			attrlist.remove((Integer)k);
			TreeEdge[] edges=new TreeEdge[maxMap.size()];
			Iterator iter=maxMap.entrySet().iterator();
			currentNode.setId(k);
			int n=0;
			while (iter.hasNext()) {
				Map.Entry entry= (Map.Entry) iter.next();
				HashMap<Integer, Integer> item=(HashMap<Integer, Integer>)entry.getValue();
				TreeNode childNoe=new TreeNode();
				childNoe.setAttrlist(attrlist);
				childNoe.setLabel(item);
				edges[n]=new TreeEdge();
				edges[n].setChildNoe(childNoe);
				edges[n++].setValue((int)entry.getKey());
			}
			
			currentNode.setEdges(edges);
			insertNode(currentNode);
		}
		}
	}
}
