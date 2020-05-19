package com.pomanytskyi.payroll.socket;

import com.pomanytskyi.payroll.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import static com.pomanytskyi.payroll.socket.WebSocketConfiguration.MESSAGE_PREFIX;

@Component
@RepositoryEventHandler
public class EventHandler {
    private final SimpMessagingTemplate webSocket;
    private final EntityLinks entityLinks;

    @Autowired
    public EventHandler(SimpMessagingTemplate webSocket, EntityLinks entityLinks) {
        this.webSocket = webSocket;
        this.entityLinks = entityLinks;
    }

    @HandleAfterCreate
    public void newEmployee(Employee employee) {
        this.webSocket.convertAndSend(MESSAGE_PREFIX + "/newEmployee", getPath(employee));
    }

    @HandleAfterDelete
    public void deleteEmployee(Employee employee) {
        this.webSocket.convertAndSend(
                MESSAGE_PREFIX + "/deleteEmployee", getPath(employee));
    }

    @HandleAfterSave
    public void updateEmployee(Employee employee) {
        this.webSocket.convertAndSend(
                MESSAGE_PREFIX + "/updateEmployee", getPath(employee));
    }

    /**
     * Take an {@link Employee} and get the URI using Spring Data REST's {@link EntityLinks}.
     * @param employee
     */
    private String getPath(Employee employee) {
        return this.entityLinks.linkForItemResource(employee.getClass(), employee.getId()).toUri().getPath();
    }
}
