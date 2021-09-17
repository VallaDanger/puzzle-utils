package mx.chux.cs.pzl;

import java.time.Instant;

public class PuzzleExecutor<T> {

    public static <T> PuzzleExecutor<T> from(final PuzzleSolution<T> puzzle) {
        return new PuzzleExecutor<T>(puzzle);
    }

    final PuzzleSolution<T> puzzle;

    private PuzzleExecutor(final PuzzleSolution<T> puzzle) {
        this.puzzle = puzzle;
    }

    public T execute() {
        return this.puzzle.optimalSolution();
    }

    public TimedPuzzleSolution<T> executeTimed() {
        final Instant start = Instant.now();
        final T solution = execute();
        final Instant finish = Instant.now();
        return TimedPuzzleSolution.from(solution, start, finish);
    }

}
