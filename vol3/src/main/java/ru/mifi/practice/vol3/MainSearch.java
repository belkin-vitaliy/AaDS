package ru.mifi.practice.vol3;

import java.util.List;
import java.util.Optional;

public abstract class MainSearch {
    public static void main(String[] args) {
        Counter counter = new Counter.Default();
        boolean debug = true;
        Search<Long, Number> search = new BinaryNaturalSearch(debug);
        Optional<Number> n = search.search(24L, new Search.Range<>(10L, 17L), number -> 2 * number, counter);
        System.out.println("RES: " + n);
        System.out.println("OPS: " + counter);
        counter.reset();
        var real = new BinaryRealSearch(0.0001, debug);
        n = real.search(24., new Search.Range<>(10., 17.), i -> 2 * i, counter);
        System.out.println("RES: " + n);
        System.out.println("OPS: " + counter);
        counter.reset();
        List<Long> generated = NumberGenerator.generateSlice(1000);
        generated.sort(Long::compareTo);
        var index = new BinaryIndexSearch(generated, debug);
        n = index.search(10L, counter);
        System.out.println("RES: " + n);
        System.out.println("OPS: " + counter);
        counter.reset();
    }
}
