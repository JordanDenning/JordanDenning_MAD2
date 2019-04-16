package com.example.lab7;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataActivity {

    public List<ResortContent.resortItem> loadXML() throws XmlPullParserException, IOException{
        System.out.printf("hello");
        String  new_name = new String();
        List<ResortContent.resortItem> resorts = new ArrayList<ResortContent.resortItem>();

        StringBuffer stringBuffer = new StringBuffer();

        XmlPullParser xpp = MyApplication.getAppContext().getResources().getXml(R.xml.resorts);

        xpp.next();

        int eventType = xpp.getEventType();
        while(eventType != XmlPullParser.END_DOCUMENT){
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    break;
                case XmlPullParser.START_TAG:
                    if(xpp.getName().equals("mountain")){
                        stringBuffer.append("\nSTART_TAG: " + xpp.getName());
                    }
                    else if (xpp.getName().equals("name")){
                        stringBuffer.append("\nSTART_TAG: " + xpp.getName());
                        eventType = xpp.next();
                        new_name = xpp.getText();
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if(xpp.getName().equals("mountain")){
                        ResortContent.resortItem newResort = new ResortContent.resortItem(new_name);
                        resorts.add(newResort);
                    }
                    break;
                case XmlPullParser.TEXT:
                    break;

            }
            eventType= xpp.next();
        }
        return resorts;
    }
}
