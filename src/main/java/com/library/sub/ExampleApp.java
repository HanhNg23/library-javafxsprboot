// ExampleApp.java - ExampleApp interface
package com.library.sub;
import java.net.URL;

public interface ExampleApp {
    default URL getViewLocation() {
        return getClass().getResource("primary.fxml");
    }
}
