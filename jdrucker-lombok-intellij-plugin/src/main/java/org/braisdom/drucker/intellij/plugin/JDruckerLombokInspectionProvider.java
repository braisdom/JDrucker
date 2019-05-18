package org.braisdom.drucker.intellij.plugin;

import com.intellij.codeInspection.InspectionToolProvider;
import org.jetbrains.annotations.NotNull;

public class JDruckerLombokInspectionProvider implements InspectionToolProvider {
    @NotNull
    @Override
    public Class[] getInspectionClasses() {
        return new Class[]{JDruckerLombokInspection.class};
    }
}
