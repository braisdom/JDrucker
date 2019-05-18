package org.braisdom.drucker.intellij.plugin;

import com.intellij.openapi.project.DumbService;
import com.intellij.psi.*;
import com.intellij.psi.augment.PsiAugmentProvider;
import de.plushnikov.intellij.lombok.UserMapKeys;
import de.plushnikov.intellij.lombok.processor.clazz.LombokClassProcessor;
import de.plushnikov.intellij.lombok.util.PsiClassUtil;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class JDruckerLombokAugmentProvider extends PsiAugmentProvider {

    private final Collection<LombokClassProcessor> allClassHandlers;

    public JDruckerLombokAugmentProvider() {
        allClassHandlers = new HashSet<>();
        allClassHandlers.add(new JDruckerActiveRecordProcessor());
    }

    @NotNull
    @Override
    public <Psi extends PsiElement> List<Psi> getAugments(@NotNull PsiElement element, @NotNull Class<Psi> type) {
        List<Psi> result = Collections.emptyList();
        // Expecting that we are only augmenting an PsiClass
        // Don't filter !isPhysical elements or code autocompletion will not work
        if (!(element instanceof PsiClass) || !element.isValid()) {
            return result;
        }
        // skip processing during index rebuild
        if (DumbService.getInstance(element.getProject()).isDumb()) {
            return result;
        }

        result = new ArrayList<Psi>();
        final PsiClass psiClass = (PsiClass) element;

        if (type.isAssignableFrom(PsiField.class)) {

        } else if (type.isAssignableFrom(PsiMethod.class)) {
            cleanAttributeUsage(psiClass);
            processPsiClassAnnotations(result, psiClass, type);
        }
        return result;
    }

    protected void cleanAttributeUsage(PsiClass psiClass) {
        for (PsiField psiField : PsiClassUtil.collectClassFieldsIntern(psiClass)) {
            UserMapKeys.removeAllUsagesFrom(psiField);
        }
    }

    private <Psi extends PsiElement> void processPsiClassAnnotations(@NotNull List<Psi> result, @NotNull PsiClass psiClass,
                                                                     @NotNull Class<Psi> type) {
        final PsiModifierList modifierList = psiClass.getModifierList();
        if (modifierList != null) {
            for (PsiAnnotation psiAnnotation : modifierList.getAnnotations()) {
                processClassAnnotation(psiAnnotation, psiClass, result, type);
            }
        }
    }

    private <Psi extends PsiElement> void processClassAnnotation(@NotNull PsiAnnotation psiAnnotation, @NotNull PsiClass psiClass,
                                                                 @NotNull List<Psi> result, @NotNull Class<Psi> type) {
        for (LombokClassProcessor classProcessor : allClassHandlers) {
            if (classProcessor.acceptAnnotation(psiAnnotation, type)) {
                classProcessor.process(psiClass, psiAnnotation, result);
            }
        }
    }

}
