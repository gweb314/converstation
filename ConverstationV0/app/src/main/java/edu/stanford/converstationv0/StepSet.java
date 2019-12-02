package edu.stanford.converstationv0;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.Log;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class StepSet implements Serializable {

    //private Context context;
    public String converStationName;
    //public Drawable converStationPic;
    public String expectedTravelTime;
    public String travelDistance;

    public StepSet(String cSN, String  eTT, String tD) {

        converStationName = cSN;
        expectedTravelTime = eTT;
        travelDistance = tD;
    }

}
