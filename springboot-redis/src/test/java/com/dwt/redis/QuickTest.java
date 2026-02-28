// package com.dwt.redis;
//
// import org.openjdk.jmh.annotations.*;
// import java.util.concurrent.TimeUnit;
//
// @BenchmarkMode(Mode.Throughput)
// @OutputTimeUnit(TimeUnit.SECONDS)
// @State(Scope.Benchmark)
// @Warmup(iterations = 1, time = 1)
// @Measurement(iterations = 1, time = 1)
// @Threads(1)
// public class QuickTest {
//
//     @Benchmark
//     public int testMethod() {
//         return 1 + 1;
//     }
//
//     public static void main(String[] args) throws Exception {
//         org.openjdk.jmh.Main.main(args);
//     }
// }