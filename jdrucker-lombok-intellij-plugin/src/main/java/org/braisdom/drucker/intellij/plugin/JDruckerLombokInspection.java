package org.braisdom.drucker.intellij.plugin;

import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.JavaElementVisitor;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiReferenceExpression;
import de.plushnikov.intellij.lombok.problem.LombokProblem;
import de.plushnikov.intellij.lombok.processor.LombokProcessor;
import de.plushnikov.intellij.plugin.inspection.LombokInspection;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class JDruckerLombokInspection extends LombokInspection {

    private Map<String, LombokProcessor> allProblemHandlers;

    public JDruckerLombokInspection() {
        JDruckerActiveRecordProcessor activeRecordProcessor = new JDruckerActiveRecordProcessor();
        allProblemHandlers = new HashMap<>();

        allProblemHandlers.put(activeRecordProcessor.getSupportedAnnotation(), activeRecordProcessor);
    }

    @NotNull
    @Override
    public PsiElementVisitor buildVisitor(@NotNull final ProblemsHolder holder, final boolean isOnTheFly) {
        return new JDruckerLombokVisitor(holder);
    }

    private class JDruckerLombokVisitor extends JavaElementVisitor {

        private final ProblemsHolder holder;

        public JDruckerLombokVisitor(ProblemsHolder holder) {
            this.holder = holder;
        }

        @Override
        public void visitReferenceExpression(PsiReferenceExpression expression) {
            // do nothing, just implement
        }

        @Override
        public void visitAnnotation(PsiAnnotation annotation) {
            super.visitAnnotation(annotation);

            final String qualifiedName = annotation.getQualifiedName();
            if (null != qualifiedName && allProblemHandlers.containsKey(qualifiedName)) {
                LombokProcessor inspector = allProblemHandlers.get(qualifiedName);
                Collection<LombokProblem> problems = inspector.verifyAnnotation(annotation);
                for (LombokProblem problem : problems) {
                    holder.registerProblem(annotation, problem.getMessage(),
                            problem.getHighlightType(), problem.getQuickFixes());
                }
            }
        }
    }
}
