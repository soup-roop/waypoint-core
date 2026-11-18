package waypoint.model;

import java.time.Instant;
import java.util.Objects;

public final class Ticket {
    private final TicketId id;
    private final SpotId spot;
    private final Instant entry;
    private Instant exit;
    private Money fare;

    private Ticket(TicketId id, SpotId spot, Instant entry) {
        this.id = Objects.requireNonNull(id, "id");
        this.spot = Objects.requireNonNull(spot, "spot");
        this.entry = Objects.requireNonNull(entry, "entry");
    }

    public static Ticket open(TicketId id, SpotId spot, Instant entry) {
        return new Ticket(id, spot, entry);
    }

    public boolean isOpen() {
        return exit == null;
    }

    public Money fare() {
        return fare;
    }

    public TicketId id() {
        return id;
    }

    public Money close(Instant at, FarePolicy policy) {
        Objects.requireNonNull(at, "exit time");
        Objects.requireNonNull(policy, "fare policy");
        if (!isOpen()) {
            throw new TicketAlreadyClosed(id);
        }
        if (at.isBefore(entry)) {
            throw new IllegalArgumentException("exit " + at + " before entry " + entry);
        }
        Money computed = Objects.requireNonNull(policy.fareFor(entry, at, spot), "fare");
        if (computed.isNegative()) {
            throw new IllegalArgumentException("negative fare: " + computed);
        }
        this.exit = at;
        this.fare = computed;
        return this.fare;
    }
}

final class TicketAlreadyClosed extends RuntimeException {
    TicketAlreadyClosed(TicketId id) {
        super("Ticket already closed: " + id);
    }
}