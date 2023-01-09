package controllers;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    private final String DELIMITER;
    private BufferedReader reader;
    private String[] headers;
    private String file;
    private ArrayList<ArrayList<String>> fileAsArrList;

    /**
     * Reads the content of the CSV file to a 2d ArrayList
     * @param CSVFilePath the path to the CSV file
     * @param HasHeaders a boolean representing whether the columns have titles
     */
    public CSVReader(String CSVFilePath, String ValueSeperator, boolean HasHeaders)  {
        this.DELIMITER = ValueSeperator;
        this.file = CSVFilePath;
        InputStream stream = this.getClass().getResourceAsStream(file);
        assert stream != null;
        this.reader = new BufferedReader(new InputStreamReader(stream));
        try {
            if (HasHeaders) {
                this.headers = reader.readLine().split(DELIMITER);
            }
            fileAsArrList = new ArrayList<>();
            int count = 0;
            String line;
            while ((line = reader.readLine())!=null) {
                fileAsArrList.add(count, new ArrayList<>(List.of(line.split(DELIMITER))));
                count++;
            }
            reader.close();
        }catch (IOException ignore){
        }
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

