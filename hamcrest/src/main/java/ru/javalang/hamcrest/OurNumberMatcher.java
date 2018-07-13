package ru.javalang.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class OurNumberMatcher extends TypeSafeDiagnosingMatcher<Integer> {

    public void describeTo(Description description) {
        description.appendText("GreaterThan 5 and LessThanOrEqual to 7 and notEqualtTo 6");

    }

    @Override
    protected boolean matchesSafely(Integer item, Description mismatchDescription) {
        int i = item.intValue();
        mismatchDescription.appendText("was ")
            .appendValue(i)
            .appendText(", which is not what we need");
        return (i > 5 && i <= 7 && i != 6);

    }

    public static OurNumberMatcher isOurNumber() {
        return new OurNumberMatcher();
    }

}