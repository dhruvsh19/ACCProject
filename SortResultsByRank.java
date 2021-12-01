package ACCProject;
import java.io.File;
import java.util.*;


public class SortResultsByRank {
	//Sorting the values in the HashMap in descending order
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static HashMap<String,Integer> sortValuesInverse(HashMap<String,Integer> map){		
		List list = new LinkedList(map.entrySet());   
		Collections.sort(list, new Comparator(){public int compare(Object o1, Object o2){return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());}});   
		HashMap<String,Integer> sortedHashMap = new LinkedHashMap<String,Integer>();  
		for (Iterator it = list.iterator(); it.hasNext();)   
		{  
		 Map.Entry<String,Integer> entry = (Map.Entry<String,Integer>) it.next();  
		sortedHashMap.put(entry.getKey(), entry.getValue());  
		}   
		return sortedHashMap;  
	}
	
	
	//Sorting in the Descending order.
	public static Map<String,Integer> sortByRank(ArrayList<String> as, String phrase) {
		HashMap<String,Integer> wordCount = new HashMap<String,Integer>();
		
		for(String fileName : as) {
			String[] words = PreSearch.getWordsFromFile(new File("W3cWebPages/UwinText/"+fileName));
			for(String word:words) {
				if(word.toLowerCase().equals(phrase.split("\\W+")[0])) {
					if(wordCount.containsKey(fileName)) {
						wordCount.put(fileName, wordCount.get(fileName)+1);					
					}
					else {
						wordCount.put(fileName, 1);
					}
				}			
			}
		}
		Map<String,Integer> sortedMap = sortValuesInverse(wordCount);
		return sortedMap;
	}
}
