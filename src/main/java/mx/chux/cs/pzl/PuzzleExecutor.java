package mx.chux.cs.pzl;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class PuzzleExecutor<T> implements Supplier<Puzzle<T>> {

    public static <T> PuzzleExecutor<T> from(final Puzzle<T> puzzle) {
        return new PuzzleExecutor<T>(puzzle);
    }

    private final Puzzle<T> puzzle;
    private final AtomicInteger ticker;

    private PuzzleExecutor(final Puzzle<T> puzzle) {
        this.puzzle = puzzle;
        this.ticker = new AtomicInteger(0);
        this.puzzle.setTicker(this.ticker);
    }
    
    @Override
    public Puzzle<T> get() {
        return this.puzzle;
    }

    public T execute() {
        return execute(true);
    }

    public T execute(final boolean optimal) {
        resetTicker();
        return exec(optimal);
    }
    
    public TimedPuzzleSolution<T> executeTimed() {
        return executeTimed(true);
    }
    
    public TimedPuzzleSolution<T> executeTimed(final boolean optimal) {
        resetTicker();
        final Instant start = Instant.now();
        final T solution = exec(optimal);
        final Instant finish = Instant.now();
        return TimedPuzzleSolution.from(solution, this.ticker.get(), start, finish);
    }
    
    public int ticks() {
        return this.ticker.get();
    }
    
    private T exec(final boolean executeOptimal) {
        return executeOptimal? getOptimalSolution() : getBruteForceSolution();
    }
    
    private void resetTicker() {
        this.ticker.set(0);
    }
    
    private T getOptimalSolution() {
        return this.puzzle.optimalSolution();
    }
    
    private T getBruteForceSolution() {
        return this.puzzle.bruteForceSolution();
    }

}
