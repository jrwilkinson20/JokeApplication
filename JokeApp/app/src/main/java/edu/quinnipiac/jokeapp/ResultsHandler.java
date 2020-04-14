package edu.quinnipiac.jokeapp;

import org.json.JSONException;
import org.json.JSONObject;

public class ResultsHandler {
    public ResultsHandler(){

    }
    public String getJokeInfo(String jokeInfo) throws JSONException {
        String category, joke, punchline;
        String printString;
        JSONObject jokeData = new JSONObject(jokeInfo);
        if (jokeData.getString("type").equals("twopart")) {
            category = jokeData.getString("category");
            joke = jokeData.getString("setup");
            punchline = jokeData.getString("delivery");
        } else {
            joke = jokeData.getString("joke");
            category = jokeData.getString("category");
            punchline = " ";
        }
        printString = "Category: " + category + "\nJoke: " + joke +  "\nPunchline: " + punchline;
        return printString;

    }


}
