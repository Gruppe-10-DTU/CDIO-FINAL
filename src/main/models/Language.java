package game.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Language {

    private HashMap<String, String> languageValues;

    public Language() {
        updateLanguage("/game/src/game/english.txt");
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
            case "English":
                target = "english.txt";
                break;
            case "Danish":
                target = "danish.txt";
                break;
            default:
                target = "english.txt";
                break;
        }
        InputStream file = this.getClass().getResourceAsStream(target);

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
