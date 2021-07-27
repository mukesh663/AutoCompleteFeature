import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TrieNode {
	Map<Character, TrieNode> children;
	char c;
	boolean isLeaf;
	
	TrieNode(char c) {
		this.c = c;
		children = new HashMap<>();
	}
	
	void insert(String word) {
		if (word == null || word.isEmpty())
			return;
		char first = word.charAt(0);
		TrieNode child = children.get(first);
		
		if (child == null) {
			child = new TrieNode(first);
			children.put(first, child);
		}	
		
		if (word.length() > 1)
            child.insert(word.substring(1));   //Recursion
        else
            child.isLeaf = true;
	}
}

public class Trie {
	
	TrieNode root;
	
	Trie(List<String> words) {               //constructor
		root = new TrieNode('\0');
		
		for (String word : words)
			root.insert(word);
	}
	
	public boolean find(String prefix, boolean exact) {
        TrieNode rootNode = root;
        for (char c : prefix.toCharArray()) {
            rootNode = rootNode.children.get(c);
            if (rootNode == null)
                return false;
        }
        return !exact || rootNode.isLeaf;
    }
 
    public boolean find(String prefix) {
        return find(prefix, false);
    }
	
	public void suggestHelper(TrieNode root, List<String> list, StringBuffer curr) {
        if (root.isLeaf) {
            list.add(curr.toString());
        }
 
        if (root.children == null || root.children.isEmpty())
            return;
 
        for (TrieNode child : root.children.values()) {
            suggestHelper(child, list, curr.append(child.c));
            curr.setLength(curr.length() - 1);
        }
    }
	
	public List<String> suggest(String prefix) {
        List<String> list = new ArrayList<>();
        TrieNode rootNode = root;
        StringBuffer curr = new StringBuffer();
        for (char c : prefix.toCharArray()) {
            rootNode = rootNode.children.get(c);
            if (rootNode == null)
                return list;
            curr.append(c);
        }
        suggestHelper(rootNode, list, curr);
        return list;
    }
}
