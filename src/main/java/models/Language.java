package models;

import controllers.StartValues;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Language {

    private HashMap<String, String> languageValues;

    private static Language instance = new Language();

    private Language(){
        updateLanguage(System.getProperty("user.language"));
    }

    public static Language getInstance(){
        return instance;
    }

    //Returns the value to the requestes key, can add extra string if the value includes {0}
    public String getLanguageValue(String key, String...txt) {
        String value = languageValues.get(key);
        for (int i = 0; i < txt.length ; i++) {
            value = value.replace("{"+i+"}",txt[i]);
        }
        return value;
    }

    public String getLanguageValue(String key) {
        return languageValues.get(key);
    }

    //Updates the language
    public void updateLanguage(String language) {
        String target;
        switch (language){
            case "en":
                target = "/LanguagePack/english.txt";
                break;
            case "da":
                target = "/LanguagePack/danish.txt";
                break;
            default:
                target = "/LanguagePack/danish.txt";
                break;
        }
        Class c = this.getClass();
        InputStream file = c.getResourceAsStream(target);

        languageValues = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file));
            String line;
            while((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] keyValuePair = line.split("=");
                    languageValues.put(keyValuePair[0], keyValuePair[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
