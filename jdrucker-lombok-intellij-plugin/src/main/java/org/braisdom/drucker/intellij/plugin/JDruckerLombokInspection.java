package org.braisdom.drucker.intellij.plugin;

import com.intellij.codeInspection.AbstractBaseJavaLocalInspectionTool;
import com.intellij.codeInspection.InspectionsBundle;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.JavaElementVisitor;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiMethodCallExpression;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

public class JDruckerLombokInspection extends AbstractBaseJavaLocalInspectionTool {

    @NotNull
    @Override
    public String getDisplayName() {
        return "JDrucker Lombok annotations inspection";
    }

    @Nls
    @NotNull
    @Override
    public String getGroupDisplayName() {
        return InspectionsBundle.message("group.names.probable.bugs");
    }

    @NotNull
    @Override
    public String getShortName() {
        return "JDruckerLombok";
    }

    @Override
    public boolean isEnabledByDefault() {
        return true;
    }

    @NotNull
    @Override
    public PsiElementVisitor buildVisitor(@NotNull final ProblemsHolder holder, final boolean isOnTheFly) {
        return new JDruckerLombokVisitor(holder);
    }

    private class JDruckerLombokVisitor extends JavaElementVisitor {

        public JDruckerLombokVisitor(ProblemsHolder holder) {
        }

        @Override
        public void visitMethodCallExpression(PsiMethodCallExpression expression) {
            super.visitMethodCallExpression(expression);
        }

        @Override
        public void visitAnnotation(PsiAnnotation annotation) {
            super.visitAnnotation(annotation);
        }
    }
}
