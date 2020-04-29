package persistence;

import java.io.PrintWriter;

// Represents data that can be saved to file
public interface Saveable {

    // MODIFIES: printWriter
    // EFFECTS: writes data to be saved to printWriter
    void save(PrintWriter printWriter);
}
