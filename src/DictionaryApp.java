import java.util.Scanner;

public class DictionaryApp {
    private static final String FILE_PATH_EN_RU = "dictionary_en_ru.txt";
    private static final String FILE_PATH_NUM_RU = "dictionary_num_ru.txt";
    private static DictionaryService englishToRussianDictionary;
    private static DictionaryService numberToRussianDictionary;

    public static void main(String[] args) {
        englishToRussianDictionary = new DictionaryService(FILE_PATH_EN_RU);
        numberToRussianDictionary = new DictionaryService(FILE_PATH_NUM_RU);
        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            System.out.println("Выберите словарь:");
            System.out.println("1. Английский-Русский");
            System.out.println("2. Числовой-Русский");
            System.out.println("3. Выход");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    handleDictionary(englishToRussianDictionary, "Английский-Русский");
                    break;
                case "2":
                    handleDictionary(numberToRussianDictionary, "Числовой-Русский");
                    break;
                case "3":
                    System.out.println("Выход...");
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        } while (!choice.equals("3"));

        scanner.close();
    }

    private static void handleDictionary(DictionaryService dictionary, String language) {
        Scanner scanner = new Scanner(System.in);
        String action;

        do {
            System.out.println("\nВыберите действие для " + language + ":");
            System.out.println("1. Просмотр содержимого");
            System.out.println("2. Добавить запись");
            System.out.println("3. Удалить запись");
            System.out.println("4. Найти запись");
            System.out.println("5. Вернуться в главное меню");
            action = scanner.nextLine();

            switch (action) {
                case "1":
                    dictionary.displayDictionary();
                    break;
                case "2":
                    System.out.print("Введите ключ: ");
                    String keyToAdd;
                    keyToAdd = scanner.nextLine();
                    System.out.print("Введите значение: ");
                    String valueToAdd;
                    valueToAdd = scanner.nextLine();
                    dictionary.addEntry(keyToAdd, valueToAdd);
                    break;
                case "3":
                    System.out.print("Введите ключ для удаления: ");
                    String keyToRemove = scanner.nextLine();
                    dictionary.removeEntry(keyToRemove);
                    break;
                case "4":
                    System.out.print("Введите ключ для поиска: ");
                    String keyToFind = scanner.nextLine();
                    String result = dictionary.findEntry(keyToFind);
                    if (result != null) {
                        System.out.println("Найдено: " + keyToFind + " => " + result);
                    } else {
                        System.out.println("Запись не найдена.");
                    }
                    break;
                case "5":
                    System.out.println("Возврат в главное меню...");
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        } while (!action.equals("5"));
    }
}

