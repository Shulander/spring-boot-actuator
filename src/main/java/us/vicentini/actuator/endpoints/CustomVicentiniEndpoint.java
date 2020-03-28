package us.vicentini.actuator.endpoints;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

@WebEndpoint(id = "vicentini")
@Component
public class CustomVicentiniEndpoint {

    private final Set<String> strings;


    public CustomVicentiniEndpoint() {
        strings = new TreeSet<>();
        strings.add("foo");
        strings.add("bar");
        strings.add("baz");
    }


    @ReadOperation
    public Collection<String> strings() {
        return strings;
    }


    @ReadOperation
    public String strings(@Selector String name) {
        return strings.contains(name) ? name : null;
    }


    @WriteOperation
    public void writeOperation(@Selector String name) {
        strings.add(name);
    }


    @DeleteOperation
    public void deleteOperation(@Selector String name) {
        strings.remove(name);
    }

}
