package com.example.lab7;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResortContent {

        //array of resort items
        public static final List<resortItem> ITEMS = new ArrayList<resortItem>();

        //
//        public static final Map<String, resortItem> ITEM_MAP = new HashMap<String, resortItem>();

        public void dataSetup(){
            List<ResortContent.resortItem> xmlData = new ArrayList<ResortContent.resortItem>();
            DataActivity xmlDataActivity = new DataActivity();

            if(ITEMS.size() == 0){
                try{
                    xmlData = xmlDataActivity.loadXML();
                    for (int i = 0; i < xmlData.size(); i++)
                    {
                        addItem(xmlData.get(i));
                    }
                }catch (XmlPullParserException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        private static void addItem(resortItem item){
            ITEMS.add(item);
//            ITEM_MAP.put(item.id, item);
        }




    public static class resortItem{
            public final String name;

            public resortItem(String name){
                this.name = name;
            }

        @Override
        public String toString() {
            return name;

        }
    }






}
