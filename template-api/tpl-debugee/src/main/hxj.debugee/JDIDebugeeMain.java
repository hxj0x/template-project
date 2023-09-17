package hxj.debugee;

import com.sun.jdi.Bootstrap;
import com.sun.jdi.VirtualMachine;
import com.sun.jdi.VirtualMachineManager;
import com.sun.jdi.connect.AttachingConnector;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.event.Event;
import com.sun.jdi.event.EventSet;
import com.sun.jdi.event.MethodEntryEvent;
import com.sun.jdi.request.EventRequest;
import com.sun.jdi.request.MethodEntryRequest;
import com.sun.tools.jdi.SocketAttachingConnector;

import java.util.List;
import java.util.Map;

/**
 * @author huangxj
 * @since 2023/9/17
 */
public class JDIDebugeeMain {

    public static void main(String[] args) throws Exception {

        VirtualMachineManager vmm = Bootstrap.virtualMachineManager();
        List<AttachingConnector> connectors = vmm.attachingConnectors();
        SocketAttachingConnector sac = null;
        for (AttachingConnector ac : connectors) {
            if (ac instanceof SocketAttachingConnector) {
                sac = (SocketAttachingConnector) ac;
            }
        }
        if (sac == null) {
            throw new Exception("未找到SocketAttachingConnector连接器");
        }
        Map<String, Connector.Argument> arguments = sac.defaultArguments();
        arguments.get("hostname").setValue("localhost");
        arguments.get("port").setValue(String.valueOf(8011));
        VirtualMachine virtualMachine = sac.attach(arguments);

        MethodEntryRequest entryRequest = virtualMachine.eventRequestManager().createMethodEntryRequest();
        entryRequest.addClassFilter("org.springframework.validation.annotation*");
        // entryRequest.addThreadFilter(T);
        entryRequest.setSuspendPolicy(EventRequest.SUSPEND_EVENT_THREAD);
        entryRequest.enable();

        while (true) {
            EventSet eventSet = virtualMachine.eventQueue().remove();
            for (Event event : eventSet) {
                if (event instanceof MethodEntryEvent) {
                    MethodEntryEvent methodEntryEvent = (MethodEntryEvent) event;
                    String className = methodEntryEvent.location().declaringType().name();
                    if (className.startsWith("java") || className.startsWith("jdk") || className.startsWith("sun")) {
                        break;
                    }
                    System.out.println("methodEntryEvent.thread().name() = " + methodEntryEvent.thread().name());
                    System.out.println("className = " + className);
                    System.out.println("methodEntryEvent.method().name() = " + methodEntryEvent.method().name());
                    System.out.println("==========");
                }
            }
            eventSet.resume();
        }

    }
}
