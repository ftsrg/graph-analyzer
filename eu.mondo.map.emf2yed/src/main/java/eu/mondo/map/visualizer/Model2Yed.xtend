package eu.mondo.map.visualizer

import java.util.HashMap
import java.util.List
import java.util.Map
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference

class Model2Yed{
	
	def public transform(List<EObject> model) {
		val Map<EObject, Integer> objectToID = new HashMap
		
		'''
		graph
		[
			«FOR object:model SEPARATOR '\n'»
				«this.transformObject(object,objectToID.size+1,objectToID)»
			«ENDFOR»
			«FOR from:model»
				«FOR reference:from.eClass.EAllReferences»
					«IF reference.isMany»
					«FOR target : from.eGet(reference) as List<EObject>»
						«reference.transformRelation(from,target,objectToID)»
					«ENDFOR»
					«ELSE»
						«reference.transformRelation(from,from.eGet(reference) as EObject,objectToID)»
					«ENDIF»
				«ENDFOR»
			«ENDFOR»
		]
		'''
	}
	
	val protected titleSize = 16
	val protected attributeSize = 14
	val protected borderDistance = 6
	val protected attributeBorderDistance = 8
	val protected ratio = 11.0/20.0
	
	def protected transformObject(EObject object,int id,Map<EObject, Integer> objectToID){
		val title = object.transormTitle(id)
		val attributes = object.eClass.
			getEAllAttributes.map[transformAttribute(object.eGet(it,true))]
		
		var double width = title.length*titleSize + borderDistance*2;
		for(x:attributes.map[length*attributeSize + borderDistance*2 + attributeBorderDistance*2])
			width = Math::max(width,x)
		width = width*ratio
		
		val height = Math::max(
			titleSize+4,
			(attributes.size+1)*attributeSize + borderDistance*2)
			
		objectToID.put(object,id)
		
		'''
		node
			[
				id	«id»
				graphics
				[
					w	«width»
					h	«height»
					type	"rectangle"
					fill	"#FFFFFF"
					fill2	"#FFFFFF"
					outline	"#000000"
				]
				LabelGraphics
				[
					text	"«title»"
					outline	"#000000"
					fill	"#FFFFFF"
					fontSize	«titleSize»
					fontName	"Monospace"
					autoSizePolicy	"node_width"
					anchor	"t"
					borderDistance	0.0
				]
				LabelGraphics
				[
					text	"
		«FOR attribute : attributes»
		«attribute»
		«ENDFOR»"
					fontSize	«attributeSize»
					fontName	"Consolas"
					alignment	"left"
					anchor	"tl"
					borderDistance	«borderDistance»
				]
			]
		'''
	}
	
	def protected transormTitle(EObject object,int id)
		 '''o«id»: «object.eClass.name»'''
	
	def protected dispatch transformAttribute(EAttribute attribute, Object value) '''«attribute.name» = «value»'''
	def protected dispatch transformAttribute(EAttribute attribute, List<?> values) '''«attribute.name» = [«values.map[transformAttributeValue(attribute.EType.instanceClass)].join(",")»]'''
	
	def dispatch transformAttributeValue(String value, Class<?> type) '''"«value»"'''
	def dispatch transformAttributeValue(double value,Class<?> type) '''«value»'''
	def dispatch transformAttributeValue(int value,Class<?> type) '''«value»'''
	def dispatch transformAttributeValue(Object value,Class<?> type) {
		if(type.isEnum) '''::«value.toString»'''
		else return '''«value»'''
	}	
	
	def protected transformRelation(EReference reference, EObject source, EObject target,Map<EObject, Integer> objectToID){
		if(source!=null && target!=null) {
			'''
			edge
			[
				source	«objectToID.get(source)»
				target	«objectToID.get(target)»
				graphics
				[
					fill	"#000000"
					«IF reference.containment»
						width	3
					«ENDIF»
					targetArrow	"standard"
				]
				LabelGraphics
				[
					text	"«reference.name»"
					fontSize	14
					fontName	"Consolas"
					configuration	"AutoFlippingLabel"
					model	"six_pos"
					position	"thead"
				]
			]
			'''}
		else return ''''''
	}
}