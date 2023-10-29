package edu.hw3.Task5;

class Contact {
    String fullName;
    String lastName;

    Contact(String fullName) {
        this.fullName = fullName;
        String[] words = fullName.split(" ");
        this.lastName = words.length > 1 ? words[words.length - 1] : words[0];
    }
}
