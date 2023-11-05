package edu.hw4;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AnimalProcessor {
    private List<Animal> animals;

    public AnimalProcessor(List<Animal> animals) {
        this.animals = animals;
    }

    public List<Animal> sortAnimalsByHeight() {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .collect(Collectors.toList());
    }

    public List<Animal> sortAnimalsByWeight(int k) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k)
            .collect(Collectors.toList());
    }

    @SuppressWarnings("LineLength")
    public Map<Animal.Type, Integer> countAnimalsByType() {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));
    }

    public Optional<Animal> findAnimalWithLongestName() {
        return animals.stream()
            .max(Comparator.comparingInt(animal -> animal.name().length()));
    }

    public Animal.Sex findDominantSex() {
        long malesCount = animals.stream().filter(animal -> animal.sex() == Animal.Sex.M).count();
        long femalesCount = animals.size() - malesCount;

        return malesCount > femalesCount ? Animal.Sex.M : Animal.Sex.F;
    }

    public Map<Animal.Type, Animal> findHeaviestAnimalByType() {
        return animals.stream()
            .collect(Collectors.toMap(
                Animal::type,
                Function.identity(),
                BinaryOperator.maxBy(Comparator.comparingInt(Animal::weight))
            ));
    }

    public Optional<Animal> findKthOldestAnimal(int k) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::age))
            .skip(k - 1)
            .findFirst();
    }

    public Optional<Animal> findHeaviestAnimalBelowHeight(int maxHeight) {
        return animals.stream()
            .filter(animal -> animal.height() < maxHeight)
            .max(Comparator.comparingInt(Animal::weight));
    }

    public int sumOfPaws() {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    public List<Animal> findAnimalsWithMismatchedAgeAndPaws() {
        return animals.stream()
            .filter(animal -> animal.age() != animal.paws())
            .collect(Collectors.toList());
    }

    @SuppressWarnings("MagicNumber")
    public List<Animal> findBitingAnimalsWithHeightAbove100() {
        return animals.stream()
            .filter(animal -> animal.bites() && animal.height() > 100)
            .collect(Collectors.toList());
    }

    public long countAnimalsWithWeightExceedingHeight() {
        return animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count();
    }

    public List<Animal> findAnimalsWithMoreThanTwoWordsInName() {
        return animals.stream()
            .filter(animal -> animal.name().split("\\s+").length > 2)
            .collect(Collectors.toList());
    }

    public boolean hasDogWithHeightAbove(int k) {
        return animals.stream()
            .anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > k);
    }

    public Map<Animal.Type, Integer> totalWeightOfAnimalsWithAgeBetween(int k, int l) {
        return animals.stream()
            .filter(animal -> animal.age() >= k && animal.age() <= l)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    public List<Animal> sortAnimalsByTypeAndSexAndName() {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .collect(Collectors.toList());
    }

    @SuppressWarnings("MagicNumber")
    public boolean doSpidersBiteMoreOftenThanDogs() {
        long spiderBitesCount = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.SPIDER && animal.bites())
            .count();

        long dogBitesCount = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG && animal.bites())
            .count();

        if (spiderBitesCount + dogBitesCount < 10) {
            return false;
        }

        return spiderBitesCount > dogBitesCount;
    }

    public Animal findHeaviestFish(List<List<Animal>> fishLists) {
        return fishLists.stream()
            .flatMap(List::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight))
            .orElse(null);
    }

    public static class ValidationError {

        public String getErrorDescription() {
            return errorDescription;
        }

        public ValidationError(String errorDescription) {
            this.errorDescription = errorDescription;
        }

        private final String errorDescription;
    }

    @SuppressWarnings("InnerTypeLast")
    public Map<String, Set<ValidationError>> findAnimalsWithErrors() {
        Map<String, Set<ValidationError>> animalsWithErrors = new HashMap<>();

        for (Animal animal : animals) {
            Set<ValidationError> errors = new HashSet<>();

            if (animal.age() < 0) {
                errors.add(new ValidationError("Negative age"));
            }

            if (animal.height() < 0) {
                errors.add(new ValidationError("Negative height"));
            }

            if (animal.weight() < 0) {
                errors.add(new ValidationError("Negative weight"));
            }

            if (!errors.isEmpty()) {
                animalsWithErrors.put(animal.name(), errors);
            }
        }

        return animalsWithErrors;
    }

    @SuppressWarnings("InnerTypeLast")
    public Map<String, String> formatAnimalsWithErrors() {
        Map<String, Set<ValidationError>> animalsWithErrors = findAnimalsWithErrors();
        Map<String, String> formattedErrors = new HashMap<>();

        for (Map.Entry<String, Set<ValidationError>> entry : animalsWithErrors.entrySet()) {
            String animalName = entry.getKey();
            Set<String> errorDescriptions = entry.getValue().stream()
                .map(ValidationError::getErrorDescription)
                .collect(Collectors.toSet());

            String errors = String.join(", ", errorDescriptions);
            formattedErrors.put(animalName, errors);
        }

        return formattedErrors;
    }
}
