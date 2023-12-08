package org.pneditor.petrinet.models.binome6;

public class ArcException extends Exception {
    private String errorMessage; // Additional error message for detailed exception information

    public ArcException() {
        super("Arc Exception: An error occurred in arc operations."); // Default error message
        this.errorMessage = "An error occurred in arc operations."; // Initialize error message
    }

    public ArcException(String message) {
        super(message); // Constructor to set a custom error message
        this.errorMessage = message; // Set the error message
    }

    public String getErrorMessage() {
        return errorMessage; // Getter method to retrieve the error message
    }
}
