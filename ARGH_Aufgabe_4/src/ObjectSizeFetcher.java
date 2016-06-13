import java.lang.instrument.Instrumentation;

public class ObjectSizeFetcher {
	
	private static Instrumentation instrumentation;

    public static void premain(Instrumentation inst) {
        instrumentation = inst;
    }

    public static long getObjectSize(Object o) {
        return instrumentation.getObjectSize(o);
    }
}
