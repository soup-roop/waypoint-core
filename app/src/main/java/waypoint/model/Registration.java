package waypoint.model;

import java.util.Objects;
import java.util.regex.Pattern;

public record Registration(String value) {
    private static final Pattern P = Pattern.compile("[A-Z]{2}\\d{2}[A-Z]{1,2}\\d{4}");

    public Registration {
        Objects.requireNonNull(value, "registration required");
        value = value.trim().toUpperCase();
        if (!P.matcher(value).matches()) {
            throw new IllegalArgumentException("bad registration: " + value);
        }
    }

    @Override
    public String toString() {
        return value;
    }
}