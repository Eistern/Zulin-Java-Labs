import java.io.IOException;
import wordCounter.*;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.print("No files given");
            return;
        }

        StringReader reader = null;
        Printer printer = null;
        CSVParser parser = new CSVParser();
        try {
            reader = new StringFileReader(args[0]);
            printer = new CSVPrinter();
            parser.parseFile(reader);
            parser.printStats(printer);
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (reader != null)
                    reader.closeReader();
                if (printer != null)
                    printer.closePrinter();
            }
            catch(IOException e){
                System.err.println(e.getMessage());
            }
        }
    }
}
