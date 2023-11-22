package ut.ru.samokat.atlassian.jira.tutorials.sdkcondition;

import org.junit.Test;
import ru.samokat.atlassian.jira.tutorials.sdkcondition.api.MyPluginComponent;
import ru.samokat.atlassian.jira.tutorials.sdkcondition.impl.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}