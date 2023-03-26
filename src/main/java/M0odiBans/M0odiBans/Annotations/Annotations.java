package M0odiBans.M0odiBans.Annotations;

import M0odiBans.M0odiBans.Annotations.InjectAliasesCheck.InjectAliasesCheck;
import M0odiBans.M0odiBans.Annotations.InjectAliasesCheck.InjectAliasesCheckValidator;
import M0odiBans.M0odiBans.Annotations.InjectBannedCheck.InjectBannedCheck;
import M0odiBans.M0odiBans.Annotations.InjectBannedCheck.InjectBannedCheckValidator;
import M0odiBans.M0odiBans.Annotations.InjectMutedCheck.InjectMutedCheck;
import M0odiBans.M0odiBans.Annotations.InjectMutedCheck.InjectMutedCheckValidator;
import M0odiBans.M0odiBans.Annotations.InjectPriorityCheck.InjectPriorityCheck;
import M0odiBans.M0odiBans.Annotations.InjectPriorityCheck.InjectPriorityCheckValidator;
import M0odiBans.M0odiBans.Annotations.InjectPriorityPermCheck.InjectPriorityPermCheck;
import M0odiBans.M0odiBans.Annotations.InjectPriorityPermCheck.InjectPriorityPermCheckValidator;
import M0odiBans.M0odiBans.Annotations.InjectReasonExistsCheck.InjectReasonExistsCheck;
import M0odiBans.M0odiBans.Annotations.InjectReasonExistsCheck.InjectReasonExistsCheckValidator;
import M0odiBans.M0odiBans.Annotations.InjectRequirements.InjectRequirements;
import M0odiBans.M0odiBans.Annotations.InjectRequirements.InjectRequirementsValidator;
import M0odiBans.M0odiBans.Annotations.InjectYourSelfTarget.InjectYourSelfTarget;
import M0odiBans.M0odiBans.Annotations.InjectYourSelfTarget.InjectYourSelfTargetValidator;
import lombok.AllArgsConstructor;

import java.lang.annotation.Annotation;

@AllArgsConstructor
public enum Annotations {

    INJECT_REQUIREMENTS (InjectRequirements.class, InjectRequirementsValidator.class),
    INJECT_ALIASES_CHECK (InjectAliasesCheck.class, InjectAliasesCheckValidator.class),
    INJECT_REASON_EXISTS_CHECK (InjectReasonExistsCheck.class, InjectReasonExistsCheckValidator.class),
    INJECT_YOUR_SELF_TARGET (InjectYourSelfTarget.class, InjectYourSelfTargetValidator.class),
    INJECT_PRIORITY_CHECK (InjectPriorityCheck.class, InjectPriorityCheckValidator.class),
    INJECT_PRIORITY_PERM_CHECK (InjectPriorityPermCheck.class, InjectPriorityPermCheckValidator.class),
    INJECT_BANNED_CHECK (InjectBannedCheck.class, InjectBannedCheckValidator.class),
    INJECT_MUTED_CHECK (InjectMutedCheck.class, InjectMutedCheckValidator.class);

    private final Class<? extends Annotation> annotation;

    private final Class<? extends Validator> validator;


    /**
     * Метод возвращает валидатор аннотации из аргумента.
     *
     * @param annotation Аннотация.
     * @return Валидатор этой аннотации, или, если его нет, null.
     */
    public static Class<? extends Validator> getValidatorAnnotation(Annotation annotation) {

        for (Annotations value : Annotations.values()) {
            if (value.annotation.equals(annotation.annotationType())) return value.validator;
        }

        return null;

    }

}
