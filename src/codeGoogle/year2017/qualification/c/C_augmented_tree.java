package codeGoogle.year2017.qualification.c;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class C_augmented_tree {
	
	public static void main(String[] args) throws IOException {
		
		URL url = C_augmented_tree.class.getResource("C-small-practice-2.in");
		FileReader fr = new FileReader(new File(url.getPath()));
		BufferedReader br = new BufferedReader(fr);
		
		int T = Integer.parseInt(br.readLine());
		
		for (int _case = 0; _case < T; _case++) {
			List<String> tokens = Arrays.asList(br.readLine().split(" "));
			List<Long> iTokens = tokens.stream().map(x -> Long.valueOf(x)).collect(Collectors.toList());
			
			long N = iTokens.get(0);
			long K = iTokens.get(1);
			
			Node root = new Node(N);
			
			for (int person = 0; person < K-1; person++) {
				Node curr = root;
				while (curr.getLeft() != null) {
					if (curr.getLeft().getValue() >= curr.getRight().getValue()) {
						curr = curr.getLeft();
					} else {
						curr = curr.getRight();
					}
				}
				
				Node newLeft = new Node(Math.floorDiv(curr.getValue(), 2));
				Node newRight = new Node(Math.floorDiv(curr.getValue(), 2));
				
				if (curr.getValue() % 2 == 0) {
					newLeft.setValue(newLeft.getValue() - 1);
				}
				
				curr.setLeft(newLeft);
				curr.setRight(newRight);
				
				updateUpwards(curr);
			}
			
			long focus = root.getValue();
			long max_d = Math.floorDiv(focus, 2);
			long min_d = Math.floorDiv(focus, 2);
			
			if (focus % 2 == 0) {
				min_d -= 1;
			}

			System.out.printf("Case #%d: %d %d\n", _case + 1, max_d, min_d);
		}
	}
	
	private static void updateUpwards(Node curr) {
		while (curr != null) {
			curr.setValue(Math.max(curr.getLeft().getValue(), curr.getRight().getValue()));
			curr = curr.getParent();
		}
	}
	
}

class Node {
	
	private Node parent;
	private Node left, right;
	private long value;
	
	public Node(long value) {
		this.value = value;
		this.parent = null;
		this.left = null;
		this.right = null;
	}
	
	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Node getLeft() {
		return left;
	}
	
	public void setLeft(Node left) {
		this.left = left;
		left.setParent(this);
	}
	
	public Node getRight() {
		return right;
	}
	
	public void setRight(Node right) {
		this.right = right;
		right.setParent(this);
	}
	
	public long getValue() {
		return value;
	}
	
	public void setValue(long value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		long leftValue = left == null ? -1 : left.getValue();
		long rightValue = right == null ? -1 : right.getValue();
		
		return String.format("%d, %d, %d", leftValue, value, rightValue);
	}
}