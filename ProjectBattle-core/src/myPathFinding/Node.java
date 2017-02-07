package myPathFinding;

import com.badlogic.gdx.math.Vector2;

public class Node implements Comparable<Node>{
	
	Node parentNode;
	Node endNode;
	public int f;
	public int g;
	public int h;
	
	public Vector2 tilePosition = new Vector2();
	public int x;
	public int y;
	
	

	public Node(int tileX, int tileY){
		tilePosition.x = tileX;
		tilePosition.y = tileY;
		x = tileX;
		y = tileY;

		

		f = g + h;
		
	}
	
	public Node(int tileX, int tileY, Node paNode, Node eNode){
		tilePosition.x = tileX;
		tilePosition.y = tileY;
		x = tileX;
		y = tileY;
		parentNode = paNode;
		endNode = eNode;
		
		g = getG(this);
		h = Pathfind.cost(this, endNode);
		f = g + h;
		
	}
	
	public Node(int tileX, int tileY, Node eNode){
		tilePosition.x = tileX;
		tilePosition.y = tileY;
		x = tileX;
		y = tileY;
		
		endNode = eNode;
		

	}
	
	public Node(int tileX, int tileY, Node paNode, Node eNode, int g){
		tilePosition.x = tileX;
		tilePosition.y = tileY;
		x = tileX;
		y = tileY;
		parentNode = paNode;
		endNode = eNode;
		
		this.g = g;
		h = Pathfind.cost(this, endNode);
		f = g + h;
		
	}
	
	public int getG(Node n){
		return getG(n.parentNode) + Pathfind.cost(parentNode, this);
	}
	
	public int compareTo(Node n)
	{
	     return(this.f - n.f);
	}
	

}
