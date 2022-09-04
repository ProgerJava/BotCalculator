
import java.util.ArrayList;
import java.util.Iterator;

public class Counter {

    ArrayList <String> list1 = new ArrayList<>();
    String finalValue = "";
    public ArrayList <String> split (String concat) {
        ArrayList <String> list = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < concat.length(); i++ ) {
            String character = String.valueOf(concat.charAt(i));
            if (character.matches("\\d")) {
                stringBuilder.append(character);
            }else if (character.equals(".")) {
                stringBuilder.append(character);
            }else {
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
        String [] array = stringBuilder.toString().split(" ");
        for (String str : array) {
            list.add(str);
        }
        return list;
    }
    public ArrayList <String> methodWithBracket (ArrayList <String> listOfBracket) {
        while (listOfBracket.contains("(") || listOfBracket.contains(")")) {
            for (int i = 0; i < listOfBracket.size(); i++) {
                if (!listOfBracket.contains("(") || !listOfBracket.contains(")")) {
                    break;
                }
                String s = listOfBracket.get(i);
                if (s.equals("(")) {
                    for (int j = i+1; j < listOfBracket.size(); j++) {
                        String r = listOfBracket.get(j);
                        if (r.equals("(")) {
                            break;
                        } else if (r.equals(")")) {
                            for (int z = i; z <= j; z++) {
                                list1.add(listOfBracket.get(z));
                            }
                            list1.remove(0);
                            list1.remove(list1.size()-1);
                            if (list1.contains("*") || list1.contains("/")) {
                                methodMultiplicationAndDivision(list1);
                            } else {
                                methodAdditionAndSubtraction(list1);
                            }
                            int count = 0;
                            for (Iterator<String> iterator = listOfBracket.iterator(); iterator.hasNext();) {
                                iterator.next();
                                if(count >= i && count <= j) {
                                    iterator.remove();
                                }
                                count++;
                            }
                            listOfBracket.add(i, list1.get(0));
                            list1.clear();
                            break;
                        }
                    }
                }
            }
        }
        if (listOfBracket.contains("*") || listOfBracket.contains("/")) {
            methodMultiplicationAndDivision(listOfBracket);
        } else if (listOfBracket.contains("+") || listOfBracket.contains("-")) {
            methodAdditionAndSubtraction(listOfBracket);
        }
        return listOfBracket;
    }
    public void methodMultiplicationAndDivision (ArrayList <String> list) {
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
            methodAdditionAndSubtraction(list);
        }
    }
    public void methodAdditionAndSubtraction (ArrayList <String> list) {
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
    }
    public void setResult (ArrayList <String> list1) {
       finalValue = list1.toString().replaceAll("^\\[|]$", "");
    }

}
