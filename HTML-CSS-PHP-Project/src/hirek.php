<?php
session_start();
include "classes/Komment.php";      // a Komment osztály beágyazása
include "kozos.php";                // a hozzászólások betöltéséért és elmentéséért felelő függvényeket tartalmazó fájl beágyazása

$kommentek = loadComments("comments.txt");  // a meglévő hozzászólások betöltése fájlból
$kommentek = array_reverse($kommentek);     // a tömb megfordítása (így a legkésőbb fájlba írt komment lesz elöl)

// a felhasználó által írt új komment feldolgozása

if (isset($_POST["kommentel"])) {
    $hibak = [];    // egy tömb az űrlapfeldolgozás során adódó lehetséges hibáknak

    if (!isset($_POST["hozzaszolas"]) || trim($_POST["hozzaszolas"]) === "") {  // ha a komment szövegét nem adták meg...
        $hibak[] = "HIBA: Add meg a hozzászólás szövegét!";
    }

    if (strlen($_POST["hozzaszolas"]) > 500) {    // ha a komment szövege 500 karakternél hosszabb...
        $hibak[] = "HIBA: A hozzászólás nem lehet hosszabb 500 karakternél!";
    }

    $felhasznalonev = $_SESSION["user"]["username"];    // a Komment osztály példányosításához szükséges adatok
    $datum = new DateTime();
    $datum->setTimezone(new DateTimeZone("Europe/Budapest"));
    $szoveg = trim($_POST["hozzaszolas"]);

    if (count($hibak) === 0) {
        $uj_komment = new Komment($felhasznalonev, $datum, $szoveg);    // egy új Komment objektum példányosítása
        $kommentek[] = $uj_komment;                                     // az új komment beszúrása az eddigi kommenteket tároló tömbbe
        saveComments("comments.txt", $kommentek);                       // kommentek elmentése fájlba

        header("Location: hirek.php");                                  // a weboldal újratöltése
    }
}
?>

<!DOCTYPE html>
<html lang="hu">

<head>
    <title>Hírek - Óvervécs</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <link rel="icon" type="image/x-icon" href="./assets/base/Logo.svg">
    <link rel="stylesheet" href="./css/style.css" />

    <style>
        iframe {
            display: block;
            margin: auto;
            width: 80%;
            aspect-ratio: 16/9;
        }

        #articles {
            display: flex;
            align-items: center;
            flex-direction: column;
        }

        article {
            align-items: center;
            width: 70%;
            margin-bottom: 4rem;
            padding: 2rem;
            padding-top: 0.5rem;
            background-color: var(--secondary-backgound-color);
            border-radius: 1rem;
        }

        #kommentek{

            width: 70%;
            margin-bottom: 4rem;
            padding: 2rem;
            padding-top: 0.5rem;
            background-color: var(--secondary-backgound-color);
            border-radius: 1rem;

        }
        


        article:first-child {
            transition: box-shadow 0.5s;
        }

        article:first-child:hover {
            box-shadow: 0.5rem 0.5rem 0.5rem rgba(0, 0, 0, 0.5);
        }

        article:nth-of-type(2) {
            transition: box-shadow 0.5s;
        }

        article:nth-of-type(2):hover {
            box-shadow: 0.5rem 0.5rem 0.5rem rgba(0, 0, 0, 0.5);
        }

        article:last-child {
            transition: box-shadow 0.5s;
        }

        article:last-child:hover {
            box-shadow: 0.5rem 0.5rem 0.5rem rgba(0, 0, 0, 0.5);
        }
    </style>
</head>

<body>
    <header>
        <span style="float: left; padding-left: 2rem">
            <a href="./index.html" style="display: inline">
                <span class="logo">
                    <img class="imgLogo" src="./assets/base/Logo.svg" alt="Óvervécs logo" />
                    <img class="imgLogoText" src="./assets/base/Text_logo_light.svg" alt="Óvervécs" />
                </span>
            </a>
        </span>
        <div style="float: right; padding-right: 2rem">
            <nav style="display: inline">
                <span class="menubar">
                    <a href="./hirek.php" class="active-page">Hírek</a>
                    <a href="./jatekTortenete.html">Története</a>
                    <a href="./jatekmodok.html">Játékmódok</a>
                    <a href="./hosokBemutatasa.html">Hősök</a>
                    <a href="./esemenyek.html">Események</a>
                </span>
            </nav>

            <a href="./bejelentkezes.php" style="display: inline">
                <span class="logo">
                    <img class="imgLogin" src="./assets/base/Login.svg" alt="Bejelentkezés" />
                </span>
            </a>
        </div>
    </header>
    <main>
        <!--                    Content                    -->

        <h1>Legfrisebb hírek az Overwatch-ról</h1>
        <p style="text-align: center; font-style: italic; margin-bottom: 6rem;">Ezen az oldalon értesülhetsz a játékkal
            kapcsolatos legfrissebb hírekről és cikkekről.</p>
        <section id="articles">
            <article>
                <h2>Ingyenessé vált a játék az ünnepekre</h2>
                <img src="assets/hirek/articleFree.jpg" alt="Ingyenes az Overwatch" style="display: block; margin: auto; width: 80%;">
                <p>Az ünnepek alatt a Blizzard Entertainment az Overwatchot ingyenesen játszható játékként kínálja
                    konzolokon és PC-n. Overwatch hasonló ingyenes próbaverziója már a tavalyi ünnepi szezonban is
                    futott, ahol az újonnan érkezők korlátozott ideig ingyenesen ugorhattak be a lövöldébe. A tavaly
                    decemberi free-to-play kínálat ráadásul olyan kedvezményekkel együtt indult, amelyek jelentősen
                    csökkentették az egyre népszerűbb online lövöldözős játék teljes árát.</p>
                <p>A GameSpot beszámolója szerint az Overwatch legújabb ingyenes próbaverziója az ünnepi szezon
                    hátralévő részében fog pörögni, így a PC-s, PlayStation-ös és Xbox-os játékosok január 2-ig,
                    vasárnapig ingyenesen nyüstölhetik a címet. A korábbi ingyenes próbaverziókhoz hasonlóan az újonnan
                    érkezők is hozzáférhetnek a többjátékos cucc minden aspektusához, beleértve az egyes játékmódokat,
                    mind a 32 hős karaktert és a 28 elérhető pályát. Ráadásul az éves Winter Wonderland esemény, amely
                    január 6-án, csütörtökön ér véget, számos egyedi skinnel és játékmóddal büszkélkedhet, amelyeket a
                    rajongók és az újoncok egyaránt kipróbálhatnak, amíg az ünnepi hangulat tart.</p>
            </article>
            <article>
                <h2>Jesse McCree új neve Cole Cassidy lesz</h2>
                <img src="assets/hirek/articleNewName.jpg" alt="Cole Cassidy" style="display: block; margin: auto; width: 80%;">
                <ul>
                    <li>Jesse McCree új nevet kap</li>
                    <li>az eredeti nevét a zaklatási botrányban résztvevő fejlesztő után kapta</li>
                </ul>
                <p>Kínos, ha az ember olyasvalakiről nevezi el a karakterét, akit később többször is néven neveznek a
                    céget érintő zaklatási botrányok során. Így járt a Blizzard. Az Overwatch cowboy karaktere ugyanis a
                    volt vezető dizájner, Jesse McCree után kapta a nevét. Ám sajnos az említett urat a Blizzard-botrány
                    során többször is megnevezték, mint az egyik olyan munkatársat, akinek köszönhetően a stúdióban
                    toxikus volt a légkör.</p>
                <p>Szóval az Overwatch Jesse McCree kedden egy frissítés keretében új nevet kap, onnantól Cole
                    Cassidy-ként hivatkozhatunk rá. Amúgy nem ő az egyetlen karakter, akit érintett a Blizzard-botrány.
                    A múlt hónapban több NPC-t is eltávolítottak a World of Warcraft-ból, amik a szexuális zaklatással
                    vádolt, korábbi vezető kreatív direktor, Alex Afrasiabira hivatkoznak.</p>
            </article>
            <article>
                <h2>Élesedett a cross-play</h2>
                <iframe src="https://www.youtube-nocookie.com/embed/DEvKTSF7CMM" title="YouTube video player" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                <!--<img src="assets/hirek/articleCrossPlay.jpg" alt="Cole Cassidy" style="display: block; margin: auto; width: 80%;">-->
                <ul>
                    <li>Már elérhető a cross-play az Overwatch-ban</li>
                    <li>A játékosok ezentúl platformtól függetlenül együtt játszhatnak</li>
                </ul>
                <p>A Blizzard korábbi ígérete szerint ma végre megérkezett az Overwatch cross-play funkciója, melynek
                    köszönhetően mostantól platformtól függetlenül összecsaphatnak a PC, PlayStation, Xbox és Nintendo
                    Switch játékosok. A Competitive Play keretében a statok megkímélése érdekében a funkció nem lesz
                    elérhető. A cross-play élesedése alkalmából továbbá 2021 végéig minden játékos markát egy Golden
                    Loot Box üti, aminek megszerzéséhez elég bejelentkezni a játékba.</p>


            </article>
            <div id="kommentek">
            <h3>Hozzászólások</h3>
            <?php if (count($kommentek) === 0) { ?>
                <p>Még nem érkezett hozzászólás.</p>
            <?php } else { ?>
                <!-- Hozzászólások dinamikus kilistázása PHP segítségével -->

                <?php foreach ($kommentek as $komment) { ?>
                    <div class="komment">
                        <img src="<?php echo getProfilePicture('images', $komment->getSzerzo(), ['png', 'jpg', 'jpeg']); ?>" alt="Profilkép" class="profile" height="100" />
                        <h4><?php echo $komment->getSzerzo(); ?></h4>
                        <div class="datum"><?php echo $komment->getDatum()->format('Y-m-d H:i:s'); ?></div>
                        <div class="szoveg" style="padding-bottom: 2rem;"><?php echo $komment->getSzoveg(); ?></div>
                    </div>
                <?php } ?>
            <?php } ?>

            <?php if (isset($_SESSION["user"])) { ?>
                <div class="komment-iras">
                    <p><b>Írd meg a véleményedet! (max. 500 karakter)</b></p>
                    <form class="komment-urlap" action="hirek.php" method="POST">
                        <textarea name="hozzaszolas" rows="10" cols="100" maxlength="500" required=""></textarea> <br />
                        <input type="submit" name="kommentel" value="Közzététel" />
                    </form>
                </div>
            <?php } ?>
            </div>
            
        </section>

        <!--                    Content                    -->
    </main>

    <footer>Copyright 2022 - Halápi Vivien, Barna Gergely</footer>
</body>

</html>