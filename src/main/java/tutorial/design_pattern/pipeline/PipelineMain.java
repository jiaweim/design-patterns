package tutorial.design_pattern.pipeline;

class PipelineMain {

    public static void main(String[] args) {
        Step<String, Character> pipe = Step.of((Step<String, String>) String::toUpperCase)
                .pipe(input -> input.charAt(0));
        Character test = pipe.process("test");
        System.out.println(test);
    }
}