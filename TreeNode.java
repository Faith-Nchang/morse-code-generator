/**
 * The external Tree Node for Linked Trees
 * @author Faith Fru Nchang
 */

public class TreeNode<T>{
	 private T data;
	 private TreeNode<T> leftChild;
	 private TreeNode<T> rightChild;
	 
	 /**
	  * Create a new TreeNode with left and right child set to null and data set to the dataNode
	  * @param dataNode - the data to be stored in the TreeNode
	  */
	 public TreeNode(T dataNode)
	 {
		 this.data = dataNode;
		 this.leftChild =null;
		 this.rightChild = null;
	 }
	 
	 /**
	  * used for making deep copies
	  * @param node - node to make copy of
	  */
	 public TreeNode(TreeNode<T> node)
	 {
		 this.data = node.data;
		 this.leftChild = node.leftChild;
		 this.rightChild = node.rightChild;
	 }
	 
	 /**
	  * gets the data within this TreeNode
	  * @return the data within the TreeNode
	  */
	 public T getData()
	 {
		 return this.data;
	 }
	 
	 /**
	  * updates the data field
	  * @param data - new data
	  */
	 public void setData(T data)
	 {
		 this.data = data;
	 }
	 /**
	  * gets the node's left child
	  * @return the left child
	  */
	 public TreeNode<T> getLeftChild()
	 {
		 return this.leftChild;
	 }
	 
	 /**
	  * updates the left child
	  * @param newNode - new left child
	  */
	 public void setLeftChild(TreeNode<T> newNode)
	 {
		 this.leftChild = newNode;
	 }
	 
	 /**
	  * gets the right child
	  * @return right child
	  */
	 public TreeNode<T> getRightChild()
	 {
		 return this.rightChild;
	 }
	 
	 /**
	  * updates the right child
	  * @param newNode - right child
	  */
	 public void setRightChild(TreeNode<T> newNode)
	 {
		 this.rightChild = newNode;
	 }
}
