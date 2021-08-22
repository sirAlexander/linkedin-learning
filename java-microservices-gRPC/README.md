# Protocol Buffer Compiler Installation

- Linux, using apt or apt-get, for example:

```
$ sudo apt install -y protobuf-compiler
$ protoc --version  # Ensure compiler version is 3+
```

### Download protoc Java generation plugin

Download the appropriate java generation grpc plugin from `mvnrepository`

https://mvnrepository.com/artifact/io.grpc/protoc-gen-grpc-java

### Use Java Protocol Buffers

To use protobuf in Java, use the protocol compiler (a.k.a. `protoc`) and use it to generate Java code for your `.proto` files:

```
protoc --plugin=protoc-gen-grpc-java=$HOME/tools/grpc/protoc-gen-grpc-java-1.40.0-linux-x86_64.exe --grpc-java_out=. employee.proto
```
