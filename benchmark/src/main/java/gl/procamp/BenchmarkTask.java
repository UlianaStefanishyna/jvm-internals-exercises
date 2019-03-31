package gl.procamp;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 10, timeUnit = TimeUnit.MILLISECONDS)
@BenchmarkMode(value = Mode.AverageTime)
public class BenchmarkTask {


    private final int size = 10_000_000;

    //    -------------------- Exception with stackTrace vs Exception without stackTrace ----------------------------------
    @Benchmark
    public void tuneThrowingExceptionWithStackTrace(Blackhole blackhole) {
        String message;
        try {
            throw new CustomExceptionWithStackTrace("with stackTrace");
        } catch (CustomExceptionWithStackTrace e) {
            message = e.getMessage();
        }
        blackhole.consume(message);
    }

    @Benchmark
    public void tuneThrowingExceptionWithoutStackTrace(Blackhole blackhole) {
        String message;
        try {
            throw new CustomRuntimeExceptionWithoutStachTrace("without stackTrace");
        } catch (CustomRuntimeExceptionWithoutStachTrace e) {
            message = e.getMessage();
        }
        blackhole.consume(message);
    }
//    with_stacktrace       without_stacktrace
//    1438.105              26.507

//    -----------------------------------------------------------------------------------------------------------------

    @Benchmark
    public void tuneLoopOperation(Blackhole blackhole) {
        List<Integer> listValues = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            listValues.add(i);
        }
        blackhole.consume(listValues.get(0));
    }
//    size          loop                stream              parallel_stream
//    100           979.783             2_734.213           237_914.954
//    1_000         24_794.766          27_090.149          273_669.820
//    10_000        218_450.643         253_816.701         776_081.298
//    100_000       1_681_990.495       1_938_446.931       2_940_217.234
//    1_000_000     35_545_604.920      36_750_890.680      41_276_167.060
//    10_000_000    404_264_730.280     421_823_436.760     669_853_224.680

    @Benchmark
    public void tuneStreamOperation(Blackhole blackhole) {
        List<Integer> listValues = IntStream.rangeClosed(1, this.size).boxed().collect(Collectors.toList());
        blackhole.consume(listValues.get(0));
    }

    @Benchmark
    public void tuneStreamParallelOperation(Blackhole blackhole) {
        List<Integer> listValues = IntStream.rangeClosed(1, this.size).parallel().boxed().collect(Collectors.toList());
        blackhole.consume(listValues.get(0));
    }
}