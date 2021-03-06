package st.gravel.support.compiler.jvm;

/*
	This file is automatically generated from typed smalltalk source. Do not edit by hand.
	(C) AG5.com
*/

import java.math.BigDecimal;
import java.math.BigInteger;
import st.gravel.support.jvm.NonLocalReturn;
import st.gravel.support.compiler.jvm.JVMNonPrimitiveType;
import st.gravel.support.compiler.jvm.JVMNonPrimitiveType.JVMNonPrimitiveType_Factory;
import st.gravel.support.compiler.jvm.JVMType;
import st.gravel.support.compiler.jvm.JVMIntType;
import st.gravel.support.compiler.jvm.JVMDynamicObjectType;
import st.gravel.support.compiler.jvm.CastTo;
import st.gravel.support.compiler.jvm.IdentityCast;
import st.gravel.support.compiler.jvm.CastObjectToDefined;
import st.gravel.support.compiler.jvm.JVMMethodType;

public class JVMDefinedObjectType extends JVMNonPrimitiveType implements Cloneable {

	public static JVMDefinedObjectType_Factory factory = new JVMDefinedObjectType_Factory();

	String[] _path;

	public static class JVMDefinedObjectType_Factory extends JVMNonPrimitiveType_Factory {

		public JVMDefinedObjectType basicNew() {
			JVMDefinedObjectType newInstance = new JVMDefinedObjectType();
			newInstance.initialize();
			return newInstance;
		}

		public JVMDefinedObjectType bigDecimal() {
			return this.className_("java/math/BigDecimal");
		}

		public JVMDefinedObjectType bigInteger() {
			return this.className_("java/math/BigInteger");
		}

		public JVMDefinedObjectType block_(final int _anInteger) {
			return JVMDefinedObjectType.factory.className_("st/gravel/support/jvm/Block" + "" + _anInteger);
		}

		public JVMDefinedObjectType className_(final String _aString) {
			return this.path_(st.gravel.support.jvm.StringExtensions.tokensBasedOn_(_aString, '/'));
		}

		public JVMDefinedObjectType dottedClassName_(final String _aString) {
			return this.path_(st.gravel.support.jvm.StringExtensions.tokensBasedOn_(_aString, '.'));
		}

		public JVMDefinedObjectType integer() {
			return this.className_("java/lang/Integer");
		}

		public JVMDefinedObjectType nonLocalReturn() {
			return this.className_("st/gravel/support/jvm/NonLocalReturn");
		}

		public JVMDefinedObjectType object() {
			return this.className_("java/lang/Object");
		}

		public JVMDefinedObjectType objectClass() {
			return this.className_("st/gravel/support/jvm/ObjectClass");
		}

		public JVMDefinedObjectType path_(final String[] _anArray) {
			return this.basicNew().initializePath_(_anArray);
		}

		public JVMDefinedObjectType runtimeException() {
			return this.className_("java/lang/RuntimeException");
		}

		public JVMDefinedObjectType string() {
			return this.className_("java/lang/String");
		}

		public JVMDefinedObjectType symbol() {
			return this.className_("st/gravel/core/Symbol");
		}
	}

	static public JVMDefinedObjectType _bigDecimal(Object receiver) {
		return factory.bigDecimal();
	}

	static public JVMDefinedObjectType _bigInteger(Object receiver) {
		return factory.bigInteger();
	}

	static public JVMDefinedObjectType _block_(Object receiver, final int _anInteger) {
		return factory.block_(_anInteger);
	}

	static public JVMDefinedObjectType _className_(Object receiver, final String _aString) {
		return factory.className_(_aString);
	}

	static public JVMDefinedObjectType _dottedClassName_(Object receiver, final String _aString) {
		return factory.dottedClassName_(_aString);
	}

	static public JVMDefinedObjectType _integer(Object receiver) {
		return factory.integer();
	}

	static public JVMDefinedObjectType _nonLocalReturn(Object receiver) {
		return factory.nonLocalReturn();
	}

	static public JVMDefinedObjectType _object(Object receiver) {
		return factory.object();
	}

	static public JVMDefinedObjectType _objectClass(Object receiver) {
		return factory.objectClass();
	}

	static public JVMDefinedObjectType _path_(Object receiver, final String[] _anArray) {
		return factory.path_(_anArray);
	}

	static public JVMDefinedObjectType _runtimeException(Object receiver) {
		return factory.runtimeException();
	}

	static public JVMDefinedObjectType _string(Object receiver) {
		return factory.string();
	}

	static public JVMDefinedObjectType _symbol(Object receiver) {
		return factory.symbol();
	}

	@Override
	public String className() {
		return st.gravel.support.jvm.ArrayExtensions.joinWith_(_path, "/");
	}

	@Override
	public JVMType commonSuperTypeWithInt_(final JVMIntType _aJVMIntType) {
		if (st.gravel.support.jvm.ObjectExtensions.equals_(this, JVMDefinedObjectType.factory.integer())) {
			return JVMDefinedObjectType.factory.integer();
		}
		return JVMDynamicObjectType.factory.basicNew();
	}

	@Override
	public JVMType commonSuperTypeWith_(final JVMType _aJVMType) {
		return _aJVMType.commonSuperTypeWithDefined_(this);
	}

	public JVMDefinedObjectType copy() {
		try {
			JVMDefinedObjectType _temp1 = (JVMDefinedObjectType) this.clone();
			_temp1.postCopy();
			return _temp1;
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public JVMDefinedObjectType descriptorOn_(final StringBuilder _aStream) {
		_aStream.append('L');
		boolean _temp1 = true;
		for (final String _each : _path) {
			if (_temp1) {
				_temp1 = false;
			} else {
				_aStream.append('/');
			}
			_aStream.append(_each);
		}
		_aStream.append(';');
		return this;
	}

	@Override
	public String dottedClassName() {
		return st.gravel.support.jvm.ArrayExtensions.joinWith_(_path, ".");
	}

	@Override
	public boolean equals(final Object _anObject) {
		if (!(this.getClass() == _anObject.getClass())) {
			return false;
		}
		if (this._path == null) {
			if (!(((JVMDefinedObjectType) _anObject)._path == null)) {
				return false;
			}
		} else {
			if (!st.gravel.support.jvm.ArrayExtensions.equals_(this._path, ((JVMDefinedObjectType) _anObject)._path)) {
				return false;
			}
		}
		return true;
	}

	public JVMDefinedObjectType_Factory factory() {
		return factory;
	}

	@Override
	public int hashCode() {
		return (super.hashCode() + java.util.Arrays.hashCode(_path));
	}

	public JVMDefinedObjectType initializePath_(final String[] _anOrderedCollection) {
		_path = _anOrderedCollection;
		this.initialize();
		return this;
	}

	@Override
	public boolean isDefinedType() {
		return true;
	}

	@Override
	public boolean isObjectType() {
		return true;
	}

	@Override
	public CastTo newCastInstructionFromDefinedObject_(final JVMDefinedObjectType _anObject) {
		if (st.gravel.support.jvm.ObjectExtensions.equals_(this, _anObject)) {
			return IdentityCast.factory.basicNew();
		}
		return CastObjectToDefined.factory.type_(this);
	}

	@Override
	public CastObjectToDefined newCastInstructionFromDynamicObject() {
		return CastObjectToDefined.factory.type_(this);
	}

	@Override
	public CastTo newCastInstructionTo_(final JVMType _aJVMType) {
		return _aJVMType.newCastInstructionFromDefinedObject_(this);
	}

	public String[] path() {
		return _path;
	}

	@Override
	public JVMDefinedObjectType printOn_(final StringBuilder _aStream) {
		this.sourceOn_(_aStream);
		return this;
	}

	@Override
	public JVMDefinedObjectType sourceOn_(final StringBuilder _aStream) {
		this.descriptorOn_(_aStream);
		return this;
	}

	public JVMMethodType withArgument_(final JVMType _aJVMType) {
		return JVMMethodType.factory.returnType_arguments_(this, st.gravel.support.jvm.ArrayFactory.with_(_aJVMType));
	}
}
