package org.braisdom.drucker.intellij.plugin;

import com.intellij.codeInspection.InspectionToolProvider;
import org.jetbrains.annotations.NotNull;

public class JDruckerLombokInspectionProvider implements InspectionToolProvider {
    @NotNull
    @Override
    public Class[] getInspectionClasses() {
        System.out.println("----------------");
        return new Class[]{JDruckerLombokInspection.class};
    }
}
