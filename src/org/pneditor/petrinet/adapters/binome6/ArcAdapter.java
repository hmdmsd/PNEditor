package org.pneditor.petrinet.adapters.binome6;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.UnimplementedCaseException;
import org.pneditor.petrinet.models.binome6.*;

public class ArcAdapter extends org.pneditor.petrinet.AbstractArc {

    public Arc arc;
    private AbstractNode source ;
    private AbstractNode destination;

    public ArcAdapter(Arc arc,AbstractNode source,AbstractNode destination) {
        this.arc = arc;
        this.source = source;
        this.destination = destination ;
    }

    @Override
    public AbstractNode getSource() {
    	return this.source;
    }

    @Override
    public AbstractNode getDestination() {
        return this.destination;
    }

    @Override
    public boolean isReset(){
    	if (isSourceAPlace()) {
    		ArcPlaceTransition ArcPT = (ArcPlaceTransition) arc ;
    		return ArcPT.isZero();
    	}
		return false;
    }

    @Override
    public boolean isRegular(){
    	if (isSourceAPlace()) {
    		ArcPlaceTransition ArcPT = (ArcPlaceTransition) arc ;
    		return ArcPT.isZero()==false && ArcPT.isVideur()==false;
    	}
    	return false;    
    	}

    @Override
    public boolean isInhibitory() {
    	if (isSourceAPlace()) {
    		ArcPlaceTransition ArcPT = (ArcPlaceTransition) arc ;
    		return ArcPT.isVideur();
    	}
    	return false ;    
}

    @Override
    public int getMultiplicity() throws ResetArcMultiplicityException {
    	return arc.getWeight();
    }

    @Override
    public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
        arc.setWeight(multiplicity);
    }
}
