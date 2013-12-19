package org.gravel.support.compiler.ast;

/*
	This file is automatically generated from typed smalltalk source. Do not edit by hand.
	(C) AG5.com
*/

import java.math.BigDecimal;
import java.math.BigInteger;
import org.gravel.support.jvm.NonLocalReturn;
import org.gravel.support.compiler.ast.AbstractTraitUsageToDirectiveConverter;
import org.gravel.support.compiler.ast.AbstractTraitUsageToDirectiveConverter.AbstractTraitUsageToDirectiveConverter_Factory;
import org.gravel.support.compiler.ast.SimpleTraitUsageNode;
import org.gravel.support.compiler.ast.Node;
import org.gravel.support.compiler.ast.StringLiteralNode;

public class TraitUsageToDirectiveConverter extends AbstractTraitUsageToDirectiveConverter implements Cloneable {

	public static TraitUsageToDirectiveConverter_Factory factory = new TraitUsageToDirectiveConverter_Factory();

	public static class TraitUsageToDirectiveConverter_Factory extends AbstractTraitUsageToDirectiveConverter_Factory {

		public TraitUsageToDirectiveConverter basicNew() {
			TraitUsageToDirectiveConverter newInstance = new TraitUsageToDirectiveConverter();
			newInstance.initialize();
			return newInstance;
		}
	}

	public TraitUsageToDirectiveConverter copy() {
		try {
			TraitUsageToDirectiveConverter _temp1 = (TraitUsageToDirectiveConverter) this.clone();
			_temp1.postCopy();
			return _temp1;
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	public TraitUsageToDirectiveConverter_Factory factory() {
		return factory;
	}

	@Override
	public Node[] produceSimpleNoMethod_(final SimpleTraitUsageNode _aSimpleTraitUsageNode) {
		return org.gravel.support.jvm.ArrayFactory.with_(this.reader().send_with_("addSimpleTrait:", StringLiteralNode.factory.value_(_aSimpleTraitUsageNode.reference().toString())));
	}
}
