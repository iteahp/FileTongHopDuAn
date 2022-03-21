package io;


import java.io.*;

import java.util.ArrayList;
import java.util.List;

public class ReadAndWriteFile <T>{
    public void writeToFile(String path , List<T> list){
        try {

            FileOutputStream fis = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fis);
            oos.writeObject(list);
            oos.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<T> readFromFile(String path){
       List<T> list = new ArrayList<>();
        try {
            File file = new File(path);
            if (!file.isFile()) {
                file.createNewFile();
            }
            FileInputStream fis = new FileInputStream(path);
            if (fis.available() != 0) {
                ObjectInputStream objectInputStream = new ObjectInputStream(fis);
                return (List<T>) objectInputStream.readObject();
            }
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}
