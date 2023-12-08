package org.pneditor.petrinet.adapters.binome6;

import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.models.binome6.Place;
import org.pneditor.petrinet.models.binome6.PlaceException;

public class PlaceAdapter extends AbstractPlace {
    public Place place;

    public PlaceAdapter(String label) throws PlaceException {
        super(label);
        this.place = new Place(0);
    }

    @Override
    public void addToken() {
        try {
            place.addTokens(1);
        } catch (PlaceException e) {
            e.printStackTrace(); // Handle or log the exception accordingly
        }
    }

    @Override
    public void removeToken() {
        try {
            place.removeTokens(1);
        } catch (PlaceException e) {
            e.printStackTrace(); // Handle or log the exception accordingly
        }
    }

    @Override
    public int getTokens() {
        return place.getTokens();
    }

    @Override
    public void setTokens(int tokens) {
    	try {
			place.removeTokens(place.getTokens());
			place.addTokens(tokens);
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
        return this.place;
     }
}
