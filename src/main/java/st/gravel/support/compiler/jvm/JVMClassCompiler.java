package st.gravel.support.compiler.jvm;

/*
	This file is automatically generated from typed smalltalk source. Do not edit by hand.
	(C) AG5.com
*/

import java.math.BigDecimal;
import java.math.BigInteger;
import st.gravel.support.jvm.NonLocalReturn;
import st.gravel.support.compiler.ast.ClassDescriptionNode;
import st.gravel.support.compiler.ast.SystemNode;
import st.gravel.support.compiler.ast.SystemMappingUpdater;
import st.gravel.support.compiler.jvm.JVMDefinedObjectType;
import java.util.List;
import st.gravel.support.compiler.jvm.BlockInnerClass;
import st.gravel.support.compiler.jvm.JVMMethodConstant;
import st.gravel.support.compiler.ast.SelectorConverter;
import st.gravel.support.compiler.jvm.JVMMethod;
import st.gravel.support.compiler.jvm.JVMField;
import st.gravel.support.compiler.jvm.JVMClass;
import st.gravel.support.compiler.jvm.JVMNonPrimitiveType;
import st.gravel.support.compiler.jvm.JVMMethodType;
import st.gravel.support.compiler.jvm.JVMInstruction;
import java.util.ArrayList;
import st.gravel.support.compiler.jvm.Load;
import st.gravel.support.compiler.jvm.JVMDynamicObjectType;
import st.gravel.support.compiler.jvm.DynamicSuperSend;
import st.gravel.support.compiler.jvm.AReturn;
import st.gravel.support.compiler.jvm.JVMLocalDeclaration;
import st.gravel.support.compiler.jvm.JVMBlockCompiler;
import java.util.Set;
import java.util.HashSet;
import st.gravel.support.compiler.ast.MethodNode;
import st.gravel.support.compiler.ast.Reference;
import st.gravel.support.compiler.ast.BoundVariableDeclarationNode;
import st.gravel.support.compiler.ast.VariableDeclarationNode;
import st.gravel.support.compiler.jvm.Return;
import st.gravel.support.compiler.jvm.InvokeSpecial;
import st.gravel.support.compiler.jvm.JVMType;
import st.gravel.support.compiler.jvm.JVMMethodCompiler;
import st.gravel.support.compiler.ast.Expression;
import st.gravel.support.compiler.ast.BlockNode;
import st.gravel.support.compiler.jvm.JVMVariable;
import st.gravel.support.compiler.jvm.Invoke;
import st.gravel.support.compiler.ast.SourcePosition;

public class JVMClassCompiler extends Object implements Cloneable {

	public static JVMClassCompiler_Factory factory = new JVMClassCompiler_Factory();

	ClassDescriptionNode _classDescriptionNode;

	int _constantCount;

	List<JVMMethodConstant> _constants;

	List<JVMClass> _extraClasses;

	List<JVMField> _fields;

	List<BlockInnerClass> _innerclasses;

	boolean _isStatic;

	List<JVMMethod> _jvmMethods;

	JVMDefinedObjectType _ownerType;

	int[] _positionsCache;

	SelectorConverter _selectorConverter;

	JVMNonPrimitiveType _selfType;

	JVMDefinedObjectType _superType;

	SystemMappingUpdater _systemMappingUpdater;

	SystemNode _systemNode;

	public static class JVMClassCompiler_Factory extends st.gravel.support.jvm.SmalltalkFactory {

		public JVMClassCompiler basicNew() {
			JVMClassCompiler newInstance = new JVMClassCompiler();
			newInstance.initialize();
			return newInstance;
		}

		public JVMClassCompiler classDescriptionNode_systemNode_systemMappingUpdater_isStatic_(final ClassDescriptionNode _aClassDescriptionNode, final SystemNode _aSystemNode, final SystemMappingUpdater _aSystemMappingUpdater, final boolean _anObject) {
			return this.basicNew().initializeClassDescriptionNode_systemNode_systemMappingUpdater_isStatic_(_aClassDescriptionNode, _aSystemNode, _aSystemMappingUpdater, _anObject);
		}
	}

	static public JVMClassCompiler _classDescriptionNode_systemNode_systemMappingUpdater_isStatic_(Object receiver, final ClassDescriptionNode _aClassDescriptionNode, final SystemNode _aSystemNode, final SystemMappingUpdater _aSystemMappingUpdater, final boolean _anObject) {
		return factory.classDescriptionNode_systemNode_systemMappingUpdater_isStatic_(_aClassDescriptionNode, _aSystemNode, _aSystemMappingUpdater, _anObject);
	}

	public JVMClassCompiler addInvokeSuper_functionName_numArgs_superReference_superSig_(final String _name, final String _functionName, final int _numArgs, final String _superReference, final JVMMethodType _superSig) {
		final List<JVMInstruction> _instructions;
		_instructions = new java.util.ArrayList();
		for (int _i = 0; _i <= _numArgs; _i++) {
			_instructions.add(Load.factory.index_type_(_i, JVMDynamicObjectType.factory.basicNew()));
		}
		_instructions.add(DynamicSuperSend.factory.functionName_numArgs_superReference_(_functionName, _numArgs, _superReference));
		_instructions.add(AReturn.factory.basicNew());
		_jvmMethods.add(JVMMethod.factory.name_locals_instructions_isStatic_signature_(_name, new JVMLocalDeclaration[] {}, _instructions.toArray(new JVMInstruction[_instructions.size()]), true, _superSig));
		return this;
	}

	public JVMClassCompiler buildPositionsCache() {
		final java.nio.CharBuffer _stream;
		final String _src;
		char _ch;
		final java.io.File _file;
		int _line;
		final List<Integer> _positionsCacheColl;
		_file = _classDescriptionNode.findSourceFile().asFile();
		_src = st.gravel.support.jvm.FilenameExtensions.contentsOfEntireFile(_file);
		_positionsCacheColl = new java.util.ArrayList();
		_stream = st.gravel.support.jvm.ReadStreamFactory.on_(_src);
		_line = 1;
		boolean _temp1 = false;
		while (!_temp1) {
			_temp1 = st.gravel.support.jvm.ReadStreamExtensions.atEnd(_stream);
			if (!_temp1) {
				_ch = st.gravel.support.jvm.ReadStreamExtensions.next(_stream);
				if (st.gravel.support.jvm.CharacterExtensions.equals_(_ch, st.gravel.support.jvm.StringExtensions.lineEndConvention())) {
					_line = (_line + 1);
				}
				_positionsCacheColl.add(_line);
			}
		}
		_positionsCache = st.gravel.support.jvm.OrderedCollectionExtensions.toIntArray(_positionsCacheColl);
		return this;
	}

	public ClassDescriptionNode classDescriptionNode() {
		return _classDescriptionNode;
	}

	public JVMClass compileBlockNoAdd_(final BlockInnerClass _aBlockInnerClass) {
		return JVMBlockCompiler.factory.parent_block_(this, _aBlockInnerClass).compileBlock();
	}

	public JVMClassCompiler compileBlocks() {
		final java.util.Set<JVMDefinedObjectType>[] _done;
		BlockInnerClass _cl;
		_done = new java.util.Set[1];
		_done[0] = new java.util.HashSet();
		_cl = null;
		boolean _temp1 = false;
		while (!_temp1) {
			_cl = ((BlockInnerClass) st.gravel.support.jvm.OrderedCollectionExtensions.detect_ifNone_(_innerclasses, new st.gravel.support.jvm.Predicate1<BlockInnerClass>() {

				@Override
				public boolean value_(final BlockInnerClass _e) {
					return !_done[0].contains(_e.ownerType());
				}
			}, ((st.gravel.support.jvm.Block0<BlockInnerClass>) (new st.gravel.support.jvm.Block0<BlockInnerClass>() {

				@Override
				public BlockInnerClass value() {
					return (BlockInnerClass) null;
				}
			}))));
			_temp1 = _cl == null;
			if (!_temp1) {
				JVMClassCompiler.this.compileBlock_(_cl);
				_done[0].add(_cl.ownerType());
			}
		}
		return this;
	}

	public JVMClass compileBlock_(final BlockInnerClass _aBlockInnerClass) {
		final JVMClass _blockClass;
		_blockClass = this.compileBlockNoAdd_(_aBlockInnerClass);
		_extraClasses.add(_blockClass);
		return _blockClass;
	}

	public JVMClass compileClassNode() {
		final MethodNode[] _allMethods;
		Reference _superclassReference;
		BoundVariableDeclarationNode[] _allInstVars;
		final MethodNode[] _localLinkedMethods;
		ClassDescriptionNode _scn;
		if (_ownerType == null) {
			_ownerType = ((JVMDefinedObjectType) _selfType);
		}
		_allMethods = _classDescriptionNode.methods();
		_superclassReference = _classDescriptionNode.superclassReference();
		_allInstVars = _classDescriptionNode.boundInstVars();
		boolean _temp1 = false;
		while (!_temp1) {
			_temp1 = _superclassReference == null;
			if (!_temp1) {
				_scn = _systemNode.classNodeAt_(_superclassReference);
				_superclassReference = _scn.superclassReference();
				_allInstVars = st.gravel.support.jvm.ArrayExtensions.copyWithAll_(_scn.boundInstVars(), _allInstVars);
			}
		}
		_localLinkedMethods = _systemMappingUpdater.localLinkMethods_instVars_ownerReference_(_allMethods, _allInstVars, _classDescriptionNode.reference());
		for (final MethodNode _each : _localLinkedMethods) {
			JVMClassCompiler.this.compileMethod_(_each);
		}
		for (final VariableDeclarationNode _each : _classDescriptionNode.instVars()) {
			_fields.add(JVMField.factory.ownerType_varName_type_isStatic_(_ownerType, _each.name(), JVMDynamicObjectType.factory.basicNew(), false));
		}
		this.compileBlocks();
		return this.createContainerClass();
	}

	public JVMClassCompiler compileClinit() {
		final List<JVMInstruction>[] _instructions;
		_instructions = new List[1];
		_instructions[0] = new java.util.ArrayList();
		for (final JVMMethodConstant _each : _constants) {
			st.gravel.support.jvm.OrderedCollectionExtensions.addAll_(_instructions[0], _each.clinitInstructions());
			_fields.add(_each.fieldDefinition());
		}
		_instructions[0].add(Return.factory.basicNew());
		_jvmMethods.add(JVMMethod.factory.name_locals_instructions_isStatic_signature_("<clinit>", new JVMLocalDeclaration[] {}, _instructions[0].toArray(new JVMInstruction[_instructions[0].size()]), true, JVMMethodType.factory.r_void()));
		return this;
	}

	public JVMClassCompiler compileClone() {
		final List<JVMInstruction> _instructions;
		_instructions = new java.util.ArrayList();
		_instructions.add(Load.factory.index_type_(0, _ownerType));
		_instructions.add(InvokeSpecial.factory.ownerType_name_signature_(JVMDefinedObjectType.factory.object(), "clone", JVMMethodType.factory.returnType_arguments_(JVMDefinedObjectType.factory.object(), new JVMType[] {})));
		_instructions.add(AReturn.factory.basicNew());
		_jvmMethods.add(JVMMethod.factory.name_locals_instructions_isStatic_signature_("clone", new JVMLocalDeclaration[] {}, _instructions.toArray(new JVMInstruction[_instructions.size()]), false, JVMMethodType.factory.object()));
		return this;
	}

	public JVMClassCompiler compileInit() {
		final List<JVMInstruction> _instructions;
		_instructions = new java.util.ArrayList();
		_instructions.add(Load.factory.index_type_(0, _ownerType));
		_instructions.add(InvokeSpecial.factory.init_voidArguments_(_superType, new JVMType[] {}));
		_instructions.add(Return.factory.basicNew());
		_jvmMethods.add(JVMMethod.factory.name_locals_instructions_isStatic_signature_("<init>", st.gravel.support.jvm.ArrayFactory.with_(JVMLocalDeclaration.factory.self()), _instructions.toArray(new JVMInstruction[_instructions.size()]), false, JVMMethodType.factory.r_void()));
		return this;
	}

	public JVMClassCompiler compileMethod_(final MethodNode _aMethodNode) {
		JVMMethodCompiler _temp1 = JVMMethodCompiler.factory.parent_(this);
		_temp1.visit_(_aMethodNode);
		_jvmMethods.add(_temp1.buildMethod());
		return this;
	}

	public JVMMethodConstant constantAt_ifAbsentPut_(final Expression _anObject, final st.gravel.support.jvm.Block0<JVMMethodConstant> _aBlock) {
		return ((JVMMethodConstant) st.gravel.support.jvm.OrderedCollectionExtensions.detect_ifNone_(_constants, new st.gravel.support.jvm.Predicate1<JVMMethodConstant>() {

			@Override
			public boolean value_(final JVMMethodConstant _each) {
				return st.gravel.support.jvm.ObjectExtensions.equals_(_each.expression(), _anObject);
			}
		}, ((st.gravel.support.jvm.Block0<JVMMethodConstant>) (new st.gravel.support.jvm.Block0<JVMMethodConstant>() {

			@Override
			public JVMMethodConstant value() {
				final JVMMethodConstant _constant;
				_constant = _aBlock.value();
				_constants.add(_constant);
				return (JVMMethodConstant) _constant;
			}
		}))));
	}

	public JVMClassCompiler copy() {
		try {
			JVMClassCompiler _temp1 = (JVMClassCompiler) this.clone();
			_temp1.postCopy();
			return _temp1;
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	public BlockInnerClass createBlockInnerClass_copiedVariables_(final BlockNode _aBlockNode, final JVMVariable[] _anArray) {
		final BlockInnerClass _innerclass;
		_innerclass = BlockInnerClass.factory.ownerType_blockNode_copiedVariables_(this.newInnerClassType(), _aBlockNode, _anArray);
		_innerclasses.add(_innerclass);
		return _innerclass;
	}

	public JVMClass createContainerClass() {
		final JVMClass _aClass;
		this.compileClinit();
		this.compileInit();
		this.compileClone();
		_aClass = JVMClass.factory.type_superType_fields_methods_(_ownerType, _superType, _fields.toArray(new JVMField[_fields.size()]), _jvmMethods.toArray(new JVMMethod[_jvmMethods.size()]));
		if (!((_classDescriptionNode == null) || (_classDescriptionNode.findSourceFile() == null))) {
			_aClass.source_(_classDescriptionNode.findSourceFile().name());
		}
		_jvmMethods = null;
		return _aClass;
	}

	public Invoke createInvokeInstruction_name_numArgs_(final JVMDefinedObjectType _type, final String _name, final int _numArgs) {
		return _systemMappingUpdater.compilerTools().createInvokeInstruction_name_numArgs_(_type, _name, _numArgs);
	}

	public JVMClassCompiler extensionPostfix_(final String _extensionPostfix) {
		st.gravel.support.jvm.ObjectExtensions.assert_(this, _isStatic);
		_ownerType = JVMDefinedObjectType.factory.className_(_selfType.className() + "$" + _extensionPostfix);
		_superType = JVMDefinedObjectType.factory.object();
		return this;
	}

	public JVMClass[] extraClasses() {
		return _extraClasses.toArray(new JVMClass[_extraClasses.size()]);
	}

	public JVMClassCompiler extraClassesDo_(final st.gravel.support.jvm.Block1<Object, JVMClass> _aBlock) {
		for (final JVMClass _temp1 : _extraClasses) {
			_aBlock.value_(_temp1);
		}
		return this;
	}

	public JVMClassCompiler_Factory factory() {
		return factory;
	}

	public boolean hasConstantsOrFieldsOrExtraClasses() {
		return (_constants.size() != 0) || ((_fields.size() != 0) || (_extraClasses.size() != 0));
	}

	public JVMClassCompiler initialize() {
		_innerclasses = new java.util.ArrayList();
		_constantCount = 0;
		_constants = new java.util.ArrayList();
		_selectorConverter = SelectorConverter.factory.basicNew();
		_jvmMethods = new java.util.ArrayList();
		_fields = new java.util.ArrayList();
		_extraClasses = new java.util.ArrayList();
		if ((_classDescriptionNode != null) && (_selfType == null)) {
			final Reference _superclassReference;
			_selfType = JVMDefinedObjectType.factory.dottedClassName_(_systemMappingUpdater.compilerTools().referenceAsClassName_(_classDescriptionNode.reference()));
			_superclassReference = _classDescriptionNode.superclassReference();
			_superType = ((JVMDefinedObjectType) (_superclassReference == null ? _classDescriptionNode.isMeta() ? JVMDefinedObjectType.factory.objectClass() : JVMDefinedObjectType.factory.object() : _systemMappingUpdater.compilerTools().jvmTypeForClass_(_systemMappingUpdater.systemMapping().classMappingAtReference_(_superclassReference).identityClass())));
		}
		return this;
	}

	public JVMClassCompiler initializeClassDescriptionNode_systemNode_systemMappingUpdater_isStatic_(final ClassDescriptionNode _aClassDescriptionNode, final SystemNode _aSystemNode, final SystemMappingUpdater _aSystemMappingUpdater, final boolean _aBoolean) {
		_classDescriptionNode = _aClassDescriptionNode;
		_systemNode = _aSystemNode;
		_systemMappingUpdater = _aSystemMappingUpdater;
		_isStatic = _aBoolean;
		this.initialize();
		return this;
	}

	public boolean isStatic() {
		return _isStatic;
	}

	public Integer lineNumberOf_(final SourcePosition _aSourcePosition) {
		if (_classDescriptionNode.findSourceFile() == null) {
			return null;
		}
		if (!st.gravel.support.jvm.ObjectExtensions.equals_(_classDescriptionNode.findSourceFile(), _aSourcePosition.sourceFile())) {
			return null;
		}
		if (_positionsCache == null) {
			JVMClassCompiler.this.buildPositionsCache();
		}
		return _positionsCache[_aSourcePosition.start() - 1];
	}

	public String newConstantName() {
		return "__constant_" + "" + ++_constantCount;
	}

	public JVMDefinedObjectType newInnerClassType() {
		return JVMDefinedObjectType.factory.className_(_ownerType.className() + "$Block" + "" + _innerclasses.size());
	}

	public JVMDefinedObjectType ownerType() {
		return _ownerType;
	}

	public JVMClassCompiler ownerType_(final JVMDefinedObjectType _anObject) {
		_ownerType = _anObject;
		return this;
	}

	public JVMClassCompiler postCopy() {
		return this;
	}

	public SelectorConverter selectorConverter() {
		return _selectorConverter;
	}

	public JVMNonPrimitiveType selfType() {
		return _selfType;
	}

	public JVMClassCompiler selfType_(final JVMNonPrimitiveType _anObject) {
		st.gravel.support.jvm.ObjectExtensions.assert_(this, _isStatic);
		_selfType = _anObject;
		return this;
	}

	public SystemMappingUpdater systemMappingUpdater() {
		return _systemMappingUpdater;
	}

	public SystemNode systemNode() {
		return _systemNode;
	}
}
