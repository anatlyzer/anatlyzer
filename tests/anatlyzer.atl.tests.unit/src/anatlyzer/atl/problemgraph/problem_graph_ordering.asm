<?xml version = '1.0' encoding = 'ISO-8859-1' ?>
<asm version="1.0" name="0">
	<cp>
		<constant value="problemgraphordering"/>
		<constant value="links"/>
		<constant value="NTransientLinkSet;"/>
		<constant value="col"/>
		<constant value="J"/>
		<constant value="main"/>
		<constant value="A"/>
		<constant value="OclParametrizedType"/>
		<constant value="#native"/>
		<constant value="Collection"/>
		<constant value="J.setName(S):V"/>
		<constant value="OclSimpleType"/>
		<constant value="OclAny"/>
		<constant value="J.setElementType(J):V"/>
		<constant value="ABCD"/>
		<constant value="aFilterWithError"/>
		<constant value="__initaFilterWithError"/>
		<constant value="J.registerHelperAttribute(SS):V"/>
		<constant value="TransientLinkSet"/>
		<constant value="A.__matcher__():V"/>
		<constant value="A.__exec__():V"/>
		<constant value="7:16-7:22"/>
		<constant value="self"/>
		<constant value="__resolve__"/>
		<constant value="1"/>
		<constant value="J.oclIsKindOf(J):B"/>
		<constant value="18"/>
		<constant value="NTransientLinkSet;.getLinkBySourceElement(S):QNTransientLink;"/>
		<constant value="J.oclIsUndefined():B"/>
		<constant value="15"/>
		<constant value="NTransientLink;.getTargetFromSource(J):J"/>
		<constant value="17"/>
		<constant value="30"/>
		<constant value="Sequence"/>
		<constant value="2"/>
		<constant value="A.__resolve__(J):J"/>
		<constant value="QJ.including(J):QJ"/>
		<constant value="QJ.flatten():QJ"/>
		<constant value="e"/>
		<constant value="value"/>
		<constant value="resolveTemp"/>
		<constant value="S"/>
		<constant value="NTransientLink;.getNamedTargetFromSource(JS):J"/>
		<constant value="name"/>
		<constant value="__matcher__"/>
		<constant value="A.__matchmodel():V"/>
		<constant value="A.__matchrB():V"/>
		<constant value="__exec__"/>
		<constant value="model"/>
		<constant value="NTransientLinkSet;.getLinksByRule(S):QNTransientLink;"/>
		<constant value="A.__applymodel(NTransientLink;):V"/>
		<constant value="rB"/>
		<constant value="A.__applyrB(NTransientLink;):V"/>
		<constant value="MABCD!A;"/>
		<constant value="0"/>
		<constant value="nameNotFound"/>
		<constant value="someCondition"/>
		<constant value="J.=(J):J"/>
		<constant value="8:2-8:6"/>
		<constant value="8:2-8:19"/>
		<constant value="8:22-8:37"/>
		<constant value="8:2-8:37"/>
		<constant value="__matchmodel"/>
		<constant value="Model"/>
		<constant value="IN"/>
		<constant value="MMOF!Classifier;.allInstancesFrom(S):QJ"/>
		<constant value="TransientLink"/>
		<constant value="NTransientLink;.setRule(MATL!Rule;):V"/>
		<constant value="src"/>
		<constant value="NTransientLink;.addSourceElement(SJ):V"/>
		<constant value="tgt"/>
		<constant value="WXYZ"/>
		<constant value="NTransientLink;.addTargetElement(SJ):V"/>
		<constant value="NTransientLinkSet;.addLink2(NTransientLink;B):V"/>
		<constant value="13:10-18:3"/>
		<constant value="__applymodel"/>
		<constant value="NTransientLink;"/>
		<constant value="NTransientLink;.getSourceElement(S):J"/>
		<constant value="NTransientLink;.getTargetElement(S):J"/>
		<constant value="3"/>
		<constant value="elements"/>
		<constant value="4"/>
		<constant value="B.not():B"/>
		<constant value="24"/>
		<constant value="CJ.including(J):CJ"/>
		<constant value="17:18-17:21"/>
		<constant value="17:18-17:30"/>
		<constant value="17:43-17:44"/>
		<constant value="17:43-17:61"/>
		<constant value="17:18-17:63"/>
		<constant value="17:6-17:63"/>
		<constant value="link"/>
		<constant value="__matchrB"/>
		<constant value="B"/>
		<constant value="X"/>
		<constant value="23:10-25:6"/>
		<constant value="__applyrB"/>
		<constant value="fromB"/>
		<constant value="24:11-24:18"/>
		<constant value="24:3-24:18"/>
	</cp>
	<field name="1" type="2"/>
	<field name="3" type="4"/>
	<operation name="5">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<getasm/>
			<push arg="7"/>
			<push arg="8"/>
			<new/>
			<dup/>
			<push arg="9"/>
			<pcall arg="10"/>
			<dup/>
			<push arg="11"/>
			<push arg="8"/>
			<new/>
			<dup/>
			<push arg="12"/>
			<pcall arg="10"/>
			<pcall arg="13"/>
			<set arg="3"/>
			<push arg="6"/>
			<push arg="14"/>
			<findme/>
			<push arg="15"/>
			<push arg="16"/>
			<pcall arg="17"/>
			<getasm/>
			<push arg="18"/>
			<push arg="8"/>
			<new/>
			<set arg="1"/>
			<getasm/>
			<pcall arg="19"/>
			<getasm/>
			<pcall arg="20"/>
		</code>
		<linenumbertable>
			<lne id="21" begin="16" end="18"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="22" begin="0" end="30"/>
		</localvariabletable>
	</operation>
	<operation name="23">
		<context type="6"/>
		<parameters>
			<parameter name="24" type="4"/>
		</parameters>
		<code>
			<load arg="24"/>
			<getasm/>
			<get arg="3"/>
			<call arg="25"/>
			<if arg="26"/>
			<getasm/>
			<get arg="1"/>
			<load arg="24"/>
			<call arg="27"/>
			<dup/>
			<call arg="28"/>
			<if arg="29"/>
			<load arg="24"/>
			<call arg="30"/>
			<goto arg="31"/>
			<pop/>
			<load arg="24"/>
			<goto arg="32"/>
			<push arg="33"/>
			<push arg="8"/>
			<new/>
			<load arg="24"/>
			<iterate/>
			<store arg="34"/>
			<getasm/>
			<load arg="34"/>
			<call arg="35"/>
			<call arg="36"/>
			<enditerate/>
			<call arg="37"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="2" name="38" begin="23" end="27"/>
			<lve slot="0" name="22" begin="0" end="29"/>
			<lve slot="1" name="39" begin="0" end="29"/>
		</localvariabletable>
	</operation>
	<operation name="40">
		<context type="6"/>
		<parameters>
			<parameter name="24" type="4"/>
			<parameter name="34" type="41"/>
		</parameters>
		<code>
			<getasm/>
			<get arg="1"/>
			<load arg="24"/>
			<call arg="27"/>
			<load arg="24"/>
			<load arg="34"/>
			<call arg="42"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="22" begin="0" end="6"/>
			<lve slot="1" name="39" begin="0" end="6"/>
			<lve slot="2" name="43" begin="0" end="6"/>
		</localvariabletable>
	</operation>
	<operation name="44">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<getasm/>
			<pcall arg="45"/>
			<getasm/>
			<pcall arg="46"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="22" begin="0" end="3"/>
		</localvariabletable>
	</operation>
	<operation name="47">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<getasm/>
			<get arg="1"/>
			<push arg="48"/>
			<call arg="49"/>
			<iterate/>
			<store arg="24"/>
			<getasm/>
			<load arg="24"/>
			<pcall arg="50"/>
			<enditerate/>
			<getasm/>
			<get arg="1"/>
			<push arg="51"/>
			<call arg="49"/>
			<iterate/>
			<store arg="24"/>
			<getasm/>
			<load arg="24"/>
			<pcall arg="52"/>
			<enditerate/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="1" name="38" begin="5" end="8"/>
			<lve slot="1" name="38" begin="15" end="18"/>
			<lve slot="0" name="22" begin="0" end="19"/>
		</localvariabletable>
	</operation>
	<operation name="16">
		<context type="53"/>
		<parameters>
		</parameters>
		<code>
			<load arg="54"/>
			<get arg="55"/>
			<push arg="56"/>
			<call arg="57"/>
		</code>
		<linenumbertable>
			<lne id="58" begin="0" end="0"/>
			<lne id="59" begin="0" end="1"/>
			<lne id="60" begin="2" end="2"/>
			<lne id="61" begin="0" end="3"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="22" begin="0" end="3"/>
		</localvariabletable>
	</operation>
	<operation name="62">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<push arg="63"/>
			<push arg="14"/>
			<findme/>
			<push arg="64"/>
			<call arg="65"/>
			<iterate/>
			<store arg="24"/>
			<getasm/>
			<get arg="1"/>
			<push arg="66"/>
			<push arg="8"/>
			<new/>
			<dup/>
			<push arg="48"/>
			<pcall arg="67"/>
			<dup/>
			<push arg="68"/>
			<load arg="24"/>
			<pcall arg="69"/>
			<dup/>
			<push arg="70"/>
			<push arg="63"/>
			<push arg="71"/>
			<new/>
			<pcall arg="72"/>
			<pusht/>
			<pcall arg="73"/>
			<enditerate/>
		</code>
		<linenumbertable>
			<lne id="74" begin="19" end="24"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="1" name="68" begin="6" end="26"/>
			<lve slot="0" name="22" begin="0" end="27"/>
		</localvariabletable>
	</operation>
	<operation name="75">
		<context type="6"/>
		<parameters>
			<parameter name="24" type="76"/>
		</parameters>
		<code>
			<load arg="24"/>
			<push arg="68"/>
			<call arg="77"/>
			<store arg="34"/>
			<load arg="24"/>
			<push arg="70"/>
			<call arg="78"/>
			<store arg="79"/>
			<load arg="79"/>
			<dup/>
			<getasm/>
			<push arg="33"/>
			<push arg="8"/>
			<new/>
			<load arg="34"/>
			<get arg="80"/>
			<iterate/>
			<store arg="81"/>
			<load arg="81"/>
			<get arg="15"/>
			<call arg="82"/>
			<if arg="83"/>
			<load arg="81"/>
			<call arg="84"/>
			<enditerate/>
			<call arg="35"/>
			<set arg="80"/>
			<pop/>
		</code>
		<linenumbertable>
			<lne id="85" begin="14" end="14"/>
			<lne id="86" begin="14" end="15"/>
			<lne id="87" begin="18" end="18"/>
			<lne id="88" begin="18" end="19"/>
			<lne id="89" begin="11" end="24"/>
			<lne id="90" begin="9" end="26"/>
			<lne id="74" begin="8" end="27"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="4" name="38" begin="17" end="23"/>
			<lve slot="3" name="70" begin="7" end="27"/>
			<lve slot="2" name="68" begin="3" end="27"/>
			<lve slot="0" name="22" begin="0" end="27"/>
			<lve slot="1" name="91" begin="0" end="27"/>
		</localvariabletable>
	</operation>
	<operation name="92">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<push arg="93"/>
			<push arg="14"/>
			<findme/>
			<push arg="64"/>
			<call arg="65"/>
			<iterate/>
			<store arg="24"/>
			<getasm/>
			<get arg="1"/>
			<push arg="66"/>
			<push arg="8"/>
			<new/>
			<dup/>
			<push arg="51"/>
			<pcall arg="67"/>
			<dup/>
			<push arg="68"/>
			<load arg="24"/>
			<pcall arg="69"/>
			<dup/>
			<push arg="70"/>
			<push arg="94"/>
			<push arg="71"/>
			<new/>
			<pcall arg="72"/>
			<pusht/>
			<pcall arg="73"/>
			<enditerate/>
		</code>
		<linenumbertable>
			<lne id="95" begin="19" end="24"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="1" name="68" begin="6" end="26"/>
			<lve slot="0" name="22" begin="0" end="27"/>
		</localvariabletable>
	</operation>
	<operation name="96">
		<context type="6"/>
		<parameters>
			<parameter name="24" type="76"/>
		</parameters>
		<code>
			<load arg="24"/>
			<push arg="68"/>
			<call arg="77"/>
			<store arg="34"/>
			<load arg="24"/>
			<push arg="70"/>
			<call arg="78"/>
			<store arg="79"/>
			<load arg="79"/>
			<dup/>
			<getasm/>
			<push arg="97"/>
			<call arg="35"/>
			<set arg="43"/>
			<pop/>
		</code>
		<linenumbertable>
			<lne id="98" begin="11" end="11"/>
			<lne id="99" begin="9" end="13"/>
			<lne id="95" begin="8" end="14"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="3" name="70" begin="7" end="14"/>
			<lve slot="2" name="68" begin="3" end="14"/>
			<lve slot="0" name="22" begin="0" end="14"/>
			<lve slot="1" name="91" begin="0" end="14"/>
		</localvariabletable>
	</operation>
</asm>
