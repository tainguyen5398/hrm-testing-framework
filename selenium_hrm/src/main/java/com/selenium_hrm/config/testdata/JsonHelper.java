package com.selenium_hrm.config.testdata;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.selenium_hrm.config.logs.Log;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class JsonHelper {

    // Update value for JSON without sublevel
    public static void updateValueJsonFile_nosub(String filePath, String keyName ,String newValue) {
        Reader reader;
        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            Log.info("Original JSON: " + jsonObject);

            //Update value if exist key
            jsonObject.addProperty(keyName, newValue);

            Log.info("Modified JSON: " + jsonObject);

            //Store new Json data to new file
            File jsonFile = new File(filePath);
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Update value for JSON with 1 sublevel
    public static void updateValueJsonFile_sub1(String filePath, String keyName, String keySub1, String newValue) {
        Reader reader;
        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            Log.info("Original JSON: " + jsonObject);

            //Update value if exist key
            jsonObject.getAsJsonObject(keyName).addProperty(keySub1, newValue);

            Log.info("Modified JSON: " + jsonObject);

            //Store new Json data to new file
            File jsonFile = new File(filePath);
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Update value for JSON with 2 sublevels
    public static void updateValueJsonFile_sub2(String filePath, String keyName, String keySub1,String keySub2, String newValue) {
        Reader reader;
        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            Log.info("Original JSON: " + jsonObject);

            //Update value if exist key
            jsonObject.getAsJsonObject(keyName).getAsJsonObject(keySub1).addProperty(keySub2, newValue);

            Log.info("Modified JSON: " + jsonObject);

            //Store new Json data to new file
            File jsonFile = new File(filePath);
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateValueArray(String filePath,String KeyArray, String valueArray1, String valueArray2) {
        Reader reader;
        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            Log.info("Original JSON: " + jsonObject);

            //Create array value
            JsonArray objectArray = new JsonArray();
            objectArray.add(valueArray1);
            objectArray.add(valueArray2);
            //Add array value to key "department"
            jsonObject.add(KeyArray, objectArray);

            Log.info("Modified JSON: " + jsonObject);
            //Store new Json data to new file
            File jsonFile = new File(filePath);
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateValueArrayObject(String filePath,String KeyObject, String SubKeyO1, String Value1, String SubKeyO2, String Value2) {
        Reader reader;
        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            Log.info("Original JSON: " + jsonObject);

            //Add key:{object}
            Map< String, Object > objectMap = new HashMap< >();
            objectMap.put(SubKeyO1, Value1);
            objectMap.put(SubKeyO2, Value2);
            JsonElement jsonElement = gson.toJsonTree(objectMap);
            jsonObject.add(KeyObject, jsonElement);

            Log.info("Modified JSON: " + jsonObject);
            //Store new Json data to new file
            File jsonFile = new File(filePath);
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
