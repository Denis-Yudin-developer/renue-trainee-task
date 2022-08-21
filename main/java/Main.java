import search.Searcher;

public class Main {
    public static void main(String[] args) {
        int columnNumber = Integer.parseInt(args[0]);
        if(columnNumber < 0 || columnNumber > 14){
            throw new IllegalArgumentException("Столбец в CSV файле отсутствует");
        }
        Searcher searcher = new Searcher(columnNumber);
        searcher.run();
    }
}
