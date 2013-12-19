package org.gravel.support.compiler.ast;

/*
	This file is automatically generated from typed smalltalk source. Do not edit by hand.
	(C) AG5.com
*/

import java.math.BigDecimal;
import java.math.BigInteger;
import org.gravel.support.jvm.NonLocalReturn;
import org.gravel.support.compiler.ast.NodeCopier;
import org.gravel.support.compiler.ast.NodeCopier.NodeCopier_Factory;
import org.gravel.support.compiler.ast.AssignmentNode;
import org.gravel.support.compiler.ast.Expression;
import org.gravel.support.compiler.ast.WriteHolderNode;
import org.gravel.support.compiler.ast.VariableNode;
import org.gravel.support.compiler.ast.SequenceNode;
import java.util.List;
import org.gravel.support.compiler.ast.VariableDeclarationNode;
import org.gravel.support.compiler.ast.Statement;
import java.util.ArrayList;
import org.gravel.support.compiler.ast.HolderDeclarationNode;
import org.gravel.support.compiler.ast.TypeNode;
import org.gravel.support.compiler.ast.CreateHolderNode;
import org.gravel.support.compiler.ast.ReadHolderNode;

public class VariableToHolderRewriter extends NodeCopier implements Cloneable {

	public static VariableToHolderRewriter_Factory factory = new VariableToHolderRewriter_Factory();

	String _varName;

	public static class VariableToHolderRewriter_Factory extends NodeCopier_Factory {

		public VariableToHolderRewriter basicNew() {
			VariableToHolderRewriter newInstance = new VariableToHolderRewriter();
			newInstance.initialize();
			return newInstance;
		}

		public VariableToHolderRewriter varName_(final String _aString) {
			return this.basicNew().initializeVarName_(_aString);
		}
	}

	static public VariableToHolderRewriter _varName_(Object receiver, final String _aString) {
		return factory.varName_(_aString);
	}

	public VariableToHolderRewriter copy() {
		try {
			VariableToHolderRewriter _temp1 = (VariableToHolderRewriter) this.clone();
			_temp1.postCopy();
			return _temp1;
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	public VariableToHolderRewriter_Factory factory() {
		return factory;
	}

	public VariableToHolderRewriter initializeVarName_(final String _aString) {
		_varName = _aString;
		this.initialize();
		return this;
	}

	public String varName() {
		return _varName;
	}

	@Override
	public Expression visitAssignmentNode_(final AssignmentNode _anObject) {
		if (org.gravel.support.jvm.StringExtensions.equals_(_anObject.varName(), _varName)) {
			return WriteHolderNode.factory.varName_value_(_varName, ((Expression) VariableToHolderRewriter.this.visit_(_anObject.value())));
		}
		return AssignmentNode.factory.variable_value_(((VariableNode) this.visit_(_anObject.variable())), ((Expression) this.visit_(_anObject.value())));
	}

	@Override
	public SequenceNode visitSequenceNode_(final SequenceNode _anObject) {
		final List<VariableDeclarationNode>[] _found;
		Statement[] _stmts;
		final VariableDeclarationNode[] _temporaries;
		_found = new List[1];
		_found[0] = new java.util.ArrayList();
		_stmts = org.gravel.support.jvm.ArrayExtensions.collect_(_anObject.statements(), ((org.gravel.support.jvm.Block1<Statement, Statement>) (new org.gravel.support.jvm.Block1<Statement, Statement>() {

			@Override
			public Statement value_(final Statement _each) {
				return (Statement) VariableToHolderRewriter.this.visit_(_each);
			}
		})));
		_temporaries = org.gravel.support.jvm.ArrayExtensions.collect_(_anObject.temporaries(), ((org.gravel.support.jvm.Block1<VariableDeclarationNode, VariableDeclarationNode>) (new org.gravel.support.jvm.Block1<VariableDeclarationNode, VariableDeclarationNode>() {

			@Override
			public VariableDeclarationNode value_(final VariableDeclarationNode _each) {
				VariableDeclarationNode _newDecl;
				if (org.gravel.support.jvm.StringExtensions.equals_(_each.name(), _varName)) {
					_found[0].add(_each);
					_newDecl = HolderDeclarationNode.factory.name_type_(_each.name(), ((TypeNode) VariableToHolderRewriter.this.visit_(_each.type())));
				} else {
					_newDecl = ((VariableDeclarationNode) VariableToHolderRewriter.this.visit_(_each));
				}
				return (VariableDeclarationNode) _newDecl;
			}
		})));
		if (_found[0].size() != 0) {
			_stmts = org.gravel.support.jvm.ArrayExtensions.copyWithFirst_(_stmts, AssignmentNode.factory.variable_value_(VariableNode.factory.name_(_varName), CreateHolderNode.factory.basicNew()));
		}
		return SequenceNode.factory.temporaries_statements_(_temporaries, _stmts);
	}

	@Override
	public Expression visitVariableNode_(final VariableNode _anObject) {
		if (org.gravel.support.jvm.StringExtensions.equals_(_anObject.name(), _varName)) {
			return ReadHolderNode.factory.varName_(_varName);
		}
		return VariableNode.factory.name_(_anObject.name());
	}
}
