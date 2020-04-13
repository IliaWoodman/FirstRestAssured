package petstore.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RunWith(Parameterized.class)
public class paraClass {
        public final int petID;
        public final String petName;

    public paraClass(int petID, String petName) {
        this.petID = petID;
        this.petName = petName;
    }

    @Parameterized.Parameters
        public static HashMap<Integer, String> data(){
            HashMap<Integer, String> map = new HashMap<>();
            map.put(1234, "Sharik");
            map.put(1235, "Buddy3");
            map.put(1236, "Barbos");
            return map;
        }




    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "qwe");
        map.put(2, "rty");
        map.put(3,"asd");

        for (Map.Entry<Integer, String> pair : map.entrySet()){
            Integer key = pair.getKey();
            String value = pair.getValue();

            System.out.println(key + " " + value);
        }
    }
}
