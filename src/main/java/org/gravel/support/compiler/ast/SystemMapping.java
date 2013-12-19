package org.gravel.support.compiler.ast;

/*
	This file is automatically generated from typed smalltalk source. Do not edit by hand.
	(C) AG5.com
*/

import java.math.BigDecimal;
import java.math.BigInteger;
import org.gravel.support.jvm.NonLocalReturn;
import org.gravel.support.compiler.ast.AbstractMapping;
import org.gravel.support.compiler.ast.AbstractMapping.AbstractMapping_Factory;
import org.gravel.support.compiler.ast.SystemNode;
import org.gravel.support.compiler.ast.SystemMappingCompilerTools;
import java.util.Map;
import org.gravel.support.compiler.ast.ClassMapping;
import org.gravel.support.compiler.ast.Reference;
import org.gravel.support.compiler.ast.AbsoluteReference;
import org.gravel.support.compiler.ast.SelectorConverter;
import org.gravel.support.compiler.ast.SystemDefinitionNode;
import org.gravel.support.compiler.ast.SystemDiff;
import org.gravel.support.compiler.ast.SystemMappingUpdater;
import org.gravel.support.compiler.ast.ClassDiff;
import org.gravel.support.compiler.ast.SharedDeclarationNode;
import org.gravel.support.compiler.ast.Node;
import org.gravel.support.compiler.jvm.JVMDefinedObjectType;
import org.gravel.support.compiler.ast.BlockNode;
import org.gravel.support.compiler.jvm.BlockInnerClass;
import org.gravel.support.compiler.jvm.JVMClass;
import org.gravel.support.compiler.ast.IntermediateNodeRewriter;
import org.gravel.support.compiler.ast.NonLocalTempWritesToHolderConverter;
import org.gravel.support.compiler.ast.VariableAccessToFieldAccessConverter;
import org.gravel.support.compiler.ast.NilLiteralNode;
import org.gravel.support.compiler.jvm.JVMVariable;
import org.gravel.support.compiler.jvm.JVMClassCompiler;
import org.gravel.support.compiler.ast.ClassDescriptionNode;
import org.gravel.support.compiler.ast.ClassNode;
import org.gravel.support.compiler.ast.Expression;
import java.util.HashMap;
import java.util.Date;
import org.gravel.support.compiler.ast.AbstractMethodMapping;
import org.gravel.support.compiler.ast.MethodMapping;
import org.gravel.support.compiler.ast.NamespaceNode;
import java.util.Map;
import java.util.Map.*;
import java.util.Set;
import org.gravel.support.compiler.ast.AnonymousMethodMapping;
import org.gravel.support.compiler.ast.VariableDeclarationNode;
import org.gravel.support.compiler.ast.MethodNode;
import org.gravel.support.compiler.ast.EmptyTraitUsageNode;
import org.gravel.support.compiler.ast.IdentityClassPartMapping;
import org.gravel.support.compiler.ast.ExtensionClassPartMapping;
import org.gravel.support.compiler.ast.InstVarMapping;
import org.gravel.support.compiler.ast.PackageNodeMerger;

public class SystemMapping extends AbstractMapping implements Cloneable {

	public static SystemMapping_Factory factory = new SystemMapping_Factory();

	Map<Class, ClassMapping> _classMappingsByJavaClass;

	Map<Reference, ClassMapping> _classMappingsByReference;

	SystemMappingCompilerTools _compilerTools;

	ClassMapping _nilClassMapping;

	SelectorConverter _selectorConverter;

	Map<AbsoluteReference, org.gravel.support.jvm.runtime.AlmostFinalValue> _singletonHolders;

	SystemDefinitionNode _systemDefinitionNode;

	SystemNode _systemNode;

	public static class SystemMapping_Factory extends AbstractMapping_Factory {

		public SystemMapping basicNew() {
			SystemMapping newInstance = new SystemMapping();
			newInstance.initialize();
			return newInstance;
		}

		public SystemMapping systemNode_compilerTools_(final SystemNode _aSystemNode, final SystemMappingCompilerTools _aMockSystemMappingCompilerTools) {
			return ((SystemMapping) this.basicNew().initializeSystemNode_compilerTools_(_aSystemNode, _aMockSystemMappingCompilerTools));
		}
	}

	static public SystemMapping _systemNode_compilerTools_(Object receiver, final SystemNode _aSystemNode, final SystemMappingCompilerTools _aMockSystemMappingCompilerTools) {
		return factory.systemNode_compilerTools_(_aSystemNode, _aMockSystemMappingCompilerTools);
	}

	public SystemMapping addClassMapping_(final ClassMapping _aClassMapping) {
		final Class _identityClass;
		_identityClass = _aClassMapping.identityClass();
		if (_identityClass == null) {
			_nilClassMapping = _aClassMapping;
		} else {
			_classMappingsByJavaClass.put(_identityClass, _aClassMapping);
		}
		_classMappingsByReference.put(_aClassMapping.classNode().reference(), _aClassMapping);
		_systemNode = _systemNode.withClassDescriptionNode_(_aClassMapping.classNode());
		return this;
	}

	public SystemMapping applyDiff_(final SystemDiff _aSystemDiff) {
		final SystemMappingUpdater[] _updater;
		_updater = new SystemMappingUpdater[1];
		_updater[0] = this.newSystemMappingUpdater();
		for (final ClassDiff _each : _aSystemDiff.classDiffs()) {
			_updater[0].visit_(_each);
		}
		_updater[0].setNamespaceNodes_(_aSystemDiff.namespaces());
		_updater[0].link();
		return this;
	}

	public ClassMapping bestClassMappingFor_(final Class _receiverClass) {
		final ClassMapping[] _best;
		_best = new ClassMapping[1];
		_best[0] = null;
		for (final ClassMapping _cm : _classMappingsByReference.values()) {
			if (_compilerTools.isAssignable_from_(_cm.identityClass(), _receiverClass)) {
				if ((_best[0]) == null) {
					_best[0] = _cm;
				} else {
					if (_compilerTools.isAssignable_from_(_best[0].identityClass(), _cm.identityClass())) {
						_best[0] = _cm;
					}
				}
			}
		}
		return _best[0];
	}

	public ClassMapping classMappingAtJavaClass_ifAbsent_(final Class _aClass, final org.gravel.support.jvm.Block0<ClassMapping> _absentBlock) {
		if (_aClass == null) {
			return _nilClassMapping;
		}
		return org.gravel.support.jvm.DictionaryExtensions.at_ifAbsent_(_classMappingsByJavaClass, _aClass, _absentBlock);
	}

	public ClassMapping classMappingAtReference_(final Reference _aReference) {
		return this.classMappingAtReference_ifAbsent_(_aReference, ((org.gravel.support.jvm.Block0<ClassMapping>) (new org.gravel.support.jvm.Block0<ClassMapping>() {

			@Override
			public ClassMapping value() {
				throw new RuntimeException("Cannot find: " + _aReference.toString());
			}
		})));
	}

	public ClassMapping classMappingAtReference_ifAbsent_(final Reference _aReference, final org.gravel.support.jvm.Block0<ClassMapping> _absentBlock) {
		return org.gravel.support.jvm.DictionaryExtensions.at_ifAbsent_(_classMappingsByReference, _aReference, _absentBlock);
	}

	public ClassMapping classMappingAt_(final String _aString) {
		return this.classMappingAt_ifAbsent_(_aString, ((org.gravel.support.jvm.Block0<ClassMapping>) (new org.gravel.support.jvm.Block0<ClassMapping>() {

			@Override
			public ClassMapping value() {
				throw new RuntimeException("Cannot find: " + _aString);
			}
		})));
	}

	public ClassMapping classMappingAt_ifAbsent_(final String _aString, final org.gravel.support.jvm.Block0<ClassMapping> _absentBlock) {
		return this.classMappingAtReference_ifAbsent_(AbsoluteReference.factory.path_(org.gravel.support.jvm.ArrayExtensions.collect_(org.gravel.support.jvm.StringExtensions.tokensBasedOn_(_aString, '.'), ((org.gravel.support.jvm.Block1<org.gravel.core.Symbol, String>) (new org.gravel.support.jvm.Block1<org.gravel.core.Symbol, String>() {

			@Override
			public org.gravel.core.Symbol value_(final String _each) {
				return (org.gravel.core.Symbol) org.gravel.core.Symbol.value(_each);
			}
		})))), _absentBlock);
	}

	public ClassMapping classMappingForJavaClass_(final Class _receiverClass) {
		return this.classMappingAtJavaClass_ifAbsent_(_receiverClass, ((org.gravel.support.jvm.Block0<ClassMapping>) (new org.gravel.support.jvm.Block0<ClassMapping>() {

			@Override
			public ClassMapping value() {
				final ClassMapping _newClassMapping;
				_newClassMapping = SystemMapping.this.newClassMappingForJavaClass_(_receiverClass);
				_classMappingsByJavaClass.put(_receiverClass, _newClassMapping);
				return (ClassMapping) _newClassMapping;
			}
		})));
	}

	public SystemMapping classMappingsDo_(final org.gravel.support.jvm.Block1<Object, ClassMapping> _aBlock) {
		for (final ClassMapping _temp1 : _classMappingsByReference.values()) {
			_aBlock.value_(_temp1);
		}
		return this;
	}

	public org.gravel.support.jvm.runtime.AlmostFinalValue classSharedSingletonHolderAtReference_ifAbsent_(final AbsoluteReference _reference, final org.gravel.support.jvm.Block0<org.gravel.support.jvm.runtime.AlmostFinalValue> _aBlock) {
		final Object _temp2 = new Object();
		try {
			org.gravel.support.jvm.runtime.AlmostFinalValue _temp1 = _singletonHolders.get(_reference);
			if (_temp1 == null) {
				_temp1 = ((org.gravel.support.jvm.runtime.AlmostFinalValue) org.gravel.support.jvm.DictionaryExtensions.at_ifAbsent_(_singletonHolders, _reference.namespace().namespace().$slash$(_reference.name()), ((org.gravel.support.jvm.Block0<org.gravel.support.jvm.runtime.AlmostFinalValue>) (new org.gravel.support.jvm.Block0<org.gravel.support.jvm.runtime.AlmostFinalValue>() {

					@Override
					public org.gravel.support.jvm.runtime.AlmostFinalValue value() {
						final ClassMapping _cm;
						final AbsoluteReference _superclassReference;
						final SharedDeclarationNode _sharedVariable;
						_cm = ((ClassMapping) org.gravel.support.jvm.DictionaryExtensions.at_ifAbsent_(_classMappingsByReference, _reference.namespace(), ((org.gravel.support.jvm.Block0<ClassMapping>) (new org.gravel.support.jvm.Block0<ClassMapping>() {

							@Override
							public ClassMapping value() {
								throw new NonLocalReturn(_aBlock.value(), _temp2);
							}
						}))));
						_sharedVariable = _cm.classNode().meta().sharedVariableAt_ifAbsent_(_reference.name().asString(), new org.gravel.support.jvm.Block0<SharedDeclarationNode>() {

							@Override
							public SharedDeclarationNode value() {
								return (SharedDeclarationNode) null;
							}
						});
						if (_sharedVariable != null) {
							throw new RuntimeException("Shared not initialized: " + _reference.toString());
						}
						_superclassReference = ((AbsoluteReference) _cm.superclassReference());
						if (_superclassReference == null) {
							throw new NonLocalReturn(_aBlock.value(), _temp2);
						}
						return (org.gravel.support.jvm.runtime.AlmostFinalValue) SystemMapping.this.classSharedSingletonHolderAtReference_ifAbsent_(_superclassReference.$slash$(_reference.name()), _aBlock);
					}
				}))));
			}
			return ((org.gravel.support.jvm.runtime.AlmostFinalValue) _temp1);
		} catch (NonLocalReturn nlr) {
			if (nlr.marker == _temp2) {
				return (org.gravel.support.jvm.runtime.AlmostFinalValue) nlr.returnValue;
			} else {
				throw nlr;
			}
		}
	}

	public Class compileExpression_reference_(final Node _anExpression, final Reference _aReference) {
		final JVMDefinedObjectType _ownerType;
		final BlockNode _fieldAccessed;
		final BlockInnerClass _aBlockInnerClass;
		final BlockNode _intermediate;
		final BlockNode _holderized;
		final JVMClass _jvmClass;
		_intermediate = ((BlockNode) IntermediateNodeRewriter.factory.visit_(BlockNode.factory.expression_(_anExpression)));
		_holderized = ((BlockNode) NonLocalTempWritesToHolderConverter.factory.visit_(_intermediate));
		_fieldAccessed = ((BlockNode) VariableAccessToFieldAccessConverter.factory.instVarNames_owner_ownerReference_(new String[] {}, NilLiteralNode.factory.basicNew(), _aReference).visit_(_holderized));
		_ownerType = JVMDefinedObjectType.factory.dottedClassName_("Expression$" + _compilerTools.nextExtensionPostfix());
		_aBlockInnerClass = BlockInnerClass.factory.ownerType_blockNode_copiedVariables_(_ownerType, _fieldAccessed, new JVMVariable[] {});
		_jvmClass = JVMClassCompiler.factory.classDescriptionNode_systemNode_systemMappingUpdater_isStatic_(null, _systemNode, this.newSystemMappingUpdater(), false).compileBlock_(_aBlockInnerClass);
		return _compilerTools.writeClass_(_jvmClass);
	}

	public SystemMappingCompilerTools compilerTools() {
		return _compilerTools;
	}

	public SystemMapping copy() {
		try {
			SystemMapping _temp1 = (SystemMapping) this.clone();
			_temp1.postCopy();
			return _temp1;
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	public ClassDescriptionNode definitionClassNodeAt_(final Reference _reference) {
		return this.definitionClassNodeAt_ifAbsent_(_reference, ((org.gravel.support.jvm.Block0<ClassDescriptionNode>) (new org.gravel.support.jvm.Block0<ClassDescriptionNode>() {

			@Override
			public ClassDescriptionNode value() {
				throw new RuntimeException("Cannot find: " + _reference.toString());
			}
		})));
	}

	public ClassDescriptionNode definitionClassNodeAt_ifAbsent_(final Reference _reference, final org.gravel.support.jvm.Block0<ClassDescriptionNode> _aBlock) {
		final Object _temp1 = new Object();
		try {
			if (_reference.isMeta()) {
				return ((ClassNode) SystemMapping.this.definitionClassNodeAt_ifAbsent_(_reference.nonmeta(), ((org.gravel.support.jvm.Block0<ClassDescriptionNode>) (new org.gravel.support.jvm.Block0<ClassDescriptionNode>() {

					@Override
					public ClassDescriptionNode value() {
						throw new NonLocalReturn(_aBlock.value(), _temp1);
					}
				})))).metaclassNode();
			}
			return _systemDefinitionNode.classNodeAt_ifAbsent_(_reference, ((org.gravel.support.jvm.Block0<ClassNode>) (new org.gravel.support.jvm.Block0<ClassNode>() {

				@Override
				public ClassNode value() {
					throw new NonLocalReturn(_aBlock.value(), _temp1);
				}
			})));
		} catch (NonLocalReturn nlr) {
			if (nlr.marker == _temp1) {
				return (ClassDescriptionNode) nlr.returnValue;
			} else {
				throw nlr;
			}
		}
	}

	public ClassDescriptionNode definitionOrObsoleteClassNodeAt_(final Reference _reference) {
		return this.definitionOrObsoleteClassNodeAt_ifAbsent_(_reference, ((org.gravel.support.jvm.Block0<ClassDescriptionNode>) (new org.gravel.support.jvm.Block0<ClassDescriptionNode>() {

			@Override
			public ClassDescriptionNode value() {
				throw new RuntimeException("Cannot find: " + _reference.toString());
			}
		})));
	}

	public ClassDescriptionNode definitionOrObsoleteClassNodeAt_ifAbsent_(final Reference _reference, final org.gravel.support.jvm.Block0<ClassDescriptionNode> _aBlock) {
		return this.definitionClassNodeAt_ifAbsent_(_reference, ((org.gravel.support.jvm.Block0<ClassDescriptionNode>) (new org.gravel.support.jvm.Block0<ClassDescriptionNode>() {

			@Override
			public ClassDescriptionNode value() {
				return (ClassDescriptionNode) SystemMapping.this.obsoleteClassNodeAt_ifAbsent_(_reference, ((org.gravel.support.jvm.Block0<ClassDescriptionNode>) (new org.gravel.support.jvm.Block0<ClassDescriptionNode>() {

					@Override
					public ClassDescriptionNode value() {
						throw new RuntimeException("Cannot find: " + _reference.toString());
					}
				})));
			}
		})));
	}

	public Object evaluateExpression_reference_(final Expression _anExpression, final AbsoluteReference _aReference) {
		final Class _cl;
		_cl = this.compileExpression_reference_(_anExpression, _aReference);
		return _compilerTools.evaluateBlock0Class_(_cl);
	}

	public SystemMapping_Factory factory() {
		return factory;
	}

	@Override
	public SystemMapping initialize() {
		_classMappingsByJavaClass = new java.util.HashMap<Class, ClassMapping>();
		_classMappingsByReference = new java.util.HashMap<Reference, ClassMapping>();
		_singletonHolders = new java.util.HashMap<AbsoluteReference, org.gravel.support.jvm.runtime.AlmostFinalValue>();
		_selectorConverter = SelectorConverter.factory.basicNew();
		return this;
	}

	public SystemMapping initializeSystemNode_compilerTools_(final SystemNode _aSystemNode, final SystemMappingCompilerTools _aMockSystemMappingCompilerTools) {
		_systemNode = _aSystemNode;
		_compilerTools = _aMockSystemMappingCompilerTools;
		this.initialize();
		return this;
	}

	public SystemMapping log_(final String _aString) {
		return this;
	}

	public SystemMapping log_while_(final String _aString, final org.gravel.support.jvm.Block0<Object> _aBlock) {
		final int _ms;
		this.log_(_aString + "...");
		long _temp1 = new Date().getTime();
		_aBlock.value();
		long _temp2 = new Date().getTime();
		_ms = (int) (_temp2 - _temp1);
		this.log_(_aString + " Done in " + "" + _ms + " ms");
		return this;
	}

	public java.lang.invoke.MethodHandle methodHandleForNil_(final String _methodName) {
		final org.gravel.core.Symbol _sel;
		final ClassMapping _classMapping;
		_classMapping = this.nilClassMapping();
		_sel = _selectorConverter.functionNameAsSelector_(_methodName);
		return this.methodHandleFrom_selector_(_classMapping, _sel);
	}

	public java.lang.invoke.MethodHandle methodHandleFor_methodName_(final Class _receiverClass, final String _methodName) {
		final org.gravel.core.Symbol _sel;
		final ClassMapping _classMapping;
		_classMapping = this.classMappingForJavaClass_(_receiverClass);
		_sel = _selectorConverter.functionNameAsSelector_(_methodName);
		return this.methodHandleFrom_selector_(_classMapping, _sel);
	}

	public java.lang.invoke.MethodHandle methodHandleFrom_selector_(final ClassMapping _classMapping, final org.gravel.core.Symbol _sel) {
		final AbstractMethodMapping _methodMapping;
		_methodMapping = this.methodMappingFrom_selector_(_classMapping, _sel);
		if (_methodMapping == null) {
			return null;
		}
		return _methodMapping.methodHandle();
	}

	public MethodMapping methodMappingForNil_(final String _methodName) {
		final org.gravel.core.Symbol _sel;
		final ClassMapping _classMapping;
		_classMapping = this.nilClassMapping();
		_sel = _selectorConverter.functionNameAsSelector_(_methodName);
		return ((MethodMapping) this.methodMappingFrom_selector_(_classMapping, _sel));
	}

	public MethodMapping methodMappingFor_methodName_(final Class _receiverClass, final String _methodName) {
		final org.gravel.core.Symbol _sel;
		final ClassMapping _classMapping;
		_classMapping = this.classMappingForJavaClass_(_receiverClass);
		_sel = _selectorConverter.functionNameAsSelector_(_methodName);
		return ((MethodMapping) this.methodMappingFrom_selector_(_classMapping, _sel));
	}

	public AbstractMethodMapping methodMappingFrom_selector_(final ClassMapping _classMapping, final org.gravel.core.Symbol _sel) {
		return _classMapping.methodMappingAt_ifAbsent_(_sel, ((org.gravel.support.jvm.Block0<AbstractMethodMapping>) (new org.gravel.support.jvm.Block0<AbstractMethodMapping>() {

			@Override
			public AbstractMethodMapping value() {
				final Reference _superclassReference;
				AbstractMethodMapping _mapping;
				_superclassReference = _classMapping.superclassReferenceForMethodLookup();
				if (_superclassReference == null) {
					_mapping = null;
				} else {
					_mapping = SystemMapping.this.methodMappingFrom_selector_(SystemMapping.this.classMappingAtReference_(_superclassReference), _sel);
				}
				return (AbstractMethodMapping) _mapping;
			}
		})));
	}

	public org.gravel.support.jvm.runtime.AlmostFinalValue namespacedSingletonHolderAtReference_ifAbsent_(final AbsoluteReference _reference, final org.gravel.support.jvm.Block0<org.gravel.support.jvm.runtime.AlmostFinalValue> _aBlock) {
		final Object _temp1 = new Object();
		try {
			final NamespaceNode _nsNode;
			_nsNode = _systemNode.namespaceNodeAt_ifAbsent_(_reference.namespace().namespace(), ((org.gravel.support.jvm.Block0<NamespaceNode>) (new org.gravel.support.jvm.Block0<NamespaceNode>() {

				@Override
				public NamespaceNode value() {
					return (NamespaceNode) null;
				}
			})));
			if (_nsNode != null) {
				_nsNode.allImportsIn_do_(_systemNode, new org.gravel.support.jvm.Block1<Object, NamespaceNode>() {

					@Override
					public Object value_(final NamespaceNode _importNS) {
						final org.gravel.support.jvm.runtime.AlmostFinalValue _sh;
						final SharedDeclarationNode _shared;
						_shared = _importNS.sharedVariableAt_ifAbsent_(_reference.name().asString(), ((org.gravel.support.jvm.Block0<SharedDeclarationNode>) (new org.gravel.support.jvm.Block0<SharedDeclarationNode>() {

							@Override
							public SharedDeclarationNode value() {
								return (SharedDeclarationNode) null;
							}
						})));
						if (_shared != null) {
							throw new NonLocalReturn(SystemMapping.this.singletonAtReference_initialize_(_importNS.reference().$slash$(_reference.name()), _shared), _temp1);
						}
						org.gravel.support.jvm.runtime.AlmostFinalValue _temp2 = _singletonHolders.get(_importNS.reference().$slash$(_reference.name()));
						_sh = ((org.gravel.support.jvm.runtime.AlmostFinalValue) _temp2);
						if (_sh != null) {
							throw new NonLocalReturn(_sh, _temp1);
						}
						return SystemMapping.this;
					}
				});
			}
			return _aBlock.value();
		} catch (NonLocalReturn nlr) {
			if (nlr.marker == _temp1) {
				return (org.gravel.support.jvm.runtime.AlmostFinalValue) nlr.returnValue;
			} else {
				throw nlr;
			}
		}
	}

	public SystemMapping namespaceGlobalsAndInitializersDo_(final org.gravel.support.jvm.Block2<Object, AbsoluteReference, SharedDeclarationNode> _aBlock) {
		for (final Map.Entry<Reference, NamespaceNode> _temp1 : _systemNode.namespaceNodes().entrySet()) {
			Reference _reference = _temp1.getKey();
			NamespaceNode _namespaceNode = _temp1.getValue();
			for (final SharedDeclarationNode _sharedVariable : _namespaceNode.sharedVariables()) {
				_aBlock.value_value_(((AbsoluteReference) _reference).$slash$(org.gravel.core.Symbol.value(_sharedVariable.name())), _sharedVariable);
			}
		}
		return this;
	}

	public ClassMapping newClassMappingForJavaClass_(final Class _receiverClass) {
		final Map<org.gravel.core.Symbol, AbstractMethodMapping>[] _methodMappings;
		final ClassNode _classNode;
		final ClassMapping _superMapping;
		final java.util.Set<org.gravel.core.Symbol>[] _allSelectors;
		_allSelectors = new java.util.Set[1];
		_methodMappings = new Map[1];
		_superMapping = this.bestClassMappingFor_(_receiverClass);
		_allSelectors[0] = _superMapping.allSelectorsIn_(this);
		_methodMappings[0] = new java.util.HashMap<org.gravel.core.Symbol, AbstractMethodMapping>();
		_compilerTools.methodNamesIn_do_(_receiverClass, new org.gravel.support.jvm.Block1<Object, String>() {

			@Override
			public Object value_(final String _methodName) {
				final org.gravel.core.Symbol _sel;
				java.lang.invoke.MethodHandle _methodHandle;
				_sel = _selectorConverter.functionNameAsSelector_(_methodName);
				if (_allSelectors[0].contains(_sel)) {
					_methodHandle = _compilerTools.methodHandleAt_numArgs_in_identityClass_isStatic_(_methodName, _sel.numArgs(), _receiverClass, _receiverClass, false);
					return _methodMappings[0].put(_sel, AnonymousMethodMapping.factory.methodHandle_definingClass_(_methodHandle, _receiverClass));
				}
				return SystemMapping.this;
			}
		});
		_classNode = ClassNode.factory.name_superclassPath_properties_instVars_classInstVars_sharedVariables_methods_classMethods_namespace_isExtension_isTrait_traitUsage_classTraitUsage_(_superMapping.classNode().name(), _superMapping.reference().toString(), new java.util.HashMap<String, String>(), new VariableDeclarationNode[] {}, new VariableDeclarationNode[] {}, new SharedDeclarationNode[] {}, new MethodNode[] {}, new MethodNode[] {}, _superMapping.classNode().namespace(), false, false, EmptyTraitUsageNode.factory.basicNew(), EmptyTraitUsageNode.factory.basicNew());
		return ClassMapping.factory.identityMapping_extensions_instVarMappings_classNode_(IdentityClassPartMapping.factory.javaClass_isGenerated_(_receiverClass, false).withMethodMappings_(_methodMappings[0]), new ExtensionClassPartMapping[] {}, new java.util.HashMap<String, InstVarMapping>(), _classNode);
	}

	public SystemMappingUpdater newSystemMappingUpdater() {
		return SystemMappingUpdater.factory.systemMapping_compilerTools_(this, _compilerTools);
	}

	public ClassMapping nilClassMapping() {
		return _nilClassMapping;
	}

	public ClassDescriptionNode obsoleteClassNodeAt_ifAbsent_(final Reference _aReference, final org.gravel.support.jvm.Block0<ClassDescriptionNode> _absentBlock) {
		final Object _temp1 = new Object();
		try {
			return this.classMappingAtReference_ifAbsent_(_aReference, ((org.gravel.support.jvm.Block0<ClassMapping>) (new org.gravel.support.jvm.Block0<ClassMapping>() {

				@Override
				public ClassMapping value() {
					throw new NonLocalReturn(_absentBlock.value(), _temp1);
				}
			}))).classNode();
		} catch (NonLocalReturn nlr) {
			if (nlr.marker == _temp1) {
				return (ClassDescriptionNode) nlr.returnValue;
			} else {
				throw nlr;
			}
		}
	}

	public org.gravel.core.Symbol[] packageNames() {
		return _systemDefinitionNode.packageNames();
	}

	public org.gravel.support.jvm.runtime.AlmostFinalValue resolveSingletonHolder_(final AbsoluteReference _reference) {
		return this.resolveSingletonHolder_ifAbsent_(_reference, ((org.gravel.support.jvm.Block0<org.gravel.support.jvm.runtime.AlmostFinalValue>) (new org.gravel.support.jvm.Block0<org.gravel.support.jvm.runtime.AlmostFinalValue>() {

			@Override
			public org.gravel.support.jvm.runtime.AlmostFinalValue value() {
				throw new RuntimeException("Singleton not found:" + _reference.toString());
			}
		})));
	}

	public org.gravel.support.jvm.runtime.AlmostFinalValue resolveSingletonHolder_ifAbsent_(final AbsoluteReference _reference, final org.gravel.support.jvm.Block0<org.gravel.support.jvm.runtime.AlmostFinalValue> _aBlock) {
		return this.namespacedSingletonHolderAtReference_ifAbsent_(_reference, ((org.gravel.support.jvm.Block0<org.gravel.support.jvm.runtime.AlmostFinalValue>) (new org.gravel.support.jvm.Block0<org.gravel.support.jvm.runtime.AlmostFinalValue>() {

			@Override
			public org.gravel.support.jvm.runtime.AlmostFinalValue value() {
				return (org.gravel.support.jvm.runtime.AlmostFinalValue) SystemMapping.this.classSharedSingletonHolderAtReference_ifAbsent_(_reference, _aBlock);
			}
		})));
	}

	public SystemMapping setNamespaceNodes_(final Map<Reference, NamespaceNode> _aDictionary) {
		final Map<Reference, NamespaceNode>[] _dict;
		_dict = new Map[1];
		_dict[0] = new java.util.HashMap<Reference, NamespaceNode>();
		org.gravel.support.jvm.DictionaryExtensions.syncWith(_systemNode.namespaceNodes(), _aDictionary, new org.gravel.support.jvm.Block2<Object, NamespaceNode, NamespaceNode>() {

			@Override
			public Object value_value_(final NamespaceNode _old, final NamespaceNode _new) {
				return _dict[0].put(_new.reference(), _old.mergeWith_(_new));
			}
		}, new org.gravel.support.jvm.Block1<Object, NamespaceNode>() {

			@Override
			public Object value_(final NamespaceNode _nsNode) {
				return _dict[0].put(_nsNode.reference(), _nsNode);
			}
		}, new org.gravel.support.jvm.Block1<Object, NamespaceNode>() {

			@Override
			public Object value_(final NamespaceNode _nsNode) {
				return _dict[0].put(_nsNode.reference(), _nsNode);
			}
		});
		_systemNode = _systemNode.withNamespaceNodes_(_dict[0]);
		return this;
	}

	public Object singletonAtReferenceString_(final String _aString) {
		return this.singletonAtReference_(((AbsoluteReference) Reference.factory.value_(_aString)));
	}

	public Object singletonAtReference_(final AbsoluteReference _reference) {
		final org.gravel.support.jvm.runtime.AlmostFinalValue _holder;
		org.gravel.support.jvm.runtime.AlmostFinalValue _temp1 = _singletonHolders.get(_reference);
		if (_temp1 == null) {
			throw new RuntimeException("Singleton not found:" + _reference.toString());
		}
		_holder = ((org.gravel.support.jvm.runtime.AlmostFinalValue) _temp1);
		return _compilerTools.valueOfSingletonHolder_(_holder);
	}

	public org.gravel.support.jvm.runtime.AlmostFinalValue singletonAtReference_ifAbsentPut_(final AbsoluteReference _reference, final org.gravel.support.jvm.Block0<Object> _aBlock) {
		org.gravel.support.jvm.runtime.AlmostFinalValue _temp1 = _singletonHolders.get(_reference);
		if (_temp1 == null) {
			org.gravel.support.jvm.runtime.AlmostFinalValue _temp2 = _compilerTools.newSingletonHolder_value_(_reference, _aBlock.value());
			_singletonHolders.put(_reference, _temp2);
			_temp1 = _temp2;
		}
		return _temp1;
	}

	public org.gravel.support.jvm.runtime.AlmostFinalValue singletonAtReference_initialize_(final AbsoluteReference _reference, final SharedDeclarationNode _sharedVariable) {
		return this.singletonAtReference_ifAbsentPut_(_reference, new org.gravel.support.jvm.Block0<Object>() {

			@Override
			public Object value() {
				if (_sharedVariable.initializer() != null) {
					return SystemMapping.this.evaluateExpression_reference_(_sharedVariable.initializer(), _reference);
				}
				return SystemMapping.this;
			}
		});
	}

	public SystemMapping subclassMappingsFor_do_(final Reference _aReference, final org.gravel.support.jvm.Block1<Object, ClassMapping> _aBlock) {
		for (final ClassMapping _each : _classMappingsByReference.values()) {
			if (org.gravel.support.jvm.ObjectExtensions.equals_(_each.superclassReference(), _aReference)) {
				_aBlock.value_(_each);
			}
		}
		return this;
	}

	public AbstractMethodMapping superMethodMappingFor_methodName_(final Reference _aReference, final String _methodName) {
		final org.gravel.core.Symbol _sel;
		final ClassMapping _classMapping;
		final Reference _superclassReference;
		_classMapping = this.classMappingAtReference_(_aReference);
		_superclassReference = _classMapping.superclassReference();
		if (_superclassReference == null) {
			return null;
		}
		_sel = _selectorConverter.functionNameAsSelector_(_methodName);
		return this.methodMappingFrom_selector_(this.classMappingAtReference_(_superclassReference), _sel);
	}

	public SystemDefinitionNode systemDefinitionNode() {
		return _systemDefinitionNode;
	}

	public SystemNode systemNode() {
		return _systemNode;
	}

	public SystemMapping updateTo_(final SystemDefinitionNode _newSystemDefinitionNode) {
		final SystemDiff[] _diff;
		final SystemNode[] _newSystemNode;
		_newSystemNode = new SystemNode[1];
		_diff = new SystemDiff[1];
		_newSystemNode[0] = PackageNodeMerger.factory.systemDefinitionNode_(_newSystemDefinitionNode).load();
		this.log_while_("Calculating diff", new org.gravel.support.jvm.Block0<Object>() {

			@Override
			public Object value() {
				return _diff[0] = _systemNode.diffTo_(_newSystemNode[0]);
			}
		});
		this.log_while_("Applying diff", new org.gravel.support.jvm.Block0<Object>() {

			@Override
			public Object value() {
				return SystemMapping.this.applyDiff_(_diff[0]);
			}
		});
		_systemDefinitionNode = _newSystemDefinitionNode;
		return this;
	}
}
