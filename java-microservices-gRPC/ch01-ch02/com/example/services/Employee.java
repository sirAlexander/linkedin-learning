// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: employee.proto

package com.example.services;

public final class Employee {
  private Employee() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_example_EmployeeRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_example_EmployeeRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_example_EmployeeResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_example_EmployeeResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016employee.proto\022\007example\"\035\n\017EmployeeReq" +
      "uest\022\n\n\002id\030\001 \001(\005\"A\n\020EmployeeResponse\022\n\n\002" +
      "id\030\001 \001(\005\022\014\n\004name\030\002 \001(\t\022\023\n\013designation\030\003 " +
      "\001(\t2U\n\017EmployeeService\022B\n\013getEmployee\022\030." +
      "example.EmployeeRequest\032\031.example.Employ" +
      "eeResponseB\030\n\024com.example.servicesP\001b\006pr" +
      "oto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_example_EmployeeRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_example_EmployeeRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_example_EmployeeRequest_descriptor,
        new java.lang.String[] { "Id", });
    internal_static_example_EmployeeResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_example_EmployeeResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_example_EmployeeResponse_descriptor,
        new java.lang.String[] { "Id", "Name", "Designation", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}