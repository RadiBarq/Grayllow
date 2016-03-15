package com.hfad.nablusmunicipality1;

import android.telephony.PhoneNumberFormattingTextWatcher;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by sbitanyhome on 12/23/2015.
 */
public class ReportsData {

    private String rArea;
    private String rDescription;
    private int rPhotoId;
    private String rPhoneNumber;
    String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());





    public ReportsData(String rArea, String rDescription,String rPhoneNumber) {

        this.rArea = rArea;
        this.rDescription = rDescription;
        this.rPhoneNumber = rPhoneNumber;

    }


    public String getrArea() {
        return rArea;
    }

    public String getrDescription() {
        return rDescription;
    }

    public int getrPhotoId() {
        return rPhotoId;
    }

    public String getrPhoneNumber()
    {
        return rPhoneNumber;
    }

    public String getrTime() {
        return currentDateTimeString;
    }

    public String toString()
    {
        return this.rArea;
    }


}
