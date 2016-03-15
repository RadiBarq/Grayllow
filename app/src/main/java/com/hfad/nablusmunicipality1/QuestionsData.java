package com.hfad.nablusmunicipality1;

/**
 * Created by sbitanyhome on 12/23/2015.
 */
public class QuestionsData {

    private String qQuestion;
    private String qPhoneNumber;
    private int qRate;

    // The array of informations that we have
    public static final QuestionsData[] questions =
            {
                    new QuestionsData("Nablus", "an this is what we have",R.drawable.youtube1 ),
                    new QuestionsData("Rafidya ", "hrry",R.drawable.youtube),
                    new QuestionsData("Here is the maddness", "heey my little friend",R.drawable.water),
                    new QuestionsData("Nablus", "an this is what we have",R.drawable.youtube1 ),
                    new QuestionsData("Rafidya ", "hrry",R.drawable.youtube),
                    new QuestionsData("Here is the maddness", "heey my little friend",R.drawable.water),
                    new QuestionsData("Nablus", "an this is what we have",R.drawable.youtube1 ),
                    new QuestionsData("Rafidya ", "hrry",R.drawable.youtube),
                    new QuestionsData("Here is the maddness", "heey my little friend",R.drawable.water),
                    new QuestionsData("Nablus", "an this is what we have",R.drawable.youtube1 ),
                    new QuestionsData("Rafidya ", "hrry",R.drawable.youtube),
                    new QuestionsData("Here is the maddness", "heey my little friend",R.drawable.water)
            };


    private QuestionsData(String qQuestion, String qPhoneNumber, int qRate)
    {
        this.qQuestion = qQuestion;
        this.qPhoneNumber = qPhoneNumber;
        this.qRate = qRate;

    }


    public String getqQuestion()
    {
        return qQuestion;
    }

    public String getqPhoneNumber()
    {
        return qPhoneNumber;
    }

    public int getqRate()
    {
        return qRate;
    }

    public String toString()
    {
        return this.qQuestion;
    }



}
