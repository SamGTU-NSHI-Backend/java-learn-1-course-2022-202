package ru.nshi.lesson2;

public class WorkWithArraysAndStrings {
    public static void main(String[] args) {
//      Создание массива с изначальными значениями
        int[] array = new int[]{1, 2, 4};
//      Создание пустого массива, все значения равны 0
//        int[] array = new int[4];
        for (int i = 0; i < array.length; i++) {
//          Запролняем элементы массива
            array[i] = i;

//          Получаем значение массива и выводим
            System.out.println(array[i]);
        }

//      Создание массива массивов
        int[][] matrix = new int[2][];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new int[4];
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

//      Создание строки
        String str = "Hello world!";
        str += " Beautiful world!";
        System.out.println(str);

//      Получение длины строки
        int length = str.length();
        System.out.println("Chars count in str: " + length);

//      Получение символа из строки
        System.out.println("str.charAt(0) = " + str.charAt(0));

//      Изменение регистра строки
        System.out.println("str.toLowerCase() = " + str.toLowerCase());
        System.out.println("str.toUpperCase() = " + str.toUpperCase());

//      Поиск символа в строке
        System.out.println("str.indexOf(\"w\") = " + str.indexOf("w"));
        System.out.println("str.lastIndexOf(\"w\") = " + str.lastIndexOf("w"));
        System.out.println("str.indexOf(\"0\") = " + str.indexOf("0"));

        System.out.println("str.contains(\"world\") = " + str.contains("world"));

//      Получение подстроки из строки
//      [beginIndex, endIndex)
        System.out.println("str.substring(str.indexOf(\"!\") + 1) = " + str.substring(str.indexOf("!") + 1));

        str = "test    w       orldw   \t";
        System.out.println("str = " + str);
//      Удаление с начала и с конца
        System.out.println("str.strip() = " + str.strip());

//      Замена символа w на h
        System.out.println("str.replace(\"w\", \"h\") = " + str.replace("w", "h"));

//      Замена всех символов пробела на один пробел
        System.out.println("str.replaceAll(\"\\\\s+\", \" \") = " + str.replaceAll("\\s+", " "));
        System.out.println("str.replaceFirst(\"\\\\s+\", \" \") = " + str.replaceFirst("\\s+", " "));

//      Проверка строки на пустоту
        System.out.println("\" \".isEmpty() = " + " ".isEmpty());
        System.out.println("\"\".isEmpty() = " + "".isEmpty());

        System.out.println("\" \".isBlank() = " + " ".isBlank());
        System.out.println("\"\".isBlank() = " + "".isBlank());

//      Получение позиции символа в таблице кодировки
        System.out.println("\"a\".codePointAt(0) = " + "a".codePointAt(0));
        System.out.println("\"A\".codePointAt(0) = " + "A".codePointAt(0));

        System.out.println("\"abc\".equals(\"ABC\") = " + "abc".equals("ABC"));
        System.out.println("\"abc\".equalsIgnoreCase(\"ABC\") = " + "abc".equalsIgnoreCase("ABC"));

//        Сравнение строк
//        is negative <
//        is zero =
//        is positive >
        System.out.println("\"0\".compareTo(\"1\") = " + "0".compareTo("1"));
        System.out.println("\"0\".compareTo(\"0\") = " + "0".compareTo("0"));
        System.out.println("\"1\".compareTo(\"0\") = " + "1".compareTo("0"));

        System.out.println("\"aa\".compareTo(\"az\") = " + "aa".compareTo("az"));

//      Разделение одной строки на несколько строк
        String[] words = "Hello world, i am tea pot!"
            .replaceAll("[^a-zA-Z\\s]", "")
            .split("\\s+");
        for (int i = 0; i < words.length; i++) {
            System.out.println(words[i]);
        }
    }
}
