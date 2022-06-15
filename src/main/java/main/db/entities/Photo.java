package main.db.entities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class Photo {
    private int id;
    private InputStream file;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public InputStream getFile() {
        return file;
    }

    public void setFile(InputStream file) {
        this.file = file;
    }

    public String getBase64() {
        try {
            return Base64.getEncoder().encodeToString(file.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "null";
    }

    @Override
    public String toString() {
        return String.format("Photo[id = %d, file = %s]", id, file);
    }
}
