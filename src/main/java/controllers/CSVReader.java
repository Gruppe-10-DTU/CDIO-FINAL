package controllers;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    private final String DELIMITER;
    private Scanner reader;
    private String[] headers;
    private File file;
    private ArrayList<ArrayList<String>> fileAsArrList;

    /**
     * Reads the content of the CSV file to a 2d ArrayList
     * @param CSVFilePath the path to the CSV file
     * @param HasHeaders a boolean representing whether the columns have titles
     * @throws FileNotFoundException
     */
    public CSVReader(String CSVFilePath, String ValueSeperator, boolean HasHeaders) throws FileNotFoundException {
        this.DELIMITER = ValueSeperator;
        this.file = new File(CSVFilePath);

        this.reader = new Scanner(new BufferedReader(new FileReader(file)));

        if(HasHeaders) {
            this.headers = reader.nextLine().split(DELIMITER);
        }
        fileAsArrList = new ArrayList<>();
        int count = 0;
        while(reader.hasNextLine()){
            fileAsArrList.add(count, readLine());
            count++;
        }
        close();
    }

    private ArrayList<String> readLine(){
        ArrayList<String> result = new ArrayList<>(List.of(reader.nextLine().split(DELIMITER)));
        return result;
    }
    private void close(){
        reader.close();
    }
    public ArrayList<ArrayList<String>> getDataAsArrList(){
        return fileAsArrList;
    }

    /**
     *
     * @return a string representation of the read CSV file
     */
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < fileAsArrList.size(); i++){
            for(int j = 0; j < fileAsArrList.get(i).size(); j++){
                if(!fileAsArrList.get(i).get(j).equals("")){
                    str.append(fileAsArrList.get(i).get(j)).append(", ");
                }
            }
            str.append("\n");
        }
        return str.toString();
    }

    /**
     * Outputs the content of the file as a 2d String Array
     * @return String[][]
     */
    public String[][] getData(){
        String[][] result = new String[fileAsArrList.size()][];
        for (int i = 0; i < fileAsArrList.size(); i++) {
            result[i] = new String[fileAsArrList.get(i).size()];
            for (int j = 0; j < fileAsArrList.get(i).size(); j++) {
                result[i][j] = fileAsArrList.get(i).get(j);
            }
        }
        return result;
    }

    public String[] getHeaders(){
        return headers;
    }

    /**
     * Searches for the desired title not case-sensitive
     * @param HeaderTitle the deisred column
     * @return index of title or -1 if title not found
     */
    public int getHeaderIndex(String HeaderTitle){
        int value = -1;
        String title = HeaderTitle.toLowerCase();
        for (int i = 0; i < headers.length; i++) {
            if(headers[i].toLowerCase().equals(title)){
                return i;
            }
        }
        return value;
    }

}

