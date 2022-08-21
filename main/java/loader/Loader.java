package loader;

import parser.csv.CSVParser;
import parser.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class Loader {
    private LinkedHashMap<String, List<List<Integer>>> recordBytePosition;
    private Path csvFile;
    private final String fileName = "airports.csv";

    public Loader() {
        URL resource = null;
        try {
            resource = getClass().getClassLoader().getResource(fileName);
            if (resource == null){
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        try {
            csvFile = Paths.get(resource.toURI());
        } catch (URISyntaxException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        recordBytePosition = new LinkedHashMap<>();
    }

    public Path getCsvFilePath(){
        return csvFile;
    }

    public void loadRecordBytePosition(Integer columnNumber) {
        CSVParser csvParser = new CSVParser(csvFile);
        Iterator<CSVRecord> csvRecords = csvParser.iterator();
        String key;
        int recordStartBytePosition = 0;
        while(csvRecords.hasNext()){
            List<Integer> bytesList = new ArrayList<>();
            CSVRecord currentRecord = csvRecords.next();
            key = currentRecord.getColumnCurrentField(columnNumber);
            bytesList.add(recordStartBytePosition);
            bytesList.add(currentRecord.getByteLength());
            if(!recordBytePosition.containsKey(key)){
                List<List<Integer>> list = new ArrayList<>();
                list.add(bytesList);
                recordBytePosition.put(key, list);
            }
            else{
                List<List<Integer>> list= recordBytePosition.get(key);
                list.add(bytesList);
                recordBytePosition.put(key, list);
            }
            recordStartBytePosition += currentRecord.getByteLength() + 1;
        }
    }

    public LinkedHashMap<String, List<List<Integer>>> getRecordBytePosition(){
        return recordBytePosition;
    }
}
