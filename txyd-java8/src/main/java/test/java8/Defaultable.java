package test.java8;

public interface Defaultable {
    // Interfaces now allow default methods, the implementer may or
    // may not implement (override) them.
    public default String notRequired() {
        return "Default implementation";
    }
}
