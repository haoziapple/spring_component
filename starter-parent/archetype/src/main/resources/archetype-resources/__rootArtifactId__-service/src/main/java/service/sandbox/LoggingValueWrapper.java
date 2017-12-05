#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.sandbox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 用于aop和mybatis打印参数和返回值，延迟执行
 */
public class LoggingValueWrapper extends Throwable {
    /**
     * 集合最大打印数量，0则全部打印
     */
    public static final int MAX_COLLECTION_LOGGING_SIZE = 2;
    private Object val = null;

    private static Logger logger = LoggerFactory.getLogger(LogConstants.LOGGER_DEBUG);

    public LoggingValueWrapper(Object val) {
        super();
        this.val = val;
    }

    private boolean isOverLoggingSize(int size) {
        return MAX_COLLECTION_LOGGING_SIZE > 0 && size > MAX_COLLECTION_LOGGING_SIZE;
    }

    public String toString() {
        if (logger.isDebugEnabled()) {
            try {
                return JsonUtil.getJsonStrByObject(val);
            } catch (IOException e) {

            }
        }
        if (val == null) {
            return "<null>";
        }
        if (val.getClass().isArray()) {
            Object[] array = (Object[]) val;
            if (!isOverLoggingSize(array.length)) {
                return Arrays.toString(array);
            }
            StringBuffer buffer = new StringBuffer();
            buffer.append("[");
            int count = 0;
            for (Object object : array) {
                count++;
                if (isOverLoggingSize(count)) {
                    break;
                }
                buffer.append(object).append(", ");
            }
            buffer.append("...](").append(array.length).append(")");
            return buffer.toString();
        } else if (List.class.isAssignableFrom(val.getClass())) {
            Collection<?> col = (Collection<?>) val;
            if (!isOverLoggingSize(col.size())) {
                return val.toString();
            }
            StringBuffer buffer = new StringBuffer();
            buffer.append("[");
            int count = 0;
            for (Object object : col) {
                count++;
                if (isOverLoggingSize(count)) {
                    break;
                }
                buffer.append(object).append(", ");
            }
            buffer.append("...](").append(col.size()).append(")");
            return buffer.toString();
        } else if (val instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) val;
            if (!isOverLoggingSize(map.size())) {
                return val.toString();
            }
            StringBuffer buffer = new StringBuffer();
            buffer.append("{");
            int count = 0;
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                count++;
                if (isOverLoggingSize(count)) {
                    break;
                }
                buffer.append(entry.getKey()).append(": ").append(entry.getValue()).append(", ");
            }
            buffer.append("...}(").append(map.size()).append(")");
            return buffer.toString();
        }
        return val.toString();
    }
}
