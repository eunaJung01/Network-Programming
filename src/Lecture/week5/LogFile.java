package Lecture.week5;

import java.io.*;
import java.util.Date;

public class LogFile {

    private Writer out;

    public LogFile(File f) throws IOException {
        FileWriter fw = new FileWriter(f);
        this.out = new BufferedWriter(fw);
    }

    /*
    // TODO: log info can "mix up" due to multiple threads interrupting each other → Synchronize
    public void writeEntry(String message) throws IOException {
        Date d = new Date();
        out.write(d.toString());
        out.write('\t');
        out.write(message);
        out.write('\t');
    }
    //*/

    /*
    // TODO: Solution 1 - Synchronize Writer object out
    public void writeEntry(String message) throws IOException {
        synchronized (out) {
            Date d = new Date();
            out.write(d.toString());
            out.write('\t');
            out.write(message);
            out.write('\t');
        }
    }
    //*/

    /*
    // TODO: Solution 2 - Synchronize LogFile object itself
    public void writeEntry(String message) throws IOException {
        synchronized (this) {
            Date d = new Date();
            out.write(d.toString());
            out.write('\t');
            out.write(message);
            out.write('\t');
        }
    }
    //*/

    //*
    // TODO: Solution 3 - Synchronize methods
    public synchronized void writeEntry(String message) throws IOException {
        Date d = new Date();
        out.write(d.toString());
        out.write('\t');
        out.write(message);
        out.write('\t');
    }
    //*/

    public void close() throws IOException {
        out.flush();
        out.close();
    }

}
