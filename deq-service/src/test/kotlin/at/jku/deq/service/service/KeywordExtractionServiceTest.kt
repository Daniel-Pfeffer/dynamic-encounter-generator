package at.jku.deq.service.service

import org.jsoup.Jsoup
import org.junit.jupiter.api.Test

class KeywordExtractionServiceTest {

    private val keywordExtractionService = KeywordExtractionService()

    @Test
    fun test() {
        val x = "<p>Animal lords are the immortal spirits of legendary animals. They serve as the divine protectors of animals of their kind, and they appear as hybrids of humanoids and the animals they defend. They frequently change into giant, idealized versions of the animals they&rsquo;re associated with&mdash;albeit with glowing eyes. When contending with people, they sometimes appear as humanlike beings with subtle, animal-like features. No matter their appearance, animal lords exhibit the instincts and predilections of the animals they represent, tempered by their intellect and experience.</p>\n" +
                    "<p>Most animal lords make their homes in the Beastlands, but they occasionally journey to the Feywild or other idyllic realms. They rarely travel to the Material Plane, making exceptions only when a world faces environmental disaster or droves of animals are otherwise in jeopardy.</p>\n" +
                    "<p>Among the best-known animal lords are those that represent cats, hawks, lizards, and wolves, but animal lords exist for beasts of all types. Some animal lords even embody creatures that are rare or extinct on Material Plane worlds, like megafauna or dinosaurs. Using their divine might, animal lords can summon spectral animals, channel spiritual energy, and exhibit powers associated with one of three broad groups: animal lord (forager);foragers, animal lord (hunter);hunters, or animal lord (sage);sages. These powers are tied to an animal lord&rsquo;s personality and traits associated with the creature it resembles. Roll on or choose results from the relevant Animal Lord Appearances table to inspire what creature an animal lord resembles.</p>\n" +
                    "<table class=\"table-compendium table--generic-dice\"><caption>\n" +
                    "<h4>Forager Animal Lord Appearances</h4>\n" +
                    "</caption>\n" +
                    "<thead>\n" +
                    "<tr>\n" +
                    "<th>1d10</th>\n" +
                    "<th>Bestial Shape</th>\n" +
                    "</tr>\n" +
                    "</thead>\n" +
                    "<tbody>\n" +
                    "<tr>\n" +
                    "<td>1</td>\n" +
                    "<td>Bear</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td>2</td>\n" +
                    "<td>Bee</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td>3</td>\n" +
                    "<td>Bison</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td>4</td>\n" +
                    "<td>Capybara</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td>5</td>\n" +
                    "<td>Carp</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td>6</td>\n" +
                    "<td>Rabbit</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td>7</td>\n" +
                    "<td>Rooster</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td>8</td>\n" +
                    "<td>Sloth</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td>9</td>\n" +
                    "<td>Stag</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td>10</td>\n" +
                    "<td>Vulture</td>\n" +
                    "</tr>\n" +
                    "</tbody>\n" +
                    "</table>\n" +
                    "<table class=\"table-compendium table--generic-dice\"><caption>\n" +
                    "<h4>Hunter Animal Lord Appearances</h4>\n" +
                    "</caption>\n" +
                    "<thead>\n" +
                    "<tr>\n" +
                    "<th>1d10</th>\n" +
                    "<th>Bestial Shape</th>\n" +
                    "</tr>\n" +
                    "</thead>\n" +
                    "<tbody>\n" +
                    "<tr>\n" +
                    "<td>1</td>\n" +
                    "<td>Alligator</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td>2</td>\n" +
                    "<td>Badger</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td>3</td>\n" +
                    "<td>Bat</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td>4</td>\n" +
                    "<td>Cat</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td>5</td>\n" +
                    "<td>Hawk</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td>6</td>\n" +
                    "<td>Mongoose</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td>7</td>\n" +
                    "<td>Praying mantis</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td>8</td>\n" +
                    "<td>Shark</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td>9</td>\n" +
                    "<td>Snake</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td>10</td>\n" +
                    "<td>Wolf</td>\n" +
                    "</tr>\n" +
                    "</tbody>\n" +
                    "</table>\n" +
                    "<table class=\"table-compendium table--generic-dice\"><caption>\n" +
                    "<h4>Sage Animal Lord Appearances</h4>\n" +
                    "</caption>\n" +
                    "<thead>\n" +
                    "<tr>\n" +
                    "<th>1d10</th>\n" +
                    "<th>Bestial Shape</th>\n" +
                    "</tr>\n" +
                    "</thead>\n" +
                    "<tbody>\n" +
                    "<tr>\n" +
                    "<td>1</td>\n" +
                    "<td>Coyote</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td>2</td>\n" +
                    "<td>Crow</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td>3</td>\n" +
                    "<td>Elephant</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td>4</td>\n" +
                    "<td>Lizard</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td>5</td>\n" +
                    "<td>Mouse</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td>6</td>\n" +
                    "<td>Owl</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td>7</td>\n" +
                    "<td>Salmon</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td>8</td>\n" +
                    "<td>Spider</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td>9</td>\n" +
                    "<td>Turtle</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td>10</td>\n" +
                    "<td>Whale</td>\n" +
                    "</tr>\n" +
                    "</tbody>\n" +
                    "</table>"


        val html = Jsoup.parse(x)
        val text = html.text()

        val keywords = keywordExtractionService.extractKeywords(text)
        keywords.forEach { println(it) }
    }
}