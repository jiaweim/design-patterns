package tutorial.design_pattern.pipeline;

// 定义输入、输出类型，以及处理顺序
public interface Step<I, O> {

    O process(I input);

    default <R> Step<I, R> pipe(Step<O, R> source) {
        return value -> source.process(process(value));
    }

    static <IN, OUT> Step<IN, OUT> of(Step<IN, OUT> source) {
        return source;
    }
}