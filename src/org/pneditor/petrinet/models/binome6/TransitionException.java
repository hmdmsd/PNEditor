package org.pneditor.petrinet.models.binome6;

public class TransitionException extends Exception {
    private String errorMessage; // Additional error message for detailed exception information

    public TransitionException() {
        super("Transition Exception: An error occurred in transition operations."); // Default error message
        this.errorMessage = "An error occurred in transition operations."; // Initialize error message
    }

    public TransitionException(String message) {
        super(message); // Constructor to set a custom error message
        this.errorMessage = message; // Set the error message
    }

    public String getErrorMessage() {
        return errorMessage; // Getter method to retrieve the error message
    }
}
