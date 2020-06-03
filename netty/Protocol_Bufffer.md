* [Overview](#overview)
  * [protocol buffer 是什么](#protocol-buffer%E6%98%AF%E4%BB%80%E4%B9%88)
  * [为什么不用XML](#%E4%B8%BA%E4%BB%80%E4%B9%88%E4%B8%8D%E7%94%A8xml)
* [Language Guide(proto3)](#language-guideproto3)
  * [定义一个消息类型](#%E5%AE%9A%E4%B9%89%E4%B8%80%E4%B8%AA%E6%B6%88%E6%81%AF%E7%B1%BB%E5%9E%8B)
    * [指定字段类型](#%E6%8C%87%E5%AE%9A%E5%AD%97%E6%AE%B5%E7%B1%BB%E5%9E%8B)
    * [赋值字段数字](#%E8%B5%8B%E5%80%BC%E5%AD%97%E6%AE%B5%E6%95%B0%E5%AD%97)
    * [指定字段规则](#%E6%8C%87%E5%AE%9A%E5%AD%97%E6%AE%B5%E8%A7%84%E5%88%99)
    * [添加更多的消息类型](#%E6%B7%BB%E5%8A%A0%E6%9B%B4%E5%A4%9A%E7%9A%84%E6%B6%88%E6%81%AF%E7%B1%BB%E5%9E%8B)
    * [添加注释](#%E6%B7%BB%E5%8A%A0%E6%B3%A8%E9%87%8A)
    * [保留字段](#%E4%BF%9D%E7%95%99%E5%AD%97%E6%AE%B5)
    * [你的\.proto生成了什么](#%E4%BD%A0%E7%9A%84proto%E7%94%9F%E6%88%90%E4%BA%86%E4%BB%80%E4%B9%88)
  * [标量值类型](#%E6%A0%87%E9%87%8F%E5%80%BC%E7%B1%BB%E5%9E%8B)
  * [默认值](#%E9%BB%98%E8%AE%A4%E5%80%BC)
  * [枚举](#%E6%9E%9A%E4%B8%BE)
    * [保留值](#%E4%BF%9D%E7%95%99%E5%80%BC)
  * [使用其他的消息类型](#%E4%BD%BF%E7%94%A8%E5%85%B6%E4%BB%96%E7%9A%84%E6%B6%88%E6%81%AF%E7%B1%BB%E5%9E%8B)
    * [导入定义](#%E5%AF%BC%E5%85%A5%E5%AE%9A%E4%B9%89)
    * [使用proto2的消息类型](#%E4%BD%BF%E7%94%A8proto2%E7%9A%84%E6%B6%88%E6%81%AF%E7%B1%BB%E5%9E%8B)
  * [嵌套类型](#%E5%B5%8C%E5%A5%97%E7%B1%BB%E5%9E%8B)
  * [更新消息类型](#%E6%9B%B4%E6%96%B0%E6%B6%88%E6%81%AF%E7%B1%BB%E5%9E%8B)
  * [未知字段](#%E6%9C%AA%E7%9F%A5%E5%AD%97%E6%AE%B5)
  * [Any 类型](#any-%E7%B1%BB%E5%9E%8B)
  * [oneof](#oneof)
    * [使用oneof](#%E4%BD%BF%E7%94%A8oneof)
    * [oneof 的特性](#oneof-%E7%9A%84%E7%89%B9%E6%80%A7)
    * [向后兼容性问题](#%E5%90%91%E5%90%8E%E5%85%BC%E5%AE%B9%E6%80%A7%E9%97%AE%E9%A2%98)
  * [Maps](#maps)
    * [向后兼容性](#%E5%90%91%E5%90%8E%E5%85%BC%E5%AE%B9%E6%80%A7)
  * [Packages](#packages)
    * [包和名称解析](#%E5%8C%85%E5%92%8C%E5%90%8D%E7%A7%B0%E8%A7%A3%E6%9E%90)
  * [定义Services](#%E5%AE%9A%E4%B9%89services)
  * [JSON Mapping](#json-mapping)
    * [JSON选项](#json%E9%80%89%E9%A1%B9)
  * [Options](#options)
    * [自定义 Option](#%E8%87%AA%E5%AE%9A%E4%B9%89-option)
  * [自动生成你的类文件](#%E8%87%AA%E5%8A%A8%E7%94%9F%E6%88%90%E4%BD%A0%E7%9A%84%E7%B1%BB%E6%96%87%E4%BB%B6)
* [Style Guide](#style-guide)
  * [标准文件格式](#%E6%A0%87%E5%87%86%E6%96%87%E4%BB%B6%E6%A0%BC%E5%BC%8F)
  * [文件结构](#%E6%96%87%E4%BB%B6%E7%BB%93%E6%9E%84)
  * [package](#package)
  * [message 和 字段名](#message-%E5%92%8C-%E5%AD%97%E6%AE%B5%E5%90%8D)
  * [重复字段](#%E9%87%8D%E5%A4%8D%E5%AD%97%E6%AE%B5)
  * [枚举](#%E6%9E%9A%E4%B8%BE-1)
  * [Services](#services)
* [Basics: Java](#basics-java)
  * [为什么用Protocol Buffer](#%E4%B8%BA%E4%BB%80%E4%B9%88%E7%94%A8protocol-buffer)
  * [定义protocol 格式](#%E5%AE%9A%E4%B9%89protocol-%E6%A0%BC%E5%BC%8F)
  * [编译Protocol Buffer](#%E7%BC%96%E8%AF%91protocol-buffer)
  * [Protocol Buffer API](#protocol-buffer-api)
    * [枚举和嵌套类](#%E6%9E%9A%E4%B8%BE%E5%92%8C%E5%B5%8C%E5%A5%97%E7%B1%BB)
    * [Builders vs\. Messages](#builders-vs-messages)
    * [Standard Message Methods](#standard-message-methods)
    * [Parsing and Serialization](#parsing-and-serialization)
  * [Writing A Message](#writing-a-message)
  * [Reading A Message](#reading-a-message)
  * [Extending a Protocol Buffer](#extending-a-protocol-buffer)

## Overview

### protocol buffer 是什么

- `protocol buffer`是一种灵活、高效、自动化的序列化结构化数据的机制，就像XML，但它更小、更快、更简单。一旦定义了数据的结构化方式，就可以使用特殊生成的源代码轻松地编写和读取结构化数据，并使用各种语言在各种数据流之间来回切换。您甚至可以更新数据结构，而不会破坏根据“旧”格式编译的已部署程序。

### 为什么不用XML

- `protocol buffer`在序列化结构化数据方面比`XML`有很多优点。协议缓冲区:
  - 更简单
  - 是3到10倍小
  - 速度是20到100倍
  - 不模糊
  - 生成更容易以编程方式使用的数据访问类

## Language Guide(proto3)

### 定义一个消息类型

- 首先，让我们看一个简单的实例，假设你要定一个搜索请求消息格式，每一个搜索请求有一个查询字符串、您所感兴趣的结果的特定页面、每个页面的多个结果，你可以使用这个`.proto`文件来定义消息类型

  ```protobuf
  syntax = "proto3";
  
  message SearchRequest {
    string query = 1;
    int32 page_number = 2;
    int32 result_per_page = 3;
  }
  ```

  - 文件第一行表示你指定用`proto3`语法，如果你不这么做，`protocol buffer`编译器会认为你用`proto2`，这必须在文件的第一行，且没有空格，没有注释。
  - `SearchRequest`消息定义指定了三个字段（`name/value`对），其中一个字段用于希望包含在这种类型的消息中的每段数据。每个字段都有一个`name`和`type`

#### 指定字段类型
- 在上述例子中，所有的字段都是标量类型：两个整数（`page_number`和`result_per_page`）和一个`string`（`query`），你也可以给字段指定复合类型，比如枚举和其他的消息类型

#### 赋值字段数字

- 每个字段在消息中定义了一个唯一的数字，这个字段数字用来在 [message binary format](https://developers.google.com/protocol-buffers/docs/encoding) 中标识字段，当消息类型被使用后，就不应该再改变。这个字段数字在`1～15`范围时用一个字节来编码，包含字段数字和字段类型。字段数组在`16~2047`时消耗2个字节，因此，你应该为非常频繁出现的消息元素保留数字`1～15`，记住要为将来可能添加的频繁出现的元素留出一些空间。
- 你可以指定的最小字段数字是1，最大是`2^29 - 1`，你也不能使用`19000~19999`之间的数字（`FieldDescriptor::kFirstReservedNumber` through `FieldDescriptor::kLastReservedNumber`），因为它们是为`Protocol Buffer`实现保留的，如果您在`.proto`中使用这些保留的数字之一，`protocol buffer compiler`就会发出警告,类似地，你不能使用任何以前保留的字段数字。

#### 指定字段规则

- 消息字段可以为以下之一：
  - `singular`：一个格式良好的消息可以有零个或一个字段(但不能多于一个)。这是proto3语法的默认字段规则。
  - `repeated`：在格式良好的消息中，此字段可以重复任意次数(包括0次)。重复值的顺序将被保留。

- 在`proto3`中，标量数字类型的重复字段在默认情况下使用`packed`编码。

#### 添加更多的消息类型

- 一个`.proto`文件中可以定义多个消息类型，这在定义多个相关消息时非常有用——例如，如果您想定义与您的`SearchResponse`消息类型相对应的应答消息格式，您可以将其添加到相同的`.proto`:

  ```protobuf
  message SearchRequest {
    string query = 1;
    int32 page_number = 2;
    int32 result_per_page = 3;
  }
  
  message SearchResponse {
   ...
  }
  ```

#### 添加注释

- 要在`.proto`文件中添加注释，请使用`C/C++`样式的`//`和`/*…* /`语法。

  ```protobuf
  /* SearchRequest represents a search query, with pagination options to
   * indicate which results to include in the response. */
  
  message SearchRequest {
    string query = 1;
    int32 page_number = 2;  // Which page number do we want?
    int32 result_per_page = 3;  // Number of results to return per page.
  }
  ```

####   保留字段

- 如果通过完全删除字段或将其注释掉来更新消息类型，未来的用户在对类型进行更新时可以重用字段号。如果以后加载相同`.proto`的旧版本，可能会导致严重的问题，包括数据损坏、隐私漏洞等等。确保不发生这种情况的一种方法是指定保留已删除字段的字段号(`and/or names`，这也可能导致`JSON`序列化问题)。如果将来有用户试图使用这些字段标识符，`protocol buffer compiler`将发出警告。

  ```protobuf
  message Foo {
    reserved 2, 15, 9 to 11;
    reserved "foo", "bar";
  }
  ```

  - 注意，不能在同一个保留语句中混合字段名和字段号。

#### 你的`.proto`生成了什么

- 当你在 `.proto`上运行`protocol buffer compiler`时， `compiler`会用你选择的语言生成代码，你需要使用文件中描述的消息类型，包括获取和设置字段值、将消息序列化到输出流以及解析来自输入流的消息。

  - 对于`Java`，编译器生成一个`. Java`文件，其中包含每个消息类型的类，以及用于创建消息类实例的特殊构建器类。

### 标量值类型

- 标量消息字段可以有以下一种类型——表中显示了.proto文件中指定的类型，以及自动生成的类中相应的类型:

| `.proto`类型 | `java`类型   | 备注                                                         |
| ------------ | ------------ | ------------------------------------------------------------ |
| `double`     | `double`     |                                                              |
| `float`      | `float`      |                                                              |
| `int32`      | `int`        | 使用变长编码。编码负数的效率很低——如果字段可能有负值，那么使用`sint32`。 |
| `int64`      | `long`       | 使用变长编码。编码负数的效率很低——如果字段可能有负值，那么使用`sint64`。 |
| `uint32`     | `int`        | 使用变长编码，在Java中，无符号32位和64位整数使用它们的有符号对应项来表示，最上面的位只是存储在符号位中。 |
| `uint64`     | `long`       | 使用变长编码，在Java中，无符号32位和64位整数使用它们的有符号对应项来表示，最上面的位只是存储在符号位中。 |
| `sint32`     | `int`        | 使用变长编码。它们比普通的`int32`更有效地编码负数。          |
| `sint64`     | `long`       | 使用变长编码。它们比普通的`int64`更有效地编码负数。          |
| `fixed32`    | `int`        | 总是四个字节。如果值经常大于`2^28`，则比`uint32`更有效。无符号32位和64位整数使用它们的有符号对应项来表示，最上面的位只是存储在符号位中。 |
| `fixed64`    | `long`       | 总是八个字节。如果值经常大于`2^56`，则比`uint64`更有效。无符号32位和64位整数使用它们的有符号对应项来表示，最上面的位只是存储在符号位中。 |
| `sfixed32`   | `int`        | 总是四个字节。                                               |
| `sfixed64`   | `long`       | 总是八个字节。                                               |
| `bool`       | `boolean`    |                                                              |
| `string`     | `String`     | 符串必须始终包含`UTF-8`编码或`7`位`ASCII`文本，且不能超过`2^32`。 |
| `bytes`      | `ByteString` | 可以包含任何长度不超过`2^32`的字节序列                       |

### 默认值

- 当解析消息时，如果编码的消息不包含特定的奇异元素，则解析对象中的相应字段被设置为该字段的默认值。这些默认值是特定于类型的:
  - 对于字符串，默认值是空字符串。
  - 对于字节，默认值是空字节。
  - 对于`bool`，默认值为`false`。
  - 对于数字类型，默认值为零。
  - 对于枚举，默认值是第一个定义的枚举值，它必须是0。
  - 对于消息字段，未设置该字段。其确切值依赖于语言
- 重复字段的默认值为空(通常是适当语言中的空列表)。
- 注意,对于标量消息字段,在消息解析后，没有办法判断字段是是被设置为默认值还是没有设置值（例如一个布尔值是否设置为`false`）,你应该牢记这一点在定义你的消息类型。例如，如果不希望默认情况下也发生这种行为，那么不要使用布尔值来在设置为false时切换某些行为。还请注意，如果将标量消息字段设置为其默认值，`the value will not be serialized on the wire`。

### 枚举

- 在定义消息类型时，可能希望其中一个字段只具有预定义的值列表中的一个。例如，假设您想为每个`SearchRequest`添加一个`corpus`字段，其中`corpus`可以是 `UNIVERSAL`, `WEB`, `IMAGES`, `LOCAL`, `NEWS`, `PRODUCTS` or `VIDEO`。您可以通过向消息定义添加一个`enum`，并为每个可能的值添加一个常量，从而非常简单地做到这一点。

- 在下面的例子中，我们添加了一个名为`Corpus`的枚举，它包含所有可能的值，以及一个`Corpus`类型的字段:

  ```protobuf
  message SearchRequest {
    string query = 1;
    int32 page_number = 2;
    int32 result_per_page = 3;
    enum Corpus {
      UNIVERSAL = 0;
      WEB = 1;
      IMAGES = 2;
      LOCAL = 3;
      NEWS = 4;
      PRODUCTS = 5;
      VIDEO = 6;
    }
    Corpus corpus = 4;
  }
  ```

- 如你所见，`Corpus enum`的第一个常量映射到零:每个`enum`定义必须包含一个常量，该常量作为其第一个元素映射到零。这是因为:

  - 必须有一个零值，以便我们可以使用0作为数字默认值。
  - 为了与proto2语义兼容，zero值需要作为第一个元素，在proto2语义中，第一个enum值总是默认值。

- 可以通过将相同的值分配给不同的枚举常量来定义别名。要做到这一点，您需要将`allow_alias`选项设置为`true`，否则当发现别名时，`protocol compiler`将生成一条错误消息。

  ```protobuf
  message MyMessage1 {
    enum EnumAllowingAlias {
      option allow_alias = true;
      UNKNOWN = 0;
      STARTED = 1;
      RUNNING = 1;
    }
  }
  message MyMessage2 {
    enum EnumNotAllowingAlias {
      UNKNOWN = 0;
      STARTED = 1;
      // RUNNING = 1;  // Uncommenting this line will cause a compile error inside Google and a warning message outside.
    }
  }
  ```

- 枚举数常数必须在`32`位整数的范围内。因为`enum`值在连线上使用`varint`编码，所以不推荐使用负值。您可以在消息定义内定义枚举，如上面的示例所示，也可以在消息定义外定义枚举——这些枚举可以在`.proto`文件中的任何消息定义中重用。您还可以使用在一个消息中声明的枚举类型作为另一个消息中的字段类型，使用`MessageType.EnumType`语法。

#### 保留值

- 如果通过完全删除`enum`条目或将其注释掉来更新`enum`类型，未来的用户在对该类型进行更新时可以重用该数值。如果以后加载相同`.proto`的旧版本，这可能会导致严重的问题，包括数据损坏、隐私`bug`等等。确保不发生这种情况的一种方法是指定保留已删除条目的数值(和/或名称，这也可能导致`JSON`序列化问题)。如果将来有用户试图使用这些标识符，协议缓冲区编译器将发出警告。可以使用`max`关键字指定保留的数值范围达到可能的最大值。

  ```protobuf
  enum Foo {
    reserved 2, 15, 9 to 11, 40 to max;
    reserved "FOO", "BAR";
  }
  ```

  - 注意，不能在同一个保留语句中混合字段名和字段号。

### 使用其他的消息类型

- 您可以使用其他消息类型作为字段类型。例如，假设您想在每个`SearchResponse`消息中包含结果消息—要做到这一点，您可以在相同的`.proto`中定义一个结果消息类型，然后在`SearchResponse`中指定一个`Result`类型的字段:

  ```protobuf
  message SearchResponse {
    repeated Result results = 1;
  }
  
  message Result {
    string url = 1;
    string title = 2;
    repeated string snippets = 3;
  }
  ```

#### 导入定义

- 在上面的例子中，结果消息类型定义在与`SearchResponse`相同的文件中——如果您要用作字段类型的消息类型已经在另一个`.proto`文件中定义了呢?

- 您可以通过导入其他`.proto`文件来使用它们的定义。要导入另一个`.proto`的定义，需要在文件的顶部添加一个`import`语句:

  ```protobuf
  import "myproject/other_protos.proto";
  ```

- 默认情况下，只能使用直接导入的`.proto`文件中的定义。但是，有时可能需要将.proto文件移动到新位置。为了避免因为一个地方的改动而修改所有其他地方的调用，可以不直接移动`.proto`文件，你可以在旧位置放一个哑`.proto`文件，使用`import public`将所有导入转发到新位置。任何导入包含`import public`语句的声明都可以传递地依赖`import public`依赖项。例如:

  ```protobuf
  // new.proto
  // All definitions are moved here
  ```

  ```protobuf
  // old.proto
  // This is the proto that all clients are importing.
  import public "new.proto";
  import "other.proto";
  ```

  ```protobuf
  // client.proto
  import "old.proto";
  // You use definitions from old.proto and new.proto, but not other.proto
  ```

- `protocol compiler`使用在`protocol compiler`命令行中通过`-I`/`--proto_path`标志指定的一组目录中搜索导入的文件。如果没有给出标志，它会在调用`compiler`的目录中查找。通常，应该将`--proto_path`标志设置为项目的根，并为所有导入使用完全限定的名称。

#### 使用`proto2`的消息类型

- 可以导入`proto2`消息类型并在`proto3`消息中使用它们，反之亦然。但是，`proto2`枚举不能直接在`proto3`语法中使用（如果导入`proto2`消息并通过`proto2`使用它们是可以的）。

### 嵌套类型

- 可以在其他消息类型中定义和使用消息类型，如下面的示例所示——这里的结果消息是在`SearchResponse`消息中定义的:

  ```protobuf
  message SearchResponse {
    message Result {
      string url = 1;
      string title = 2;
      repeated string snippets = 3;
    }
    repeated Result results = 1;
  }
  ```

- 果您想在父消息类型之外重用此消息类型，您可以将其引用为`Parent.Type`：

   ```protobuf
  message SomeOtherMessage {
    SearchResponse.Result result = 1;
  }
  ```

- 你可以把消息嵌入到你喜欢的深度:

  ```protobuf
  message Outer {                  // Level 0
    message MiddleAA {  // Level 1
      message Inner {   // Level 2
        int64 ival = 1;
        bool  booly = 2;
      }
    }
    message MiddleBB {  // Level 1
      message Inner {   // Level 2
        int32 ival = 1;
        bool  booly = 2;
      }
    }
  }
  ```

### 更新消息类型

- 如果现有的消息类型不再满足您的所有需求，例如，您希望消息格式有一个额外的字段，但是您仍然希望使用用旧格式创建的代码，不要担心！在不破坏任何现有代码的情况下更新消息类型非常简单。只要记住以下规则:
  - 不要更改任何现有字段的字段数字。‘
  - 如果添加新字段，使用“旧”消息格式的代码序列化的任何消息仍然可以由新生成的代码解析。您应该记住这些元素的默认值，以便新代码可以正确地与旧代码生成的消息进行交互。类似地，由新代码创建的消息可以由旧代码解析:旧的二进制文件在解析时简单地忽略新字段。有关详细信息，请参阅[Unknown Fields](https://developers.google.com/protocol-buffers/docs/proto3#unknowns)部分。
  - 可以删除字段，只要在更新的消息类型中不再使用字段号。您可能希望重命名字段，或者添加前缀`ete_`，或者保留字段号，以便`.proto`的未来用户不会意外地重用该编号。
  - `int32`、`uint32`、`int64`、`uint64`和`bool`都是兼容的，这意味着您可以将一个字段从其中一种类型更改为另一种类型，而不会中断向前或向后兼容性。如果解析一个不适合相应类型的数字，那么您将获得与在`c++`中将该数字强制转换为该类型相同的效果(例如，如果将`64`位数字读取为`int32`，那么它将被截断为`32`位)。
  - `sint32`和`sint64`相互兼容，但与其他整数类型不兼容。
  - `string`和`bytes`是兼容的，只要字节是有效的`UTF-8`。
  - 如果字节包含消息的编码版本，则`embedded message`与`bytes`兼容。
  - `fixed32`与`sfixed32`兼容，`fixed64`与`sfixed64`兼容。
  - 对于`string`、`bytes`和消息字段，`optional`与`repeat`兼容。给定一个重复字段的序列化数据作为输入，客户端希望这个字段是可选的,并且将会接受最后一个输入值(如果它是一个基本类型字段)，或者合并所有输入元素(如果它是一个消息类型字段)。注意，这对于数值类型(包括`bools`和`enum`)通常是不安全的。数值类型的重复字段可以以打包格式序列化，当需要`optional`字段时，将无法正确解析该格式。
  - `enum`在连线格式方面与`int32`、`uint32`、`int64`和`uint64`兼容(注意，如果值不合适，将被截断)。但是要注意，当消息被反序列化时，客户端代码可能会以不同的方式对待它们:例如，消息中会保留无法识别的`proto3 enum`类型，但是当消息被反序列化时，它是如何表示的则取决于语言。`Int`字段总是保持它们的值。
  - 将单个值改到新的`oneof`中是安全且二进制兼容的。如果您确定没有代码一次设置一个以上的字段，那么将多个字段移动到一个新的`oneof`可能是安全的。将任何字段移动到现有`oneof`中都是不安全的。

### 未知字段

- 未知字段是格式良好的`protocol buffe`序列化数据中解析器不能识别的字段。例如，当旧的二进制文件解析带有新字段的新二进制文件发送的数据时，这些新字段将成为旧二进制文件中的未知字段。
- 起初，在解析过程中，`proto3`消息总是丢弃未知字段，但是在`3.5`版本中，我们重新引入了保存未知字段的方法，以匹配`proto2`的行为。在版本`3.5`或更高版本中，解析过程中保留未知字段，并将其包含在序列化输出中。

### Any 类型

- `Any`消息类型允许将消息作为`embedded type`使用，而不需要定义`.proto`。`Any`包含作为`bytes`的任意序列化消息，以及充当全局惟一标识符并解析该消息的类型的`URL`。要使用`Any`类型，需要`import "google/protobuf/any.proto";`

  ```protobuf
  import "google/protobuf/any.proto";
  
  message ErrorStatus {
    string message = 1;
    repeated google.protobuf.Any details = 2;
  }
  ```

- 给定消息类型的默认类型`URL`是`type.googleapis.com/packagename.messagename`。

- 不同的语言实现将支持运行时库助手以类型安全的方式打包和解包`Any`值——例如，在`Java`中，`Any`类型将有特殊的`pack()`和`unpack()`访问器，而在`c++`中有`PackFrom()`和`UnpackTo()`方法.

- 目前，用于处理`Any`类型的运行时库在开发中。

### oneof

- 如果消息中有许多字段，同时最多只能设置一个字段，那么可以使用`oneof`特性强制执行此行为并节省内存。
- 除了在同一个`oneof`中的共享内存字段之外，`oneof`字段与常规字段类似，最多只能同时设置一个字段。设置`oneof`的任何成员将自动清除所有其他成员。你可以使用特定的`case()` 或者 `WhichOneof()`方法检查`oneof`中那个字段被设置值了，这取决于你选择的语言。

#### 使用`oneof`

- 要在`.proto`中定义一个`oneof`，可以使用`oneof`关键字后跟一个`oneof`名称，在本例中为`test_oneof`:

  ```protobuf
  message SampleMessage {
    oneof test_oneof {
      string name = 4;
      SubMessage sub_message = 9;
    }
  }
  ```

- 然后将一个`oneof`字段添加到`oneof`定义中。您可以添加任何类型的字段，除了`map`字段和`repeated`字段。

- 在生成的代码中，其中`oneof`字段具有与常规字段相同的`getter`和`setter`方法。你还可以得到一个特殊的方法来检查设置了`oneof`中的哪个值(如果有的话)。你可以在相关的[API reference](https://developers.google.com/protocol-buffers/docs/reference/overview)中找到更多关于你选择的语言的`oneof`的`API`。

#### oneof 的特性

- 设置一个`oneof`字段将自动清空`oneof`的所有其他成员。因此，如果您设置了`oneof`字段中的一个，那么只有你设置的最后一个字段仍然具有值。

  ```protobuf
  SampleMessage message;
  message.set_name("name");
  CHECK(message.has_name());
  message.mutable_sub_message();   // Will clear name field.
  CHECK(!message.has_name());
  ```

- 如果解析器在网络上遇到同一`oneof`的多个成员，那么在解析的消息中只使用最后一个成员。

- `oneof`不能是 `repeated`

- 反射`API`适用于`oneof`的字段

- 如果您将一个字段设置为默认值(例如将`int32`的一个字段设置为`0`)，那么将设置该字段的“`case`”，并且该值将在线序列化。

#### 向后兼容性问题

- 添加或删除字段时要小心。如果检查`oneof`返回`None`/`NOT_SET`的值，这可能意味着`oneof`没有被设置，或者它已经被设置为`oneof`的另一个版本的字段。因为无法知道一个未知字段是否属于`oneof`中的字段，所以无法知道它们之间的区别。

- 标签重用问题
  - 将字段移入或移出`oneof`：在消息序列化和解析之后，您可能会丢失一些信息(一些字段将被清除)。但是，你可以安全地将单个字段移动到一个新的`oneof`中，如果知道只设置了一个字段，则可以移动多个字段。
  - 删除一个字段并将其添加回去：这可能会在消息序列化和解析后清除你当前设置的`oneof`字段。
  - 拆分或合并`oneof`：这与移动常规字段类似。

### Maps

- 如果你想创建一个`map`关联你数据的一部分，`protocol buffers`提供了一个方便快捷的语法:

  ```protobuf
  map<key_type, value_type> map_field = N;
  ```

- 其中`key_type`可以是任何整数类型或字符串类型（除了浮点类型和`bytes`之外的任何标量类型）。注意，`enum`不是有效的`key_type`。`value_type`可以是除另一个`map`之外的任何类型。

- 例如，如果您想创建一个项目映射，其中每个项目消息都与一个字符串键相关联，您可以这样定义它:

  ```protobuf
  map<string, Project> projects = 3;
  ```

  - `map`的字段不能是`repeated`
  - `map`的值的连线格式排序和映射迭代排序是未定义的，因此你不能认为`map`  以某种方式排好序。
  - 在为`.proto`生成文本格式时，`map`按键排序。`key`为数字时按数字顺序排序。
  - 当从线上进行解析或合并时，如果有重复的`key`，则使用最后看到的键。当从文本格式解析`map`时，如果存在重复`key`，解析可能会失败。
  - 如果您为`map`字段提供了一个`key`，但是没有提供`value`，那么字段序列化时的行为就是依赖于语言的。在`c++`、`Java`和`Python`中，类型的默认值会被序列化，而在其他语言中则是不序列化的。

- 生成的`map` 的`API`目前可用于所有受`proto3`支持的语言。您可以在相关的[API reference](https://developers.google.com/protocol-buffers/docs/reference/overview)中找到更多关于您所选语言的`map`的` API`信息。

#### 向后兼容性

- `map`语法如下，因此不支持`map`的`protocol buffers`实现仍然可以处理你的数据:

  ```protobuf
  message MapFieldEntry {
    key_type key = 1;
    value_type value = 2;
  }
  
  repeated MapFieldEntry map_field = N;
  ```

- 任何支持`map`的`protocol buffers`实现都必须生成和接受上述定义可以接受的数据。

### Packages

- 您可以向`.proto`文件中添加一个可选的`package`说明符，以防止协议消息类型之间的名称冲突。

  ```protobuf
  package foo.bar;
  message Open { ... }
  ```

- 然后，当定义消息类型的字段时，可以使用包说明符:

  ```protobuf
  message Foo {
    ...
    foo.bar.Open open = 1;
    ...
  }
  ```

- 包说明符影响生成代码的方式取决于您选择的语言:

  - 在`Java`中，包被用作`Java`包，除非在`.proto`文件中显式地提供了一个`option java_package`。

####   包和名称解析

- `protocol buffer`中的类型名称解析的工作方式类似于`c++`：首先搜索最内层的作用域，然后搜索下一个最内层的作用域，以此类推，每个包都被认为是其父包的“内部”。一个领先'.'（例如，`.foo.bar. baz`）表示从最外层的作用域开始。
- `protocol buffer compiler`通过解析导入的`.proto`文件解析所有类型名。每种语言的代码生成器都知道如何引用该语言中的每种类型，即使它具有不同的作用域规则。

### 定义Services

- 如果您想在`RPC`(远程过程调用)系统中使用消息类型，您可以在`.proto`文件中定义`RPC`服务接口，`protocol buffer compiler`将用你选择的语言生成服务接口代码和存根。因此，例如，如果您想定义一个`RPC`服务，它的方法接受您的`SearchRequest`并返回一个`SearchResponse`，您可以在`.proto`文件中定义它，如下所示:

  ```protobuf
  service SearchService {
    rpc Search (SearchRequest) returns (SearchResponse);
  }
  ```

- 与`protocol buffer`一起使用的最简单的`RPC`系统是[gRPC](https://grpc.io/)：在谷歌开发的与语言和平台无关的开放源码的`RPC`系统。`gRPC`与`protocol buffer`一起工作得特别好，它允许您使用一个特殊的`protocol buffer compiler`插件直接从`.proto`文件生成相关的`RPC`代码。

### JSON Mapping

- `Proto3`支持`JSON`格式的规范化编码，使系统之间更容易地共享数据。在下面的表中，将逐个类型地描述编码。

- 如果`json`编码的数据中缺少一个值，或者该值为`nul`l，那么在解析到`protocol buffer`时，它将被解释为适当的默认值。如果一个字段在`protocol buffer`中有默认值，那么在`json`编码的数据中将缺省省略该字段，以节省空间。实现可以提供选项来在`json`编码的输出中发出具有默认值的字段。

  | proto3                 | JSON          | JSON example                              | Notes                                                        |
  | :--------------------- | :------------ | :---------------------------------------- | :----------------------------------------------------------- |
  | message                | object        | `{"fooBar": v,"g": null,…}`               | 生成`JSON`对象。消息字段名被映射到`lowerCamelCase`并成为`JSON`对象键。如果指定了`json_name`字段选项，则将使用指定的值作为键。解析器既接受`lowerCamelCase`名称(或由`json_name`选项指定的名称)，也接受原始的`proto`字段名。`null`是所有字段类型的可接受值，并作为相应字段类型的默认值处理。 |
  | enum                   | string        | `"FOO_BAR"`                               | 使用`proto`中指定的枚举值的名称。解析器同时接受枚举名和整数值。 |
  | map<K,V>               | object        | `{"k": v, …}`                             | 所有的键都被转换成字符串。                                   |
  | repeated V             | array         | `[v, …]`                                  | `null`被接受为空列表`[]`。                                   |
  | bool                   | true, false   | `true, false`                             |                                                              |
  | string                 | string        | `"Hello World!"`                          |                                                              |
  | bytes                  | base64 string | `"YWJjMTIzIT8kKiYoKSctPUB+"`              | `JSON`值将是使用标准base64编码被编码为字符串的数据，         |
  | int32, fixed32, uint32 | number        | `1, -10, 0`                               | `JSON`值将是一个十进制数。可以接受数字或字符串。             |
  | int64, fixed64, uint64 | string        | `"1", "-10"`                              | `JSON`值将是一个十进制数。可以接受数字或字符串。             |
  | float, double          | number        | `1.1, -10.0, 0, "NaN", "Infinity"`        | `JSON`值将是一个数字或特殊字符串值`NaN`、`Infinity`和`-Infinity`中的一个。可以接受数字或字符串。指数符号也被接受。 |
  | Any                    | `object`      | `{"@type": "url", "f": v, … }`            | 如果`Any`包含一个具有特殊`JSON`映射的值，它将被转换如下:`{"@type": xxx， "value": yyy}`。否则，该值将被转换为`JSON`对象，并插入`@type`字段来指示实际的数据类型。 |
  | Timestamp              | string        | `"1972-01-01T10:00:20.021Z"`              | 使用`RFC 3339`，其中生成的输出总是`z`规范化的，并使用`0、3、6或9`位小数。除`Z`之外的偏移量也可以接受。 |
  | Duration               | string        | `"1.000340012s", "1s"`                    | 根据所需的精度，生成的输出总是包含`0、3、6或9`个小数，后面跟着后缀`s`。接受任何小数(也没有)，只要它们符合纳秒精度并且后缀`s`是必需的。 |
  | Struct                 | `object`      | `{ … }`                                   | 任何一个`JSON`对象。见`struct.proto`。                       |
  | Wrapper types          | various types | `2, "2", "foo", true, "true", null, 0, …` | 包装器在JSON中使用与包装的原生类型相同的表示形式，除此之外`null`在数据转换和传输期间允许保留。 |
  | FieldMask              | string        | `"f.fooBar,h"`                            | 见 `field_mask.proto`.                                       |
  | ListValue              | array         | `[foo, bar, …]`                           |                                                              |
  | Value                  | value         |                                           | Any JSON value                                               |
  | NullValue              | null          |                                           | JSON null                                                    |
  | Empty                  | object        | {}                                        | An empty JSON object                                         |

#### JSON选项

- 一个`proto3`的`JSON`实现可以提供如下选择：
  - 发出具有默认值的字段：默认情况下，在`proto3`的` JSON`输出中省略具有默认值的字段。实现可以提供一个选项来覆盖此行为，并使用其默认值输出字段。
- 忽略未知字段：默认情况下，`Proto3`的` JSON`解析器应该拒绝未知字段，但是可以提供一个选项来忽略解析中的未知字段。
- 使用`proto`字段名而不是`lowerCamelCase`名：默认情况下，`proto3`的` JSON`应该将字段名转换为`lowerCamelCase`并将其用作`JSON`名。实现可以提供一个选项来使用`proto字`段名作为`JSON`名。`Proto3 `的`JSON`解析器需要同时接受转换后的`lowerCamelCase`名和`proto`字段名。
- 将枚举值作为整数而不是字符串发出：在`JSON`输出中默认使用枚举值的名称。可以提供一个选项来使用`enum`值的数字值。

### Options

- 在`.proto`文件中的单个声明可以使用多个`option`进行标注。`option`不会改变声明的总体含义，但可能会影响在特定上下文中处理它的方式。可用选项的完整列表在`google/protobuf/descriptor.proto`中定义。

- 有些`option`是`file-level`选项，这意味着它们应该在`top-level`范围内编写，而不是在任何`message`、`enum`或`service definition`内编写。有些`option`是`message-level`选项，这意味着它们应该在`message definition`中编写。有些`option`是`field-level`选项，这意味着它们应该在`field definition`中编写。`option`还可以写入`enum type`、`enum value`、`oneof`字段、`service type`和`service method`中的一个，但是，目前还不存在任何有用的`option`。

- 以下是一些最常用的`option`：

  - `java_package` （`file option`）：希望用于生成的`Java`类的包。如果`.proto`文件中没有给出显式的`java_package`选项，那么默认情况下将使用`proto`包（在`.proto`文件中使用`package`关键字指定）。然而，`proto`包通常不能成为优秀的`Java`包，因为不希望`proto`包以反向域名开始。如果不生成`Java代`码，则此选项无效。

    ```protobuf
    option java_package = "com.example.foo";
    ```

  - `java_multiple_files` （`file option`）：使`top-level messages`、`enums`和`services`在包级别定义，而不是在以`.proto`文件命名的外部类中定义。

    ```protobuf
    option java_multiple_files = true;
    ```

  - `java_outer_classname`（`file option`）：要生成的最外层`Java`类的类名(因此是文件名)。如果在`.proto`文件中没有指定显式的`java_outer_classname`，则将通过将`.proto`文件名转换为大小写(如`foo_bar`变成`FooBar.java`)来构造类名。如果不生成`Java`代码，则此`option`无效。

    ```protobuf
    option java_outer_classname = "Ponycopter";
    ```

  - `optimize_for`（`file option`）：可以设置为`SPEED`、`CODE_SIZE`或`LITE_RUNTIME`。这将以以下方式影响`c++`和`Java`代码生成器(可能还有第三方生成器)：

    - `SPEED`(默认值)：`protocol buffer compiler`将生成用于序列化、解析和对消息类型执行其他常见操作的代码。这段代码经过了高度优化。

    - `CODE_SIZE`：`protocol buffer compiler`将生成最小的类，并依赖于共享的、基于反射的代码来实现序列化、解析和各种其他操作。因此，生成的代码将比`SPEED`小得多，但操作将更慢。类仍将实现与在`SPEED`模式下完全相同的公共`API`。这种模式在包含大量`.proto`文件的应用程序中最有用，而且不需要所有文件都快得让人眼花缭乱。

    - `LITE_RUNTIME`：`protocol buffer compiler`将生成仅依赖于`lite`运行时库的类(`libprotobuf-lite`，而不是`libprotobuf`)。`lite`运行时比完整的库小得多(大约小一个数量级)，但是忽略了某些特性，比如描述符和反射。这对于在受限平台(如手机)上运行的应用程序尤其有用。编译器仍然会像在`SPEED`模式下那样生成所有方法的快速实现。生成的类将仅用每种语言实现`MessageLite`接口，该接口只提供完整`Message`接口的方法的一个子集。

      ```protobuf
      option optimize_for = CODE_SIZE;
      ```

  - `deprecated`(字段选项)：如果设置为`true`，表示该字段已被弃用，新代码不应该使用该字段。在大多数语言中，这没有实际效果。在`Java`中，这变成了一个`@Deprecated`注释。将来，其他特定于语言的代码生成器可能会在字段访问器上生成弃用注释，这反过来又会在编译试图使用该字段的代码时发出警告。如果任何人都不使用该字段，并且您希望阻止新用户使用它，那么可以考虑使用保留语句替换该字段声明。

    ```protobuf
    int32 old_field = 6 [deprecated = true];
    ```

#### 自定义 Option

- `Protocol Buffers`还允许定义和使用自己的`option`。这是一个大多数人不需要的高级特性。如果你认为需要创建自己的`option`，请参阅[Proto2 Language Guide](https://developers.google.com/protocol-buffers/docs/proto#customoptions)了解详细信息。注意，创建自定义选项使用的是扩展，而扩展只允许用于`proto3`中的自定义选项。

###  自动生成你的类文件

- 要生成`Java、Python、c++、Go、Ruby、Objective-C`或`c#`代码，需要使用`.proto`文件中定义的消息类型，需要在`.proto`上运行`protocol buffer compiler `的`ptotoc`命令。

- `protocol buffer compiler `调用如下:

  ```shell
  protoc --proto_path=IMPORT_PATH  --java_out=DST_DIR  path/to/file.proto
  ```

- `IMPORT_PATH`指定在解析`import`指令时查找`.proto`文件的目录。如果省略，则使用当前目录。可以通过`--proto_path`选项来指定多个导入目录;它们将按顺序被搜查。`-I=IMPORT_PATH`可以用作`--proto_path`的缩写形式。

- `--java_out` ：在`DST_DIR`中生成`Java`代码。有关更多信息，请参见[Java generated code reference](https://developers.google.com/protocol-buffers/docs/reference/java-generated)。

  - 作为一种额外的便利，如果`DST_DIR`以`.zip`或`. JAR`结尾，编译器会将输出写入一个给定名称的`zip`格式存档文件。注意，如果输出存档已经存在，它将被覆盖;编译器不够智能，无法将文件添加到现有存档。

- 您必须提供一个或多个`.proto`文件作为输入。可以一次指定多个`.proto`文件。尽管这些文件是相对于当前目录命名的，但是每个文件必须驻留在`import_path`中的一个目录中，以便编译器可以确定它的规范名称。

## Style Guide

- 本文档提供了`.proto`文件的样式指南。通过遵循这些约定，您将使协议缓冲区消息定义及其对应的类保持一致且易于阅读。

### 标准文件格式

- 保持行长度为80个字符。
- 使用缩进2个空格。

### 文件结构

- 文件应该命名为`lower_snake_case.proto`

- 所有档案的排列顺序如下:
  1. License header (if applicable)
  2. File overview
  3. Syntax
  4. Package
  5. Imports (sorted)
  6. File options
  7. Everything else

### package

- 包名应该是小写的，并且应该与目录层次结构相对应。例如，如果文件在`my/package/`中，那么包名应该是`my.package`。

### message 和 字段名

- 使用`CamelCase`作为`message`名称，例如`SongServerRequest`。对字段名（包括 `oneof`字段和扩展名）使用`underscore_separated_names`，例如`song_name`。

  ```protobuf
  message SongServerRequest {
    required string song_name = 1;
  }
  ```

- 对字段名使用这种命名约定，可以得到如下访问器:

  ```protobuf
  public String getSongName() { ... }
  public Builder setSongName(String v) { ... }
  ```

- 如果您的字段名包含一个数字，该数字应该出现在字母后面，而不是下划线后面。例如，使用`song_name1`而不是`song_name_1`

### 重复字段

- 对重复字段使用复数名称。

  ```protobuf
    repeated string keys = 1;
    ...
    repeated MyMessage accounts = 17;
  ```

### 枚举

- 对枚举类型名使用`CamelCase`，对值名使用大写`CAPITALS_WITH_UNDERSCORES`下划线:

  ```protobuf
  enum Foo {
    FOO_UNSPECIFIED = 0;
    FOO_FIRST_VALUE = 1;
    FOO_SECOND_VALUE = 2;
  }
  ```

- 每个枚举值都应该以分号结束，而不是逗号。宁可使用`enum`值作为前缀，而不要在封装消息中包围它们。零值枚举应该具有`UNSPECIFIED`后缀。

### Services

- 如果你的`.proto`定义了`RPC`服务，你应该对`service name`和任何`RPC method name`都使用`CamelCase`:

  ```protobuf
  service FooService {
    rpc GetSomething(FooRequest) returns (FooResponse);
  }
  ```

## Basics: Java

### 为什么用Protocol Buffer

- 我们将要使用的示例是一个非常简单的“地址簿”应用程序，它可以在文件中读写人们的联系信息。地址簿中的每个人都有姓名、ID、电子邮件地址和联系电话号码。

- 如何像这样序列化和检索结构化数据?有几个方法来解决这个问题:
  - 使用`Java Serialization`。这是默认的方法，因为它已经内置到语言中，但是它有很多众所周知的问题(参见`Josh Bloch`的`《Effective Java》`第`213`页)，而且如果需要与用`c++`或`Python`编写的应用程序共享数据，它也不能很好地工作。
  - 您可以发明一种特殊的方法来将数据项编码为单个字符串—例如将4输入编码为`12:3:-23:67`。这是一种简单而灵活的方法，尽管它确实需要编写一次性编码和解析代码，并且解析的运行时开销很小。这对于编码非常简单的数据最有效。
  - 将数据序列化为`XML`。这种方法非常有吸引力，因为`XML`(某种程度上)是人类可读的，而且有许多语言的绑定库。如果您希望与其他应用程序/项目共享数据，这是一个很好的选择。然而，`XML`是出了名的空间密集型，对它进行编码/解码会给应用程序带来巨大的性能损失。此外，在`XML DOM`树中导航要比在类中的简单字段中导航复杂得多。
- `Protocol Buffer`是一种灵活、高效、自动化的解决方案，可以准确地解决这个问题。使用`Protocol Buffer`，可以编写要存储的数据结构的.proto描述。在此基础上，`Protocol Buffer Compiler`创建一个类，该类使用有效的二进制格式实现`protocol buffer`数据的自动编码和解析。生成的类为组成`protocol buffer`的字段提供`getter`和`setter`方法，并负责将`protocol buffer`作为一个单元读写的细节。重要的是，`protocol buffer`格式支持随时间扩展格式的思想，以便代码仍然可以读取用旧格式编码的数据。

### 定义protocol 格式

- 要创建地址簿应用程序，需要从`.proto`文件开始。`proto`文件中的定义很简单:为要序列化的每个数据结构添加一条消息，然后为消息中的每个字段指定一个名称和类型。这是定义消息的`.proto`文件`addressbook.proto`。

  ```protobuf
  syntax = "proto2";
  
  package tutorial;
  
  option java_package = "com.example.tutorial";
  option java_outer_classname = "AddressBookProtos";
  
  message Person {
    required string name = 1;
    required int32 id = 2;
    optional string email = 3;
  
    enum PhoneType {
      MOBILE = 0;
      HOME = 1;
      WORK = 2;
    }
  
    message PhoneNumber {
      required string number = 1;
      optional PhoneType type = 2 [default = HOME];
    }
  
    repeated PhoneNumber phones = 4;
  }
  
  message AddressBook {
    repeated Person people = 1;
  }
  ```

- 如您所见，其语法类似于`c++`或`Java`。让我们检查一下文件的每个部分，看看它是做什么的。

- `proto`文件以一个`package`声明开始，这有助于防止不同项目之间的命名冲突。在`Java`中，`package`名用作`Java`包，除非您像这里一样显式地指定了`java_package`。即使提供了`java_package`，也应该定义一个普通的`package`，以避免在`protocol buffer`的名称空间中以及在非`java`语言中发生名称冲突。

- 在`package`声明之后，您可以看到两个特定于`java`的选项：`java_package`和`java_outer_classname`。`java_package`指定生成的类的`Java`包名。如果您没有显式地指定它，它只是匹配`package`声明给出的包名称，但是这些名称通常不是合适的`Java`包名称(因为它们通常不以域名开头)。`java_outer_classname`选项定义了类名，它应该包含这个文件中的所有类。如果没有显式地给出`java_outer_classname`，则将通过将文件名转换为驼峰大小写来生成它。例如,`my_proto`,在默认情况下，`proto`将使用`MyProto`作为外部类名。

- 接下来，您有了消息定义。消息只是包含一组类型化字段的聚合。许多标准的简单数据类型可以作为字段类型使用，包括`bool`、`int32`、`float`、`double`和`string`。您还可以通过使用其他消息类型作为字段类型来为消息添加进一步的结构，在上面的示例中，`Person`消息包含`PhoneNumber`消息，而`AddressBook`消息包含`Person`消息。您甚至可以定义嵌套在其他消息中的消息类型——如您所见，`PhoneNumber`类型是在`Person`中定义的。如果您希望某个字段具有预定义的值列表之一，那么还可以定义`enum`类型——在这里，您希望指定电话号码可以是`MOBILE`、`HOME`或`WORK`中的一个。

- 每个元素上的`= 1`、`= 2`标记标识二进制编码中字段使用的惟一“标记”。标记数`1-15`编码所需的字节比编码数高的编码少一个字节，因此，作为一种优化，您可以决定将这些标记用于常用的或重复的元素，而将标记`16`或更高的标记用于不常用的可选元素。重复字段中的每个元素都需要重新编码标记号，因此重复字段特别适合于此优化。

- 每个字段必须用下列修饰符之一进行注释:

  - `required`：必须提供字段的值，否则消息将被视为“未初始化”。试图构建未初始化的消息将抛出`RuntimeException`。解析未初始化的消息将抛出`IOException`。除此之外，`required`字段的行为与`optional`字段完全一样。
  - `optional`：字段可以设置，也可以不设置。如果未设置`optional`字段值，则使用默认值。对于简单类型，您可以指定自己的默认值，就像我们在示例中为电话号码类型所做的那样。否则，将使用系统默认值：数字类型为零，字符串为空，`bools`为假。对于嵌套消息，默认值始终是消息的“默认实例”或“原型”，它没有设置任何字段。调用访问器获取未显式设置的`optional`(或`required`)字段的值总是返回该字段的默认值。
  - `repeated`：该字段可以重复任意次数(包括`0`次)。重复值的顺序将保留在`protocol buffer`中。将重复字段视为动态大小的数组。

### 编译Protocol Buffer

- 现在有了`.proto`，接下来要做的是生成需要读写`AddressBook`(以及`Person`和`PhoneNumber`)消息的类。要做到这一点，你需要在你的`.proto`上运行`protocol buffer compiler`:

  1. [安装](https://developers.google.com/protocol-buffers/docs/downloads)`protocol buffer compiler`

  2. 现在运行`protocol buffer compiler`，指定源目录（应用程序源代码所在的位置，如果不提供，则使用当前目录）、目标目录（要生成代码的位置;通常与`$SRC_DIR`相同），以及`.proto`的路径。在这种情况下，你……

     ```protobuf
     protoc -I=$SRC_DIR --java_out=$DST_DIR $SRC_DIR/addressbook.proto
     ```

- 这将在指定的目标目录中生成`com/example/tutorial/AddressBookProtos.java`。

### Protocol Buffer API

- 让我们看看一些生成的代码，看看`protocol buffer compiler`创建了什么类和方法。如果你看`AddressBookProtos.java`，你可以看到它定义了一个名为`AddressBookProtos`的类，在这个类中嵌套了您在`addressbook.proto`中指定的每个消息的类。每个类都有自己的`Builder`，您可以使用它来创建该类的实例。您可以在[Builders vs. Messages](https://developers.google.com/protocol-buffers/docs/javatutorial#builders)一节中找到关于`Builder`的更多信息。

- `message`和`builder`都为`message`的每个字段自动生成访问器方法,消息只有`getter`方法，而`builder`有`getter`方法和`setter`方法。下面是`Person`类的一些访问器(为简洁起见，省略了实现):

  ```java
  // required string name = 1;
  public boolean hasName();
  public String getName();
  
  // required int32 id = 2;
  public boolean hasId();
  public int getId();
  
  // optional string email = 3;
  public boolean hasEmail();
  public String getEmail();
  
  // repeated .tutorial.Person.PhoneNumber phones = 4;
  public List<PhoneNumber> getPhonesList();
  public int getPhonesCount();
  public PhoneNumber getPhones(int index);
  ```

- 与此同时,`Person.Builder`有相同的`getter`和`setter`:

  ```java
  // required string name = 1;
  public boolean hasName();
  public java.lang.String getName();
  public Builder setName(String value);
  public Builder clearName();
  
  // required int32 id = 2;
  public boolean hasId();
  public int getId();
  public Builder setId(int value);
  public Builder clearId();
  
  // optional string email = 3;
  public boolean hasEmail();
  public String getEmail();
  public Builder setEmail(String value);
  public Builder clearEmail();
  
  // repeated .tutorial.Person.PhoneNumber phones = 4;
  public List<PhoneNumber> getPhonesList();
  public int getPhonesCount();
  public PhoneNumber getPhones(int index);
  public Builder setPhones(int index, PhoneNumber value);
  public Builder addPhones(PhoneNumber value);
  public Builder addAllPhones(Iterable<PhoneNumber> value);
  public Builder clearPhones();
  ```

- 如你所见，每个字段都有简单的`javabean`样式的`getter`和`setter`方法。每个字段都有`hasXXX`方法，如果该字段已经被设置，该方法将返回true。最后，每个字段都有一个`clearXXX`方法，可以将字段重置为空。

- `repeated`字段有一些额外的方法——`XXXcount`方法(也就是`list`的大小),`getter`和`setter`方法获取或设置一个特定的元素列表的索引,`addXXX`方法添加一个新元素添加到列表,一个`addAllXXX`方法将整个容器的元素添加到`list`中。

- 注意这些访问器方法如何使用驼峰命名，即使`.proto`文件使用小写加下划线。此转换由`protocol buffer compiler`自动完成，以便生成的类与标准`Java`样式约定相匹配。在`.proto`文件中，字段名应该始终使用小写加下划线;这确保了在所有生成的语言中都有良好的命名实践。

#### 枚举和嵌套类

- 生成的代码包括一个`java 5`中的枚举`PhoneType` ，嵌套在`Person`中:

  ```java
  public static enum PhoneType {
    MOBILE(0, 0),
    HOME(1, 1),
    WORK(2, 2),
    ;
    ...
  }
  ```

- 嵌套类型`Person`。如你所料，`PhoneNumber`是作为`Person`中的嵌套类生成的。

#### Builders vs. Messages

- `protocol buffer compiler`生成的消息类都是不可变的。一旦构造了消息对象，就像`Java`字符串一样不能修改它。要构造消息，必须首先构造一个`builder`，将要设置的任何字段设置为所选的值，然后调用`builder`的`build()`方法。
- 你可能已经注意到，修改消息的`builder`的每个方法都返回另一个`builder`。返回的对象实际上与在其上调用方法的`builder`相同。返回它是为了方便，这样就可以在一行代码中将多个`setter`连接在一起。

- 下面是一个如何创建`Person`实例的例子:

  ```java
  Person john = Person.newBuilder()
      .setId(1234)
      .setName("John Doe")
      .setEmail("jdoe@example.com")
      .addPhones(
        Person.PhoneNumber.newBuilder()
          .setNumber("555-4321")
          .setType(Person.PhoneType.HOME))
      .build();
  ```

#### Standard Message Methods

- 每个`message`和`builder`类还包含许多其他方法，可以检查或操作整个消息，包括:
  - `isInitialized()`：检查是否已经设置了所有必需的字段。
  - `toString()`：返回人类可读的消息表示，对于调试特别有用。
  - `mergeFrom(Message other)`： (`builder only`)将`other`的内容合并到此消息中，覆盖单数标量字段，合并复合字段，并连接重复的字段。
  - `clear()`：(`builder only`) 清空所有的字段变为空的状态

#### Parsing and Serialization

- 最后，每个`protocol buffer`类都有使用`protocol buffer`二进制格式编写和读取所选类型消息的方法。这些包括:
  - `byte[] toByteArray();`: 序列化消息并返回包含其原始字节的字节数组。
  - `static Person parseFrom(byte[] data);`: 解析来自给定字节数组的消息。
  - `void writeTo(OutputStream output);`: 序列化消息并将其写入`OutputStream`。
  - `static Person parseFrom(InputStream input);`: 读取和解析来自`InputStream`的消息。

### Writing A Message

- 现在让我们尝试使用`protocol buffer`类。您希望地址簿应用程序能够做的第一件事是将个人详细信息写入地址簿文件。为此，您需要创建并填充`protocol buffer`类的实例，然后将它们写入输出流。

- 这是一个程序，它从文件中读取`AddressBook`，根据用户输入添加一个新`Person`，然后将新的`AddressBook`重新写入文件。突出显示了直接调用或引用`protocol buffer compiler`生成的代码的部分。

  ```java
  import com.example.tutorial.AddressBookProtos.AddressBook;
  import com.example.tutorial.AddressBookProtos.Person;
  import java.io.BufferedReader;
  import java.io.FileInputStream;
  import java.io.FileNotFoundException;
  import java.io.FileOutputStream;
  import java.io.InputStreamReader;
  import java.io.IOException;
  import java.io.PrintStream;
  
  class AddPerson {
    // This function fills in a Person message based on user input.
    static Person PromptForAddress(BufferedReader stdin,
                                   PrintStream stdout) throws IOException {
      Person.Builder person = Person.newBuilder();
  
      stdout.print("Enter person ID: ");
      person.setId(Integer.valueOf(stdin.readLine()));
  
      stdout.print("Enter name: ");
      person.setName(stdin.readLine());
  
      stdout.print("Enter email address (blank for none): ");
      String email = stdin.readLine();
      if (email.length() > 0) {
        person.setEmail(email);
      }
  
      while (true) {
        stdout.print("Enter a phone number (or leave blank to finish): ");
        String number = stdin.readLine();
        if (number.length() == 0) {
          break;
        }
  
        Person.PhoneNumber.Builder phoneNumber =
          Person.PhoneNumber.newBuilder().setNumber(number);
  
        stdout.print("Is this a mobile, home, or work phone? ");
        String type = stdin.readLine();
        if (type.equals("mobile")) {
          phoneNumber.setType(Person.PhoneType.MOBILE);
        } else if (type.equals("home")) {
          phoneNumber.setType(Person.PhoneType.HOME);
        } else if (type.equals("work")) {
          phoneNumber.setType(Person.PhoneType.WORK);
        } else {
          stdout.println("Unknown phone type.  Using default.");
        }
  
        person.addPhones(phoneNumber);
      }
  
      return person.build();
    }
  
    // Main function:  Reads the entire address book from a file,
    //   adds one person based on user input, then writes it back out to the same
    //   file.
    public static void main(String[] args) throws Exception {
      if (args.length != 1) {
        System.err.println("Usage:  AddPerson ADDRESS_BOOK_FILE");
        System.exit(-1);
      }
  
      AddressBook.Builder addressBook = AddressBook.newBuilder();
  
      // Read the existing address book.
      try {
        addressBook.mergeFrom(new FileInputStream(args[0]));
      } catch (FileNotFoundException e) {
        System.out.println(args[0] + ": File not found.  Creating a new file.");
      }
  
      // Add an address.
      addressBook.addPerson(
        PromptForAddress(new BufferedReader(new InputStreamReader(System.in)),
                         System.out));
  
      // Write the new address book back to disk.
      FileOutputStream output = new FileOutputStream(args[0]);
      addressBook.build().writeTo(output);
      output.close();
    }
  }
  ```

### Reading A Message

- 当然，如果你不能从地址簿中获得任何信息，地址簿就没有多大用处!这个例子读取上面例子创建的文件并打印其中的所有信息。

  ```java
  import com.example.tutorial.AddressBookProtos.AddressBook;
  import com.example.tutorial.AddressBookProtos.Person;
  import java.io.FileInputStream;
  import java.io.IOException;
  import java.io.PrintStream;
  
  class ListPeople {
    // Iterates though all people in the AddressBook and prints info about them.
    static void Print(AddressBook addressBook) {
      for (Person person: addressBook.getPeopleList()) {
        System.out.println("Person ID: " + person.getId());
        System.out.println("  Name: " + person.getName());
        if (person.hasEmail()) {
          System.out.println("  E-mail address: " + person.getEmail());
        }
  
        for (Person.PhoneNumber phoneNumber : person.getPhonesList()) {
          switch (phoneNumber.getType()) {
            case MOBILE:
              System.out.print("  Mobile phone #: ");
              break;
            case HOME:
              System.out.print("  Home phone #: ");
              break;
            case WORK:
              System.out.print("  Work phone #: ");
              break;
          }
          System.out.println(phoneNumber.getNumber());
        }
      }
    }
  
    // Main function:  Reads the entire address book from a file and prints all
    //   the information inside.
    public static void main(String[] args) throws Exception {
      if (args.length != 1) {
        System.err.println("Usage:  ListPeople ADDRESS_BOOK_FILE");
        System.exit(-1);
      }
  
      // Read the existing address book.
      AddressBook addressBook =
        AddressBook.parseFrom(new FileInputStream(args[0]));
  
      Print(addressBook);
    }
  }
  ```

### Extending a Protocol Buffer

- 在释放使用``protocol buffer``的代码之后，您迟早会希望“改进”``protocol buffer``的定义。如果希望新的`protocol buffer`向后兼容，而旧的`protocol buffer`向前兼容(您几乎肯定希望这样)，那么您需要遵循一些规则。在新版本的`protocol buffer`:
  - 不能更改任何现有字段的标记号。
  - 不能添加或删除任何`required`的字段。
  - 可以删除`optional`或`repeated`字段。
  - 可以添加新的`optional`或`repeated`字段，但是必须使用新的标记号(例如，标记号从来没有在这个`protocol buffer`中使用过，甚至没有被删除的字段使用过)。
- 如果您遵循这些规则，旧代码就会很高兴地阅读新消息，并简单地忽略任何新字段。对于旧代码，已删除的`optional`字段将只有其默认值，已删除的`repeated`字段将为空。新代码也将透明地读取旧消息。但是，请记住，新的`optional`字段不会出现在旧消息中，因此您需要显式地检查它们是否用`has_XXX`设置，或者在`.proto`文件中提供一个合理的默认值，在标记号后面加上`[default = value]`。如果未为`optional`元素指定默认值，则使用特定于类型的默认值：对于字符串，默认值为空字符串。对于布尔值，默认值为`false`。对于数字类型，默认值为零。还要注意，如果您添加了一个新的`repeat`字段，那么新代码将无法判断它是空的(由新代码决定)还是从未设置过(由旧代码决定)，因为它没有`has_`标志。

