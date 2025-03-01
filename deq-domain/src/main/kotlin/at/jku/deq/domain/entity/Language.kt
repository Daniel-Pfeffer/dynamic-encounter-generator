package at.jku.deq.domain.entity


// do not touch dead inside, generated code via
/**
 * Enum representing the languages in D&D
 * Do not touch, dead inside;
 * generated code via
 * ```js
 * let dict = {};
 * (document.querySelector("#select2-drop > ul").innerText.split("\n")
 *     .map(x => {
 *         let humanreadable_name = x;
 *         let keyword_name = x.toLowerCase().replace(/[ '`'’-]/g, "_");
 *         if (dict[keyword_name]) return null
 *         dict[keyword_name] = true;
 *         return keyword_name + "(\"" + humanreadable_name + "\")";
 *     })).filter(x => x !== null)
 *     .join();
 * ```
 *
 */
enum class Language(val humanReadable: String) {
    aarakocra("Aarakocra"), aartuk("Aartuk"), abanasinian("Abanasinian"), abyssal("Abyssal"), all("All"), angulotl("Angulotl"),
    ankeshelian("Ankeshelian"),
    aquan("Aquan"), auran("Auran"), birdfolk("Birdfolk"), black_speech("Black Speech"), blink_dog("Blink Dog"), bothii("Bothii"), bullywug(
        "Bullywug"
    ),
    capran("Capran"), celestial("Celestial"), cervan("Cervan"), citlanés("Citlanés"), common("Common"), common_sign_language(
        "Common Sign Language"
    ),
    daelkyr("Daelkyr"), dalish("Dalish"), darakhul("Darakhul"), deep_speech("Deep Speech"), demodand("Demodand"), derro(
        "Derro"
    ),
    djaynaian("Djaynaian"), dohwar("Dohwar"), draconic("Draconic"), druidic("Druidic"), dunlendish("Dunlendish"), dwarvish(
        "Dwarvish"
    ),
    eluran("Eluran"), elvish("Elvish"), eonic("Eonic"), ergot("Ergot"), erina("Erina"), feather_speech("Feather Speech"), giant(
        "Giant"
    ),
    giant_eagle("Giant Eagle"), giant_elk("Giant Elk"), giant_owl("Giant Owl"), gibberling("Gibberling"), gith("Gith"), gnoll(
        "Gnoll"
    ),
    gnomish("Gnomish"), goblin("Goblin"), godstongue("Godstongue"), grell("Grell"), grippli("Grippli"), grung("Grung"), hadozee(
        "Hadozee"
    ),
    halfling("Halfling"), halri("Halri"), hedge("Hedge"), hook_horror("Hook Horror"), huginn_s_speech("Huginn's Speech"), ice_toad(
        "Ice Toad"
    ),
    ignan("Ignan"), infernal("Infernal"), istarian("Istarian"), ixitxachitl("Ixitxachitl"), jerbeen("Jerbeen"), kenderspeak(
        "Kenderspeak"
    ),
    kharolian("Kharolian"), khur("Khur"), khuzdul("Khuzdul"), kothian("Kothian"), kraul("Kraul"), kruthik("Kruthik"), kuran_zoi(
        "Kuran'zoi"
    ),
    lemurfolk("Lemurfolk"), leonin("Leonin"), loxodan("Loxodan"), loxodon("Loxodon"), mapach("Mapach"), marquesian("Marquesian"), maynah(
        "Maynah"
    ),
    millitaur("Millitaur"), minotaur("Minotaur"), modron("Modron"), naush("Naush"), nerakese("Nerakese"), netherese("Netherese"), none(
        "None"
    ),
    nordmaarian("Nordmaarian"), northern_tongue("Northern Tongue"), n_warian("N'warian"), ogre("Ogre"), olman("Olman"), orc(
        "Orc"
    ),
    orkish("Orkish"), otyugh("Otyugh"), primordial("Primordial"), quirapu("Quirapu"), quori("Quori"), ravenfolk("Ravenfolk"), riedran(
        "Riedran"
    ),
    sahuagin("Sahuagin"), sensan("Sensan"), shankhi("Shankhi"), sindarin("Sindarin"), skitterwidget("Skitterwidget"), slaad(
        "Slaad"
    ),
    solamnic("Solamnic"), sphinx("Sphinx"), sylvan("Sylvan"), telepathy("Telepathy"), terran("Terran"), thayan("Thayan"), thieves__cant(
        "Thieves' Cant"
    ),
    thri_kreen("Thri-kreen"), tilia("Tilia"), tletlahtolli("Tletlahtolli"), tlincalli("Tlincalli"), tosculi("Tosculi"), troglodyte(
        "Troglodyte"
    ),
    trollkin("Trollkin"), umber_hulk("Umber Hulk"), umbral("Umbral"), undercommon("Undercommon"), vedalken("Vedalken"), vegepygmy(
        "Vegepygmy"
    ),
    void_speech("Void Speech"), vulpin("Vulpin"), warg_speech("Warg-speech"), westron("Westron"), winter_wolf("Winter Wolf"), worg(
        "Worg"
    ),
    xingyu("Xingyu"), yeti("Yeti"), yikaria("Yikaria"), zabaani("Zabaani"), zemnian("Zemnian"), ziklight("Ziklight")
}