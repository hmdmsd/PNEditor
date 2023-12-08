package org.pneditor.petrinet.models.binome6;

import java.util.LinkedList;

public class PetriNet implements InterfacePetriNet {

    private LinkedList<Arc> arcs;
    private LinkedList<Place> places;
    private LinkedList<Transition> transitions;

    public PetriNet() {
        this.arcs = new LinkedList<Arc>();
        this.places = new LinkedList<Place>();
        this.transitions = new LinkedList<Transition>();
    }
    
    // add existing place 
    
    public void addExistingPlace(Place p) throws PlaceException {
        places.add(p);
    }
    
    // create existing place 
    
    public void addExistingTransition(Transition t) throws TransitionException {
        transitions.add(t);
    }

      
    // create new place 
    
    @Override
    public void addPlace(int tokens) throws PlaceException {
        places.add(new Place(tokens));
    }

    // create transition
    
    @Override
    public void addTransition() {
        transitions.add(new Transition());
    }

    public void addArcPlaceTransition(Place place, Transition transition,int weight, boolean isZero, boolean isVideur) throws ArcException, PlaceException, TransitionException {
        ArcPlaceTransition arc = new ArcPlaceTransition(weight, isZero, isVideur);
        arc.place = place;
        arc.transition = transition;
        arcs.add(arc);
        arc.place.addArc(arc);
        arc.transition.addArc(arc);
    }

    public void addArcTransitionPlace(Place place, Transition transition,int weight) throws ArcException, PlaceException, TransitionException {
        ArcTransitionPlace arc = new ArcTransitionPlace(weight);
        arc.place = place;
        arc.transition = transition;
        arcs.add(arc);
        arc.place.addArc(arc);
        arc.transition.addArc(arc);
    }

    public void removePlace(Place place) {
    	
    	places.remove(place);
    }
    
    public void removeTransition(Transition transition) {
    	transitions.remove(transition);
    }
    @Override
    public void removeArc(Arc a) throws PlaceException, TransitionException {
        if (arcs.contains(a)) {
        	a.place.removeArc(a);
        	a.transition.removeArc(a);
            arcs.remove(a); // Remove an arc from the list
        } else {
            throw new PlaceException(); // Throw an exception if the arc is not in the list
        }
    }

    @Override
    public void updatePlace(Place place, int weight) throws PlaceException {
        place.empty();
        place.addTokens(weight);
    }

    @Override
    public void step(Transition t) throws PlaceException, ArcException {
        if (t.isFirable()) {
            t.fire();
            System.out.println("fired!");
        } else {
            System.out.println("Transition is not firable");
        }
    }

    @Override
    public void removeArcs() throws PlaceException, ArcException {
        for (Arc a : arcs) {
            a.getPlace().removeArc(a); // Remove arcs from connected places
        }
        arcs = new LinkedList<Arc>(); // Set the list of arcs to an empty list
    }

}
