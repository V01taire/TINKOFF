package edu.hw3.Task5;

import java.util.Arrays;


@SuppressWarnings("HideUtilityClassConstructor")
public class Task5 {

    public static String[] parseContacts(String[] names, String direction) {
        if (names == null || names.length == 0) {
            return new String[0];
        }

        Contact[] contacts = Arrays.stream(names)
            .map(Contact::new)
            .toArray(Contact[]::new);

        Arrays.sort(contacts, (a, b) -> {
            if ("ASC".equalsIgnoreCase(direction)) {
                return a.lastName.compareToIgnoreCase(b.lastName);
            } else {
                return b.lastName.compareToIgnoreCase(a.lastName);
            }
        });

        return Arrays.stream(contacts)
            .map(contact -> contact.fullName)
            .toArray(String[]::new);
    }
}
