import static org.junit.Assert.*;
import org.junit.Test;

public class HelloworldTest {
    public Helloworld helloworld = new Helloworld();
    @Test
    public void testHello() {
        helloworld.hello();
        assertEquals("Hello World", helloworld.getStr());
    }
    @Test
    public void testHello2() {
        helloworld.hello();
        assertEquals("Hello World", helloworld.getStr());
    }
}


