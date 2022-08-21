package parser.csv;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CSVParser implements Iterable<CSVRecord>{
    private Path path;

    public CSVParser(Path path) {
        this.path = path;
    }

    public List<String> getRecords() {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Iterator<CSVRecord> iterator() {
        Iterator<CSVRecord> csvRecordIterator = null;
        try {
            csvRecordIterator = new CSVRecordIterator(Files.readAllLines(path));
            return csvRecordIterator;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        try {
            if (csvRecordIterator == null){
                throw new Exception();
            }
        }
        catch (Exception e){
            System.err.println("Невозможно создать итератор для этой коллекции");
            e.printStackTrace();
        }
        return null;
    }

    static class CSVRecordIterator implements Iterator<CSVRecord> {
        private List<String> records;
        private int index;

        public CSVRecordIterator(List<String> records){
            this.records = records;
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < records.size();
        }

        @Override
        public CSVRecord next() {
            CSVRecord csvRecord = new CSVRecord(records.get(index));
            index++;
            return csvRecord;
        }
    }
}
