import my.package.HSFSOSDSL.*;

class CSDesignValidation extends AbstractCSDesignValidation {

    @Inject ValidationHelper helper;

    @Check
    public void checkCSDecl(CSDecl csDecl) {
        helper.warning("Not all validations have been implemented yet.", CSDeclPackage.Literals.CS_DECL);
    }

    @Check
    public void checkPublicinterfaceDef(PublicinterfaceDef publicinterfaceDef) {
        var formalParameters = publicinterfaceDef.paramsDecl?.formalParameterList?.paramDecl;
        if (formalParameters != null) {
            for (int i = 0; i < formalParameters.size() - 1; i++) {
                var currentParameter = formalParameters.get(i);
                for (int j = i + 1; j < formalParameters.size(); j++) {
                    var otherParameter = formalParameters.get(j);
                    if (currentParameter.name.equals(otherParameter.name)) {
                        helper.error("Duplicate parameter name " + currentParameter.name + ".", publicinterfaceDef,
                                CSDeclPackage.Literals.PUBLICINTERFACE_DEF__NAME, ValidationMessageAcceptor.INSIGNIFICANT_INDEX);
                    }
                }
            }
        }
    }

    @Check
    public void checkProtDeclaration(ProtDeclaration protDeclaration) {
        if (protDeclaration.PublicInteractionPointArea.isEmpty()) {
            helper.error("A port declaration must have at least one public interface definition.", protDeclaration,
                    CSDeclPackage.Literals.PROT_DECLARATION__PORTTYPE);
        } else {
            Set<String> names = new HashSet<>();
            for (var publicInterfaceDef : protDeclaration.PublicInteractionPointArea) {
                if (names.contains(publicInterfaceDef.Name)) {
                    helper.error("Duplicate interface name " + publicInterfaceDef.Name + ".", publicInterfaceDef,
                            CSDeclPackage.Literals.PUBLICINTERFACE_DEF__NAME, ValidationMessageAcceptor.INSIGNIFICANT_INDEX);
                }
                names.add(publicInterfaceDef.Name);
            }
        }
    }

    @Check
    public void checkCColActions(CColActions cColActions) {
        if (cColActions.methodBody.isEmpty()) {
            helper.warning("This collaborative action has no functionality.", cColActions,
                    CSDeclPackage.Literals.CCOL_ACTIONS__NAME);
        }
    }

    @Check
    public void checkAttachCSs(AttachCSs attachCSs) {
        if (attachCSs.attachParam1.type instanceof PortDecl) {
            var portDeclaration = (PortDecl) attachCSs.attachParam1.type;
            if (portDeclaration.PublicInteractionPointArea.stream().noneMatch(interface -> interface.Name.equals(attachCSs.attachParam1.ID))) {
                helper.error("Interface " + attachCSs.attachParam1.ID + " does not exist in port " + portDeclaration.portName + ".", attachCSs,
                        CSDeclPackage.Literals.ATTACH_CS__ATTACHPARAM1);
            }
        } else if (attachCSs.attachParam1.type instanceof TraitDeclaration) {
            var traitDeclaration = (TraitDeclaration) attachCSs.attachParam1.type;
            if (traitDeclaration.roles.stream().noneMatch(role -> role.Name.equals(attachCSs.attachParam1.ID))) {
                helper.error("Role " + attachCSs.attachParam1.ID + " does not exist in trait " + traitDeclaration.name + ".", attachCSs,
                        CSDeclPackage.Literals.ATTACH_CS__ATTACHPARAM1);
            }
        } import my.package.HSFSOSDSL.*;

// Ensure that the IoT-SFS model is well-formed
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
}else {
            helper.error("Type " + attachCSs.attachParam1.type + " is not supported as an attachment parameter.", attachCSs,
                    CSDeclPackage.Literals.ATTACH_CS__ATTACHPARAM1);
        }
    }
}

class MedDeclValidator {

    @Check
    public void checkMedRoleDeclarationNameUnique(MedDecl medDecl) {
        medDecl.MedRoles.stream()
                .filter(r -> r.name != null)
                .collect(Collectors.groupingBy(r -> r.name))
                .forEach((name, roles) -> {
                    if (roles.size() > 1) {
                        error("Duplicate MedRoleDeclaration name '" + name + "'", roles.get(0), null, "uniqueName");
                    }
                });
    }

    @Check
    public void checkPublicInteractionPointDefined(MedRoleDeclaration medRoleDecl) {
        if (medRoleDecl.PublicInteractionPoint == null) {
            error("No PublicInteractionPoint defined for MedRoleDeclaration", medRoleDecl, null, "publicInterface");
        }
    }
}

class BsArchDeclValidator extends AbstractBsArchDeclValidator {

    @Check
    public void checkGlobalPropertiesType(GlobalProperties globalProps) {
        // Check that the defined property type is valid
        var propertyType = globalProps.PropertyType.get(0).name;
        if (!isValidType(propertyType)) {
            error("Invalid property type: " + propertyType, globalProps,
                    BsArchDeclPackage.Literals.GLOBAL_PROPERTIES__PROPERTY_TYPE,
                    ValidationMessageAcceptor.INSIGNIFICANT_INDEX);
        }
    }

    private boolean isValidType(String type) {
        // Check if the given type is a valid type
        return Set.of("string", "int", "float", "boolean", "type", "void").contains(type);
    }
}