import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {

	public static void main(String[] args) {
		int[] nums = {1,0, 1,1}; 
		int k = 1;
		System.out.println(coolSolution(nums, k));
	}

	public static boolean coolSolution(int[] nums, int k) {
		Set<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++){
            if(i > k) set.remove(nums[i-k-1]);
            if(!set.add(nums[i])) return true;
        }
        return false;
	}
	
	private static Map<Integer, Integer> sortByComparator(
			Map<Integer, Integer> unsortMap) {

		// Convert Map to List
		List<Map.Entry<Integer, Integer>> list = new LinkedList<Map.Entry<Integer, Integer>>(
				unsortMap.entrySet());

		// Sort list with comparator, to compare the Map values
		Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
			public int compare(Map.Entry<Integer, Integer> o1,
					Map.Entry<Integer, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		// Convert sorted map back to a Map
		Map<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();
		for (Iterator<Map.Entry<Integer, Integer>> it = list.iterator(); it
				.hasNext();) {
			Map.Entry<Integer, Integer> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

	public static boolean containsNearbyDuplicate(int[] nums, int k) {
		// key is index, value is the number
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int index = 0; index < nums.length; index++) {
			map.put(index, nums[index]);
		}
		Map<Integer, Integer> sortedMap = sortByComparator(map);
		Iterator iterator = sortedMap.entrySet().iterator();
		Integer baseNum;
		List<Integer> indexList = new ArrayList<Integer>();
		List<Map.Entry> mapList = new ArrayList<Map.Entry>(sortedMap.entrySet());

		for (int i = 0; i < mapList.size(); i++) {
			Map.Entry pair = mapList.get(i);
			baseNum = (Integer) pair.getValue();
			indexList.add((Integer) pair.getKey());
			int j = i + 1;
			while (j < mapList.size()
					&& mapList.get(j).getValue().equals(baseNum)) {
					indexList.add((Integer) mapList.get(j).getKey());
					j++;
			}
		
			if(indexList.size()==1){
				indexList.clear();
				continue;
			}
			i = j-1;
			Collections.sort(indexList);
			for(int m=0; m<indexList.size()-1; m++){
				if(indexList.get(m+1)-indexList.get(m)<=k){
					return true;
				}
			}
				indexList.clear();
		}
		return false;
	}
}