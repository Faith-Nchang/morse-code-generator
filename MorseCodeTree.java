import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

/**
 * This is a MorseCodeTree which is specifically used for the conversion of morse code to english
 * It relies on a root (reference to root of the tree)
 * The root is set to null when the tree is empty.
 * The class uses an external generic TreeNode class which consists of a reference to the data and a reference to the left and right child. The TreeNode is parameterized as a String, TreeNode This class uses a private member root (reference to a TreeNode)
 * The constructor will call the buildTree
 * @author Faith Fru Nchang
 */
public class MorseCodeTree<T> implements LinkedConverterTreeInterface<T>{
	private TreeNode<String> treeRoot = null;
	private TreeMap<String, String> treeMap;
	private ArrayList<String> tree;
	
	/**
	 * Constructor - calls the buildTree method
	 */
	public MorseCodeTree()
	{
		buildTree();
		tree = new ArrayList<>();
	}
	
	/**
	 * Returns a reference to the root
	 * @return reference to root
	 */
	public TreeNode<T> getRoot()
	{
		return (TreeNode<T>) treeRoot;
		
	}
	
	/**
	 * sets the root of the Tree
	 * @param newNode a TreeNode<T> that will be the new root
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void setRoot(TreeNode<T> newNode) 
	{
		this.treeRoot = (TreeNode<String>) newNode;
		
	}
	
	
	/**
	 * Adds result to the correct position in the tree based on the code
	 * This method will call the recursive method addNode
	 * 
	 * @param code the code for the new node to be added
	 * 
	 */
	@Override
	public void insert(T code, T result)
    {
		 addNode((TreeNode<T>) this.treeRoot, code, result);
	}
	 

		
	/**
	 * Fetch the data in the tree based on the code
	 * 		 
	 * This method will call the recursive method fetchNode 
	 * @param code the code that describes the traversals within the tree
	 * @return the result that corresponds to the code
	 * 		 */
	public T fetch(String code)
	{
		return (T) fetchNode((TreeNode<T>)this.treeRoot, (T) code);
	}
		
	/**
	 * This method builds the LinkedConverterTree by inserting TreeNodes<T>
	 * into their proper locations
	*/
	@SuppressWarnings("unchecked")
	public void buildTree()
	{
		// creates a mapping for each letter of the alphabet and the corresponding moreseCode
		treeMap = new TreeMap<String, String>();
		treeRoot = new TreeNode<String>("");
		String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s","t", "u", "v", "w", "x" ,"y","z"};
		String[] morseCode = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
		
		for (int i =0; i < letters.length; i++)
		{
			// insert a mapping for all the letters of the alphabet			
			treeMap.put(morseCode[i], letters[i]);	
			// adds each letter to the morse code tree based on the morse code
			this.addNode((TreeNode<T>) treeRoot, (T)morseCode[i], (T)letters[i]);
		}
	
	}
		
	/**
	 * This operation is not supported for a LinkedConverterTree
	 * 		 * @return reference to the current tree
	 * @throws UnsupportedOperationException
	 */
	public LinkedConverterTreeInterface<T> update() throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException();
	}
		
	/**
	 * This operation is not supported for a LinkedConverterTree
	 * @param data data of node to be deleted
	 * @return reference to the current tree
	 * @throws UnsupportedOperationException
	 */
	@Override
	public LinkedConverterTreeInterface<T> delete(T data) throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException();
	}
		
	/**
	 * This is a recursive method that adds element to the correct position 
	 * in the tree based on the code. 
	 * @param root the root of the tree for this particular recursive instance of addNode
	 * @param code the code for this particular recursive instance of addNode
	 * @param letter the data of the new TreeNode to be added
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void addNode(TreeNode<T> root, T code, T letter)
	{
		String codeS ="";
		if (code!=null)
			codeS = code.toString();
		// if the tree is empty, adds to the beginning
		if (root == null)
		{
			root =  new TreeNode<T>((T) "");
		}
		else if (code == null)
		{
			// ignore it, the letter is not inserted
		}
		// if the code is empty, the letter should be inserted where the current root is
		else if (codeS.isEmpty())
		{
			root.setData(letter);		
		}
		// if the next character is '.', it traverses left
		else if (codeS.charAt(0) == '.')
		{
			// creates a new node to the left of the current root if the left child is empty
			if (root.getLeftChild() == null)
			{
				root.setLeftChild((TreeNode<T>) new TreeNode<String>(""));
			}
			addNode(root.getLeftChild(), (T) codeS.substring(1), letter);
		}
		// if the next character is '-', traverses right
		else if (codeS.charAt(0) == '-' || codeS.charAt(0) == '_')
		{
			// creates a new node to the right of the current root if the right child is empty
			if (root.getRightChild() == null)
			{
				root.setRightChild((TreeNode<T>) new TreeNode<String>(""));
			}
			addNode(root.getRightChild(), (T) codeS.substring(1), letter);
		}
		// ignores unwanted characters
		else
		{
			addNode(root, (T) codeS.substring(1), letter);
		}
	}
	/**
	 * This is the recursive method that fetches the data of the TreeNode
	 * that corresponds with the code 
	 * @param root the root of the tree for this particular recursive instance of addNode
	 * @param code the code for this particular recursive instance of fetchNode
	 * @return the data corresponding to the code
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T fetchNode(TreeNode<T> root, T code)
	{
		// if the root is null the tree is empty. If the code is null, there's no mapping to a null code
		if (code == null || root == null)
		{
			return null;
		}
		String codeS = code.toString();  
		
		// if the code is empty, the data is the current node's data
		if (codeS.isEmpty())
		{
			return root.getData();
		}
		// if the next character in the code is '.', traverses left
		else if (codeS.charAt(0) == '.')
		{
			if (root.getLeftChild() == null)
			{
				root.setLeftChild(new TreeNode<>((T) ""));
			}
			else
				return fetchNode(root.getLeftChild(), (T) codeS.substring(1));
		}
		// if the next character in the code is '-', traverses right
		else if (codeS.charAt(0) == '-')
		{
			if (root.getRightChild() == null)
			{
				root.setRightChild(new TreeNode<>((T) ""));
			}
				return fetchNode(root.getRightChild(), (T) codeS.substring(1));
		}
		
		return null;
	}
	
	/**
	 * The recursive method to put the contents of the linked converter tree in an ArrayList<T> 
	 * LNR (Inorder)
	 * @param root the root of the tree for this particular recursive instance
	 * @param list the ArrayList that will hold the contents of the tree in LNR order
	 */
	@Override
	public void LNRoutputTraversal(TreeNode<T> root, ArrayList<T> list)
	{
		if (root != null) {
			
		// traverses the left subtree
		LNRoutputTraversal(root.getLeftChild(), list);
			
		list.add(root.getData());
		// traverses the right subtree
		LNRoutputTraversal(root.getRightChild(), list);
		
		}
			
	}
	
	
	/**
	 * Returns an ArrayList of the items in the linked converter Tree in LNR (Inorder) Traversal order
	 * Used for testing to make sure tree is built correctly
	 * @return an ArrayList of the items in the linked Tree
	 */
	@Override
	public ArrayList<T> toArrayList()
	{
		// stores all the items in the morse code tree in the tree ArrayList in Inorder traversal
		LNRoutputTraversal((TreeNode<T>) this.treeRoot, (ArrayList<T>) tree);
		
		// ArrayList of all the items in the list
		return (ArrayList<T>) tree;
	
	}

	

}
