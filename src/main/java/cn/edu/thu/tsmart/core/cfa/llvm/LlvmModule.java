package cn.edu.thu.tsmart.core.cfa.llvm;

import com.google.common.collect.Sets;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author guangchen on 24/02/2017.
 */
public class LlvmModule {
    private final Context context;
    private final String moduleIdentifier;
    private final Map<String, LlvmFunction> functionMap;
    private final List<GlobalVariable> globalList;

    public LlvmModule(Context context, String moduleIdentifier, Map<String, LlvmFunction> functionMap, List<GlobalVariable> globalList) {
        this.context = context;
        this.moduleIdentifier = moduleIdentifier;
        this.functionMap = functionMap;
        this.globalList = globalList;
    }

    public String getModuleIdentifier() {
        return this.moduleIdentifier;
    }

    public LlvmFunction getFunction(String name) {
        return functionMap.get(name);
    }

    public Iterable<Map.Entry<String, LlvmFunction>> functionEntries() {
        return functionMap.entrySet();
    }

    public Iterable<LlvmFunction> functions() {
        return functionMap.values();
    }

    public List<GlobalVariable> getGlobalList() {
        return globalList;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public int hashCode() {
        int result = moduleIdentifier.hashCode();
        result = 31 * result + functionMap.hashCode();
        result = 31 * result + globalList.hashCode();
        return result;
    }
}