package christmas.utils;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class DataInitializer<T> {

    private final Supplier<T> supplier;

    private final Consumer<IllegalArgumentException> exceptionHandler;

    public DataInitializer(Supplier<T> supplier, Consumer<IllegalArgumentException> exceptionHandler) {
        this.supplier = supplier;
        this.exceptionHandler = exceptionHandler;
    }

    public T initialize() {
        T item;
        while (true) {
            try {
                item = supplier.get();
                return item;
            } catch (IllegalArgumentException ex) {
                exceptionHandler.accept(ex);
            }
        }
    }
}
