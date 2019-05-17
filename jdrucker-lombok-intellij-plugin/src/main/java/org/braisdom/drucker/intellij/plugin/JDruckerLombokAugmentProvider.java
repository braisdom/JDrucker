package org.braisdom.drucker.intellij.plugin;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.augment.PsiAugmentProvider;
import com.intellij.psi.impl.source.PsiExtensibleClass;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class JDruckerLombokAugmentProvider extends PsiAugmentProvider {

    @NotNull
    @Override
    protected <Psi extends PsiElement> List<Psi> getAugments(@NotNull PsiElement element, @NotNull Class<Psi> type) {
        final List<Psi> emptyResult = Collections.emptyList();
        if ((type != PsiClass.class && type != PsiField.class && type != PsiMethod.class) || !(element instanceof PsiExtensibleClass)) {
            return emptyResult;
        }

        // Don't filter !isPhysical elements or code auto completion will not work
        if (!element.isValid()) {
            return emptyResult;
        }
        final PsiClass psiClass = (PsiClass) element;
        // Skip processing of Annotations and Interfaces
        if (psiClass.isAnnotationType() || psiClass.isInterface()) {
            return emptyResult;
        }

        return emptyResult;
    }

}
