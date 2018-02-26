package hu.bme.mit.modelgen.conditions;

import java.util.Set;

public class TerminationConditions {

    private Set<TerminationCondition> conditions;

    public boolean satisfiedAny() {
        return conditions.stream().anyMatch(cond -> cond.satisfied());
    }

}
