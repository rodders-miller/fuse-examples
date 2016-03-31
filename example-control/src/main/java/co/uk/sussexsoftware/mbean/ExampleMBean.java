package co.uk.sussexsoftware.mbean;

import co.uk.sussexsoftware.Worker;
import javax.management.NotCompliantMBeanException;
import javax.management.StandardMBean;


public class ExampleMBean extends StandardMBean implements ExampleMBeanInterface {
    private final Worker worker;
    
    /**
     * <p>Make a DynamicMBean out of the object
     * <var>implementation</var>, using the specified
     * <var>mbeanInterface</var> class.</p>
     *
     * @param implementation The implementation of this MBean.
     * @param mbeanInterface The Management Interface exported by this
     *                       MBean's implementation. If <code>null</code>, then this
     *                       object will use standard JMX design pattern to determine
     *                       the management interface associated with the given
     *                       implementation.
     * @throws IllegalArgumentException if the given
     *                                            <var>implementation</var> is null.
     * @throws NotCompliantMBeanException         if the <var>mbeanInterface</var>
     *                                            does not follow JMX design patterns for Management Interfaces, or
     *                                            if the given <var>implementation</var> does not implement the
     *                                            specified interface.
     */
    public ExampleMBean(Worker worker) throws NotCompliantMBeanException {
        /* Using the StandardMBean as a parrent we must tell it which of the interfaces represents the Mbean */
        super(ExampleMBeanInterface.class);
        
        this.worker = worker;
    }

    @Override
    public String exampleOperation(int arg) {
        return worker.doWork(arg);
    }

    public String getValue() {
        return worker.getValue();
    }

    public void setValue(String Value) {
        worker.setValue(Value);
    }
}
