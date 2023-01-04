package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class StartValues {
    private Properties properties;
    private static StartValues instance = new StartValues() ;

    private StartValues(){
        try (InputStream input = GameController.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
            }

            //load a properties file from class path, inside static method
            properties.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static StartValues getInstance() {
        return instance;
    }

    public int getValue(String value){
        return Integer.valueOf(this.properties.getProperty(value));
    }

}
