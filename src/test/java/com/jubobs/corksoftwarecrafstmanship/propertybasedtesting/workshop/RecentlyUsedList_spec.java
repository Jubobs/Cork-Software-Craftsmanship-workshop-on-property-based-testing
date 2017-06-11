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

import static com.jubobs.corksoftwarecrafstmanship.propertybasedtesting.workshop.ListBasedRecentlyUsedList.newInstance;

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

        @Ignore
        @Property
        public void can_be_instantiated_with_a_positive_capacity() {

        }

        @Ignore
        @Property
        public void is_empty() {

        }

    }

    @RunWith(JUnitQuickcheck.class)
    public static final class An_empty_list {

        @Ignore
        @Property
        public void retains_a_single_addition() {

        }

        @Ignore
        @Property
        public void retains_unique_additions_in_stack_order_up_to_its_capacity() {

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

}
