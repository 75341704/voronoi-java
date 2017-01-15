package de.alsclo.voronoi.graph;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BisectorMap {

    private final Map<Bisector, Edge> data = new HashMap<>();

    public void put(Point a, Point b, Edge e) {
        assert !data.containsKey(new Bisector(a, b));
        data.put(new Bisector(a, b), e);
    }

    public Edge get(Point a, Point b) {
        return data.get(new Bisector(a, b));
    }

    public Collection<Edge> values() {
        return data.values();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BisectorMap that = (BisectorMap) o;

        return data.size() == that.data.size() && data.keySet().stream().allMatch(that.data.keySet()::contains);
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }

    @RequiredArgsConstructor
    @ToString
    private static class Bisector {
        @NonNull
        private final Point a, b;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Bisector bisector = (Bisector) o;
            return (a.equals(bisector.a) && b.equals(bisector.b)) || (a.equals(bisector.b) && b.equals(bisector.a));
        }

        @Override
        public int hashCode() {
            return a.hashCode() + b.hashCode();
        }
    }
}