package edu.quinnipiac.ser210.jokeapplication;

/*
@authors: Victoria Gorski, Jenna Saleh, and Julia Wilkinson
@date: 5 - 2 - 20
@description: The ResultsHandler class handles the results retrieve from the API.
 */

// Imports
import org.json.JSONException;
import org.json.JSONObject;

// Start of class
public class ResultsHandler {
    public ResultsHandler() {
    }

    // Methods
    // Get the joke information from the API
    public String getJokeInfo(String jokeInfo) throws JSONException {
        String category, joke, punchline;
        String printString;
        JSONObject jokeData = new JSONObject(jokeInfo);
        // If the data contains a joke and a punchline, display both
        if (jokeData.getString("type").equals("twopart")) {
            category = jokeData.getString("category");
            joke = jokeData.getString("setup");
            punchline = jokeData.getString("delivery");
            printString = "Category:  " + category + "\n " + "\nJoke:  " + joke + "\n " + "\nPunchline:  " + punchline;
        } else {
            // If the data only contains a joke, display it
            joke = jokeData.getString("joke");
            category = jokeData.getString("category");
            printString = "Category:  " + category + "\n " + "\nJoke:  " + joke + "\n ";
        }
        return printString;
    }
}
