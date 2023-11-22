package ru.samokat.atlassian.jira.tutorials.sdkcondition.postprocessor;

import com.atlassian.jira.component.ComponentAccessor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;

@Named
@Slf4j
public class SdkChecker {
    @Getter
    private final boolean isSdkEnvironment;
    public SdkChecker() {
        log.debug("SdkChecker()");
        String baseUrl = ComponentAccessor.getApplicationProperties().getJiraBaseUrl();
        log.trace("baseUrl is {}", baseUrl);
        isSdkEnvironment = baseUrl.equals("http://localhost:2990/jira");
        log.trace("plugin is running on SDK environment - {}", isSdkEnvironment);
    }
}

