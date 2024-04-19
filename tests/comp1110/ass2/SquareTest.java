package comp1110.ass2;

import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class SquareTest {
    private Square test = new Square(3, 5, 'p');

    @BeforeAll
    public static void setup() throws InterruptedException {
        // Launch JavaFX application
        Thread t = new Thread("JavaFX Init Thread") {
            public void run() {
                Application.launch(TestApp.class, new String[0]);
            }
        };
        t.setDaemon(true);
        t.start();
        Thread.sleep(500);
    }

    @Test
    void testX() {
        assertEquals(test.getValueX(), 3);
    }

    @Test
    void testY() {
        assertEquals(test.getValueY(), 5);
    }

    @Test
    void testT() {
        assertEquals(test.getT(), Square.type.PURPLE);
    }

    public static class TestApp extends Application {
        @Override
        public void start(Stage primaryStage) {
        }
    }
}
