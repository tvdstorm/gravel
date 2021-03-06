package st.gravel.support.compiler.jvm;

/*
	This file is automatically generated from typed smalltalk source. Do not edit by hand.
	(C) AG5.com
*/

import java.math.BigDecimal;
import java.math.BigInteger;
import st.gravel.support.jvm.NonLocalReturn;
import st.gravel.support.compiler.jvm.CastTo;
import st.gravel.support.compiler.jvm.CastTo.CastTo_Factory;
import st.gravel.support.compiler.jvm.JVMInstructionVisitor;
import st.gravel.support.compiler.jvm.JVMStack;
import st.gravel.support.compiler.jvm.JVMInstruction;
import st.gravel.support.compiler.jvm.JVMType;
import st.gravel.support.compiler.jvm.JVMBooleanType;

public class CastObjectToBoolean extends CastTo implements Cloneable {

	public static CastObjectToBoolean_Factory factory = new CastObjectToBoolean_Factory();

	public static class CastObjectToBoolean_Factory extends CastTo_Factory {

		public CastObjectToBoolean basicNew() {
			CastObjectToBoolean newInstance = new CastObjectToBoolean();
			newInstance.initialize();
			return newInstance;
		}
	}

	@Override
	public <X> X accept_(final JVMInstructionVisitor<X> _visitor) {
		return _visitor.visitCastObjectToBoolean_(this);
	}

	public CastObjectToBoolean copy() {
		try {
			CastObjectToBoolean _temp1 = (CastObjectToBoolean) this.clone();
			_temp1.postCopy();
			return _temp1;
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public JVMInstruction effectStack_(final JVMStack _aJVMStack) {
		st.gravel.support.jvm.ObjectExtensions.assert_(this, _aJVMStack.pop().isObjectType());
		_aJVMStack.push_(this.type());
		return this;
	}

	public CastObjectToBoolean_Factory factory() {
		return factory;
	}

	@Override
	public CastObjectToBoolean printOn_(final StringBuilder _aStream) {
		final String _title;
		_title = this.factory().toString();
		_aStream.append(st.gravel.support.jvm.CharacterExtensions.isVowel(_title.charAt(0)) ? "an " : "a ");
		_aStream.append(_title);
		_aStream.append('[');
		this.sourceOn_(_aStream);
		_aStream.append(']');
		return this;
	}

	@Override
	public CastObjectToBoolean sourceOn_(final StringBuilder _aStream) {
		return this;
	}

	@Override
	public JVMType type() {
		return JVMBooleanType.factory.basicNew();
	}

	@Override
	public CastObjectToBoolean withReturnType_(final JVMType _aType) {
		if (st.gravel.support.jvm.ObjectExtensions.equals_(this.type(), _aType)) {
			return CastObjectToBoolean.this;
		}
		throw new RuntimeException("niy");
	}
}
