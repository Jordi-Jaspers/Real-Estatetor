package org.nassauframework.core.logging.kibana;

import io.micronaut.aop.InterceptorBean;
import io.micronaut.aop.MethodInterceptor;
import io.micronaut.aop.MethodInvocationContext;
import jakarta.inject.Singleton;
import org.nassauframework.core.logging.model.TransactionId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.nassauframework.core.logging.kibana.KibanaLogFieldNames.TX_ID;

/**
 * This bean intercepts the {@link SupplyTxId} annotation and fetches the transaction id passed through the variables.
 * After receiving the transaction id, the {@link KibanaLogFieldNames} of 'TX_ID' is tagged by this value. This allows proper
 * logging tracing during a flow.
 *
 * @author Jordi Jaspers
 */
@Singleton
@InterceptorBean(SupplyTxId.class)
public class SupplyTxIdInterceptor implements MethodInterceptor<Object, Object> {

    /**
     * The class logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SupplyTxIdInterceptor.class);

    /**
     * {@inheritDoc}.
     */
    @Override
    public Object intercept(MethodInvocationContext<Object, Object> context) {
        try {
            KibanaLogFields.tag(TX_ID, TransactionId.get());
            return context.proceed();
        } finally {
            TransactionId.remove();
            KibanaLogFields.clear();
        }
    }
}
