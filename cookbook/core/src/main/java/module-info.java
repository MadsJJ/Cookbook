module user.core {
    requires transitive com.fasterxml.jackson.databind;
    exports cookbook.core;
    requires com.google.gson;
    opens cookbook.core;
}