package java8_advantage;

import java.lang.annotation.Repeatable;

@Repeatable(Hints.class)
public @interface Hint {
    String value();
}
