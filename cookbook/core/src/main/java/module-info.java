module user.core {
    exports cookbook.core;
    requires com.google.gson;
    opens cookbook.core;
}