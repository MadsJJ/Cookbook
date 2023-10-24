module user.core {
    requires transitive com.fasterxml.jackson.databind;
    exports cookbook.core;
    exports cookbook.json;
    requires com.google.gson;
    opens cookbook.core;
}
