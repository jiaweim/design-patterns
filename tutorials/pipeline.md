# 管道模式

2023-12-02, 14:07
@author Jiawei Mao
****

## 简介

管道模式（pipeline pattern）将一系列操作串联起来，当前操作的输出为下一个操作的输入。

## 实现

下面是一个简洁实现：

```java
public interface Step<I, O> {

    O apply(I input);

    default <R> Step<I, R> pipe(Step<O, R> source) {

        return value -> source.apply(apply(value));
    }

    static <IN, OUT> Step<IN, OUT> of(Step<IN, OUT> source) {
        return source;
    }
}
```

调用：将字符串先转换为大写，然后取第一个字母

```java
Step<String, Character> pipe = Step.of((Step<String, String>) String::toUpperCase)
        .pipe(input -> input.charAt(0));
Character test = pipe.process("test");
System.out.println(test);
```

```
T
```

## Function

Java 中，`Stream` 采用了管道模式，参考 `Function` 实现：

```java
public interface Function<T, R> {

    R apply(T t);

    default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

    default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }
}
```

可以发现，该实现与 `Step` 几乎一样。不过 `Function` 与 `Stream` 集成较好，因此建议直接采用 Java `Stream`，不用再自定义实现管道模式。

## 参考

- https://stackoverflow.com/questions/39947155/pipeline-design-pattern-implementation