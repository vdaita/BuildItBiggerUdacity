package com.daita.joes;

import java.util.Random;

// Jokes from Reader's Digest. Much original.

public class JokeFactory {
    public static String generateJoke(){
        String[] jokes = {"I invented a new word! \n Plagiarism!", "Did you hear about the mathematician who's afraid of negative numbers? \n " +
                " He'll stop at nothing to avoid them", "Why do we tell actors to 'break a leg?' \n Because every play has a cast",
                "Did you hear about the actor who fell through the floorboards? \n He was just going through a stage. "
                , "Why don’t scientists trust atoms? \n Because they make up everything. "
        };

        Random random = new Random();
        int armadillo = random.nextInt(4);

        return jokes[armadillo];
    }
}
