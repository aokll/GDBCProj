package HW3.SaveLoad;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;

public class Load implements Serializable,ILoad {//обновляет объект в БД

    @Override
    public void load(List<String> list) {

        ObjectInputStream objectInputStream;
        try (FileInputStream fileInputStream = new FileInputStream("src/main/java/HW3/SaveLoad/BD.txt")
        ){
            objectInputStream = new ObjectInputStream(fileInputStream);

            list = (List<String>) objectInputStream.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
