package org.ernstjan.advent.day1;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

public class ReportRepair {

    private final int targetSum;

    public ReportRepair(int targetSum) {
        this.targetSum = targetSum;
    }

    public Optional<Long> find(int numberOfEntries, List<Integer> numbers) {
        checkArgument(numberOfEntries > 0, "At least 1 entry required");
        checkArgument(numberOfEntries <= numbers.size(), "Number of entries should be less or equal to available numbers");

        return possibleEntries(numberOfEntries, numbers)
                .filter(entries -> entries.sum() == targetSum)
                .map(Entries::product)
                .next()
                .blockOptional();
    }

    private Flux<Entries> possibleEntries(int numberOfEntries, List<Integer> numbers) {
        Flux<Tuple2<ImmutableSet<Long>, ImmutableList<Integer>>> listStream = indexedNumbersStream(numbers);

        for (int i = 1; i < numberOfEntries; i++) {
            listStream = listStream.flatMap(indexedList ->
                    indexedNumbersStream(numbers)
                            .map(indexedListToMerge -> Tuples.of(
                                    ImmutableSet.<Long>builder().addAll(indexedList.getT1()).addAll(indexedListToMerge.getT1()).build(),
                                    ImmutableList.<Integer>builder().addAll(indexedList.getT2()).addAll(indexedListToMerge.getT2()).build())));
        }

        return listStream
                // skip, when numbers where fetched form the same input position
                .filter(indicesValues -> indicesValues.getT1().size() == numberOfEntries)
                .map(Tuple2::getT2)
                .map(l -> l.toArray(Integer[]::new))
                .map(Entries::new);
    }

    private Flux<Tuple2<ImmutableSet<Long>, ImmutableList<Integer>>> indexedNumbersStream(List<Integer> numbers) {
        return Flux.fromIterable(numbers)
                .index()
                .map(indexedElement -> {
                    Long index = indexedElement.getT1();
                    Integer element = indexedElement.getT2();

                    return Tuples.of(ImmutableSet.of(index), ImmutableList.of(element));
                });
    }
}
