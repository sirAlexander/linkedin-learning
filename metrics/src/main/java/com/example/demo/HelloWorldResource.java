package com.example.demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

@Path("/hello")
public class HelloWorldResource {

  @Timed(
    name = "HelloRequestTime", 
    absolute = true, 
    description = "Time taken to respond to hello world")
  @Counted(
    name = "HelloRequestCount", 
    absolute = true, 
    monotonic = true, 
    description = "Number of times hello is called")
  @GET
  public Response sayHello() {
    return Response.ok("Hello World").build();
  }

}