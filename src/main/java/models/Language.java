package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Language {

    private HashMap<String, String> languageValues;

    public Language() {
        updateLanguage("English");
    }
    public Language(String language){
        updateLanguage(language);
    }

    //Returns the value to the requestes key, kan add extra string if the value includes {0}
    public String getLanguageValue(String key, String...txt) {
        String value = languageValues.get(key);
        value = value.replace("{0}", txt[0]);
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
                target = "/english.txt";
                break;
            case "da":
                target = "/danish.txt";
                break;
            default:
                target = "/english.txt";
                break;
        }
        Class c = this.getClass();
        InputStream file = c.getResourceAsStream(target);

        languageValues = new HashMap<String, String>();
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
        }
    }
}
