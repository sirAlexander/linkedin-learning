# Protocol Buffer Compiler Installation

- Linux, using apt or apt-get, for example:

```
$ sudo apt install -y protobuf-compiler
$ protoc --version  # Ensure compiler version is 3+
```

### Use Java Protocol Buffers

To use protobuf in Java, use the protocol compiler (a.k.a. `protoc`) and use it to generate Java code for your `.proto` files:

```
protoc --java_out=. employee.proto
```
