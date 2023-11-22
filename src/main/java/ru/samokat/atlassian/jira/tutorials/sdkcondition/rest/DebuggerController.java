package ru.samokat.atlassian.jira.tutorials.sdkcondition.rest;

import lombok.extern.slf4j.Slf4j;
import ru.samokat.atlassian.jira.tutorials.sdkcondition.postprocessor.SdkCondition;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/debugger_controller_class")
@Slf4j
@SdkCondition
public class DebuggerController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("debugger_controller_method")
    public String sayHello() {
        log.debug("sayHello()");

        return "Hello Jira REST World";
    }

}

