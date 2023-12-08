package org.pneditor.petrinet.models.binome6;

public class PlaceException extends Exception {
    private String errorMessage; // Additional error message for detailed exception information

    public PlaceException() {
        super("Place Exception: An error occurred in place operations."); // Default error message
        this.errorMessage = "An error occurred in place operations."; // Initialize error message
    }

    public PlaceException(String message) {
        super(message); // Constructor to set a custom error message
        this.errorMessage = message; // Set the error message
    }

    public String getErrorMessage() {
        return errorMessage; // Getter method to retrieve the error message
    }
}
