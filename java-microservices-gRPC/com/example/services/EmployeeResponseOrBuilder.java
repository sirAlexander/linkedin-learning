// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: employee.proto

package com.example.services;

public interface EmployeeResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:example.EmployeeResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * int64, float, double, bool
   * </pre>
   *
   * <code>int32 id = 1;</code>
   */
  int getId();

  /**
   * <code>string name = 2;</code>
   */
  java.lang.String getName();
  /**
   * <code>string name = 2;</code>
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>string designation = 3;</code>
   */
  java.lang.String getDesignation();
  /**
   * <code>string designation = 3;</code>
   */
  com.google.protobuf.ByteString
      getDesignationBytes();
}
