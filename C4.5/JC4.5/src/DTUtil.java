import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

import org.omg.CORBA.INTERNAL;


public class DTUtil {

	int dataSet[][]=null;
	public DTUtil(int[][] dataSet) {
		this.dataSet=dataSet;
	}

	
	//给定一个属性列计算其信息增益率
	public  double getMaxGainRation(Integer index,HashMap<Integer, HashMap<Integer,Integer>> targetMap){
		
		//HashMap<Integer, HashMap<Integer,Integer>> targetMap =new HashMap<Integer,HashMap<Integer,Integer>>();
		CalnumPerValuePerColumn(index, targetMap);
		double gain=0.0;
		int sum=0;//每个分类的数量
		double split=0.0;//分裂信息
		int numarry[]=new int[targetMap.size()];
		int i=0;
		int total=0;
		Iterator iter = targetMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			HashMap<Integer, Integer> temp=(HashMap<Integer, Integer>)entry.getValue();
			Iterator iter1 = temp.entrySet().iterator();
			while (iter1.hasNext()) {
				Map.Entry entry1= (Map.Entry) iter1.next();
				sum+=(int)entry1.getValue();
			}
			total+=sum;
			numarry[i++]=sum;
			gain+=sum*getEntropy(temp,sum);
			sum=0;
		}
		for (int j = 0; j < numarry.length; j++) {
			double p=((1.0)*numarry[j])/total;
			split+=p*(Math.log(p)/Math.log((double)2));
		}
		gain/=total;
		HashMap<Integer, Integer> temp=new HashMap<>();
		CalnumPerValueTargetColumn(index, temp);
		gain=getEntropy(temp, total)-gain;
		return gain/(-split);
		
	}
	//计算熵
    public  double getEntropy(HashMap<Integer, Integer> temp,int n){
        double entropy = 0.0;
        Iterator iter = temp.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			double p=(1.0*(int)entry.getValue())/n;
			entropy+=p* (Math.log(p)/Math.log((double)2));
		}
        return -entropy;
    }

    //计算目标列的熵
    public void CalnumPerValueTargetColumn(int index,HashMap<Integer, Integer> temp) {
    	int resule[]=getSameValueArray(index);
		for (int i = 0; i < resule.length; i++) {
			for (int j = 0; j < dataSet.length; j++)
				if (resule[i]==dataSet[j][index])
					if (temp.get(dataSet[j][4])!=null) 
						temp.put(dataSet[j][4], temp.get(dataSet[j][4])+1);
					else
						temp.put(dataSet[j][4], 1);
				 
		}
	}
    
	//计算一列数据按相同值划分的，每个值的数据个数
	public  void CalnumPerValuePerColumn(int index,HashMap<Integer, HashMap<Integer,Integer>> temp){
		
		int resule[]=getSameValueArray(index);
		for (int i = 0; i < resule.length; i++) {
			for (int j = 0; j < dataSet.length; j++) {
				if (resule[i]==dataSet[j][index]) {
					if (temp.get(resule[i])!=null) {
						HashMap<Integer, Integer> t=temp.get(resule[i]);
						if(t.get(dataSet[j][4])!=null){
							t.put(dataSet[j][4], t.get(dataSet[j][4])+1);
						}else {
							t.put(dataSet[j][4], 1);
						}
						temp.put(resule[i], t);
					}
					else{
						HashMap<Integer, Integer> t=new HashMap<Integer, Integer>();
						t.put(dataSet[j][4], 1);
						temp.put(resule[i],t);
					}
				} 
			}
		}
	}
	
	public int[] getSameValueArray(int index) {
		TreeSet<Integer> values = new TreeSet<Integer>(new Comparator<Object>() {
			@Override
			public int compare(Object obj1, Object obj2) {
				int a = (int) obj1;
				int b = (int) obj2;
				return a-b;
			}
		});
		for (int i = 0; i < dataSet.length; i++) 
			values.add(dataSet[i][index]);
		
		int[] result = new int[values.size()];
		Object obj[]=values.toArray();
		for (int i = 0; i < obj.length; i++) {
			result[i]=(int)obj[i];
		}
		return result;
	}
	
	public int[][] pickUpSubArray(int value,int row,int index) {
		
		int sunDataSet[][]=new int[row][5];
		int line=0;
		for (int i = 0; i < dataSet.length; i++) {
				if (dataSet[i][index]==value) {
					for (int j = 0; j < dataSet[0].length; j++) {
						sunDataSet[line][j]=dataSet[i][j];
					}
					line++;
				}
		}
		return sunDataSet;
	}
}
