package edu.hw7.Task3_5;

class Contact {
    String fullName;
    String lastName;

    Contact(String fullName) {
        this.fullName = fullName;
        String[] words = fullName.split(" ");
        this.lastName = words.length > 1 ? words[words.length - 1] : words[0];
    }
}
