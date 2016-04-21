/*
 * Copyright 2016 Sussex Software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and

 */
package co.uk.sussexsoftware.command;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.felix.gogo.commands.*;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.apache.log4j.Logger;

/**
 *
 * Example Operation that exposes a command to the Karaf Console
 *
 * This command delegates to a worker that is executes the command
 *
 * @author rodm
 */
@Command(scope = "buffering", name = "size-check", description = "checks the size of a buffer")
public class SizeCheckOperation extends OsgiCommandSupport {

    private static final Logger LOGGER = Logger.getLogger(SizeCheckOperation.class);

    private final ActiveMQConnectionFactory connectionFactory;

    /**
     * The Argument is passed by the framework before calling doExecute
     */
    @Argument(index = 0, name = "argument",
            description = "The destination name to check",
            required = true, multiValued = false)
    String destination;
    // private final Worker worker;

    /**
     *
     * @param connectionFactory
     */
    public SizeCheckOperation(ActiveMQConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    /**
     *
     * The entry point for the command to be executed from the Karaf CLI
     *
     * The argument is passed to the call by the framework using the @Argument
     * annotation
     *
     * @return
     * @throws Exception
     */
    @Override
    protected Object doExecute() throws Exception {

        long startTime = System.nanoTime();
        retriveDetails();
        long endTime = System.nanoTime();

        long duration = ((endTime - startTime) / 1000000);  //divide by 1000000 to get milliseconds.     

        System.out.println("execution took " + duration);

        return null;
    }
    /**
     *  Method utilises the amq's statistics plugin - the broker must have the <statistBrokerPlugin/> enabled to retrive the 
     *  correct values from the queues.  Stats are retrived by sending a message to ActiveMQ.Statistics.Destination and 
     *  the broker replys to the replyTo queue
     * 
     * @throws JMSException 
     */
    private void retriveDetails() throws JMSException {

        ActiveMQConnection connection = (ActiveMQConnection) connectionFactory.createConnection();
        connection.start();

        Session amqSession = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

        Queue replyTo = amqSession.createTemporaryQueue();
        MessageConsumer consumer = amqSession.createConsumer(replyTo);

        MessageProducer producer = amqSession.createProducer(null);

        // Issue with Fuse 6.2 means there is no '.' between Desination and the destination name to be checked
        String queueName = "ActiveMQ.Statistics.Destination" + destination;
        Queue query = amqSession.createQueue(queueName);

        Message msg = amqSession.createMessage();
        msg.setJMSReplyTo(replyTo);
        producer.send(query, msg);
        MapMessage reply = (MapMessage) consumer.receive(1000);

        long size = reply.getLong("size");
        long messageSize = reply.getLong("averageMessageSize");
        long storeUseage = size * messageSize;

        System.out.println("Message Size " + messageSize);
        System.out.println("Size " + size);
        System.out.println("Store Usage " + storeUseage);

    }
}
