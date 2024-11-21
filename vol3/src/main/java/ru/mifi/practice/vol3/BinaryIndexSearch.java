package ru.mifi.practice.vol3;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class BinaryIndexSearch implements Search<Long, Number> {
    private final List<Long> array;

    public BinaryIndexSearch(List<Long> array) {
        this.array = array;
    }

    public Optional<Number> search(Long element, Counter counter) {
        Range<Long> range = new Range<>(0L, (long) array.size());
        return search(element, range, index -> array.get(Math.toIntExact(index)), counter);
    }

    @Override
    public Optional<Number> search(Long element, Range<Long> range, Function<Long> function, Counter counter) {
        var left = range.left;
        var right = range.right;
        while (!Objects.equals(left, right - 1)) {
            var mid = (right + left) >>> 1;
            var result = function.apply(mid);
            if (result > element) {
                right = mid;
            } else {
                left = mid;
            }
            counter.increment();
        }
        var result = function.apply(left);
        if (Objects.equals(result, element)) {
            return Optional.of(left);
        }
        return Optional.empty();
    }
}
