package org.gravel.support.compiler.ast;

/*
	This file is automatically generated from typed smalltalk source. Do not edit by hand.
	(C) AG5.com
*/

import java.math.BigDecimal;
import java.math.BigInteger;
import org.gravel.support.jvm.NonLocalReturn;
import org.gravel.support.compiler.ast.Node;
import org.gravel.support.compiler.ast.Node.Node_Factory;
import java.util.Map;
import org.gravel.support.compiler.ast.Reference;
import org.gravel.support.compiler.ast.ClassDescriptionNode;
import org.gravel.support.compiler.ast.NamespaceNode;
import java.util.HashMap;
import org.gravel.support.compiler.ast.NodeVisitor;
import org.gravel.support.compiler.ast.ClassNode;
import org.gravel.support.compiler.ast.SystemDiff;
import java.util.List;
import org.gravel.support.compiler.ast.ClassDiff;
import java.util.ArrayList;
import org.gravel.support.compiler.ast.UpdateClassDiff;
import org.gravel.support.compiler.ast.AddClassDiff;
import org.gravel.support.compiler.ast.WrapJavaclassDiff;
import org.gravel.support.compiler.ast.RemoveClassDiff;
import org.gravel.support.compiler.ast.NewClassDiff;
import org.gravel.support.compiler.ast.TraitFlattener;
import org.gravel.support.compiler.ast.AbsoluteReference;
import org.gravel.support.compiler.ast.SourcePrinter;
import org.gravel.support.compiler.ast.SourcePosition;

public class SystemNode extends Node implements Cloneable {

	public static SystemNode_Factory factory = new SystemNode_Factory();

	Map<Reference, ClassDescriptionNode> _classDescriptionNodes;

	Map<Reference, NamespaceNode> _namespaceNodes;

	public static class SystemNode_Factory extends Node_Factory {

		public SystemNode basicNew() {
			SystemNode newInstance = new SystemNode();
			newInstance.initialize();
			return newInstance;
		}

		public SystemNode classDescriptionNodes_namespaceNodes_(final Map<Reference, ClassDescriptionNode> _aDictionary, final Map<Reference, NamespaceNode> _aDictionary2) {
			return this.basicNew().initializeClassDescriptionNodes_namespaceNodes_(_aDictionary, _aDictionary2);
		}

		public SystemNode empty() {
			return this.classDescriptionNodes_namespaceNodes_(new java.util.HashMap<Reference, ClassDescriptionNode>(), new java.util.HashMap<Reference, NamespaceNode>());
		}
	}

	static public SystemNode _classDescriptionNodes_namespaceNodes_(Object receiver, final Map<Reference, ClassDescriptionNode> _aDictionary, final Map<Reference, NamespaceNode> _aDictionary2) {
		return factory.classDescriptionNodes_namespaceNodes_(_aDictionary, _aDictionary2);
	}

	static public SystemNode _empty(Object receiver) {
		return factory.empty();
	}

	@Override
	public <X> X accept_(final NodeVisitor<X> _visitor) {
		return _visitor.visitSystemNode_(this);
	}

	@Override
	public SystemNode allNodesDo_(final org.gravel.support.jvm.Block1<Object, Node> _aBlock) {
		this.nodesDo_(new org.gravel.support.jvm.Block1<Object, Node>() {

			@Override
			public Object value_(final Node _each) {
				return _each.withAllNodesDo_(_aBlock);
			}
		});
		return this;
	}

	@Override
	public SystemNode allNodesDo_pruneWhere_(final org.gravel.support.jvm.Block1<Object, Node> _aBlock, final org.gravel.support.jvm.Block1<Boolean, Node> _pruneBlock) {
		this.nodesDo_(new org.gravel.support.jvm.Block1<Object, Node>() {

			@Override
			public Object value_(final Node _each) {
				if (!_pruneBlock.value_(_each)) {
					return _each.withAllNodesDo_pruneWhere_(_aBlock, _pruneBlock);
				}
				return SystemNode.this;
			}
		});
		return this;
	}

	public Map<Reference, ClassDescriptionNode> classDescriptionNodes() {
		return _classDescriptionNodes;
	}

	public ClassDescriptionNode classNodeAt_(final Reference _aReference) {
		return _classDescriptionNodes.get(_aReference);
	}

	public Map<Reference, ClassNode> classNodes() {
		final Map<Reference, ClassNode>[] _dict;
		_dict = new Map[1];
		_dict[0] = new java.util.HashMap<Reference, ClassNode>();
		for (final ClassDescriptionNode _each : _classDescriptionNodes.values()) {
			if (_each.isClassNode()) {
				final ClassNode _cn;
				_cn = ((ClassNode) _each);
				_dict[0].put(_each.reference(), _cn);
			}
		}
		return _dict[0];
	}

	public SystemNode copy() {
		try {
			SystemNode _temp1 = (SystemNode) this.clone();
			_temp1.postCopy();
			return _temp1;
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	public SystemDiff diffTo_(final SystemNode _aSystemNode) {
		final List<ClassDiff>[] _unordered;
		final SystemNode[] _start;
		final List<ClassDiff>[] _classDiffs;
		_unordered = new List[1];
		_start = new SystemNode[1];
		_classDiffs = new List[1];
		_unordered[0] = new java.util.ArrayList();
		org.gravel.support.jvm.DictionaryExtensions.syncWith(this.classNodes(), _aSystemNode.classNodes(), new org.gravel.support.jvm.Block2<Object, ClassNode, ClassNode>() {

			@Override
			public Object value_value_(final ClassNode _old, final ClassNode _new) {
				final UpdateClassDiff _diff;
				_diff = _old.diffTo_(_new);
				if (!_diff.isEmpty()) {
					return _unordered[0].add(_diff);
				}
				return SystemNode.this;
			}
		}, new org.gravel.support.jvm.Block1<Object, ClassNode>() {

			@Override
			public Object value_(final ClassNode _classNode) {
				return _unordered[0].add(_classNode.javaClassPath() == null ? AddClassDiff.factory.classNode_(_classNode) : WrapJavaclassDiff.factory.classNode_(_classNode));
			}
		}, new org.gravel.support.jvm.Block1<Object, ClassNode>() {

			@Override
			public Object value_(final ClassNode _classNode) {
				return _unordered[0].add(RemoveClassDiff.factory.classNode_(_classNode));
			}
		});
		_start[0] = this;
		_classDiffs[0] = new java.util.ArrayList();
		boolean _temp1 = false;
		while (!_temp1) {
			_temp1 = _unordered[0].size() == 0;
			if (!_temp1) {
				final List<ClassDiff> _todo;
				_todo = org.gravel.support.jvm.OrderedCollectionExtensions.select_(_unordered[0], new org.gravel.support.jvm.Predicate1<ClassDiff>() {

					@Override
					public boolean value_(final ClassDiff _elem) {
						final Reference _superclassReference;
						_superclassReference = _elem.superclassReference();
						return (_superclassReference == null) || _start[0].includesReference_(_superclassReference);
					}
				});
				if (_todo.size() == 0) {
					throw new RuntimeException("Prerequisite error; trying to load classes with unknown superclass");
				}
				_unordered[0].removeAll(_todo);
				for (final ClassDiff _each : _todo) {
					if (_each.isNewClassDiff()) {
						_start[0] = _start[0].withClassDescriptionNode_(((NewClassDiff) _each).classNode());
						if (_each.isStatic()) {
							Reference _nextRef;
							_nextRef = _each.superclassReference();
							boolean _temp2 = false;
							while (!_temp2) {
								_temp2 = _nextRef == null;
								if (!_temp2) {
									final ClassDiff _superNode;
									final Reference _ref;
									_ref = _nextRef;
									_superNode = ((ClassDiff) org.gravel.support.jvm.OrderedCollectionExtensions.detect_ifNone_(_classDiffs[0], new org.gravel.support.jvm.Predicate1<ClassDiff>() {

										@Override
										public boolean value_(final ClassDiff _cd) {
											return org.gravel.support.jvm.ObjectExtensions.equals_(_cd.reference(), _ref);
										}
									}, ((org.gravel.support.jvm.Block0<ClassDiff>) (new org.gravel.support.jvm.Block0<ClassDiff>() {

										@Override
										public ClassDiff value() {
											throw new RuntimeException("Prereq not found");
										}
									}))));
									_nextRef = _superNode.isStatic() ? null : _superNode.superclassReference();
									_superNode.beStatic();
								}
							}
						}
					}
					_classDiffs[0].add(_each);
				}
			}
		}
		return SystemDiff.factory.classDiffs_namespaces_(_classDiffs[0].toArray(new ClassDiff[_classDiffs[0].size()]), _aSystemNode.namespaceNodes());
	}

	public SystemNode_Factory factory() {
		return factory;
	}

	public SystemNode flattenTraits() {
		return TraitFlattener.factory.start_(this).flattenTraits();
	}

	public boolean includesReference_(final Reference _aReference) {
		return _classDescriptionNodes.containsKey(_aReference);
	}

	public SystemNode initializeClassDescriptionNodes_namespaceNodes_(final Map<Reference, ClassDescriptionNode> _aDictionary, final Map<Reference, NamespaceNode> _aDictionary2) {
		_classDescriptionNodes = _aDictionary;
		_namespaceNodes = _aDictionary2;
		this.initialize();
		return this;
	}

	@Override
	public SystemNode localVarNamesDo_(final org.gravel.support.jvm.Block1<Object, String> _aBlock) {
		return this;
	}

	public NamespaceNode namespaceNodeAt_(final AbsoluteReference _aReference) {
		return this.namespaceNodeAt_ifAbsent_(_aReference, ((org.gravel.support.jvm.Block0<NamespaceNode>) (new org.gravel.support.jvm.Block0<NamespaceNode>() {

			@Override
			public NamespaceNode value() {
				throw new RuntimeException("Can\'t find namespace: " + _aReference.toString());
			}
		})));
	}

	public NamespaceNode namespaceNodeAt_ifAbsent_(final AbsoluteReference _aReference, final org.gravel.support.jvm.Block0<NamespaceNode> _aBlock) {
		return org.gravel.support.jvm.DictionaryExtensions.at_ifAbsent_(_namespaceNodes, _aReference, _aBlock);
	}

	public Map<Reference, NamespaceNode> namespaceNodes() {
		return _namespaceNodes;
	}

	@Override
	public SystemNode nodesDo_(final org.gravel.support.jvm.Block1<Object, Node> _aBlock) {
		for (final ClassDescriptionNode _temp1 : _classDescriptionNodes.values()) {
			_aBlock.value_(_temp1);
		}
		for (final NamespaceNode _temp2 : _namespaceNodes.values()) {
			_aBlock.value_(_temp2);
		}
		return this;
	}

	@Override
	public SystemNode prettySourceOn_(final StringBuilder _aStream) {
		SourcePrinter.factory.on_(_aStream).visit_(this);
		return this;
	}

	@Override
	public SystemNode printOn_(final StringBuilder _aStream) {
		final String _title;
		_title = this.factory().toString();
		_aStream.append(org.gravel.support.jvm.CharacterExtensions.isVowel(_title.charAt(0)) ? "an " : "a ");
		_aStream.append(_title);
		_aStream.append('[');
		this.sourceOn_(_aStream);
		_aStream.append(']');
		return this;
	}

	public SystemNode pvtSetNamespaceNodes_(final Map<Reference, NamespaceNode> _aDictionary) {
		_namespaceNodes = _aDictionary;
		return this;
	}

	@Override
	public SystemNode pvtSetSourcePosition_(final SourcePosition _aSourcePosition) {
		_sourcePosition = _aSourcePosition;
		return this;
	}

	@Override
	public SystemNode sourceOn_(final StringBuilder _aStream) {
		return this;
	}

	@Override
	public SystemNode withAllNodesDo_(final org.gravel.support.jvm.Block1<Object, Node> _aBlock) {
		_aBlock.value_(this);
		this.allNodesDo_(_aBlock);
		return this;
	}

	@Override
	public SystemNode withAllNodesDo_pruneWhere_(final org.gravel.support.jvm.Block1<Object, Node> _aBlock, final org.gravel.support.jvm.Block1<Boolean, Node> _pruneBlock) {
		_aBlock.value_(this);
		this.allNodesDo_pruneWhere_(_aBlock, _pruneBlock);
		return this;
	}

	public SystemNode withClassDescriptionNode_(final ClassDescriptionNode _aClassDescriptionNode) {
		final AbsoluteReference[] _namespace;
		_namespace = new AbsoluteReference[1];
		_namespace[0] = _aClassDescriptionNode.reference().namespace();
		return this.factory().classDescriptionNodes_namespaceNodes_(org.gravel.support.jvm.DictionaryExtensions.copyAt_put_(_classDescriptionNodes, _aClassDescriptionNode.reference(), _aClassDescriptionNode), org.gravel.support.jvm.DictionaryExtensions.copyAt_ifAbsentPut_(_namespaceNodes, _namespace[0], ((org.gravel.support.jvm.Block0<NamespaceNode>) (new org.gravel.support.jvm.Block0<NamespaceNode>() {

			@Override
			public NamespaceNode value() {
				return (NamespaceNode) NamespaceNode.factory.for_(_namespace[0]);
			}
		}))));
	}

	public SystemNode withNamespaceNodes_(final Map<Reference, NamespaceNode> _aDictionary) {
		return this.copy().pvtSetNamespaceNodes_(_aDictionary);
	}

	@Override
	public SystemNode withSourcePosition_(final SourcePosition _aSourcePosition) {
		if (_sourcePosition == _aSourcePosition) {
			return SystemNode.this;
		}
		return this.copy().pvtSetSourcePosition_(_aSourcePosition);
	}
}
