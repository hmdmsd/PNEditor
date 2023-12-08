package org.pneditor.petrinet.models.binome6;

public abstract class Arc {
	protected int weight;	// Weight of the arc
	protected Transition transition ;	// The connected transition
	protected Place place ;		// The connected place

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public Arc(int weight) throws ArcException {
		if (weight<=0) {
			throw new ArcException();	// Throw an exception for non-positive weight
		} else {
			this.weight =weight;
		}
	}
	
	public void updateWeight(int w) throws ArcException{
		if (w<=0) {
			throw new ArcException();	// Throw an exception for non-positive weight
		} else {
		this.weight=w;	// Update the weight
		}
	}
	
	public Transition getTransition() throws ArcException{
		return this.transition;		// Get the connected transition
	}
	
	public Place getPlace() throws ArcException{
		return this.place;		// Get the connected place
	}
	
	// Check if two arcs connect the same place and transition
	public boolean hasSameProperties(Arc a) throws ArcException {
		return (this.place == a.getPlace() && this.transition == a.getTransition());
	}
	
	// Abstract method to update the place with a specified weight
	abstract public void updatePlace(Place place, int weight) throws ArcException,PlaceException;
	
	// Abstract method to fire the arc
	abstract public void fireArc() throws ArcException,PlaceException;
	
}