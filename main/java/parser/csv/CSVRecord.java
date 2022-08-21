package parser.csv;

public class CSVRecord{
    private String record;

    public CSVRecord(String record){
        this.record = record;
    }

    public String getColumnCurrentField(Integer columnNumber){
        String key = record.split(",")[columnNumber - 1];
        if(!isNumeric(key)){
            return key.substring(1, key.length() - 1);
        }
        return key;
    }

    public int getByteLength(){
        return record.getBytes().length;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
