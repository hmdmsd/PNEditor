package org.pneditor.petrinet.models.binome6;

public class ArcTransitionPlace extends Arc {
	public ArcTransitionPlace(int weight) throws ArcException {
		super(weight);		// Constructor for ArcTransitionPlace
	}

	@Override
	public void updatePlace(Place place, int weight) throws ArcException, PlaceException {
		place.removeTokens(weight);		// Update a place by removing tokens
	}
	
	public void fireArc() throws PlaceException {
		this.place.addTokens(this.weight);		// Add tokens to the place when the arc is fired

	}
	

}