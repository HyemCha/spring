package mvc.resnerrorcoach.common;

import java.util.ArrayList;
import java.util.List;

public class ExceptionContext {

    static public final ThreadLocal<List<Integer>> threadLocal = ThreadLocal.withInitial(ArrayList::new);

}
