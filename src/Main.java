import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        CountWords("files/words.txt");
    }

    public static void CountWords(String filepath) {

        Map<String, Integer> wordsList = new TreeMap<>();
        String text = readTextFile(filepath);


        String[] words = text.split(" ");
        for (String word : words) {
            if (!wordsList.containsKey(word)) wordsList.put(word, 1);
            else wordsList.put(word, wordsList.get(word) + 1);
        }
        System.out.println("|Слово| Количество повторений|");
        String key = null;
        int value = 0;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : wordsList.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            if (value > maxCount) maxCount = value;
            System.out.printf("\n|%s|%d|", key, value);
        }

        for (Map.Entry<String, Integer> entry : wordsList.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            if (value == maxCount)
                System.out.printf("\nСлово '%s' встречается в файле %d раз(а)", key, value);
        }
    }

    public static String readTextFile(String filepath) {
        File file = new File(filepath);
        String text = null;
        try (FileReader reader = new FileReader(file)) {

            char[] buf = new char[256];
            int c;
            while ((c = reader.read(buf)) > 0) {
                if (c < 256) {
                    buf = Arrays.copyOf(buf, c);
                }
            }
            text = String.valueOf(buf);

        } catch (FileNotFoundException error) {
            System.err.println("Файл не найден");
        } catch (IOException error) {
            throw new RuntimeException(error);
        }
        return text;
    }
}