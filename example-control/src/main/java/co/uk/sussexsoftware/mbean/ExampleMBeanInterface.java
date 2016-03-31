package co.uk.sussexsoftware.mbean;

/**
 *
 * Example Interface for an MBean that exposes both operations
 * and attributes to the MBean Server
 * 
 * @author rodm
 */
public interface ExampleMBeanInterface 
{

    /*  
        A Method that allows us to expose an MBean Operation
    */     
    String exampleOperation(int arg);
    
    /* Getter / Setter pair that allows us to expose an
       MBean Attribute of Value
    */ 
    String getValue();
    void setValue(String Value);
}
