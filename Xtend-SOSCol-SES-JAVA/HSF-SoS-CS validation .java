package my.package.HSFSOSDSL;

/**
 * Class to validate the IoT-SFS model.
 */
public class IoTSFSModelValidator {

    /**
     * Ensures that the IoT-SFS model is well-formed.
     *
     * @param system the constituent system to validate
     */
    @Check
    public void checkIoTSFSModel(Constituent_System system) {
        // Check that the system has a parameter list
        if (system.parameter_List == null || system.parameter_List.parameter.isEmpty()) {
            warning("The system does not have any parameters", HybridStochasticFormalismPackage.Literals.CONSTITUENT_SYSTEM__PARAMETER_LIST);
        }

        // Check that the system has at least one public interface
        if (system.publicInterface.isEmpty()) {
            error("The system does not have any public interfaces", HybridStochasticFormalismPackage.Literals.CONSTITUENT_SYSTEM__PUBLIC_INTERFACE);
        }

        // Check that each public interface has a name
        for (PublicInterface publicInterface : system.publicInterface) {
            if (publicInterface.name == null || publicInterface.name.trim().isEmpty()) {
                warning("A public interface does not have a name", HybridStochasticFormalismPackage.Literals.PUBLIC_INTERFACE__NAME);
            }
        }

        // Check that the system has at least one behavior
        if (system.behaviors.isEmpty()) {
            error("The system does not have any behaviors", HybridStochasticFormalismPackage.Literals.CONSTITUENT_SYSTEM__BEHAVIORS);
        }

        // Check that each behavior has a name
        for (Behavior behavior : system.behaviors) {
            if (behavior.name == null || behavior.name.trim().isEmpty()) {
                warning("A behavior does not have a name", HybridStochasticFormalismPackage.Literals.BEHAVIOR__NAME);
            }
        }

        // Check that each behavior has at least one stochastic constraint
        for (Behavior behavior : system.behaviors) {
            if (behavior.stochasticConstraint.isEmpty()) {
                error("A behavior does not have any stochastic constraints", HybridStochasticFormalismPackage.Literals.BEHAVIOR__STOCHASTIC_CONSTRAINT);
            }
        }

        // Check that each stochastic constraint has a tell and ask clause
        for (StochasticConstraint stochasticConstraint : system.getAllStochasticConstraints()) {
            if (stochasticConstraint.tellClause == null) {
                error("A stochastic constraint does not have a tell clause", HybridStochasticFormalismPackage.Literals.STOCHASTIC_CONSTRAINT__TELL_CLAUSE);
            }

            if (stochasticConstraint.askClause == null) {
                error("A stochastic constraint does not have an ask clause", HybridStochasticFormalismPackage.Literals.STOCHASTIC_CONSTRAINT__ASK_CLAUSE);
            }
        }

        // Check that each stochastic constraint has a valid ask and tell clause
        for (StochasticConstraint stochasticConstraint : system.getAllStochasticConstraints()) {
            // Check the tell clause
            if (stochasticConstraint.tellClause != null) {
                if (stochasticConstraint.tellClause.variable == null) {
                    error("The tell clause of a stochastic constraint does not have a variable", HybridStochasticFormalismPackage.Literals.STOCHASTIC_CONSTRAINT__TELL_CLAUSE);
                }

                if (stochasticConstraint.tellClause.expression == null) {
                    error("The tell clause of a stochastic constraint does not have an expression", HybridStochasticFormalismPackage.Literals.STOCHASTIC_CONSTRAINT__TELL_CLAUSE);
                }
            }

            // Check the ask clause
            if (stochasticConstraint.askClause != null) {
                if (stochasticConstraint.askClause.variable == null) {
                    error("The ask clause of a stochastic constraint does not have a variable", HybridStochasticFormalismPackage.Literals.STOCHASTIC_CONSTRAINT__ASK_CLAUSE);
                }

                if (stochasticConstraint.askClause.expression == null) {
                    error("The ask clause of a stochastic constraint does not have an expression", HybridStochasticFormalismPackage.Literals.STOCHASTIC_CONSTRAINT__ASK_CLAUSE);
                }
            }
        }
    }

    private void warning(String message, Object... args) {
        // Implementation for warning messages
    }

    private void error(String message, Object... args) {
        // Implementation for error messages
    }
}
