package org.pneditor.petrinet.adapters.binome6;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.UnimplementedCaseException;
import org.pneditor.petrinet.models.binome6.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class PetriNetAdapter extends org.pneditor.petrinet.PetriNetInterface {

    private PetriNet petriNet;

    public PetriNetAdapter() {
        this.petriNet = new PetriNet();
    }

    @Override
    public AbstractPlace addPlace() {
        try {
            return new PlaceAdapter("place");
        } catch (PlaceException e) {}
        return null;
    }

    @Override
    public AbstractTransition addTransition() {
    	try {
			return new TransitionAdapter("transition");
		} catch (TransitionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
        return null;
    }

    @Override
    public AbstractArc addRegularArc(AbstractNode source, AbstractNode destination) throws UnimplementedCaseException {
    	
    	if ( source.isPlace()) {
    		PlaceAdapter place = (PlaceAdapter) source;
    		TransitionAdapter transition = (TransitionAdapter) destination;
    		try {
				petriNet.addArcPlaceTransition(place.place, transition.transition, 0, false, false);
				return new ArcAdapter(new ArcPlaceTransition(0, false, false),source,destination);
			} catch (ArcException | PlaceException | TransitionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	else {
    		TransitionAdapter transition = (TransitionAdapter) source;
    		PlaceAdapter place = (PlaceAdapter) destination;
    		try {
				petriNet.addArcTransitionPlace(place.place, transition.transition,0);
				return new ArcAdapter(new ArcTransitionPlace(0),source,destination);
			} catch (ArcException | PlaceException | TransitionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return null;
    }

    @Override
    public AbstractArc addInhibitoryArc(AbstractPlace place, AbstractTransition transition) throws UnimplementedCaseException {
    		PlaceAdapter adaptedPlace = (PlaceAdapter) place;
    		TransitionAdapter adaptedTransition = (TransitionAdapter) transition;
    		try {
				petriNet.addArcPlaceTransition(adaptedPlace.place, adaptedTransition.transition, 0, true, false);
				return new ArcAdapter(new ArcPlaceTransition(0, true, false),place,transition);
			} catch (ArcException | PlaceException | TransitionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	return null;
    }

    @Override
    public AbstractArc addResetArc(AbstractPlace place, AbstractTransition transition) throws UnimplementedCaseException {
		PlaceAdapter adaptedPlace = (PlaceAdapter) place;
		TransitionAdapter adaptedTransition = (TransitionAdapter) transition;
		try {
			petriNet.addArcPlaceTransition(adaptedPlace.place, adaptedTransition.transition, 0, false, true);
			return new ArcAdapter(new ArcPlaceTransition(0, false, true),place,transition);
		} catch (ArcException | PlaceException | TransitionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return null;
    }

    @Override
    public void removePlace(AbstractPlace place) {
    	PlaceAdapter adaptedPlace = (PlaceAdapter) place;
        petriNet.removePlace(adaptedPlace.place);
    }

    @Override
    public void removeTransition(AbstractTransition transition) {
		TransitionAdapter adaptedTransition = (TransitionAdapter) transition;
        petriNet.removeTransition(adaptedTransition.transition);
    }

    @Override
    public void removeArc(AbstractArc arc) {
    	ArcAdapter adaptedArc = (ArcAdapter) arc;
    	try {
			petriNet.removeArc(adaptedArc.arc);
		} catch (PlaceException | TransitionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public boolean isEnabled(AbstractTransition transition) throws ResetArcMultiplicityException {
		TransitionAdapter adaptedTransition = (TransitionAdapter) transition;
    	try {
			return adaptedTransition.transition.isFirable();
		} catch (ArcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
    }

    @Override
    public void fire(AbstractTransition transition) throws ResetArcMultiplicityException {
		TransitionAdapter adaptedTransition = (TransitionAdapter) transition;
    	try {
			petriNet.step(adaptedTransition.transition);
		} catch (PlaceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ArcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // Implement other abstract methods from PetriNetInterface using your PetriNet class
    // ...

    // Implement other methods from PetriNetInterface based on your requirements
    // ...
}
