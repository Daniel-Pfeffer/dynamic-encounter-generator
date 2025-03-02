package at.jku.deq.ddbeyond.dto

import kotlin.enums.enumEntries

data class DeqMonster(
    val id: Long,
    val name: String,
    val url: String,
    val avatarUrl: String,
    val description: String,
    val size: DDBSize,
    val type: DDBMonsterType,
    val alignment: DDBAlignment,
    val stats: Map<DDBStatType, Long>,
    val armorClass: Long,
    val challengeRatingId: Long,
    val environments: Set<DDBEnvironment>,
    val languages: Set<DDBLanguage>
)

inline fun <reified T : Enum<T>> fromId(id: Long): T {
    try {
        return when (T::class) {
            DDBSkill::class -> enumEntries<T>()[id.toInt() - 2] // dunno why skills are magically 2 indexed

            DDBSize::class -> when (id) {
                10L -> DDBSize.MEDIUM_OR_SMALL as T
                else -> enumEntries<T>()[id.toInt() - 2]
            }

            else -> enumEntries<T>()[id.toInt() - 1]
        }
    } catch (e: IndexOutOfBoundsException) {
        throw IllegalArgumentException("No enum constant ${T::class.simpleName} with id $id")
    }
}

enum class DDBMonsterType {
    ABERRATION, BEAST, CELESTIAL,
    CONSTRUCT, NOOP/*why is there another gap?*/, DRAGON, ELEMENTAL,
    FEY, FIEND, GIANT,
    HUMANOID, NOOP2/*another gap????*/, MONSTROSITY, OOZE,
    PLANT, UNDEAD
}

enum class DDBEnvironment {
    Any, Arctic, Coastal,
    Desert, Forest, Grassland,
    Hill, Mountain, Abyss,
    Acheron, AstralPlane,
    Beastlands, ElementalChaos,
    ElementalAir, ElementalEarth,
    ElementalFire, ElementalWater,
    ElementalPlanes, EtherealPlane,
    Feywild, Gehenna, Limbo,
    LowerPlanes, Mechanus, NineHells,
    Shadowfell, UpperPlanes, Swamp,
    Underdark, Underwater, Urban;
}

enum class DDBLanguage {
    aarakocra, aartuk, abanasinian, abyssal, all, angulotl, ankeshelian, aquan, auran, birdfolk, black_speech, blink_dog, bothii, bullywug, capran, celestial, cervan, citlanés, common, common_sign_language, daelkyr, dalish, darakhul, deep_speech, demodand, derro, djaynaian, dohwar, draconic, druidic, dunlendish, dwarvish, eluran, elvish, eonic, ergot, erina, feather_speech, giant, giant_eagle, giant_elk, giant_owl, gibberling, gith, gnoll, gnomish, goblin, godstongue, grell, grippli, grung, hadozee, halfling, halri, hedge, hook_horror, huginn_s_speech, ice_toad, ignan, infernal, istarian, ixitxachitl, jerbeen, kenderspeak, kharolian, khur, khuzdul, kothian, kraul, kruthik, kuran_zoi, lemurfolk, leonin, loxodan, loxodon, mapach, marquesian, maynah, millitaur, minotaur, modron, naush, nerakese, netherese, none, nordmaarian, northern_tongue, n_warian, ogre, olman, orc, orkish, otyugh, primordial, quirapu, quori, ravenfolk, riedran, sahuagin, sensan, shankhi, sindarin, skitterwidget, slaad, solamnic, sphinx, sylvan, telepathy, terran, thayan, thieves__cant, thri_kreen, tilia, tletlahtolli, tlincalli, tosculi, troglodyte, trollkin, umber_hulk, umbral, undercommon, vedalken, vegepygmy, void_speech, vulpin, warg_speech, westron, winter_wolf, worg, xingyu, yeti, yikaria, zabaani, zemnian, ziklight
}

enum class DDBSize {
    TINY,
    SMALL,
    MEDIUM,
    LARGE,
    HUGE,
    GARGANTUAN,
    MEDIUM_OR_SMALL,
}

enum class DDBAlignment {
    LawfulGood,
    NeutralGood,
    ChaoticGood,
    LawfulNeutral,
    Neutral,
    ChaoticNeutral,
    LawfulEvil,
    NeutralEvil,
    ChaoticEvil,
    Unaligned,
    AnyAlignment,
    DoNotUse/*For some reason dnd beyond uses 1-11 and then 13-30, why I don't have a f*cking clue*/,
    AnyEvilAlignment,
    AnyGoodAlignment,
    AnyChaoticAlignment,
    AnyLawfulAlignment,
    AnyNonGoodAlignment,
    AnyNonLawfulAlignment,
    TypicallyChaoticNeutral,
    TypicallyNeutralGood,
    TypicallyLawfulGood,
    TypicallyChaoticEvil,
    TypicallyNeutralEvil,
    TypicallyChaoticGood,
    TypicallyNeutral,
    TypicallyLawfulEvil,
    TypicallyLawfulNeutral,
    AnyNeutralAlignment,
    AnyNonChaoticAlignment
}

enum class DDBStatType(private val id: Long) {
    Strength(1),
    Dexterity(2),
    Constitution(3),
    Intelligence(4),
    Wisdom(5),
    Charisma(6);
}

enum class DDBSenseType(private val id: Long) {
    Blindsight(1),
    Darkvision(2),
    Tremorsense(3),
    Truesight(4)
}

enum class DDBSkill(private val id: Long) {
    // why no 1????????
    // ordered by StatType and alphabetical - actually see filter in dndbeyond dunno why no 1?
    Athletics(2),
    Acrobatics(3),
    SleightOfHand(4),
    Stealth(5),
    Arcana(6),
    History(7),
    Investigation(8),
    Nature(9),
    Religion(10),
    AnimalHandling(11),
    Insight(12),
    Medicine(13),
    Perception(14),
    Survival(15),
    Deception(16),
    Intimidation(17),
    Performance(18),
    Persuasion(19),
}