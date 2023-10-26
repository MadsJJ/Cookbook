module user.springboot.rest {
    requires com.fasterxml.jackson.databind;

    requires spring.web;
    requires spring.beans;
    requires spring.boot;
    requires spring.context;
    requires spring.boot.autoconfigure;

    requires user.core;

    opens cookbook.springboot.restserver to spring.beans, spring.context, spring.web;
}
