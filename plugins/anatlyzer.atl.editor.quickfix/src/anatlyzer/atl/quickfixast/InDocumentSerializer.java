package anatlyzer.atl.quickfixast;

import java.io.ByteArrayInputStream;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentRewriteSession;
import org.eclipse.jface.text.DocumentRewriteSessionType;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension4;
import org.eclipse.m2m.atl.adt.ui.editor.formatter.AtlCodeFormatter;
import org.eclipse.m2m.atl.common.AtlNbCharFile;

import anatlyzer.atl.quickfixast.QuickfixApplication.Action;
import anatlyzer.atl.quickfixast.QuickfixApplication.AddCommentBefore;
import anatlyzer.atl.quickfixast.QuickfixApplication.DeleteAction;
import anatlyzer.atl.quickfixast.QuickfixApplication.InsertAfterAction;
import anatlyzer.atl.quickfixast.QuickfixApplication.PutInAction;
import anatlyzer.atl.quickfixast.QuickfixApplication.ReplacementAction;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atlext.ATL.LocatedElement;

public class InDocumentSerializer extends ATLSerializer {

	private QuickfixApplication qfa;
	private IDocument document;
	private Action currentAction;
	private AtlNbCharFile help;

	public InDocumentSerializer(QuickfixApplication qfa, IDocument document) {
		this.qfa = qfa;
		this.document = document;

		this.help = new AtlNbCharFile(new ByteArrayInputStream(document.get().getBytes()));
		// return help.getIndexChar(obj.getLocation());
	}

	public void serialize() {
		IDocumentExtension4 ext4 = (IDocumentExtension4) document;
		
		final DocumentRewriteSession session = ext4.startRewriteSession(DocumentRewriteSessionType.SEQUENTIAL);
		try {
			for(Action a : qfa.getActions()) {
				if ( a instanceof PutInAction ) {
					a = ((PutInAction) a).toMockReplacement();
				} else if ( a instanceof DeleteAction ) {
					// This is only an easy way to do this, but it is not able to preserve the layout
					a = ((DeleteAction) a).toMockReplacement();					
				}
	
				this.currentAction = a;
				
				// Special action that does not have a tgt
				if ( a instanceof AddCommentBefore ) {
					LocatedElement element = ((AddCommentBefore) a).getElement();
					String comment  = ((AddCommentBefore) a).getComment();
					int[] offsets = help.getIndexChar(element.getLocation());

					int start = offsets[0];
					int length = 0;
					String s = comment + "\n";
					
					document.replace(start, length, s);
					return;
				}
				
				
				startVisiting(a.getTgt());
				String s = g(a.getTgt());
				
				int start  = -1;
				int length = -1;
				
				if ( a instanceof ReplacementAction ) {
					int[] offsets = help.getIndexChar(((LocatedElement) ((ReplacementAction) a).getSrc()).getLocation());
	
					start  = offsets[0];
					length = offsets[1] - offsets[0];
				} else if ( a instanceof InsertAfterAction ) {
					int[] offsets = help.getIndexChar(((LocatedElement) ((InsertAfterAction) a).getAnchor()).getLocation());
					start  = offsets[1];
					length = 0;
					// By default, add a carriage return...
					s = "\n\n" + s;
				} else {
					throw new UnsupportedOperationException();
				}
				// For the moment analysing the cases, because I do not want to make Actions dependent on AtlHelp...
				
				
				AtlCodeFormatter formatter = new AtlCodeFormatter();
				s = formatter.format(new ByteArrayInputStream(s.getBytes()));
				
				document.replace(start, length, s);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			ext4.stopRewriteSession(session);
		}

		
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
