package edu.project1;

import java.util.Objects;

sealed interface GuessResult {

    char[] state();

    String message();

    record Defeat(char[] state) implements GuessResult {
        public Defeat {
            Objects.requireNonNull(state);
        }

        @Override
        public String message() {
            return "You lost! The correct word was: " + new String(state);
        }
    }

    record Win(char[] state, int attempts) implements GuessResult {
        public Win {
            Objects.requireNonNull(state);
        }

        @Override
        public String message() {
            return "You won! Congratulations! Number of attempts: " + (attempts - 1);
        }
    }

    record Hit(char[] state) implements GuessResult {
        public Hit {
            Objects.requireNonNull(state);
        }

        @Override
        public String message() {
            return "Hit!";
        }
    }

    record Miss(char[] state) implements GuessResult {
        public Miss {
            Objects.requireNonNull(state);
        }

        @Override
        public String message() {
            return "Miss!";
        }
    }
}

