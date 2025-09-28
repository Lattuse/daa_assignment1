package org.lattuse.algo.metrics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter implements AutoCloseable {
    private final BufferedWriter bw;
    private final boolean append;

    public CsvWriter(String filepath, boolean append) throws IOException {
        this.append = append;
        File f = new File(filepath);
        boolean exists = f.exists();
        bw = new BufferedWriter(new FileWriter(f, append));
        if (!exists || !append) {

        }
    }

    public void writeLine(String line) throws IOException {
        bw.write(line);
        bw.newLine();
        bw.flush();
    }

    @Override
    public void close() throws IOException {
        bw.close();
    }
}
