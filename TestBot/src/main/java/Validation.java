public class Validation {
    int leftBracket = 0;
    int rightBracket = 0;
    int countNumber = 0;
    int twoCharactersInARow = 0;
    int presenceOfLetters = 0;

    public boolean formValidation (String message) {
        if (message.startsWith("*") || message.startsWith("/") || message.startsWith("+")) {
            return false;
        }
        if (message.contains(" ")) {
            return false;
        }
        for (int i = 0; i < message.length(); i++) {
            String character = String.valueOf(message.charAt(i));
            if (character.equals(")")) {
                leftBracket++;
            }
            else if (character.equals("(")) {
                rightBracket++;
            }
            else if (character.matches("\\d")) {
                countNumber++;
            }
            else if (i != 0 && character.matches("[+\\-*/]") && (String.valueOf(message.charAt(i-1)).matches("[+\\-*/]"))) {
                twoCharactersInARow++;
            }
            else if (character.matches("[a-zA-Z]")) {
                presenceOfLetters++;
            }
            else if (character.matches("[а-яА-Я]")) {
                presenceOfLetters++;
            }
        }
        if (leftBracket != rightBracket) {
            return false;
        }
        if (countNumber < 1) {
            return false;
        }
        if (twoCharactersInARow >= 1) {
            return false;
        }
        if (presenceOfLetters >= 1) {
            return false;
        }
        return true;
    }
}
