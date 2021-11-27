package ua.com.alevel;

import java.util.List;

public class Name {

    public static void getFirstUnique(List<String> input) {
        String output = "No unique names\n";
        for (int i = 0; i < input.size(); i++) {
            if (input.lastIndexOf(input.get(i)) == input.indexOf(input.get(i))) {
                if (input.get(i).isEmpty())
                    continue;
                output = input.get(i) + "\n";
                break;
            }
        }
        Output.run(output);
    }
}
