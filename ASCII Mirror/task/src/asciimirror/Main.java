package asciimirror;

import java.io.*;
import java.util.*;

public class Main {
    static final Map<Character, Character> NON_ALPHA  = new HashMap<>() ;

    public static void main(String[] args) {
        NON_ALPHA.put('<', '>');
        NON_ALPHA.put('>', '<');
        NON_ALPHA.put('[', ']');
        NON_ALPHA.put(']', '[');
        NON_ALPHA.put('{', '}');
        NON_ALPHA.put('}', '{');
        NON_ALPHA.put('(', ')');
        NON_ALPHA.put(')', '(');
        NON_ALPHA.put('/', '\\');
        NON_ALPHA.put('\\', '/');

        Scanner input = new Scanner(System.in);
        System.out.println("Input the file path:");
        String filePath = input.nextLine();

        List<String> lines = new ArrayList<>();

        File file = new File(filePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println("File not found!");
        }

        String longer = "";
        for (String line : lines) {
            if (longer.length() < line.length()) {
                longer = line;
            }
        }

        String modified;
        int nb;
        for (String line : lines) {
            nb = longer.length() - line.length();
            modified = String.format("%s%s", line, " ".repeat(nb));

            System.out.printf("%s | %s\n", modified, reverseLine(modified));
        }
    }

    private static String reverseLine(String line) {
        StringBuilder reverse = new StringBuilder(line).reverse();

        for (int i = 0, len = reverse.length(); i < len; i++) {
            if(NON_ALPHA.containsKey(reverse.charAt(i))){
                reverse.setCharAt(i, NON_ALPHA.get(reverse.charAt(i)));
            }
        }

        return reverse.toString();
    }
}