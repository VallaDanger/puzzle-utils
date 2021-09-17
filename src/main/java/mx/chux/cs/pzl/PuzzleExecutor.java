package mx.chux.cs.pzl;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

public class PuzzleExecutor<T> {

    public static <T> PuzzleExecutor<T> from(final Puzzle<T> puzzle) {
        return new PuzzleExecutor<T>(puzzle);
    }

    private final Puzzle<T> puzzle;
    private final AtomicInteger ticker;

    private PuzzleExecutor(final Puzzle<T> puzzle) {
        this.puzzle = puzzle;
        this.ticker = new AtomicInteger(0);
    }

    public T execute() {
        this.puzzle.setTicker(this.ticker);
        return this.puzzle.optimalSolution();
    }

    public TimedPuzzleSolution<T> executeTimed() {
        final Instant start = Instant.now();
        final T solution = execute();
        final Instant finish = Instant.now();
        return TimedPuzzleSolution.from(solution, this.ticker.get(), start, finish);
    }

}
