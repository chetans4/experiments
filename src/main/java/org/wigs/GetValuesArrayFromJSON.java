package org.wigs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class GetValuesArrayFromJSON {

    public static void main(String[] args) throws JSONException {
        String jsonStr = """
                {name : "Suneel",
                location: ["Hyderabad","Chennai"],
                office: {
                office1: "AT&T",
                office2: "AT&T1"},
                pin: [1234,5678],
                working: false
                }
                """;

        JSONObject jsonObject = new JSONObject(jsonStr);
        ArrayList valuesList = new ArrayList();
        getValues(jsonObject, valuesList);

        System.out.println("Output values list : "+valuesList);

    }

    private static void getValues(JSONObject jsonObject, ArrayList valuesList) throws JSONException {
        for (Iterator it = jsonObject.keys(); it.hasNext(); ) {
            String key = (String) it.next();
            Object valuesObj = jsonObject.get(key);
            if(valuesObj instanceof String){
                valuesList.add(valuesObj);
            }else if(valuesObj instanceof Boolean){
                valuesList.add(valuesObj.toString());
            }else if(valuesObj instanceof JSONObject){
                getValues((JSONObject) valuesObj, valuesList);
            }else if(valuesObj instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) valuesObj;
                for (int i = 0; i < jsonArray.length(); i++) {
                    valuesList.add(jsonArray.get(i));
                }
            }
        }
    }

}
