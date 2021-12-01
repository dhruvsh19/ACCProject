package ACCProject;
import java.io.File;
import java.util.*;

//using Inverted Index and Hash table
public class PreSearch {
	public Map<Integer,String> sources;
    public HashMap<String, HashSet<Integer>> index;
    public TrieST<Integer> st = new TrieST<Integer>();
	
	PreSearch(){
		prepareSearch();
	}
	
	//creating inverted index
	public void prepareSearch() {
		sources = new HashMap<Integer,String>();
        index = new HashMap<String, HashSet<Integer>>();
		File directory = new File("W3cWebPages/UwinText/");
		File files[] = directory.listFiles();
		
		for(int i =0; i<files.length;i++) {
			
			String[] words = getWordsFromFile(files[i]);
			sources.put(i,files[i].getName());
			createMap(words,i,files[i].getName());
		}
	}
	
	//Inserting value in inverted index
	public void createMap(String[] words , int i, String fileName) {
		for(String word:words) {
			word = word.toLowerCase();
            if (!index.containsKey(word))
                index.put(word, new HashSet<Integer>());
            index.get(word).add(i);
            st.put(word, i);
		}
		
	}
	//kfiniding keys using prefix for autocompletion
		public String[] findPrefix(String key) {
			
			List<String> temp = new ArrayList<String>();
			for(String s : st.keysWithPrefix(key))
			{
				temp.add(s);
			}
			String[] stockArr = new String[temp.size()];
			stockArr = temp.toArray(stockArr);
			return stockArr;

		}
	
	//Search words
	public ArrayList<String> find(String phrase){
    	ArrayList<String> fileNames;
    	try {
    		phrase = phrase.toLowerCase();
    		fileNames = new ArrayList<String>();
	        String[] words = phrase.split("\\W+");
	        String hashKey = words[0].toLowerCase();
	        if(index.get(hashKey) == null) {
	        	 System.out.println("Not found!!!");
	        	return null;
	        }
	        HashSet<Integer> res = new HashSet<Integer>(index.get(hashKey));	        
	        for(String word: words){
	            res.retainAll(index.get(word));
	        }
	
	        if(res.size()==0) {
	            System.out.println("Not found!!!");
	            return null;
	        }
	        for(int num : res){
	        	fileNames.add(sources.get(num));
	        }
    	}catch(Exception e) {
    		 System.out.println("Not found!!!");
    		 System.out.println("Exception Found:" + e.getMessage());
    		 return null;
    	}  
    return fileNames;
    }	
	
	//Returns words
	public static String[] getWordsFromFile(File f) {
		In in = new In(f);
		String text = in.readAll();
		text = text.replaceAll("[^a-zA-Z0-9\\s]", ""); 
		String[] words = text.split(" ");		
		
		return words;
		
	}
}
