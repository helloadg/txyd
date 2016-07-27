package test.java8;

import java.util.function.Supplier;

public interface DefaultableFactory {
	// Interfaces now allow static methods
    public static Defaultable create( Supplier< Defaultable > supplier ) {
        return supplier.get();
    }
}
