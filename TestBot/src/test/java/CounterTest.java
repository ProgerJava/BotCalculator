import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;


public class CounterTest {
    ArrayList <String> list;
    @ParameterizedTest
    @MethodSource("pull")
    public void split (String concat) {
        Assert.assertNotNull(concat);
        ArrayList<String> list = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < concat.length(); i++) {
            String character = String.valueOf(concat.charAt(i));
            if (character.matches("\\d")) {
                stringBuilder.append(character);
            } else if (character.equals(".")) {
                stringBuilder.append(character);
            } else {
                if (i != 0 && !String.valueOf(stringBuilder.toString().charAt(stringBuilder.length() - 1)).equals(" ")) {
                    stringBuilder.append(" ");
                    stringBuilder.append(character);
                    stringBuilder.append(" ");
                } else {
                    stringBuilder.append(character);
                    stringBuilder.append(" ");
                }
            }
        }
        String[] array = stringBuilder.toString().split(" ");
        Assert.assertArrayEquals(new String []{"2", "+", "2"}, array);
        for (String str : array) {
            list.add(str);
        }
    }
    static Stream <String> pull () {
        return Stream.of("2+2");
    }


    @Test
    public void methodMultiplicationAndDivision () {
        list1();
        while (list.contains("*") || list.contains("/")) {
            for (int i = 0; i < list.size(); i++) {
                String s = list.get(i);
                if (s.equals("*")) {
                    float var1 = Float.parseFloat(list.get(i - 1));
                    float var2 = Float.parseFloat(list.get(i + 1));
                    float summa = var1 * var2;
                    list.add(i, String.valueOf(summa));
                    list.remove(i + 2);
                    list.remove(i + 1);
                    list.remove(i - 1);
                } else if (s.equals("/")) {
                    float var1 = Float.parseFloat(list.get(i - 1));
                    float var2 = Float.parseFloat(list.get(i + 1));
                    float summa = var1 / var2;
                    list.add(i, String.valueOf(summa));
                    list.remove(i + 2);
                    list.remove(i + 1);
                    list.remove(i - 1);
                }
            }
        }
        if (list.contains("-") || list.contains("+")) {
            //methodAdditionAndSubtraction(list);
        }
        Assert.assertEquals("100.0", list.get(0));
    }
    @Test
    public void methodAdditionAndSubtraction () {
        list2();
        while (list.size() > 1) {
            for (int i = 0; i < list.size(); i++) {
                String s = list.get(i);
                if (s.equals("+")) {
                    float var1 = Float.parseFloat(list.get(i - 1));
                    float var2 = Float.parseFloat(list.get(i + 1));
                    float summa = var1 + var2;
                    list.add(i, String.valueOf(summa));
                    list.remove(i + 2);
                    list.remove(i + 1);
                    list.remove(i - 1);
                } else if (s.equals("-")) {
                    float var1 = Float.parseFloat(list.get(i - 1));
                    float var2 = Float.parseFloat(list.get(i + 1));
                    float summa = var1 - var2;
                    list.add(i, String.valueOf(summa));
                    list.remove(i + 2);
                    list.remove(i + 1);
                    list.remove(i - 1);
                }
            }
        }
        Assert.assertEquals("9.0", list.get(0));
    }
    public void list1 () {
        list = new ArrayList<>();
        list.add("25");
        list.add("*");
        list.add("4");
    }
    public void list2 () {
        list = new ArrayList<>();
        list.add("25");
        list.add("-");
        list.add("16");
    }
}
