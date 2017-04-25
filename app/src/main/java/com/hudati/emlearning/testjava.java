package com.hudati.emlearning;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huylv on 16-Apr-17.
 */

public class testjava {
    public static void main(String[] args) {
        System.out.println("===> Filter edit_text");
        editext_filter();

        System.out.println("===> Filter multi_choice");
        multi_choice_filter();

        System.out.println("===> Filter dropdown");
        dropdown_filter();

        System.out.println("===> Filter single_choice");
        single_choice_filter();
    }


    protected static void single_choice_filter() {

        String s = "21. What do the students agree should "
                + "be included in their aims?\n[21:A] factors "
                + "affecting where organisms live\n[21:B]. the need to preserve "
                + "endangered species\n[21:C].techniques for classifying different "
                + "organisms\n22. What equipment did they forget to "
                + "take on the Field Trip?\n[22:A]. string\n[22:B]. a "
                + "compass\n[22:C]. a ruler\n23. In Helenâ€™s procedure section, "
                + "Colin suggests a change in\n[23:A]. the order in which "
                + "information is given.\n[23:B].the way the information is "
                + "divided up.\n[23:C]. the amount of information provided.\n";

        String pattern = "\\[(\\d+:[A-D])]";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(s);

        while (matcher.find()) {
            System.out.println(matcher.group());
        }

    }


    protected static void dropdown_filter() {

        String s = "17. box office [17:A,B,C,D,E,F,G]\n18. "
                + "theatre managerâ€™s office [18:A,B,C,D,E,F,G]\n19. lighting "
                + "box [19:A,B,C,D,E,F,G]\n20. artistic directorâ€™s office [20:A,B,C,D,E,F,G]";

        String pattern = "\\[(\\d+:.*)]";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(s);

        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }


    protected static void multi_choice_filter() {
        String s = "[11,12:A].Some rooms now have a different use.\n[11,12:B]. A different "
                + "type of seating has been installed.\n[11,12:C].An elevator has been"
                + " installed.\n[11,12:D].The outside of the building has been"
                + " repaired.\n[11,12:E].The outside of the building has been repaired.";

        String pattern = "\\[(\\d+.*:[A-Z])]";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(s);

        while (matcher.find()) {
            System.out.println(matcher.group());
        }

    }


    protected static void editext_filter() {
        String s = "Currently staying in a [1:...] during the week\nPostal address: 17, [2:...] Street, "
                + "Stamford, Lines\nPostcode: [3:...]\nOccupation: student and part-time job as a [4:...]\nStudying "
                + "[5:...] (major subject) and history (minor subject)\nHobbies: does a lot of [6:...], "
                + "and is interested in the [7:...]\nOn Youth Council, "
                + "wants to work with young people who are [8:...]\nWill come to talk to the Elections"
                + " Officer next Monday at [9:...] am\nMobile number: [10:...]";

        String pattern = "\\[(\\d+:...)]";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(s);

        while (matcher.find()) {
            System.out.println(matcher.group());
        }

    }

}
