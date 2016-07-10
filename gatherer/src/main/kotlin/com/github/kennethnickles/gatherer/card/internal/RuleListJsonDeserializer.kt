package com.github.kennethnickles.gatherer.card.internal

import com.github.kennethnickles.gatherer.card.Rule
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.util.ArrayList

/**
 * @author kenneth.nickles
 * @since 2016-06-25.
 */
class RuleListJsonDeserializer : JsonDeserializer<ArrayList<Rule>> {

	@Throws(JsonParseException::class)
	override fun deserialize(json: JsonElement, typeOfT: java.lang.reflect.Type,
							 context: JsonDeserializationContext): ArrayList<Rule> {
		val text = json.asString
		val lines = text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
		val rules: ArrayList<Rule> = ArrayList()
		for (i in lines.indices) {
			val rule = Rule.from(lines[i]) ?: continue
			rules.add(rule)
		}
		return rules
	}
}