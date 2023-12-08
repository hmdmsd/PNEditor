package org.pneditor.petrinet.models.binome6;

import java.util.LinkedList;
import java.util.List;


public class Place {
	private int nbrTokens;		// Number of tokens in the place
	private List<Arc> arcs;		// List of arcs connected to this place
	
	public Place(int tokens) throws PlaceException{
		if (tokens<0) {
			throw new PlaceException();		// Throw an exception for negative tokens
		}
		this.nbrTokens = tokens;
		arcs = new LinkedList<Arc>();
	}
	
	public void addTokens(int weight) throws PlaceException{
			nbrTokens += weight;	// Add tokens to the place
	}
	
	public void removeTokens(int weight) throws PlaceException{
		nbrTokens -= weight;	// Remove tokens from the place
	}

	
	public boolean isFirable(int weight) {
		return nbrTokens>=weight;	// Check if the place has enough tokens to fire
	}
	
	public boolean isEmpty() {
		return nbrTokens==0;	// Check if the place is empty
	}
	
	public void empty() {
		this.nbrTokens = 0;		// Empty the place by setting tokens to zero
	}
	
	public void removeArc(Arc a) throws PlaceException {
		if (arcs.contains(a)) {
			arcs.remove(a);		// Remove an arc from the list
		} else {
			throw new PlaceException();		// Throw an exception if the arc is not in the list
		}
	}
	
	public void addArc(Arc a) throws PlaceException{
		arcs.add(a);		// Add an arc to the list
	}
	
	public int getTokens() {
		return nbrTokens;		// Get the current number of tokens in the place
	}
	

	public void removeArcs() throws TransitionException, ArcException {
		for (Arc a : arcs) {
			a.getTransition().removeArc(a);		// Remove arcs from connected transitions
			a = null;		// Set the arc to null
		}
		arcs = null;		// Set the list of arcs to null
	}
	
}
