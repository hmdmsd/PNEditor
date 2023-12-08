package org.pneditor.petrinet.models.binome6;

public class ArcPlaceTransition extends Arc {
	private boolean isZero;	// Indicates if the arc is for zero tokens
	private boolean isVideur;	// Indicates if the arc is a "videur" (clearing) arc
	public boolean isZero() {
		return isZero;
	}

	public void setZero(boolean isZero) {
		this.isZero = isZero;
	}

	public boolean isVideur() {
		return isVideur;
	}

	public void setVideur(boolean isVideur) {
		this.isVideur = isVideur;
	}


	public ArcPlaceTransition(int weight, boolean isZero, boolean isVideur) throws ArcException {
		super(weight);	// Constructor for ArcPlaceTransition
		this.isZero = isZero;	// Initialise isZero flag
		this.isVideur = isVideur; // Initialise isVideur flag
	}
	
	@Override
	public void updatePlace(Place place, int weight) throws ArcException, PlaceException {
		place.addTokens(weight);	// Update a place by adding tokens
	}
	
	public boolean isActivatable() {

		if (this.place.isFirable(this.weight)) {
			return true;	// Check if the place can fire the transition
		} else
			return false;
	}

	public void fireArc() throws PlaceException {
		this.place.removeTokens(this.weight);	// Remove tokens from the place when the arc is fired
		
		System.out.println("fired3 weight:"+this.weight);
	}


}
