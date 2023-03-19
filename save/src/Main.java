import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        GameProgress progress1 = new GameProgress(100, 2, 1, 0.5);
        GameProgress progress2 = new GameProgress(90, 2, 2, 1.5);
        GameProgress progress3 = new GameProgress(50, 3, 3, 5.5);
        String path = "C:\\Users\\User\\Documents\\Netology\\java core\\HW\\Games\\savegames";
        File savegames = new File(path);

        saveGame("C:\\Users\\User\\Documents\\Netology\\java core\\HW\\Games\\savegames\\savegame1.dat", progress1);
        saveGame("C:\\Users\\User\\Documents\\Netology\\java core\\HW\\Games\\savegames\\savegame2.dat", progress2);
        saveGame("C:\\Users\\User\\Documents\\Netology\\java core\\HW\\Games\\savegames\\savegame3.dat", progress3);
        List<String> listZip = new ArrayList<>();
        for (File item : savegames.listFiles()) {
            listZip.add(item.getName());
        }

        zipFiles("C:\\Users\\User\\Documents\\Netology\\java core\\HW\\Games\\savegames\\zip.zip", listZip, savegames);

    }

    public static void saveGame(String path, GameProgress gameProgress) throws FileNotFoundException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(path);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void zipFiles(String path, List<String> list, File file) {

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(path));
             FileInputStream fileInputStream = new FileInputStream(path)) {
            for (int i = 0; i < list.size(); i++) {
                ZipEntry entry = new ZipEntry(list.get(i));
                zipOutputStream.putNextEntry(entry);
            }
            byte[] buffer = new byte[fileInputStream.available()];
            fileInputStream.read(buffer);
            zipOutputStream.write(buffer);
            zipOutputStream.close();
    // добалено использование метода delete
            delete(file, list);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // добавлен метод по удалению файлов
    public static void delete(File file, List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            for (File f : file.listFiles()) {
                if (f.getName().equals(list.get(i))) {
                    f.delete();
                }
            }

        }
    }
}
