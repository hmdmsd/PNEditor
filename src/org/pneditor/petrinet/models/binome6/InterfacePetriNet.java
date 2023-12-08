package org.pneditor.petrinet.models.binome6;

public interface InterfacePetriNet {
	
	void addExistingPlace(Place p) throws PlaceException;
	
	void addExistingTransition(Transition t) throws TransitionException;

    void addPlace(int weight) throws PlaceException;

    void addTransition();
    
    void addArcPlaceTransition(Place place, Transition transition,int weight, boolean isZero, boolean isVideur) throws ArcException, PlaceException, TransitionException;

    void addArcTransitionPlace(Place place, Transition transition,int weight) throws ArcException, PlaceException, TransitionException ;
    
    void removeArcs() throws PlaceException, ArcException;

    void step(Transition t) throws PlaceException, ArcException;

    void updatePlace(Place place, int weight) throws PlaceException;

    void removeArc(Arc a) throws PlaceException, TransitionException;

	void removePlace(Place place);

	void removeTransition(Transition transition);


}
