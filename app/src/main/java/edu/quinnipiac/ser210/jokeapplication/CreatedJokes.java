package edu.quinnipiac.ser210.jokeapplication;

/*
@authors: Victoria Gorski, Jenna Saleh, and Julia Wilkinson
@date: 5 - 2 - 20
@description: The CreatedJokes class is used to store the user's created joke and place it into the database.
 */

// Start of class
public class CreatedJokes {

    // Instance variables
    private long id;
    private String joke;


    // Methods
    // Get the ID of the joke
    public long getId() {
        return id;
    }

    // Set the ID of the joke
    public void setId(long id) {
        this.id = id;
    }

    // Set the joke into a string
    public void setJoke(String joke) {
        this.joke = joke;
    }

    // Get the joke
    public String getJoke() {
        return joke;
    }

    // Set the created joke
    public CreatedJokes(String joke) {
        this.joke = joke;
    }
}
