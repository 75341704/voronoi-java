package de.alsclo.voronoi;

import de.alsclo.voronoi.beachline.Beachline;
import de.alsclo.voronoi.event.Event;
import de.alsclo.voronoi.event.SiteEvent;
import de.alsclo.voronoi.util.Point;
import lombok.val;

import java.util.Collection;
import java.util.PriorityQueue;

public class Voronoi {

    public Voronoi(double width, double height, Collection<Point> points) {
        val queue = new PriorityQueue<Event>();
        points.stream().map(SiteEvent::new).forEach(queue::offer);

        val beachline = new Beachline();

        while(!queue.isEmpty()) {
            queue.poll().handle(beachline).ifPresent(queue::add);
        }
    }

}
