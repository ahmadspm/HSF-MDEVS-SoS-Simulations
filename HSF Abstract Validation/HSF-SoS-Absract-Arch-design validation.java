import my.package.HSFSOSDSL.*

class CSDesignValidation extends AbstractCSDesignValidation {

    @Inject ValidationHelper helper

    @Check
    def checkCSDecl(CSDecl csDecl) {
        helper.warning("Not all validations have been implemented yet.", CSDeclPackage.Literals.CS_DECL)
    }
    
    @Check
    def checkPublicinterfaceDef(PublicinterfaceDef publicinterfaceDef) {
        def formalParameters = publicinterfaceDef.paramsDecl?.formalParameterList?.paramDecl
        if (formalParameters != null) {
            for (i in 0..(formalParameters.size() - 2)) {
                def currentParameter = formalParameters[i]
                for (j in (i+1)..(formalParameters.size() - 1)) {
                    def otherParameter = formalParameters[j]
                    if (currentParameter.name == otherParameter.name) {
                        helper.error("Duplicate parameter name ${currentParameter.name}.", publicinterfaceDef, CSDeclPackage.Literals.PUBLICINTERFACE_DEF__NAME, ValidationMessageAcceptor.INSIGNIFICANT_INDEX)
                    }
                }
            }
        }
    }

    @Check
    def checkProtDeclaration(ProtDeclaration protDeclaration) {
        if (protDeclaration.PubliicIntractionpointare.isEmpty()) {
            helper.error("A port declaration must have at least one public interface definition.", protDeclaration, CSDeclPackage.Literals.PROT_DECLARATION__PORTTYPE)
        } else {
            def names = HashSet<String>()
            for (publicInterfaceDef : protDeclaration.PubliicIntractionpointare) {
                if (names.contains(publicInterfaceDef.Name)) {
                    helper.error("Duplicate interface name ${publicInterfaceDef.Name}.", publicInterfaceDef, CSDeclPackage.Literals.PUBLICINTERFACE_DEF__NAME, ValidationMessageAcceptor.INSIGNIFICANT_INDEX)
                }
                names.add(publicInterfaceDef.Name)
            }
        }
    }

    @Check
    def checkCColActions(CColActions cColActions) {
        if (cColActions.methodBody.isEmpty()) {
            helper.warning("This collaborative action has no functionality.", cColActions, CSDeclPackage.Literals.CCOL_ACTIONS__NAME)
        }
    }

    @Check
    def checkAttachCSs(AttachCSs attachCSs) {
        if (attachCSs.attachParam1.type instanceof PortDecl) {
            def portDeclaration = attachCSs.attachParam1.type as PortDecl
            if (portDeclaration.PubliicIntractionpointare.filter[interface | interface.Name == attachCSs.attachParam1.ID].isEmpty()) {
                helper.error("Interface ${attachCSs.attachParam1.ID} does not exist in port {portDeclaration.portName}.", attachCSs, CSDeclPackage.Literals.ATTACH_CS__ATTACHPARAM1)
            }
        } else if (attachCSs.attachParam1.type instanceof TraitDeclaration) {
            def traitDeclaration = attachCSs.attachParam1.type as TraitDeclaration
            if (traitDeclaration.roles.filter[role | role.Name == attachCSs.attachParam1.ID].isEmpty()) {
                helper.error("Role ${attachCSs.attachParam1.ID} does not exist in trait ${traitDeclaration.name}.", attachCSs, CSDeclPackage.Literals.ATTACH_CS__ATTACHPARAM1)
            }
        } else {
            helper.error("Type ${attachCSs.attachParam1.type} is not supported as an attachment parameter.", attachCSs, CSDeclPackage.Literals.ATTACH_CS__ATTACHPARAM1)
        }
    }
}



class MedDeclValidator {

    @Check
    def checkMedRoleDeclarationNameUnique(MedDecl medDecl) {
        medDecl.MedRoles.filter(r | r.name != null).groupBy[r.name].forEach[k, v |
            if (v.size > 1) {
                error("Duplicate MedRoleDeclaration name '" + k + "'", v.head, null, "uniqueName")
            }
        ]
    }
    
    @Check
    def checkPublicInteractionPointDefined(MedRoleDeclaration medRoleDecl) {
        if (medRoleDecl.PublicInteractionPoint == null) {
            error("No PublicInteractionPoint defined for MedRoleDeclaration", medRoleDecl, null, "publicInterface")
        }
    }
    
}


class BsArchDeclValidator extends AbstractBsArchDeclValidator {

    @Check
    def checkGlobalPropertiesType(GlobalProperties globalProps) {
        // Check that the defined property type is valid
        val propertyType = globalProps.PropertyType.first().name
        if (!isValidType(propertyType)) {
            error("Invalid property type: " + propertyType, globalProps, 
                  BsArchDeclPackage.Literals.GLOBAL_PROPERTIES__PROPERTY_TYPE, 
                  ValidationMessageAcceptor::INSIGNIFICANT_INDEX)
        }
    }

    def isValidType(String type) {
        // Check if the given type is a valid type
        return ['string', 'int', 'float', 'boolean', 'type', 'void'].contains(type)
    }
}






