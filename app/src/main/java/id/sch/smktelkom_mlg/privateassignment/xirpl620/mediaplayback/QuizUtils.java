/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package id.sch.smktelkom_mlg.privateassignment.xirpl620.mediaplayback;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class QuizUtils {

    private static final String CURRENT_SCORE_KEY = "Skor Saat ini";
    private static final String HIGH_SCORE_KEY = "Skor Tertinggi";
    private static final String GAME_FINISHED = "Selesai";
    private static final int NUM_ANSWERS = 4;


    static ArrayList<Integer> generateQuestion(ArrayList<Integer> remainingSampleIDs) {

        // Shuffle the remaining sample ID's.
        Collections.shuffle(remainingSampleIDs);

        ArrayList<Integer> answers = new ArrayList<>();

        // Pick the first four random Sample ID's.
        for (int i = 0; i < NUM_ANSWERS; i++) {
            if (i < remainingSampleIDs.size()) {
                answers.add(remainingSampleIDs.get(i));
            }
        }

        return answers;
    }

    /**
     * Helper method for getting the user's high score.
     *
     * @param context The application context.
     * @return The user's high score.
     */
    static int getHighScore(Context context) {
        SharedPreferences mPreferences = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        return mPreferences.getInt(HIGH_SCORE_KEY, 0);
    }

    /**
     * Helper method for setting the user's high score.
     *
     * @param context   The application context.
     * @param highScore The user's high score.
     */
    static void setHighScore(Context context, int highScore) {
        SharedPreferences mPreferences = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(HIGH_SCORE_KEY, highScore);
        editor.apply();
    }

    /**
     * Helper method for getting the user's current score.
     *
     * @param context The application context.
     * @return The user's current score.
     */
    static int getCurrentScore(Context context) {
        SharedPreferences mPreferences = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        return mPreferences.getInt(CURRENT_SCORE_KEY, 0);
    }

    /**
     * Helper method for setting the user's current score.
     *
     * @param context      The application context.
     * @param currentScore The user's current score.
     */
    static void setCurrentScore(Context context, int currentScore) {
        SharedPreferences mPreferences = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(CURRENT_SCORE_KEY, currentScore);
        editor.apply();
    }

    /**
     * Picks one of the possible answers to be the correct one at random.
     *
     * @param answers The possible answers to the question.
     * @return The correct answer.
     */
    static int getCorrectAnswerID(ArrayList<Integer> answers) {
        Random r = new Random();
        int answerIndex = r.nextInt(answers.size());
        return answers.get(answerIndex);
    }

    /**
     * Checks that the user's selected answer is the correct one.
     *
     * @param correctAnswer The correct answer.
     * @param userAnswer    The user's answer
     * @return true if the user is correct, false otherwise.
     */
    static boolean userCorrect(int correctAnswer, int userAnswer) {
        return userAnswer == correctAnswer;
    }


    /**
     * Helper method for ending the game.
     *
     * @param context The application method.
     */
    static void endGame(Context context) {
        Intent endGame = new Intent(context, MainActivity.class);
        endGame.putExtra(GAME_FINISHED, true);
        context.startActivity(endGame);
    }
}
