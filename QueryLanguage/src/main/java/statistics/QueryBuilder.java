package statistics;

import statistics.matcher.*;

public class QueryBuilder {
    Matcher matcher;

    public QueryBuilder() {
        matcher = new All();
    }

    public Matcher build() {
        return matcher;
    }

    public QueryBuilder playsIn(String team) {
        // Matcher m = new And(new Not(new HasAtLeast(1, "goals")), new PlaysIn("NYR"));
        // Matcher mat = new And(null);
        this.matcher = new And(this.matcher, new PlaysIn(team));
        return this;
    }

    public QueryBuilder hasAtLeast(int number, String type) {
        this.matcher = new And(this.matcher, new HasAtLeast(number, type));
        return this;
    }

    public QueryBuilder hasFewerThan(int number, String type) {
        this.matcher = new And(this.matcher, new HasFewerThan(number, type));
        return this;
    }

    public QueryBuilder oneOf(Matcher... matchers) {
        this.matcher = new Or(matchers);
        return this;
    }

}