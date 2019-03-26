package gl.procamp;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(value = Mode.AverageTime)
public class BenchmarkTask {

    private final int size = 100;

    @Benchmark
    public void tuneThrowingExceptionWithStackTrace() {
        throw new CustomExceptionWithStackTrace("with stackTrace");

    }

    @Benchmark
    public void tuneThrowingExceptionWithoutStackTrace() {
        throw new CustomRuntimeExceptionWithoutStachTrace("without stackTrace");
    }

    @Benchmark
    public void tuneLoopOperation() {
        List<Integer> listValues = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            listValues.add(i);
        }
        System.out.println(listValues.get(0));
    }

    @Benchmark
    public void tuneStreamOperation() {
        List<Integer> listValues = IntStream.rangeClosed(1, this.size).boxed().collect(Collectors.toList());
        System.out.println(listValues.get(0));
    }

    @Benchmark
    public void tuneStreamParallelOperation(){
        List<Integer> listValues = IntStream.rangeClosed(1, this.size).parallel().boxed().collect(Collectors.toList());
        System.out.println(listValues.get(0));
    }
}