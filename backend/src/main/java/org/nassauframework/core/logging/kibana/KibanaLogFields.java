package org.nassauframework.core.logging.kibana;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Class that holds the extra fields used for Kibana logging.
 * <p>
 * Log lines for Kibana will contain all fields set, until the log fields are cleared by invoking {@link KibanaLogFields#clear()}.
 *
 * @author Jordi Jaspers
 */
public final class KibanaLogFields {

    /**
     * Private constructor.
     */
    private KibanaLogFields() {
        // Prevent instantiation
    }

    /**
     * Sets the Kibana log field {@code field} to the {@code value}.
     */
    public static KibanaLogField tag(final KibanaLogField field, final Enum<?> value) {
        return tag(field, value.toString());
    }

    /**
     * Sets the Kibana log field {@code field} to the {@code value}.
     */
    public static KibanaLogField tag(final KibanaLogField field, final int value) {
        return tag(field, Integer.toString(value));
    }

    /**
     * Sets the Kibana log field {@code field} to the {@code value}.
     */
    public static KibanaLogField tag(final KibanaLogField field, final String value) {
        if (isBlank(value)) {
            clear(field);
            return field;
        }
        MDC.put(field.getLogName(), value);
        return field;
    }

    /**
     * Sets the Kibana log field {@code field} to the {@code value}.
     */
    public static KibanaLogField tag(final KibanaLogField field, final Collection<String> values) {
        if (values == null || values.isEmpty()) {
            return tag(field, (String) null);
        }
        final String value = values.stream().map(string -> String.format("'%s'", string)).collect(Collectors.joining(", ", "[", "]"));
        return tag(field, value);
    }

    /**
     * Retrieves the value for the {@code field}. It will return {@code null} if no value is set.
     */
    public static String get(final KibanaLogField field) {
        return getOrDefault(field, null);
    }

    /**
     * Retrieves the value for the {@code field}. It will return {@code defaultValue} if no value is set.
     */
    public static String getOrDefault(final KibanaLogField field, final String defaultValue) {
        return StringUtils.defaultIfEmpty(MDC.get(field.getLogName()), defaultValue);
    }

    /**
     * Removes the value for the field {@code field}.
     */
    public static void clear(final KibanaLogField field) {
        MDC.remove(field.getLogName());
    }

    /**
     * Removes the value for the fields {@code fields}.
     */
    public static void clear(final KibanaLogField... fields) {
        for (final KibanaLogField field : fields) {
            MDC.remove(field.getLogName());
        }
    }

    /**
     * Removes all values set for all fields.
     */
    public static void clear() {
        MDC.clear();
    }

    /**
     * Getter for the log string.
     *
     * @return the log string
     */
    public static String getValuesAsLogString() {
        final StringBuilder result = new StringBuilder();
        MDC.getCopyOfContextMap().forEach((key, value) -> {
            if (!key.equals(KibanaLogFieldNames.LOG_TYPE.getLogName())) {
                result.append(' ');
                result.append(key);
                result.append("=\"");
                result.append(value);
                result.append('"');
            }
        });

        return result.toString();
    }
}
