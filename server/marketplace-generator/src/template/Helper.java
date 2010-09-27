/*******************************************************************************
 * Copyright (c) 2010 Tom Seidel, Remus Software
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *
 * Contributors:
 *     Tom Seidel - initial API and implementation
 *******************************************************************************/
package template;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public class Helper {

	public static List<String> interfaceNames = new ArrayList<String>();

	public static String typeName(EAttribute attribute) {
		EDataType name = attribute.getEAttributeType();
		return name.getInstanceTypeName();
	}

	public static String firstToUpper(String string) {
		return string.substring(0, 1).toUpperCase() + string.substring(1);
	}

	public static EClass getEntityClass(EObject entity) {
		EReference eContainmentFeature = entity.eContainmentFeature();
		return eContainmentFeature.getEReferenceType();
	}

	public static String substring(String str, int start, int end) {
		return str.substring(start, end);
	}

	public static String removeRightChar(String str, Integer count) {
		if (str == null) {
			return "";
		}
		return str.substring(0, str.length() - count);
	}

	public static String annotation2String(EAnnotation attribute) {
		if (attribute.getSource().startsWith("javax.xml")) {
			return "";
		}
		if (attribute.getSource().indexOf("OneToMany") != -1
				|| attribute.getSource().indexOf("OneToOne") != -1
				|| attribute.getSource().indexOf("ManyToMany") != -1) {
			EObject eContainer = attribute.eContainer();
			if (eContainer instanceof EReference) {
				EReference eOpposite = ((EReference) eContainer).getEOpposite();
				if (eOpposite != null) {
					boolean skipMap = attribute.getDetails().containsKey(
							"remus.skipMapping");
					if (!skipMap) {
						attribute.getDetails().put("mappedBy",
								"\"" + eOpposite.getName() + "\"");
					}
				}
			}

		}
		if (attribute.getSource().indexOf("OneToOne") != -1
				&& attribute.eContainer() instanceof EStructuralFeature) {
			attribute.getDetails()
					.put("optional",
							String.valueOf(((EStructuralFeature) attribute
									.eContainer()).getLowerBound() == 0));
		}
		EMap<String, String> details = attribute.getDetails();
		Set<String> keySet = new HashSet<String>(details.keySet());

		for (String string : keySet) {
			if (string.startsWith("remus")) {
				attribute.getDetails().remove(string);
			}
		}

		return _annotation2String(attribute);
	}

	public static String jaxbannotation2String(EAnnotation attribute) {
		if (attribute.getSource().startsWith("javax.xml")) {
			if (attribute.getSource().indexOf("OneToMany") != -1
					|| attribute.getSource().indexOf("OneToOne") != -1
					|| attribute.getSource().indexOf("ManyToMany") != -1) {
				EObject eContainer = attribute.eContainer();
				if (eContainer instanceof EReference) {
					EReference eOpposite = ((EReference) eContainer)
							.getEOpposite();
					if (eOpposite != null) {
						boolean skipMap = attribute.getDetails().containsKey(
								"remus.skipMapping");
						if (!skipMap) {
							attribute.getDetails().put("mappedBy",
									"\"" + eOpposite.getName() + "\"");
						}
					}
				}

			}
			if (attribute.getSource().indexOf("OneToOne") != -1
					&& attribute.eContainer() instanceof EStructuralFeature) {
				attribute.getDetails().put(
						"optional",
						String.valueOf(((EStructuralFeature) attribute
								.eContainer()).getLowerBound() == 0));
			}
			EMap<String, String> details = attribute.getDetails();
			Set<String> keySet = new HashSet<String>(details.keySet());

			for (String string : keySet) {
				if (string.startsWith("remus")) {
					attribute.getDetails().remove(string);
				}
			}

			return _annotation2String(attribute);

		}
		return "";
	}

	public static String _annotation2String(EAnnotation annotation) {
		String source = "@" + annotation.getSource();
		if (annotation.getDetails().size() > 0) {
			List<String> list = new ArrayList<String>();
			Set<String> keySet = annotation.getDetails().keySet();
			for (String string : keySet) {
				String string2 = annotation.getDetails().get(string);
				if (string2 != null) {
					list.add(string + " = " + string2);
				} else {
					list.add(string);
				}
			}
			source += "(" + org.apache.commons.lang.StringUtils.join(list, ",")
					+ ")";
		}
		return source;
	}

	public static String buildExtends(EClass clazz) {
		EList<EClass> eAllSuperTypes = clazz.getEAllSuperTypes();
		if (eAllSuperTypes.size() > 0) {
			return " extends "
					+ eAllSuperTypes.get(eAllSuperTypes.size() - 1).getName()
					+ " ";
		}
		return "";
	}

	public boolean isUniqueColumn(EAttribute att) {
		EList<EAnnotation> eAnnotations = att.getEAnnotations();
		for (EAnnotation eAnnotation : eAnnotations) {
			if ("javax.persistence.Column".equals(eAnnotation.getSource())) {
				EMap<String, String> details = eAnnotation.getDetails();
				if (details.containsKey("unique")) {
					if ("true".equals(details.get("unique"))) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static String calculateParameter(List listofParameters) {
		List<String> list = new ArrayList<String>();
		for (Object eAttribute : listofParameters) {
			list.add(((EAttribute) eAttribute).getEType().getInstanceTypeName()
					+ " " + ((EAttribute) eAttribute).getName());
		}
		return "(" + org.apache.commons.lang.StringUtils.join(list, ",") + ")";
	}

	public static boolean hasFKFunction(EReference reference) {
		if (reference.getEOpposite() != null) {
			EClass eContainingClass = reference.getEOpposite()
					.getEReferenceType();

			EList<EAttribute> eAllAttributes = eContainingClass
					.getEAllAttributes();
			for (EAttribute eAttribute : eAllAttributes) {
				if (eAttribute.isID()) {
					return true;
				}
			}
		}
		return false;
	}

	public static String getFKFunctionName(EReference reference) {
		EClass eContainingClass = reference.getEOpposite().getEReferenceType();
		EList<EAttribute> eAllAttributes = eContainingClass.getEAllAttributes();
		for (EAttribute eAttribute : eAllAttributes) {
			if (eAttribute.isID()) {
				return "findBy" + firstToUpper(reference.getName())
						+ firstToUpper(eAttribute.getName());
			}
		}
		return null;

	}

	public static String getFKFunctionParameter(EReference reference) {
		EClass eContainingClass = reference.getEReferenceType();
		EList<EAttribute> eAllAttributes = eContainingClass.getEAllAttributes();
		for (EAttribute eAttribute : eAllAttributes) {
			if (eAttribute.isID()) {
				return eAttribute.getEAttributeType().getInstanceTypeName()
						+ " id";
			}
		}
		return null;

	}

	public String getPKColumnFromFKReference(EReference reference) {
		EClass eContainingClass = reference.getEOpposite().getEReferenceType();
		EList<EAttribute> eAllAttributes = eContainingClass.getEAllAttributes();
		for (EAttribute eAttribute : eAllAttributes) {
			if (eAttribute.isID()) {
				return eAttribute.getName();
			}
		}
		return null;
	}

	public boolean targetIsOneToOne(EReference reference) {
		EList<EAnnotation> eAnnotations = reference.getEOpposite()
				.getEAnnotations();
		for (EAnnotation eAnnotation : eAnnotations) {
			if (eAnnotation.getSource().indexOf("OneToOne") != -1
					|| eAnnotation.getSource().indexOf("ManyToOne") != -1) {
				return true;
			}
		}
		return false;
	}

	public static String addInterfaceName(String name) {
		interfaceNames.add(name);
		return null;
	}

	public static String clearInterfaceName() {
		interfaceNames.clear();
		return null;
	}

	public static List<String> getInterfaceNames() {
		return interfaceNames;
	}

	public boolean hasLazyFunction(EReference ref) {
		EList<EAnnotation> eAnnotations = ref.getEAnnotations();
		for (EAnnotation eAnnotation : eAnnotations) {
			EMap<String, String> details = eAnnotation.getDetails();
			Collection<String> values = details.values();
			for (String string : values) {
				if ("javax.persistence.FetchType.EAGER".equals(string)) {
					return false;
				}
			}
		}
		return true;
	}

	public EAttribute getIdAttribute(EClass clazz) {
		EList<EAttribute> eAllAttributes = clazz.getEAllAttributes();
		for (EAttribute eAttribute : eAllAttributes) {
			if (eAttribute.isID()) {
				return eAttribute;
			}
		}
		return null;
	}

	// //////////////FORMS

	public String getTypeIdentifierByReference(String className,
			String boundClass) {
		if (className != null && className.equals("form::StringProperty")) {
			return "String";
		}
		if (className != null && className.equals("form::IntegerProperty")) {
			return "Integer";
		}
		if (className != null && className.equals("form::DateProperty")) {
			return "java.util.Date";
		}
		if (className != null && className.equals("form::FloatProperty")) {
			return "Float";
		}
		if (className != null && className.equals("form::BooleanProperty")) {
			return "Boolean";
		}
		if (className.equals("form::CustomMultipleProperty")) {
			return "java.util.Set<" + boundClass + ">";
		}
		if (className.equals("form::CustomProperty")) {
			return boundClass;
		}
		return null;
	}

	public String buildValidationConstruct(String className, String property) {
		if (className.equals("form::NotEmptyValidation")) {
			return "org.apache.commons.lang.StringUtils.isEmpty(data."
					+ property + ")";
		}
		return "false";
	}

}
