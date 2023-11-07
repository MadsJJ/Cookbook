module user.springboot.rest {
 

    requires spring.web;
    requires spring.beans;
    requires spring.boot;
    requires spring.context;
    requires spring.boot.autoconfigure;
    requires com.google.gson;
    requires user.core;

    opens cookbook.springboot.restserver to spring.beans, spring.context, spring.web;
}
