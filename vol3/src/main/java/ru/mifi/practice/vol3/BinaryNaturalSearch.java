package ru.mifi.practice.vol3;

import java.util.Objects;
import java.util.Optional;

public final class BinaryNaturalSearch implements Search<Long, Number> {
    private final boolean debug;

    public BinaryNaturalSearch(boolean debug) {
        this.debug = debug;
    }

    @Override
    public Optional<Number> search(Long element, Range<Long> range, Function<Long> function, Counter counter) {
        var left = range.left;
        var right = range.right;
        while (!Objects.equals(left, right - 1)) {
            var mid = (right + left) >>> 1;
            if (debug) {
                System.out.printf("[%2d - %2d]. mid: %d%n", left, right, mid);
            }
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
            return Optional.of(result);
        }
        return Optional.empty();
    }
}
