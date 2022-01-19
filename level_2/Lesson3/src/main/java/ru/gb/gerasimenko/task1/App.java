package ru.gb.gerasimenko.task1;
import ru.gb.gerasimenko.Surnames;

/*
** 1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
**    Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
**    Посчитать, сколько раз встречается каждое слово.
*/


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class App
{
    private static ArrayList<String> list = new ArrayList<>();
    private static Random random = new Random();

    static {
        int max = random.nextInt(15, 20);
        for (int i = 0; i < max; i++) {
            int num = random.nextInt(max / 2) ;
            list.add(Surnames.values()[num].getSurname());
        }
    }

    public static void main( String[] args )
    {
        System.out.println("Orginal list: \n\t" + list);
        System.out.println("Unique values=repeat:");
        System.out.println(countUnique(list));
    }

    private static HashMap<String, Integer> countUnique(ArrayList<String> list) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        int primaryValue = 1;

        for (String s : list) {
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, primaryValue);
            }
        }
        return map;
    }
}
