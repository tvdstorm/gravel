package st.gravel.support.compiler.ast;

/*
	This file is automatically generated from typed smalltalk source. Do not edit by hand.
	(C) AG5.com
*/

import java.math.BigDecimal;
import java.math.BigInteger;
import st.gravel.support.jvm.NonLocalReturn;
import st.gravel.support.compiler.ast.IntermediateNode;
import st.gravel.support.compiler.ast.IntermediateNode.IntermediateNode_Factory;
import st.gravel.support.compiler.ast.AbsoluteReference;
import st.gravel.support.compiler.ast.NodeVisitor;
import st.gravel.support.compiler.ast.Node;
import st.gravel.support.compiler.ast.Expression;
import st.gravel.support.compiler.ast.SourcePrinter;
import st.gravel.support.compiler.ast.SourcePosition;

public class InstanceCreationNode extends IntermediateNode implements Cloneable {

	public static InstanceCreationNode_Factory factory = new InstanceCreationNode_Factory();

	AbsoluteReference _reference;

	public static class InstanceCreationNode_Factory extends IntermediateNode_Factory {

		public InstanceCreationNode basicNew() {
			InstanceCreationNode newInstance = new InstanceCreationNode();
			newInstance.initialize();
			return newInstance;
		}

		public InstanceCreationNode reference_(final AbsoluteReference _anAbsoluteReference) {
			return this.basicNew().initializeReference_(_anAbsoluteReference);
		}
	}

	static public InstanceCreationNode _reference_(Object receiver, final AbsoluteReference _anAbsoluteReference) {
		return factory.reference_(_anAbsoluteReference);
	}

	@Override
	public <X> X accept_(final NodeVisitor<X> _visitor) {
		return _visitor.visitInstanceCreationNode_(this);
	}

	@Override
	public InstanceCreationNode allNodesDo_(final st.gravel.support.jvm.Block1<Object, Node> _aBlock) {
		this.nodesDo_(new st.gravel.support.jvm.Block1<Object, Node>() {

			@Override
			public Object value_(final Node _each) {
				return _each.withAllNodesDo_(_aBlock);
			}
		});
		return this;
	}

	@Override
	public InstanceCreationNode allNodesDo_pruneWhere_(final st.gravel.support.jvm.Block1<Object, Node> _aBlock, final st.gravel.support.jvm.Block1<Boolean, Node> _pruneBlock) {
		this.nodesDo_(new st.gravel.support.jvm.Block1<Object, Node>() {

			@Override
			public Object value_(final Node _each) {
				if (!_pruneBlock.value_(_each)) {
					return _each.withAllNodesDo_pruneWhere_(_aBlock, _pruneBlock);
				}
				return InstanceCreationNode.this;
			}
		});
		return this;
	}

	public InstanceCreationNode copy() {
		try {
			InstanceCreationNode _temp1 = (InstanceCreationNode) this.clone();
			_temp1.postCopy();
			return _temp1;
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	public InstanceCreationNode_Factory factory() {
		return factory;
	}

	public InstanceCreationNode initializeReference_(final AbsoluteReference _anAbsoluteReference) {
		_reference = _anAbsoluteReference;
		this.initialize();
		return this;
	}

	@Override
	public InstanceCreationNode innerSourceOn_(final StringBuilder _aStream) {
		_aStream.append("{");
		_aStream.append(_reference.toString());
		_aStream.append(" basicNew}");
		return this;
	}

	@Override
	public InstanceCreationNode localVarNamesDo_(final st.gravel.support.jvm.Block1<Object, String> _aBlock) {
		return this;
	}

	@Override
	public InstanceCreationNode nodesDo_(final st.gravel.support.jvm.Block1<Object, Node> _aBlock) {
		return this;
	}

	@Override
	public InstanceCreationNode prettySourceOn_(final StringBuilder _aStream) {
		SourcePrinter.factory.on_(_aStream).visit_(this);
		return this;
	}

	@Override
	public InstanceCreationNode printOn_(final StringBuilder _aStream) {
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
	public InstanceCreationNode pvtSetSourcePosition_(final SourcePosition _aSourcePosition) {
		_sourcePosition = _aSourcePosition;
		return this;
	}

	public AbsoluteReference reference() {
		return _reference;
	}

	@Override
	public InstanceCreationNode sourceOn_(final StringBuilder _aStream) {
		this.innerSourceOn_(_aStream);
		return this;
	}

	@Override
	public Expression unoptimized() {
		return null;
	}

	@Override
	public InstanceCreationNode withAllNodesDo_(final st.gravel.support.jvm.Block1<Object, Node> _aBlock) {
		_aBlock.value_(this);
		this.allNodesDo_(_aBlock);
		return this;
	}

	@Override
	public InstanceCreationNode withAllNodesDo_pruneWhere_(final st.gravel.support.jvm.Block1<Object, Node> _aBlock, final st.gravel.support.jvm.Block1<Boolean, Node> _pruneBlock) {
		_aBlock.value_(this);
		this.allNodesDo_pruneWhere_(_aBlock, _pruneBlock);
		return this;
	}

	@Override
	public InstanceCreationNode withSourcePosition_(final SourcePosition _aSourcePosition) {
		if (_sourcePosition == _aSourcePosition) {
			return InstanceCreationNode.this;
		}
		return this.copy().pvtSetSourcePosition_(_aSourcePosition);
	}
}