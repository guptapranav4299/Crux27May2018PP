package L16_June24;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Garima Chhikara
 * @email garima.chhikara@codingblocks.com
 * @date Jun 24, 2018
 */

public class GenericTree {

	Scanner scn;

	private class Node {

		int data;
		ArrayList<Node> children = new ArrayList<>();
	}

	private Node root;

	public GenericTree(String str) {
		scn = new Scanner(str);
		this.root = takeInput(null, -1);
	}

	// ith child of parent
	private Node takeInput(Node parent, int ith) {

		// give prompt to user
		if (parent == null) {
			System.out.println("Enter the data for root node ?");
		} else {
			System.out.println("Enter the data for " + ith + " child of " + parent.data + " ?");
		}

		// take item input
		int item = scn.nextInt();

		// make a ew node
		Node nn = new Node();
		nn.data = item;

		// take input for no of children
		System.out.println("No of children for " + nn.data);
		int noc = scn.nextInt();

		// loop on children
		for (int i = 0; i < noc; i++) {

			// take input for each child
			Node child = takeInput(nn, i);

			// attach the child with present array list
			nn.children.add(child);
		}

		// return the newly created node
		return nn;

	}

	public void display() {
		System.out.println("-------------------");
		display(this.root);
		System.out.println("-------------------");
	}

	private void display(Node node) {

		// self work
		String str = node.data + " -> ";

		for (Node child : node.children) {
			str += child.data + " ";
		}
		str += ".";
		System.out.println(str);

		// smaller problem
		for (Node child : node.children) {
			display(child);
		}

	}

	public int size() {
		return size(this.root);
	}

	private int size(Node node) {

		int count = 0;

		for (Node child : node.children) {
			count += size(child);
		}

		return count + 1;

	}

	public int max() {
		return max(this.root);
	}

	private int max(Node node) {

		int tm = node.data;

		for (Node child : node.children) {
			int cm = max(child);
			if (cm > tm) {
				tm = cm;
			}
		}

		return tm;
	}

	public boolean find(int item) {
		return find(this.root, item);
	}

	private boolean find(Node node, int item) {

		if (node.data == item) {
			return true;
		}

		for (Node child : node.children) {

			boolean cf = find(child, item);
			if (cf)
				return true;

		}

		return false;
	}

	public int ht() {
		return ht(this.root);
	}

	private int ht(Node node) {

		int th = -1;

		for (Node child : node.children) {
			int ch = ht(child);

			if (ch > th) {
				th = ch;
			}
		}

		return th + 1;

	}

	public void mirror() {
		mirror(this.root);
	}

	private void mirror(Node node) {

		// smaller problem
		for (Node child : node.children) {
			mirror(child);
		}

		// self work
		int left = 0;
		int right = node.children.size() - 1;

		while (left < right) {

			Node ln = node.children.get(left);
			Node rn = node.children.get(right);

			node.children.set(left, rn);
			node.children.set(right, ln);

			left++;
			right--;
		}

	}

	private void mirrorAlternative(Node node) {

		// smaller problem
		for (Node child : node.children) {
			mirrorAlternative(child);
		}

		// self work
		ArrayList<Node> list = new ArrayList<>();

		for (int i = node.children.size() - 1; i >= 0; i--) {
			list.add(node.children.get(i));
		}

		node.children = list;

	}

	public void preorder() {
		preorder(this.root);
	}

	private void preorder(Node node) {

		// node print
		System.out.println(node.data);

		// child command
		for (Node child : node.children) {
			preorder(child);
		}

	}

	public void postorder() {
		postorder(this.root);
	}

	private void postorder(Node node) {

		// child command
		for (Node child : node.children) {
			postorder(child);
		}

		// self work of printing
		System.out.println(node.data);

	}

	public void traversal() {
		traversal(this.root);
	}

	private void traversal(Node node) {

		System.out.println("hii " + node.data);

		for (int i = node.children.size() - 1; i >= 0; i--) {
			System.out.println("going towards " + node.children.get(i).data);
			traversal(node.children.get(i));
			System.out.println("coming from " + node.children.get(i).data);
		}

		System.out.println("bye " + node.data);

	}

	// O(n*h)
	public void levelorderRecursive() {

		int th = this.ht();
		for (int i = 0; i <= th; i++) {
			printAtLevel(0, i, this.root);
		}
		System.out.println();
	}

	public void printAtLevel(int level, int ht, Node node) {

		if (level == ht) {
			System.out.print(node.data + " ");
			return;
		}

		for (Node child : node.children) {
			printAtLevel(level + 1, ht, child);
		}
	}

	// Time : O(n)
	public void levelorderIterative() {

		LinkedList<Node> queue = new LinkedList<>();
		queue.addLast(this.root);

		while (!queue.isEmpty()) {

			Node rn = queue.removeFirst();
			System.out.print(rn.data + " ");

			for (Node child : rn.children) {
				queue.addLast(child);
			}

		}

		System.out.println();

	}

	public void levelorderlwIteractive() {

		LinkedList<Node> queue = new LinkedList<>();
		LinkedList<Node> helper = new LinkedList<>();

		queue.addLast(this.root);

		while (!queue.isEmpty()) {

			Node rn = queue.removeFirst();

			System.out.print(rn.data + " ");

			for (Node child : rn.children) {
				helper.addLast(child);
			}

			if (queue.isEmpty()) {
				System.out.println();
				queue = helper;
				helper = new LinkedList<>();
			}

		}

	}

	public void levelorderzzIterative() {

		int count = 0;
		LinkedList<Node> queue = new LinkedList<>();
		LinkedList<Node> stack = new LinkedList<>();

		queue.addLast(this.root);

		while (!queue.isEmpty()) {

			Node rn = queue.removeFirst();

			System.out.print(rn.data + " ");

			if (count % 2 == 0) {
				for (Node child : rn.children) {
					stack.addFirst(child);
				}
			} else {
				for (int i = rn.children.size() - 1; i >= 0; i--) {
					stack.addFirst(rn.children.get(i));
				}
			}
			if (queue.isEmpty()) {
				System.out.println();
				queue = stack;
				stack = new LinkedList<>();
				count++;
			}

		}

	}

	public void rightView() {

		int th = this.ht();
		for (int i = 0; i <= th; i++) {
			ArrayList<Integer> list = new ArrayList<>();
			printAtLevel(0, i, this.root, list);
			System.out.println(list.get(list.size() - 1));
		}
		System.out.println();
	}

	public void printAtLevel(int level, int ht, Node node, ArrayList<Integer> list) {

		if (level == ht) {
			list.add(node.data);
			return;
		}

		for (Node child : node.children) {
			printAtLevel(level + 1, ht, child, list);
		}
	}

	public void levelorderzzRecursive() {

		int th = this.ht();
		for (int i = 0; i <= th; i++) {
			ArrayList<Integer> list = new ArrayList<>();
			printAtLevel(0, i, this.root, list);

			if (i % 2 == 0) {
				for (int j = 0; j < list.size(); j++) {
					System.out.print(list.get(j) + " ");
				}
			} else {
				for (int j = list.size() - 1; j >= 0; j--) {
					System.out.print(list.get(j) + " ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	private class HeapMover {
		int size = 0;
		int max = Integer.MIN_VALUE;
		int ht = 0;
		boolean find = false;

		Node pred;
		Node succ;
		Node jl;
	}

	public void multiSolver(int item) {

		HeapMover mover = new HeapMover();

		multiSolver(mover, this.root, 0, item);

		System.out.println("Max:" + mover.max);
		System.out.println("Size:" + mover.size);
		System.out.println("Find:" + mover.find);
		System.out.println("Ht:" + mover.ht);
		System.out.println("Pred:" + (mover.pred == null ? "null" : mover.pred.data));
		System.out.println("Succ:" + (mover.succ == null ? "null" : mover.succ.data));

	}

	private void multiSolver(HeapMover mover, Node node, int level, int item) {

		mover.size++;

		if (mover.ht < level) {
			mover.ht = level;
		}

		if (mover.max < node.data) {
			mover.max = node.data;
		}

		if (mover.find == true && mover.succ == null) {
			mover.succ = node;
		}

		if (node.data == item) {
			mover.find = true;
		}

		if (mover.find == false) {
			mover.pred = node;
		}

		if (node.data > item) {

			if (mover.jl == null || node.data < mover.jl.data) {
				mover.jl = node;
			}
		}

		for (Node child : node.children) {
			multiSolver(mover, child, level + 1, item);
		}

	}

	public boolean isMirror(GenericTree other) {
		return isMirror(this.root, other.root);
	}

	private boolean isMirror(Node tnode, Node onode) {

		if (tnode.children.size() != onode.children.size()) {
			return false;
		}

		for (int i = 0; i < tnode.children.size(); i++) {

			Node tc = tnode.children.get(i);
			Node oc = onode.children.get(tnode.children.size() - 1 - i);

			boolean rr = isMirror(tc, oc);

			if (rr == false) {
				return false;
			}
		}

		return true;

	}

	public boolean isMirrorSS() {

		return isMirror(root, root);

	}

}
