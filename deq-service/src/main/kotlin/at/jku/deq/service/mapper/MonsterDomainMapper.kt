package at.jku.deq.service.mapper

import at.jku.deq.ddbeyond.dto.*
import at.jku.deq.domain.entity.*

fun DeqMonster.toDomainMonster(): Monster = Monster(
    externalId = id,
    name = name,
    size = size.toDomainSize(),
    armorClass = armorClass.toInt(),
    avatarUrl = avatarUrl,
    possibleAlignments = alignment.toDomainAlignment(),
    type = type.toDomainMonsterType(),
    url = url,
    stats = stats.toDomainStats(),
    description = description,
    challengeRating = challengeRatingId.toInt(),
    languages = languages.map { it.toDomainLanguage() }.toSet(),
    environments = environments.map { it.toDomainEnvironment() }.toSet(),
    source = Monster.Source.EXTERNAL
)

fun Map<DDBStatType, Long>.toDomainStats(): Map<StatType, Long> = mapKeys { it.key.toDomainStatType() }

fun DDBStatType.toDomainStatType(): StatType = when (this) {
    DDBStatType.Strength -> StatType.STR
    DDBStatType.Dexterity -> StatType.DEX
    DDBStatType.Constitution -> StatType.CON
    DDBStatType.Intelligence -> StatType.INT
    DDBStatType.Wisdom -> StatType.WIS
    DDBStatType.Charisma -> StatType.CHA
}

fun DDBEnvironment.toDomainEnvironment(): Environment = when (this) {
    DDBEnvironment.Any -> Environment.Any
    DDBEnvironment.Arctic -> Environment.Arctic
    DDBEnvironment.Coastal -> Environment.Coastal
    DDBEnvironment.Desert -> Environment.Desert
    DDBEnvironment.Forest -> Environment.Forest
    DDBEnvironment.Grassland -> Environment.Grassland
    DDBEnvironment.Hill -> Environment.Hill
    DDBEnvironment.Mountain -> Environment.Mountain
    DDBEnvironment.Abyss -> Environment.Abyss
    DDBEnvironment.Acheron -> Environment.Acheron
    DDBEnvironment.AstralPlane -> Environment.AstralPlane
    DDBEnvironment.Beastlands -> Environment.Beastlands
    DDBEnvironment.ElementalChaos -> Environment.ElementalChaos
    DDBEnvironment.ElementalAir -> Environment.ElementalAir
    DDBEnvironment.ElementalEarth -> Environment.ElementalEarth
    DDBEnvironment.ElementalFire -> Environment.ElementalFire
    DDBEnvironment.ElementalWater -> Environment.ElementalWater
    DDBEnvironment.ElementalPlanes -> Environment.ElementalPlanes
    DDBEnvironment.EtherealPlane -> Environment.EtherealPlane
    DDBEnvironment.Feywild -> Environment.Feywild
    DDBEnvironment.Gehenna -> Environment.Gehenna
    DDBEnvironment.Limbo -> Environment.Limbo
    DDBEnvironment.LowerPlanes -> Environment.LowerPlanes
    DDBEnvironment.Mechanus -> Environment.Mechanus
    DDBEnvironment.NineHells -> Environment.NineHells
    DDBEnvironment.Shadowfell -> Environment.Shadowfell
    DDBEnvironment.UpperPlanes -> Environment.UpperPlanes
    DDBEnvironment.Swamp -> Environment.Swamp
    DDBEnvironment.Underdark -> Environment.Underdark
    DDBEnvironment.Underwater -> Environment.Underwater
    DDBEnvironment.Urban -> Environment.Urban
}

fun DDBLanguage.toDomainLanguage(): Language = when (this) {
    DDBLanguage.aarakocra -> Language.aarakocra
    DDBLanguage.aartuk -> Language.aartuk
    DDBLanguage.abanasinian -> Language.abanasinian
    DDBLanguage.abyssal -> Language.abyssal
    DDBLanguage.all -> Language.all
    DDBLanguage.angulotl -> Language.angulotl
    DDBLanguage.ankeshelian -> Language.ankeshelian
    DDBLanguage.aquan -> Language.aquan
    DDBLanguage.auran -> Language.auran
    DDBLanguage.birdfolk -> Language.birdfolk
    DDBLanguage.black_speech -> Language.black_speech
    DDBLanguage.blink_dog -> Language.blink_dog
    DDBLanguage.bothii -> Language.bothii
    DDBLanguage.bullywug -> Language.bullywug
    DDBLanguage.capran -> Language.capran
    DDBLanguage.celestial -> Language.celestial
    DDBLanguage.cervan -> Language.cervan
    DDBLanguage.citlanés -> Language.citlanés
    DDBLanguage.common -> Language.common
    DDBLanguage.common_sign_language -> Language.common_sign_language
    DDBLanguage.daelkyr -> Language.daelkyr
    DDBLanguage.dalish -> Language.dalish
    DDBLanguage.darakhul -> Language.darakhul
    DDBLanguage.deep_speech -> Language.deep_speech
    DDBLanguage.demodand -> Language.demodand
    DDBLanguage.derro -> Language.derro
    DDBLanguage.djaynaian -> Language.djaynaian
    DDBLanguage.dohwar -> Language.dohwar
    DDBLanguage.draconic -> Language.draconic
    DDBLanguage.druidic -> Language.druidic
    DDBLanguage.dunlendish -> Language.dunlendish
    DDBLanguage.dwarvish -> Language.dwarvish
    DDBLanguage.eluran -> Language.eluran
    DDBLanguage.elvish -> Language.elvish
    DDBLanguage.eonic -> Language.eonic
    DDBLanguage.ergot -> Language.ergot
    DDBLanguage.erina -> Language.erina
    DDBLanguage.feather_speech -> Language.feather_speech
    DDBLanguage.giant -> Language.giant
    DDBLanguage.giant_eagle -> Language.giant_eagle
    DDBLanguage.giant_elk -> Language.giant_elk
    DDBLanguage.giant_owl -> Language.giant_owl
    DDBLanguage.gibberling -> Language.gibberling
    DDBLanguage.gith -> Language.gith
    DDBLanguage.gnoll -> Language.gnoll
    DDBLanguage.gnomish -> Language.gnomish
    DDBLanguage.goblin -> Language.goblin
    DDBLanguage.godstongue -> Language.godstongue
    DDBLanguage.grell -> Language.grell
    DDBLanguage.grippli -> Language.grippli
    DDBLanguage.grung -> Language.grung
    DDBLanguage.hadozee -> Language.hadozee
    DDBLanguage.halfling -> Language.halfling
    DDBLanguage.halri -> Language.halri
    DDBLanguage.hedge -> Language.hedge
    DDBLanguage.hook_horror -> Language.hook_horror
    DDBLanguage.huginn_s_speech -> Language.huginn_s_speech
    DDBLanguage.ice_toad -> Language.ice_toad
    DDBLanguage.ignan -> Language.ignan
    DDBLanguage.infernal -> Language.infernal
    DDBLanguage.istarian -> Language.istarian
    DDBLanguage.ixitxachitl -> Language.ixitxachitl
    DDBLanguage.jerbeen -> Language.jerbeen
    DDBLanguage.kenderspeak -> Language.kenderspeak
    DDBLanguage.kharolian -> Language.kharolian
    DDBLanguage.khur -> Language.khur
    DDBLanguage.khuzdul -> Language.khuzdul
    DDBLanguage.kothian -> Language.kothian
    DDBLanguage.kraul -> Language.kraul
    DDBLanguage.kruthik -> Language.kruthik
    DDBLanguage.kuran_zoi -> Language.kuran_zoi
    DDBLanguage.lemurfolk -> Language.lemurfolk
    DDBLanguage.leonin -> Language.leonin
    DDBLanguage.loxodan -> Language.loxodan
    DDBLanguage.loxodon -> Language.loxodon
    DDBLanguage.mapach -> Language.mapach
    DDBLanguage.marquesian -> Language.marquesian
    DDBLanguage.maynah -> Language.maynah
    DDBLanguage.millitaur -> Language.millitaur
    DDBLanguage.minotaur -> Language.minotaur
    DDBLanguage.modron -> Language.modron
    DDBLanguage.naush -> Language.naush
    DDBLanguage.nerakese -> Language.nerakese
    DDBLanguage.netherese -> Language.netherese
    DDBLanguage.none -> Language.none
    DDBLanguage.nordmaarian -> Language.nordmaarian
    DDBLanguage.northern_tongue -> Language.northern_tongue
    DDBLanguage.n_warian -> Language.n_warian
    DDBLanguage.ogre -> Language.ogre
    DDBLanguage.olman -> Language.olman
    DDBLanguage.orc -> Language.orc
    DDBLanguage.orkish -> Language.orkish
    DDBLanguage.otyugh -> Language.otyugh
    DDBLanguage.primordial -> Language.primordial
    DDBLanguage.quirapu -> Language.quirapu
    DDBLanguage.quori -> Language.quori
    DDBLanguage.ravenfolk -> Language.ravenfolk
    DDBLanguage.riedran -> Language.riedran
    DDBLanguage.sahuagin -> Language.sahuagin
    DDBLanguage.sensan -> Language.sensan
    DDBLanguage.shankhi -> Language.shankhi
    DDBLanguage.sindarin -> Language.sindarin
    DDBLanguage.skitterwidget -> Language.skitterwidget
    DDBLanguage.slaad -> Language.slaad
    DDBLanguage.solamnic -> Language.solamnic
    DDBLanguage.sphinx -> Language.sphinx
    DDBLanguage.sylvan -> Language.sylvan
    DDBLanguage.telepathy -> Language.telepathy
    DDBLanguage.terran -> Language.terran
    DDBLanguage.thayan -> Language.thayan
    DDBLanguage.thieves__cant -> Language.thieves__cant
    DDBLanguage.thri_kreen -> Language.thri_kreen
    DDBLanguage.tilia -> Language.tilia
    DDBLanguage.tletlahtolli -> Language.tletlahtolli
    DDBLanguage.tlincalli -> Language.tlincalli
    DDBLanguage.tosculi -> Language.tosculi
    DDBLanguage.troglodyte -> Language.troglodyte
    DDBLanguage.trollkin -> Language.trollkin
    DDBLanguage.umber_hulk -> Language.umber_hulk
    DDBLanguage.umbral -> Language.umbral
    DDBLanguage.undercommon -> Language.undercommon
    DDBLanguage.vedalken -> Language.vedalken
    DDBLanguage.vegepygmy -> Language.vegepygmy
    DDBLanguage.void_speech -> Language.void_speech
    DDBLanguage.vulpin -> Language.vulpin
    DDBLanguage.warg_speech -> Language.warg_speech
    DDBLanguage.westron -> Language.westron
    DDBLanguage.winter_wolf -> Language.winter_wolf
    DDBLanguage.worg -> Language.worg
    DDBLanguage.xingyu -> Language.xingyu
    DDBLanguage.yeti -> Language.yeti
    DDBLanguage.yikaria -> Language.yikaria
    DDBLanguage.zabaani -> Language.zabaani
    DDBLanguage.zemnian -> Language.zemnian
    DDBLanguage.ziklight -> Language.ziklight
}

fun DDBSize.toDomainSize(): Size = when (this) {
    DDBSize.TINY -> Size.TINY
    DDBSize.SMALL -> Size.SMALL
    DDBSize.MEDIUM -> Size.MEDIUM
    DDBSize.LARGE -> Size.LARGE
    DDBSize.HUGE -> Size.HUGE
    DDBSize.GARGANTUAN -> Size.GARGANTUAN
    DDBSize.MEDIUM_OR_SMALL -> Size.MEDIUM_OR_SMALL
}

fun DDBAlignment.toDomainAlignment(): Set<Alignment> = when (this) {
    DDBAlignment.LawfulGood -> setOf(Alignment.LAWFUL_GOOD)
    DDBAlignment.NeutralGood -> setOf(Alignment.NEUTRAL_GOOD)
    DDBAlignment.ChaoticGood -> setOf(Alignment.CHAOTIC_GOOD)
    DDBAlignment.LawfulNeutral -> setOf(Alignment.LAWFUL_NEUTRAL)
    DDBAlignment.Neutral -> setOf(Alignment.NEUTRAL)
    DDBAlignment.ChaoticNeutral -> setOf(Alignment.CHAOTIC_NEUTRAL)
    DDBAlignment.LawfulEvil -> setOf(Alignment.LAWFUL_EVIL)
    DDBAlignment.NeutralEvil -> setOf(Alignment.NEUTRAL_EVIL)
    DDBAlignment.ChaoticEvil -> setOf(Alignment.CHAOTIC_EVIL)
    DDBAlignment.Unaligned -> emptySet()
    DDBAlignment.AnyAlignment -> Alignment.entries.toSet()
    DDBAlignment.DoNotUse -> emptySet()
    DDBAlignment.AnyEvilAlignment -> setOf(Alignment.LAWFUL_EVIL, Alignment.NEUTRAL_EVIL, Alignment.CHAOTIC_EVIL)
    DDBAlignment.AnyGoodAlignment -> setOf(Alignment.LAWFUL_GOOD, Alignment.NEUTRAL_GOOD, Alignment.CHAOTIC_GOOD)
    DDBAlignment.AnyChaoticAlignment -> setOf(Alignment.CHAOTIC_GOOD, Alignment.CHAOTIC_NEUTRAL, Alignment.CHAOTIC_EVIL)
    DDBAlignment.AnyLawfulAlignment -> setOf(Alignment.LAWFUL_GOOD, Alignment.LAWFUL_NEUTRAL, Alignment.LAWFUL_EVIL)
    DDBAlignment.AnyNonGoodAlignment -> setOf(
        Alignment.LAWFUL_NEUTRAL,
        Alignment.NEUTRAL,
        Alignment.CHAOTIC_NEUTRAL,
        Alignment.LAWFUL_EVIL,
        Alignment.NEUTRAL_EVIL,
        Alignment.CHAOTIC_EVIL
    )

    DDBAlignment.AnyNonLawfulAlignment -> setOf(
        Alignment.NEUTRAL_GOOD,
        Alignment.CHAOTIC_GOOD,
        Alignment.NEUTRAL,
        Alignment.CHAOTIC_NEUTRAL,
        Alignment.NEUTRAL_EVIL,
        Alignment.CHAOTIC_EVIL
    )

    DDBAlignment.TypicallyChaoticNeutral -> setOf(Alignment.CHAOTIC_NEUTRAL)
    DDBAlignment.TypicallyNeutralGood -> setOf(Alignment.NEUTRAL_GOOD)
    DDBAlignment.TypicallyLawfulGood -> setOf(Alignment.LAWFUL_GOOD)
    DDBAlignment.TypicallyChaoticEvil -> setOf(Alignment.CHAOTIC_EVIL)
    DDBAlignment.TypicallyNeutralEvil -> setOf(Alignment.NEUTRAL_EVIL)
    DDBAlignment.TypicallyChaoticGood -> setOf(Alignment.CHAOTIC_GOOD)
    DDBAlignment.TypicallyNeutral -> setOf(Alignment.NEUTRAL)
    DDBAlignment.TypicallyLawfulEvil -> setOf(Alignment.LAWFUL_EVIL)
    DDBAlignment.TypicallyLawfulNeutral -> setOf(Alignment.LAWFUL_NEUTRAL)
    DDBAlignment.AnyNeutralAlignment -> setOf(
        Alignment.NEUTRAL_GOOD,
        Alignment.NEUTRAL,
        Alignment.NEUTRAL_EVIL,
        Alignment.CHAOTIC_NEUTRAL,
        Alignment.LAWFUL_NEUTRAL
    )

    DDBAlignment.AnyNonChaoticAlignment -> setOf(
        Alignment.LAWFUL_GOOD,
        Alignment.NEUTRAL_GOOD,
        Alignment.LAWFUL_NEUTRAL,
        Alignment.NEUTRAL,
        Alignment.LAWFUL_EVIL,
        Alignment.NEUTRAL_EVIL
    )
}

fun DDBMonsterType.toDomainMonsterType(): MonsterType = when (this) {
    DDBMonsterType.ABERRATION -> MonsterType.ABERRATION
    DDBMonsterType.BEAST -> MonsterType.BEAST
    DDBMonsterType.CELESTIAL -> MonsterType.CELESTIAL
    DDBMonsterType.CONSTRUCT -> MonsterType.CONSTRUCT
    DDBMonsterType.DRAGON -> MonsterType.DRAGON
    DDBMonsterType.ELEMENTAL -> MonsterType.ELEMENTAL
    DDBMonsterType.FEY -> MonsterType.FEY
    DDBMonsterType.FIEND -> MonsterType.FIEND
    DDBMonsterType.GIANT -> MonsterType.GIANT
    DDBMonsterType.HUMANOID -> MonsterType.HUMANOID
    DDBMonsterType.MONSTROSITY -> MonsterType.MONSTROSITY
    DDBMonsterType.OOZE -> MonsterType.OOZE
    DDBMonsterType.PLANT -> MonsterType.PLANT
    DDBMonsterType.UNDEAD -> MonsterType.UNDEAD
    DDBMonsterType.NOOP, DDBMonsterType.NOOP2 -> throw IllegalArgumentException("Invalid monster type, consider not using ddbeyond next time, dear developer")
}
