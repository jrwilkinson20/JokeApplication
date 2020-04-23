package edu.quinnipiac.ser210.jokeapplication;

import org.json.JSONException;
import org.json.JSONObject;

public class ResultsHandler {
    public ResultsHandler(){

    }
    public String getJokeInfo(String jokeInfo) throws JSONException {
        String category, joke, punchline;
        JSONObject jokeData = new JSONObject(jokeInfo);
            joke = jokeData.getString("setup");
            punchline = jokeData.getString("delivery");
            category = jokeData.getString("category");
            joke = joke.replaceAll("< /P", "");

        String printString = "Category: " + category + "\nJoke: " + joke + "\nPunchline: " + punchline;
        return printString;

    }


}
