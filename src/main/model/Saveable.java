package model;

import java.io.FileNotFoundException;

public interface Saveable {
    void save(String fileName) throws FileNotFoundException;
}
