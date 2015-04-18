<?xml version = '1.0' encoding = 'ISO-8859-1' ?>
<asm version="1.0" name="0">
	<cp>
		<constant value="ifptypes"/>
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
		<constant value="Model"/>
		<constant value="ABCD"/>
		<constant value="aHelper1"/>
		<constant value="__initaHelper1"/>
		<constant value="J.registerHelperAttribute(SS):V"/>
		<constant value="aHelper2"/>
		<constant value="__initaHelper2"/>
		<constant value="TransientLinkSet"/>
		<constant value="A.__matcher__():V"/>
		<constant value="A.__exec__():V"/>
		<constant value="6:16-6:26"/>
		<constant value="9:16-9:26"/>
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
		<constant value="__exec__"/>
		<constant value="model"/>
		<constant value="NTransientLinkSet;.getLinksByRule(S):QNTransientLink;"/>
		<constant value="A.__applymodel(NTransientLink;):V"/>
		<constant value="MABCD!Model;"/>
		<constant value="0"/>
		<constant value="elements"/>
		<constant value="J.first():J"/>
		<constant value="7:2-7:6"/>
		<constant value="7:2-7:15"/>
		<constant value="7:2-7:24"/>
		<constant value="J.asSequence():J"/>
		<constant value="10:2-10:6"/>
		<constant value="10:2-10:15"/>
		<constant value="10:2-10:29"/>
		<constant value="__matchmodel"/>
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
		<constant value="14:10-20:3"/>
		<constant value="__applymodel"/>
		<constant value="NTransientLink;"/>
		<constant value="NTransientLink;.getSourceElement(S):J"/>
		<constant value="NTransientLink;.getTargetElement(S):J"/>
		<constant value="3"/>
		<constant value="4"/>
		<constant value="test"/>
		<constant value="5"/>
		<constant value="test2"/>
		<constant value="J.+(J):J"/>
		<constant value="15:33-15:36"/>
		<constant value="15:33-15:45"/>
		<constant value="16:8-16:14"/>
		<constant value="17:36-17:39"/>
		<constant value="17:36-17:48"/>
		<constant value="17:36-17:62"/>
		<constant value="18:9-18:16"/>
		<constant value="17:6-18:16"/>
		<constant value="16:8-18:17"/>
		<constant value="15:14-18:17"/>
		<constant value="15:6-18:17"/>
		<constant value="var2"/>
		<constant value="var"/>
		<constant value="link"/>
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
			<push arg="14"/>
			<push arg="15"/>
			<findme/>
			<push arg="16"/>
			<push arg="17"/>
			<pcall arg="18"/>
			<push arg="14"/>
			<push arg="15"/>
			<findme/>
			<push arg="19"/>
			<push arg="20"/>
			<pcall arg="18"/>
			<getasm/>
			<push arg="21"/>
			<push arg="8"/>
			<new/>
			<set arg="1"/>
			<getasm/>
			<pcall arg="22"/>
			<getasm/>
			<pcall arg="23"/>
		</code>
		<linenumbertable>
			<lne id="24" begin="16" end="18"/>
			<lne id="25" begin="22" end="24"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="26" begin="0" end="36"/>
		</localvariabletable>
	</operation>
	<operation name="27">
		<context type="6"/>
		<parameters>
			<parameter name="28" type="4"/>
		</parameters>
		<code>
			<load arg="28"/>
			<getasm/>
			<get arg="3"/>
			<call arg="29"/>
			<if arg="30"/>
			<getasm/>
			<get arg="1"/>
			<load arg="28"/>
			<call arg="31"/>
			<dup/>
			<call arg="32"/>
			<if arg="33"/>
			<load arg="28"/>
			<call arg="34"/>
			<goto arg="35"/>
			<pop/>
			<load arg="28"/>
			<goto arg="36"/>
			<push arg="37"/>
			<push arg="8"/>
			<new/>
			<load arg="28"/>
			<iterate/>
			<store arg="38"/>
			<getasm/>
			<load arg="38"/>
			<call arg="39"/>
			<call arg="40"/>
			<enditerate/>
			<call arg="41"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="2" name="42" begin="23" end="27"/>
			<lve slot="0" name="26" begin="0" end="29"/>
			<lve slot="1" name="43" begin="0" end="29"/>
		</localvariabletable>
	</operation>
	<operation name="44">
		<context type="6"/>
		<parameters>
			<parameter name="28" type="4"/>
			<parameter name="38" type="45"/>
		</parameters>
		<code>
			<getasm/>
			<get arg="1"/>
			<load arg="28"/>
			<call arg="31"/>
			<load arg="28"/>
			<load arg="38"/>
			<call arg="46"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="26" begin="0" end="6"/>
			<lve slot="1" name="43" begin="0" end="6"/>
			<lve slot="2" name="47" begin="0" end="6"/>
		</localvariabletable>
	</operation>
	<operation name="48">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<getasm/>
			<pcall arg="49"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="26" begin="0" end="1"/>
		</localvariabletable>
	</operation>
	<operation name="50">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<getasm/>
			<get arg="1"/>
			<push arg="51"/>
			<call arg="52"/>
			<iterate/>
			<store arg="28"/>
			<getasm/>
			<load arg="28"/>
			<pcall arg="53"/>
			<enditerate/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="1" name="42" begin="5" end="8"/>
			<lve slot="0" name="26" begin="0" end="9"/>
		</localvariabletable>
	</operation>
	<operation name="17">
		<context type="54"/>
		<parameters>
		</parameters>
		<code>
			<load arg="55"/>
			<get arg="56"/>
			<call arg="57"/>
		</code>
		<linenumbertable>
			<lne id="58" begin="0" end="0"/>
			<lne id="59" begin="0" end="1"/>
			<lne id="60" begin="0" end="2"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="26" begin="0" end="2"/>
		</localvariabletable>
	</operation>
	<operation name="20">
		<context type="54"/>
		<parameters>
		</parameters>
		<code>
			<load arg="55"/>
			<get arg="56"/>
			<call arg="61"/>
		</code>
		<linenumbertable>
			<lne id="62" begin="0" end="0"/>
			<lne id="63" begin="0" end="1"/>
			<lne id="64" begin="0" end="2"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="26" begin="0" end="2"/>
		</localvariabletable>
	</operation>
	<operation name="65">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<push arg="14"/>
			<push arg="15"/>
			<findme/>
			<push arg="66"/>
			<call arg="67"/>
			<iterate/>
			<store arg="28"/>
			<getasm/>
			<get arg="1"/>
			<push arg="68"/>
			<push arg="8"/>
			<new/>
			<dup/>
			<push arg="51"/>
			<pcall arg="69"/>
			<dup/>
			<push arg="70"/>
			<load arg="28"/>
			<pcall arg="71"/>
			<dup/>
			<push arg="72"/>
			<push arg="14"/>
			<push arg="73"/>
			<new/>
			<pcall arg="74"/>
			<pusht/>
			<pcall arg="75"/>
			<enditerate/>
		</code>
		<linenumbertable>
			<lne id="76" begin="19" end="24"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="1" name="70" begin="6" end="26"/>
			<lve slot="0" name="26" begin="0" end="27"/>
		</localvariabletable>
	</operation>
	<operation name="77">
		<context type="6"/>
		<parameters>
			<parameter name="28" type="78"/>
		</parameters>
		<code>
			<load arg="28"/>
			<push arg="70"/>
			<call arg="79"/>
			<store arg="38"/>
			<load arg="28"/>
			<push arg="72"/>
			<call arg="80"/>
			<store arg="81"/>
			<load arg="81"/>
			<dup/>
			<getasm/>
			<load arg="38"/>
			<get arg="56"/>
			<store arg="82"/>
			<push arg="83"/>
			<load arg="38"/>
			<get arg="56"/>
			<call arg="61"/>
			<store arg="84"/>
			<push arg="85"/>
			<call arg="86"/>
			<call arg="39"/>
			<set arg="47"/>
			<pop/>
		</code>
		<linenumbertable>
			<lne id="87" begin="11" end="11"/>
			<lne id="88" begin="11" end="12"/>
			<lne id="89" begin="14" end="14"/>
			<lne id="90" begin="15" end="15"/>
			<lne id="91" begin="15" end="16"/>
			<lne id="92" begin="15" end="17"/>
			<lne id="93" begin="19" end="19"/>
			<lne id="94" begin="15" end="19"/>
			<lne id="95" begin="14" end="20"/>
			<lne id="96" begin="11" end="20"/>
			<lne id="97" begin="9" end="22"/>
			<lne id="76" begin="8" end="23"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="5" name="98" begin="18" end="19"/>
			<lve slot="4" name="99" begin="13" end="20"/>
			<lve slot="3" name="72" begin="7" end="23"/>
			<lve slot="2" name="70" begin="3" end="23"/>
			<lve slot="0" name="26" begin="0" end="23"/>
			<lve slot="1" name="100" begin="0" end="23"/>
		</localvariabletable>
	</operation>
</asm>
