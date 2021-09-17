package mx.chux.cs.pzl;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Supplier;

public class TimedPuzzleSolution<T> implements Supplier<T> {

    final T solution;
    final long timeElapsed;

    static <T> TimedPuzzleSolution<T> from(final T solution, final Instant start, final Instant finish) {
        return new TimedPuzzleSolution<>(solution, start, finish);
    }

    TimedPuzzleSolution(final T solution, final Instant start, final Instant finish) {
        this.solution = solution;
        this.timeElapsed = Duration.between(start, finish).toMillis();
    }

    @Override
    public T get() {
        return this.solution;
    }

    public Long timeElapsed() {
        return Long.valueOf(timeElapsed);
    }

}
