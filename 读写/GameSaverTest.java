import java.io.*;

public class GameSaverTest {
    public static void main(String[] args) {
        GameCharater one = new GameCharater(10, "Elf", new String[]{"bow","sword","dust"});
        GameCharater two = new GameCharater(20, "Human", new String[]{"gun","RPG"});
        GameCharater three = new GameCharater(30, "Witch", new String[]{"fire ball","blizzard"});

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Game.ser"))) {
            oos.writeObject(one);
            oos.writeObject(two);
            oos.writeObject(three);
        } catch (Exception e) {
            e.printStackTrace();
        }

        one = null;
        two = null;
        three = null;
        GameCharater oneRestore = null;
        GameCharater twoRestore = null;
        GameCharater threeRestore = null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Game.ser"))) {
            oneRestore = (GameCharater) ois.readObject();
            twoRestore = (GameCharater) ois.readObject();
            threeRestore = (GameCharater) ois.readObject();

            // System.out.println(oneRestore.getPower());
            // System.out.println(twoRestore.getType());
            // System.out.println(threeRestore.getWeapons());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (FileWriter fos = new FileWriter("input.txt")) {
            fos.write(oneRestore.getPower() + "," + oneRestore.getType()  + oneRestore.getWeapons() + "\n");
            fos.write(twoRestore.getPower() + "," + twoRestore.getType()  + twoRestore.getWeapons() + "\n");
            fos.write(threeRestore.getPower() + "," + threeRestore.getType()  + threeRestore.getWeapons() + "\n");
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}

class GameCharater implements Serializable{
    int power;
    String type;
    String[] weapons;

    public GameCharater(int p, String t, String[] w){
        power = p;
        type = t;
        weapons = w;
    }

    public int getPower(){
        return power;
    }

    public String getType(){
        return type;
    }

    public String getWeapons(){
        StringBuilder weaponsList = new StringBuilder();
        for (String w : weapons) {
            weaponsList.append(",").append(w);
        }
        return weaponsList.toString();
    }
}