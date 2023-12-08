package org.pneditor.petrinet.adapters.binome6;

import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.models.binome6.Place;
import org.pneditor.petrinet.models.binome6.PlaceException;

public class PlaceAdapter extends AbstractPlace {
    public Place adaptee;

    public PlaceAdapter(Place adaptee) {
        super("");
        this.adaptee = adaptee;
        System.out.println(this.getId());
    }

    @Override
    public void addToken() {
        try {
            adaptee.addTokens(1);
        } catch (PlaceException e) {
            e.printStackTrace(); // Handle or log the exception accordingly
        }
    }

    @Override
    public void removeToken() {
        try {
            adaptee.removeTokens(1);
        } catch (PlaceException e) {
            e.printStackTrace(); // Handle or log the exception accordingly
        }
    }

    @Override
    public int getTokens() {
        return adaptee.getTokens();
    }

    @Override
    public void setTokens(int tokens) {
    	try {
			adaptee.removeTokens(adaptee.getTokens());
			adaptee.addTokens(tokens);
		} catch (PlaceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // Implement other methods from AbstractPlace as required

    @Override
    public boolean isPlace() {
        return true;
    }
    
    Place getModelObject() {
        return this.adaptee;
     }
}
