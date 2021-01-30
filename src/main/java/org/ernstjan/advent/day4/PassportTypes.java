package org.ernstjan.advent.day4;

import lombok.Value;

public class PassportTypes {
    public interface WrappedValue<T> {
        T getValue();
    }

    @Value
    static class Year implements WrappedValue<Integer> {
        Integer value;
        String raw;

        static Year value(Integer value) {
            return new Year(value, null);
        }

        static Year raw(String input) {
            return new Year(null, input);
        }
    }

    @Value
    static class Height implements WrappedValue<Integer> {
        Integer value;
        String raw;

        static Height value(String valueIncludingUnit) {
            String unit = valueIncludingUnit.substring(valueIncludingUnit.length() - 2);
            String stringValue = valueIncludingUnit.substring(0, valueIncludingUnit.length() - 2);
            int value = Integer.parseInt(stringValue);

            switch (unit) {
                case "cm":
                    return new Height(value, null);
                case "in":
                    return new Height(Math.round(value * 2.54f), null);
                default:
                    throw new IllegalArgumentException("Unknown unit: " + unit);
            }
        }

        static Height raw(String input) {
            return new Height(null, input);
        }
    }

    @Value
    static class NamedColor implements WrappedValue<String> {
        String value;
        String raw;

        static NamedColor value(String value) {
            return new NamedColor(value, null);
        }

        static NamedColor raw(String input) {
            return new NamedColor(null, input);
        }
    }

    @Value
    static class HexColor implements WrappedValue<String> {
        String value;
        String raw;

        static HexColor value(String value) {
            return new HexColor(value, null);
        }

        static HexColor raw(String input) {
            return new HexColor(null, input);
        }
    }

    @Value
    static class Id implements WrappedValue<String> {
        String value;
        String raw;

        static Id value(String value) {
            return new Id(value, null);
        }

        static Id raw(String input) {
            return new Id(null, input);
        }
    }
}
