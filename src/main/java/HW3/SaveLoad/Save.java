package HW3.SaveLoad;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Save implements Serializable,ISave {//сохраняет объект в БД
    private List<String> list;

    public Save() {
        list = new ArrayList<>();
    }
    @Override
    public void save(List<String> list) {
        ObjectOutputStream objectOutputStream;
        try (FileOutputStream outputStream = new FileOutputStream("src/main/java/HW3/SaveLoad/BD.txt",true))
        {
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(list);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
