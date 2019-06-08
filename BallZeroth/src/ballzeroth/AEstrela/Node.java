package ballzeroth.astar;

import ballzeroth.main.*;
import java.awt.Rectangle;
import java.awt.Graphics;

public class Node extends Rectangle {

    /**
	 * id do terreno ao ser renderizado
         *      0 == grama
         *      1 == pedra
         * 
	 */
        public int terrainID;
    
	/**
	 * How much it costs to move orthogonally from one node to another.
	 */
	protected static final int MOVEMENT_COST = 10;

	/**
	 * The node's X position on the map.
	 */
	private int x;

	/**
	 * The node's Y position on the map.
	 */
	private int y;

	/**
	 * If the node is not a wall and can be walked through.
	 */
	private boolean walkable;

	/**
	 * Used for when getting the resulting path. The node prior to this node.
	 */
	private Node parent;

	/**
	 * The cost of getting from the first node to this node.
	 */
	private int g;

	/**
	 * A heuristic that estimates the cost of the cheapest path from this node
	 * to the goal.
	 */
	private int h;

	/**
	 * Creates a simple node.
	 * 
	 * @param x
	 *            The node's X position on the map.
	 * @param y
	 *            The node's Y position on the map.
	 * @param walkable
	 *            If the node is not a wall and can be walked through.
	 */
	public Node(int x, int y, int size, int terrainID, boolean walkable)
	{
		this.x = x;
		this.y = y;
		this.walkable = walkable;
                
                setBounds(x, y, size, size);
                this.terrainID = terrainID;
        }
        
	/**
	 * Sets the G score based on the parent's G score and the movement cost.
	 * 
	 * @param parent
	 *            The node prior to this one.
	 */
	public void setG(Node parent)
	{
		g = (parent.getG() + MOVEMENT_COST);
	}

	/**
	 * Calculates and return the G score, without changing it on the class.
	 * 
	 * @param parent
	 *            The node prior to this one.
	 * @return This node's G score.
	 */
	public int calculateG(Node parent)
	{
		return (parent.getG() + MOVEMENT_COST);
	}
        
        /**
	 * @return The cost of getting from the first node to this node.
	 */
	public int getG()
	{
		return g;
	}

	/**
	 * @return A heuristic that estimates the cost of the cheapest path from
	 *         this node to the goal.
	 */
	public int getH()
	{
		return h;
	}

	/**
	 * Sets the H score based on the goal's position.
	 * 
	 * @param goal
	 *            The final node on the path.
	 */
	public void setH(Node goal)
	{
		h = (Math.abs(getXAxis() - goal.getXAxis()) + Math.abs(getYAxis() - goal.getYAxis())) * MOVEMENT_COST;
	}

	/**
	 * @return The node's X position on the map.
	 */
	public int getXAxis()
	{
		return x;
	}

	/**
	 * Sets the node's X position on the map;
	 * 
	 * @param x
	 *            The X position to be set.
	 */
	public void setCoordX(int x)
	{
		this.x = x;
	}

	/**
	 * @return The node's Y position on the map.
	 */
	public int getYAxis()
	{
		return y;
	}

	/**
	 * Sets the node's Y position on the map;
	 * 
	 * @param y
	 *            The Y position to be set.
	 */
	public void setY(int y)
	{
		this.y = y;
	}

	/**
	 * @return True if the node is not a wall and can be walked through, false
	 *         otherwise.
	 */
	public boolean isWalkable()
	{
		return walkable;
	}

	/**
	 * Sets if the node is not a wall and can be walked through.
	 * 
	 * @param walkable
	 *            Can you walk through this node?
	 */
	public void setWalkable(boolean walkable)
	{
		this.walkable = walkable;
	}

	/**
	 * @return The node prior to this node.
	 */
	public Node getParent()
	{
		return parent;
	}

	/**
	 * Sets the node prior to this one.
	 * 
	 * @param parent
	 *            The node to be set as this node's parent.
	 */
	public void setParent(Node parent)
	{
		this.parent = parent;
	}

	/**
	 * @return The G score + the H score.
	 */
	public int getF()
	{
		return g + h;
	}

	@Override
	public boolean equals(Object o)
	{
		if (o == null)
			return false;
		if (!(o instanceof Node))
			return false;
		if (o == this)
			return true;

		Node n = (Node) o;
		if (n.getX() == x && n.getY() == y && n.isWalkable() == walkable)
			return true;
		return false;
	}
        
        public void draw(Graphics g) {
                g.drawImage(Screen.tileset_ground[terrainID], x, y, width, height, null);
        }
}
