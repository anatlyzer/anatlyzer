package anatlyzer.atl.quickfixast;

import java.io.ByteArrayInputStream;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.m2m.atl.common.AtlNbCharFile;

import anatlyzer.atl.quickfixast.QuickfixScope.ReplacementAction;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atlext.ATL.LocatedElement;

public class InDocumentSerializer extends ATLSerializer {

	private QuickfixScope<?> qs;
	private IDocument document;
	private ReplacementAction currentAction;
	private AtlNbCharFile help;

	public InDocumentSerializer(QuickfixScope<?> qs, IDocument document) {
		this.qs = qs;
		this.document = document;

		this.help = new AtlNbCharFile(new ByteArrayInputStream(document.get().getBytes()));
		// return help.getIndexChar(obj.getLocation());
	}

	public void serialize() {
		qs.getActions().forEach(a -> {
			this.currentAction = a;

			startVisiting(a.getTgt());
			
			String s = g(a.getTgt());
			int[] offsets = help.getIndexChar(((LocatedElement) a.getSrc()).getLocation());
			
			try {
				document.replace(offsets[0], offsets[1] - offsets[0], s);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}			
		});

	}

	/*
	@Override
	public void inModule(Module self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inMatchedRule(MatchedRule self) {
		throw new UnsupportedOperationException();
	}

	// Matched and Lazy rules
	public void ruleGenerator(RuleWithPattern self, String header) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inLazyRule(LazyRule self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inCalledRule(CalledRule self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inForEachOutPatternElement(ForEachOutPatternElement self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inActionBlock(ActionBlock self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inBindingStat(BindingStat self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inInPattern(InPattern self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inSimpleInPatternElement(SimpleInPatternElement self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inOutPattern(OutPattern self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inSimpleOutPatternElement(SimpleOutPatternElement self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inBinding(Binding self) {
		throw new UnsupportedOperationException();
	}

	//
	// Helpers
	//
	@Override
	public void inContextHelper(ContextHelper self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inStaticHelper(StaticHelper self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inOperatorCallExp(OperatorCallExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inNavigationOrAttributeCallExp(NavigationOrAttributeCallExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inOperationCallExp(OperationCallExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inCollectionOperationCallExp(CollectionOperationCallExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inIfExp(IfExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inLetExp(LetExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inIteratorExp(IteratorExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inIterateExp(IterateExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inVariableExp(VariableExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inStringExp(StringExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inIntegerExp(IntegerExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inRealExp(RealExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inBooleanExp(BooleanExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inOclUndefinedExp(OclUndefinedExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inSequenceExp(SequenceExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inSetExp(SetExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inOrderedSetExp(OrderedSetExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inBagExp(BagExp self) {
		throw new UnsupportedOperationException();
	}

	private void doCollectionExp(String atlText, CollectionExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inMapExp(MapExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inTupleExp(TupleExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inOclModelElement(OclModelElement self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inEnumLiteralExp(EnumLiteralExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inOclAnyType(OclAnyType self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inIntegerType(IntegerType self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inStringType(StringType self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inRealType(RealType self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inBooleanType(BooleanType self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inSequenceType(SequenceType self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inSetType(SetType self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inOrderedSetType(OrderedSetType self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inBagType(BagType self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inMapType(MapType self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inTupleType(TupleType self) {
		throw new UnsupportedOperationException();
	}

	//
	// Imperative
	//
	@Override
	public void inExpressionStat(ExpressionStat self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inIfStat(IfStat self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inForStat(ForStat self) {
		throw new UnsupportedOperationException();
	}
	*/
	
	@Override
	protected String g(EObject obj) {
		if ( currentAction.getTrace().isPreserved(obj)  ){
			LocatedElement l = (LocatedElement) obj;
			int[] offsets = help.getIndexChar(l.getLocation());
			try {
				return document.get(offsets[0], offsets[1] - offsets[0]);
			} catch (BadLocationException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		
		return super.g(obj);
	}
}
