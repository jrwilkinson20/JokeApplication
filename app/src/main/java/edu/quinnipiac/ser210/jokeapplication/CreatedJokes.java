package edu.quinnipiac.ser210.jokeapplication;

public class CreatedJokes {
    private long id;
    private String joke;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setJoke(String joke) {
        this.joke = joke;
    }

    public String toString() {
        return joke;
    }
}
