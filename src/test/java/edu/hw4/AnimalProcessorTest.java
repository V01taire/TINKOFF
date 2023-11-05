package edu.hw4;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

public class AnimalProcessorTest {

    private AnimalProcessor animalProcessor;

    @BeforeEach
    void setUp() {
        List<Animal> animals = Arrays.asList(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 2, 30, 4, true),
            new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 3, 35, 5, true),
            new Animal("Bird", Animal.Type.BIRD, Animal.Sex.M, 1, 20, 2, false),
            new Animal("Fish", Animal.Type.FISH, Animal.Sex.F, 1, 10, 1, false)
        );
        animalProcessor = new AnimalProcessor(animals);
    }

    @Test
    @DisplayName("Тест для метода sortAnimalsByHeight")
    void testSortAnimalsByHeight() {
        List<Animal> sortedByHeight = animalProcessor.sortAnimalsByHeight();
        assertThat(sortedByHeight).isSortedAccordingTo(Comparator.comparingInt(Animal::height));
    }

    @Test
    @DisplayName("Тест для метода sortAnimalsByWeight")
    void testSortAnimalsByWeight() {
        List<Animal> sortedByWeight = animalProcessor.sortAnimalsByWeight(2);
        assertThat(sortedByWeight).isSortedAccordingTo(Comparator.comparingInt(Animal::weight).reversed());
        assertThat(sortedByWeight).hasSize(2);
    }

    @Test
    @DisplayName("Тест для метода countAnimalsByType")
    void testCountAnimalsByType() {
        Map<Animal.Type, Integer> animalCountByType = animalProcessor.countAnimalsByType();
        assertThat(animalCountByType.get(Animal.Type.CAT)).isEqualTo(1);
        assertThat(animalCountByType.get(Animal.Type.DOG)).isEqualTo(1);
        assertThat(animalCountByType.get(Animal.Type.BIRD)).isEqualTo(1);
        assertThat(animalCountByType.get(Animal.Type.FISH)).isEqualTo(1);
    }

    @Test
    @DisplayName("Тест для метода findAnimalWithLongestName")
    void testFindAnimalWithLongestName() {
        Optional<Animal> animalWithLongestName = animalProcessor.findAnimalWithLongestName();
        assertThat(animalWithLongestName.isPresent()).isTrue();
        assertThat(animalWithLongestName.get().name()).isEqualTo("Bird");
    }

    @Test
    @DisplayName("Тест для метода countMaleAndFemaleAnimals")
    void testCountMaleAndFemaleAnimals() {
        Animal.Sex sexWithMoreCount = animalProcessor.findDominantSex();
        assertThat(sexWithMoreCount).isEqualTo(Animal.Sex.F);
    }

    @Test
    @DisplayName("Тест для метода findHeaviestAnimalByType")
    void testFindHeaviestAnimalByType() {
        Map<Animal.Type, Animal> heaviestAnimalsByType = animalProcessor.findHeaviestAnimalByType();
        assertThat(heaviestAnimalsByType.get(Animal.Type.CAT).name()).isEqualTo("Cat");
        assertThat(heaviestAnimalsByType.get(Animal.Type.DOG).name()).isEqualTo("Dog");
        assertThat(heaviestAnimalsByType.get(Animal.Type.BIRD).name()).isEqualTo("Bird");
        assertThat(heaviestAnimalsByType.get(Animal.Type.FISH).name()).isEqualTo("Fish");
    }

    @Test
    @DisplayName("Тест для метода findKthOldestAnimal")
    void testFindKthOldestAnimal() {
        Optional<Animal> kthOldestAnimal = animalProcessor.findKthOldestAnimal(2);
        assertThat(kthOldestAnimal.isPresent()).isTrue();
        assertThat(kthOldestAnimal.get().name()).isEqualTo("Fish");
    }

    @Test
    @DisplayName("Тест для метода findHeaviestAnimalBelowHeight")
    void testFindHeaviestAnimalBelowHeight() {
        Optional<Animal> heaviestAnimalBelowHeight = animalProcessor.findHeaviestAnimalBelowHeight(25);
        assertThat(heaviestAnimalBelowHeight.isPresent()).isTrue();
        assertThat(heaviestAnimalBelowHeight.get().name()).isEqualTo("Bird");
    }

    @Test
    @DisplayName("Тест для метода sumAllPaws")
    void testSumAllPaws() {
        int totalPaws = animalProcessor.sumOfPaws();
        assertThat(totalPaws).isEqualTo(10);
    }

    @Test
    @DisplayName("Тест для метода findAnimalsWithMismatchedAgeAndPaws")
    void testFindAnimalsWithMismatchedAgeAndPaws() {
        List<Animal> animalsWithMismatchedAgeAndPaws = animalProcessor.findAnimalsWithMismatchedAgeAndPaws();
        assertThat(animalsWithMismatchedAgeAndPaws).hasSize(4);
        assertThat(animalsWithMismatchedAgeAndPaws.get(0).name()).isEqualTo("Cat");
        assertThat(animalsWithMismatchedAgeAndPaws.get(1).name()).isEqualTo("Dog");
    }

    @Test
    @DisplayName("Тест для метода findBitingAnimalsWithHeightGreaterThan")
    void testFindBitingAnimalsWithHeightGreaterThan() {
        List<Animal> bitingAnimalsWithHeightGreaterThan = animalProcessor.findBitingAnimalsWithHeightAbove100();
        assertThat(bitingAnimalsWithHeightGreaterThan).hasSize(0);
    }

    @Test
    @DisplayName("Тест для метода countAnimalsWithWeightExceedingHeight")
    void testCountAnimalsWithWeightExceedingHeight() {
        long count = animalProcessor.countAnimalsWithWeightExceedingHeight();
        assertThat(count).isEqualTo(0);
    }

    @Test
    @DisplayName("Тест для метода findAnimalsWithNamesContainingMoreThanTwoWords")
    void testFindAnimalsWithNamesContainingMoreThanTwoWords() {
        List<Animal> animalsWithNamesContainingMoreThanTwoWords = animalProcessor.findAnimalsWithMoreThanTwoWordsInName();
        assertThat(animalsWithNamesContainingMoreThanTwoWords).hasSize(0);
    }

    @Test
    @DisplayName("Тест для метода containsDogWithHeightGreaterThan")
    void testContainsDogWithHeightGreaterThan() {
        boolean containsDogWithHeightGreaterThan25 = animalProcessor.hasDogWithHeightAbove(25);
        assertThat(containsDogWithHeightGreaterThan25).isTrue();
    }

    @Test
    @DisplayName("Тест для метода calculateTotalWeightByTypeWithAgeBetween")
    void testCalculateTotalWeightByTypeWithAgeBetween() {
        Map<Animal.Type, Integer> totalWeightByType = animalProcessor.totalWeightOfAnimalsWithAgeBetween(1, 3);
        assertThat(totalWeightByType.get(Animal.Type.CAT)).isEqualTo(4);
        assertThat(totalWeightByType.get(Animal.Type.DOG)).isEqualTo(5);
        assertThat(totalWeightByType.get(Animal.Type.BIRD)).isEqualTo(2);
        assertThat(totalWeightByType.get(Animal.Type.FISH)).isEqualTo(1);
    }

    @Test
    @DisplayName("Тест для метода sortAnimalsByTypeThenSexThenName")
    void testSortAnimalsByTypeThenSexThenName() {
        List<Animal> sortedAnimals = animalProcessor.sortAnimalsByTypeAndSexAndName();
        assertThat(sortedAnimals.get(0).name()).isEqualTo("Cat");
        assertThat(sortedAnimals.get(1).name()).isEqualTo("Dog");
        assertThat(sortedAnimals.get(2).name()).isEqualTo("Bird");
        assertThat(sortedAnimals.get(3).name()).isEqualTo("Fish");
    }

    @Test
    @DisplayName("Тест для метода doSpidersBiteMoreOftenThanDogs")
    void testDoSpidersBiteMoreOftenThanDogs() {
        boolean spidersBiteMoreOftenThanDogs = animalProcessor.doSpidersBiteMoreOftenThanDogs();
        assertThat(spidersBiteMoreOftenThanDogs).isFalse();
    }

    @Test
    @DisplayName("Тест для метода findHeaviestFish")
    void testFindHeaviestFish() {
        List<List<Animal>> animalLists = Arrays.asList(
            Arrays.asList(
                new Animal("Fish1", Animal.Type.FISH, Animal.Sex.M, 2, 15, 2, false),
                new Animal("Fish2", Animal.Type.FISH, Animal.Sex.F, 3, 18, 3, false)
            ),
            Arrays.asList(
                new Animal("Fish3", Animal.Type.FISH, Animal.Sex.M, 1, 13, 1, false)
            )
        );
        Animal heaviestFish = animalProcessor.findHeaviestFish(animalLists);
        assertThat(heaviestFish.name()).isEqualTo("Fish2");
    }

    @Test
    @DisplayName("Тест для метода formatAnimalsWithErrors")
    void testFormatAnimalsWithErrors() {
        Map<String, String> animalsWithErrorsFormatted = animalProcessor.formatAnimalsWithErrors();
        assertThat(animalsWithErrorsFormatted.get("Fish")).isEqualTo(null);
    }

    @Test
    @DisplayName("Тест для метода findAnimalsWithErrorsFormatted")
    void testFindAnimalsWithErrorsFormatted() {
        Map<String, String> animalsWithErrorsFormatted = animalProcessor.formatAnimalsWithErrors();
        assertThat(animalsWithErrorsFormatted.get("Fish")).isEqualTo(null);
    }
}
