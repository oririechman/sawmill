package io.logz.sawmill.conditions;

import com.google.common.collect.ImmutableMap;
import io.logz.sawmill.Doc;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.logz.sawmill.utils.DocUtils.createDoc;
import static org.assertj.core.api.Assertions.assertThat;

public class FieldHasValueConditionTest {

    @Test
    public void testEmptyPossibleValues() {
        String field = "field1";
        List<Object> possibleValues = Collections.emptyList();
        FieldHasValueCondition fieldHasValueCondition = new FieldHasValueCondition(field, possibleValues);

        Doc doc = createDoc("field1", "value1");
        assertThat(fieldHasValueCondition.evaluate(doc)).isFalse();
    }

    @Test
    public void testFieldNotExists() {
        String field = "field1";
        List<Object> possibleValues = Arrays.asList("value1");
        FieldHasValueCondition fieldHasValueCondition = new FieldHasValueCondition(field, possibleValues);

        Doc doc = createDoc("field2", "value2");
        assertThat(fieldHasValueCondition.evaluate(doc)).isFalse();
    }

    @Test
    public void testFieldHasValue() {
        String field = "field1";
        String stringValue = "value1";
        int intValue = 1;
        long longValue = 2l;
        double doubleValue = 3.5d;
        boolean boolValue = true;
        List<String> listValue = Arrays.asList("some", "list");
        ImmutableMap<String, String> mapValue = ImmutableMap.of("some", "map");
        List<Object> possibleValues = Arrays.asList(stringValue, intValue, longValue, doubleValue, boolValue, listValue, mapValue);
        FieldHasValueCondition fieldHasValueCondition = new FieldHasValueCondition(field, possibleValues);

        Doc doc = createDoc("field1", stringValue);
        assertThat(fieldHasValueCondition.evaluate(doc)).isTrue();

        doc = createDoc("field1", intValue);
        assertThat(fieldHasValueCondition.evaluate(doc)).isTrue();

        doc = createDoc("field1", longValue);
        assertThat(fieldHasValueCondition.evaluate(doc)).isTrue();

        doc = createDoc("field1", doubleValue);
        assertThat(fieldHasValueCondition.evaluate(doc)).isTrue();

        doc = createDoc("field1", boolValue);
        assertThat(fieldHasValueCondition.evaluate(doc)).isTrue();

        doc = createDoc("field1", listValue);
        assertThat(fieldHasValueCondition.evaluate(doc)).isTrue();

        doc = createDoc("field1", mapValue);
        assertThat(fieldHasValueCondition.evaluate(doc)).isTrue();
    }
}