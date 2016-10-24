import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

import org.omg.CORBA.INTERNAL;


public class DTUtil {

	//给定一个属性列计算其信息增益率
	public  double getMaxGainRation(int index,HashMap<Integer, HashMap<Integer,Integer>> targetMap,int dataSet[][]){
		
		CalnumPerValuePerColumn(index, targetMap,dataSet);
		double gain=0.0;
		int sum=0;//每个分类的数量
		double split=0.0;//分裂信息
		int numarry[]=new int[targetMap.size()];
		int i=0;
		int total=0;
		Iterator iter = targetMap.entrySet().iterator();
		HashMap<Integer, Integer> targetTemp=new HashMap<>();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			HashMap<Integer, Integer> temp=(HashMap<Integer, Integer>)entry.getValue();
			Iterator iter1 = temp.entrySet().iterator();
			while (iter1.hasNext()) {
				Map.Entry entry1= (Map.Entry) iter1.next();
				sum+=(int)entry1.getValue();
				if (targetTemp.containsKey(entry1.getKey())) 
					targetTemp.put((Integer)entry1.getKey(), targetTemp.get(entry1.getKey())+(Integer)entry1.getValue());
				else 
					targetTemp.put((Integer)entry1.getKey(),(Integer)entry1.getValue());
			}
			total+=sum;
			numarry[i++]=sum;
			gain+=sum*getEntropy(temp,sum);//初始条件熵的计算
			sum=0;
		}
		
		//计算分裂信息
		for (int j = 0; j < numarry.length; j++) {
			double p=((1.0)*numarry[j])/total;
			split+=p*(Math.log(p)/Math.log((double)2));
		}
		gain/=total;//最终条件熵
		gain=getEntropy(targetTemp,total)-gain;//计算信息增益
		return gain/(-split);//计算信息增益率
		
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
    
	//计算一列数据按相同值划分的，每个值的数据个数
	public  void CalnumPerValuePerColumn(int index,HashMap<Integer, HashMap<Integer,Integer>> temp,int dataSet[][]){
		
		int result[]=getSameValueArray(index,dataSet);
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < dataSet.length; j++) {
				if (result[i]==dataSet[j][index]) {
					if (temp.get(result[i])!=null) {
						HashMap<Integer, Integer> t=temp.get(result[i]);
						if(t.get(dataSet[j][4])!=null){
							t.put(dataSet[j][4], t.get(dataSet[j][4])+1);
						}else {
							t.put(dataSet[j][4], 1);
						}
						temp.put(result[i], t);
					}
					else{
						HashMap<Integer, Integer> t=new HashMap<Integer, Integer>();
						t.put(dataSet[j][4], 1);
						temp.put(result[i],t);
					}
				} 
			}
		}
	}
	
	public int[] getSameValueArray(int index,int dataSet[][]) {
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
	
	public int[][] pickUpSubArray(int value,int row,int index,int data[][]) {
		
		int sunDataSet[][]=new int[row][5];
		int line=0;
		for (int i = 0; i < data.length; i++) {
				if (data[i][index]==value) {
					for (int j = 0; j < data[0].length; j++) {
						sunDataSet[line][j]=data[i][j];
					}
					line++;
				}
		}
		return sunDataSet;
	}
}
