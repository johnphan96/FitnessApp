package persistence;

import java.io.*;
import java.lang.reflect.Field;

// A writer that writes workout program data to a file
public class Writer {
    private PrintWriter printWriter;

    // EFFECTS: constructs a writer
    public Writer(File file) throws FileNotFoundException, UnsupportedEncodingException {
        printWriter = new PrintWriter(file, "UTF-8");
    }

    // MODIFIES: this
    // EFFECTS: write saveable to file
    public void write(Saveable saveable) {
        saveable.save(printWriter);
    }

    // MODIFIES: this
    // EFFECTS: close print writer
    // NOTE: must close print writer after writing data is finished
    public void close() {
        printWriter.close();
    }
}
