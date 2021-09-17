package mx.chux.cs.pzl;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Puzzle<T> implements PuzzleSolution<T> {

    AtomicInteger ticker;

    void setTicker(final AtomicInteger ticker) {
        this.ticker = ticker;
    }

    protected int tick() {
        return this.ticker.incrementAndGet();
    }

}
