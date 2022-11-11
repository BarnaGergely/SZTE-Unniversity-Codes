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

    $felhasznalonev = $_SESSION["user"]["felhasznalonev"];    // a Komment osztály példányosításához szükséges adatok
    $datum = new DateTime();
    $datum->setTimezone(new DateTimeZone("Europe/Budapest"));
    $szoveg = trim($_POST["hozzaszolas"]);

    if (count($hibak) === 0) {
      $uj_komment = new Komment($felhasznalonev, $datum, $szoveg);    // egy új Komment objektum példányosítása
      $kommentek[] = $uj_komment;                                     // az új komment beszúrása az eddigi kommenteket tároló tömbbe
      saveComments("comments.txt", $kommentek);                       // kommentek elmentése fájlba

      header("Location: index.php");                                  // a weboldal újratöltése
    }
  }
?>

<!DOCTYPE html>
<html lang="hu">
  <head>
    <title>Főoldal</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="css/style.css"/>
  </head>
  <body>
    <?php if (isset($hibak) && count($hibak) > 0) { ?>
      <!-- A hozzászólás elmentése során adódó hibák kilistázása -->

      <div class="hibak">
        <?php foreach ($hibak as $hiba) { echo "<p>" . $hiba . "</p>"; } ?>        
      </div>
    <?php } ?>

    <main>
      <!-- Fejléc -->

      <header>
        <h1>Az én klassz weboldalam</h1>
      </header>

      <!-- Navigációs Menü -->

      <nav>
        <ul>
          <li class="active"><a href="index.php">Főoldal</a></li>
          <?php if (isset($_SESSION["user"])) { ?>
            <li><a href="profile.php">Profilom</a></li>
            <li><a href="logout.php">Kijelentkezés</a></li>
          <?php } else { ?>
            <li><a href="login.php">Bejelentkezés</a></li>
            <li><a href="signup.php">Regisztráció</a></li>
          <?php } ?>
        </ul>
      </nav>

      <!-- Az oldal tartalma -->

      <section id="content">
        <h2>Főoldal</h2>
        <hr/>

        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent malesuada lectus quis nibh volutpat, in sollicitudin nunc sagittis. Interdum et malesuada fames ac ante ipsum primis in faucibus. Fusce tincidunt consectetur arcu, sed pellentesque nisl ullamcorper vitae. Cras scelerisque pellentesque eros, ac elementum enim eleifend et. Ut posuere metus vitae ultrices bibendum. Duis varius eget risus quis faucibus. Quisque sem lectus, varius a tellus nec, blandit vehicula ipsum. Duis eget neque nulla. Phasellus interdum velit vitae sollicitudin eleifend. Duis a luctus ante. Proin luctus fringilla urna, a venenatis orci fringilla ultricies. Donec sit amet tellus et erat fermentum ultrices. Donec fermentum, sapien ac pulvinar rutrum, lacus quam maximus urna, a efficitur lectus purus elementum justo. Cras gravida ex urna, eget dignissim ipsum dictum et.</p>
        <p>Maecenas et convallis felis. Maecenas id justo in quam dapibus cursus. Nunc eu convallis magna, id dictum ante. Maecenas rutrum quam a augue finibus, quis cursus arcu lobortis. Morbi elementum nibh nec tortor vestibulum porttitor. Cras auctor in ex vitae commodo. Aenean ut maximus nulla. Donec massa lorem, efficitur ac sem a, imperdiet interdum turpis. Praesent sed lacinia magna. Suspendisse a dui non massa aliquam iaculis id tempus nisl. Integer ut eros in massa elementum rutrum. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nam non elit erat. Sed consectetur lectus in dui euismod placerat. Maecenas dictum neque elit, et lobortis dolor aliquet ut.</p>
        <p>Duis accumsan rutrum leo, quis vulputate augue condimentum non. In id dui id lectus pellentesque ornare in at ipsum. Morbi accumsan urna vitae sagittis imperdiet. Proin iaculis nec ex vel malesuada. Phasellus ultrices in sem non sodales. Donec laoreet nulla non mollis dictum. Integer sed pharetra nisl. Aliquam a turpis at lacus euismod pellentesque quis non nibh. Curabitur porttitor nisl vehicula luctus sagittis. Morbi ut erat placerat, auctor mauris eget, gravida lorem. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Sed in turpis diam.</p>
        <p>Nullam accumsan, quam non congue pulvinar, est lectus suscipit neque, id aliquet eros leo a velit. Nulla vel urna faucibus, aliquet dolor eget, auctor arcu. Morbi tempus velit a mi mattis gravida. Quisque consectetur urna nisl, feugiat luctus dui vestibulum in. Proin varius sapien in ullamcorper convallis. Mauris sit amet aliquet orci. Nulla rhoncus nulla eu lorem feugiat tincidunt. Vestibulum nec mauris erat. Proin eu ante blandit, accumsan elit malesuada, pellentesque leo. Duis iaculis neque sed aliquet eleifend. Nullam volutpat turpis vitae enim rutrum aliquam. Donec justo nibh, malesuada vitae felis eu, porta eleifend erat.</p>
        <p>Proin ut pellentesque nibh. Nam vel massa placerat lectus vestibulum tempus. Aenean ac tempus lorem. Suspendisse vehicula blandit nibh sed suscipit. Praesent sed rutrum libero. Phasellus eleifend arcu massa. In a quam elementum, lacinia nibh ut, congue mi. Pellentesque vel lectus vel erat lacinia auctor. Nullam sagittis arcu feugiat leo condimentum euismod. Ut sagittis eu ligula vitae viverra.</p>

        <hr/>
        <h3>Hozzászólások</h3>

        <?php  if (count($kommentek) === 0) { ?>
          <p>Még nem érkezett hozzászólás.</p>
        <?php } else { ?>
          <!-- Hozzászólások dinamikus kilistázása PHP segítségével -->

          <?php foreach ($kommentek as $komment) { ?>
            <div class="komment">
              <img src="<?php echo getProfilePicture('images', $komment->getSzerzo(), ['png', 'jpg', 'jpeg']); ?>" alt="Profilkép" class="profile"/>
              <h4><?php echo $komment->getSzerzo(); ?></h4>
              <div class="datum"><?php echo $komment->getDatum()->format('Y-m-d H:i:s'); ?></div>
              <div class="szoveg"><?php echo $komment->getSzoveg(); ?></div>
            </div>
          <?php } ?>
        <?php } ?>

        <?php if (isset($_SESSION["user"])) { ?>
          <div class="komment-iras">
            <p><b>Írd meg a véleményedet! (max. 500 karakter)</b></p>
            <form class="komment-urlap" action="index.php" method="POST">
              <textarea name="hozzaszolas" rows="10" cols="100" maxlength="500" required=""></textarea> <br/>
              <input type="submit" name="kommentel" value="Közzététel"/>
            </form>
          </div>
        <?php } ?>
      </section>

      <!-- Lábléc -->

      <footer>
        <p>&copy; Webtervezés gyakorlat</p>
      </footer>
    </main>
  </body>
</html>