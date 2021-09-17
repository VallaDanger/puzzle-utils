package mx.chux.cs.pzl;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Supplier;

public class TimedPuzzleSolution<T> implements Supplier<T> {

    private final T solution;
    private final long timeElapsed;
    private final int ticks;

    static <T> TimedPuzzleSolution<T> from(final T solution, final int ticks, final Instant start, final Instant finish) {
        return new TimedPuzzleSolution<>(solution, ticks, start, finish);
    }

    TimedPuzzleSolution(final T solution, final int ticks, final Instant start, final Instant finish) {
        this.solution = solution;
        this.timeElapsed = Duration.between(start, finish).toMillis();
        this.ticks = ticks;
    }
    
    TimedPuzzleSolution(final T solution, final Instant start, final Instant finish) {
        this(solution, -1, start, finish);
    }

    @Override
    public T get() {
        return this.solution;
    }

    public Long timeElapsed() {
        return Long.valueOf(timeElapsed);
    }
    
    public Integer ticks() {
        return Integer.valueOf(this.ticks);
    }

}
