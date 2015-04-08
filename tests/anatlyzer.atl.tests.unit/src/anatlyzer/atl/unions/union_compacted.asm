<?xml version = '1.0' encoding = 'ISO-8859-1' ?>
<asm version="1.0" name="0">
	<cp>
		<constant value="unioncompacted"/>
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
		<constant value="TransientLinkSet"/>
		<constant value="A.__matcher__():V"/>
		<constant value="A.__exec__():V"/>
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
		<constant value="selectBandC"/>
		<constant value="MABCD!Model;"/>
		<constant value="0"/>
		<constant value="elements"/>
		<constant value="B"/>
		<constant value="ABCD"/>
		<constant value="J.oclIsKindOf(J):J"/>
		<constant value="B.not():B"/>
		<constant value="16"/>
		<constant value="CJ.including(J):CJ"/>
		<constant value="C"/>
		<constant value="33"/>
		<constant value="J.union(J):J"/>
		<constant value="7:2-7:6"/>
		<constant value="7:2-7:15"/>
		<constant value="7:28-7:29"/>
		<constant value="7:42-7:48"/>
		<constant value="7:28-7:49"/>
		<constant value="7:2-7:51"/>
		<constant value="8:9-8:13"/>
		<constant value="8:9-8:22"/>
		<constant value="8:35-8:36"/>
		<constant value="8:49-8:55"/>
		<constant value="8:35-8:56"/>
		<constant value="8:9-8:57"/>
		<constant value="7:2-8:58"/>
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
		<constant value="13:10-15:3"/>
		<constant value="__applymodel"/>
		<constant value="NTransientLink;"/>
		<constant value="NTransientLink;.getSourceElement(S):J"/>
		<constant value="NTransientLink;.getTargetElement(S):J"/>
		<constant value="3"/>
		<constant value="J.selectBandC():J"/>
		<constant value="J.first():J"/>
		<constant value="14:11-14:14"/>
		<constant value="14:11-14:28"/>
		<constant value="14:11-14:37"/>
		<constant value="14:11-14:42"/>
		<constant value="14:3-14:42"/>
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
			<getasm/>
			<push arg="14"/>
			<push arg="8"/>
			<new/>
			<set arg="1"/>
			<getasm/>
			<pcall arg="15"/>
			<getasm/>
			<pcall arg="16"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="17" begin="0" end="24"/>
		</localvariabletable>
	</operation>
	<operation name="18">
		<context type="6"/>
		<parameters>
			<parameter name="19" type="4"/>
		</parameters>
		<code>
			<load arg="19"/>
			<getasm/>
			<get arg="3"/>
			<call arg="20"/>
			<if arg="21"/>
			<getasm/>
			<get arg="1"/>
			<load arg="19"/>
			<call arg="22"/>
			<dup/>
			<call arg="23"/>
			<if arg="24"/>
			<load arg="19"/>
			<call arg="25"/>
			<goto arg="26"/>
			<pop/>
			<load arg="19"/>
			<goto arg="27"/>
			<push arg="28"/>
			<push arg="8"/>
			<new/>
			<load arg="19"/>
			<iterate/>
			<store arg="29"/>
			<getasm/>
			<load arg="29"/>
			<call arg="30"/>
			<call arg="31"/>
			<enditerate/>
			<call arg="32"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="2" name="33" begin="23" end="27"/>
			<lve slot="0" name="17" begin="0" end="29"/>
			<lve slot="1" name="34" begin="0" end="29"/>
		</localvariabletable>
	</operation>
	<operation name="35">
		<context type="6"/>
		<parameters>
			<parameter name="19" type="4"/>
			<parameter name="29" type="36"/>
		</parameters>
		<code>
			<getasm/>
			<get arg="1"/>
			<load arg="19"/>
			<call arg="22"/>
			<load arg="19"/>
			<load arg="29"/>
			<call arg="37"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="17" begin="0" end="6"/>
			<lve slot="1" name="34" begin="0" end="6"/>
			<lve slot="2" name="38" begin="0" end="6"/>
		</localvariabletable>
	</operation>
	<operation name="39">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<getasm/>
			<pcall arg="40"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="17" begin="0" end="1"/>
		</localvariabletable>
	</operation>
	<operation name="41">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<getasm/>
			<get arg="1"/>
			<push arg="42"/>
			<call arg="43"/>
			<iterate/>
			<store arg="19"/>
			<getasm/>
			<load arg="19"/>
			<pcall arg="44"/>
			<enditerate/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="1" name="33" begin="5" end="8"/>
			<lve slot="0" name="17" begin="0" end="9"/>
		</localvariabletable>
	</operation>
	<operation name="45">
		<context type="46"/>
		<parameters>
		</parameters>
		<code>
			<push arg="28"/>
			<push arg="8"/>
			<new/>
			<load arg="47"/>
			<get arg="48"/>
			<iterate/>
			<store arg="19"/>
			<load arg="19"/>
			<push arg="49"/>
			<push arg="50"/>
			<findme/>
			<call arg="51"/>
			<call arg="52"/>
			<if arg="53"/>
			<load arg="19"/>
			<call arg="54"/>
			<enditerate/>
			<push arg="28"/>
			<push arg="8"/>
			<new/>
			<load arg="47"/>
			<get arg="48"/>
			<iterate/>
			<store arg="19"/>
			<load arg="19"/>
			<push arg="55"/>
			<push arg="50"/>
			<findme/>
			<call arg="51"/>
			<call arg="52"/>
			<if arg="56"/>
			<load arg="19"/>
			<call arg="54"/>
			<enditerate/>
			<call arg="57"/>
		</code>
		<linenumbertable>
			<lne id="58" begin="3" end="3"/>
			<lne id="59" begin="3" end="4"/>
			<lne id="60" begin="7" end="7"/>
			<lne id="61" begin="8" end="10"/>
			<lne id="62" begin="7" end="11"/>
			<lne id="63" begin="0" end="16"/>
			<lne id="64" begin="20" end="20"/>
			<lne id="65" begin="20" end="21"/>
			<lne id="66" begin="24" end="24"/>
			<lne id="67" begin="25" end="27"/>
			<lne id="68" begin="24" end="28"/>
			<lne id="69" begin="17" end="33"/>
			<lne id="70" begin="0" end="34"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="1" name="33" begin="6" end="15"/>
			<lve slot="1" name="33" begin="23" end="32"/>
			<lve slot="0" name="17" begin="0" end="34"/>
		</localvariabletable>
	</operation>
	<operation name="71">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<push arg="72"/>
			<push arg="50"/>
			<findme/>
			<push arg="73"/>
			<call arg="74"/>
			<iterate/>
			<store arg="19"/>
			<getasm/>
			<get arg="1"/>
			<push arg="75"/>
			<push arg="8"/>
			<new/>
			<dup/>
			<push arg="42"/>
			<pcall arg="76"/>
			<dup/>
			<push arg="77"/>
			<load arg="19"/>
			<pcall arg="78"/>
			<dup/>
			<push arg="79"/>
			<push arg="72"/>
			<push arg="80"/>
			<new/>
			<pcall arg="81"/>
			<pusht/>
			<pcall arg="82"/>
			<enditerate/>
		</code>
		<linenumbertable>
			<lne id="83" begin="19" end="24"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="1" name="77" begin="6" end="26"/>
			<lve slot="0" name="17" begin="0" end="27"/>
		</localvariabletable>
	</operation>
	<operation name="84">
		<context type="6"/>
		<parameters>
			<parameter name="19" type="85"/>
		</parameters>
		<code>
			<load arg="19"/>
			<push arg="77"/>
			<call arg="86"/>
			<store arg="29"/>
			<load arg="19"/>
			<push arg="79"/>
			<call arg="87"/>
			<store arg="88"/>
			<load arg="88"/>
			<dup/>
			<getasm/>
			<load arg="29"/>
			<call arg="89"/>
			<call arg="90"/>
			<get arg="38"/>
			<call arg="30"/>
			<set arg="38"/>
			<pop/>
		</code>
		<linenumbertable>
			<lne id="91" begin="11" end="11"/>
			<lne id="92" begin="11" end="12"/>
			<lne id="93" begin="11" end="13"/>
			<lne id="94" begin="11" end="14"/>
			<lne id="95" begin="9" end="16"/>
			<lne id="83" begin="8" end="17"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="3" name="79" begin="7" end="17"/>
			<lve slot="2" name="77" begin="3" end="17"/>
			<lve slot="0" name="17" begin="0" end="17"/>
			<lve slot="1" name="96" begin="0" end="17"/>
		</localvariabletable>
	</operation>
</asm>
