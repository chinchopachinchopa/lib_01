import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DictionaryService {
    private Map<String, String> dictionary = new HashMap<>();
    private String filePath;

    public DictionaryService(String filePath) {
        this.filePath = filePath;
        loadDictionary();
    }

    private void loadDictionary() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    dictionary.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке словаря: " + e.getMessage());
        }
    }

    public void addEntry(String key, String value) {
        if (isValidKey(key) && isValidValue(value)) {
            dictionary.put(key, value);
            saveDictionary();
        } else {
            System.out.println("Ошибка: Неверный формат ключа или значения.");
        }
    }

    public void removeEntry(String key) {
        if (dictionary.remove(key) != null) {
            saveDictionary();
        } else {
            System.out.println("Запись не найдена.");
        }
    }

    public String findEntry(String key) {
        return dictionary.get(key);
    }

    public void displayDictionary() {
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
    }

    private void saveDictionary() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                bw.write(entry.getKey() + ";" + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении словаря: " + e.getMessage());
        }
    }

    private boolean isValidKey(String key) {
        return key.length() == 4 && key.matches("[a-zA-Z]+");
    }

    private boolean isValidValue(String value) {
        return value.length() == 5 && value.matches("\\d+");
    }
}
