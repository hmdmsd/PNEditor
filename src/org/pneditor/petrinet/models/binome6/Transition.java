package org.pneditor.petrinet.models.binome6;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Transition {
	private List<ArcPlaceTransition> incomingArcs;		// List of incoming arcs
	private List<ArcTransitionPlace> outgoingArcs;		// List of outgoing arcs
	
	public Transition() {
        incomingArcs = new ArrayList<ArcPlaceTransition>();
        outgoingArcs = new ArrayList<ArcTransitionPlace>();
	}
	
	public boolean isFirable() throws ArcException {
		boolean firable = true;
		int i = 0;
		while (firable && i<incomingArcs.size()) {
			ArcPlaceTransition arc=incomingArcs.get(i);
			firable = arc.isActivatable();	// Check if the transition is firable
			i+=1;
		}
		return firable;
	}
	
	public void fire() throws PlaceException {
		for (ArcPlaceTransition a : incomingArcs) {
			a.fireArc();	// Fire incoming arcs
		}
		for (ArcTransitionPlace a : outgoingArcs) {
			a.fireArc();	// Fire outgoing arcs
		}
	}
	
	public void addArc(Arc a) throws TransitionException, ArcException {
		if ( a instanceof ArcTransitionPlace) {
			boolean findSimilar = false;
			int i=0;
			while ((! findSimilar) && (i<outgoingArcs.size())) {
				if (outgoingArcs.get(i).hasSameProperties(a)) {
					findSimilar = true;
				}
				i++;
			}
			if (! findSimilar) {
				outgoingArcs.add((ArcTransitionPlace) a);	// Add an outgoing arc
			} else {
				throw new TransitionException();	// Throw an exception if a similar outgoing arc is found
			}
		} else {
			boolean findSimilar = false;
			int i=0;
			while ((! findSimilar) && (i<incomingArcs.size())) {
				if (incomingArcs.get(i).hasSameProperties(a)) {
					findSimilar = true;
				}
				i++;
			}
			if (! findSimilar) {
				incomingArcs.add((ArcPlaceTransition) a);	// Add an incoming arc
			} else {
				throw new TransitionException();	// Throw an exception if a similar incoming arc is found
			}
		}
	}
	
	public void removeArc(Arc a) throws TransitionException {
			if (outgoingArcs.contains(a)) {
				outgoingArcs.remove(a);	// Remove an outgoing arc
			} else {
				throw new TransitionException();	// Throw an exception if the arc is not found in outgoing arcs
			}
			if (incomingArcs.contains(a)) {
				incomingArcs.remove(a);	// Remove an incoming arc
			} else {
				throw new TransitionException();	// Throw an exception if the arc is not found in incoming arcs
			}
		}
	
	
	public void removeArcs() throws PlaceException, ArcException {
		for (Arc a : outgoingArcs) {
			a.getPlace().removeArc(a);	// Remove outgoing arcs from connected places
			a = null;
		}
		for (Arc a : incomingArcs) {
			a.getPlace().removeArc(a);	// Remove incoming arcs from connected places
			a = null;
		}
		outgoingArcs = null;	// Set outgoing arcs to null
		incomingArcs = null;	// Set incoming arcs to null
	}


}