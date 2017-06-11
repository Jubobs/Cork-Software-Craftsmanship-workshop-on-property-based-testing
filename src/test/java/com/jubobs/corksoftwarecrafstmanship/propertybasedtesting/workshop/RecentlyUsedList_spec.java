package com.jubobs.corksoftwarecrafstmanship.propertybasedtesting.workshop;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.util.*;

import static com.jubobs.corksoftwarecrafstmanship.propertybasedtesting.workshop.ListBasedRecentlyUsedList.newInstance;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Enclosed.class)
public final class RecentlyUsedList_spec {

    @RunWith(JUnitQuickcheck.class)
    public static final class A_new_list {

        @Rule
        public final ExpectedException thrown = ExpectedException.none();

        @Property
        public void cannot_be_instantiated_with_a_nonpositive_capacity(
                @InRange(maxInt = 0) int capacity) {
            thrown.expect(IllegalArgumentException.class);
            newInstance(capacity);
        }

        @Property
        public void can_be_instantiated_with_a_positive_capacity(
            @InRange(minInt = 1) int capacity) {
                RecentlyUsedList<String> rul = newInstance(capacity);

                assertThat(rul.capacity()).isEqualTo(capacity);
        }

        @Property
        public void is_empty(
            @InRange(minInt = 1) int capacity) {
                RecentlyUsedList<?> rul = newInstance(capacity);

                assertThat(rul.isEmpty()).isTrue();
                assertThat(rul.size()).isEqualTo(0);
        }

    }

    @RunWith(JUnitQuickcheck.class)
    public static final class An_empty_list {

        @Property
        public void retains_a_single_addition(
            @InRange(minInt = 1) int capacity,
            String element) {
                RecentlyUsedList<String> rul = newInstance(capacity);

                rul.push(element);

                assertThat(rul.size()).isEqualTo(1);
                assertThat(rul.elementAt(0)).isEqualTo(element);
        }

        @Property
        public void retains_unique_additions_in_stack_order_up_to_its_capacity(
                @InRange(minInt = 1) int capacity,
                Set<String> uniqueItems) {
            RecentlyUsedList<String> rul = recentlyUsedListBuiltFrom(capacity, uniqueItems);

            assertThat(rul.size()).isEqualTo(uniqueItems.size());

            List<String> copy = new ArrayList<>(uniqueItems);
            Collections.reverse(copy);
            int toIndex = Math.min(uniqueItems.size(), rul.capacity());
            List<String> expected = copy.subList(0, toIndex);
            assertThat(rul.toList()).isEqualTo(expected);
        }

    }

    @RunWith(JUnitQuickcheck.class)
    public static final class An_nonempty_list {

        @Ignore
        @Property
        public void is_unchanged_when_its_head_item_is_re_added() {

        }

    }

    @RunWith(JUnitQuickcheck.class)
    public static final class Any_list {

        @Ignore
        @Property
        public void rejects_the_addition_of_a_null_item() {

        }

        @Ignore
        @Property
        public void of_at_least_two_items_moves_a_non_head_item_to_head_when_that_item_is_re_added() {

        }

        @Ignore
        @Property
        public void that_gets_cleared_yields_an_empty_list_of_the_same_capacity() {

        }

        @Ignore
        @Property
        public void allows_indexing_within_its_bounds() {

        }

        @Ignore
        @Property
        public void rejects_negative_indexing() {

        }

        @Ignore
        @Property
        public void rejects_indexing_past_its_end() {

        }

        @Ignore
        @Test
        public void satisfies_the_equals_hashCode_contract() {

        }

        @Ignore
        @Property
        public void has_a_sensible_toString_implementation() {

        }

    }

    private static <T> RecentlyUsedList<T> recentlyUsedListBuiltFrom(
            int capacity,
            Collection<T> items) {
        RecentlyUsedList<T> rul = newInstance(capacity);
        items.forEach(rul::push);
        return rul;
    }

}
