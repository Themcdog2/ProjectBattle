package myPathFinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.projectbattle.game.World;

public class Pathfind {
	
	public static int c_n = 10;
	public static int c_d = 14;
	
	public Pathfind(){
		
	}
	
	public static ArrayList<Node> search(Node beginNode, Node endNode){
		//Implementation by Will Allen
		//Basically, it's probably broken
		//All vectors are in tile coordinates, not world coordinates
			ArrayList<Node> openList = new ArrayList<Node>();
			openList.add(beginNode);
			ArrayList<Node> closedList = new ArrayList<Node>();
			Node currentNode;
			Node openNeb;
			beginNode.g = 0;
			beginNode.f = beginNode.g + cost(beginNode, endNode);
			while(openList.size() > 0){
				
				Collections.sort(openList);
				currentNode = openList.get(0);
				//System.out.println(closedList);
				if(currentNode.x == endNode.x && currentNode.y == endNode.y){
					return constructPath(currentNode, endNode);
				}
				
				openList.remove(currentNode);
				closedList.add(currentNode);
				
				for(Node neb: findNeighbors(currentNode, endNode)){
					if(!closedList.contains(neb)){
						neb.f = neb.g + cost(neb, endNode);
						if(!openList.contains(neb)){
							openList.add(neb);
						}else{
							 openNeb = neb;
							 if(neb.g < openNeb.g){
								 openNeb.g = neb.g;
								 openNeb.parentNode = neb.parentNode;
							 }
						}
					}
				}
				
			}
			
			return null;
			
		}
	
	public static ArrayList<Node> findNeighbors(Node origin, Node endNode){
		ArrayList<Node> tempList = new ArrayList<Node>();
		World.refreshBlockedTileArray();
		try{
		if(World.blockedTiles[origin.x+1][origin.y] != true && isInBounds(World.blockedTiles, origin.x+1) && isInBounds(World.blockedTiles, origin.y)){
			tempList.add(new Node(origin.x+1, origin.y, origin, endNode, origin.g + c_n));
		}
		if(World.blockedTiles[origin.x+1][origin.y+1] != true && isInBounds(World.blockedTiles, origin.x+1) && isInBounds(World.blockedTiles, origin.y+1)){
			tempList.add(new Node(origin.x+1, origin.y+1, origin, endNode, origin.g + c_n));
		}
		if(World.blockedTiles[origin.x][origin.y+1] != true && isInBounds(World.blockedTiles, origin.x) && isInBounds(World.blockedTiles, origin.y+1)){
			tempList.add(new Node(origin.x, origin.y+1, origin, endNode, origin.g + c_n));
		}
		if(World.blockedTiles[origin.x-1][origin.y] != true && isInBounds(World.blockedTiles, origin.x-1) && isInBounds(World.blockedTiles, origin.y)){
			tempList.add(new Node(origin.x-1, origin.y, origin, endNode, origin.g + c_n));
		}
		if(World.blockedTiles[origin.x-1][origin.y-1] != true && isInBounds(World.blockedTiles, origin.x-1) && isInBounds(World.blockedTiles, origin.y-1)){
			tempList.add(new Node(origin.x-1, origin.y-1, origin, endNode, origin.g + c_n));
		}
		if(World.blockedTiles[origin.x][origin.y-1] != true && isInBounds(World.blockedTiles, origin.x) && isInBounds(World.blockedTiles, origin.y-1)){
			tempList.add(new Node(origin.x, origin.y-1, origin, endNode, origin.g + c_n));
		}
		if(World.blockedTiles[origin.x+1][origin.y-1] != true && isInBounds(World.blockedTiles, origin.x+1) && isInBounds(World.blockedTiles, origin.y-1)){
			tempList.add(new Node(origin.x+1, origin.y-1, origin, endNode, origin.g + c_d));
		}
		if(World.blockedTiles[origin.x-1][origin.y+1] != true && isInBounds(World.blockedTiles, origin.x-1) && isInBounds(World.blockedTiles, origin.y+1)){
			tempList.add(new Node(origin.x-1, origin.y+1, origin, endNode, origin.g + c_d));
		}
		}catch(IndexOutOfBoundsException e){
			
		}
		return tempList;
		
		
	}
	
	public static boolean isInBounds(boolean[][] blockedTiles, int index){
		
		if((index >= 0) && (index < blockedTiles.length-1)){
			//System.out.println("all godo");
			//System.out.println(index);
			return true;
		}else{
			System.out.println(index);
		}
		return false;
	}
	
	public static ArrayList<Node> constructPath(Node node, Node endNode){
		ArrayList<Node> tempPath = new ArrayList<Node>();
		tempPath.add(endNode);
		while(node.parentNode != null){
			node = node.parentNode;
			tempPath.add(node);
		}
		return tempPath;
		
	}
	
	public static int cost(Node node, Node endNode){
		int d_max = Math.max(Math.abs(node.x - endNode.x) , Math.abs(node.y - endNode.y));
		int d_min = Math.min(Math.abs(node.x - endNode.x) , Math.abs(node.y - endNode.y));
	
		return c_d * d_min + c_n * (d_max - d_min);
	}

}
