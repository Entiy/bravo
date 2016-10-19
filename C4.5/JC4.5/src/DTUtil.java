import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class DTUtil {

	
	public static void main(String[] args) {
		// 12:22:54 PM Oct 18, 2016
		int dataSet[][]={
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
		int a[]=new int[14];
		int b[]=new int[14];
		for (int i = 0; i < 14; i++) {
			b[i]=dataSet[i][4];
		}
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 14; i++) 
				a[i]=dataSet[i][j];
			HashMap<Integer, Integer> temp=new HashMap<Integer, Integer>();
			HashMap<Integer, HashMap<Integer,Integer>> temp1=new HashMap<Integer, HashMap<Integer,Integer>>();
			CalnumPerValuePerColumn(b,a,temp,temp1);
			System.out.println(getMaxGainRation(temp1, b));
			temp=null;
			temp1=null;
		}
		
	}
	
	//给定一个属性列计算其信息增益
	public static double getMaxGainRation(HashMap<Integer, HashMap<Integer,Integer>> targetMap,int[] targetAttr){
		
		
		double gain=0.0;
		int sum=0;
		int toal=0;
		double split=0.0;
		Iterator iter = targetMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			HashMap<Integer, Integer> temp=(HashMap<Integer, Integer>)entry.getValue();
			Iterator iter1 = temp.entrySet().iterator();
			while (iter1.hasNext()) {
				Map.Entry entry1= (Map.Entry) iter1.next();
				sum+=(int)entry1.getValue();
			}
			toal+=sum;
			gain+=sum*getEntropy(temp);
			sum=0;
		}
		iter = targetMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			HashMap<Integer, Integer> temp=(HashMap<Integer, Integer>)entry.getValue();
			Iterator iter1 = temp.entrySet().iterator();
			while (iter1.hasNext()) {
				Map.Entry entry1= (Map.Entry) iter1.next();
				sum+=(int)entry1.getValue();
			}
			double p=((1.0)*sum)/toal;
			split+=p*(Math.log(p)/Math.log((double)2));
			sum=0;
		}
		gain/=toal;
		HashMap<Integer,Integer> attr=new HashMap<Integer,Integer>();
		CalnumPerValueTargetColumn(targetAttr,attr);
		gain=getEntropy(attr)-gain;
		return gain/(-split);
		
	}
	//计算熵
    public static double getEntropy(HashMap<Integer, Integer> temp){
        double entropy = 0.0;
        int n=0;
        Iterator iter = temp.entrySet().iterator();
        while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			n+=(int)entry.getValue();
		}
        iter = temp.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			double p=(1.0*(int)entry.getValue())/n;
			entropy+=p* (Math.log(p)/Math.log((double)2));
		}
        return -entropy;
    }

    //计算目标列数据按相同值划分的，每个值的数据个数
    public static void CalnumPerValueTargetColumn(int targetAttr[],HashMap<Integer, Integer> temp){
		for (int i = 0; i < targetAttr.length; i++) {
			if (temp.get(targetAttr[i])!=null) {
				temp.put(targetAttr[i], temp.get(targetAttr[i])+1);
			}else{
				temp.put(targetAttr[i], 1);
			}
		}
	}
	//计算一列数据按相同值划分的，每个值的数据个数
	public static void CalnumPerValuePerColumn(int targetAttr[], int dataSetCol[],HashMap<Integer, Integer> temp,HashMap<Integer, HashMap<Integer,Integer>> temp1){
		for (int i = 0; i < dataSetCol.length; i++) {
			if (temp.get(dataSetCol[i])!=null) {
				temp.put(dataSetCol[i], temp.get(dataSetCol[i])+1);
			}else{
				temp.put(dataSetCol[i], 1);
			}
			if (temp1.get(dataSetCol[i])!=null) {
				HashMap<Integer, Integer> t=temp1.get(dataSetCol[i]);
				if(t.get(targetAttr[i])!=null){
					t.put(targetAttr[i], t.get(targetAttr[i])+1);
				}else {
					t.put(targetAttr[i], 1);
				}
				temp1.put(dataSetCol[i], t);
				
			}else{
				HashMap<Integer, Integer> t=new HashMap<Integer, Integer>();
				t.put(targetAttr[i], 1);
				temp1.put(dataSetCol[i],t);
			}
		}
	}
	
}
